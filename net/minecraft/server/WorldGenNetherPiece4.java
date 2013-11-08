package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece4 extends WorldGenNetherPiece {

    public WorldGenNetherPiece4() {}

    public WorldGenNetherPiece4(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 1, 0, true);
    }

    public static WorldGenNetherPiece4 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 14, 10, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece4(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        int i = this.a(Blocks.NETHER_BRICK_STAIRS, 2);

        for (int j = 0; j <= 9; ++j) {
            int k = Math.max(1, 7 - j);
            int l = Math.min(Math.max(k + 5, 14 - j), 13);
            int i1 = j;

            this.a(world, structureboundingbox, 0, 0, j, 4, k, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
            this.a(world, structureboundingbox, 1, k + 1, j, 3, l - 1, j, Blocks.AIR, Blocks.AIR, false);
            if (j <= 6) {
                this.a(world, Blocks.NETHER_BRICK_STAIRS, i, 1, k + 1, j, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_STAIRS, i, 2, k + 1, j, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_STAIRS, i, 3, k + 1, j, structureboundingbox);
            }

            this.a(world, structureboundingbox, 0, l, j, 4, l, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
            this.a(world, structureboundingbox, 0, k + 1, j, 0, l - 1, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
            this.a(world, structureboundingbox, 4, k + 1, j, 4, l - 1, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
            if ((j & 1) == 0) {
                this.a(world, structureboundingbox, 0, k + 2, j, 0, k + 3, j, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
                this.a(world, structureboundingbox, 4, k + 2, j, 4, k + 3, j, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
            }

            for (int j1 = 0; j1 <= 4; ++j1) {
                this.b(world, Blocks.NETHER_BRICK, 0, j1, -1, i1, structureboundingbox);
            }
        }

        return true;
    }
}
