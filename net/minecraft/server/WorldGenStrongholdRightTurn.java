package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdRightTurn extends WorldGenStrongholdLeftTurn {

    public WorldGenStrongholdRightTurn(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i, random, structureboundingbox, j);
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        if (this.h != 2 && this.h != 3) {
            this.b((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 1);
        } else {
            this.c((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 1);
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random, WorldGenStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.a, 1, 1, 0);
            if (this.h != 2 && this.h != 3) {
                this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, 0, 0, false);
            } else {
                this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, 0, 0, false);
            }

            return true;
        }
    }
}
