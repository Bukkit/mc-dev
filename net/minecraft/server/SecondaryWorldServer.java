package net.minecraft.server;

public class SecondaryWorldServer extends WorldServer {

    public SecondaryWorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings, WorldServer worldserver) {
        super(minecraftserver, idatamanager, s, i, worldsettings);
        this.worldMaps = worldserver.worldMaps;
    }
}
