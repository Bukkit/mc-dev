package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenVillagePieces {

    public WorldGenVillagePieces() {}

    public static ArrayList a(Random random, int i) {
        ArrayList arraylist = new ArrayList();

        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse.class, 4, MathHelper.a(random, 2 + i, 4 + i * 2)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageTemple.class, 20, MathHelper.a(random, 0 + i, 1 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageLibrary.class, 20, MathHelper.a(random, 0 + i, 2 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageHut.class, 3, MathHelper.a(random, 2 + i, 5 + i * 3)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageButcher.class, 15, MathHelper.a(random, 0 + i, 2 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageBigFarm.class, 3, MathHelper.a(random, 1 + i, 4 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageFarm.class, 3, MathHelper.a(random, 2 + i, 4 + i * 2)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageBlacksmith.class, 15, MathHelper.a(random, 0, 1 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse2.class, 8, MathHelper.a(random, 0 + i, 3 + i * 2)));
        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            if (((WorldGenVillagePieceWeight) iterator.next()).d == 0) {
                iterator.remove();
            }
        }

        return arraylist;
    }

    private static int a(ArrayList arraylist) {
        boolean flag = false;
        int i = 0;

        WorldGenVillagePieceWeight worldgenvillagepieceweight;

        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); i += worldgenvillagepieceweight.b) {
            worldgenvillagepieceweight = (WorldGenVillagePieceWeight) iterator.next();
            if (worldgenvillagepieceweight.d > 0 && worldgenvillagepieceweight.c < worldgenvillagepieceweight.d) {
                flag = true;
            }
        }

        return flag ? i : -1;
    }

    private static WorldGenVillagePiece a(WorldGenVillagePieceWeight worldgenvillagepieceweight, List list, Random random, int i, int j, int k, int l, int i1) {
        Class oclass = worldgenvillagepieceweight.a;
        Object object = null;

        if (oclass == WorldGenVillageHouse.class) {
            object = WorldGenVillageHouse.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageTemple.class) {
            object = WorldGenVillageTemple.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageLibrary.class) {
            object = WorldGenVillageLibrary.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageHut.class) {
            object = WorldGenVillageHut.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageButcher.class) {
            object = WorldGenVillageButcher.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageBigFarm.class) {
            object = WorldGenVillageBigFarm.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageFarm.class) {
            object = WorldGenVillageFarm.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageBlacksmith.class) {
            object = WorldGenVillageBlacksmith.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenVillageHouse2.class) {
            object = WorldGenVillageHouse2.a(list, random, i, j, k, l, i1);
        }

        return (WorldGenVillagePiece) object;
    }

    private static WorldGenVillagePiece c(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        int j1 = a(worldgenvillagestartpiece.d);

        if (j1 <= 0) {
            return null;
        } else {
            int k1 = 0;

            while (k1 < 5) {
                ++k1;
                int l1 = random.nextInt(j1);
                Iterator iterator = worldgenvillagestartpiece.d.iterator();

                while (iterator.hasNext()) {
                    WorldGenVillagePieceWeight worldgenvillagepieceweight = (WorldGenVillagePieceWeight) iterator.next();

                    l1 -= worldgenvillagepieceweight.b;
                    if (l1 < 0) {
                        if (!worldgenvillagepieceweight.a(i1) || worldgenvillagepieceweight == worldgenvillagestartpiece.c && worldgenvillagestartpiece.d.size() > 1) {
                            break;
                        }

                        WorldGenVillagePiece worldgenvillagepiece = a(worldgenvillagepieceweight, list, random, i, j, k, l, i1);

                        if (worldgenvillagepiece != null) {
                            ++worldgenvillagepieceweight.c;
                            worldgenvillagestartpiece.c = worldgenvillagepieceweight;
                            if (!worldgenvillagepieceweight.a()) {
                                worldgenvillagestartpiece.d.remove(worldgenvillagepieceweight);
                            }

                            return worldgenvillagepiece;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = WorldGenVillageLight.a(list, random, i, j, k, l);

            if (structureboundingbox != null) {
                return new WorldGenVillageLight(i1, random, structureboundingbox, l);
            } else {
                return null;
            }
        }
    }

    private static StructurePiece d(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        if (i1 > 50) {
            return null;
        } else if (Math.abs(i - worldgenvillagestartpiece.b().a) <= 112 && Math.abs(k - worldgenvillagestartpiece.b().c) <= 112) {
            WorldGenVillagePiece worldgenvillagepiece = c(worldgenvillagestartpiece, list, random, i, j, k, l, i1 + 1);

            if (worldgenvillagepiece != null) {
                int j1 = (worldgenvillagepiece.g.a + worldgenvillagepiece.g.d) / 2;
                int k1 = (worldgenvillagepiece.g.c + worldgenvillagepiece.g.f) / 2;
                int l1 = worldgenvillagepiece.g.d - worldgenvillagepiece.g.a;
                int i2 = worldgenvillagepiece.g.f - worldgenvillagepiece.g.c;
                int j2 = l1 > i2 ? l1 : i2;

                if (worldgenvillagestartpiece.a().a(j1, k1, j2 / 2 + 4, WorldGenVillage.a)) {
                    list.add(worldgenvillagepiece);
                    worldgenvillagestartpiece.e.add(worldgenvillagepiece);
                    return worldgenvillagepiece;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private static StructurePiece e(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        if (i1 > 3 + worldgenvillagestartpiece.b) {
            return null;
        } else if (Math.abs(i - worldgenvillagestartpiece.b().a) <= 112 && Math.abs(k - worldgenvillagestartpiece.b().c) <= 112) {
            StructureBoundingBox structureboundingbox = WorldGenVillageRoad.a(worldgenvillagestartpiece, list, random, i, j, k, l);

            if (structureboundingbox != null && structureboundingbox.b > 10) {
                WorldGenVillageRoad worldgenvillageroad = new WorldGenVillageRoad(i1, random, structureboundingbox, l);
                int j1 = (worldgenvillageroad.g.a + worldgenvillageroad.g.d) / 2;
                int k1 = (worldgenvillageroad.g.c + worldgenvillageroad.g.f) / 2;
                int l1 = worldgenvillageroad.g.d - worldgenvillageroad.g.a;
                int i2 = worldgenvillageroad.g.f - worldgenvillageroad.g.c;
                int j2 = l1 > i2 ? l1 : i2;

                if (worldgenvillagestartpiece.a().a(j1, k1, j2 / 2 + 4, WorldGenVillage.a)) {
                    list.add(worldgenvillageroad);
                    worldgenvillagestartpiece.f.add(worldgenvillageroad);
                    return worldgenvillageroad;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    static StructurePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        return d(worldgenvillagestartpiece, list, random, i, j, k, l, i1);
    }

    static StructurePiece b(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        return e(worldgenvillagestartpiece, list, random, i, j, k, l, i1);
    }
}
