package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenVillagePiece extends StructurePiece {

    protected WorldGenVillagePiece(int i) {
        super(i);
    }

    protected StructurePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j) {
        switch (this.h) {
        case 0:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.a - 1, this.g.b + i, this.g.c + j, 1, this.c());

        case 1:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.a + j, this.g.b + i, this.g.c - 1, 2, this.c());

        case 2:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.a - 1, this.g.b + i, this.g.c + j, 1, this.c());

        case 3:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.a + j, this.g.b + i, this.g.c - 1, 2, this.c());

        default:
            return null;
        }
    }

    protected StructurePiece b(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j) {
        switch (this.h) {
        case 0:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.d + 1, this.g.b + i, this.g.c + j, 3, this.c());

        case 1:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.a + j, this.g.b + i, this.g.f + 1, 0, this.c());

        case 2:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.d + 1, this.g.b + i, this.g.c + j, 3, this.c());

        case 3:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.g.a + j, this.g.b + i, this.g.f + 1, 0, this.c());

        default:
            return null;
        }
    }

    protected int b(World world, StructureBoundingBox structureboundingbox) {
        int i = 0;
        int j = 0;

        for (int k = this.g.c; k <= this.g.f; ++k) {
            for (int l = this.g.a; l <= this.g.d; ++l) {
                if (structureboundingbox.b(l, 64, k)) {
                    int i1 = world.f(l, k);

                    world.getClass();
                    i += Math.max(i1, 63);
                    ++j;
                }
            }
        }

        if (j == 0) {
            return -1;
        } else {
            return i / j;
        }
    }

    protected static boolean a(StructureBoundingBox structureboundingbox) {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
