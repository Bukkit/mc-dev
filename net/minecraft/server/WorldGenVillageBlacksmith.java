package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageBlacksmith extends WorldGenVillagePiece {

    private static final StructurePieceTreasure[] a = new StructurePieceTreasure[] { new StructurePieceTreasure(Items.DIAMOND, 0, 1, 3, 3), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 10), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 1, 3, 5), new StructurePieceTreasure(Items.BREAD, 0, 1, 3, 15), new StructurePieceTreasure(Items.APPLE, 0, 1, 3, 15), new StructurePieceTreasure(Items.IRON_PICKAXE, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_SWORD, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_CHESTPLATE, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_HELMET, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_LEGGINGS, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_BOOTS, 0, 1, 1, 5), new StructurePieceTreasure(Item.getItemOf(Blocks.OBSIDIAN), 0, 3, 7, 5), new StructurePieceTreasure(Item.getItemOf(Blocks.SAPLING), 0, 3, 7, 5), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 3), new StructurePieceTreasure(Items.HORSE_ARMOR_IRON, 0, 1, 1, 1), new StructurePieceTreasure(Items.HORSE_ARMOR_GOLD, 0, 1, 1, 1), new StructurePieceTreasure(Items.HORSE_ARMOR_DIAMOND, 0, 1, 1, 1)};
    private boolean b;

    public WorldGenVillageBlacksmith() {}

    public WorldGenVillageBlacksmith(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(worldgenvillagestartpiece, i);
        this.g = j;
        this.f = structureboundingbox;
    }

    public static WorldGenVillageBlacksmith a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 10, 6, 7, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageBlacksmith(worldgenvillagestartpiece, i1, random, structureboundingbox, l) : null;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Chest", this.b);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getBoolean("Chest");
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k < 0) {
            this.k = this.b(world, structureboundingbox);
            if (this.k < 0) {
                return true;
            }

            this.f.a(0, this.k - this.f.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 1, 0, 9, 4, 6, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 0, 0, 0, 9, 0, 6, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 4, 0, 9, 4, 6, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 5, 0, 9, 5, 6, Blocks.STEP, Blocks.STEP, false);
        this.a(world, structureboundingbox, 1, 5, 1, 8, 5, 5, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 4, 0, Blocks.LOG, Blocks.LOG, false);
        this.a(world, structureboundingbox, 3, 1, 0, 3, 4, 0, Blocks.LOG, Blocks.LOG, false);
        this.a(world, structureboundingbox, 0, 1, 6, 0, 4, 6, Blocks.LOG, Blocks.LOG, false);
        this.a(world, Blocks.WOOD, 0, 3, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, 3, 1, 2, 3, 3, 2, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 4, 1, 3, 5, 3, 3, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 5, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 1, 6, 5, 3, 6, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 5, 1, 0, 5, 3, 0, Blocks.FENCE, Blocks.FENCE, false);
        this.a(world, structureboundingbox, 9, 1, 0, 9, 3, 0, Blocks.FENCE, Blocks.FENCE, false);
        this.a(world, structureboundingbox, 6, 1, 4, 9, 4, 6, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, Blocks.LAVA, 0, 7, 1, 5, structureboundingbox);
        this.a(world, Blocks.LAVA, 0, 8, 1, 5, structureboundingbox);
        this.a(world, Blocks.IRON_FENCE, 0, 9, 2, 5, structureboundingbox);
        this.a(world, Blocks.IRON_FENCE, 0, 9, 2, 4, structureboundingbox);
        this.a(world, structureboundingbox, 7, 2, 4, 8, 2, 5, Blocks.AIR, Blocks.AIR, false);
        this.a(world, Blocks.COBBLESTONE, 0, 6, 1, 3, structureboundingbox);
        this.a(world, Blocks.FURNACE, 0, 6, 2, 3, structureboundingbox);
        this.a(world, Blocks.FURNACE, 0, 6, 3, 3, structureboundingbox);
        this.a(world, Blocks.DOUBLE_STEP, 0, 8, 1, 1, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 4, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 2, 6, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 2, 6, structureboundingbox);
        this.a(world, Blocks.FENCE, 0, 2, 1, 4, structureboundingbox);
        this.a(world, Blocks.WOOD_PLATE, 0, 2, 2, 4, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 1, 1, 5, structureboundingbox);
        this.a(world, Blocks.WOOD_STAIRS, this.a(Blocks.WOOD_STAIRS, 3), 2, 1, 5, structureboundingbox);
        this.a(world, Blocks.WOOD_STAIRS, this.a(Blocks.WOOD_STAIRS, 1), 1, 1, 4, structureboundingbox);
        int i;
        int j;

        if (!this.b) {
            i = this.a(1);
            j = this.a(5, 5);
            int k = this.b(5, 5);

            if (structureboundingbox.b(j, i, k)) {
                this.b = true;
                this.a(world, structureboundingbox, random, 5, 1, 5, a, 3 + random.nextInt(6));
            }
        }

        for (i = 6; i <= 8; ++i) {
            if (this.a(world, i, 0, -1, structureboundingbox).getMaterial() == Material.AIR && this.a(world, i, -1, -1, structureboundingbox).getMaterial() != Material.AIR) {
                this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), i, 0, -1, structureboundingbox);
            }
        }

        for (i = 0; i < 7; ++i) {
            for (j = 0; j < 10; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE, 0, j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 7, 1, 1, 1);
        return true;
    }

    protected int b(int i) {
        return 3;
    }
}
