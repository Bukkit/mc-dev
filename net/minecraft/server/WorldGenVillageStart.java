package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class WorldGenVillageStart extends StructureStart {

    private boolean c = false;

    public WorldGenVillageStart(World world, Random random, int i, int j, int k) {
        ArrayList arraylist = WorldGenVillagePieces.a(random, k);
        WorldGenVillageStartPiece worldgenvillagestartpiece = new WorldGenVillageStartPiece(world.getWorldChunkManager(), 0, random, (i << 4) + 2, (j << 4) + 2, arraylist, k);

        this.a.add(worldgenvillagestartpiece);
        worldgenvillagestartpiece.a(worldgenvillagestartpiece, this.a, random);
        ArrayList arraylist1 = worldgenvillagestartpiece.j;
        ArrayList arraylist2 = worldgenvillagestartpiece.i;

        int l;

        while (!arraylist1.isEmpty() || !arraylist2.isEmpty()) {
            StructurePiece structurepiece;

            if (arraylist1.isEmpty()) {
                l = random.nextInt(arraylist2.size());
                structurepiece = (StructurePiece) arraylist2.remove(l);
                structurepiece.a((StructurePiece) worldgenvillagestartpiece, (List) this.a, random);
            } else {
                l = random.nextInt(arraylist1.size());
                structurepiece = (StructurePiece) arraylist1.remove(l);
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
}
