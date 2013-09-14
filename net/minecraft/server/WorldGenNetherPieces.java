package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPieces {

    private static final WorldGenNetherPieceWeight[] a = new WorldGenNetherPieceWeight[] { new WorldGenNetherPieceWeight(WorldGenNetherPiece3.class, 30, 0, true), new WorldGenNetherPieceWeight(WorldGenNetherPiece1.class, 10, 4), new WorldGenNetherPieceWeight(WorldGenNetherPiece13.class, 10, 4), new WorldGenNetherPieceWeight(WorldGenNetherPiece16.class, 10, 3), new WorldGenNetherPieceWeight(WorldGenNetherPiece12.class, 5, 2), new WorldGenNetherPieceWeight(WorldGenNetherPiece6.class, 5, 1)};
    private static final WorldGenNetherPieceWeight[] b = new WorldGenNetherPieceWeight[] { new WorldGenNetherPieceWeight(WorldGenNetherPiece9.class, 25, 0, true), new WorldGenNetherPieceWeight(WorldGenNetherPiece7.class, 15, 5), new WorldGenNetherPieceWeight(WorldGenNetherPiece10.class, 5, 10), new WorldGenNetherPieceWeight(WorldGenNetherPiece8.class, 5, 10), new WorldGenNetherPieceWeight(WorldGenNetherPiece4.class, 10, 3, true), new WorldGenNetherPieceWeight(WorldGenNetherPiece5.class, 7, 2), new WorldGenNetherPieceWeight(WorldGenNetherPiece11.class, 5, 2)};

    public static void a() {
        WorldGenFactory.a(WorldGenNetherPiece1.class, "NeBCr");
        WorldGenFactory.a(WorldGenNetherPiece2.class, "NeBEF");
        WorldGenFactory.a(WorldGenNetherPiece3.class, "NeBS");
        WorldGenFactory.a(WorldGenNetherPiece4.class, "NeCCS");
        WorldGenFactory.a(WorldGenNetherPiece5.class, "NeCTB");
        WorldGenFactory.a(WorldGenNetherPiece6.class, "NeCE");
        WorldGenFactory.a(WorldGenNetherPiece7.class, "NeSCSC");
        WorldGenFactory.a(WorldGenNetherPiece8.class, "NeSCLT");
        WorldGenFactory.a(WorldGenNetherPiece9.class, "NeSC");
        WorldGenFactory.a(WorldGenNetherPiece10.class, "NeSCRT");
        WorldGenFactory.a(WorldGenNetherPiece11.class, "NeCSR");
        WorldGenFactory.a(WorldGenNetherPiece12.class, "NeMT");
        WorldGenFactory.a(WorldGenNetherPiece13.class, "NeRC");
        WorldGenFactory.a(WorldGenNetherPiece16.class, "NeSR");
        WorldGenFactory.a(WorldGenNetherPiece15.class, "NeStart");
    }

    private static WorldGenNetherPiece b(WorldGenNetherPieceWeight worldgennetherpieceweight, List list, Random random, int i, int j, int k, int l, int i1) {
        Class oclass = worldgennetherpieceweight.a;
        Object object = null;

        if (oclass == WorldGenNetherPiece3.class) {
            object = WorldGenNetherPiece3.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece1.class) {
            object = WorldGenNetherPiece1.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece13.class) {
            object = WorldGenNetherPiece13.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece16.class) {
            object = WorldGenNetherPiece16.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece12.class) {
            object = WorldGenNetherPiece12.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece6.class) {
            object = WorldGenNetherPiece6.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece9.class) {
            object = WorldGenNetherPiece9.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece10.class) {
            object = WorldGenNetherPiece10.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece8.class) {
            object = WorldGenNetherPiece8.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece4.class) {
            object = WorldGenNetherPiece4.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece5.class) {
            object = WorldGenNetherPiece5.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece7.class) {
            object = WorldGenNetherPiece7.a(list, random, i, j, k, l, i1);
        } else if (oclass == WorldGenNetherPiece11.class) {
            object = WorldGenNetherPiece11.a(list, random, i, j, k, l, i1);
        }

        return (WorldGenNetherPiece) object;
    }

    static WorldGenNetherPiece a(WorldGenNetherPieceWeight worldgennetherpieceweight, List list, Random random, int i, int j, int k, int l, int i1) {
        return b(worldgennetherpieceweight, list, random, i, j, k, l, i1);
    }

    static WorldGenNetherPieceWeight[] b() {
        return a;
    }

    static WorldGenNetherPieceWeight[] c() {
        return b;
    }
}
