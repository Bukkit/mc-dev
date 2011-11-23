package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageTemple extends WorldGenVillagePiece {

    private int a = -1;

    public WorldGenVillageTemple(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenVillageTemple a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 5, 12, 9, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageTemple(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a < 0) {
            this.a = this.b(world, structureboundingbox);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 12 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 3, 7, 0, 0, false);
        this.a(world, structureboundingbox, 1, 5, 1, 3, 9, 3, 0, 0, false);
        this.a(world, structureboundingbox, 1, 0, 0, 3, 0, 8, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 1, 0, 3, 10, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 10, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 4, 1, 1, 4, 10, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 0, 4, 0, 4, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 4, 0, 4, 4, 4, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 1, 8, 3, 4, 8, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 5, 4, 3, 10, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 1, 5, 5, 3, 5, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 9, 0, 4, 9, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        this.a(world, Block.COBBLESTONE.id, 0, 0, 11, 2, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 4, 11, 2, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 2, 11, 0, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 2, 11, 4, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 1, 1, 6, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 1, 1, 7, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 2, 1, 7, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 3, 1, 6, structureboundingbox);
        this.a(world, Block.COBBLESTONE.id, 0, 3, 1, 7, structureboundingbox);
        this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 1, 1, 5, structureboundingbox);
        this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 2, 1, 6, structureboundingbox);
        this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 3, 1, 5, structureboundingbox);
        this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 1), 1, 2, 7, structureboundingbox);
        this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 0), 3, 2, 7, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 3, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 2, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 3, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 6, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 7, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 6, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 7, 2, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 6, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 7, 0, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 6, 4, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 7, 4, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 0, 3, 6, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 4, 3, 6, structureboundingbox);
        this.a(world, Block.THIN_GLASS.id, 0, 2, 3, 8, structureboundingbox);
        this.a(world, Block.TORCH.id, 0, 2, 4, 7, structureboundingbox);
        this.a(world, Block.TORCH.id, 0, 1, 4, 6, structureboundingbox);
        this.a(world, Block.TORCH.id, 0, 3, 4, 6, structureboundingbox);
        this.a(world, Block.TORCH.id, 0, 2, 4, 5, structureboundingbox);
        int i = this.c(Block.LADDER.id, 4);

        int j;

        for (j = 1; j <= 9; ++j) {
            this.a(world, Block.LADDER.id, i, 3, j, 3, structureboundingbox);
        }

        this.a(world, 0, 0, 2, 1, 0, structureboundingbox);
        this.a(world, 0, 0, 2, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 2, 1, 0, this.c(Block.WOODEN_DOOR.id, 1));
        if (this.a(world, 2, 0, -1, structureboundingbox) == 0 && this.a(world, 2, -1, -1, structureboundingbox) != 0) {
            this.a(world, Block.COBBLESTONE_STAIRS.id, this.c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        }

        for (j = 0; j < 9; ++j) {
            for (int k = 0; k < 5; ++k) {
                this.b(world, k, 12, j, structureboundingbox);
                this.b(world, Block.COBBLESTONE.id, 0, k, -1, j, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 2, 1, 2, 1);
        return true;
    }

    protected int a(int i) {
        return 2;
    }
}
