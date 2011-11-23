package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class WorldGenVillageStart extends StructureStart {

    private boolean c = false;

    public WorldGenVillageStart(World world, Random random, int i, int j) {
        byte b0 = 0;
        ArrayList arraylist = WorldGenVillagePieces.a(random, b0);
        WorldGenVillageStartPiece worldgenvillagestartpiece = new WorldGenVillageStartPiece(world.getWorldChunkManager(), 0, random, (i << 4) + 2, (j << 4) + 2, arraylist, b0);

        this.a.add(worldgenvillagestartpiece);
        worldgenvillagestartpiece.a(worldgenvillagestartpiece, this.a, random);
        ArrayList arraylist1 = worldgenvillagestartpiece.f;
        ArrayList arraylist2 = worldgenvillagestartpiece.e;

        int k;

        while (!arraylist1.isEmpty() || !arraylist2.isEmpty()) {
            StructurePiece structurepiece;

            if (!arraylist1.isEmpty()) {
                k = random.nextInt(arraylist1.size());
                structurepiece = (StructurePiece) arraylist1.remove(k);
                structurepiece.a((StructurePiece) worldgenvillagestartpiece, (List) this.a, random);
            } else {
                k = random.nextInt(arraylist2.size());
                structurepiece = (StructurePiece) arraylist2.remove(k);
                structurepiece.a((StructurePiece) worldgenvillagestartpiece, (List) this.a, random);
            }
        }

        this.d();
        k = 0;
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece1 = (StructurePiece) iterator.next();

            if (!(structurepiece1 instanceof WorldGenVillageRoadPiece)) {
                ++k;
            }
        }

        this.c = k > 2;
    }

    public boolean a() {
        return this.c;
    }
}
