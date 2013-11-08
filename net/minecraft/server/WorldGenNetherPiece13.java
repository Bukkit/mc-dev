package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece13 extends WorldGenNetherPiece {

    public WorldGenNetherPiece13() {}

    public WorldGenNetherPiece13(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 2, 0, false);
        this.b((WorldGenNetherPiece15) structurepiece, list, random, 0, 2, false);
        this.c((WorldGenNetherPiece15) structurepiece, list, random, 0, 2, false);
    }

    public static WorldGenNetherPiece13 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 9, 7, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece13(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 0, 6, 7, 6, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 2, 5, 0, 4, 5, 0, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 2, 5, 6, 4, 5, 6, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 5, 2, 0, 5, 4, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 5, 2, 6, 5, 4, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);

        for (int i = 0; i <= 6; ++i) {
            for (int j = 0; j <= 6; ++j) {
                this.b(world, Blocks.NETHER_BRICK, 0, i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
