package net.minecraft.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class StructureGenerator extends WorldGenBase {

    protected HashMap e = new HashMap();

    public StructureGenerator() {}

    public void a(IChunkProvider ichunkprovider, World world, int i, int j, byte[] abyte) {
        super.a(ichunkprovider, world, i, j, abyte);
    }

    protected void a(World world, int i, int j, int k, int l, byte[] abyte) {
        if (!this.e.containsKey(Long.valueOf(ChunkCoordIntPair.a(i, j)))) {
            this.c.nextInt();
            if (this.a(i, j)) {
                StructureStart structurestart = this.b(i, j);

                this.e.put(Long.valueOf(ChunkCoordIntPair.a(i, j)), structurestart);
            }
        }
    }

    public boolean a(World world, Random random, int i, int j) {
        int k = (i << 4) + 8;
        int l = (j << 4) + 8;
        boolean flag = false;
        Iterator iterator = this.e.values().iterator();

        while (iterator.hasNext()) {
            StructureStart structurestart = (StructureStart) iterator.next();

            if (structurestart.a() && structurestart.b().a(k, l, k + 15, l + 15)) {
                structurestart.a(world, random, new StructureBoundingBox(k, l, k + 15, l + 15));
                flag = true;
            }
        }

        return flag;
    }

    public boolean a(int i, int j, int k) {
        Iterator iterator = this.e.values().iterator();

        while (iterator.hasNext()) {
            StructureStart structurestart = (StructureStart) iterator.next();

            if (structurestart.a() && structurestart.b().a(i, k, i, k)) {
                Iterator iterator1 = structurestart.c().iterator();

                while (iterator1.hasNext()) {
                    StructurePiece structurepiece = (StructurePiece) iterator1.next();

                    if (structurepiece.b().b(i, j, k)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ChunkPosition a(World world, int i, int j, int k) {
        this.d = world;
        this.c.setSeed(world.getSeed());
        long l = this.c.nextLong();
        long i1 = this.c.nextLong();
        long j1 = (long) (i >> 4) * l;
        long k1 = (long) (k >> 4) * i1;

        this.c.setSeed(j1 ^ k1 ^ world.getSeed());
        this.a(world, i >> 4, k >> 4, 0, 0, (byte[]) null);
        double d0 = Double.MAX_VALUE;
        ChunkPosition chunkposition = null;
        Iterator iterator = this.e.values().iterator();

        ChunkPosition chunkposition1;
        int l1;
        int i2;
        double d1;
        int j2;

        while (iterator.hasNext()) {
            StructureStart structurestart = (StructureStart) iterator.next();

            if (structurestart.a()) {
                StructurePiece structurepiece = (StructurePiece) structurestart.c().get(0);

                chunkposition1 = structurepiece.b_();
                i2 = chunkposition1.x - i;
                l1 = chunkposition1.y - j;
                j2 = chunkposition1.z - k;
                d1 = (double) (i2 + i2 * l1 * l1 + j2 * j2);
                if (d1 < d0) {
                    d0 = d1;
                    chunkposition = chunkposition1;
                }
            }
        }

        if (chunkposition != null) {
            return chunkposition;
        } else {
            List list = this.a();

            if (list != null) {
                ChunkPosition chunkposition2 = null;
                Iterator iterator1 = list.iterator();

                while (iterator1.hasNext()) {
                    chunkposition1 = (ChunkPosition) iterator1.next();
                    i2 = chunkposition1.x - i;
                    l1 = chunkposition1.y - j;
                    j2 = chunkposition1.z - k;
                    d1 = (double) (i2 + i2 * l1 * l1 + j2 * j2);
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

    protected List a() {
        return null;
    }

    protected abstract boolean a(int i, int j);

    protected abstract StructureStart b(int i, int j);
}
