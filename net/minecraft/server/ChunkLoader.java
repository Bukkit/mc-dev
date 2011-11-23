package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

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

                if (!nbttagcompound.hasKey("Level")) {
                    System.out.println("Chunk file at " + i + "," + j + " is missing level data, skipping");
                    return null;
                }

                if (!nbttagcompound.l("Level").hasKey("Blocks")) {
                    System.out.println("Chunk file at " + i + "," + j + " is missing block data, skipping");
                    return null;
                }

                Chunk chunk = a(world, nbttagcompound.l("Level"));

                if (!chunk.a(i, j)) {
                    System.out.println("Chunk file at " + i + "," + j + " is in the wrong location; relocating. (Expected " + i + ", " + j + ", got " + chunk.x + ", " + chunk.z + ")");
                    nbttagcompound.a("xPos", i);
                    nbttagcompound.a("zPos", j);
                    chunk = a(world, nbttagcompound.l("Level"));
                }

                chunk.h();
                return chunk;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    public void a(World world, Chunk chunk) {
        world.l();
        File file1 = this.a(chunk.x, chunk.z);

        if (file1.exists()) {
            WorldData worlddata = world.r();

            worlddata.b(worlddata.g() - file1.length());
        }

        try {
            File file2 = new File(this.a, "tmp_chunk.dat");
            FileOutputStream fileoutputstream = new FileOutputStream(file2);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            nbttagcompound.a("Level", (NBTBase) nbttagcompound1);
            a(chunk, world, nbttagcompound1);
            CompressedStreamTools.a(nbttagcompound, (OutputStream) fileoutputstream);
            fileoutputstream.close();
            if (file1.exists()) {
                file1.delete();
            }

            file2.renameTo(file1);
            WorldData worlddata1 = world.r();

            worlddata1.b(worlddata1.g() + file1.length());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void a(Chunk chunk, World world, NBTTagCompound nbttagcompound) {
        world.l();
        nbttagcompound.a("xPos", chunk.x);
        nbttagcompound.a("zPos", chunk.z);
        nbttagcompound.setLong("LastUpdate", world.getTime());
        nbttagcompound.a("Blocks", chunk.b);
        nbttagcompound.a("Data", chunk.g.a);
        nbttagcompound.a("SkyLight", chunk.h.a);
        nbttagcompound.a("BlockLight", chunk.i.a);
        nbttagcompound.a("HeightMap", chunk.heightMap);
        nbttagcompound.a("TerrainPopulated", chunk.done);
        chunk.s = false;
        NBTTagList nbttaglist = new NBTTagList();

        Iterator iterator;
        NBTTagCompound nbttagcompound1;

        for (int i = 0; i < chunk.entitySlices.length; ++i) {
            iterator = chunk.entitySlices[i].iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                chunk.s = true;
                nbttagcompound1 = new NBTTagCompound();
                if (entity.c(nbttagcompound1)) {
                    nbttaglist.a((NBTBase) nbttagcompound1);
                }
            }
        }

        nbttagcompound.a("Entities", (NBTBase) nbttaglist);
        NBTTagList nbttaglist1 = new NBTTagList();

        iterator = chunk.tileEntities.values().iterator();

        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();

            nbttagcompound1 = new NBTTagCompound();
            tileentity.b(nbttagcompound1);
            nbttaglist1.a((NBTBase) nbttagcompound1);
        }

        nbttagcompound.a("TileEntities", (NBTBase) nbttaglist1);
        List list = world.a(chunk, false);

        if (list != null) {
            long j = world.getTime();
            NBTTagList nbttaglist2 = new NBTTagList();
            Iterator iterator1 = list.iterator();

            while (iterator1.hasNext()) {
                NextTickListEntry nextticklistentry = (NextTickListEntry) iterator1.next();
                NBTTagCompound nbttagcompound2 = new NBTTagCompound();

                nbttagcompound2.a("i", nextticklistentry.d);
                nbttagcompound2.a("x", nextticklistentry.a);
                nbttagcompound2.a("y", nextticklistentry.b);
                nbttagcompound2.a("z", nextticklistentry.c);
                nbttagcompound2.a("t", (int) (nextticklistentry.e - j));
                nbttaglist2.a((NBTBase) nbttagcompound2);
            }

            nbttagcompound.a("TileTicks", (NBTBase) nbttaglist2);
        }
    }

    public static Chunk a(World world, NBTTagCompound nbttagcompound) {
        int i = nbttagcompound.f("xPos");
        int j = nbttagcompound.f("zPos");
        Chunk chunk = new Chunk(world, i, j);

        chunk.b = nbttagcompound.k("Blocks");
        chunk.g = new NibbleArray(nbttagcompound.k("Data"), world.heightBits);
        chunk.h = new NibbleArray(nbttagcompound.k("SkyLight"), world.heightBits);
        chunk.i = new NibbleArray(nbttagcompound.k("BlockLight"), world.heightBits);
        chunk.heightMap = nbttagcompound.k("HeightMap");
        chunk.done = nbttagcompound.n("TerrainPopulated");
        if (!chunk.g.a()) {
            chunk.g = new NibbleArray(chunk.b.length, world.heightBits);
        }

        if (chunk.heightMap == null || !chunk.h.a()) {
            chunk.heightMap = new byte[256];
            chunk.h = new NibbleArray(chunk.b.length, world.heightBits);
            chunk.initLighting();
        }

        if (!chunk.i.a()) {
            chunk.i = new NibbleArray(chunk.b.length, world.heightBits);
            chunk.a();
        }

        NBTTagList nbttaglist = nbttagcompound.m("Entities");

        if (nbttaglist != null) {
            for (int k = 0; k < nbttaglist.d(); ++k) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(k);
                Entity entity = EntityTypes.a(nbttagcompound1, world);

                chunk.s = true;
                if (entity != null) {
                    chunk.a(entity);
                }
            }
        }

        NBTTagList nbttaglist1 = nbttagcompound.m("TileEntities");

        if (nbttaglist1 != null) {
            for (int l = 0; l < nbttaglist1.d(); ++l) {
                NBTTagCompound nbttagcompound2 = (NBTTagCompound) nbttaglist1.a(l);
                TileEntity tileentity = TileEntity.c(nbttagcompound2);

                if (tileentity != null) {
                    chunk.a(tileentity);
                }
            }
        }

        if (nbttagcompound.hasKey("TileTicks")) {
            NBTTagList nbttaglist2 = nbttagcompound.m("TileTicks");

            if (nbttaglist2 != null) {
                for (int i1 = 0; i1 < nbttaglist2.d(); ++i1) {
                    NBTTagCompound nbttagcompound3 = (NBTTagCompound) nbttaglist2.a(i1);

                    world.d(nbttagcompound3.f("x"), nbttagcompound3.f("y"), nbttagcompound3.f("z"), nbttagcompound3.f("i"), nbttagcompound3.f("t"));
                }
            }
        }

        return chunk;
    }

    public void a() {}

    public void b() {}

    public void b(World world, Chunk chunk) {}
}
