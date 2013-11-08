package net.minecraft.server;

import java.util.concurrent.Callable;

public class WorldData {

    private long seed;
    private WorldType type;
    private String generatorOptions;
    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private long time;
    private long dayTime;
    private long lastPlayed;
    private long sizeOnDisk;
    private NBTTagCompound playerData;
    private int dimension;
    private String name;
    private int version;
    private boolean isRaining;
    private int rainTicks;
    private boolean isThundering;
    private int thunderTicks;
    private EnumGamemode gameType;
    private boolean useMapFeatures;
    private boolean hardcore;
    private boolean allowCommands;
    private boolean initialized;
    private GameRules gameRules;

    protected WorldData() {
        this.type = WorldType.NORMAL;
        this.generatorOptions = "";
        this.gameRules = new GameRules();
    }

    public WorldData(NBTTagCompound nbttagcompound) {
        this.type = WorldType.NORMAL;
        this.generatorOptions = "";
        this.gameRules = new GameRules();
        this.seed = nbttagcompound.getLong("RandomSeed");
        if (nbttagcompound.hasKeyOfType("generatorName", 8)) {
            String s = nbttagcompound.getString("generatorName");

            this.type = WorldType.getType(s);
            if (this.type == null) {
                this.type = WorldType.NORMAL;
            } else if (this.type.f()) {
                int i = 0;

                if (nbttagcompound.hasKeyOfType("generatorVersion", 99)) {
                    i = nbttagcompound.getInt("generatorVersion");
                }

                this.type = this.type.a(i);
            }

            if (nbttagcompound.hasKeyOfType("generatorOptions", 8)) {
                this.generatorOptions = nbttagcompound.getString("generatorOptions");
            }
        }

        this.gameType = EnumGamemode.a(nbttagcompound.getInt("GameType"));
        if (nbttagcompound.hasKeyOfType("MapFeatures", 99)) {
            this.useMapFeatures = nbttagcompound.getBoolean("MapFeatures");
        } else {
            this.useMapFeatures = true;
        }

        this.spawnX = nbttagcompound.getInt("SpawnX");
        this.spawnY = nbttagcompound.getInt("SpawnY");
        this.spawnZ = nbttagcompound.getInt("SpawnZ");
        this.time = nbttagcompound.getLong("Time");
        if (nbttagcompound.hasKeyOfType("DayTime", 99)) {
            this.dayTime = nbttagcompound.getLong("DayTime");
        } else {
            this.dayTime = this.time;
        }

        this.lastPlayed = nbttagcompound.getLong("LastPlayed");
        this.sizeOnDisk = nbttagcompound.getLong("SizeOnDisk");
        this.name = nbttagcompound.getString("LevelName");
        this.version = nbttagcompound.getInt("version");
        this.rainTicks = nbttagcompound.getInt("rainTime");
        this.isRaining = nbttagcompound.getBoolean("raining");
        this.thunderTicks = nbttagcompound.getInt("thunderTime");
        this.isThundering = nbttagcompound.getBoolean("thundering");
        this.hardcore = nbttagcompound.getBoolean("hardcore");
        if (nbttagcompound.hasKeyOfType("initialized", 99)) {
            this.initialized = nbttagcompound.getBoolean("initialized");
        } else {
            this.initialized = true;
        }

        if (nbttagcompound.hasKeyOfType("allowCommands", 99)) {
            this.allowCommands = nbttagcompound.getBoolean("allowCommands");
        } else {
            this.allowCommands = this.gameType == EnumGamemode.CREATIVE;
        }

        if (nbttagcompound.hasKeyOfType("Player", 10)) {
            this.playerData = nbttagcompound.getCompound("Player");
            this.dimension = this.playerData.getInt("Dimension");
        }

        if (nbttagcompound.hasKeyOfType("GameRules", 10)) {
            this.gameRules.a(nbttagcompound.getCompound("GameRules"));
        }
    }

