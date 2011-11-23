package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHut extends WorldGenVillagePiece {

    private int a = -1;
    private final boolean b;
    private final int c;

    public WorldGenVillageHut(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
        this.b = random.nextBoolean();
        this.c = random.nextInt(3);
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenVillageHut a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 4, 6, 5, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageHut(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a < 0) {
            this.a = this.b(world, structureboundingbox);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 5, 4, 0, 0, false);
        this.a(world, structureboundingbox, 0, 0, 0, 3, 0, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 0, 1, 2, 0, 3, Block.DIRT.id, Block.DIRT.id, false);
        if (this.b) {
            this.a(world, structureboundingbox, 1, 4, 1, 2, 4, 3, Block.LOG.id, Block.LOG.id, false);
        } else {
            this.a(world, structureboundingbox, 1, 5, 1, 2, 5, 3, Block.LOG.id, Block.LOG.id, false);
        }

        this.a(world, Block.LOG.id, 0, 1, 4, 0, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 2, 4, 0, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 1, 4, 4, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 2, 4, 4, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 0, 4, 1, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 0, 4, 2, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 0, 4, 3, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 3, 4, 1, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 3, 4, 2, structureboundingbox);
        this.a(world, Block.LOG.id, 0, 3, 4, 3, structureboundingbox);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 3, 0, Block.LOG.id, Block.LOG.id, false);
        this.a(world, structureboundingbox, 3, 1, 0, 3, 3, 0, Block.LOG.id, Block.LOG.id, false);
        this.a(world, structureboundingbox, 0, 1, 4, 0, 3, 4, Block.LOG.id, Block.LOG.id, false);
        this.a(world, structureboundingbox, 3, 1, 4, 3, 3, 4, Block.LOG.id, Block.LOG.id, false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 3, 1, 1, 3, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, structureboundingbox, 1, 1, 4, 2, 3, 4, Block.WOOD.id, Block.WOOD.id, false);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 3, 2, 2, structureboundingbox);
        if (this.c > 0) {
            this.a(world, Block.FENCE.id, 0, this.c, 1, 3, structureboundingbox);
            this.a(world, Block.WOOD_PLATE.id, 0, this.c, 2, 3, structureboundingbox);
        }

        this.a(world, 0, 0, 1, 1, 0, structureboundingbox);
        this.a(world, 0, 0, 1, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 1, 1, 0, this.c(Block.WOODEN_DOOR.id, 1));
        if (this.a(world, 1, 0, -1, structureboundingbox) == 0 && this.a(world, 1, -1, -1, structureboundingbox) != 0) {
            this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 1, 0, -1, structureboundingbox);
        }

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 1, 1, 2, 1);
        return true;
    }
}
