package net.minecraft.server;

import java.net.Socket;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

public class NetLoginHandler extends NetHandler {

    public static Logger logger = Logger.getLogger("Minecraft");
    private static Random random = new Random();
    public NetworkManager networkManager;
    public boolean c = false;
    private MinecraftServer server;
    private int f = 0;
    private String g = null;
    private Packet1Login h = null;
    private String loginKey = "";

    public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s) {
        this.server = minecraftserver;
        this.networkManager = new NetworkManager(socket, s, this);
        this.networkManager.f = 0;
    }

    public void a() {
        if (this.h != null) {
            this.b(this.h);
            this.h = null;
        }

        if (this.f++ == 600) {
            this.disconnect("Took too long to log in");
        } else {
            this.networkManager.b();
        }
    }

    public void disconnect(String s) {
        try {
            logger.info("Disconnecting " + this.getName() + ": " + s);
            this.networkManager.queue(new Packet255KickDisconnect(s));
            this.networkManager.d();
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(Packet2Handshake packet2handshake) {
        if (this.server.onlineMode) {
            this.loginKey = Long.toString(random.nextLong(), 16);
            this.networkManager.queue(new Packet2Handshake(this.loginKey));
        } else {
            this.networkManager.queue(new Packet2Handshake("-"));
        }
    }

    public void a(Packet1Login packet1login) {
        this.g = packet1login.name;
        if (packet1login.a != 28) {
            if (packet1login.a > 28) {
                this.disconnect("Outdated server!");
            } else {
                this.disconnect("Outdated client!");
            }
        } else {
            if (!this.server.onlineMode) {
                this.b(packet1login);
            } else {
                (new ThreadLoginVerifier(this, packet1login)).start();
            }
        }
    }

    public void b(Packet1Login packet1login) {
        EntityPlayer entityplayer = this.server.serverConfigurationManager.attemptLogin(this, packet1login.name);

        if (entityplayer != null) {
            this.server.serverConfigurationManager.b(entityplayer);
            entityplayer.spawnIn(this.server.getWorldServer(entityplayer.dimension));
            entityplayer.itemInWorldManager.a((WorldServer) entityplayer.world);
            logger.info(this.getName() + " logged in with entity id " + entityplayer.id + " at (" + entityplayer.locX + ", " + entityplayer.locY + ", " + entityplayer.locZ + ")");
            WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);
            ChunkCoordinates chunkcoordinates = worldserver.getSpawn();

            entityplayer.itemInWorldManager.b(worldserver.getWorldData().getGameType());
            NetServerHandler netserverhandler = new NetServerHandler(this.server, this.networkManager, entityplayer);

            netserverhandler.sendPacket(new Packet1Login("", entityplayer.id, worldserver.getWorldData().getType(), entityplayer.itemInWorldManager.getGameMode(), worldserver.worldProvider.dimension, (byte) worldserver.difficulty, (byte) worldserver.getHeight(), (byte) this.server.serverConfigurationManager.getMaxPlayers()));
            netserverhandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z));
            this.server.serverConfigurationManager.a(entityplayer, worldserver);
            this.server.serverConfigurationManager.sendAll(new Packet3Chat("\u00A7e" + entityplayer.name + " joined the game."));
            this.server.serverConfigurationManager.c(entityplayer);
            netserverhandler.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
            this.server.networkListenThread.a(netserverhandler);
            netserverhandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
            Iterator iterator = entityplayer.getEffects().iterator();

            while (iterator.hasNext()) {
                MobEffect mobeffect = (MobEffect) iterator.next();

                netserverhandler.sendPacket(new Packet41MobEffect(entityplayer.id, mobeffect));
            }

            entityplayer.syncInventory();
        }

        this.c = true;
    }

    public void a(String s, Object[] aobject) {
        logger.info(this.getName() + " lost connection");
        this.c = true;
    }

    public void a(Packet254GetInfo packet254getinfo) {
        try {
            String s = this.server.motd + "\u00A7" + this.server.serverConfigurationManager.getPlayerCount() + "\u00A7" + this.server.serverConfigurationManager.getMaxPlayers();

            this.networkManager.queue(new Packet255KickDisconnect(s));
            this.networkManager.d();
            this.server.networkListenThread.a(this.networkManager.getSocket());
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onUnhandledPacket(Packet packet) {
        this.disconnect("Protocol error");
    }

    public String getName() {
        return this.g != null ? this.g + " [" + this.networkManager.getSocketAddress().toString() + "]" : this.networkManager.getSocketAddress().toString();
    }

    public boolean c() {
        return true;
    }

    static String a(NetLoginHandler netloginhandler) {
        return netloginhandler.loginKey;
    }

    static Packet1Login a(NetLoginHandler netloginhandler, Packet1Login packet1login) {
        return netloginhandler.h = packet1login;
    }
}
