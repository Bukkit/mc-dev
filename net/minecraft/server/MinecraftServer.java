package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.net.Proxy;
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

    private static MinecraftServer l;
    private final Convertable convertable;
    private final MojangStatisticsGenerator n = new MojangStatisticsGenerator("server", this, aq());
    private final File universe;
    private final List p = new ArrayList();
    private final ICommandHandler q;
    public final MethodProfiler methodProfiler = new MethodProfiler();
    private String serverIp;
    private int s = -1;
    public WorldServer[] worldServer;
    private PlayerList t;
    private boolean isRunning = true;
    private boolean isStopped;
    private int ticks;
    protected Proxy c;
    public String d;
    public int e;
    private boolean onlineMode;
    private boolean spawnAnimals;
    private boolean spawnNPCs;
    private boolean pvpMode;
    private boolean allowFlight;
    private String motd;
    private int D;
    private long E;
    private long F;
    private long G;
    private long H;
    public final long[] f;
    public final long[] g;
    public final long[] h;
    public final long[] i;
    public final long[] j;
    public long[][] k;
    private KeyPair I;
    private String J;
    private String K;
    private boolean demoMode;
    private boolean N;
    private boolean O;
    private String P;
    private boolean Q;
    private long R;
    private String S;
    private boolean T;
    private boolean U;

    public MinecraftServer(File file1) {
        this.c = Proxy.NO_PROXY;
        this.f = new long[100];
        this.g = new long[100];
        this.h = new long[100];
        this.i = new long[100];
        this.j = new long[100];
        this.P = "";
        l = this;
        this.universe = file1;
        this.q = new CommandDispatcher();
        this.convertable = new WorldLoaderServer(file1);
        this.ar();
    }

    private void ar() {
        DispenserRegistry.a();
    }

    protected abstract boolean init();

    protected void a(String s) {
        if (this.getConvertable().isConvertable(s)) {
            this.getLogger().info("Converting map!");
            this.b("menu.convertingLevel");
            this.getConvertable().convert(s, new ConvertProgressUpdater(this));
        }
    }

    protected synchronized void b(String s) {
        this.S = s;
    }

    protected void a(String s, String s1, long i, WorldType worldtype, String s2) {
        this.a(s);
        this.b("menu.loadingLevel");
        this.worldServer = new WorldServer[3];
        this.k = new long[this.worldServer.length][100];
        IDataManager idatamanager = this.convertable.a(s, true);
        WorldData worlddata = idatamanager.getWorldData();
        WorldSettings worldsettings;

        if (worlddata == null) {
            worldsettings = new WorldSettings(i, this.getGamemode(), this.getGenerateStructures(), this.isHardcore(), worldtype);
            worldsettings.a(s2);
        } else {
            worldsettings = new WorldSettings(worlddata);
        }

        if (this.N) {
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
                if (this.O()) {
                    this.worldServer[j] = new DemoWorldServer(this, idatamanager, s1, b0, this.methodProfiler, this.getLogger());
                } else {
                    this.worldServer[j] = new WorldServer(this, idatamanager, s1, b0, worldsettings, this.methodProfiler, this.getLogger());
                }
            } else {
                this.worldServer[j] = new SecondaryWorldServer(this, idatamanager, s1, b0, worldsettings, this.worldServer[0], this.methodProfiler, this.getLogger());
            }

            this.worldServer[j].addIWorldAccess(new WorldManager(this, this.worldServer[j]));
            if (!this.K()) {
                this.worldServer[j].getWorldData().setGameType(this.getGamemode());
            }

            this.t.setPlayerFileData(this.worldServer);
        }

        this.c(this.getDifficulty());
        this.f();
    }

    protected void f() {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        int i = 0;

        this.b("menu.generatingTerrain");
        byte b0 = 0;

        this.getLogger().info("Preparing start region for level " + b0);
        WorldServer worldserver = this.worldServer[b0];
        ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
        long j = aq();

        for (int k = -192; k <= 192 && this.isRunning(); k += 16) {
            for (int l = -192; l <= 192 && this.isRunning(); l += 16) {
                long i1 = aq();

                if (i1 - j > 1000L) {
                    this.a_("Preparing spawn area", i * 100 / 625);
                    j = i1;
                }

                ++i;
                worldserver.chunkProviderServer.getChunkAt(chunkcoordinates.x + k >> 4, chunkcoordinates.z + l >> 4);
            }
        }

        this.l();
    }

    public abstract boolean getGenerateStructures();

    public abstract EnumGamemode getGamemode();

    public abstract int getDifficulty();

    public abstract boolean isHardcore();

    public abstract int k();

    protected void a_(String s, int i) {
        this.d = s;
        this.e = i;
        this.getLogger().info(s + ": " + i + "%");
    }

    protected void l() {
        this.d = null;
        this.e = 0;
    }

    protected void saveChunks(boolean flag) {
        if (!this.O) {
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
        if (!this.O) {
            this.getLogger().info("Stopping server");
            if (this.ag() != null) {
                this.ag().a();
            }

            if (this.t != null) {
                this.getLogger().info("Saving players");
                this.t.savePlayers();
                this.t.r();
            }

            this.getLogger().info("Saving worlds");
            this.saveChunks(false);

            for (int i = 0; i < this.worldServer.length; ++i) {
                WorldServer worldserver = this.worldServer[i];

                worldserver.saveLevel();
            }

            if (this.n != null && this.n.d()) {
                this.n.e();
            }
        }
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void c(String s) {
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
                long i = aq();

                for (long j = 0L; this.isRunning; this.Q = true) {
                    long k = aq();
                    long l = k - i;

                    if (l > 2000L && i - this.R >= 15000L) {
                        this.getLogger().warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        l = 2000L;
                        this.R = i;
                    }

                    if (l < 0L) {
                        this.getLogger().warning("Time ran backwards! Did the system time change?");
                        l = 0L;
                    }

                    j += l;
                    i = k;
                    if (this.worldServer[0].everyoneDeeplySleeping()) {
                        this.s();
                        j = 0L;
                    } else {
                        while (j > 50L) {
                            j -= 50L;
                            this.s();
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

            File file1 = new File(new File(this.q(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

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
                this.r();
            }
        }
    }

    protected File q() {
        return new File(".");
    }

    protected void a(CrashReport crashreport) {}

    protected void r() {}

    protected void s() {
        long i = System.nanoTime();

        AxisAlignedBB.a().a();
        ++this.ticks;
        if (this.T) {
            this.T = false;
            this.methodProfiler.a = true;
            this.methodProfiler.a();
        }

        this.methodProfiler.a("root");
        this.t();
        if (this.ticks % 900 == 0) {
            this.methodProfiler.a("save");
            this.t.savePlayers();
            this.saveChunks(true);
            this.methodProfiler.b();
        }

        this.methodProfiler.a("tallying");
        this.j[this.ticks % 100] = System.nanoTime() - i;
        this.f[this.ticks % 100] = Packet.q - this.E;
        this.E = Packet.q;
        this.g[this.ticks % 100] = Packet.r - this.F;
        this.F = Packet.r;
        this.h[this.ticks % 100] = Packet.o - this.G;
        this.G = Packet.o;
        this.i[this.ticks % 100] = Packet.p - this.H;
        this.H = Packet.p;
        this.methodProfiler.b();
        this.methodProfiler.a("snooper");
        if (!this.n.d() && this.ticks > 100) {
            this.n.a();
        }

        if (this.ticks % 6000 == 0) {
            this.n.b();
        }

        this.methodProfiler.b();
        this.methodProfiler.b();
    }

    public void t() {
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
                    this.t.a(new Packet4UpdateTime(worldserver.getTime(), worldserver.getDayTime(), worldserver.getGameRules().getBoolean("doDaylightCycle")), worldserver.worldProvider.dimension);
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

            this.k[i][this.ticks % 100] = System.nanoTime() - j;
        }

        this.methodProfiler.c("connection");
        this.ag().b();
        this.methodProfiler.c("players");
        this.t.tick();
        this.methodProfiler.c("tickables");

        for (i = 0; i < this.p.size(); ++i) {
            ((IUpdatePlayerListBox) this.p.get(i)).a();
        }

        this.methodProfiler.b();
    }

    public boolean getAllowNether() {
        return true;
    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
        this.p.add(iupdateplayerlistbox);
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
                dedicatedserver.j(s);
            }

            if (s2 != null) {
                dedicatedserver.k(s2);
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
                dedicatedserver.at();
            }

            dedicatedserver.v();
            Runtime.getRuntime().addShutdownHook(new ThreadShutdown(dedicatedserver));
        } catch (Exception exception) {
            if (iconsolelogmanager != null) {
                iconsolelogmanager.severe("Failed to start the minecraft server", exception);
            } else {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", exception);
            }
        }
    }

    public void v() {
        (new ThreadServerApplication(this, "Server thread")).start();
    }

    public File d(String s) {
        return new File(this.q(), s);
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

    public String w() {
        return this.serverIp;
    }

    public int x() {
        return this.s;
    }

    public String y() {
        return this.motd;
    }

    public String getVersion() {
        return "1.6.2";
    }

    public int A() {
        return this.t.getPlayerCount();
    }

    public int B() {
        return this.t.getMaxPlayers();
    }

    public String[] getPlayers() {
        return this.t.d();
    }

    public String getPlugins() {
        return "";
    }

    public String g(String s) {
        RemoteControlCommandListener.instance.d();
        this.q.a(RemoteControlCommandListener.instance, s);
        return RemoteControlCommandListener.instance.e();
    }

    public boolean isDebugging() {
        return false;
    }

    public void h(String s) {
        this.getLogger().severe(s);
    }

    public void i(String s) {
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

        if (this.t != null) {
            crashreport.g().a("Player Count", (Callable) (new CrashReportPlayerCount(this)));
        }

        return crashreport;
    }

    public List a(ICommandListener icommandlistener, String s) {
        ArrayList arraylist = new ArrayList();

        if (s.startsWith("/")) {
            s = s.substring(1);
            boolean flag = !s.contains(" ");
            List list = this.q.b(icommandlistener, s);

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
            String[] astring1 = this.t.d();
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
        return l;
    }

    public String getName() {
        return "Server";
    }

    public void sendMessage(ChatMessage chatmessage) {
        this.getLogger().info(chatmessage.toString());
    }

    public boolean a(int i, String s) {
        return true;
    }

    public ICommandHandler getCommandHandler() {
        return this.q;
    }

    public KeyPair H() {
        return this.I;
    }

    public int I() {
        return this.s;
    }

    public void setPort(int i) {
        this.s = i;
    }

    public String J() {
        return this.J;
    }

    public void j(String s) {
        this.J = s;
    }

    public boolean K() {
        return this.J != null;
    }

    public String L() {
        return this.K;
    }

    public void k(String s) {
        this.K = s;
    }

    public void a(KeyPair keypair) {
        this.I = keypair;
    }

    public void c(int i) {
        for (int j = 0; j < this.worldServer.length; ++j) {
            WorldServer worldserver = this.worldServer[j];

            if (worldserver != null) {
                if (worldserver.getWorldData().isHardcore()) {
                    worldserver.difficulty = 3;
                    worldserver.setSpawnFlags(true, true);
                } else if (this.K()) {
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

    public boolean O() {
        return this.demoMode;
    }

    public void b(boolean flag) {
        this.demoMode = flag;
    }

    public void c(boolean flag) {
        this.N = flag;
    }

    public Convertable getConvertable() {
        return this.convertable;
    }

    public void R() {
        this.O = true;
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
        return this.P;
    }

    public void setTexturePack(String s) {
        this.P = s;
    }

    public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(false));
        mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(0));
        mojangstatisticsgenerator.a("players_current", Integer.valueOf(this.A()));
        mojangstatisticsgenerator.a("players_max", Integer.valueOf(this.B()));
        mojangstatisticsgenerator.a("players_seen", Integer.valueOf(this.t.getSeenPlayers().length));
        mojangstatisticsgenerator.a("uses_auth", Boolean.valueOf(this.onlineMode));
        mojangstatisticsgenerator.a("gui_state", this.ai() ? "enabled" : "disabled");
        mojangstatisticsgenerator.a("run_time", Long.valueOf((aq() - mojangstatisticsgenerator.g()) / 60L * 1000L));
        mojangstatisticsgenerator.a("avg_tick_ms", Integer.valueOf((int) (MathHelper.a(this.j) * 1.0E-6D)));
        mojangstatisticsgenerator.a("avg_sent_packet_count", Integer.valueOf((int) MathHelper.a(this.f)));
        mojangstatisticsgenerator.a("avg_sent_packet_size", Integer.valueOf((int) MathHelper.a(this.g)));
        mojangstatisticsgenerator.a("avg_rec_packet_count", Integer.valueOf((int) MathHelper.a(this.h)));
        mojangstatisticsgenerator.a("avg_rec_packet_size", Integer.valueOf((int) MathHelper.a(this.i)));
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
                mojangstatisticsgenerator.a("world[" + i + "][height]", Integer.valueOf(this.D));
                mojangstatisticsgenerator.a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.L().getLoadedChunks()));
                ++i;
            }
        }

        mojangstatisticsgenerator.a("worlds", Integer.valueOf(i));
    }

    public void b(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("singleplayer", Boolean.valueOf(this.K()));
        mojangstatisticsgenerator.a("server_brand", this.getServerModName());
        mojangstatisticsgenerator.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        mojangstatisticsgenerator.a("dedicated", Boolean.valueOf(this.V()));
    }

    public boolean getSnooperEnabled() {
        return true;
    }

    public int U() {
        return 16;
    }

    public abstract boolean V();

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
        return this.D;
    }

    public void d(int i) {
        this.D = i;
    }

    public boolean isStopped() {
        return this.isStopped;
    }

    public PlayerList getPlayerList() {
        return this.t;
    }

    public void a(PlayerList playerlist) {
        this.t = playerlist;
    }

    public void a(EnumGamemode enumgamemode) {
        for (int i = 0; i < this.worldServer.length; ++i) {
            getServer().worldServer[i].getWorldData().setGameType(enumgamemode);
        }
    }

    public abstract ServerConnection ag();

    public boolean ai() {
        return false;
    }

    public abstract String a(EnumGamemode enumgamemode, boolean flag);

    public int aj() {
        return this.ticks;
    }

    public void ak() {
        this.T = true;
    }

    public ChunkCoordinates b() {
        return new ChunkCoordinates(0, 0, 0);
    }

    public World f_() {
        return this.worldServer[0];
    }

    public int getSpawnProtection() {
        return 16;
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        return false;
    }

    public abstract IConsoleLogManager getLogger();

    public void setForceGamemode(boolean flag) {
        this.U = flag;
    }

    public boolean getForceGamemode() {
        return this.U;
    }

    public Proxy ap() {
        return this.c;
    }

    public static long aq() {
        return System.currentTimeMillis();
    }

    public static PlayerList a(MinecraftServer minecraftserver) {
        return minecraftserver.t;
    }
}
