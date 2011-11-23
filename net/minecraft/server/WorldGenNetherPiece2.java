package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece2 extends WorldGenNetherPiece {

    public WorldGenNetherPiece2(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 1, 3, false);
    }

    public static WorldGenNetherPiece2 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -3, 0, 5, 10, 19, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece2(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 3, 0, 4, 4, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 1, 5, 0, 3, 7, 18, 0, 0, false);
        this.a(world, structureboundingbox, 0, 5, 0, 0, 5, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 4, 5, 0, 4, 5, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 0, 4, 2, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 13, 4, 2, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 0, 15, 4, 1, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

        for (int i = 0; i <= 4; ++i) {
            for (int j = 0; j <= 2; ++j) {
                this.b(world, Block.NETHER_BRICK.id, 0, i, -1, j, structureboundingbox);
                this.b(world, Block.NETHER_BRICK.id, 0, i, -1, 18 - j, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 0, 1, 1, 0, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 0, 3, 4, 0, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 0, 3, 14, 0, 4, 14, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 0, 1, 17, 0, 4, 17, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 4, 1, 1, 4, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 4, 3, 4, 4, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 4, 3, 14, 4, 4, 14, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 4, 1, 17, 4, 4, 17, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        return true;
    }
}
