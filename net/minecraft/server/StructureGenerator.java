package net.minecraft.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public abstract class StructureGenerator extends WorldGenBase {

    private PersistentStructure e;
    protected Map d = new HashMap();

    public StructureGenerator() {}

    public abstract String a();

    protected final void a(World world, int i, int j, int k, int l, Block[] ablock) {
        this.a(world);
        if (!this.d.containsKey(Long.valueOf(ChunkCoordIntPair.a(i, j)))) {
            this.b.nextInt();

            try {
                if (this.a(i, j)) {
                    StructureStart structurestart = this.b(i, j);

                    this.d.put(Long.valueOf(ChunkCoordIntPair.a(i, j)), structurestart);
                    this.a(i, j, structurestart);
                }
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.a(throwable, "Exception preparing structure feature");
                CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Feature being prepared");

                crashreportsystemdetails.a("Is feature chunk", (Callable) (new CrashReportIsFeatureChunk(this, i, j)));
                crashreportsystemdetails.a("Chunk location", String.format("%d,%d", new Object[] { Integer.valueOf(i), Integer.valueOf(j)}));
                crashreportsystemdetails.a("Chunk pos hash", (Callable) (new CrashReportChunkPosHash(this, i, j)));
                crashreportsystemdetails.a("Structure type", (Callable) (new CrashReportStructureType(this)));
                throw new ReportedException(crashreport);
            }
        }
    }

    public boolean a(World world, Random random, int i, int j) {
        this.a(world);
        int k = (i << 4) + 8;
        int l = (j << 4) + 8;
        boolean flag = false;
        Iterator iterator = this.d.values().iterator();

        while (iterator.hasNext()) {
            StructureStart structurestart = (StructureStart) iterator.next();

            if (structurestart.d() && structurestart.a().a(k, l, k + 15, l + 15)) {
                structurestart.a(world, random, new StructureBoundingBox(k, l, k + 15, l + 15));
                flag = true;
                this.a(structurestart.e(), structurestart.f(), structurestart);
            }
        }

        return flag;
    }

    public boolean b(int i, int j, int k) {
        this.a(this.c);
        return this.c(i, j, k) != null;
    }

    protected StructureStart c(int i, int j, int k) {
        Iterator iterator = this.d.values().iterator();

        while (iterator.hasNext()) {
            StructureStart structurestart = (StructureStart) iterator.next();

            if (structurestart.d() && structurestart.a().a(i, k, i, k)) {
                Iterator iterator1 = structurestart.b().iterator();

                while (iterator1.hasNext()) {
                    StructurePiece structurepiece = (StructurePiece) iterator1.next();

                    if (structurepiece.c().b(i, j, k)) {
                        return structurestart;
                    }
                }
            }
        }

        return null;
    }

    public boolean d(int i, int j, int k) {
        this.a(this.c);
        Iterator iterator = this.d.values().iterator();

        StructureStart structurestart;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            structurestart = (StructureStart) iterator.next();
        } while (!structurestart.d());

        return structurestart.a().a(i, k, i, k);
    }

    public ChunkPosition getNearestGeneratedFeature(World world, int i, int j, int k) {
        this.c = world;
        this.a(world);
        this.b.setSeed(world.getSeed());
        long l = this.b.nextLong();
        long i1 = this.b.nextLong();
        long j1 = (long) (i >> 4) * l;
        long k1 = (long) (k >> 4) * i1;

        this.b.setSeed(j1 ^ k1 ^ world.getSeed());
        this.a(world, i >> 4, k >> 4, 0, 0, (Block[]) null);
        double d0 = Double.MAX_VALUE;
        ChunkPosition chunkposition = null;
        Iterator iterator = this.d.values().iterator();

        ChunkPosition chunkposition1;
        int l1;
        int i2;
        double d1;
        int j2;

        while (iterator.hasNext()) {
            StructureStart structurestart = (StructureStart) iterator.next();

            if (structurestart.d()) {
                StructurePiece structurepiece = (StructurePiece) structurestart.b().get(0);

                chunkposition1 = structurepiece.a();
                i2 = chunkposition1.x - i;
                l1 = chunkposition1.y - j;
                j2 = chunkposition1.z - k;
                d1 = (double) (i2 * i2 + l1 * l1 + j2 * j2);
                if (d1 < d0) {
                    d0 = d1;
                    chunkposition = chunkposition1;
                }
            }
        }

        if (chunkposition != null) {
            return chunkposition;
        } else {
            List list = this.o_();

            if (list != null) {
                ChunkPosition chunkposition2 = null;
                Iterator iterator1 = list.iterator();

                while (iterator1.hasNext()) {
                    chunkposition1 = (ChunkPosition) iterator1.next();
                    i2 = chunkposition1.x - i;
                    l1 = chunkposition1.y - j;
                    j2 = chunkposition1.z - k;
                    d1 = (double) (i2 * i2 + l1 * l1 + j2 * j2);
                    if (d1 < d0) {
                        d0 = d1;
                        chunkposition2 = chunkposition1;
                    }
                }

                return chunkposition2;
            } else {
                return null;
            }
        }
    }

    protected List o_() {
        return null;
    }

    private void a(World world) {
        if (this.e == null) {
            this.e = (PersistentStructure) world.a(PersistentStructure.class, this.a());
            if (this.e == null) {
                this.e = new PersistentStructure(this.a());
                world.a(this.a(), (PersistentBase) this.e);
            } else {
                NBTTagCompound nbttagcompound = this.e.a();
                Iterator iterator = nbttagcompound.c().iterator();

                while (iterator.hasNext()) {
                    String s = (String) iterator.next();
                    NBTBase nbtbase = nbttagcompound.get(s);

                    if (nbtbase.getTypeId() == 10) {
                        NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbtbase;

                        if (nbttagcompound1.hasKey("ChunkX") && nbttagcompound1.hasKey("ChunkZ")) {
                            int i = nbttagcompound1.getInt("ChunkX");
                            int j = nbttagcompound1.getInt("ChunkZ");
                            StructureStart structurestart = WorldGenFactory.a(nbttagcompound1, world);

                            this.d.put(Long.valueOf(ChunkCoordIntPair.a(i, j)), structurestart);
                        }
                    }
                }
            }
        }
    }

    private void a(int i, int j, StructureStart structurestart) {
        this.e.a(structurestart.a(i, j), i, j);
        this.e.c();
    }

    protected abstract boolean a(int i, int j);

    protected abstract StructureStart b(int i, int j);
}
