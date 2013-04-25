package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MinecraftServer implements ICommandListener, Runnable, IMojangStatistics {

    private static MinecraftServer k = null;
    private final Convertable convertable;
    private final MojangStatisticsGenerator m = new MojangStatisticsGenerator("server", this);
    private final File universe;
    private final List o = new ArrayList();
    private final ICommandHandler p;
    public final MethodProfiler methodProfiler = new MethodProfiler();
    private String serverIp;
    private int r = -1;
    public WorldServer[] worldServer;
    private PlayerList s;
    private boolean isRunning = true;
    private boolean isStopped = false;
    private int ticks = 0;
    public String c;
    public int d;
    private boolean onlineMode;
    private boolean spawnAnimals;
    private boolean spawnNPCs;
    private boolean pvpMode;
    private boolean allowFlight;
    private String motd;
    private int C;
    private long D;
    private long E;
    private long F;
    private long G;
    public final long[] e = new long[100];
    public final long[] f = new long[100];
    public final long[] g = new long[100];
    public final long[] h = new long[100];
    public final long[] i = new long[100];
    public long[][] j;
    private KeyPair H;
    private String I;
    private String J;
    private boolean demoMode;
    private boolean M;
    private boolean N;
    private String O = "";
    private boolean P = false;
    private long Q;
    private String R;
    private boolean S;
    private boolean T = false;

    public MinecraftServer(File file1) {
        k = this;
        this.universe = file1;
        this.p = new CommandDispatcher();
        this.convertable = new WorldLoaderServer(file1);
        this.an();
    }

    private void an() {
        DispenserRegistry.a();
    }

    protected abstract boolean init();

    protected void b(String s) {
        if (this.getConvertable().isConvertable(s)) {
            this.getLogger().info("Converting map!");
            this.c("menu.convertingLevel");
            this.getConvertable().convert(s, new ConvertProgressUpdater(this));
        }
    }

    protected synchronized void c(String s) {
        this.R = s;
    }

    protected void a(String s, String s1, long i, WorldType worldtype, String s2) {
        this.b(s);
        this.c("menu.loadingLevel");
        this.worldServer = new WorldServer[3];
        this.j = new long[this.worldServer.length][100];
        IDataManager idatamanager = this.convertable.a(s, true);
        WorldData worlddata = idatamanager.getWorldData();
        WorldSettings worldsettings;

        if (worlddata == null) {
            worldsettings = new WorldSettings(i, this.getGamemode(), this.getGenerateStructures(), this.isHardcore(), worldtype);
            worldsettings.a(s2);
        } else {
            worldsettings = new WorldSettings(worlddata);
        }

        if (this.M) {
            worldsettings.a();
        }

        for (int j = 0; j < this.worldServer.length; ++j) {
            byte b0 = 0;

            if (j == 1) {
                b0 = -1;
            }

            if (j == 2) {
                b0 = 1;
            }

            if (j == 0) {
                if (this.M()) {
                    this.worldServer[j] = new DemoWorldServer(this, idatamanager, s1, b0, this.methodProfiler, this.getLogger());
                } else {
                    this.worldServer[j] = new WorldServer(this, idatamanager, s1, b0, worldsettings, this.methodProfiler, this.getLogger());
                }
            } else {
                this.worldServer[j] = new SecondaryWorldServer(this, idatamanager, s1, b0, worldsettings, this.worldServer[0], this.methodProfiler, this.getLogger());
            }

            this.worldServer[j].addIWorldAccess(new WorldManager(this, this.worldServer[j]));
            if (!this.I()) {
                this.worldServer[j].getWorldData().setGameType(this.getGamemode());
            }

            this.s.setPlayerFileData(this.worldServer);
        }

        this.c(this.getDifficulty());
        this.e();
    }

    protected void e() {
        int i = 0;

        this.c("menu.generatingTerrain");
        byte b0 = 0;

        this.getLogger().info("Preparing start region for level " + b0);
        WorldServer worldserver = this.worldServer[b0];
        ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
        long j = System.currentTimeMillis();

        for (int k = -192; k <= 192 && this.isRunning(); k += 16) {
            for (int l = -192; l <= 192 && this.isRunning(); l += 16) {
                long i1 = System.currentTimeMillis();

                if (i1 - j > 1000L) {
                    this.a_("Preparing spawn area", i * 100 / 625);
                    j = i1;
                }

                ++i;
                worldserver.chunkProviderServer.getChunkAt(chunkcoordinates.x + k >> 4, chunkcoordinates.z + l >> 4);
            }
        }

        this.j();
    }

    public abstract boolean getGenerateStructures();

    public abstract EnumGamemode getGamemode();

    public abstract int getDifficulty();

    public abstract boolean isHardcore();

    protected void a_(String s, int i) {
        this.c = s;
        this.d = i;
        this.getLogger().info(s + ": " + i + "%");
    }

    protected void j() {
        this.c = null;
        this.d = 0;
    }

    protected void saveChunks(boolean flag) {
        if (!this.N) {
            WorldServer[] aworldserver = this.worldServer;
            int i = aworldserver.length;

            for (int j = 0; j < i; ++j) {
                WorldServer worldserver = aworldserver[j];

                if (worldserver != null) {
                    if (!flag) {
                        this.getLogger().info("Saving chunks for level \'" + worldserver.getWorldData().getName() + "\'/" + worldserver.worldProvider.getName());
                    }

                    try {
                        worldserver.save(true, (IProgressUpdate) null);
                    } catch (ExceptionWorldConflict exceptionworldconflict) {
                        this.getLogger().warning(exceptionworldconflict.getMessage());
                    }
                }
            }
        }
    }

    public void stop() {
        if (!this.N) {
            this.getLogger().info("Stopping server");
            if (this.ae() != null) {
                this.ae().a();
            }

            if (this.s != null) {
                this.getLogger().info("Saving players");
                this.s.savePlayers();
                this.s.r();
            }

            this.getLogger().info("Saving worlds");
            this.saveChunks(false);

            for (int i = 0; i < this.worldServer.length; ++i) {
                WorldServer worldserver = this.worldServer[i];

                worldserver.saveLevel();
            }

            if (this.m != null && this.m.d()) {
                this.m.e();
            }
        }
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void d(String s) {
        this.serverIp = s;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void safeShutdown() {
        this.isRunning = false;
    }

    public void run() {
        try {
            if (this.init()) {
                long i = System.currentTimeMillis();

                for (long j = 0L; this.isRunning; this.P = true) {
                    long k = System.currentTimeMillis();
                    long l = k - i;

                    if (l > 2000L && i - this.Q >= 15000L) {
                        this.getLogger().warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        l = 2000L;
                        this.Q = i;
                    }

                    if (l < 0L) {
                        this.getLogger().warning("Time ran backwards! Did the system time change?");
                        l = 0L;
                    }

                    j += l;
                    i = k;
                    if (this.worldServer[0].everyoneDeeplySleeping()) {
                        this.q();
                        j = 0L;
                    } else {
                        while (j > 50L) {
                            j -= 50L;
                            this.q();
                        }
                    }

                    Thread.sleep(1L);
                }
            } else {
                this.a((CrashReport) null);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.getLogger().severe("Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
            CrashReport crashreport = null;

            if (throwable instanceof ReportedException) {
                crashreport = this.b(((ReportedException) throwable).a());
            } else {
                crashreport = this.b(new CrashReport("Exception in server tick loop", throwable));
            }

            File file1 = new File(new File(this.o(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (crashreport.a(file1, this.getLogger())) {
                this.getLogger().severe("This crash report has been saved to: " + file1.getAbsolutePath());
            } else {
                this.getLogger().severe("We were unable to save this crash report to disk.");
            }

            this.a(crashreport);
        } finally {
            try {
                this.stop();
                this.isStopped = true;
            } catch (Throwable throwable1) {
                throwable1.printStackTrace();
            } finally {
                this.p();
            }
        }
    }

    protected File o() {
        return new File(".");
    }

    protected void a(CrashReport crashreport) {}

    protected void p() {}

    protected void q() {
        long i = System.nanoTime();

        AxisAlignedBB.a().a();
        ++this.ticks;
        if (this.S) {
            this.S = false;
            this.methodProfiler.a = true;
            this.methodProfiler.a();
        }

        this.methodProfiler.a("root");
        this.r();
        if (this.ticks % 900 == 0) {
            this.methodProfiler.a("save");
            this.s.savePlayers();
            this.saveChunks(true);
            this.methodProfiler.b();
        }

        this.methodProfiler.a("tallying");
        this.i[this.ticks % 100] = System.nanoTime() - i;
        this.e[this.ticks % 100] = Packet.q - this.D;
        this.D = Packet.q;
        this.f[this.ticks % 100] = Packet.r - this.E;
        this.E = Packet.r;
        this.g[this.ticks % 100] = Packet.o - this.F;
        this.F = Packet.o;
        this.h[this.ticks % 100] = Packet.p - this.G;
        this.G = Packet.p;
        this.methodProfiler.b();
        this.methodProfiler.a("snooper");
        if (!this.m.d() && this.ticks > 100) {
            this.m.a();
        }

        if (this.ticks % 6000 == 0) {
            this.m.b();
        }

        this.methodProfiler.b();
        this.methodProfiler.b();
    }

    public void r() {
        this.methodProfiler.a("levels");

        int i;

        for (i = 0; i < this.worldServer.length; ++i) {
            long j = System.nanoTime();

            if (i == 0 || this.getAllowNether()) {
                WorldServer worldserver = this.worldServer[i];

                this.methodProfiler.a(worldserver.getWorldData().getName());
                this.methodProfiler.a("pools");
                worldserver.getVec3DPool().a();
                this.methodProfiler.b();
                if (this.ticks % 20 == 0) {
                    this.methodProfiler.a("timeSync");
                    this.s.a(new Packet4UpdateTime(worldserver.getTime(), worldserver.getDayTime()), worldserver.worldProvider.dimension);
                    this.methodProfiler.b();
                }

                this.methodProfiler.a("tick");

                CrashReport crashreport;

                try {
                    worldserver.doTick();
                } catch (Throwable throwable) {
                    crashreport = CrashReport.a(throwable, "Exception ticking world");
                    worldserver.a(crashreport);
                    throw new ReportedException(crashreport);
                }

                try {
                    worldserver.tickEntities();
                } catch (Throwable throwable1) {
                    crashreport = CrashReport.a(throwable1, "Exception ticking world entities");
                    worldserver.a(crashreport);
                    throw new ReportedException(crashreport);
                }

                this.methodProfiler.b();
                this.methodProfiler.a("tracker");
                worldserver.getTracker().updatePlayers();
                this.methodProfiler.b();
                this.methodProfiler.b();
            }

            this.j[i][this.ticks % 100] = System.nanoTime() - j;
        }

        this.methodProfiler.c("connection");
        this.ae().b();
        this.methodProfiler.c("players");
        this.s.tick();
        this.methodProfiler.c("tickables");

        for (i = 0; i < this.o.size(); ++i) {
            ((IUpdatePlayerListBox) this.o.get(i)).a();
        }

        this.methodProfiler.b();
    }

    public boolean getAllowNether() {
        return true;
    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
        this.o.add(iupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        StatisticList.a();
        IConsoleLogManager iconsolelogmanager = null;

        try {
            boolean flag = !GraphicsEnvironment.isHeadless();
            String s = null;
            String s1 = ".";
            String s2 = null;
            boolean flag1 = false;
            boolean flag2 = false;
            int i = -1;

            for (int j = 0; j < astring.length; ++j) {
                String s3 = astring[j];
                String s4 = j == astring.length - 1 ? null : astring[j + 1];
                boolean flag3 = false;

                if (!s3.equals("nogui") && !s3.equals("--nogui")) {
                    if (s3.equals("--port") && s4 != null) {
                        flag3 = true;

                        try {
                            i = Integer.parseInt(s4);
                        } catch (NumberFormatException numberformatexception) {
                            ;
                        }
                    } else if (s3.equals("--singleplayer") && s4 != null) {
                        flag3 = true;
                        s = s4;
                    } else if (s3.equals("--universe") && s4 != null) {
                        flag3 = true;
                        s1 = s4;
                    } else if (s3.equals("--world") && s4 != null) {
                        flag3 = true;
                        s2 = s4;
                    } else if (s3.equals("--demo")) {
                        flag1 = true;
                    } else if (s3.equals("--bonusChest")) {
                        flag2 = true;
                    }
                } else {
                    flag = false;
                }

                if (flag3) {
                    ++j;
                }
            }

            DedicatedServer dedicatedserver = new DedicatedServer(new File(s1));

            iconsolelogmanager = dedicatedserver.getLogger();
            if (s != null) {
                dedicatedserver.k(s);
            }

            if (s2 != null) {
                dedicatedserver.l(s2);
            }

            if (i >= 0) {
                dedicatedserver.setPort(i);
            }

            if (flag1) {
                dedicatedserver.b(true);
            }

            if (flag2) {
                dedicatedserver.c(true);
            }

            if (flag) {
                dedicatedserver.ap();
            }

            dedicatedserver.t();
            Runtime.getRuntime().addShutdownHook(new ThreadShutdown(dedicatedserver));
        } catch (Exception exception) {
            if (iconsolelogmanager != null) {
                iconsolelogmanager.severe("Failed to start the minecraft server", exception);
            } else {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", exception);
            }
        }
    }

    public void t() {
        (new ThreadServerApplication(this, "Server thread")).start();
    }

    public File e(String s) {
        return new File(this.o(), s);
    }

    public void info(String s) {
        this.getLogger().info(s);
    }

    public void warning(String s) {
        this.getLogger().warning(s);
    }

    public WorldServer getWorldServer(int i) {
        return i == -1 ? this.worldServer[1] : (i == 1 ? this.worldServer[2] : this.worldServer[0]);
    }

    public String u() {
        return this.serverIp;
    }

    public int v() {
        return this.r;
    }

    public String w() {
        return this.motd;
    }

    public String getVersion() {
        return "1.5.2";
    }

    public int y() {
        return this.s.getPlayerCount();
    }

    public int z() {
        return this.s.getMaxPlayers();
    }

    public String[] getPlayers() {
        return this.s.d();
    }

    public String getPlugins() {
        return "";
    }

    public String h(String s) {
        RemoteControlCommandListener.instance.c();
        this.p.a(RemoteControlCommandListener.instance, s);
        return RemoteControlCommandListener.instance.d();
    }

    public boolean isDebugging() {
        return false;
    }

    public void i(String s) {
        this.getLogger().severe(s);
    }

    public void j(String s) {
        if (this.isDebugging()) {
            this.getLogger().info(s);
        }
    }

    public String getServerModName() {
        return "vanilla";
    }

    public CrashReport b(CrashReport crashreport) {
        crashreport.g().a("Profiler Position", (Callable) (new CrashReportProfilerPosition(this)));
        if (this.worldServer != null && this.worldServer.length > 0 && this.worldServer[0] != null) {
            crashreport.g().a("Vec3 Pool Size", (Callable) (new CrashReportVec3DPoolSize(this)));
        }

        if (this.s != null) {
            crashreport.g().a("Player Count", (Callable) (new CrashReportPlayerCount(this)));
        }

        return crashreport;
    }

    public List a(ICommandListener icommandlistener, String s) {
        ArrayList arraylist = new ArrayList();

        if (s.startsWith("/")) {
            s = s.substring(1);
            boolean flag = !s.contains(" ");
            List list = this.p.b(icommandlistener, s);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    String s1 = (String) iterator.next();

                    if (flag) {
                        arraylist.add("/" + s1);
                    } else {
                        arraylist.add(s1);
                    }
                }
            }

            return arraylist;
        } else {
            String[] astring = s.split(" ", -1);
            String s2 = astring[astring.length - 1];
            String[] astring1 = this.s.d();
            int i = astring1.length;

            for (int j = 0; j < i; ++j) {
                String s3 = astring1[j];

                if (CommandAbstract.a(s2, s3)) {
                    arraylist.add(s3);
                }
            }

            return arraylist;
        }
    }

    public static MinecraftServer getServer() {
        return k;
    }

    public String getName() {
        return "Server";
    }

    public void sendMessage(String s) {
        this.getLogger().info(StripColor.a(s));
    }

    public boolean a(int i, String s) {
        return true;
    }

    public String a(String s, Object... aobject) {
        return LocaleLanguage.a().a(s, aobject);
    }

    public ICommandHandler getCommandHandler() {
        return this.p;
    }

    public KeyPair F() {
        return this.H;
    }

    public int G() {
        return this.r;
    }

    public void setPort(int i) {
        this.r = i;
    }

    public String H() {
        return this.I;
    }

    public void k(String s) {
        this.I = s;
    }

    public boolean I() {
        return this.I != null;
    }

    public String J() {
        return this.J;
    }

    public void l(String s) {
        this.J = s;
    }

    public void a(KeyPair keypair) {
        this.H = keypair;
    }

    public void c(int i) {
        for (int j = 0; j < this.worldServer.length; ++j) {
            WorldServer worldserver = this.worldServer[j];

            if (worldserver != null) {
                if (worldserver.getWorldData().isHardcore()) {
                    worldserver.difficulty = 3;
                    worldserver.setSpawnFlags(true, true);
                } else if (this.I()) {
                    worldserver.difficulty = i;
                    worldserver.setSpawnFlags(worldserver.difficulty > 0, true);
                } else {
                    worldserver.difficulty = i;
                    worldserver.setSpawnFlags(this.getSpawnMonsters(), this.spawnAnimals);
                }
            }
        }
    }

    protected boolean getSpawnMonsters() {
        return true;
    }

    public boolean M() {
        return this.demoMode;
    }

    public void b(boolean flag) {
        this.demoMode = flag;
    }

    public void c(boolean flag) {
        this.M = flag;
    }

    public Convertable getConvertable() {
        return this.convertable;
    }

    public void P() {
        this.N = true;
        this.getConvertable().d();

        for (int i = 0; i < this.worldServer.length; ++i) {
            WorldServer worldserver = this.worldServer[i];

            if (worldserver != null) {
                worldserver.saveLevel();
            }
        }

        this.getConvertable().e(this.worldServer[0].getDataManager().g());
        this.safeShutdown();
    }

    public String getTexturePack() {
        return this.O;
    }

    public void setTexturePack(String s) {
        this.O = s;
    }

    public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(false));
        mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(0));
        mojangstatisticsgenerator.a("players_current", Integer.valueOf(this.y()));
        mojangstatisticsgenerator.a("players_max", Integer.valueOf(this.z()));
        mojangstatisticsgenerator.a("players_seen", Integer.valueOf(this.s.getSeenPlayers().length));
        mojangstatisticsgenerator.a("uses_auth", Boolean.valueOf(this.onlineMode));
        mojangstatisticsgenerator.a("gui_state", this.ag() ? "enabled" : "disabled");
        mojangstatisticsgenerator.a("avg_tick_ms", Integer.valueOf((int) (MathHelper.a(this.i) * 1.0E-6D)));
        mojangstatisticsgenerator.a("avg_sent_packet_count", Integer.valueOf((int) MathHelper.a(this.e)));
        mojangstatisticsgenerator.a("avg_sent_packet_size", Integer.valueOf((int) MathHelper.a(this.f)));
        mojangstatisticsgenerator.a("avg_rec_packet_count", Integer.valueOf((int) MathHelper.a(this.g)));
        mojangstatisticsgenerator.a("avg_rec_packet_size", Integer.valueOf((int) MathHelper.a(this.h)));
        int i = 0;

        for (int j = 0; j < this.worldServer.length; ++j) {
            if (this.worldServer[j] != null) {
                WorldServer worldserver = this.worldServer[j];
                WorldData worlddata = worldserver.getWorldData();

                mojangstatisticsgenerator.a("world[" + i + "][dimension]", Integer.valueOf(worldserver.worldProvider.dimension));
                mojangstatisticsgenerator.a("world[" + i + "][mode]", worlddata.getGameType());
                mojangstatisticsgenerator.a("world[" + i + "][difficulty]", Integer.valueOf(worldserver.difficulty));
                mojangstatisticsgenerator.a("world[" + i + "][hardcore]", Boolean.valueOf(worlddata.isHardcore()));
                mojangstatisticsgenerator.a("world[" + i + "][generator_name]", worlddata.getType().name());
                mojangstatisticsgenerator.a("world[" + i + "][generator_version]", Integer.valueOf(worlddata.getType().getVersion()));
                mojangstatisticsgenerator.a("world[" + i + "][height]", Integer.valueOf(this.C));
                mojangstatisticsgenerator.a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.K().getLoadedChunks()));
                ++i;
            }
        }

        mojangstatisticsgenerator.a("worlds", Integer.valueOf(i));
    }

    public void b(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("singleplayer", Boolean.valueOf(this.I()));
        mojangstatisticsgenerator.a("server_brand", this.getServerModName());
        mojangstatisticsgenerator.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        mojangstatisticsgenerator.a("dedicated", Boolean.valueOf(this.T()));
    }

    public boolean getSnooperEnabled() {
        return true;
    }

    public int S() {
        return 16;
    }

    public abstract boolean T();

    public boolean getOnlineMode() {
        return this.onlineMode;
    }

    public void setOnlineMode(boolean flag) {
        this.onlineMode = flag;
    }

    public boolean getSpawnAnimals() {
        return this.spawnAnimals;
    }

    public void setSpawnAnimals(boolean flag) {
        this.spawnAnimals = flag;
    }

    public boolean getSpawnNPCs() {
        return this.spawnNPCs;
    }

    public void setSpawnNPCs(boolean flag) {
        this.spawnNPCs = flag;
    }

    public boolean getPvP() {
        return this.pvpMode;
    }

    public void setPvP(boolean flag) {
        this.pvpMode = flag;
    }

    public boolean getAllowFlight() {
        return this.allowFlight;
    }

    public void setAllowFlight(boolean flag) {
        this.allowFlight = flag;
    }

    public abstract boolean getEnableCommandBlock();

    public String getMotd() {
        return this.motd;
    }

    public void setMotd(String s) {
        this.motd = s;
    }

    public int getMaxBuildHeight() {
        return this.C;
    }

    public void d(int i) {
        this.C = i;
    }

    public boolean isStopped() {
        return this.isStopped;
    }

    public PlayerList getPlayerList() {
        return this.s;
    }

    public void a(PlayerList playerlist) {
        this.s = playerlist;
    }

    public void a(EnumGamemode enumgamemode) {
        for (int i = 0; i < this.worldServer.length; ++i) {
            getServer().worldServer[i].getWorldData().setGameType(enumgamemode);
        }
    }

    public abstract ServerConnection ae();

    public boolean ag() {
        return false;
    }

    public abstract String a(EnumGamemode enumgamemode, boolean flag);

    public int ah() {
        return this.ticks;
    }

    public void ai() {
        this.S = true;
    }

    public ChunkCoordinates b() {
        return new ChunkCoordinates(0, 0, 0);
    }

    public int getSpawnProtection() {
        return 16;
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        return false;
    }

    public abstract IConsoleLogManager getLogger();

    public void setForceGamemode(boolean flag) {
        this.T = flag;
    }

    public boolean getForceGamemode() {
        return this.T;
    }

    public static PlayerList a(MinecraftServer minecraftserver) {
        return minecraftserver.s;
    }
}
