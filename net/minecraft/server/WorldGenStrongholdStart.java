package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class WorldGenStrongholdStart extends StructureStart {

    public WorldGenStrongholdStart(World world, Random random, int i, int j) {
        WorldGenStrongholdPieces.a();
        WorldGenStrongholdStairs2 worldgenstrongholdstairs2 = new WorldGenStrongholdStairs2(0, random, (i << 4) + 2, (j << 4) + 2);

        this.a.add(worldgenstrongholdstairs2);
        worldgenstrongholdstairs2.a(worldgenstrongholdstairs2, this.a, random);
        ArrayList arraylist = worldgenstrongholdstairs2.c;

        while (!arraylist.isEmpty()) {
            int k = random.nextInt(arraylist.size());
            StructurePiece structurepiece = (StructurePiece) arraylist.remove(k);

            structurepiece.a((StructurePiece) worldgenstrongholdstairs2, (List) this.a, random);
        }

        this.d();
        this.a(world, random, 10);
    }
}
