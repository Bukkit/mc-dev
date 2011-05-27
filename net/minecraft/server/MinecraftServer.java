package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinecraftServer implements ICommandListener, Runnable {

    public static Logger a = Logger.getLogger("Minecraft");
    public static HashMap b = new HashMap();
    public NetworkListenThread c;
    public PropertyManager d;
    public WorldServer e;
    public ServerConfigurationManager f;
    private boolean o = true;
    public boolean g = false;
    int h = 0;
    public String i;
    public int j;
    private List p = new ArrayList();
    private List q = Collections.synchronizedList(new ArrayList());
    public EntityTracker k;
    public boolean l;
    public boolean m;
    public boolean n;

    public MinecraftServer() {
        new ThreadSleepForever(this);
    }

    private boolean d() {
        ThreadCommandReader threadcommandreader = new ThreadCommandReader(this);

        threadcommandreader.setDaemon(true);
        threadcommandreader.start();
        ConsoleLogManager.a();
        a.info("Starting minecraft server version 0.2.7");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            a.warning("**** NOT ENOUGH RAM!");
            a.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        a.info("Loading properties");
        this.d = new PropertyManager(new File("server.properties"));
        String s = this.d.a("server-ip", "");

        this.l = this.d.a("online-mode", true);
        this.m = this.d.a("spawn-animals", true);
        this.n = this.d.a("pvp", true);
        InetAddress inetaddress = null;

        if (s.length() > 0) {
            inetaddress = InetAddress.getByName(s);
        }

        int i = this.d.a("server-port", 25565);

        a.info("Starting Minecraft server on " + (s.length() == 0 ? "*" : s) + ":" + i);

        try {
            this.c = new NetworkListenThread(this, inetaddress, i);
        } catch (IOException ioexception) {
            a.warning("**** FAILED TO BIND TO PORT!");
            a.log(Level.WARNING, "The exception was: " + ioexception.toString());
            a.warning("Perhaps a server is already running on that port?");
            return false;
        }

        if (!this.l) {
            a.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            a.warning("The server will make no attempt to authenticate usernames. Beware.");
            a.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            a.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
        }

        this.f = new ServerConfigurationManager(this);
        this.k = new EntityTracker(this);
        String s1 = this.d.a("level-name", "world");

        a.info("Preparing level \"" + s1 + "\"");
        this.c(s1);
        a.info("Done! For help, type \"help\" or \"?\"");
        return true;
    }

    private void c(String s) {
        a.info("Preparing start region");
        this.e = new WorldServer(this, new File("."), s, this.d.a("hellworld", false) ? -1 : 0);
        this.e.a(new WorldManager(this));
        this.e.k = this.d.a("spawn-monsters", true) ? 1 : 0;
        this.f.a(this.e);
        byte b0 = 10;

        for (int i = -b0; i <= b0; ++i) {
            this.a("Preparing spawn area", (i + b0) * 100 / (b0 + b0 + 1));

            for (int j = -b0; j <= b0; ++j) {
                if (!this.o) {
                    return;
                }

                this.e.A.d((this.e.m >> 4) + i, (this.e.o >> 4) + j);
            }
        }

        this.e();
    }

    private void a(String s, int i) {
        this.i = s;
        this.j = i;
        System.out.println(s + ": " + i + "%");
    }

    private void e() {
        this.i = null;
        this.j = 0;
    }

    private void f() {
        a.info("Saving chunks");
        this.e.a(true, (IProgressUpdate) null);
    }

    private void g() {
        a.info("Stopping server");
        if (this.f != null) {
            this.f.d();
        }

        if (this.e != null) {
            this.f();
        }
    }

    public void a() {
        this.o = false;
    }

    public void run() {
        try {
            if (this.d()) {
                long i = System.currentTimeMillis();
                long j = 0L;

                while (this.o) {
                    long k = System.currentTimeMillis();
                    long l = k - i;

                    if (l > 2000L) {
                        a.warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        l = 2000L;
                    }

                    if (l < 0L) {
                        a.warning("Time ran backwards! Did the system time change?");
                        l = 0L;
                    }

                    j += l;
                    i = k;

                    while (j > 50L) {
                        j -= 50L;
                        this.h();
                    }

                    Thread.sleep(1L);
                }
            } else {
                while (this.o) {
                    this.b();

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException interruptedexception) {
                        interruptedexception.printStackTrace();
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            a.log(Level.SEVERE, "Unexpected exception", exception);

            while (this.o) {
                this.b();

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException interruptedexception1) {
                    interruptedexception1.printStackTrace();
                }
            }
        } finally {
            this.g();
            this.g = true;
            System.exit(0);
        }
    }

    private void h() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = b.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            int i = ((Integer) b.get(s)).intValue();

            if (i > 0) {
                b.put(s, Integer.valueOf(i - 1));
            } else {
                arraylist.add(s);
            }
        }

        int j;

        for (j = 0; j < arraylist.size(); ++j) {
            b.remove(arraylist.get(j));
        }

        AxisAlignedBB.a();
        Vec3D.a();
        ++this.h;
        if (this.h % 20 == 0) {
            this.f.a((Packet) (new Packet4UpdateTime(this.e.e)));
        }

        this.e.f();

        while (this.e.d()) {
            ;
        }

        this.e.c();
        this.c.a();
        this.f.b();
        this.k.a();

        for (j = 0; j < this.p.size(); ++j) {
            ((IUpdatePlayerListBox) this.p.get(j)).a();
        }

        try {
            this.b();
        } catch (Exception exception) {
            a.log(Level.WARNING, "Unexpected exception while parsing console command", exception);
        }
    }

    public void a(String s, ICommandListener icommandlistener) {
        this.q.add(new ServerCommand(s, icommandlistener));
    }

    public void b() {
        while (this.q.size() > 0) {
            ServerCommand servercommand = (ServerCommand) this.q.remove(0);
            String s = servercommand.a;
            ICommandListener icommandlistener = servercommand.b;
            String s1 = icommandlistener.c();

            if (!s.toLowerCase().startsWith("help") && !s.toLowerCase().startsWith("?")) {
                if (s.toLowerCase().startsWith("list")) {
                    icommandlistener.b("Connected players: " + this.f.c());
                } else if (s.toLowerCase().startsWith("stop")) {
                    this.a(s1, "Stopping the server..");
                    this.o = false;
                } else if (s.toLowerCase().startsWith("save-all")) {
                    this.a(s1, "Forcing save..");
                    this.e.a(true, (IProgressUpdate) null);
                    this.a(s1, "Save complete.");
                } else if (s.toLowerCase().startsWith("save-off")) {
                    this.a(s1, "Disabling level saving..");
                    this.e.C = true;
                } else if (s.toLowerCase().startsWith("save-on")) {
                    this.a(s1, "Enabling level saving..");
                    this.e.C = false;
                } else {
                    String s2;

                    if (s.toLowerCase().startsWith("op ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        this.f.e(s2);
                        this.a(s1, "Opping " + s2);
                        this.f.a(s2, "\u00A7eYou are now op!");
                    } else if (s.toLowerCase().startsWith("deop ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        this.f.f(s2);
                        this.f.a(s2, "\u00A7eYou are no longer op!");
                        this.a(s1, "De-opping " + s2);
                    } else if (s.toLowerCase().startsWith("ban-ip ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        this.f.c(s2);
                        this.a(s1, "Banning ip " + s2);
                    } else if (s.toLowerCase().startsWith("pardon-ip ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        this.f.d(s2);
                        this.a(s1, "Pardoning ip " + s2);
                    } else {
                        EntityPlayer entityplayer;

                        if (s.toLowerCase().startsWith("ban ")) {
                            s2 = s.substring(s.indexOf(" ")).trim();
                            this.f.a(s2);
                            this.a(s1, "Banning " + s2);
                            entityplayer = this.f.h(s2);
                            if (entityplayer != null) {
                                entityplayer.a.c("Banned by admin");
                            }
                        } else if (s.toLowerCase().startsWith("pardon ")) {
                            s2 = s.substring(s.indexOf(" ")).trim();
                            this.f.b(s2);
                            this.a(s1, "Pardoning " + s2);
                        } else if (s.toLowerCase().startsWith("kick ")) {
                            s2 = s.substring(s.indexOf(" ")).trim();
                            entityplayer = null;

                            for (int i = 0; i < this.f.b.size(); ++i) {
                                EntityPlayer entityplayer1 = (EntityPlayer) this.f.b.get(i);

                                if (entityplayer1.at.equalsIgnoreCase(s2)) {
                                    entityplayer = entityplayer1;
                                }
                            }

                            if (entityplayer != null) {
                                entityplayer.a.c("Kicked by admin");
                                this.a(s1, "Kicking " + entityplayer.at);
                            } else {
                                icommandlistener.b("Can\'t find user " + s2 + ". No kick.");
                            }
                        } else {
                            String[] astring;
                            EntityPlayer entityplayer2;

                            if (s.toLowerCase().startsWith("tp ")) {
                                astring = s.split(" ");
                                if (astring.length == 3) {
                                    entityplayer = this.f.h(astring[1]);
                                    entityplayer2 = this.f.h(astring[2]);
                                    if (entityplayer == null) {
                                        icommandlistener.b("Can\'t find user " + astring[1] + ". No tp.");
                                    } else if (entityplayer2 == null) {
                                        icommandlistener.b("Can\'t find user " + astring[2] + ". No tp.");
                                    } else {
                                        entityplayer.a.a(entityplayer2.p, entityplayer2.q, entityplayer2.r, entityplayer2.v, entityplayer2.w);
                                        this.a(s1, "Teleporting " + astring[1] + " to " + astring[2] + ".");
                                    }
                                } else {
                                    icommandlistener.b("Syntax error, please provice a source and a target.");
                                }
                            } else if (s.toLowerCase().startsWith("give ")) {
                                astring = s.split(" ");
                                if (astring.length != 3 && astring.length != 4) {
                                    return;
                                }

                                String s3 = astring[1];

                                entityplayer2 = this.f.h(s3);
                                if (entityplayer2 != null) {
                                    try {
                                        int j = Integer.parseInt(astring[2]);

                                        if (Item.c[j] != null) {
                                            this.a(s1, "Giving " + entityplayer2.at + " some " + j);
                                            int k = 1;

                                            if (astring.length > 3) {
                                                k = this.b(astring[3], 1);
                                            }

                                            if (k < 1) {
                                                k = 1;
                                            }

                                            if (k > 64) {
                                                k = 64;
                                            }

                                            entityplayer2.a(new ItemStack(j, k));
                                        } else {
                                            icommandlistener.b("There\'s no item with id " + j);
                                        }
                                    } catch (NumberFormatException numberformatexception) {
                                        icommandlistener.b("There\'s no item with id " + astring[2]);
                                    }
                                } else {
                                    icommandlistener.b("Can\'t find user " + s3);
                                }
                            } else if (s.toLowerCase().startsWith("say ")) {
                                s = s.substring(s.indexOf(" ")).trim();
                                a.info("[" + s1 + "] " + s);
                                this.f.a((Packet) (new Packet3Chat("\u00A7d[Server] " + s)));
                            } else if (s.toLowerCase().startsWith("tell ")) {
                                astring = s.split(" ");
                                if (astring.length >= 3) {
                                    s = s.substring(s.indexOf(" ")).trim();
                                    s = s.substring(s.indexOf(" ")).trim();
                                    a.info("[" + s1 + "->" + astring[1] + "] " + s);
                                    s = "\u00A77" + s1 + " whispers " + s;
                                    a.info(s);
                                    if (!this.f.a(astring[1], (Packet) (new Packet3Chat(s)))) {
                                        icommandlistener.b("There\'s no player by that name online.");
                                    }
                                }
                            } else {
                                a.info("Unknown console command. Type \"help\" for help.");
                            }
                        }
                    }
                }
            } else {
                icommandlistener.b("To run the server without a gui, start it like this:");
                icommandlistener.b("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
                icommandlistener.b("Console commands:");
                icommandlistener.b("   help  or  ?               shows this message");
                icommandlistener.b("   kick <player>             removes a player from the server");
                icommandlistener.b("   ban <player>              bans a player from the server");
                icommandlistener.b("   pardon <player>           pardons a banned player so that they can connect again");
                icommandlistener.b("   ban-ip <ip>               bans an IP address from the server");
                icommandlistener.b("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
                icommandlistener.b("   op <player>               turns a player into an op");
                icommandlistener.b("   deop <player>             removes op status from a player");
                icommandlistener.b("   tp <player1> <player2>    moves one player to the same location as another player");
                icommandlistener.b("   give <player> <id> [num]  gives a player a resource");
                icommandlistener.b("   tell <player> <message>   sends a private message to a player");
                icommandlistener.b("   stop                      gracefully stops the server");
                icommandlistener.b("   save-all                  forces a server-wide level save");
                icommandlistener.b("   save-off                  disables terrain saving (useful for backup scripts)");
                icommandlistener.b("   save-on                   re-enables terrain saving");
                icommandlistener.b("   list                      lists all currently connected players");
                icommandlistener.b("   say <message>             broadcasts a message to all players");
            }
        }
    }

    private void a(String s, String s1) {
        String s2 = s + ": " + s1;

        this.f.i("\u00A77(" + s2 + ")");
        a.info(s2);
    }

    private int b(String s, int i) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            return i;
        }
    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
        this.p.add(iupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        try {
            MinecraftServer minecraftserver = new MinecraftServer();

            if (!GraphicsEnvironment.isHeadless() && (astring.length <= 0 || !astring[0].equals("nogui"))) {
                ServerGUI.a(minecraftserver);
            }

            (new ThreadServerApplication("Server thread", minecraftserver)).start();
        } catch (Exception exception) {
            a.log(Level.SEVERE, "Failed to start the minecraft server", exception);
        }
    }

    public File a(String s) {
        return new File(s);
    }

    public void b(String s) {
        a.info(s);
    }

    public String c() {
        return "CONSOLE";
    }

    public static boolean a(MinecraftServer minecraftserver) {
        return minecraftserver.o;
    }
}
