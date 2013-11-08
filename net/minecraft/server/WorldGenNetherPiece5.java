package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece5 extends WorldGenNetherPiece {

    public WorldGenNetherPiece5() {}

    public WorldGenNetherPiece5(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        byte b0 = 1;

        if (this.g == 1 || this.g == 2) {
            b0 = 5;
        }

        this.b((WorldGenNetherPiece15) structurepiece, list, random, 0, b0, random.nextInt(8) > 0);
        this.c((WorldGenNetherPiece15) structurepiece, list, random, 0, b0, random.nextInt(8) > 0);
    }

    public static WorldGenNetherPiece5 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -3, 0, 0, 9, 7, 9, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece5(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 0, 8, 5, 8, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 3, 0, 1, 4, 0, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 7, 3, 0, 7, 4, 0, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 1, 4, 2, 2, 4, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 6, 1, 4, 7, 2, 4, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 0, 3, 8, 8, 3, 8, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 0, 3, 6, 0, 3, 7, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 8, 3, 6, 8, 3, 7, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 4, 5, 1, 5, 5, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 7, 4, 5, 7, 5, 5, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);

        for (int i = 0; i <= 5; ++i) {
            for (int j = 0; j <= 8; ++j) {
                this.b(world, Blocks.NETHER_BRICK, 0, j, -1, i, structureboundingbox);
            }
        }

        return true;
    }
}
