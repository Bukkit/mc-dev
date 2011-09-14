package net.minecraft.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public abstract class StructureGenerator extends MapGenBase {

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

    protected abstract boolean a(int i, int j);

    protected abstract StructureStart b(int i, int j);
}
