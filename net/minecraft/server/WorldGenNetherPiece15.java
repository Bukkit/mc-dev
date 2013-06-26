package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece15 extends WorldGenNetherPiece1 {

    public WorldGenNetherPieceWeight b;
    public List c = new ArrayList();
    public List d;
    public ArrayList e = new ArrayList();

    public WorldGenNetherPiece15(Random random, int i, int j) {
        super(random, i, j);
        WorldGenNetherPieceWeight[] aworldgennetherpieceweight = WorldGenNetherPieces.a();
        int k = aworldgennetherpieceweight.length;

        int l;
        WorldGenNetherPieceWeight worldgennetherpieceweight;

        for (l = 0; l < k; ++l) {
            worldgennetherpieceweight = aworldgennetherpieceweight[l];
            worldgennetherpieceweight.c = 0;
            this.c.add(worldgennetherpieceweight);
        }

        this.d = new ArrayList();
        aworldgennetherpieceweight = WorldGenNetherPieces.b();
        k = aworldgennetherpieceweight.length;

        for (l = 0; l < k; ++l) {
            worldgennetherpieceweight = aworldgennetherpieceweight[l];
            worldgennetherpieceweight.c = 0;
            this.d.add(worldgennetherpieceweight);
        }
    }
}
