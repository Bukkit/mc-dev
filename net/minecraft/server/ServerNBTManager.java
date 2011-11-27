package net.minecraft.server;

import java.io.File;
import java.util.List;

public class ServerNBTManager extends WorldNBTStorage {

    public ServerNBTManager(File file1, String s, boolean flag) {
        super(file1, s, flag);
    }

    public IChunkLoader createChunkLoader(WorldProvider worldprovider) {
        File file1 = this.getDirectory();
        File file2;

        if (worldprovider instanceof WorldProviderHell) {
            file2 = new File(file1, "DIM-1");
            file2.mkdirs();
            return new ChunkRegionLoader(file2);
        } else if (worldprovider instanceof WorldProviderTheEnd) {
            file2 = new File(file1, "DIM1");
            file2.mkdirs();
            return new ChunkRegionLoader(file2);
        } else {
            return new ChunkRegionLoader(file1);
        }
    }

    public void saveWorldData(WorldData worlddata, List list) {
        worlddata.a(19132);
        super.saveWorldData(worlddata, list);
    }

    public void e() {
        try {
            FileIOThread.a.a();
        } catch (InterruptedException interruptedexception) {
            interruptedexception.printStackTrace();
        }

        RegionFileCache.a();
    }
}
