package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageWell extends WorldGenVillagePiece {

    private final boolean a = true;
    private int b = -1;

    public WorldGenVillageWell(int i, Random random, int j, int k) {
        super(i);
        this.h = random.nextInt(4);
        switch (this.h) {
        case 0:
        case 2:
            this.g = new StructureBoundingBox(j, 64, k, j + 6 - 1, 78, k + 6 - 1);
            break;

        default:
            this.g = new StructureBoundingBox(j, 64, k, j + 6 - 1, 78, k + 6 - 1);
        }
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.a - 1, this.g.e - 4, this.g.c + 1, 1, this.c());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.d + 1, this.g.e - 4, this.g.c + 1, 3, this.c());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.a + 1, this.g.e - 4, this.g.c - 1, 2, this.c());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.a + 1, this.g.e - 4, this.g.f + 1, 0, this.c());
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.b < 0) {
            this.b = this.b(world, structureboundingbox);
            if (this.b < 0) {
                return true;
            }

            this.g.a(0, this.b - this.g.e + 3, 0);
        }

        if (this.a) {
            ;
        }

        this.a(world, structureboundingbox, 1, 0, 1, 4, 12, 4, Block.COBBLESTONE.id, Block.WATER.id, false);
        this.a(world, 0, 0, 2, 12, 2, structureboundingbox);
        this.a(world, 0, 0, 3, 12, 2, structureboundingbox);
        this.a(world, 0, 0, 2, 12, 3, structureboundingbox);
        this.a(world, 0, 0, 3, 12, 3, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 1, 13, 1, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 1, 14, 1, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 4, 13, 1, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 4, 14, 1, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 1, 13, 4, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 1, 14, 4, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 4, 13, 4, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 4, 14, 4, structureboundingbox);
        this.a(world, structureboundingbox, 1, 15, 1, 4, 15, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);

        for (int i = 0; i <= 5; ++i) {
            for (int j = 0; j <= 5; ++j) {
                if (j == 0 || j == 5 || i == 0 || i == 5) {
                    this.a(world, Block.GRAVEL.id, 0, j, 11, i, structureboundingbox);
                    this.b(world, j, 12, i, structureboundingbox);
                }
            }
        }

        return true;
    }
}
