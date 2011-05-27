package net.minecraft.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;

public class ChunkRegionLoader implements IChunkLoader {

    private final File a;

    public ChunkRegionLoader(File file1) {
        this.a = file1;
    }

    public Chunk a(World world, int i, int j) {
        DataInputStream datainputstream = RegionFileCache.c(this.a, i, j);

        if (datainputstream != null) {
            NBTTagCompound nbttagcompound = CompressedStreamTools.a((DataInput) datainputstream);

            if (!nbttagcompound.b("Level")) {
                System.out.println("Chunk file at " + i + "," + j + " is missing level data, skipping");
                return null;
            } else if (!nbttagcompound.k("Level").b("Blocks")) {
                System.out.println("Chunk file at " + i + "," + j + " is missing block data, skipping");
                return null;
            } else {
                Chunk chunk = ChunkLoader.a(world, nbttagcompound.k("Level"));

                if (!chunk.a(i, j)) {
                    System.out.println("Chunk file at " + i + "," + j + " is in the wrong location; relocating. (Expected " + i + ", " + j + ", got " + chunk.j + ", " + chunk.k + ")");
                    nbttagcompound.a("xPos", i);
                    nbttagcompound.a("zPos", j);
                    chunk = ChunkLoader.a(world, nbttagcompound.k("Level"));
                }

                return chunk;
            }
        } else {
            return null;
        }
    }

    public void a(World world, Chunk chunk) {
        world.i();

        try {
            DataOutputStream dataoutputstream = RegionFileCache.d(this.a, chunk.j, chunk.k);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            nbttagcompound.a("Level", (NBTBase) nbttagcompound1);
            ChunkLoader.a(chunk, world, nbttagcompound1);
            CompressedStreamTools.a(nbttagcompound, (DataOutput) dataoutputstream);
            dataoutputstream.close();
            WorldData worlddata = world.n();

            worlddata.b(worlddata.g() + (long) RegionFileCache.b(this.a, chunk.j, chunk.k));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void b(World world, Chunk chunk) {}

    public void a() {}

    public void b() {}
}
