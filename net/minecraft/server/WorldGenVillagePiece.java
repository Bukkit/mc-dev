package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenVillagePiece extends StructurePiece {

    private int a;

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
                    i += Math.max(world.f(l, k), world.seaLevel);
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

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l) {
        if (this.a < l) {
            for (int i1 = this.a; i1 < l; ++i1) {
                int j1 = this.a(i + i1, k);
                int k1 = this.b(j);
                int l1 = this.b(i + i1, k);

                if (!structureboundingbox.b(j1, k1, l1)) {
                    break;
                }

                ++this.a;
                EntityVillager entityvillager = new EntityVillager(world, this.a(i1));

                entityvillager.setPositionRotation((double) j1 + 0.5D, (double) k1, (double) l1 + 0.5D, 0.0F, 0.0F);
                world.addEntity(entityvillager);
            }
        }
    }

    protected int a(int i) {
        return 0;
    }
}
