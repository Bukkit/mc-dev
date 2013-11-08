package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdStairsStraight extends WorldGenStrongholdPiece {

    public WorldGenStrongholdStairsStraight() {}

    public WorldGenStrongholdStairsStraight(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.d = this.a(random);
        this.f = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdStairsStraight a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 11, 8, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdStairsStraight(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 10, 7, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 1, 7, 0);
            this.a(world, random, structureboundingbox, WorldGenStrongholdDoorType.a, 1, 1, 7);
            int i = this.a(Blocks.COBBLESTONE_STAIRS, 2);

            for (int j = 0; j < 6; ++j) {
                this.a(world, Blocks.COBBLESTONE_STAIRS, i, 1, 6 - j, 1 + j, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE_STAIRS, i, 2, 6 - j, 1 + j, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE_STAIRS, i, 3, 6 - j, 1 + j, structureboundingbox);
                if (j < 5) {
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 1, 5 - j, 1 + j, structureboundingbox);
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 2, 5 - j, 1 + j, structureboundingbox);
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 3, 5 - j, 1 + j, structureboundingbox);
                }
            }

            return true;
        }
    }
}
