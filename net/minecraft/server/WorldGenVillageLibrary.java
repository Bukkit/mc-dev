package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageLibrary extends WorldGenVillagePiece {

    private int a = -1;

    public WorldGenVillageLibrary(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenVillageLibrary a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 9, 6, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageLibrary(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a < 0) {
            this.a = this.b(world, structureboundingbox);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 9 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 7, 5, 4, 0, 0, false);
        this.a(world, structureboundingbox, 0, 0, 0, 8, 0, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 5, 0, 8, 5, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 6, 1, 8, 6, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 7, 2, 8, 7, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        int i = this.c(Block.WOOD_STAIRS.id, 3);
        int j = this.c(Block.WOOD_STAIRS.id, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Block.WOOD_STAIRS.id, i, l, 6 + k, k, structureboundingbox);
                this.a(world, Block.WOOD_STAIRS.id, j, l, 6 + k, 5 - k, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 1, 5, 8, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 8, 1, 0, 8, 1, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 2, 1, 0, 7, 1, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 4, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 2, 5, 0, 4, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 8, 2, 5, 8, 4, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 8, 2, 0, 8, 4, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 2, 1, 0, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 2, 5, 7, 4, 5, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 8, 2, 1, 8, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 4, 0, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 2, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 5, 2, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 6, 2, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 3, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 5, 3, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 6, 3, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 3, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 3, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 3, 3, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 3, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 3, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 3, 3, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 2, 5, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 3, 2, 5, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 5, 2, 5, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 6, 2, 5, structureboundingbox);
        this.a(world, structureboundingbox, 1, 4, 1, 7, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 4, 4, 7, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 3, 4, 7, 3, 4, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
        this.a(world, Block.WOOD.id, 0, 7, 1, 4, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, this.c(Block.WOOD_STAIRS.id, 0), 7, 1, 3, structureboundingbox);
        k = this.c(Block.WOOD_STAIRS.id, 3);
        this.a(world, Block.WOOD_STAIRS.id, k, 6, 1, 4, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, k, 5, 1, 4, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, k, 4, 1, 4, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, k, 3, 1, 4, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 6, 1, 3, structureboundingbox);
        this.a(world, Block.WOOD_PLATE.id, 0, 6, 2, 3, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 4, 1, 3, structureboundingbox);
        this.a(world, Block.WOOD_PLATE.id, 0, 4, 2, 3, structureboundingbox);
        this.a(world, Block.WORKBENCH.id, 0, 7, 1, 1, structureboundingbox);
        this.a(world, 0, 0, 1, 1, 0, structureboundingbox);
        this.a(world, 0, 0, 1, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 1, 1, 0, this.c(Block.WOODEN_DOOR.id, 1));
        if (this.a(world, 1, 0, -1, structureboundingbox) == 0 && this.a(world, 1, -1, -1, structureboundingbox) != 0) {
            this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 1, 0, -1, structureboundingbox);
        }

        for (l = 0; l < 6; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.b(world, i1, 9, l, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, i1, -1, l, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 2, 1, 2, 1);
        return true;
    }

    protected int a(int i) {
        return 1;
    }
}
