package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenVillagePiece extends StructurePiece {

    private int a;
    protected WorldGenVillageStartPiece k;

    protected WorldGenVillagePiece(WorldGenVillageStartPiece worldgenvillagestartpiece, int i) {
        super(i);
        this.k = worldgenvillagestartpiece;
    }

    protected StructurePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j) {
        switch (this.g) {
        case 0:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.a - 1, this.f.b + i, this.f.c + j, 1, this.c());

        case 1:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.a + j, this.f.b + i, this.f.c - 1, 2, this.c());

        case 2:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.a - 1, this.f.b + i, this.f.c + j, 1, this.c());

        case 3:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.a + j, this.f.b + i, this.f.c - 1, 2, this.c());

        default:
            return null;
        }
    }

    protected StructurePiece b(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j) {
        switch (this.g) {
        case 0:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.d + 1, this.f.b + i, this.f.c + j, 3, this.c());

        case 1:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.a + j, this.f.b + i, this.f.f + 1, 0, this.c());

        case 2:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.d + 1, this.f.b + i, this.f.c + j, 3, this.c());

        case 3:
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.f.a + j, this.f.b + i, this.f.f + 1, 0, this.c());

        default:
            return null;
        }
    }

    protected int b(World world, StructureBoundingBox structureboundingbox) {
        int i = 0;
        int j = 0;

        for (int k = this.f.c; k <= this.f.f; ++k) {
            for (int l = this.f.a; l <= this.f.d; ++l) {
                if (structureboundingbox.b(l, 64, k)) {
                    i += Math.max(world.i(l, k), world.worldProvider.getSeaLevel());
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
                int k1 = this.a(j);
                int l1 = this.b(i + i1, k);

                if (!structureboundingbox.b(j1, k1, l1)) {
                    break;
                }

                ++this.a;
                EntityVillager entityvillager = new EntityVillager(world, this.b(i1));

                entityvillager.setPositionRotation((double) j1 + 0.5D, (double) k1, (double) l1 + 0.5D, 0.0F, 0.0F);
                world.addEntity(entityvillager);
            }
        }
    }

    protected int b(int i) {
        return 0;
    }

    protected int d(int i, int j) {
        if (this.k.b) {
            if (i == Block.LOG.id) {
                return Block.SANDSTONE.id;
            }

            if (i == Block.COBBLESTONE.id) {
                return Block.SANDSTONE.id;
            }

            if (i == Block.WOOD.id) {
                return Block.SANDSTONE.id;
            }

            if (i == Block.WOOD_STAIRS.id) {
                return Block.SANDSTONE_STAIRS.id;
            }

            if (i == Block.COBBLESTONE_STAIRS.id) {
                return Block.SANDSTONE_STAIRS.id;
            }

            if (i == Block.GRAVEL.id) {
                return Block.SANDSTONE.id;
            }
        }

        return i;
    }

    protected int e(int i, int j) {
        if (this.k.b) {
            if (i == Block.LOG.id) {
                return 0;
            }

            if (i == Block.COBBLESTONE.id) {
                return 0;
            }

            if (i == Block.WOOD.id) {
                return 2;
            }
        }

        return j;
    }

    protected void a(World world, int i, int j, int k, int l, int i1, StructureBoundingBox structureboundingbox) {
        int j1 = this.d(i, j);
        int k1 = this.e(i, j);

        super.a(world, j1, k1, k, l, i1, structureboundingbox);
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, int k1, int l1, boolean flag) {
        int i2 = this.d(k1, 0);
        int j2 = this.e(k1, 0);
        int k2 = this.d(l1, 0);
        int l2 = this.e(l1, 0);

        super.a(world, structureboundingbox, i, j, k, l, i1, j1, i2, j2, k2, l2, flag);
    }

    protected void b(World world, int i, int j, int k, int l, int i1, StructureBoundingBox structureboundingbox) {
        int j1 = this.d(i, j);
        int k1 = this.e(i, j);

        super.b(world, j1, k1, k, l, i1, structureboundingbox);
    }
}
