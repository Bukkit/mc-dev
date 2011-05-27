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
    public List b = new ArrayList();
    private MinecraftServer c;
    private PlayerManager d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private Set i = new HashSet();
    private File j;
    private File k;
    private File l;
    private File m;
    private PlayerFileData n;
    private boolean o;

    public ServerConfigurationManager(MinecraftServer minecraftserver) {
        this.c = minecraftserver;
        this.j = minecraftserver.a("banned-players.txt");
        this.k = minecraftserver.a("banned-ips.txt");
        this.l = minecraftserver.a("ops.txt");
        this.m = minecraftserver.a("white-list.txt");
        this.d = new PlayerManager(minecraftserver);
        this.e = minecraftserver.d.a("max-players", 20);
        this.o = minecraftserver.d.a("white-list", false);
        this.g();
        this.i();
        this.k();
        this.m();
        this.h();
        this.j();
        this.l();
        this.n();
    }

    public void a(WorldServer worldserver) {
        this.n = worldserver.m().d();
    }

    public int a() {
        return this.d.b();
    }

    public void a(EntityPlayer entityplayer) {
        this.b.add(entityplayer);
        this.n.b(entityplayer);
        this.c.e.u.d((int) entityplayer.locX >> 4, (int) entityplayer.locZ >> 4);

        while (this.c.e.a(entityplayer, entityplayer.boundingBox).size() != 0) {
            entityplayer.a(entityplayer.locX, entityplayer.locY + 1.0D, entityplayer.locZ);
        }

        this.c.e.a(entityplayer);
        this.d.a(entityplayer);
    }

    public void b(EntityPlayer entityplayer) {
        this.d.c(entityplayer);
    }

    public void c(EntityPlayer entityplayer) {
        this.n.a(entityplayer);
        this.c.e.d(entityplayer);
        this.b.remove(entityplayer);
        this.d.b(entityplayer);
    }

    public EntityPlayer a(NetLoginHandler netloginhandler, String s, String s1) {
        if (this.f.contains(s.trim().toLowerCase())) {
            netloginhandler.a("You are banned from this server!");
            return null;
        } else if (!this.g(s)) {
            netloginhandler.a("You are not white-listed on this server!");
            return null;
        } else {
            String s2 = netloginhandler.b.b().toString();

            s2 = s2.substring(s2.indexOf("/") + 1);
            s2 = s2.substring(0, s2.indexOf(":"));
            if (this.g.contains(s2)) {
                netloginhandler.a("Your IP address is banned from this server!");
                return null;
            } else if (this.b.size() >= this.e) {
                netloginhandler.a("The server is full!");
                return null;
            } else {
                for (int i = 0; i < this.b.size(); ++i) {
                    EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

                    if (entityplayer.name.equalsIgnoreCase(s)) {
                        entityplayer.a.a("You logged in from another location");
                    }
                }

                return new EntityPlayer(this.c, this.c.e, s, new ItemInWorldManager(this.c.e));
            }
        }
    }

    public EntityPlayer d(EntityPlayer entityplayer) {
        this.c.k.a(entityplayer);
        this.c.k.b(entityplayer);
        this.d.b(entityplayer);
        this.b.remove(entityplayer);
        this.c.e.e(entityplayer);
        EntityPlayer entityplayer1 = new EntityPlayer(this.c, this.c.e, entityplayer.name, new ItemInWorldManager(this.c.e));

        entityplayer1.id = entityplayer.id;
        entityplayer1.a = entityplayer.a;
        this.c.e.u.d((int) entityplayer1.locX >> 4, (int) entityplayer1.locZ >> 4);

        while (this.c.e.a(entityplayer1, entityplayer1.boundingBox).size() != 0) {
            entityplayer1.a(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ);
        }

        entityplayer1.a.b((Packet) (new Packet9Respawn()));
        entityplayer1.a.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
        this.d.a(entityplayer1);
        this.c.e.a(entityplayer1);
        this.b.add(entityplayer1);
        entityplayer1.l();
        entityplayer1.s();
        return entityplayer1;
    }

    public void b() {
        this.d.a();
    }

    public void a(int i, int j, int k) {
        this.d.a(i, j, k);
    }

    public void a(Packet packet) {
        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

            entityplayer.a.b(packet);
        }
    }

    public String c() {
        String s = "";

        for (int i = 0; i < this.b.size(); ++i) {
            if (i > 0) {
                s = s + ", ";
            }

            s = s + ((EntityPlayer) this.b.get(i)).name;
        }

        return s;
    }

    public void a(String s) {
        this.f.add(s.toLowerCase());
        this.h();
    }

    public void b(String s) {
        this.f.remove(s.toLowerCase());
        this.h();
    }

    private void g() {
        try {
            this.f.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.j));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.f.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ban list: " + exception);
        }
    }

    private void h() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.j, false));
            Iterator iterator = this.f.iterator();

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
        this.g.add(s.toLowerCase());
        this.j();
    }

    public void d(String s) {
        this.g.remove(s.toLowerCase());
        this.j();
    }

    private void i() {
        try {
            this.g.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.k));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.g.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ip ban list: " + exception);
        }
    }

    private void j() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.k, false));
            Iterator iterator = this.g.iterator();

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

    public boolean g(String s) {
        s = s.trim().toLowerCase();
        return !this.o || this.h.contains(s) || this.i.contains(s);
    }

    public boolean h(String s) {
        return this.h.contains(s.trim().toLowerCase());
    }

    public EntityPlayer i(String s) {
        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

            if (entityplayer.name.equalsIgnoreCase(s)) {
                return entityplayer;
            }
        }

        return null;
    }

    public void a(String s, String s1) {
        EntityPlayer entityplayer = this.i(s);

        if (entityplayer != null) {
            entityplayer.a.b((Packet) (new Packet3Chat(s1)));
        }
    }

    public void a(double d0, double d1, double d2, double d3, Packet packet) {
        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);
            double d4 = d0 - entityplayer.locX;
            double d5 = d1 - entityplayer.locY;
            double d6 = d2 - entityplayer.locZ;

            if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                entityplayer.a.b(packet);
            }
        }
    }

    public void j(String s) {
        Packet3Chat packet3chat = new Packet3Chat(s);

        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

            if (this.h(entityplayer.name)) {
                entityplayer.a.b((Packet) packet3chat);
            }
        }
    }

    public boolean a(String s, Packet packet) {
        EntityPlayer entityplayer = this.i(s);

        if (entityplayer != null) {
            entityplayer.a.b(packet);
            return true;
        } else {
            return false;
        }
    }

    public void d() {
        for (int i = 0; i < this.b.size(); ++i) {
            this.n.a((EntityHuman) this.b.get(i));
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
