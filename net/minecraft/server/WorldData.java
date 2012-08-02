package net.minecraft.server;

public class WorldData {

    private long seed;
    private WorldType type;
    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private long time;
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

    protected WorldData() {
        this.type = WorldType.NORMAL;
    }

    public WorldData(NBTTagCompound nbttagcompound) {
        this.type = WorldType.NORMAL;
        this.seed = nbttagcompound.getLong("RandomSeed");
        if (nbttagcompound.hasKey("generatorName")) {
            String s = nbttagcompound.getString("generatorName");

            this.type = WorldType.getType(s);
            if (this.type == null) {
                this.type = WorldType.NORMAL;
            } else if (this.type.e()) {
                int i = 0;

                if (nbttagcompound.hasKey("generatorVersion")) {
                    i = nbttagcompound.getInt("generatorVersion");
                }

                this.type = this.type.a(i);
            }
        }

        this.gameType = EnumGamemode.a(nbttagcompound.getInt("GameType"));
        if (nbttagcompound.hasKey("MapFeatures")) {
            this.useMapFeatures = nbttagcompound.getBoolean("MapFeatures");
        } else {
            this.useMapFeatures = true;
        }

        this.spawnX = nbttagcompound.getInt("SpawnX");
        this.spawnY = nbttagcompound.getInt("SpawnY");
        this.spawnZ = nbttagcompound.getInt("SpawnZ");
        this.time = nbttagcompound.getLong("Time");
        this.lastPlayed = nbttagcompound.getLong("LastPlayed");
        this.sizeOnDisk = nbttagcompound.getLong("SizeOnDisk");
        this.name = nbttagcompound.getString("LevelName");
        this.version = nbttagcompound.getInt("version");
        this.rainTicks = nbttagcompound.getInt("rainTime");
        this.isRaining = nbttagcompound.getBoolean("raining");
        this.thunderTicks = nbttagcompound.getInt("thunderTime");
        this.isThundering = nbttagcompound.getBoolean("thundering");
        this.hardcore = nbttagcompound.getBoolean("hardcore");
        if (nbttagcompound.hasKey("initialized")) {
            this.initialized = nbttagcompound.getBoolean("initialized");
        } else {
            this.initialized = true;
        }

        if (nbttagcompound.hasKey("allowCommands")) {
            this.allowCommands = nbttagcompound.getBoolean("allowCommands");
        } else {
            this.allowCommands = this.gameType == EnumGamemode.CREATIVE;
        }

        if (nbttagcompound.hasKey("Player")) {
            this.playerData = nbttagcompound.getCompound("Player");
            this.dimension = this.playerData.getInt("Dimension");
        }
    }

    public WorldData(WorldSettings worldsettings, String s) {
        this.type = WorldType.NORMAL;
        this.seed = worldsettings.d();
        this.gameType = worldsettings.e();
        this.useMapFeatures = worldsettings.g();
        this.name = s;
        this.hardcore = worldsettings.f();
        this.type = worldsettings.h();
        this.allowCommands = worldsettings.i();
        this.initialized = false;
    }

    public WorldData(WorldData worlddata) {
        this.type = WorldType.NORMAL;
        this.seed = worlddata.seed;
        this.type = worlddata.type;
        this.gameType = worlddata.gameType;
        this.useMapFeatures = worlddata.useMapFeatures;
        this.spawnX = worlddata.spawnX;
        this.spawnY = worlddata.spawnY;
        this.spawnZ = worlddata.spawnZ;
        this.time = worlddata.time;
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
        nbttagcompound.setInt("GameType", this.gameType.a());
        nbttagcompound.setBoolean("MapFeatures", this.useMapFeatures);
        nbttagcompound.setInt("SpawnX", this.spawnX);
        nbttagcompound.setInt("SpawnY", this.spawnY);
        nbttagcompound.setInt("SpawnZ", this.spawnZ);
        nbttagcompound.setLong("Time", this.time);
        nbttagcompound.setLong("SizeOnDisk", this.sizeOnDisk);
        nbttagcompound.setLong("LastPlayed", System.currentTimeMillis());
        nbttagcompound.setString("LevelName", this.name);
        nbttagcompound.setInt("version", this.version);
        nbttagcompound.setInt("rainTime", this.rainTicks);
        nbttagcompound.setBoolean("raining", this.isRaining);
        nbttagcompound.setInt("thunderTime", this.thunderTicks);
        nbttagcompound.setBoolean("thundering", this.isThundering);
        nbttagcompound.setBoolean("hardcore", this.hardcore);
        nbttagcompound.setBoolean("allowCommands", this.allowCommands);
        nbttagcompound.setBoolean("initialized", this.initialized);
        if (nbttagcompound1 != null) {
            nbttagcompound.setCompound("Player", nbttagcompound1);
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

    public NBTTagCompound h() {
        return this.playerData;
    }

    public int i() {
        return this.dimension;
    }

    public void b(long i) {
        this.time = i;
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

    public int k() {
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

    public boolean allowCommands() {
        return this.allowCommands;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void d(boolean flag) {
        this.initialized = flag;
    }
}
