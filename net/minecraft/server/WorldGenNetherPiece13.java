package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece13 extends WorldGenNetherPiece {

    public WorldGenNetherPiece13(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 1, 0, true);
        this.b((WorldGenNetherPiece15) structurepiece, list, random, 0, 1, true);
        this.c((WorldGenNetherPiece15) structurepiece, list, random, 0, 1, true);
    }

    public static WorldGenNetherPiece13 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece13(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 0, 4, 5, 4, 0, 0, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 4, 2, 0, 4, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 2, 4, 0, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 4, 2, 4, 4, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 6, 0, 4, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

        for (int i = 0; i <= 4; ++i) {
            for (int j = 0; j <= 4; ++j) {
                this.b(world, Block.NETHER_BRICK.id, 0, i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
