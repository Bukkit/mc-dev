package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

abstract class WorldGenNetherPiece extends StructurePiece {

    protected static final StructurePieceTreasure[] a = new StructurePieceTreasure[] { new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 5), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.GOLD_SWORD.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.GOLD_CHESTPLATE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.FLINT_AND_STEEL.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.NETHER_STALK.id, 0, 3, 7, 5), new StructurePieceTreasure(Item.SADDLE.id, 0, 1, 1, 10), new StructurePieceTreasure(Item.HORSE_ARMOR_GOLD.id, 0, 1, 1, 8), new StructurePieceTreasure(Item.HORSE_ARMOR_IRON.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.HORSE_ARMOR_DIAMOND.id, 0, 1, 1, 3)};

    protected WorldGenNetherPiece(int i) {
        super(i);
    }

    private int a(List list) {
        boolean flag = false;
        int i = 0;

        WorldGenNetherPieceWeight worldgennetherpieceweight;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); i += worldgennetherpieceweight.b) {
            worldgennetherpieceweight = (WorldGenNetherPieceWeight) iterator.next();
            if (worldgennetherpieceweight.d > 0 && worldgennetherpieceweight.c < worldgennetherpieceweight.d) {
                flag = true;
            }
        }

        return flag ? i : -1;
    }

    private WorldGenNetherPiece a(WorldGenNetherPiece15 worldgennetherpiece15, List list, List list1, Random random, int i, int j, int k, int l, int i1) {
        int j1 = this.a(list);
        boolean flag = j1 > 0 && i1 <= 30;
        int k1 = 0;

        while (k1 < 5 && flag) {
            ++k1;
            int l1 = random.nextInt(j1);
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                WorldGenNetherPieceWeight worldgennetherpieceweight = (WorldGenNetherPieceWeight) iterator.next();

                l1 -= worldgennetherpieceweight.b;
                if (l1 < 0) {
                    if (!worldgennetherpieceweight.a(i1) || worldgennetherpieceweight == worldgennetherpiece15.b && !worldgennetherpieceweight.e) {
                        break;
                    }

                    WorldGenNetherPiece worldgennetherpiece = WorldGenNetherPieces.a(worldgennetherpieceweight, list1, random, i, j, k, l, i1);

                    if (worldgennetherpiece != null) {
                        ++worldgennetherpieceweight.c;
                        worldgennetherpiece15.b = worldgennetherpieceweight;
                        if (!worldgennetherpieceweight.a()) {
                            list.remove(worldgennetherpieceweight);
                        }

                        return worldgennetherpiece;
                    }
                }
            }
        }

        return WorldGenNetherPiece2.a(list1, random, i, j, k, l, i1);
    }

    private StructurePiece a(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, int k, int l, int i1, boolean flag) {
        if (Math.abs(i - worldgennetherpiece15.b().a) <= 112 && Math.abs(k - worldgennetherpiece15.b().c) <= 112) {
            List list1 = worldgennetherpiece15.c;

            if (flag) {
                list1 = worldgennetherpiece15.d;
            }

            WorldGenNetherPiece worldgennetherpiece = this.a(worldgennetherpiece15, list1, list, random, i, j, k, l, i1 + 1);

            if (worldgennetherpiece != null) {
                list.add(worldgennetherpiece);
                worldgennetherpiece15.e.add(worldgennetherpiece);
            }

            return worldgennetherpiece;
        } else {
            return WorldGenNetherPiece2.a(list, random, i, j, k, l, i1);
        }
    }

    protected StructurePiece a(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, boolean flag) {
        switch (this.g) {
        case 0:
            return this.a(worldgennetherpiece15, list, random, this.f.a + i, this.f.b + j, this.f.f + 1, this.g, this.c(), flag);

        case 1:
            return this.a(worldgennetherpiece15, list, random, this.f.a - 1, this.f.b + j, this.f.c + i, this.g, this.c(), flag);

        case 2:
            return this.a(worldgennetherpiece15, list, random, this.f.a + i, this.f.b + j, this.f.c - 1, this.g, this.c(), flag);

        case 3:
            return this.a(worldgennetherpiece15, list, random, this.f.d + 1, this.f.b + j, this.f.c + i, this.g, this.c(), flag);

        default:
            return null;
        }
    }

    protected StructurePiece b(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, boolean flag) {
        switch (this.g) {
        case 0:
            return this.a(worldgennetherpiece15, list, random, this.f.a - 1, this.f.b + i, this.f.c + j, 1, this.c(), flag);

        case 1:
            return this.a(worldgennetherpiece15, list, random, this.f.a + j, this.f.b + i, this.f.c - 1, 2, this.c(), flag);

        case 2:
            return this.a(worldgennetherpiece15, list, random, this.f.a - 1, this.f.b + i, this.f.c + j, 1, this.c(), flag);

        case 3:
            return this.a(worldgennetherpiece15, list, random, this.f.a + j, this.f.b + i, this.f.c - 1, 2, this.c(), flag);

        default:
            return null;
        }
    }

    protected StructurePiece c(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, boolean flag) {
        switch (this.g) {
        case 0:
            return this.a(worldgennetherpiece15, list, random, this.f.d + 1, this.f.b + i, this.f.c + j, 3, this.c(), flag);

        case 1:
            return this.a(worldgennetherpiece15, list, random, this.f.a + j, this.f.b + i, this.f.f + 1, 0, this.c(), flag);

        case 2:
            return this.a(worldgennetherpiece15, list, random, this.f.d + 1, this.f.b + i, this.f.c + j, 3, this.c(), flag);

        case 3:
            return this.a(worldgennetherpiece15, list, random, this.f.a + j, this.f.b + i, this.f.f + 1, 0, this.c(), flag);

        default:
            return null;
        }
    }

    protected static boolean a(StructureBoundingBox structureboundingbox) {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
