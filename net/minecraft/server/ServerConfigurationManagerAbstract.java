package net.minecraft.server;

import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public abstract class ServerConfigurationManagerAbstract {

    private static final SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    public static final Logger a = Logger.getLogger("Minecraft");
    private final MinecraftServer server;
    public final List players = new ArrayList();
    private final BanList banByName = new BanList(new File("banned-players.txt"));
    private final BanList banByIP = new BanList(new File("banned-ips.txt"));
    private Set operators = new HashSet();
    private Set whitelist = new HashSet();
    private PlayerFileData playerFileData;
    private boolean hasWhitelist;
    protected int maxPlayers;
    protected int d;
    private EnumGamemode m;
    private boolean n;
    private int o = 0;

    public ServerConfigurationManagerAbstract(MinecraftServer minecraftserver) {
        this.server = minecraftserver;
        this.banByName.setEnabled(false);
        this.banByIP.setEnabled(false);
        this.maxPlayers = 8;
    }

    public void a(INetworkManager inetworkmanager, EntityPlayer entityplayer) {
        this.a(entityplayer);
        entityplayer.spawnIn(this.server.getWorldServer(entityplayer.dimension));
        entityplayer.itemInWorldManager.a((WorldServer) entityplayer.world);
        String s = "local";

        if (inetworkmanager.getSocketAddress() != null) {
            s = inetworkmanager.getSocketAddress().toString();
        }

        a.info(entityplayer.name + "[" + s + "] logged in with entity id " + entityplayer.id + " at (" + entityplayer.locX + ", " + entityplayer.locY + ", " + entityplayer.locZ + ")");
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);
        ChunkCoordinates chunkcoordinates = worldserver.getSpawn();

        this.a(entityplayer, (EntityPlayer) null, worldserver);
        NetServerHandler netserverhandler = new NetServerHandler(this.server, inetworkmanager, entityplayer);

        netserverhandler.sendPacket(new Packet1Login(entityplayer.id, worldserver.getWorldData().getType(), entityplayer.itemInWorldManager.getGameMode(), worldserver.getWorldData().isHardcore(), worldserver.worldProvider.dimension, worldserver.difficulty, worldserver.getHeight(), this.getMaxPlayers()));
        netserverhandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z));
        netserverhandler.sendPacket(new Packet202Abilities(entityplayer.abilities));
        this.b(entityplayer, worldserver);
        this.sendAll(new Packet3Chat("\u00A7e" + entityplayer.name + " joined the game."));
        this.c(entityplayer);
        netserverhandler.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
        this.server.ac().a(netserverhandler);
        netserverhandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
        if (this.server.getTexturePack().length() > 0) {
            entityplayer.a(this.server.getTexturePack(), this.server.R());
        }

        Iterator iterator = entityplayer.getEffects().iterator();

        while (iterator.hasNext()) {
            MobEffect mobeffect = (MobEffect) iterator.next();

            netserverhandler.sendPacket(new Packet41MobEffect(entityplayer.id, mobeffect));
        }

        entityplayer.syncInventory();
    }

    public void setPlayerFileData(WorldServer[] aworldserver) {
        this.playerFileData = aworldserver[0].getDataManager().getPlayerFileData();
    }

    public void a(EntityPlayer entityplayer, WorldServer worldserver) {
        WorldServer worldserver1 = entityplayer.q();

        if (worldserver != null) {
            worldserver.getPlayerManager().removePlayer(entityplayer);
        }

        worldserver1.getPlayerManager().addPlayer(entityplayer);
        worldserver1.chunkProviderServer.getChunkAt((int) entityplayer.locX >> 4, (int) entityplayer.locZ >> 4);
    }

    public int a() {
        return PlayerManager.getFurthestViewableBlock(this.o());
    }

    public void a(EntityPlayer entityplayer) {
        NBTTagCompound nbttagcompound = this.server.worldServer[0].getWorldData().h();

        if (entityplayer.getName().equals(this.server.G()) && nbttagcompound != null) {
            entityplayer.e(nbttagcompound);
        } else {
            this.playerFileData.load(entityplayer);
        }
    }

    protected void b(EntityPlayer entityplayer) {
        this.playerFileData.save(entityplayer);
    }

    public void c(EntityPlayer entityplayer) {
        this.sendAll(new Packet201PlayerInfo(entityplayer.name, true, 1000));
        this.players.add(entityplayer);
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);

        while (!worldserver.getCubes(entityplayer, entityplayer.boundingBox).isEmpty()) {
            entityplayer.setPosition(entityplayer.locX, entityplayer.locY + 1.0D, entityplayer.locZ);
        }

        worldserver.addEntity(entityplayer);
        this.a(entityplayer, (WorldServer) null);
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer1 = (EntityPlayer) iterator.next();

            entityplayer.netServerHandler.sendPacket(new Packet201PlayerInfo(entityplayer1.name, true, entityplayer1.ping));
        }
    }

    public void d(EntityPlayer entityplayer) {
        entityplayer.q().getPlayerManager().movePlayer(entityplayer);
    }

    public void disconnect(EntityPlayer entityplayer) {
        this.b(entityplayer);
        WorldServer worldserver = entityplayer.q();

        worldserver.kill(entityplayer);
        worldserver.getPlayerManager().removePlayer(entityplayer);
        this.players.remove(entityplayer);
        this.sendAll(new Packet201PlayerInfo(entityplayer.name, false, 9999));
    }

    public String attemptLogin(SocketAddress socketaddress, String s) {
        if (this.banByName.isBanned(s)) {
            BanEntry banentry = (BanEntry) this.banByName.getEntries().get(s);
            String s1 = "You are banned from this server!\nReason: " + banentry.getReason();

            if (banentry.getExpires() != null) {
                s1 = s1 + "\nYour ban will be removed on " + e.format(banentry.getExpires());
            }

            return s1;
        } else if (!this.isWhitelisted(s)) {
            return "You are not white-listed on this server!";
        } else {
            String s2 = socketaddress.toString();

            s2 = s2.substring(s2.indexOf("/") + 1);
            s2 = s2.substring(0, s2.indexOf(":"));
            if (this.banByIP.isBanned(s2)) {
                BanEntry banentry1 = (BanEntry) this.banByIP.getEntries().get(s2);
                String s3 = "Your IP address is banned from this server!\nReason: " + banentry1.getReason();

                if (banentry1.getExpires() != null) {
                    s3 = s3 + "\nYour ban will be removed on " + e.format(banentry1.getExpires());
                }

                return s3;
            } else {
                return this.players.size() >= this.maxPlayers ? "The server is full!" : null;
            }
        }
    }

    public EntityPlayer processLogin(String s) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.players.iterator();

        EntityPlayer entityplayer;

        while (iterator.hasNext()) {
            entityplayer = (EntityPlayer) iterator.next();
            if (entityplayer.name.equalsIgnoreCase(s)) {
                arraylist.add(entityplayer);
            }
        }

        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            entityplayer = (EntityPlayer) iterator.next();
            entityplayer.netServerHandler.disconnect("You logged in from another location");
        }

        Object object;

        if (this.server.L()) {
            object = new DemoItemInWorldManager(this.server.getWorldServer(0));
        } else {
            object = new ItemInWorldManager(this.server.getWorldServer(0));
        }

        return new EntityPlayer(this.server, this.server.getWorldServer(0), s, (ItemInWorldManager) object);
    }

    public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i, boolean flag) {
        entityplayer.q().getTracker().untrackPlayer(entityplayer);
        entityplayer.q().getTracker().untrackEntity(entityplayer);
        entityplayer.q().getPlayerManager().removePlayer(entityplayer);
        this.players.remove(entityplayer);
        this.server.getWorldServer(entityplayer.dimension).removeEntity(entityplayer);
        ChunkCoordinates chunkcoordinates = entityplayer.getBed();

        entityplayer.dimension = i;
        Object object;

        if (this.server.L()) {
            object = new DemoItemInWorldManager(this.server.getWorldServer(entityplayer.dimension));
        } else {
            object = new ItemInWorldManager(this.server.getWorldServer(entityplayer.dimension));
        }

        EntityPlayer entityplayer1 = new EntityPlayer(this.server, this.server.getWorldServer(entityplayer.dimension), entityplayer.name, (ItemInWorldManager) object);

        entityplayer1.copyTo(entityplayer, flag);
        entityplayer1.id = entityplayer.id;
        entityplayer1.netServerHandler = entityplayer.netServerHandler;
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);

        this.a(entityplayer1, entityplayer, worldserver);
        ChunkCoordinates chunkcoordinates1;

        if (chunkcoordinates != null) {
            chunkcoordinates1 = EntityHuman.getBed(this.server.getWorldServer(entityplayer.dimension), chunkcoordinates);
            if (chunkcoordinates1 != null) {
                entityplayer1.setPositionRotation((double) ((float) chunkcoordinates1.x + 0.5F), (double) ((float) chunkcoordinates1.y + 0.1F), (double) ((float) chunkcoordinates1.z + 0.5F), 0.0F, 0.0F);
                entityplayer1.setRespawnPosition(chunkcoordinates);
            } else {
                entityplayer1.netServerHandler.sendPacket(new Packet70Bed(0, 0));
            }
        }

        worldserver.chunkProviderServer.getChunkAt((int) entityplayer1.locX >> 4, (int) entityplayer1.locZ >> 4);

        while (!worldserver.getCubes(entityplayer1, entityplayer1.boundingBox).isEmpty()) {
            entityplayer1.setPosition(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ);
        }

        entityplayer1.netServerHandler.sendPacket(new Packet9Respawn(entityplayer1.dimension, (byte) entityplayer1.world.difficulty, entityplayer1.world.getWorldData().getType(), entityplayer1.world.getHeight(), entityplayer1.itemInWorldManager.getGameMode()));
        chunkcoordinates1 = worldserver.getSpawn();
        entityplayer1.netServerHandler.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
        entityplayer1.netServerHandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates1.x, chunkcoordinates1.y, chunkcoordinates1.z));
        this.b(entityplayer1, worldserver);
        worldserver.getPlayerManager().addPlayer(entityplayer1);
        worldserver.addEntity(entityplayer1);
        this.players.add(entityplayer1);
        entityplayer1.syncInventory();
        return entityplayer1;
    }

    public void changeDimension(EntityPlayer entityplayer, int i) {
        int j = entityplayer.dimension;
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);

        entityplayer.dimension = i;
        WorldServer worldserver1 = this.server.getWorldServer(entityplayer.dimension);

        entityplayer.netServerHandler.sendPacket(new Packet9Respawn(entityplayer.dimension, (byte) entityplayer.world.difficulty, worldserver1.getWorldData().getType(), worldserver1.getHeight(), entityplayer.itemInWorldManager.getGameMode()));
        worldserver.removeEntity(entityplayer);
        entityplayer.dead = false;
        double d0 = entityplayer.locX;
        double d1 = entityplayer.locZ;
        double d2 = 8.0D;

        if (entityplayer.dimension == -1) {
            d0 /= d2;
            d1 /= d2;
            entityplayer.setPositionRotation(d0, entityplayer.locY, d1, entityplayer.yaw, entityplayer.pitch);
            if (entityplayer.isAlive()) {
                worldserver.entityJoinedWorld(entityplayer, false);
            }
        } else if (entityplayer.dimension == 0) {
            d0 *= d2;
            d1 *= d2;
            entityplayer.setPositionRotation(d0, entityplayer.locY, d1, entityplayer.yaw, entityplayer.pitch);
            if (entityplayer.isAlive()) {
                worldserver.entityJoinedWorld(entityplayer, false);
            }
        } else {
            ChunkCoordinates chunkcoordinates = worldserver1.getDimensionSpawn();

            d0 = (double) chunkcoordinates.x;
            entityplayer.locY = (double) chunkcoordinates.y;
            d1 = (double) chunkcoordinates.z;
            entityplayer.setPositionRotation(d0, entityplayer.locY, d1, 90.0F, 0.0F);
            if (entityplayer.isAlive()) {
                worldserver.entityJoinedWorld(entityplayer, false);
            }
        }

        if (j != 1) {
            d0 = (double) MathHelper.a((int) d0, -29999872, 29999872);
            d1 = (double) MathHelper.a((int) d1, -29999872, 29999872);
            if (entityplayer.isAlive()) {
                worldserver1.addEntity(entityplayer);
                entityplayer.setPositionRotation(d0, entityplayer.locY, d1, entityplayer.yaw, entityplayer.pitch);
                worldserver1.entityJoinedWorld(entityplayer, false);
                (new PortalTravelAgent()).a(worldserver1, entityplayer);
            }
        }

        entityplayer.spawnIn(worldserver1);
        this.a(entityplayer, worldserver);
        entityplayer.netServerHandler.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
        entityplayer.itemInWorldManager.a(worldserver1);
        this.b(entityplayer, worldserver1);
        this.updateClient(entityplayer);
        Iterator iterator = entityplayer.getEffects().iterator();

        while (iterator.hasNext()) {
            MobEffect mobeffect = (MobEffect) iterator.next();

            entityplayer.netServerHandler.sendPacket(new Packet41MobEffect(entityplayer.id, mobeffect));
        }
    }

    public void tick() {
        if (++this.o > 600) {
            this.o = 0;
        }

        if (this.o < this.players.size()) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(this.o);

            this.sendAll(new Packet201PlayerInfo(entityplayer.name, true, entityplayer.ping));
        }
    }

    public void sendAll(Packet packet) {
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            entityplayer.netServerHandler.sendPacket(packet);
        }
    }

    public void a(Packet packet, int i) {
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer.dimension == i) {
                entityplayer.netServerHandler.sendPacket(packet);
            }
        }
    }

    public String c() {
        String s = "";

        for (int i = 0; i < this.players.size(); ++i) {
            if (i > 0) {
                s = s + ", ";
            }

            s = s + ((EntityPlayer) this.players.get(i)).name;
        }

        return s;
    }

    public String[] d() {
        String[] astring = new String[this.players.size()];

        for (int i = 0; i < this.players.size(); ++i) {
            astring[i] = ((EntityPlayer) this.players.get(i)).name;
        }

        return astring;
    }

    public BanList getNameBans() {
        return this.banByName;
    }

    public BanList getIPBans() {
        return this.banByIP;
    }

    public void addOp(String s) {
        this.operators.add(s.toLowerCase());
    }

    public void removeOp(String s) {
        this.operators.remove(s.toLowerCase());
    }

    public boolean isWhitelisted(String s) {
        s = s.trim().toLowerCase();
        return !this.hasWhitelist || this.operators.contains(s) || this.whitelist.contains(s);
    }

    public boolean isOp(String s) {
        return this.operators.contains(s.trim().toLowerCase()) || this.server.H() && this.server.worldServer[0].getWorldData().allowCommands() && this.server.G().equalsIgnoreCase(s) || this.n;
    }

    public EntityPlayer f(String s) {
        Iterator iterator = this.players.iterator();

        EntityPlayer entityplayer;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entityplayer = (EntityPlayer) iterator.next();
        } while (!entityplayer.name.equalsIgnoreCase(s));

        return entityplayer;
    }

    public void sendPacketNearby(double d0, double d1, double d2, double d3, int i, Packet packet) {
        this.sendPacketNearby((EntityHuman) null, d0, d1, d2, d3, i, packet);
    }

    public void sendPacketNearby(EntityHuman entityhuman, double d0, double d1, double d2, double d3, int i, Packet packet) {
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer != entityhuman && entityplayer.dimension == i) {
                double d4 = d0 - entityplayer.locX;
                double d5 = d1 - entityplayer.locY;
                double d6 = d2 - entityplayer.locZ;

                if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                    entityplayer.netServerHandler.sendPacket(packet);
                }
            }
        }
    }

    public void savePlayers() {
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            this.b(entityplayer);
        }
    }

    public void addWhitelist(String s) {
        this.whitelist.add(s);
    }

    public void removeWhitelist(String s) {
        this.whitelist.remove(s);
    }

    public Set getWhitelisted() {
        return this.whitelist;
    }

    public Set getOPs() {
        return this.operators;
    }

    public void reloadWhitelist() {}

    public void b(EntityPlayer entityplayer, WorldServer worldserver) {
        entityplayer.netServerHandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
        if (worldserver.J()) {
            entityplayer.netServerHandler.sendPacket(new Packet70Bed(1, 0));
        }
    }

    public void updateClient(EntityPlayer entityplayer) {
        entityplayer.updateInventory(entityplayer.defaultContainer);
        entityplayer.n();
    }

    public int getPlayerCount() {
        return this.players.size();
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public String[] getSeenPlayers() {
        return this.server.worldServer[0].getDataManager().getPlayerFileData().getSeenPlayers();
    }

    public boolean getHasWhitelist() {
        return this.hasWhitelist;
    }

    public void setHasWhitelist(boolean flag) {
        this.hasWhitelist = flag;
    }

    public List j(String s) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer.r().equals(s)) {
                arraylist.add(entityplayer);
            }
        }

        return arraylist;
    }

    public int o() {
        return this.d;
    }

    public MinecraftServer getServer() {
        return this.server;
    }

    public NBTTagCompound q() {
        return null;
    }

    private void a(EntityPlayer entityplayer, EntityPlayer entityplayer1, World world) {
        if (entityplayer1 != null) {
            entityplayer.itemInWorldManager.setGameMode(entityplayer1.itemInWorldManager.getGameMode());
        } else if (this.m != null) {
            entityplayer.itemInWorldManager.setGameMode(this.m);
        }

        entityplayer.itemInWorldManager.b(world.getWorldData().getGameType());
    }

    public void r() {
        while (!this.players.isEmpty()) {
            ((EntityPlayer) this.players.get(0)).netServerHandler.disconnect("Server closed");
        }
    }
}
