package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageBlacksmith extends WorldGenVillagePiece {

    private int a = -1;

    public WorldGenVillageBlacksmith(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenVillageBlacksmith a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 10, 6, 7, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageBlacksmith(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a < 0) {
            this.a = this.b(world, structureboundingbox);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 1, 0, 9, 4, 6, 0, 0, false);
        this.a(world, structureboundingbox, 0, 0, 0, 9, 0, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 4, 0, 9, 4, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 5, 0, 9, 5, 6, Block.STEP.id, Block.STEP.id, false);
        this.a(world, structureboundingbox, 1, 5, 1, 8, 5, 5, 0, 0, false);
        this.a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 4, 0, Block.LOG.id, Block.LOG.id, false);
        this.a(world, structureboundingbox, 3, 1, 0, 3, 4, 0, Block.LOG.id, Block.LOG.id, false);
        this.a(world, structureboundingbox, 0, 1, 6, 0, 4, 6, Block.LOG.id, Block.LOG.id, false);
        this.a(world, Block.WOOD.id, 0, 3, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, 3, 1, 2, 3, 3, 2, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 4, 1, 3, 5, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 1, 6, 5, 3, 6, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 5, 1, 0, 5, 3, 0, Block.FENCE.id, Block.FENCE.id, false);
        this.a(world, structureboundingbox, 9, 1, 0, 9, 3, 0, Block.FENCE.id, Block.FENCE.id, false);
        this.a(world, structureboundingbox, 6, 1, 4, 9, 4, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, Block.LAVA.id, 0, 7, 1, 5, structureboundingbox);
        this.a(world, Block.LAVA.id, 0, 8, 1, 5, structureboundingbox);
        this.a(world, Block.IRON_FENCE.id, 0, 9, 2, 5, structureboundingbox);
        this.a(world, Block.IRON_FENCE.id, 0, 9, 2, 4, structureboundingbox);
        this.a(world, structureboundingbox, 7, 2, 4, 8, 2, 5, 0, 0, false);
        this.a(world, Block.COBBLESTONE.id, 0, 6, 1, 3, structureboundingbox);
        this.a(world, Block.FURNACE.id, 0, 6, 2, 3, structureboundingbox);
        this.a(world, Block.FURNACE.id, 0, 6, 3, 3, structureboundingbox);
        this.a(world, Block.DOUBLE_STEP.id, 0, 8, 1, 1, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 4, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 2, 6, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 2, 6, structureboundingbox);
        this.a(world, Block.FENCE.id, 0, 2, 1, 4, structureboundingbox);
        this.a(world, Block.WOOD_PLATE.id, 0, 2, 2, 4, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 1, 1, 5, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, this.c(Block.WOOD_STAIRS.id, 3), 2, 1, 5, structureboundingbox);
        this.a(world, Block.WOOD_STAIRS.id, this.c(Block.WOOD_STAIRS.id, 1), 1, 1, 4, structureboundingbox);

        int i;

        for (i = 6; i <= 8; ++i) {
            if (this.a(world, i, 0, -1, structureboundingbox) == 0 && this.a(world, i, -1, -1, structureboundingbox) != 0) {
                this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), i, 0, -1, structureboundingbox);
            }
        }

        for (i = 0; i < 7; ++i) {
            for (int j = 0; j < 10; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 7, 1, 1, 1);
        return true;
    }

    protected int a(int i) {
        return 3;
    }
}