    public WorldData(WorldSettings worldsettings, String s) {
        this.type = WorldType.NORMAL;
        this.generatorOptions = "";
        this.gameRules = new GameRules();
        this.seed = worldsettings.d();
        this.gameType = worldsettings.e();
        this.useMapFeatures = worldsettings.g();
        this.name = s;
        this.hardcore = worldsettings.f();
        this.type = worldsettings.h();
        this.generatorOptions = worldsettings.j();
        this.allowCommands = worldsettings.i();
        this.initialized = false;
    }

    public WorldData(WorldData worlddata) {
        this.type = WorldType.NORMAL;
        this.generatorOptions = "";
        this.gameRules = new GameRules();
        this.seed = worlddata.seed;
        this.type = worlddata.type;
        this.generatorOptions = worlddata.generatorOptions;
        this.gameType = worlddata.gameType;
        this.useMapFeatures = worlddata.useMapFeatures;
        this.spawnX = worlddata.spawnX;
        this.spawnY = worlddata.spawnY;
        this.spawnZ = worlddata.spawnZ;
        this.time = worlddata.time;
        this.dayTime = worlddata.dayTime;
        this.lastPlayed = worlddata.lastPlayed;
        this.sizeOnDisk = worlddata.sizeOnDisk;
        this.playerData = worlddata.playerData;
        this.dimension = worlddata.dimension;
        this.name = worlddata.name;
        this.version = worlddata.version;
        this.rainTicks = worlddata.rainTicks;
        this.isRaining = worlddata.isRaining;
        this.thunderTicks = worlddata.thunderTicks;
        this.isThundering = worlddata.isThundering;
        this.hardcore = worlddata.hardcore;
        this.allowCommands = worlddata.allowCommands;
        this.initialized = worlddata.initialized;
        this.gameRules = worlddata.gameRules;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.a(nbttagcompound, this.playerData);
        return nbttagcompound;
    }

    public NBTTagCompound a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();

