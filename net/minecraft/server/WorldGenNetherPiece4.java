package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece4 extends WorldGenNetherPiece {

    private int a;

    public WorldGenNetherPiece4(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
        this.a = random.nextInt();
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenNetherPiece4 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -3, 0, 5, 10, 8, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece4(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        Random random1 = new Random((long) this.a);

        int i;
        int j;
        int k;

        for (i = 0; i <= 4; ++i) {
            for (j = 3; j <= 4; ++j) {
                k = random1.nextInt(8);
                this.a(world, structureboundingbox, i, j, 0, i, j, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
            }
        }

        i = random1.nextInt(8);
        this.a(world, structureboundingbox, 0, 5, 0, 0, 5, i, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        i = random1.nextInt(8);
        this.a(world, structureboundingbox, 4, 5, 0, 4, 5, i, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

        for (i = 0; i <= 4; ++i) {
            j = random1.nextInt(5);
            this.a(world, structureboundingbox, i, 2, 0, i, 2, j, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        }

        for (i = 0; i <= 4; ++i) {
            for (j = 0; j <= 1; ++j) {
                k = random1.nextInt(3);
                this.a(world, structureboundingbox, i, j, 0, i, j, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
            }
        }

        return true;
    }
}
