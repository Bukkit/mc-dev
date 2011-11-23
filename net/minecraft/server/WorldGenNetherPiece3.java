package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece3 extends WorldGenNetherPiece {

    public WorldGenNetherPiece3(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 5, 3, true);
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 5, 11, true);
    }

    public static WorldGenNetherPiece3 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -5, -3, 0, 13, 14, 13, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece3(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 3, 0, 12, 4, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 5, 0, 12, 13, 12, 0, 0, false);
        this.a(world, structureboundingbox, 0, 5, 0, 1, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 11, 5, 0, 12, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 5, 11, 4, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 8, 5, 11, 10, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 5, 9, 11, 7, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 5, 0, 4, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 8, 5, 0, 10, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 5, 9, 0, 7, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 11, 2, 10, 12, 10, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

        int i;

        for (i = 1; i <= 11; i += 2) {
            this.a(world, structureboundingbox, i, 10, 0, i, 11, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
            this.a(world, structureboundingbox, i, 10, 12, i, 11, 12, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
            this.a(world, structureboundingbox, 0, 10, i, 0, 11, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
            this.a(world, structureboundingbox, 12, 10, i, 12, 11, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
            this.a(world, Block.NETHER_BRICK.id, 0, i, 13, 0, structureboundingbox);
            this.a(world, Block.NETHER_BRICK.id, 0, i, 13, 12, structureboundingbox);
            this.a(world, Block.NETHER_BRICK.id, 0, 0, 13, i, structureboundingbox);
            this.a(world, Block.NETHER_BRICK.id, 0, 12, 13, i, structureboundingbox);
            this.a(world, Block.NETHER_FENCE.id, 0, i + 1, 13, 0, structureboundingbox);
            this.a(world, Block.NETHER_FENCE.id, 0, i + 1, 13, 12, structureboundingbox);
            this.a(world, Block.NETHER_FENCE.id, 0, 0, 13, i + 1, structureboundingbox);
            this.a(world, Block.NETHER_FENCE.id, 0, 12, 13, i + 1, structureboundingbox);
        }

        this.a(world, Block.NETHER_FENCE.id, 0, 0, 13, 0, structureboundingbox);
        this.a(world, Block.NETHER_FENCE.id, 0, 0, 13, 12, structureboundingbox);
        this.a(world, Block.NETHER_FENCE.id, 0, 0, 13, 0, structureboundingbox);
        this.a(world, Block.NETHER_FENCE.id, 0, 12, 13, 0, structureboundingbox);

        for (i = 3; i <= 9; i += 2) {
            this.a(world, structureboundingbox, 1, 7, i, 1, 8, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
            this.a(world, structureboundingbox, 11, 7, i, 11, 8, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        }

        i = this.c(Block.NETHER_BRICK_STAIRS.id, 3);

        int j;
        int k;
        int l;

        for (j = 0; j <= 6; ++j) {
            k = j + 4;

            for (l = 5; l <= 7; ++l) {
                this.a(world, Block.NETHER_BRICK_STAIRS.id, i, l, 5 + j, k, structureboundingbox);
            }

            if (k >= 5 && k <= 8) {
                this.a(world, structureboundingbox, 5, 5, k, 7, j + 4, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
            } else if (k >= 9 && k <= 10) {
                this.a(world, structureboundingbox, 5, 8, k, 7, j + 4, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
            }

            if (j >= 1) {
                this.a(world, structureboundingbox, 5, 6 + j, k, 7, 9 + j, k, 0, 0, false);
            }
        }

        for (j = 5; j <= 7; ++j) {
            this.a(world, Block.NETHER_BRICK_STAIRS.id, i, j, 12, 11, structureboundingbox);
        }

        this.a(world, structureboundingbox, 5, 6, 7, 5, 7, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 7, 6, 7, 7, 7, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 5, 13, 12, 7, 13, 12, 0, 0, false);
        this.a(world, structureboundingbox, 2, 5, 2, 3, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 5, 9, 3, 5, 10, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 5, 4, 2, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 9, 5, 2, 10, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 9, 5, 9, 10, 5, 10, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 10, 5, 4, 10, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        j = this.c(Block.NETHER_BRICK_STAIRS.id, 0);
        k = this.c(Block.NETHER_BRICK_STAIRS.id, 1);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 2, structureboundingbox);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 3, structureboundingbox);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 9, structureboundingbox);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 10, structureboundingbox);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 2, structureboundingbox);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 3, structureboundingbox);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 9, structureboundingbox);
        this.a(world, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 10, structureboundingbox);
        this.a(world, structureboundingbox, 3, 4, 4, 4, 4, 8, Block.SOUL_SAND.id, Block.SOUL_SAND.id, false);
        this.a(world, structureboundingbox, 8, 4, 4, 9, 4, 8, Block.SOUL_SAND.id, Block.SOUL_SAND.id, false);
        this.a(world, structureboundingbox, 3, 5, 4, 4, 5, 8, Block.NETHER_WART.id, Block.NETHER_WART.id, false);
        this.a(world, structureboundingbox, 8, 5, 4, 9, 5, 8, Block.NETHER_WART.id, Block.NETHER_WART.id, false);
        this.a(world, structureboundingbox, 4, 2, 0, 8, 2, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 4, 12, 2, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 4, 0, 0, 8, 1, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 4, 0, 9, 8, 1, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 0, 4, 3, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 9, 0, 4, 12, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

        int i1;

        for (l = 4; l <= 8; ++l) {
            for (i1 = 0; i1 <= 2; ++i1) {
                this.b(world, Block.NETHER_BRICK.id, 0, l, -1, i1, structureboundingbox);
                this.b(world, Block.NETHER_BRICK.id, 0, l, -1, 12 - i1, structureboundingbox);
            }
        }

        for (l = 0; l <= 2; ++l) {
            for (i1 = 4; i1 <= 8; ++i1) {
                this.b(world, Block.NETHER_BRICK.id, 0, l, -1, i1, structureboundingbox);
                this.b(world, Block.NETHER_BRICK.id, 0, 12 - l, -1, i1, structureboundingbox);
            }
        }

        return true;
    }
}
