package net.minecraft.server;

public class SecondaryWorldData extends WorldData {

    private final WorldData a;

    public SecondaryWorldData(WorldData worlddata) {
        this.a = worlddata;
    }

    public NBTTagCompound a() {
        return this.a.a();
    }

    public NBTTagCompound a(NBTTagCompound nbttagcompound) {
        return this.a.a(nbttagcompound);
    }

    public long getSeed() {
        return this.a.getSeed();
    }

    public int c() {
        return this.a.c();
    }

    public int d() {
        return this.a.d();
    }

    public int e() {
        return this.a.e();
    }

    public long getTime() {
        return this.a.getTime();
    }

    public long getDayTime() {
        return this.a.getDayTime();
    }

    public NBTTagCompound i() {
        return this.a.i();
    }

    public int j() {
        return this.a.j();
    }

    public String getName() {
        return this.a.getName();
    }

    public int l() {
        return this.a.l();
    }

    public boolean isThundering() {
        return this.a.isThundering();
    }

    public int getThunderDuration() {
        return this.a.getThunderDuration();
    }

    public boolean hasStorm() {
        return this.a.hasStorm();
    }

    public int getWeatherDuration() {
        return this.a.getWeatherDuration();
    }

    public EnumGamemode getGameType() {
        return this.a.getGameType();
    }

    public void setTime(long i) {}

    public void setDayTime(long i) {}

    public void setSpawn(int i, int j, int k) {}

    public void setName(String s) {}

    public void e(int i) {}

    public void setThundering(boolean flag) {}

    public void setThunderDuration(int i) {}

    public void setStorm(boolean flag) {}

    public void setWeatherDuration(int i) {}

    public boolean shouldGenerateMapFeatures() {
        return this.a.shouldGenerateMapFeatures();
    }

    public boolean isHardcore() {
        return this.a.isHardcore();
    }

    public WorldType getType() {
        return this.a.getType();
    }

    public void setType(WorldType worldtype) {}

    public boolean allowCommands() {
        return this.a.allowCommands();
    }

    public boolean isInitialized() {
        return this.a.isInitialized();
    }

    public void d(boolean flag) {}

    public GameRules getGameRules() {
        return this.a.getGameRules();
    }
}
