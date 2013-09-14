package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenVillageStart extends StructureStart {

    private boolean c;

    public WorldGenVillageStart() {}

    public WorldGenVillageStart(World world, Random random, int i, int j, int k) {
        super(i, j);
        List list = WorldGenVillagePieces.a(random, k);
        WorldGenVillageStartPiece worldgenvillagestartpiece = new WorldGenVillageStartPiece(world.getWorldChunkManager(), 0, random, (i << 4) + 2, (j << 4) + 2, list, k);

        this.a.add(worldgenvillagestartpiece);
        worldgenvillagestartpiece.a(worldgenvillagestartpiece, this.a, random);
        List list1 = worldgenvillagestartpiece.j;
        List list2 = worldgenvillagestartpiece.i;

        int l;

        while (!list1.isEmpty() || !list2.isEmpty()) {
            StructurePiece structurepiece;

            if (list1.isEmpty()) {
                l = random.nextInt(list2.size());
                structurepiece = (StructurePiece) list2.remove(l);
                structurepiece.a((StructurePiece) worldgenvillagestartpiece, (List) this.a, random);
            } else {
                l = random.nextInt(list1.size());
                structurepiece = (StructurePiece) list1.remove(l);
                structurepiece.a((StructurePiece) worldgenvillagestartpiece, (List) this.a, random);
            }
        }

        this.c();
        l = 0;
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece1 = (StructurePiece) iterator.next();

            if (!(structurepiece1 instanceof WorldGenVillageRoadPiece)) {
                ++l;
            }
        }

        this.c = l > 2;
    }

    public boolean d() {
        return this.c;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Valid", this.c);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.c = nbttagcompound.getBoolean("Valid");
    }
}
