package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHouse2 extends WorldGenVillagePiece {

    public WorldGenVillageHouse2() {}

    public WorldGenVillageHouse2(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(worldgenvillagestartpiece, i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public static WorldGenVillageHouse2 a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 7, 12, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageHouse2(worldgenvillagestartpiece, i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k < 0) {
            this.k = this.b(world, structureboundingbox);
            if (this.k < 0) {
                return true;
            }

            this.f.a(0, this.k - this.f.e + 7 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 7, 4, 4, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 2, 1, 6, 8, 4, 10, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 2, 0, 5, 8, 0, 10, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 0, 1, 7, 0, 4, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 0, 0, 0, 0, 3, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 8, 0, 0, 8, 3, 10, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 0, 0, 7, 2, 0, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 0, 5, 2, 1, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 2, 0, 6, 2, 3, 10, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 3, 0, 10, 7, 3, 10, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 3, 0, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 2, 5, 2, 3, 5, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 0, 4, 1, 8, 4, 1, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 0, 4, 4, 3, 4, 4, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 0, 5, 2, 8, 5, 3, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, Blocks.WOOD, 0, 0, 4, 2, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 0, 4, 3, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 8, 4, 2, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 8, 4, 3, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 8, 4, 4, structureboundingbox);
        int i = this.a(Blocks.WOOD_STAIRS, 3);
        int j = this.a(Blocks.WOOD_STAIRS, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Blocks.WOOD_STAIRS, i, l, 4 + k, k, structureboundingbox);
                if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6)) {
                    this.a(world, Blocks.WOOD_STAIRS, j, l, 4 + k, 5 - k, structureboundingbox);
                }
            }
        }

        this.a(world, structureboundingbox, 3, 4, 5, 3, 4, 10, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 7, 4, 2, 7, 4, 10, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 4, 5, 4, 4, 5, 10, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 6, 5, 4, 6, 5, 10, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 5, 6, 3, 5, 6, 10, Blocks.WOOD, Blocks.WOOD, false);
        k = this.a(Blocks.WOOD_STAIRS, 0);

        int i1;

        for (l = 4; l >= 1; --l) {
            this.a(world, Blocks.WOOD, 0, l, 2 + l, 7 - l, structureboundingbox);

            for (i1 = 8 - l; i1 <= 10; ++i1) {
                this.a(world, Blocks.WOOD_STAIRS, k, l, 2 + l, i1, structureboundingbox);
            }
        }

        l = this.a(Blocks.WOOD_STAIRS, 1);
        this.a(world, Blocks.WOOD, 0, 6, 6, 3, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 7, 5, 4, structureboundingbox);
        this.a(world, Blocks.WOOD_STAIRS, l, 6, 6, 4, structureboundingbox);

        int j1;

        for (i1 = 6; i1 <= 8; ++i1) {
            for (j1 = 5; j1 <= 10; ++j1) {
                this.a(world, Blocks.WOOD_STAIRS, l, i1, 12 - i1, j1, structureboundingbox);
            }
        }

        this.a(world, Blocks.LOG, 0, 0, 2, 1, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 0, 2, 4, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 3, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 4, 2, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 5, 2, 0, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 6, 2, 0, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 8, 2, 1, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 2, 3, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 8, 2, 4, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 8, 2, 5, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 8, 2, 6, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 2, 7, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 2, 8, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 8, 2, 9, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 2, 2, 6, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 2, 7, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 2, 8, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 2, 2, 9, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 4, 4, 10, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 5, 4, 10, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 6, 4, 10, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 5, 5, 10, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 2, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 2, 2, 0, structureboundingbox);
        this.a(world, Blocks.TORCH, 0, 2, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, random, 2, 1, 0, this.a(Blocks.WOODEN_DOOR, 1));
        this.a(world, structureboundingbox, 1, 0, -1, 3, 2, -1, Blocks.AIR, Blocks.AIR, false);
        if (this.a(world, 2, 0, -1, structureboundingbox).getMaterial() == Material.AIR && this.a(world, 2, -1, -1, structureboundingbox).getMaterial() != Material.AIR) {
            this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 2, 0, -1, structureboundingbox);
        }

        for (i1 = 0; i1 < 5; ++i1) {
            for (j1 = 0; j1 < 9; ++j1) {
                this.b(world, j1, 7, i1, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE, 0, j1, -1, i1, structureboundingbox);
            }
        }

        for (i1 = 5; i1 < 11; ++i1) {
            for (j1 = 2; j1 < 9; ++j1) {
                this.b(world, j1, 7, i1, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE, 0, j1, -1, i1, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 4, 1, 2, 2);
        return true;
    }
}
