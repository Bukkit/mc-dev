package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHouse2 extends WorldGenVillagePiece {

    private int a = -1;

    public WorldGenVillageHouse2(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenVillageHouse2 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 7, 12, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageHouse2(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a < 0) {
            this.a = this.b(world, structureboundingbox);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 7 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 7, 4, 4, 0, 0, false);
        this.a(world, structureboundingbox, 2, 1, 6, 8, 4, 10, 0, 0, false);
        this.a(world, structureboundingbox, 2, 0, 5, 8, 0, 10, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 0, 1, 7, 0, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 0, 0, 0, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 8, 0, 0, 8, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 0, 0, 7, 2, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 0, 5, 2, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 2, 0, 6, 2, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 3, 0, 10, 7, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 2, 5, 2, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 4, 1, 8, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 4, 4, 3, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 5, 2, 8, 5, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, Block.WOOD.id, 0, 0, 4, 2, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 0, 4, 3, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 8, 4, 2, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 8, 4, 3, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 8, 4, 4, structureboundingbox);
        int i = this.c(Block.WOOD_STAIRS.id, 3);
        int j = this.c(Block.WOOD_STAIRS.id, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Block.WOOD_STAIRS.id, i, l, 4 + k, k, structureboundingbox);
                if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6)) {
                    this.a(world, Block.WOOD_STAIRS.id, j, l, 4 + k, 5 - k, structureboundingbox);
                }
            }
        }

        this.a(world, structureboundingbox, 3, 4, 5, 3, 4, 10, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 7, 4, 2, 7, 4, 10, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 4, 5, 4, 4, 5, 10, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 6, 5, 4, 6, 5, 10, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 5, 6, 3, 5, 6, 10, Block.WOOD.id, Block.WOOD.id, false);
        k = this.c(Block.WOOD_STAIRS.id, 0);

        int i1;

        for (l = 4; l >= 1; --l) {
            this.a(world, Block.WOOD.id, 0, l, 2 + l, 7 - l, structureboundingbox);

            for (i1 = 8 - l; i1 <= 10; ++i1) {
                this.a(world, Block.WOOD_STAIRS.id, k, l, 2 + l, i1, structureboundingbox);
            }
        }

        l = this.c(Block.WOOD_STAIRS.id, 1);
        this.a(world, Block.WOOD.id, 0, 6, 6, 3, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 7, 5, 4, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, l, 6, 6, 4, structureboundingbox);

        int j1;

        for (i1 = 6; i1 <= 8; ++i1) {
            for (j1 = 5; j1 <= 10; ++j1) {
                this.a(world, Block.WOOD_STAIRS.id, l, i1, 12 - i1, j1, structureboundingbox);
            }
        }

        this.a(world, Block.LOG.id, 0, 0, 2, 1, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 0, 2, 4, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 3, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 4, 2, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 5, 2, 0, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 6, 2, 0, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 8, 2, 1, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 3, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 8, 2, 4, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 8, 2, 5, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 8, 2, 6, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 7, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 8, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 8, 2, 9, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 2, 2, 6, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 2, 7, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 2, 8, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 2, 2, 9, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 4, 4, 10, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 5, 4, 10, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 6, 4, 10, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 5, 5, 10, structureboundingbox);
        this.a(world, 0, 0, 2, 1, 0, structureboundingbox);
        this.a(world, 0, 0, 2, 2, 0, structureboundingbox);
        this.a(world, Block.TORCH.id, 0, 2, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, random, 2, 1, 0, this.c(Block.WOODEN_DOOR.id, 1));
        this.a(world, structureboundingbox, 1, 0, -1, 3, 2, -1, 0, 0, false);
        if (this.a(world, 2, 0, -1, structureboundingbox) == 0 && this.a(world, 2, -1, -1, structureboundingbox) != 0) {
            this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        }

        for (i1 = 0; i1 < 5; ++i1) {
            for (j1 = 0; j1 < 9; ++j1) {
                this.b(world, j1, 7, i1, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, j1, -1, i1, structureboundingbox);
            }
        }

        for (i1 = 5; i1 < 11; ++i1) {
            for (j1 = 2; j1 < 9; ++j1) {
                this.b(world, j1, 7, i1, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, j1, -1, i1, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 4, 1, 2, 2);
        return true;
    }
}
