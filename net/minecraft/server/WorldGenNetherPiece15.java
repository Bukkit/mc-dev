package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece15 extends WorldGenNetherPiece1 {

    public WorldGenNetherPieceWeight b;
    public List c;
    public List d;
    public ArrayList e = new ArrayList();

    public WorldGenNetherPiece15() {}

    public WorldGenNetherPiece15(Random random, int i, int j) {
        super(random, i, j);
        this.c = new ArrayList();
        WorldGenNetherPieceWeight[] aworldgennetherpieceweight = WorldGenNetherPieces.b();
        int k = aworldgennetherpieceweight.length;

        int l;
        WorldGenNetherPieceWeight worldgennetherpieceweight;

        for (l = 0; l < k; ++l) {
            worldgennetherpieceweight = aworldgennetherpieceweight[l];
            worldgennetherpieceweight.c = 0;
            this.c.add(worldgennetherpieceweight);
        }

        this.d = new ArrayList();
        aworldgennetherpieceweight = WorldGenNetherPieces.c();
        k = aworldgennetherpieceweight.length;

        for (l = 0; l < k; ++l) {
            worldgennetherpieceweight = aworldgennetherpieceweight[l];
            worldgennetherpieceweight.c = 0;
            this.d.add(worldgennetherpieceweight);
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }
}
