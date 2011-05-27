package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class ServerConfigurationManager {

    public static Logger a = Logger.getLogger("Minecraft");
    public List players = new ArrayList();
    private MinecraftServer server;
    private PlayerManager d;
    private int maxPlayers;
    private Set banByName = new HashSet();
    private Set banByIP = new HashSet();
    private Set h = new HashSet();
    private Set i = new HashSet();
    private File j;
    private File k;
    private File l;
    private File m;
    private PlayerFileData playerFileData;
    private boolean o;

    public ServerConfigurationManager(MinecraftServer minecraftserver) {
        this.server = minecraftserver;
        this.j = minecraftserver.a("banned-players.txt");
        this.k = minecraftserver.a("banned-ips.txt");
        this.l = minecraftserver.a("ops.txt");
        this.m = minecraftserver.a("white-list.txt");
        this.d = new PlayerManager(minecraftserver);
        this.maxPlayers = minecraftserver.propertyManager.getInt("max-players", 20);
        this.o = minecraftserver.propertyManager.getBoolean("white-list", false);
        this.g();
        this.i();
        this.k();
        this.m();
        this.h();
        this.j();
        this.l();
        this.n();
    }

    public void setPlayerFileData(WorldServer worldserver) {
        this.playerFileData = worldserver.p().d();
    }

    public int a() {
        return this.d.b();
    }

    public void a(EntityPlayer entityplayer) {
        this.players.add(entityplayer);
        this.playerFileData.b(entityplayer);
        this.server.worldServer.chunkProviderServer.getChunkAt((int) entityplayer.locX >> 4, (int) entityplayer.locZ >> 4);

        while (this.server.worldServer.getEntities(entityplayer, entityplayer.boundingBox).size() != 0) {
            entityplayer.setPosition(entityplayer.locX, entityplayer.locY + 1.0D, entityplayer.locZ);
        }

        this.server.worldServer.addEntity(entityplayer);
        this.d.addPlayer(entityplayer);
    }

    public void b(EntityPlayer entityplayer) {
        this.d.movePlayer(entityplayer);
    }

    public void disconnect(EntityPlayer entityplayer) {
        this.playerFileData.a(entityplayer);
        this.server.worldServer.kill(entityplayer);
        this.players.remove(entityplayer);
        this.d.removePlayer(entityplayer);
    }

    public EntityPlayer a(NetLoginHandler netloginhandler, String s) {
        if (this.banByName.contains(s.trim().toLowerCase())) {
            netloginhandler.disconnect("You are banned from this server!");
            return null;
        } else if (!this.isWhitelisted(s)) {
            netloginhandler.disconnect("You are not white-listed on this server!");
            return null;
        } else {
            String s1 = netloginhandler.networkManager.getSocketAddress().toString();

            s1 = s1.substring(s1.indexOf("/") + 1);
            s1 = s1.substring(0, s1.indexOf(":"));
            if (this.banByIP.contains(s1)) {
                netloginhandler.disconnect("Your IP address is banned from this server!");
                return null;
            } else if (this.players.size() >= this.maxPlayers) {
                netloginhandler.disconnect("The server is full!");
                return null;
            } else {
                for (int i = 0; i < this.players.size(); ++i) {
                    EntityPlayer entityplayer = (EntityPlayer) this.players.get(i);

                    if (entityplayer.name.equalsIgnoreCase(s)) {
                        entityplayer.netServerHandler.disconnect("You logged in from another location");
                    }
                }

                return new EntityPlayer(this.server, this.server.worldServer, s, new ItemInWorldManager(this.server.worldServer));
            }
        }
    }

    public EntityPlayer d(EntityPlayer entityplayer) {
        this.server.tracker.trackPlayer(entityplayer);
        this.server.tracker.untrackEntity(entityplayer);
        this.d.removePlayer(entityplayer);
        this.players.remove(entityplayer);
        this.server.worldServer.removeEntity(entityplayer);
        ChunkCoordinates chunkcoordinates = entityplayer.K();
        EntityPlayer entityplayer1 = new EntityPlayer(this.server, this.server.worldServer, entityplayer.name, new ItemInWorldManager(this.server.worldServer));

        entityplayer1.id = entityplayer.id;
        entityplayer1.netServerHandler = entityplayer.netServerHandler;
        if (chunkcoordinates != null) {
            ChunkCoordinates chunkcoordinates1 = EntityHuman.getBed(this.server.worldServer, chunkcoordinates);

            if (chunkcoordinates1 != null) {
                entityplayer1.setPositionRotation((double) ((float) chunkcoordinates1.x + 0.5F), (double) ((float) chunkcoordinates1.y + 0.1F), (double) ((float) chunkcoordinates1.z + 0.5F), 0.0F, 0.0F);
                entityplayer1.a(chunkcoordinates);
            } else {
                entityplayer1.netServerHandler.sendPacket(new Packet70Bed(0));
            }
        }

        this.server.worldServer.chunkProviderServer.getChunkAt((int) entityplayer1.locX >> 4, (int) entityplayer1.locZ >> 4);

        while (this.server.worldServer.getEntities(entityplayer1, entityplayer1.boundingBox).size() != 0) {
            entityplayer1.setPosition(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ);
        }

        entityplayer1.netServerHandler.sendPacket(new Packet9Respawn());
        entityplayer1.netServerHandler.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
        this.d.addPlayer(entityplayer1);
        this.server.worldServer.addEntity(entityplayer1);
        this.players.add(entityplayer1);
        entityplayer1.syncInventory();
        entityplayer1.w();
        return entityplayer1;
    }

    public void b() {
        this.d.flush();
    }

    public void flagDirty(int i, int j, int k) {
        this.d.flagDirty(i, j, k);
    }

    public void sendAll(Packet packet) {
        for (int i = 0; i < this.players.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(i);

            entityplayer.netServerHandler.sendPacket(packet);
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

    public void a(String s) {
        this.banByName.add(s.toLowerCase());
        this.h();
    }

    public void b(String s) {
        this.banByName.remove(s.toLowerCase());
        this.h();
    }

    private void g() {
        try {
            this.banByName.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.j));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.banByName.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ban list: " + exception);
        }
    }

    private void h() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.j, false));
            Iterator iterator = this.banByName.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ban list: " + exception);
        }
    }

    public void c(String s) {
        this.banByIP.add(s.toLowerCase());
        this.j();
    }

    public void d(String s) {
        this.banByIP.remove(s.toLowerCase());
        this.j();
    }

    private void i() {
        try {
            this.banByIP.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.k));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.banByIP.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ip ban list: " + exception);
        }
    }

    private void j() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.k, false));
            Iterator iterator = this.banByIP.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ip ban list: " + exception);
        }
    }

    public void e(String s) {
        this.h.add(s.toLowerCase());
        this.l();
    }

    public void f(String s) {
        this.h.remove(s.toLowerCase());
        this.l();
    }

    private void k() {
        try {
            this.h.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.l));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.h.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ip ban list: " + exception);
        }
    }

    private void l() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.l, false));
            Iterator iterator = this.h.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ip ban list: " + exception);
        }
    }

    private void m() {
        try {
            this.i.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.m));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.i.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load white-list: " + exception);
        }
    }

    private void n() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.m, false));
            Iterator iterator = this.i.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save white-list: " + exception);
        }
    }

    public boolean isWhitelisted(String s) {
        s = s.trim().toLowerCase();
        return !this.o || this.h.contains(s) || this.i.contains(s);
    }

    public boolean isOp(String s) {
        return this.h.contains(s.trim().toLowerCase());
    }

    public EntityPlayer i(String s) {
        for (int i = 0; i < this.players.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(i);

            if (entityplayer.name.equalsIgnoreCase(s)) {
                return entityplayer;
            }
        }

        return null;
    }

    public void a(String s, String s1) {
        EntityPlayer entityplayer = this.i(s);

        if (entityplayer != null) {
            entityplayer.netServerHandler.sendPacket(new Packet3Chat(s1));
        }
    }

    public void a(double d0, double d1, double d2, double d3, Packet packet) {
        for (int i = 0; i < this.players.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(i);
            double d4 = d0 - entityplayer.locX;
            double d5 = d1 - entityplayer.locY;
            double d6 = d2 - entityplayer.locZ;

            if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                entityplayer.netServerHandler.sendPacket(packet);
            }
        }
    }

    public void j(String s) {
        Packet3Chat packet3chat = new Packet3Chat(s);

        for (int i = 0; i < this.players.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(i);

            if (this.isOp(entityplayer.name)) {
                entityplayer.netServerHandler.sendPacket(packet3chat);
            }
        }
    }

    public boolean a(String s, Packet packet) {
        EntityPlayer entityplayer = this.i(s);

        if (entityplayer != null) {
            entityplayer.netServerHandler.sendPacket(packet);
            return true;
        } else {
            return false;
        }
    }

    public void savePlayers() {
        for (int i = 0; i < this.players.size(); ++i) {
            this.playerFileData.a((EntityHuman) this.players.get(i));
        }
    }

    public void a(int i, int j, int k, TileEntity tileentity) {}

    public void k(String s) {
        this.i.add(s);
        this.n();
    }

    public void l(String s) {
        this.i.remove(s);
        this.n();
    }

    public Set e() {
        return this.i;
    }

    public void f() {
        this.m();
    }
}
