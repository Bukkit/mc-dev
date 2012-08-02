package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenVillageStartPiece extends WorldGenVillageWell {

    public final WorldChunkManager a;
    public final boolean b;
    public final int c;
    public WorldGenVillagePieceWeight d;
    public ArrayList h;
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();

    public WorldGenVillageStartPiece(WorldChunkManager worldchunkmanager, int i, Random random, int j, int k, ArrayList arraylist, int l) {
        super((WorldGenVillageStartPiece) null, 0, random, j, k);
        this.a = worldchunkmanager;
        this.h = arraylist;
        this.c = l;
        BiomeBase biomebase = worldchunkmanager.getBiome(j, k);

        this.b = biomebase == BiomeBase.DESERT || biomebase == BiomeBase.DESERT_HILLS;
        this.k = this;
    }

    public WorldChunkManager d() {
        return this.a;
    }
}
