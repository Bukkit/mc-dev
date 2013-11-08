package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageWell extends WorldGenVillagePiece {

    public WorldGenVillageWell() {}

    public WorldGenVillageWell(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, int j, int k) {
        super(worldgenvillagestartpiece, i);
        this.g = random.nextInt(4);
        switch (this.g) {
        case 0:
        case 2:
            this.f = new StructureBoundingBox(j, 64, k, j + 6 - 1, 78, k + 6 - 1);
            break;

        default:
            this.f = new StructureBoundingBox(j, 64, k, j + 6 - 1, 78, k + 6 - 1);
        }
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.a - 1, this.f.e - 4, this.f.c + 1, 1, this.d());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.d + 1, this.f.e - 4, this.f.c + 1, 3, this.d());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.a + 1, this.f.e - 4, this.f.c - 1, 2, this.d());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.a + 1, this.f.e - 4, this.f.f + 1, 0, this.d());
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k < 0) {
            this.k = this.b(world, structureboundingbox);
            if (this.k < 0) {
                return true;
            }

            this.f.a(0, this.k - this.f.e + 3, 0);
        }

        this.a(world, structureboundingbox, 1, 0, 1, 4, 12, 4, Blocks.COBBLESTONE, Blocks.WATER, false);
        this.a(world, Blocks.AIR, 0, 2, 12, 2, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 3, 12, 2, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 2, 12, 3, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 3, 12, 3, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 1, 13, 1, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 1, 14, 1, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 4, 13, 1, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 4, 14, 1, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 1, 13, 4, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 1, 14, 4, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 4, 13, 4, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 4, 14, 4, structureboundingbox);
        this.a(world, structureboundingbox, 1, 15, 1, 4, 15, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);

        for (int i = 0; i <= 5; ++i) {
            for (int j = 0; j <= 5; ++j) {
                if (j == 0 || j == 5 || i == 0 || i == 5) {
                    this.a(world, Blocks.GRAVEL, 0, j, 11, i, structureboundingbox);
                    this.b(world, j, 12, i, structureboundingbox);
                }
            }
        }

        return true;
    }
}
