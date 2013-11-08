package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageTemple extends WorldGenVillagePiece {

    public WorldGenVillageTemple() {}

    public WorldGenVillageTemple(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(worldgenvillagestartpiece, i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public static WorldGenVillageTemple a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 5, 12, 9, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageTemple(worldgenvillagestartpiece, i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k < 0) {
            this.k = this.b(world, structureboundingbox);
            if (this.k < 0) {
                return true;
            }

            this.f.a(0, this.k - this.f.e + 12 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 3, 7, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 1, 5, 1, 3, 9, 3, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 1, 0, 0, 3, 0, 8, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 1, 0, 3, 10, 0, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 10, 3, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 4, 1, 1, 4, 10, 3, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 0, 4, 0, 4, 7, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 4, 0, 4, 4, 4, 7, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 1, 8, 3, 4, 8, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 5, 4, 3, 10, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 5, 5, 3, 5, 7, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 9, 0, 4, 9, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, Blocks.COBBLESTONE, 0, 0, 11, 2, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 4, 11, 2, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 2, 11, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 2, 11, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 1, 1, 6, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 1, 1, 7, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 2, 1, 7, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 3, 1, 6, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 3, 1, 7, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 1, 1, 5, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 2, 1, 6, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 3, 1, 5, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 1), 1, 2, 7, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 0), 3, 2, 7, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 3, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 3, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 6, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 7, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 6, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 7, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 6, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 7, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 6, 4, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 7, 4, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 3, 6, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 3, 6, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 3, 8, structureboundingbox);
        this.a(world, Blocks.TORCH, 0, 2, 4, 7, structureboundingbox);
        this.a(world, Blocks.TORCH, 0, 1, 4, 6, structureboundingbox);
        this.a(world, Blocks.TORCH, 0, 3, 4, 6, structureboundingbox);
        this.a(world, Blocks.TORCH, 0, 2, 4, 5, structureboundingbox);
        int i = this.a(Blocks.LADDER, 4);

        int j;

        for (j = 1; j <= 9; ++j) {
            this.a(world, Blocks.LADDER, i, 3, j, 3, structureboundingbox);
        }

        this.a(world, Blocks.AIR, 0, 2, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 2, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 2, 1, 0, this.a(Blocks.WOODEN_DOOR, 1));
        if (this.a(world, 2, 0, -1, structureboundingbox).getMaterial() == Material.AIR && this.a(world, 2, -1, -1, structureboundingbox).getMaterial() != Material.AIR) {
            this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 2, 0, -1, structureboundingbox);
        }

        for (j = 0; j < 9; ++j) {
            for (int k = 0; k < 5; ++k) {
                this.b(world, k, 12, j, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE, 0, k, -1, j, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 2, 1, 2, 1);
        return true;
    }

    protected int b(int i) {
        return 2;
    }
}
