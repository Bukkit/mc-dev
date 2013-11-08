package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece16 extends WorldGenNetherPiece {

    public WorldGenNetherPiece16() {}

    public WorldGenNetherPiece16(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.c((WorldGenNetherPiece15) structurepiece, list, random, 6, 2, false);
    }

    public static WorldGenNetherPiece16 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 11, 7, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece16(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 0, 6, 10, 6, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 3, 2, 0, 5, 4, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 6, 3, 2, 6, 5, 2, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 6, 3, 4, 6, 5, 4, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, Blocks.NETHER_BRICK, 0, 5, 2, 5, structureboundingbox);
        this.a(world, structureboundingbox, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 8, 2, 6, 8, 4, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 2, 5, 0, 4, 5, 0, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);

        for (int i = 0; i <= 6; ++i) {
            for (int j = 0; j <= 6; ++j) {
                this.b(world, Blocks.NETHER_BRICK, 0, i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
