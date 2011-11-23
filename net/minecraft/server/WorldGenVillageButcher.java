package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageButcher extends WorldGenVillagePiece {

    private int a = -1;

    public WorldGenVillageButcher(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenVillageButcher a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 7, 11, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageButcher(i1, random, structureboundingbox, l) : null;
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
        this.a(world, structureboundingbox, 2, 0, 6, 8, 0, 10, Block.DIRT.id, Block.DIRT.id, false);
        this.a(world, Block.COBBLESTONE.id, 0, 6, 0, 6, structureboundingbox);
        this.a(world, structureboundingbox, 2, 1, 6, 2, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
        this.a(world, structureboundingbox, 8, 1, 6, 8, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
        this.a(world, structureboundingbox, 3, 1, 10, 7, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
        this.a(world, structureboundingbox, 1, 0, 1, 7, 0, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 0, 0, 0, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 8, 0, 0, 8, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 0, 0, 7, 1, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 0, 5, 7, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 2, 5, 7, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 4, 1, 8, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 4, 4, 8, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 5, 2, 8, 5, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, Block.WOOD.id, 0, 0, 4, 2, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 0, 4, 3, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 8, 4, 2, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 8, 4, 3, structureboundingbox);
        int i = this.c(Block.WOOD_STAIRS.id, 3);
        int j = this.c(Block.WOOD_STAIRS.id, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Block.WOOD_STAIRS.id, i, l, 4 + k, k, structureboundingbox);
                this.a(world, Block.WOOD_STAIRS.id, j, l, 4 + k, 5 - k, structureboundingbox);
            }
        }

        this.a(world, Block.LOG.id, 0, 0, 2, 1, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 0, 2, 4, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 8, 2, 1, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 8, 2, 4, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 3, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 8, 2, 3, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 2, 5, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 3, 2, 5, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 5, 2, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 6, 2, 5, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 2, 1, 3, structureboundingbox);
        this.a(world, Block.WOOD_PLATE.id, 0, 2, 2, 3, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 1, 1, 4, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, this.c(Block.WOOD_STAIRS.id, 3), 2, 1, 4, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, this.c(Block.WOOD_STAIRS.id, 1), 1, 1, 3, structureboundingbox);
        this.a(world, structureboundingbox, 5, 0, 1, 7, 0, 3, Block.DOUBLE_STEP.id, Block.DOUBLE_STEP.id, false);
        this.a(world, Block.DOUBLE_STEP.id, 0, 6, 1, 1, structureboundingbox);
        this.a(world, Block.DOUBLE_STEP.id, 0, 6, 1, 2, structureboundingbox);
        this.a(world, 0, 0, 2, 1, 0, structureboundingbox);
        this.a(world, 0, 0, 2, 2, 0, structureboundingbox);
        this.a(world, Block.TORCH.id, 0, 2, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, random, 2, 1, 0, this.c(Block.WOODEN_DOOR.id, 1));
        if (this.a(world, 2, 0, -1, structureboundingbox) == 0 && this.a(world, 2, -1, -1, structureboundingbox) != 0) {
            this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        }

        this.a(world, 0, 0, 6, 1, 5, structureboundingbox);
        this.a(world, 0, 0, 6, 2, 5, structureboundingbox);
        this.a(world, Block.TORCH.id, 0, 6, 3, 4, structureboundingbox);
        this.a(world, structureboundingbox, random, 6, 1, 5, this.c(Block.WOODEN_DOOR.id, 1));

        for (k = 0; k < 5; ++k) {
            for (l = 0; l < 9; ++l) {
                this.b(world, l, 7, k, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, l, -1, k, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 4, 1, 2, 2);
        return true;
    }

    protected int a(int i) {
        return i == 0 ? 4 : 0;
    }
}
