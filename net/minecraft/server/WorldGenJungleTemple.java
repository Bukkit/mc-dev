package net.minecraft.server;

import java.util.Random;

public class WorldGenJungleTemple extends WorldGenScatteredPiece {

    private boolean e;
    private boolean i;
    private boolean j;
    private boolean k;
    private static final StructurePieceTreasure[] l = new StructurePieceTreasure[] { new StructurePieceTreasure(Items.DIAMOND, 0, 1, 3, 3), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 10), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 2, 7, 15), new StructurePieceTreasure(Items.EMERALD, 0, 1, 3, 2), new StructurePieceTreasure(Items.BONE, 0, 4, 6, 20), new StructurePieceTreasure(Items.ROTTEN_FLESH, 0, 3, 7, 16), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 3), new StructurePieceTreasure(Items.HORSE_ARMOR_IRON, 0, 1, 1, 1), new StructurePieceTreasure(Items.HORSE_ARMOR_GOLD, 0, 1, 1, 1), new StructurePieceTreasure(Items.HORSE_ARMOR_DIAMOND, 0, 1, 1, 1)};
    private static final StructurePieceTreasure[] m = new StructurePieceTreasure[] { new StructurePieceTreasure(Items.ARROW, 0, 2, 7, 30)};
    private static WorldGenJungleTemplePiece n = new WorldGenJungleTemplePiece((WorldGenJungleTempleUnknown) null);

    public WorldGenJungleTemple() {}

    public WorldGenJungleTemple(Random random, int i, int j) {
        super(random, i, 64, j, 12, 10, 15);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("placedMainChest", this.e);
        nbttagcompound.setBoolean("placedHiddenChest", this.i);
        nbttagcompound.setBoolean("placedTrap1", this.j);
        nbttagcompound.setBoolean("placedTrap2", this.k);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.e = nbttagcompound.getBoolean("placedMainChest");
        this.i = nbttagcompound.getBoolean("placedHiddenChest");
        this.j = nbttagcompound.getBoolean("placedTrap1");
        this.k = nbttagcompound.getBoolean("placedTrap2");
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (!this.a(world, structureboundingbox, 0)) {
            return false;
        } else {
            int i = this.a(Blocks.COBBLESTONE_STAIRS, 3);
            int j = this.a(Blocks.COBBLESTONE_STAIRS, 2);
            int k = this.a(Blocks.COBBLESTONE_STAIRS, 0);
            int l = this.a(Blocks.COBBLESTONE_STAIRS, 1);

            this.a(world, structureboundingbox, 0, -4, 0, this.a - 1, 0, this.c - 1, false, random, n);
            this.a(world, structureboundingbox, 2, 1, 2, 9, 2, 2, false, random, n);
            this.a(world, structureboundingbox, 2, 1, 12, 9, 2, 12, false, random, n);
            this.a(world, structureboundingbox, 2, 1, 3, 2, 2, 11, false, random, n);
            this.a(world, structureboundingbox, 9, 1, 3, 9, 2, 11, false, random, n);
            this.a(world, structureboundingbox, 1, 3, 1, 10, 6, 1, false, random, n);
            this.a(world, structureboundingbox, 1, 3, 13, 10, 6, 13, false, random, n);
            this.a(world, structureboundingbox, 1, 3, 2, 1, 6, 12, false, random, n);
            this.a(world, structureboundingbox, 10, 3, 2, 10, 6, 12, false, random, n);
            this.a(world, structureboundingbox, 2, 3, 2, 9, 3, 12, false, random, n);
            this.a(world, structureboundingbox, 2, 6, 2, 9, 6, 12, false, random, n);
            this.a(world, structureboundingbox, 3, 7, 3, 8, 7, 11, false, random, n);
            this.a(world, structureboundingbox, 4, 8, 4, 7, 8, 10, false, random, n);
            this.a(world, structureboundingbox, 3, 1, 3, 8, 2, 11);
            this.a(world, structureboundingbox, 4, 3, 6, 7, 3, 9);
            this.a(world, structureboundingbox, 2, 4, 2, 9, 5, 12);
            this.a(world, structureboundingbox, 4, 6, 5, 7, 6, 9);
            this.a(world, structureboundingbox, 5, 7, 6, 6, 7, 8);
            this.a(world, structureboundingbox, 5, 1, 2, 6, 2, 2);
            this.a(world, structureboundingbox, 5, 2, 12, 6, 2, 12);
            this.a(world, structureboundingbox, 5, 5, 1, 6, 5, 1);
            this.a(world, structureboundingbox, 5, 5, 13, 6, 5, 13);
            this.a(world, Blocks.AIR, 0, 1, 5, 5, structureboundingbox);
            this.a(world, Blocks.AIR, 0, 10, 5, 5, structureboundingbox);
            this.a(world, Blocks.AIR, 0, 1, 5, 9, structureboundingbox);
            this.a(world, Blocks.AIR, 0, 10, 5, 9, structureboundingbox);

            int i1;

            for (i1 = 0; i1 <= 14; i1 += 14) {
                this.a(world, structureboundingbox, 2, 4, i1, 2, 5, i1, false, random, n);
                this.a(world, structureboundingbox, 4, 4, i1, 4, 5, i1, false, random, n);
                this.a(world, structureboundingbox, 7, 4, i1, 7, 5, i1, false, random, n);
                this.a(world, structureboundingbox, 9, 4, i1, 9, 5, i1, false, random, n);
            }

            this.a(world, structureboundingbox, 5, 6, 0, 6, 6, 0, false, random, n);

            for (i1 = 0; i1 <= 11; i1 += 11) {
                for (int j1 = 2; j1 <= 12; j1 += 2) {
                    this.a(world, structureboundingbox, i1, 4, j1, i1, 5, j1, false, random, n);
                }

                this.a(world, structureboundingbox, i1, 6, 5, i1, 6, 5, false, random, n);
                this.a(world, structureboundingbox, i1, 6, 9, i1, 6, 9, false, random, n);
            }

            this.a(world, structureboundingbox, 2, 7, 2, 2, 9, 2, false, random, n);
            this.a(world, structureboundingbox, 9, 7, 2, 9, 9, 2, false, random, n);
            this.a(world, structureboundingbox, 2, 7, 12, 2, 9, 12, false, random, n);
            this.a(world, structureboundingbox, 9, 7, 12, 9, 9, 12, false, random, n);
            this.a(world, structureboundingbox, 4, 9, 4, 4, 9, 4, false, random, n);
            this.a(world, structureboundingbox, 7, 9, 4, 7, 9, 4, false, random, n);
            this.a(world, structureboundingbox, 4, 9, 10, 4, 9, 10, false, random, n);
            this.a(world, structureboundingbox, 7, 9, 10, 7, 9, 10, false, random, n);
            this.a(world, structureboundingbox, 5, 9, 7, 6, 9, 7, false, random, n);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 5, 9, 6, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 6, 9, 6, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, j, 5, 9, 8, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, j, 6, 9, 8, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 4, 0, 0, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 5, 0, 0, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 6, 0, 0, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 7, 0, 0, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 4, 1, 8, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 4, 2, 9, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 4, 3, 10, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 7, 1, 8, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 7, 2, 9, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, i, 7, 3, 10, structureboundingbox);
            this.a(world, structureboundingbox, 4, 1, 9, 4, 1, 9, false, random, n);
            this.a(world, structureboundingbox, 7, 1, 9, 7, 1, 9, false, random, n);
            this.a(world, structureboundingbox, 4, 1, 10, 7, 2, 10, false, random, n);
            this.a(world, structureboundingbox, 5, 4, 5, 6, 4, 5, false, random, n);
            this.a(world, Blocks.COBBLESTONE_STAIRS, k, 4, 4, 5, structureboundingbox);
            this.a(world, Blocks.COBBLESTONE_STAIRS, l, 7, 4, 5, structureboundingbox);

            for (i1 = 0; i1 < 4; ++i1) {
                this.a(world, Blocks.COBBLESTONE_STAIRS, j, 5, 0 - i1, 6 + i1, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE_STAIRS, j, 6, 0 - i1, 6 + i1, structureboundingbox);
                this.a(world, structureboundingbox, 5, 0 - i1, 7 + i1, 6, 0 - i1, 9 + i1);
            }

            this.a(world, structureboundingbox, 1, -3, 12, 10, -1, 13);
            this.a(world, structureboundingbox, 1, -3, 1, 3, -1, 13);
            this.a(world, structureboundingbox, 1, -3, 1, 9, -1, 5);

            for (i1 = 1; i1 <= 13; i1 += 2) {
                this.a(world, structureboundingbox, 1, -3, i1, 1, -2, i1, false, random, n);
            }

            for (i1 = 2; i1 <= 12; i1 += 2) {
                this.a(world, structureboundingbox, 1, -1, i1, 3, -1, i1, false, random, n);
            }

            this.a(world, structureboundingbox, 2, -2, 1, 5, -2, 1, false, random, n);
            this.a(world, structureboundingbox, 7, -2, 1, 9, -2, 1, false, random, n);
            this.a(world, structureboundingbox, 6, -3, 1, 6, -3, 1, false, random, n);
            this.a(world, structureboundingbox, 6, -1, 1, 6, -1, 1, false, random, n);
            this.a(world, Blocks.TRIPWIRE_SOURCE, this.a(Blocks.TRIPWIRE_SOURCE, 3) | 4, 1, -3, 8, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE_SOURCE, this.a(Blocks.TRIPWIRE_SOURCE, 1) | 4, 4, -3, 8, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE, 4, 2, -3, 8, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE, 4, 3, -3, 8, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 5, -3, 7, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 5, -3, 6, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 5, -3, 5, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 5, -3, 4, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 5, -3, 3, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 5, -3, 2, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 5, -3, 1, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 4, -3, 1, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 3, -3, 1, structureboundingbox);
            if (!this.j) {
                this.j = this.a(world, structureboundingbox, random, 3, -2, 1, 2, m, 2);
            }

            this.a(world, Blocks.VINE, 15, 3, -2, 2, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE_SOURCE, this.a(Blocks.TRIPWIRE_SOURCE, 2) | 4, 7, -3, 1, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE_SOURCE, this.a(Blocks.TRIPWIRE_SOURCE, 0) | 4, 7, -3, 5, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE, 4, 7, -3, 2, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE, 4, 7, -3, 3, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE, 4, 7, -3, 4, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 8, -3, 6, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 9, -3, 6, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 9, -3, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 9, -3, 4, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 9, -2, 4, structureboundingbox);
            if (!this.k) {
                this.k = this.a(world, structureboundingbox, random, 9, -2, 3, 4, m, 2);
            }

            this.a(world, Blocks.VINE, 15, 8, -1, 3, structureboundingbox);
            this.a(world, Blocks.VINE, 15, 8, -2, 3, structureboundingbox);
            if (!this.e) {
                this.e = this.a(world, structureboundingbox, random, 8, -3, 3, StructurePieceTreasure.a(l, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 2 + random.nextInt(5));
            }

            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 9, -3, 2, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 8, -3, 1, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 4, -3, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 5, -2, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 5, -1, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 6, -3, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 7, -2, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 7, -1, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 8, -3, 5, structureboundingbox);
            this.a(world, structureboundingbox, 9, -1, 1, 9, -1, 5, false, random, n);
            this.a(world, structureboundingbox, 8, -3, 8, 10, -1, 10);
            this.a(world, Blocks.SMOOTH_BRICK, 3, 8, -2, 11, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 3, 9, -2, 11, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 3, 10, -2, 11, structureboundingbox);
            this.a(world, Blocks.LEVER, BlockLever.b(this.a(Blocks.LEVER, 2)), 8, -2, 12, structureboundingbox);
            this.a(world, Blocks.LEVER, BlockLever.b(this.a(Blocks.LEVER, 2)), 9, -2, 12, structureboundingbox);
            this.a(world, Blocks.LEVER, BlockLever.b(this.a(Blocks.LEVER, 2)), 10, -2, 12, structureboundingbox);
            this.a(world, structureboundingbox, 8, -3, 8, 8, -3, 10, false, random, n);
            this.a(world, structureboundingbox, 10, -3, 8, 10, -3, 10, false, random, n);
            this.a(world, Blocks.MOSSY_COBBLESTONE, 0, 10, -2, 9, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 8, -2, 9, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 8, -2, 10, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE, 0, 10, -1, 9, structureboundingbox);
            this.a(world, Blocks.PISTON_STICKY, 1, 9, -2, 8, structureboundingbox);
            this.a(world, Blocks.PISTON_STICKY, this.a(Blocks.PISTON_STICKY, 4), 10, -2, 8, structureboundingbox);
            this.a(world, Blocks.PISTON_STICKY, this.a(Blocks.PISTON_STICKY, 4), 10, -1, 8, structureboundingbox);
            this.a(world, Blocks.DIODE_OFF, this.a(Blocks.DIODE_OFF, 2), 10, -2, 10, structureboundingbox);
            if (!this.i) {
                this.i = this.a(world, structureboundingbox, random, 9, -3, 10, StructurePieceTreasure.a(l, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 2 + random.nextInt(5));
            }

            return true;
        }
    }
}
