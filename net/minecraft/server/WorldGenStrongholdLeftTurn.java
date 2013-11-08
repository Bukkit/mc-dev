package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdLeftTurn extends WorldGenStrongholdPiece {

    public WorldGenStrongholdLeftTurn() {}

    public WorldGenStrongholdLeftTurn(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.d = this.a(random);
        this.f = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        if (this.g != 2 && this.g != 3) {
            this.c((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        } else {
            this.b((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        }
    }

    public static WorldGenStrongholdLeftTurn a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 5, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdLeftTurn(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 1, 1, 0);
            if (this.g != 2 && this.g != 3) {
                this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, Blocks.AIR, Blocks.AIR, false);
            } else {
                this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Blocks.AIR, Blocks.AIR, false);
            }

            return true;
        }
    }
}
