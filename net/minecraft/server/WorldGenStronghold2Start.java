package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStronghold2Start extends StructureStart {

    public WorldGenStronghold2Start() {}

    public WorldGenStronghold2Start(World world, Random random, int i, int j) {
        super(i, j);
        WorldGenStrongholdPieces.b();
        WorldGenStrongholdStart worldgenstrongholdstart = new WorldGenStrongholdStart(0, random, (i << 4) + 2, (j << 4) + 2);

        this.a.add(worldgenstrongholdstart);
        worldgenstrongholdstart.a(worldgenstrongholdstart, this.a, random);
        List list = worldgenstrongholdstart.c;

        while (!list.isEmpty()) {
            int k = random.nextInt(list.size());
            StructurePiece structurepiece = (StructurePiece) list.remove(k);

            structurepiece.a((StructurePiece) worldgenstrongholdstart, (List) this.a, random);
        }

        this.c();
        this.a(world, random, 10);
    }
}
