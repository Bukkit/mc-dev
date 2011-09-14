package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenVillageStartPiece extends WorldGenVillageWell {

    public WorldChunkManager a;
    public int b;
    public WorldGenVillagePieceWeight c;
    public ArrayList d;
    public ArrayList e = new ArrayList();
    public ArrayList f = new ArrayList();

    public WorldGenVillageStartPiece(WorldChunkManager worldchunkmanager, int i, Random random, int j, int k, ArrayList arraylist, int l) {
        super(0, random, j, k);
        this.a = worldchunkmanager;
        this.d = arraylist;
        this.b = l;
    }

    public WorldChunkManager a() {
        return this.a;
    }
}