        this.a(nbttagcompound1, nbttagcompound);
        return nbttagcompound1;
    }

    private void a(NBTTagCompound nbttagcompound, NBTTagCompound nbttagcompound1) {
        nbttagcompound.setLong("RandomSeed", this.seed);
        nbttagcompound.setString("generatorName", this.type.name());
        nbttagcompound.setInt("generatorVersion", this.type.getVersion());
        nbttagcompound.setString("generatorOptions", this.generatorOptions);
        nbttagcompound.setInt("GameType", this.gameType.a());
        nbttagcompound.setBoolean("MapFeatures", this.useMapFeatures);
        nbttagcompound.setInt("SpawnX", this.spawnX);
        nbttagcompound.setInt("SpawnY", this.spawnY);
        nbttagcompound.setInt("SpawnZ", this.spawnZ);
        nbttagcompound.setLong("Time", this.time);
        nbttagcompound.setLong("DayTime", this.dayTime);
        nbttagcompound.setLong("SizeOnDisk", this.sizeOnDisk);
        nbttagcompound.setLong("LastPlayed", MinecraftServer.ap());
        nbttagcompound.setString("LevelName", this.name);
        nbttagcompound.setInt("version", this.version);
        nbttagcompound.setInt("rainTime", this.rainTicks);
        nbttagcompound.setBoolean("raining", this.isRaining);
        nbttagcompound.setInt("thunderTime", this.thunderTicks);
        nbttagcompound.setBoolean("thundering", this.isThundering);
        nbttagcompound.setBoolean("hardcore", this.hardcore);
        nbttagcompound.setBoolean("allowCommands", this.allowCommands);
        nbttagcompound.setBoolean("initialized", this.initialized);
        nbttagcompound.set("GameRules", this.gameRules.a());
        if (nbttagcompound1 != null) {
            nbttagcompound.set("Player", nbttagcompound1);
        }
    }

    public long getSeed() {
        return this.seed;
    }

    public int c() {
        return this.spawnX;
    }

    public int d() {
        return this.spawnY;
    }

    public int e() {
        return this.spawnZ;
    }

    public long getTime() {
        return this.time;
    }

    public long getDayTime() {
        return this.dayTime;
    }

    public NBTTagCompound i() {
        return this.playerData;
    }

    public int j() {
        return this.dimension;
    }

    public void setTime(long i) {
        this.time = i;
    }

    public void setDayTime(long i) {
        this.dayTime = i;
    }

    public void setSpawn(int i, int j, int k) {
        this.spawnX = i;
        this.spawnY = j;
        this.spawnZ = k;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String s) {
        this.name = s;
    }

    public int l() {
        return this.version;
    }

    public void e(int i) {
        this.version = i;
    }

    public boolean isThundering() {
        return this.isThundering;
    }

    public void setThundering(boolean flag) {
        this.isThundering = flag;
    }

    public int getThunderDuration() {
        return this.thunderTicks;
    }

    public void setThunderDuration(int i) {
        this.thunderTicks = i;
    }

    public boolean hasStorm() {
        return this.isRaining;
    }

    public void setStorm(boolean flag) {
        this.isRaining = flag;
    }

    public int getWeatherDuration() {
        return this.rainTicks;
    }

    public void setWeatherDuration(int i) {
        this.rainTicks = i;
    }

    public EnumGamemode getGameType() {
        return this.gameType;
    }

    public boolean shouldGenerateMapFeatures() {
        return this.useMapFeatures;
    }

    public void setGameType(EnumGamemode enumgamemode) {
        this.gameType = enumgamemode;
    }

    public boolean isHardcore() {
        return this.hardcore;
    }

    public WorldType getType() {
        return this.type;
    }

    public void setType(WorldType worldtype) {
        this.type = worldtype;
    }

    public String getGeneratorOptions() {
        return this.generatorOptions;
    }

    public boolean allowCommands() {
        return this.allowCommands;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void d(boolean flag) {
        this.initialized = flag;
    }

    public GameRules getGameRules() {
        return this.gameRules;
    }

    public void a(CrashReportSystemDetails crashreportsystemdetails) {
        crashreportsystemdetails.a("Level seed", (Callable) (new CrashReportLevelSeed(this)));
        crashreportsystemdetails.a("Level generator", (Callable) (new CrashReportLevelGenerator(this)));
        crashreportsystemdetails.a("Level generator options", (Callable) (new CrashReportLevelGeneratorOptions(this)));
        crashreportsystemdetails.a("Level spawn location", (Callable) (new CrashReportLevelSpawnLocation(this)));
        crashreportsystemdetails.a("Level time", (Callable) (new CrashReportLevelTime(this)));
        crashreportsystemdetails.a("Level dimension", (Callable) (new CrashReportLevelDimension(this)));
        crashreportsystemdetails.a("Level storage version", (Callable) (new CrashReportLevelStorageVersion(this)));
        crashreportsystemdetails.a("Level weather", (Callable) (new CrashReportLevelWeather(this)));
        crashreportsystemdetails.a("Level game mode", (Callable) (new CrashReportLevelGameMode(this)));
    }

    static WorldType a(WorldData worlddata) {
        return worlddata.type;
    }

    static boolean b(WorldData worlddata) {
        return worlddata.useMapFeatures;
    }

    static String c(WorldData worlddata) {
        return worlddata.generatorOptions;
    }

    static int d(WorldData worlddata) {
        return worlddata.spawnX;
    }

    static int e(WorldData worlddata) {
        return worlddata.spawnY;
    }

    static int f(WorldData worlddata) {
        return worlddata.spawnZ;
    }

    static long g(WorldData worlddata) {
        return worlddata.time;
    }

    static long h(WorldData worlddata) {
        return worlddata.dayTime;
    }

    static int i(WorldData worlddata) {
        return worlddata.dimension;
    }

    static int j(WorldData worlddata) {
        return worlddata.version;
    }

    static int k(WorldData worlddata) {
        return worlddata.rainTicks;
    }

    static boolean l(WorldData worlddata) {
        return worlddata.isRaining;
    }

    static int m(WorldData worlddata) {
        return worlddata.thunderTicks;
    }

    static boolean n(WorldData worlddata) {
        return worlddata.isThundering;
    }

    static EnumGamemode o(WorldData worlddata) {
        return worlddata.gameType;
    }

    static boolean p(WorldData worlddata) {
        return worlddata.hardcore;
    }

    static boolean q(WorldData worlddata) {
        return worlddata.allowCommands;
    }
}
