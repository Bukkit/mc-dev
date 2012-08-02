package net.minecraft.server;

import java.io.File;

public interface IDataManager {

    WorldData getWorldData();

    void checkSession();

    IChunkLoader createChunkLoader(WorldProvider worldprovider);

    void saveWorldData(WorldData worlddata, NBTTagCompound nbttagcompound);

    void saveWorldData(WorldData worlddata);

    PlayerFileData getPlayerFileData();

    void a();

    File getDataFile(String s);

    String g();
}
