package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenVillageStartPiece extends WorldGenVillageWell {

    public WorldChunkManager a;
    public boolean b;
    public int c;
    public WorldGenVillagePieceWeight d;
    public List e;
    public List i = new ArrayList();
    public List j = new ArrayList();

    public WorldGenVillageStartPiece() {}

    public WorldGenVillageStartPiece(WorldChunkManager worldchunkmanager, int i, Random random, int j, int k, List list, int l) {
        super((WorldGenVillageStartPiece) null, 0, random, j, k);
        this.a = worldchunkmanager;
        this.e = list;
        this.c = l;
        BiomeBase biomebase = worldchunkmanager.getBiome(j, k);

        this.b = biomebase == BiomeBase.DESERT || biomebase == BiomeBase.DESERT_HILLS;
    }

    public WorldChunkManager e() {
        return this.a;
    }
}
