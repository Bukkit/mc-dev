package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Proxy;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;

import net.minecraft.util.com.google.common.base.Charsets;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.util.com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.buffer.ByteBufOutputStream;
import net.minecraft.util.io.netty.buffer.Unpooled;
import net.minecraft.util.io.netty.handler.codec.base64.Base64;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MinecraftServer implements ICommandListener, Runnable, IMojangStatistics {

    private static final Logger h = LogManager.getLogger();
    private static MinecraftServer i;
    private final Convertable convertable;
    private final MojangStatisticsGenerator k = new MojangStatisticsGenerator("server", this, ap());
    private final File universe;
    private final List m = new ArrayList();
    private final ICommandHandler n;
    public final MethodProfiler methodProfiler = new MethodProfiler();
    private final ServerConnection o;
    private final ServerPing p = new ServerPing();
    private final Random q = new Random();
    private String serverIp;
    private int s = -1;
    public WorldServer[] worldServer;
    private PlayerList t;
    private boolean isRunning = true;
    private boolean isStopped;
    private int ticks;
    protected final Proxy c;
    public String d;
    public int e;
    private boolean onlineMode;
    private boolean spawnAnimals;
    private boolean spawnNPCs;
    private boolean pvpMode;
    private boolean allowFlight;
    private String motd;
    private int D;
    private int E = 0;
    public final long[] f = new long[100];
    public long[][] g;
    private KeyPair F;
    private String G;
    private String H;
    private boolean demoMode;
    private boolean K;
    private boolean L;
    private String M = "";
    private boolean N;
    private long O;
    private String P;
    private boolean Q;
    private boolean R;
    private final MinecraftSessionService S;
    private long T = 0L;

    public MinecraftServer(File file1, Proxy proxy) {
        i = this;
        this.c = proxy;
        this.universe = file1;
        this.o = new ServerConnection(this);
        this.n = new CommandDispatcher();
        this.convertable = new WorldLoaderServer(file1);
        this.S = (new YggdrasilAuthenticationService(proxy, UUID.randomUUID().toString())).createMinecraftSessionService();
    }

    protected abstract boolean init();

    protected void a(String s) {
        if (this.getConvertable().isConvertable(s)) {
            h.info("Converting map!");
            this.b("menu.convertingLevel");
            this.getConvertable().convert(s, new ConvertProgressUpdater(this));
        }
    }

    protected synchronized void b(String s) {
        this.P = s;
    }

    protected void a(String s, String s1, long i, WorldType worldtype, String s2) {
        this.a(s);
        this.b("menu.loadingLevel");
        this.worldServer = new WorldServer[3];
        this.g = new long[this.worldServer.length][100];
        IDataManager idatamanager = this.convertable.a(s, true);
        WorldData worlddata = idatamanager.getWorldData();
        WorldSettings worldsettings;

        if (worlddata == null) {
            worldsettings = new WorldSettings(i, this.getGamemode(), this.getGenerateStructures(), this.isHardcore(), worldtype);
            worldsettings.a(s2);
        } else {
            worldsettings = new WorldSettings(worlddata);
        }

        if (this.K) {
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
                if (this.P()) {
                    this.worldServer[j] = new DemoWorldServer(this, idatamanager, s1, b0, this.methodProfiler);
                } else {
                    this.worldServer[j] = new WorldServer(this, idatamanager, s1, b0, worldsettings, this.methodProfiler);
                }
            } else {
                this.worldServer[j] = new SecondaryWorldServer(this, idatamanager, s1, b0, worldsettings, this.worldServer[0], this.methodProfiler);
            }

            this.worldServer[j].addIWorldAccess(new WorldManager(this, this.worldServer[j]));
            if (!this.L()) {
                this.worldServer[j].getWorldData().setGameType(this.getGamemode());
            }

            this.t.setPlayerFileData(this.worldServer);
        }

        this.a(this.getDifficulty());
        this.g();
    }

    protected void g() {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        int i = 0;

        this.b("menu.generatingTerrain");
        byte b0 = 0;

        h.info("Preparing start region for level " + b0);
        WorldServer worldserver = this.worldServer[b0];
        ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
        long j = ap();

        for (int k = -192; k <= 192 && this.isRunning(); k += 16) {
            for (int l = -192; l <= 192 && this.isRunning(); l += 16) {
                long i1 = ap();

                if (i1 - j > 1000L) {
                    this.a_("Preparing spawn area", i * 100 / 625);
                    j = i1;
                }

                ++i;
                worldserver.chunkProviderServer.getChunkAt(chunkcoordinates.x + k >> 4, chunkcoordinates.z + l >> 4);
            }
        }

        this.m();
    }

    public abstract boolean getGenerateStructures();

    public abstract EnumGamemode getGamemode();

    public abstract EnumDifficulty getDifficulty();

    public abstract boolean isHardcore();

    public abstract int l();

    protected void a_(String s, int i) {
        this.d = s;
        this.e = i;
        h.info(s + ": " + i + "%");
    }

    protected void m() {
        this.d = null;
        this.e = 0;
    }

    protected void saveChunks(boolean flag) {
        if (!this.L) {
            WorldServer[] aworldserver = this.worldServer;
            int i = aworldserver.length;

            for (int j = 0; j < i; ++j) {
                WorldServer worldserver = aworldserver[j];

                if (worldserver != null) {
                    if (!flag) {
                        h.info("Saving chunks for level \'" + worldserver.getWorldData().getName() + "\'/" + worldserver.worldProvider.getName());
                    }

                    try {
                        worldserver.save(true, (IProgressUpdate) null);
                    } catch (ExceptionWorldConflict exceptionworldconflict) {
                        h.warn(exceptionworldconflict.getMessage());
                    }
                }
            }
        }
    }

    public void stop() {
        if (!this.L) {
            h.info("Stopping server");
            if (this.ag() != null) {
                this.ag().b();
            }

            if (this.t != null) {
                h.info("Saving players");
                this.t.savePlayers();
                this.t.r();
            }

            h.info("Saving worlds");
            this.saveChunks(false);

            for (int i = 0; i < this.worldServer.length; ++i) {
                WorldServer worldserver = this.worldServer[i];

                worldserver.saveLevel();
            }

            if (this.k.d()) {
                this.k.e();
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
                long i = ap();
                long j = 0L;

                this.p.setMOTD(new ChatComponentText(this.motd));
                this.p.setServerInfo(new ServerPingServerData("1.7.2", 4));
                this.a(this.p);

                while (this.isRunning) {
                    long k = ap();
                    long l = k - i;

                    if (l > 2000L && i - this.O >= 15000L) {
                        h.warn("Can\'t keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)", new Object[] { Long.valueOf(l), Long.valueOf(l / 50L)});
                        l = 2000L;
                        this.O = i;
                    }

                    if (l < 0L) {
                        h.warn("Time ran backwards! Did the system time change?");
                        l = 0L;
                    }

                    j += l;
                    i = k;
                    if (this.worldServer[0].everyoneDeeplySleeping()) {
                        this.t();
                        j = 0L;
                    } else {
                        while (j > 50L) {
                            j -= 50L;
                            this.t();
                        }
                    }

                    Thread.sleep(1L);
                    this.N = true;
                }
            } else {
                this.a((CrashReport) null);
            }
        } catch (Throwable throwable) {
            h.error("Encountered an unexpected exception", throwable);
            CrashReport crashreport = null;

            if (throwable instanceof ReportedException) {
                crashreport = this.b(((ReportedException) throwable).a());
            } else {
                crashreport = this.b(new CrashReport("Exception in server tick loop", throwable));
            }

            File file1 = new File(new File(this.r(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (crashreport.a(file1)) {
                h.error("This crash report has been saved to: " + file1.getAbsolutePath());
            } else {
                h.error("We were unable to save this crash report to disk.");
            }

            this.a(crashreport);
        } finally {
            try {
                this.stop();
                this.isStopped = true;
            } catch (Throwable throwable1) {
                h.error("Exception stopping the server", throwable1);
            } finally {
                this.s();
            }
        }
    }

    private void a(ServerPing serverping) {
        File file1 = this.d("server-icon.png");

        if (file1.isFile()) {
            ByteBuf bytebuf = Unpooled.buffer();

            try {
                BufferedImage bufferedimage = ImageIO.read(file1);

                Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
                Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
                ImageIO.write(bufferedimage, "PNG", new ByteBufOutputStream(bytebuf));
                ByteBuf bytebuf1 = Base64.encode(bytebuf);

                serverping.setFavicon("data:image/png;base64," + bytebuf1.toString(Charsets.UTF_8));
            } catch (Exception exception) {
                h.error("Couldn\'t load server icon", exception);
            }
        }
    }

    protected File r() {
        return new File(".");
    }

    protected void a(CrashReport crashreport) {}

    protected void s() {}

    protected void t() {
        long i = System.nanoTime();

        AxisAlignedBB.a().a();
        ++this.ticks;
        if (this.Q) {
            this.Q = false;
            this.methodProfiler.a = true;
            this.methodProfiler.a();
        }

        this.methodProfiler.a("root");
        this.u();
        if (i - this.T >= 5000000000L) {
            this.T = i;
            this.p.setPlayerSample(new ServerPingPlayerSample(this.C(), this.B()));
            GameProfile[] agameprofile = new GameProfile[Math.min(this.B(), 12)];
            int j = MathHelper.nextInt(this.q, 0, this.B() - agameprofile.length);

            for (int k = 0; k < agameprofile.length; ++k) {
                agameprofile[k] = ((EntityPlayer) this.t.players.get(j + k)).getProfile();
            }

            Collections.shuffle(Arrays.asList(agameprofile));
            this.p.b().a(agameprofile);
        }

        if (this.ticks % 900 == 0) {
            this.methodProfiler.a("save");
            this.t.savePlayers();
            this.saveChunks(true);
            this.methodProfiler.b();
        }

        this.methodProfiler.a("tallying");
        this.f[this.ticks % 100] = System.nanoTime() - i;
        this.methodProfiler.b();
        this.methodProfiler.a("snooper");
        if (!this.k.d() && this.ticks > 100) {
            this.k.a();
        }

        if (this.ticks % 6000 == 0) {
            this.k.b();
        }

        this.methodProfiler.b();
        this.methodProfiler.b();
    }

    public void u() {
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
                    this.t.a(new PacketPlayOutUpdateTime(worldserver.getTime(), worldserver.getDayTime(), worldserver.getGameRules().getBoolean("doDaylightCycle")), worldserver.worldProvider.dimension);
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

            this.g[i][this.ticks % 100] = System.nanoTime() - j;
        }

        this.methodProfiler.c("connection");
        this.ag().c();
        this.methodProfiler.c("players");
        this.t.tick();
        this.methodProfiler.c("tickables");

        for (i = 0; i < this.m.size(); ++i) {
            ((IUpdatePlayerListBox) this.m.get(i)).a();
        }

        this.methodProfiler.b();
    }

    public boolean getAllowNether() {
        return true;
    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
        this.m.add(iupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        DispenserRegistry.b();

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
                dedicatedserver.ay();
            }

            dedicatedserver.w();
            Runtime.getRuntime().addShutdownHook(new ThreadShutdown("Server Shutdown Thread", dedicatedserver));
        } catch (Exception exception) {
            h.fatal("Failed to start the minecraft server", exception);
        }
    }

    public void w() {
        (new ThreadServerApplication(this, "Server thread")).start();
    }

    public File d(String s) {
        return new File(this.r(), s);
    }

    public void info(String s) {
        h.info(s);
    }

    public void warning(String s) {
        h.warn(s);
    }

    public WorldServer getWorldServer(int i) {
        return i == -1 ? this.worldServer[1] : (i == 1 ? this.worldServer[2] : this.worldServer[0]);
    }

    public String x() {
        return this.serverIp;
    }

    public int y() {
        return this.s;
    }

    public String z() {
        return this.motd;
    }

    public String getVersion() {
        return "1.7.2";
    }

    public int B() {
        return this.t.getPlayerCount();
    }

    public int C() {
        return this.t.getMaxPlayers();
    }

    public String[] getPlayers() {
        return this.t.d();
    }

    public String getPlugins() {
        return "";
    }

    public String g(String s) {
        RemoteControlCommandListener.instance.e();
        this.n.a(RemoteControlCommandListener.instance, s);
        return RemoteControlCommandListener.instance.f();
    }

    public boolean isDebugging() {
        return false;
    }

    public void h(String s) {
        h.error(s);
    }

    public void i(String s) {
        if (this.isDebugging()) {
            h.info(s);
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
            List list = this.n.b(icommandlistener, s);

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
        return i;
    }

    public String getName() {
        return "Server";
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {
        h.info(ichatbasecomponent.c());
    }

    public boolean a(int i, String s) {
        return true;
    }

    public ICommandHandler getCommandHandler() {
        return this.n;
    }

    public KeyPair I() {
        return this.F;
    }

    public int J() {
        return this.s;
    }

    public void setPort(int i) {
        this.s = i;
    }

    public String K() {
        return this.G;
    }

    public void j(String s) {
        this.G = s;
    }

    public boolean L() {
        return this.G != null;
    }

    public String M() {
        return this.H;
    }

    public void k(String s) {
        this.H = s;
    }

    public void a(KeyPair keypair) {
        this.F = keypair;
    }

    public void a(EnumDifficulty enumdifficulty) {
        for (int i = 0; i < this.worldServer.length; ++i) {
            WorldServer worldserver = this.worldServer[i];

            if (worldserver != null) {
                if (worldserver.getWorldData().isHardcore()) {
                    worldserver.difficulty = EnumDifficulty.HARD;
                    worldserver.setSpawnFlags(true, true);
                } else if (this.L()) {
                    worldserver.difficulty = enumdifficulty;
                    worldserver.setSpawnFlags(worldserver.difficulty != EnumDifficulty.PEACEFUL, true);
                } else {
                    worldserver.difficulty = enumdifficulty;
                    worldserver.setSpawnFlags(this.getSpawnMonsters(), this.spawnAnimals);
                }
            }
        }
    }

    protected boolean getSpawnMonsters() {
        return true;
    }

    public boolean P() {
        return this.demoMode;
    }

    public void b(boolean flag) {
        this.demoMode = flag;
    }

    public void c(boolean flag) {
        this.K = flag;
    }

    public Convertable getConvertable() {
        return this.convertable;
    }

    public void S() {
        this.L = true;
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

    public String getResourcePack() {
        return this.M;
    }

    public void setTexturePack(String s) {
        this.M = s;
    }

    public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(false));
        mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(0));
        mojangstatisticsgenerator.a("players_current", Integer.valueOf(this.B()));
        mojangstatisticsgenerator.a("players_max", Integer.valueOf(this.C()));
        mojangstatisticsgenerator.a("players_seen", Integer.valueOf(this.t.getSeenPlayers().length));
        mojangstatisticsgenerator.a("uses_auth", Boolean.valueOf(this.onlineMode));
        mojangstatisticsgenerator.a("gui_state", this.ai() ? "enabled" : "disabled");
        mojangstatisticsgenerator.a("run_time", Long.valueOf((ap() - mojangstatisticsgenerator.g()) / 60L * 1000L));
        mojangstatisticsgenerator.a("avg_tick_ms", Integer.valueOf((int) (MathHelper.a(this.f) * 1.0E-6D)));
        int i = 0;

        for (int j = 0; j < this.worldServer.length; ++j) {
            if (this.worldServer[j] != null) {
                WorldServer worldserver = this.worldServer[j];
                WorldData worlddata = worldserver.getWorldData();

                mojangstatisticsgenerator.a("world[" + i + "][dimension]", Integer.valueOf(worldserver.worldProvider.dimension));
                mojangstatisticsgenerator.a("world[" + i + "][mode]", worlddata.getGameType());
                mojangstatisticsgenerator.a("world[" + i + "][difficulty]", worldserver.difficulty);
                mojangstatisticsgenerator.a("world[" + i + "][hardcore]", Boolean.valueOf(worlddata.isHardcore()));
                mojangstatisticsgenerator.a("world[" + i + "][generator_name]", worlddata.getType().name());
                mojangstatisticsgenerator.a("world[" + i + "][generator_version]", Integer.valueOf(worlddata.getType().getVersion()));
                mojangstatisticsgenerator.a("world[" + i + "][height]", Integer.valueOf(this.D));
                mojangstatisticsgenerator.a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.K().getLoadedChunks()));
                ++i;
            }
        }

        mojangstatisticsgenerator.a("worlds", Integer.valueOf(i));
    }

    public void b(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("singleplayer", Boolean.valueOf(this.L()));
        mojangstatisticsgenerator.a("server_brand", this.getServerModName());
        mojangstatisticsgenerator.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        mojangstatisticsgenerator.a("dedicated", Boolean.valueOf(this.V()));
    }

    public boolean getSnooperEnabled() {
        return true;
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

    public void c(int i) {
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

    public ServerConnection ag() {
        return this.o;
    }

    public boolean ai() {
        return false;
    }

    public abstract String a(EnumGamemode enumgamemode, boolean flag);

    public int aj() {
        return this.ticks;
    }

    public void ak() {
        this.Q = true;
    }

    public ChunkCoordinates getChunkCoordinates() {
        return new ChunkCoordinates(0, 0, 0);
    }

    public World getWorld() {
        return this.worldServer[0];
    }

    public int getSpawnProtection() {
        return 16;
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        return false;
    }

    public void setForceGamemode(boolean flag) {
        this.R = flag;
    }

    public boolean getForceGamemode() {
        return this.R;
    }

    public Proxy ao() {
        return this.c;
    }

    public static long ap() {
        return System.currentTimeMillis();
    }

    public int aq() {
        return this.E;
    }

    public void d(int i) {
        this.E = i;
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return new ChatComponentText(this.getName());
    }

    public boolean ar() {
        return true;
    }

    public MinecraftSessionService as() {
        return this.S;
    }

    public ServerPing at() {
        return this.p;
    }

    public void au() {
        this.T = 0L;
    }

    public static Logger av() {
        return h;
    }

    public static PlayerList a(MinecraftServer minecraftserver) {
        return minecraftserver.t;
    }
}
