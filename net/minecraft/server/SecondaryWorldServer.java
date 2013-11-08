package net.minecraft.server;

public class SecondaryWorldServer extends WorldServer {

    public SecondaryWorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings, WorldServer worldserver, MethodProfiler methodprofiler) {
        super(minecraftserver, idatamanager, s, i, worldsettings, methodprofiler);
        this.worldMaps = worldserver.worldMaps;
        this.scoreboard = worldserver.getScoreboard();
        this.worldData = new SecondaryWorldData(worldserver.getWorldData());
    }

    protected void a() {}
}
