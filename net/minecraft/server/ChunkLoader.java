package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

public class ChunkLoader implements IChunkLoader {

    private File a;
    private boolean b;

    public ChunkLoader(File file1, boolean flag) {
        this.a = file1;
        this.b = flag;
    }

    private File a(int i, int j) {
        String s = "c." + Integer.toString(i, 36) + "." + Integer.toString(j, 36) + ".dat";
        String s1 = Integer.toString(i & 63, 36);
        String s2 = Integer.toString(j & 63, 36);
        File file1 = new File(this.a, s1);

        if (!file1.exists()) {
            if (!this.b) {
                return null;
            }

            file1.mkdir();
        }

        file1 = new File(file1, s2);
        if (!file1.exists()) {
            if (!this.b) {
                return null;
            }

            file1.mkdir();
        }

        file1 = new File(file1, s);
        return !file1.exists() && !this.b ? null : file1;
    }

    public Chunk a(World world, int i, int j) {
        File file1 = this.a(i, j);

        if (file1 != null && file1.exists()) {
            try {
                FileInputStream fileinputstream = new FileInputStream(file1);
                NBTTagCompound nbttagcompound = CompressedStreamTools.a((InputStream) fileinputstream);

                if (!nbttagcompound.a("Level")) {
                    System.out.println("Chunk file at " + i + "," + j + " is missing level data, skipping");
                    return null;
                }

                if (!nbttagcompound.j("Level").a("Blocks")) {
                    System.out.println("Chunk file at " + i + "," + j + " is missing block data, skipping");
                    return null;
                }

                Chunk chunk = a(world, nbttagcompound.j("Level"));

                if (!chunk.a(i, j)) {
                    System.out.println("Chunk file at " + i + "," + j + " is in the wrong location; relocating. (Expected " + i + ", " + j + ", got " + chunk.j + ", " + chunk.k + ")");
                    nbttagcompound.a("xPos", i);
                    nbttagcompound.a("zPos", j);
                    chunk = a(world, nbttagcompound.j("Level"));
                }

                return chunk;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    public void a(World world, Chunk chunk) {
        world.h();
        File file1 = this.a(chunk.j, chunk.k);

        if (file1.exists()) {
            world.v -= file1.length();
        }

        try {
            File file2 = new File(this.a, "tmp_chunk.dat");
            FileOutputStream fileoutputstream = new FileOutputStream(file2);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            nbttagcompound.a("Level", (NBTBase) nbttagcompound1);
            this.a(chunk, world, nbttagcompound1);
            CompressedStreamTools.a(nbttagcompound, (OutputStream) fileoutputstream);
            fileoutputstream.close();
            if (file1.exists()) {
                file1.delete();
            }

            file2.renameTo(file1);
            world.v += file1.length();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(Chunk chunk, World world, NBTTagCompound nbttagcompound) {
        world.h();
        nbttagcompound.a("xPos", chunk.j);
        nbttagcompound.a("zPos", chunk.k);
        nbttagcompound.a("LastUpdate", world.e);
        nbttagcompound.a("Blocks", chunk.b);
        nbttagcompound.a("Data", chunk.e.a);
        nbttagcompound.a("SkyLight", chunk.f.a);
        nbttagcompound.a("BlockLight", chunk.g.a);
        nbttagcompound.a("HeightMap", chunk.h);
        nbttagcompound.a("TerrainPopulated", chunk.n);
        chunk.r = false;
        NBTTagList nbttaglist = new NBTTagList();

        Iterator iterator;
        NBTTagCompound nbttagcompound1;

        for (int i = 0; i < chunk.m.length; ++i) {
            iterator = chunk.m[i].iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                chunk.r = true;
                nbttagcompound1 = new NBTTagCompound();
                if (entity.c(nbttagcompound1)) {
                    nbttaglist.a((NBTBase) nbttagcompound1);
                }
            }
        }

        nbttagcompound.a("Entities", (NBTBase) nbttaglist);
        NBTTagList nbttaglist1 = new NBTTagList();

        iterator = chunk.l.values().iterator();

        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();

            nbttagcompound1 = new NBTTagCompound();
            tileentity.b(nbttagcompound1);
            nbttaglist1.a((NBTBase) nbttagcompound1);
        }

        nbttagcompound.a("TileEntities", (NBTBase) nbttaglist1);
    }

    public static Chunk a(World world, NBTTagCompound nbttagcompound) {
        int i = nbttagcompound.d("xPos");
        int j = nbttagcompound.d("zPos");
        Chunk chunk = new Chunk(world, i, j);

        chunk.b = nbttagcompound.i("Blocks");
        chunk.e = new NibbleArray(nbttagcompound.i("Data"));
        chunk.f = new NibbleArray(nbttagcompound.i("SkyLight"));
        chunk.g = new NibbleArray(nbttagcompound.i("BlockLight"));
        chunk.h = nbttagcompound.i("HeightMap");
        chunk.n = nbttagcompound.l("TerrainPopulated");
        if (!chunk.e.a()) {
            chunk.e = new NibbleArray(chunk.b.length);
        }

        if (chunk.h == null || !chunk.f.a()) {
            chunk.h = new byte[256];
            chunk.f = new NibbleArray(chunk.b.length);
            chunk.b();
        }

        if (!chunk.g.a()) {
            chunk.g = new NibbleArray(chunk.b.length);
            chunk.a();
        }

        NBTTagList nbttaglist = nbttagcompound.k("Entities");

        if (nbttaglist != null) {
            for (int k = 0; k < nbttaglist.b(); ++k) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(k);
                Entity entity = EntityTypes.a(nbttagcompound1, world);

                chunk.r = true;
                if (entity != null) {
                    chunk.a(entity);
                }
            }
        }

        NBTTagList nbttaglist1 = nbttagcompound.k("TileEntities");

        if (nbttaglist1 != null) {
            for (int l = 0; l < nbttaglist1.b(); ++l) {
                NBTTagCompound nbttagcompound2 = (NBTTagCompound) nbttaglist1.a(l);
                TileEntity tileentity = TileEntity.c(nbttagcompound2);

                if (tileentity != null) {
                    chunk.a(tileentity);
                }
            }
        }

        return chunk;
    }

    public void a() {}

    public void b() {}

    public void b(World world, Chunk chunk) {}
}
