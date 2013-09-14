package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPieces {

    private static final WorldGenStrongholdPieceWeight[] b = new WorldGenStrongholdPieceWeight[] { new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairs.class, 40, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdPrison.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdLeftTurn.class, 20, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdRightTurn.class, 20, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdRoomCrossing.class, 10, 6), new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairsStraight.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairs2.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdCrossing.class, 5, 4), new WorldGenStrongholdPieceWeight(WorldGenStrongholdChestCorridor.class, 5, 4), new WorldGenStrongholdUnknown(WorldGenStrongholdLibrary.class, 10, 2), new WorldGenStrongholdPiece2(WorldGenStrongholdPortalRoom.class, 20, 1)};
    private static List c;
    private static Class d;
    static int a;
    private static final WorldGenStrongholdStones e = new WorldGenStrongholdStones((WorldGenStrongholdUnknown) null);

    public static void a() {
        WorldGenFactory.a(WorldGenStrongholdChestCorridor.class, "SHCC");
        WorldGenFactory.a(WorldGenStrongholdCorridor.class, "SHFC");
        WorldGenFactory.a(WorldGenStrongholdCrossing.class, "SH5C");
        WorldGenFactory.a(WorldGenStrongholdLeftTurn.class, "SHLT");
        WorldGenFactory.a(WorldGenStrongholdLibrary.class, "SHLi");
        WorldGenFactory.a(WorldGenStrongholdPortalRoom.class, "SHPR");
        WorldGenFactory.a(WorldGenStrongholdPrison.class, "SHPH");
        WorldGenFactory.a(WorldGenStrongholdRightTurn.class, "SHRT");
        WorldGenFactory.a(WorldGenStrongholdRoomCrossing.class, "SHRC");
        WorldGenFactory.a(WorldGenStrongholdStairs2.class, "SHSD");
        WorldGenFactory.a(WorldGenStrongholdStart.class, "SHStart");
        WorldGenFactory.a(WorldGenStrongholdStairs.class, "SHS");
        WorldGenFactory.a(WorldGenStrongholdStairsStraight.class, "SHSSD");
    }

    public static void b() {
        c = new ArrayList();
        WorldGenStrongholdPieceWeight[] aworldgenstrongholdpieceweight = b;
        int i = aworldgenstrongholdpieceweight.length;

        for (int j = 0; j < i; ++j) {
            WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight = aworldgenstrongholdpieceweight[j];

            worldgenstrongholdpieceweight.c = 0;
            c.add(worldgenstrongholdpieceweight);
        }

        d = null;
    }

    private static boolean d() {
        boolean flag = false;

        a = 0;

        WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight;

        for (Iterator iterator = c.iterator(); iterator.hasNext(); a += worldgenstrongholdpieceweight.b) {
            worldgenstrongholdpieceweight = (WorldGenStrongholdPieceWeight) iterator.next();
            if (worldgenstrongholdpieceweight.d > 0 && worldgenstrongholdpieceweight.c < worldgenstrongholdpieceweight.d) {
                flag = true;
            }
        }

        return flag;
    }

    private static WorldGenStrongholdPiece a(Class oclass, List list, Random random, int i, int j, int k, int l, int i1) {
        Object object = null;

        if (oclass == WorldGenStrongholdStairs.class) {
            object = WorldGenStrongholdStairs.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdPrison.class) {
            object = WorldGenStrongholdPrison.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdLeftTurn.class) {
            object = WorldGenStrongholdLeftTurn.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdRightTurn.class) {
            object = WorldGenStrongholdRightTurn.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdRoomCrossing.class) {
            object = WorldGenStrongholdRoomCrossing.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdStairsStraight.class) {
            object = WorldGenStrongholdStairsStraight.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdStairs2.class) {
            object = WorldGenStrongholdStairs2.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdCrossing.class) {
            object = WorldGenStrongholdCrossing.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdChestCorridor.class) {
            object = WorldGenStrongholdChestCorridor.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdLibrary.class) {
            object = WorldGenStrongholdLibrary.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenStrongholdPortalRoom.class) {
            object = WorldGenStrongholdPortalRoom.a(list, random, i, j, k, l, i1);
        }

        return (WorldGenStrongholdPiece) object;
    }

    private static WorldGenStrongholdPiece b(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j, int k, int l, int i1) {
        if (!d()) {
            return null;
        } else {
            if (d != null) {
                WorldGenStrongholdPiece worldgenstrongholdpiece = a(d, list, random, i, j, k, l, i1);

                d = null;
                if (worldgenstrongholdpiece != null) {
                    return worldgenstrongholdpiece;
                }
            }

            int j1 = 0;

            while (j1 < 5) {
                ++j1;
                int k1 = random.nextInt(a);
                Iterator iterator = c.iterator();

                while (iterator.hasNext()) {
                    WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight = (WorldGenStrongholdPieceWeight) iterator.next();

                    k1 -= worldgenstrongholdpieceweight.b;
                    if (k1 < 0) {
                        if (!worldgenstrongholdpieceweight.a(i1) || worldgenstrongholdpieceweight == worldgenstrongholdstart.a) {
                            break;
                        }

                        WorldGenStrongholdPiece worldgenstrongholdpiece1 = a(worldgenstrongholdpieceweight.a, list, random, i, j, k, l, i1);

                        if (worldgenstrongholdpiece1 != null) {
                            ++worldgenstrongholdpieceweight.c;
                            worldgenstrongholdstart.a = worldgenstrongholdpieceweight;
                            if (!worldgenstrongholdpieceweight.a()) {
                                c.remove(worldgenstrongholdpieceweight);
                            }

                            return worldgenstrongholdpiece1;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = WorldGenStrongholdCorridor.a(list, random, i, j, k, l);

            if (structureboundingbox != null && structureboundingbox.b > 1) {
                return new WorldGenStrongholdCorridor(i1, random, structureboundingbox, l);
            } else {
                return null;
            }
        }
    }

    private static StructurePiece c(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j, int k, int l, int i1) {
        if (i1 > 50) {
            return null;
        } else if (Math.abs(i - worldgenstrongholdstart.c().a) <= 112 && Math.abs(k - worldgenstrongholdstart.c().c) <= 112) {
            WorldGenStrongholdPiece worldgenstrongholdpiece = b(worldgenstrongholdstart, list, random, i, j, k, l, i1 + 1);

            if (worldgenstrongholdpiece != null) {
                list.add(worldgenstrongholdpiece);
                worldgenstrongholdstart.c.add(worldgenstrongholdpiece);
            }

            return worldgenstrongholdpiece;
        } else {
            return null;
        }
    }

    static StructurePiece a(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j, int k, int l, int i1) {
        return c(worldgenstrongholdstart, list, random, i, j, k, l, i1);
    }

    static Class a(Class oclass) {
        d = oclass;
        return oclass;
    }

    static WorldGenStrongholdStones c() {
        return e;
    }
}
