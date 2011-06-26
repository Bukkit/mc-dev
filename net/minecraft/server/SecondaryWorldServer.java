package net.minecraft.server;

public class SecondaryWorldServer extends WorldServer {

    public SecondaryWorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, long j, WorldServer worldserver) {
        super(minecraftserver, idatamanager, s, i, j);
        this.worldMaps = worldserver.worldMaps;
    }
}
