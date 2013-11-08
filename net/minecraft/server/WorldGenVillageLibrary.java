package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageLibrary extends WorldGenVillagePiece {

    public WorldGenVillageLibrary() {}

    public WorldGenVillageLibrary(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(worldgenvillagestartpiece, i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public static WorldGenVillageLibrary a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 9, 6, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageLibrary(worldgenvillagestartpiece, i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k < 0) {
            this.k = this.b(world, structureboundingbox);
            if (this.k < 0) {
                return true;
            }

            this.f.a(0, this.k - this.f.e + 9 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 7, 5, 4, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 0, 0, 0, 8, 0, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 5, 0, 8, 5, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 6, 1, 8, 6, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 7, 2, 8, 7, 3, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        int i = this.a(Blocks.WOOD_STAIRS, 3);
        int j = this.a(Blocks.WOOD_STAIRS, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Blocks.WOOD_STAIRS, i, l, 6 + k, k, structureboundingbox);
                this.a(world, Blocks.WOOD_STAIRS, j, l, 6 + k, 5 - k, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 1, 5, 8, 1, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 8, 1, 0, 8, 1, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 2, 1, 0, 7, 1, 0, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 4, 0, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 2, 5, 0, 4, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 8, 2, 5, 8, 4, 5, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 8, 2, 0, 8, 4, 0, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 2, 1, 0, 4, 4, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 2, 5, 7, 4, 5, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 8, 2, 1, 8, 4, 4, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 4, 0, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 2, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 5, 2, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 6, 2, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 3, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 5, 3, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 6, 3, 0, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 3, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 3, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 3, 3, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 2, 3, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 3, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 8, 3, 3, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 2, 5, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 3, 2, 5, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 5, 2, 5, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 6, 2, 5, structureboundingbox);
        this.a(world, structureboundingbox, 1, 4, 1, 7, 4, 1, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 4, 4, 7, 4, 4, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 3, 4, 7, 3, 4, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);
        this.a(world, Blocks.WOOD, 0, 7, 1, 4, structureboundingbox);
        this.a(world, Blocks.WOOD_STAIRS, this.a(Blocks.WOOD_STAIRS, 0), 7, 1, 3, structureboundingbox);
        k = this.a(Blocks.WOOD_STAIRS, 3);
        this.a(world, Blocks.WOOD_STAIRS, k, 6, 1, 4, structureboundingbox);
        this.a(world, Blocks.WOOD_STAIRS, k, 5, 1, 4, structureboundingbox);
        this.a(world, Blocks.WOOD_STAIRS, k, 4, 1, 4, structureboundingbox);
        this.a(world, Blocks.WOOD_STAIRS, k, 3, 1, 4, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 6, 1, 3, structureboundingbox);
        this.a(world, Blocks.WOOD_PLATE, 0, 6, 2, 3, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 4, 1, 3, structureboundingbox);
        this.a(world, Blocks.WOOD_PLATE, 0, 4, 2, 3, structureboundingbox);
        this.a(world, Blocks.WORKBENCH, 0, 7, 1, 1, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 1, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 1, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 1, 1, 0, this.a(Blocks.WOODEN_DOOR, 1));
        if (this.a(world, 1, 0, -1, structureboundingbox).getMaterial() == Material.AIR && this.a(world, 1, -1, -1, structureboundingbox).getMaterial() != Material.AIR) {
            this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 1, 0, -1, structureboundingbox);
        }

        for (l = 0; l < 6; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.b(world, i1, 9, l, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE, 0, i1, -1, l, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 2, 1, 2, 1);
        return true;
    }

    protected int b(int i) {
        return 1;
    }
}
