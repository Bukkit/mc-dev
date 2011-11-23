package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece11 extends WorldGenNetherPiece {

    public WorldGenNetherPiece11(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 2, 0, false);
        this.b((WorldGenNetherPiece15) structurepiece, list, random, 0, 2, false);
        this.c((WorldGenNetherPiece15) structurepiece, list, random, 0, 2, false);
    }

    public static WorldGenNetherPiece11 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 9, 7, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece11(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 6, 1, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 0, 6, 7, 6, 0, 0, false);
        this.a(world, structureboundingbox, 0, 2, 0, 1, 6, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 6, 1, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 5, 2, 0, 6, 6, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 5, 2, 6, 6, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 6, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 5, 0, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 6, 2, 0, 6, 6, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 6, 2, 5, 6, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 6, 0, 4, 6, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 5, 0, 4, 5, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 2, 6, 6, 4, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 2, 5, 6, 4, 5, 6, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 0, 6, 2, 0, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 5, 2, 0, 5, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 6, 6, 2, 6, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 6, 5, 2, 6, 5, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);

        for (int i = 0; i <= 6; ++i) {
            for (int j = 0; j <= 6; ++j) {
                this.b(world, Block.NETHER_BRICK.id, 0, i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
