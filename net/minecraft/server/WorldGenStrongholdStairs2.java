package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenStrongholdStairs2 extends WorldGenStrongholdStairs {

    public WorldGenStrongholdPieceWeight a;
    public WorldGenStrongholdPortalRoom b;
    public ArrayList c = new ArrayList();

    public WorldGenStrongholdStairs2(int i, Random random, int j, int k) {
        super(0, random, j, k);
    }

    public ChunkPosition b_() {
        return this.b != null ? this.b.b_() : super.b_();
    }
}
