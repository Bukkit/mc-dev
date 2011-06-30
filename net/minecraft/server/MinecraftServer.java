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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinecraftServer implements Runnable, ICommandListener {

    public static Logger log = Logger.getLogger("Minecraft");
    public static HashMap trackerList = new HashMap();
    public NetworkListenThread networkListenThread;
    public PropertyManager propertyManager;
    public WorldServer[] worldServer;
    public ServerConfigurationManager serverConfigurationManager;
    private ConsoleCommandHandler consoleCommandHandler;
    private boolean isRunning = true;
    public boolean isStopped = false;
    int ticks = 0;
    public String i;
    public int j;
    private List r = new ArrayList();
    private List s = Collections.synchronizedList(new ArrayList());
    public EntityTracker[] tracker = new EntityTracker[2];
    public boolean onlineMode;
    public boolean spawnAnimals;
    public boolean pvpMode;
    public boolean allowFlight;

    public MinecraftServer() {
        new ThreadSleepForever(this);
    }

    private boolean init() {
        this.consoleCommandHandler = new ConsoleCommandHandler(this);
        ThreadCommandReader threadcommandreader = new ThreadCommandReader(this);

        threadcommandreader.setDaemon(true);
        threadcommandreader.start();
        ConsoleLogManager.init();
        log.info("Starting minecraft server version Beta 1.7_01");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            log.warning("**** NOT ENOUGH RAM!");
            log.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        log.info("Loading properties");
        this.propertyManager = new PropertyManager(new File("server.properties"));
        String s = this.propertyManager.getString("server-ip", "");

        this.onlineMode = this.propertyManager.getBoolean("online-mode", true);
        this.spawnAnimals = this.propertyManager.getBoolean("spawn-animals", true);
        this.pvpMode = this.propertyManager.getBoolean("pvp", true);
        this.allowFlight = this.propertyManager.getBoolean("allow-flight", false);
        InetAddress inetaddress = null;

        if (s.length() > 0) {
            inetaddress = InetAddress.getByName(s);
        }

        int i = this.propertyManager.getInt("server-port", 25565);

        log.info("Starting Minecraft server on " + (s.length() == 0 ? "*" : s) + ":" + i);

        try {
            this.networkListenThread = new NetworkListenThread(this, inetaddress, i);
        } catch (IOException ioexception) {
            log.warning("**** FAILED TO BIND TO PORT!");
            log.log(Level.WARNING, "The exception was: " + ioexception.toString());
            log.warning("Perhaps a server is already running on that port?");
            return false;
        }

        if (!this.onlineMode) {
            log.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            log.warning("The server will make no attempt to authenticate usernames. Beware.");
            log.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            log.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
        }

        this.serverConfigurationManager = new ServerConfigurationManager(this);
        this.tracker[0] = new EntityTracker(this, 0);
        this.tracker[1] = new EntityTracker(this, -1);
        long j = System.nanoTime();
        String s1 = this.propertyManager.getString("level-name", "world");
        String s2 = this.propertyManager.getString("level-seed", "");
        long k = (new Random()).nextLong();

        if (s2.length() > 0) {
            try {
                k = Long.parseLong(s2);
            } catch (NumberFormatException numberformatexception) {
                k = (long) s2.hashCode();
            }
        }

        log.info("Preparing level \"" + s1 + "\"");
        this.a(new WorldLoaderServer(new File(".")), s1, k);
        log.info("Done (" + (System.nanoTime() - j) + "ns)! For help, type \"help\" or \"?\"");
        return true;
    }

    private void a(Convertable convertable, String s, long i) {
        if (convertable.isConvertable(s)) {
            log.info("Converting map!");
            convertable.convert(s, new ConvertProgressUpdater(this));
        }

        this.worldServer = new WorldServer[2];
        ServerNBTManager servernbtmanager = new ServerNBTManager(new File("."), s, true);

        for (int j = 0; j < this.worldServer.length; ++j) {
            if (j == 0) {
                this.worldServer[j] = new WorldServer(this, servernbtmanager, s, j == 0 ? 0 : -1, i);
            } else {
                this.worldServer[j] = new SecondaryWorldServer(this, servernbtmanager, s, j == 0 ? 0 : -1, i, this.worldServer[0]);
            }

            this.worldServer[j].addIWorldAccess(new WorldManager(this, this.worldServer[j]));
            this.worldServer[j].spawnMonsters = this.propertyManager.getBoolean("spawn-monsters", true) ? 1 : 0;
            this.worldServer[j].setSpawnFlags(this.propertyManager.getBoolean("spawn-monsters", true), this.spawnAnimals);
            this.serverConfigurationManager.setPlayerFileData(this.worldServer);
        }

        short short1 = 196;
        long k = System.currentTimeMillis();

        for (int l = 0; l < this.worldServer.length; ++l) {
            log.info("Preparing start region for level " + l);
            if (l == 0 || this.propertyManager.getBoolean("allow-nether", true)) {
                WorldServer worldserver = this.worldServer[l];
                ChunkCoordinates chunkcoordinates = worldserver.getSpawn();

                for (int i1 = -short1; i1 <= short1 && this.isRunning; i1 += 16) {
                    for (int j1 = -short1; j1 <= short1 && this.isRunning; j1 += 16) {
                        long k1 = System.currentTimeMillis();

                        if (k1 < k) {
                            k = k1;
                        }

                        if (k1 > k + 1000L) {
                            int l1 = (short1 * 2 + 1) * (short1 * 2 + 1);
                            int i2 = (i1 + short1) * (short1 * 2 + 1) + j1 + 1;

                            this.a("Preparing spawn area", i2 * 100 / l1);
                            k = k1;
                        }

                        worldserver.chunkProviderServer.getChunkAt(chunkcoordinates.x + i1 >> 4, chunkcoordinates.z + j1 >> 4);

                        while (worldserver.doLighting() && this.isRunning) {
                            ;
                        }
                    }
                }
            }
        }

        this.e();
    }

    private void a(String s, int i) {
        this.i = s;
        this.j = i;
        log.info(s + ": " + i + "%");
    }

    private void e() {
        this.i = null;
        this.j = 0;
    }

    private void saveChunks() {
        log.info("Saving chunks");

        for (int i = 0; i < this.worldServer.length; ++i) {
            WorldServer worldserver = this.worldServer[i];

            worldserver.save(true, (IProgressUpdate) null);
            worldserver.saveLevel();
        }
    }

    private void stop() {
        log.info("Stopping server");
        if (this.serverConfigurationManager != null) {
            this.serverConfigurationManager.savePlayers();
        }

        for (int i = 0; i < this.worldServer.length; ++i) {
            WorldServer worldserver = this.worldServer[i];

            if (worldserver != null) {
                this.saveChunks();
            }
        }
    }

    public void a() {
        this.isRunning = false;
    }

    public void run() {
        try {
            if (this.init()) {
                long i = System.currentTimeMillis();

                for (long j = 0L; this.isRunning; Thread.sleep(1L)) {
                    long k = System.currentTimeMillis();
                    long l = k - i;

                    if (l > 2000L) {
                        log.warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        l = 2000L;
                    }

                    if (l < 0L) {
                        log.warning("Time ran backwards! Did the system time change?");
                        l = 0L;
                    }

                    j += l;
                    i = k;
                    if (this.worldServer[0].everyoneDeeplySleeping()) {
                        this.h();
                        j = 0L;
                    } else {
                        while (j > 50L) {
                            j -= 50L;
                            this.h();
                        }
                    }
                }
            } else {
                while (this.isRunning) {
                    this.b();

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException interruptedexception) {
                        interruptedexception.printStackTrace();
                    }
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.log(Level.SEVERE, "Unexpected exception", throwable);

            while (this.isRunning) {
                this.b();

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException interruptedexception1) {
                    interruptedexception1.printStackTrace();
                }
            }
        } finally {
            try {
                this.stop();
                this.isStopped = true;
            } catch (Throwable throwable1) {
                throwable1.printStackTrace();
            } finally {
                System.exit(0);
            }
        }
    }

    private void h() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = trackerList.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            int i = ((Integer) trackerList.get(s)).intValue();

            if (i > 0) {
                trackerList.put(s, Integer.valueOf(i - 1));
            } else {
                arraylist.add(s);
            }
        }

        int j;

        for (j = 0; j < arraylist.size(); ++j) {
            trackerList.remove(arraylist.get(j));
        }

        AxisAlignedBB.a();
        Vec3D.a();
        ++this.ticks;

        for (j = 0; j < this.worldServer.length; ++j) {
            if (j == 0 || this.propertyManager.getBoolean("allow-nether", true)) {
                WorldServer worldserver = this.worldServer[j];

                if (this.ticks % 20 == 0) {
                    this.serverConfigurationManager.a(new Packet4UpdateTime(worldserver.getTime()), worldserver.worldProvider.dimension);
                }

                worldserver.doTick();

                while (worldserver.doLighting()) {
                    ;
                }

                worldserver.cleanUp();
            }
        }

        this.networkListenThread.a();
        this.serverConfigurationManager.b();

        for (j = 0; j < this.tracker.length; ++j) {
            this.tracker[j].updatePlayers();
        }

        for (j = 0; j < this.r.size(); ++j) {
            ((IUpdatePlayerListBox) this.r.get(j)).a();
        }

        try {
            this.b();
        } catch (Exception exception) {
            log.log(Level.WARNING, "Unexpected exception while parsing console command", exception);
        }
    }

    public void issueCommand(String s, ICommandListener icommandlistener) {
        this.s.add(new ServerCommand(s, icommandlistener));
    }

    public void b() {
        while (this.s.size() > 0) {
            ServerCommand servercommand = (ServerCommand) this.s.remove(0);

            this.consoleCommandHandler.handle(servercommand);
        }
    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
        this.r.add(iupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        StatisticList.a();

        try {
            MinecraftServer minecraftserver = new MinecraftServer();

            if (!GraphicsEnvironment.isHeadless() && (astring.length <= 0 || !astring[0].equals("nogui"))) {
                ServerGUI.a(minecraftserver);
            }

            (new ThreadServerApplication("Server thread", minecraftserver)).start();
        } catch (Exception exception) {
            log.log(Level.SEVERE, "Failed to start the minecraft server", exception);
        }
    }

    public File a(String s) {
        return new File(s);
    }

    public void sendMessage(String s) {
        log.info(s);
    }

    public void c(String s) {
        log.warning(s);
    }

    public String getName() {
        return "CONSOLE";
    }

    public WorldServer getWorldServer(int i) {
        return i == -1 ? this.worldServer[1] : this.worldServer[0];
    }

    public EntityTracker getTracker(int i) {
        return i == -1 ? this.tracker[1] : this.tracker[0];
    }

    public static boolean isRunning(MinecraftServer minecraftserver) {
        return minecraftserver.isRunning;
    }
}
