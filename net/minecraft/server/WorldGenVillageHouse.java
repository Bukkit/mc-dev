package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHouse extends WorldGenVillagePiece {

    private int a = -1;
    private final boolean b;

    public WorldGenVillageHouse(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
        this.b = random.nextBoolean();
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenVillageHouse a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 5, 6, 5, l);

        return StructurePiece.a(list, structureboundingbox) != null ? null : new WorldGenVillageHouse(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a < 0) {
            this.a = this.b(world, structureboundingbox);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 0, 0, 4, 0, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Block.LOG.id, Block.LOG.id, false);
        this.a(world, structureboundingbox, 1, 4, 1, 3, 4, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, Block.COBBLESTONE.id, 0, 0, 1, 0, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 0, 2, 0, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 0, 3, 0, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 4, 1, 0, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 4, 2, 0, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 4, 3, 0, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 0, 1, 4, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 0, 2, 4, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 0, 3, 4, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 4, 1, 4, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 4, 2, 4, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 4, 3, 4, structureboundingbox);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 1, 4, 3, 3, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 2, 4, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 2, 2, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 1, 1, 0, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 1, 2, 0, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 1, 3, 0, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 2, 3, 0, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 3, 3, 0, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 3, 2, 0, structureboundingbox);
        this.a(world, Block.WOOD.id, 0, 3, 1, 0, structureboundingbox);
        if (this.a(world, 2, 0, -1, structureboundingbox) == 0 && this.a(world, 2, -1, -1, structureboundingbox) != 0) {
            this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 3, 3, 0, 0, false);
        if (this.b) {
            this.a(world, Block.FENCE.id, 0, 0, 5, 0, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 1, 5, 0, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 2, 5, 0, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 3, 5, 0, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 4, 5, 0, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 0, 5, 4, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 1, 5, 4, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 2, 5, 4, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 3, 5, 4, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 4, 5, 4, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 4, 5, 1, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 4, 5, 2, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 4, 5, 3, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 0, 5, 1, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 0, 5, 2, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 0, 5, 3, structureboundingbox);
        }

        int i;

        if (this.b) {
            i = this.c(Block.LADDER.id, 3);
            this.a(world, Block.LADDER.id, i, 3, 1, 3, structureboundingbox);
            this.a(world, Block.LADDER.id, i, 3, 2, 3, structureboundingbox);
            this.a(world, Block.LADDER.id, i, 3, 3, 3, structureboundingbox);
            this.a(world, Block.LADDER.id, i, 3, 4, 3, structureboundingbox);
        }

        this.a(world, Block.TORCH.id, 0, 2, 3, 1, structureboundingbox);

        for (i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 1, 1, 2, 1);
        return true;
    }
}
