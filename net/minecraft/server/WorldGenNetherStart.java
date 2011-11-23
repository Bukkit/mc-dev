package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class WorldGenNetherStart extends StructureStart {

    public WorldGenNetherStart(World world, Random random, int i, int j) {
        WorldGenNetherPiece15 worldgennetherpiece15 = new WorldGenNetherPiece15(random, (i << 4) + 2, (j << 4) + 2);

        this.a.add(worldgennetherpiece15);
        worldgennetherpiece15.a(worldgennetherpiece15, this.a, random);
        ArrayList arraylist = worldgennetherpiece15.d;

        while (!arraylist.isEmpty()) {
            int k = random.nextInt(arraylist.size());
            StructurePiece structurepiece = (StructurePiece) arraylist.remove(k);

            structurepiece.a((StructurePiece) worldgennetherpiece15, (List) this.a, random);
        }

        this.d();
        this.a(world, random, 48, 70);
    }
}
