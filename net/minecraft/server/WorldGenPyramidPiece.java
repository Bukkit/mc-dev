package net.minecraft.server;

import java.util.Random;

public class WorldGenPyramidPiece extends WorldGenScatteredPiece {

    private boolean[] e = new boolean[4];
    private static final StructurePieceTreasure[] i = new StructurePieceTreasure[] { new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 2, 7, 15), new StructurePieceTreasure(Item.EMERALD.id, 0, 1, 3, 2), new StructurePieceTreasure(Item.BONE.id, 0, 4, 6, 20), new StructurePieceTreasure(Item.ROTTEN_FLESH.id, 0, 3, 7, 16), new StructurePieceTreasure(Item.SADDLE.id, 0, 1, 1, 3), new StructurePieceTreasure(Item.HORSE_ARMOR_IRON.id, 0, 1, 1, 1), new StructurePieceTreasure(Item.HORSE_ARMOR_GOLD.id, 0, 1, 1, 1), new StructurePieceTreasure(Item.HORSE_ARMOR_DIAMOND.id, 0, 1, 1, 1)};

    public WorldGenPyramidPiece() {}

    public WorldGenPyramidPiece(Random random, int i, int j) {
        super(random, i, 64, j, 21, 15, 21);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("hasPlacedChest0", this.e[0]);
        nbttagcompound.setBoolean("hasPlacedChest1", this.e[1]);
        nbttagcompound.setBoolean("hasPlacedChest2", this.e[2]);
        nbttagcompound.setBoolean("hasPlacedChest3", this.e[3]);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.e[0] = nbttagcompound.getBoolean("hasPlacedChest0");
        this.e[1] = nbttagcompound.getBoolean("hasPlacedChest1");
        this.e[2] = nbttagcompound.getBoolean("hasPlacedChest2");
        this.e[3] = nbttagcompound.getBoolean("hasPlacedChest3");
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, -4, 0, this.a - 1, 0, this.c - 1, Block.SANDSTONE.id, Block.SANDSTONE.id, false);

        int i;

        for (i = 1; i <= 9; ++i) {
            this.a(world, structureboundingbox, i, i, i, this.a - 1 - i, i, this.c - 1 - i, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
            this.a(world, structureboundingbox, i + 1, i, i + 1, this.a - 2 - i, i, this.c - 2 - i, 0, 0, false);
        }

        int j;

        for (i = 0; i < this.a; ++i) {
            for (j = 0; j < this.c; ++j) {
                this.b(world, Block.SANDSTONE.id, 0, i, -5, j, structureboundingbox);
            }
        }

        i = this.c(Block.SANDSTONE_STAIRS.id, 3);
        j = this.c(Block.SANDSTONE_STAIRS.id, 2);
        int k = this.c(Block.SANDSTONE_STAIRS.id, 0);
        int l = this.c(Block.SANDSTONE_STAIRS.id, 1);
        byte b0 = 1;
        byte b1 = 11;

        this.a(world, structureboundingbox, 0, 0, 0, 4, 9, 4, Block.SANDSTONE.id, 0, false);
        this.a(world, structureboundingbox, 1, 10, 1, 3, 10, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, Block.SANDSTONE_STAIRS.id, i, 2, 10, 0, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, j, 2, 10, 4, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, k, 0, 10, 2, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, l, 4, 10, 2, structureboundingbox);
        this.a(world, structureboundingbox, this.a - 5, 0, 0, this.a - 1, 9, 4, Block.SANDSTONE.id, 0, false);
        this.a(world, structureboundingbox, this.a - 4, 10, 1, this.a - 2, 10, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, Block.SANDSTONE_STAIRS.id, i, this.a - 3, 10, 0, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, j, this.a - 3, 10, 4, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, k, this.a - 5, 10, 2, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, l, this.a - 1, 10, 2, structureboundingbox);
        this.a(world, structureboundingbox, 8, 0, 0, 12, 4, 4, Block.SANDSTONE.id, 0, false);
        this.a(world, structureboundingbox, 9, 1, 0, 11, 3, 4, 0, 0, false);
        this.a(world, Block.SANDSTONE.id, 2, 9, 1, 1, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 9, 2, 1, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 9, 3, 1, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 10, 3, 1, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 11, 3, 1, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 11, 2, 1, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 11, 1, 1, structureboundingbox);
        this.a(world, structureboundingbox, 4, 1, 1, 8, 3, 3, Block.SANDSTONE.id, 0, false);
        this.a(world, structureboundingbox, 4, 1, 2, 8, 2, 2, 0, 0, false);
        this.a(world, structureboundingbox, 12, 1, 1, 16, 3, 3, Block.SANDSTONE.id, 0, false);
        this.a(world, structureboundingbox, 12, 1, 2, 16, 2, 2, 0, 0, false);
        this.a(world, structureboundingbox, 5, 4, 5, this.a - 6, 4, this.c - 6, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, 9, 4, 9, 11, 4, 11, 0, 0, false);
        this.a(world, structureboundingbox, 8, 1, 8, 8, 3, 8, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, structureboundingbox, 12, 1, 8, 12, 3, 8, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, structureboundingbox, 8, 1, 12, 8, 3, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, structureboundingbox, 12, 1, 12, 12, 3, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, structureboundingbox, 1, 1, 5, 4, 4, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, this.a - 5, 1, 5, this.a - 2, 4, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, 6, 7, 9, 6, 7, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, this.a - 7, 7, 9, this.a - 7, 7, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, 5, 5, 9, 5, 7, 11, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, structureboundingbox, this.a - 6, 5, 9, this.a - 6, 7, 11, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, 0, 0, 5, 5, 10, structureboundingbox);
        this.a(world, 0, 0, 5, 6, 10, structureboundingbox);
        this.a(world, 0, 0, 6, 6, 10, structureboundingbox);
        this.a(world, 0, 0, this.a - 6, 5, 10, structureboundingbox);
        this.a(world, 0, 0, this.a - 6, 6, 10, structureboundingbox);
        this.a(world, 0, 0, this.a - 7, 6, 10, structureboundingbox);
        this.a(world, structureboundingbox, 2, 4, 4, 2, 6, 4, 0, 0, false);
        this.a(world, structureboundingbox, this.a - 3, 4, 4, this.a - 3, 6, 4, 0, 0, false);
        this.a(world, Block.SANDSTONE_STAIRS.id, i, 2, 4, 5, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, i, 2, 3, 4, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, i, this.a - 3, 4, 5, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, i, this.a - 3, 3, 4, structureboundingbox);
        this.a(world, structureboundingbox, 1, 1, 3, 2, 2, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, this.a - 3, 1, 3, this.a - 2, 2, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, Block.SANDSTONE_STAIRS.id, 0, 1, 1, 2, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, 0, this.a - 2, 1, 2, structureboundingbox);
        this.a(world, Block.STEP.id, 1, 1, 2, 2, structureboundingbox);
        this.a(world, Block.STEP.id, 1, this.a - 2, 2, 2, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, l, 2, 1, 2, structureboundingbox);
        this.a(world, Block.SANDSTONE_STAIRS.id, k, this.a - 3, 1, 2, structureboundingbox);
        this.a(world, structureboundingbox, 4, 3, 5, 4, 3, 18, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, this.a - 5, 3, 5, this.a - 5, 3, 17, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, 3, 1, 5, 4, 2, 16, 0, 0, false);
        this.a(world, structureboundingbox, this.a - 6, 1, 5, this.a - 5, 2, 16, 0, 0, false);

        int i1;

        for (i1 = 5; i1 <= 17; i1 += 2) {
            this.a(world, Block.SANDSTONE.id, 2, 4, 1, i1, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 1, 4, 2, i1, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, this.a - 5, 1, i1, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 1, this.a - 5, 2, i1, structureboundingbox);
        }

        this.a(world, Block.WOOL.id, b0, 10, 0, 7, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 10, 0, 8, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 9, 0, 9, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 11, 0, 9, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 8, 0, 10, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 12, 0, 10, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 7, 0, 10, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 13, 0, 10, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 9, 0, 11, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 11, 0, 11, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 10, 0, 12, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 10, 0, 13, structureboundingbox);
        this.a(world, Block.WOOL.id, b1, 10, 0, 10, structureboundingbox);

        for (i1 = 0; i1 <= this.a - 1; i1 += this.a - 1) {
            this.a(world, Block.SANDSTONE.id, 2, i1, 2, 1, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 2, 2, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 2, 3, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 3, 1, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 3, 2, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 3, 3, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 4, 1, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 1, i1, 4, 2, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 4, 3, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 5, 1, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 5, 2, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 5, 3, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 6, 1, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 1, i1, 6, 2, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 6, 3, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 7, 1, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 7, 2, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 7, 3, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 8, 1, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 8, 2, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 8, 3, structureboundingbox);
        }

        for (i1 = 2; i1 <= this.a - 3; i1 += this.a - 3 - 2) {
            this.a(world, Block.SANDSTONE.id, 2, i1 - 1, 2, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 2, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1 + 1, 2, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1 - 1, 3, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 3, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1 + 1, 3, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1 - 1, 4, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 1, i1, 4, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1 + 1, 4, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1 - 1, 5, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 5, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1 + 1, 5, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1 - 1, 6, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 1, i1, 6, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1 + 1, 6, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1 - 1, 7, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1, 7, 0, structureboundingbox);
            this.a(world, Block.WOOL.id, b0, i1 + 1, 7, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1 - 1, 8, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1, 8, 0, structureboundingbox);
            this.a(world, Block.SANDSTONE.id, 2, i1 + 1, 8, 0, structureboundingbox);
        }

        this.a(world, structureboundingbox, 8, 4, 0, 12, 6, 0, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, 0, 0, 8, 6, 0, structureboundingbox);
        this.a(world, 0, 0, 12, 6, 0, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 9, 5, 0, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 1, 10, 5, 0, structureboundingbox);
        this.a(world, Block.WOOL.id, b0, 11, 5, 0, structureboundingbox);
        this.a(world, structureboundingbox, 8, -14, 8, 12, -11, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, structureboundingbox, 8, -10, 8, 12, -10, 12, Block.SANDSTONE.id, 1, Block.SANDSTONE.id, 1, false);
        this.a(world, structureboundingbox, 8, -9, 8, 12, -9, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
        this.a(world, structureboundingbox, 8, -8, 8, 12, -1, 12, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
        this.a(world, structureboundingbox, 9, -11, 9, 11, -1, 11, 0, 0, false);
        this.a(world, Block.STONE_PLATE.id, 0, 10, -11, 10, structureboundingbox);
        this.a(world, structureboundingbox, 9, -13, 9, 11, -13, 11, Block.TNT.id, 0, false);
        this.a(world, 0, 0, 8, -11, 10, structureboundingbox);
        this.a(world, 0, 0, 8, -10, 10, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 1, 7, -10, 10, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 7, -11, 10, structureboundingbox);
        this.a(world, 0, 0, 12, -11, 10, structureboundingbox);
        this.a(world, 0, 0, 12, -10, 10, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 1, 13, -10, 10, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 13, -11, 10, structureboundingbox);
        this.a(world, 0, 0, 10, -11, 8, structureboundingbox);
        this.a(world, 0, 0, 10, -10, 8, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 1, 10, -10, 7, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 10, -11, 7, structureboundingbox);
        this.a(world, 0, 0, 10, -11, 12, structureboundingbox);
        this.a(world, 0, 0, 10, -10, 12, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 1, 10, -10, 13, structureboundingbox);
        this.a(world, Block.SANDSTONE.id, 2, 10, -11, 13, structureboundingbox);

        for (i1 = 0; i1 < 4; ++i1) {
            if (!this.e[i1]) {
                int j1 = Direction.a[i1] * 2;
                int k1 = Direction.b[i1] * 2;

                this.e[i1] = this.a(world, structureboundingbox, random, 10 + j1, -11, 10 + k1, StructurePieceTreasure.a(i, new StructurePieceTreasure[] { Item.ENCHANTED_BOOK.b(random)}), 2 + random.nextInt(5));
            }
        }

        return true;
    }
}
