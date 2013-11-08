package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdRoomCrossing extends WorldGenStrongholdPiece {

    private static final StructurePieceTreasure[] b = new StructurePieceTreasure[] { new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 10), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 1, 3, 5), new StructurePieceTreasure(Items.REDSTONE, 0, 4, 9, 5), new StructurePieceTreasure(Items.COAL, 0, 3, 8, 10), new StructurePieceTreasure(Items.BREAD, 0, 1, 3, 15), new StructurePieceTreasure(Items.APPLE, 0, 1, 3, 15), new StructurePieceTreasure(Items.IRON_PICKAXE, 0, 1, 1, 1)};
    protected int a;

    public WorldGenStrongholdRoomCrossing() {}

    public WorldGenStrongholdRoomCrossing(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.d = this.a(random);
        this.f = structureboundingbox;
        this.a = random.nextInt(5);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("Type", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getInt("Type");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStart) structurepiece, list, random, 4, 1);
        this.b((WorldGenStrongholdStart) structurepiece, list, random, 1, 4);
        this.c((WorldGenStrongholdStart) structurepiece, list, random, 1, 4);
    }

    public static WorldGenStrongholdRoomCrossing a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 11, 7, 11, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdRoomCrossing(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 10, 6, 10, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 4, 1, 0);
            this.a(world, structureboundingbox, 4, 1, 10, 6, 3, 10, Blocks.AIR, Blocks.AIR, false);
            this.a(world, structureboundingbox, 0, 1, 4, 0, 3, 6, Blocks.AIR, Blocks.AIR, false);
            this.a(world, structureboundingbox, 10, 1, 4, 10, 3, 6, Blocks.AIR, Blocks.AIR, false);
            int i;

            switch (this.a) {
            case 0:
                this.a(world, Blocks.SMOOTH_BRICK, 0, 5, 1, 5, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 5, 2, 5, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 5, 3, 5, structureboundingbox);
                this.a(world, Blocks.TORCH, 0, 4, 3, 5, structureboundingbox);
                this.a(world, Blocks.TORCH, 0, 6, 3, 5, structureboundingbox);
                this.a(world, Blocks.TORCH, 0, 5, 3, 4, structureboundingbox);
                this.a(world, Blocks.TORCH, 0, 5, 3, 6, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 4, 1, 4, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 4, 1, 5, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 4, 1, 6, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 6, 1, 4, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 6, 1, 5, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 6, 1, 6, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 5, 1, 4, structureboundingbox);
                this.a(world, Blocks.STEP, 0, 5, 1, 6, structureboundingbox);
                break;

            case 1:
                for (i = 0; i < 5; ++i) {
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 3, 1, 3 + i, structureboundingbox);
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 7, 1, 3 + i, structureboundingbox);
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 3 + i, 1, 3, structureboundingbox);
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 3 + i, 1, 7, structureboundingbox);
                }

                this.a(world, Blocks.SMOOTH_BRICK, 0, 5, 1, 5, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 5, 2, 5, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 5, 3, 5, structureboundingbox);
                this.a(world, Blocks.WATER, 0, 5, 4, 5, structureboundingbox);
                break;

            case 2:
                for (i = 1; i <= 9; ++i) {
                    this.a(world, Blocks.COBBLESTONE, 0, 1, 3, i, structureboundingbox);
                    this.a(world, Blocks.COBBLESTONE, 0, 9, 3, i, structureboundingbox);
                }

                for (i = 1; i <= 9; ++i) {
                    this.a(world, Blocks.COBBLESTONE, 0, i, 3, 1, structureboundingbox);
                    this.a(world, Blocks.COBBLESTONE, 0, i, 3, 9, structureboundingbox);
                }

                this.a(world, Blocks.COBBLESTONE, 0, 5, 1, 4, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE, 0, 5, 1, 6, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE, 0, 5, 3, 4, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE, 0, 5, 3, 6, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE, 0, 4, 1, 5, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE, 0, 6, 1, 5, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE, 0, 4, 3, 5, structureboundingbox);
                this.a(world, Blocks.COBBLESTONE, 0, 6, 3, 5, structureboundingbox);

                for (i = 1; i <= 3; ++i) {
                    this.a(world, Blocks.COBBLESTONE, 0, 4, i, 4, structureboundingbox);
                    this.a(world, Blocks.COBBLESTONE, 0, 6, i, 4, structureboundingbox);
                    this.a(world, Blocks.COBBLESTONE, 0, 4, i, 6, structureboundingbox);
                    this.a(world, Blocks.COBBLESTONE, 0, 6, i, 6, structureboundingbox);
                }

                this.a(world, Blocks.TORCH, 0, 5, 3, 5, structureboundingbox);

                for (i = 2; i <= 8; ++i) {
                    this.a(world, Blocks.WOOD, 0, 2, 3, i, structureboundingbox);
                    this.a(world, Blocks.WOOD, 0, 3, 3, i, structureboundingbox);
                    if (i <= 3 || i >= 7) {
                        this.a(world, Blocks.WOOD, 0, 4, 3, i, structureboundingbox);
                        this.a(world, Blocks.WOOD, 0, 5, 3, i, structureboundingbox);
                        this.a(world, Blocks.WOOD, 0, 6, 3, i, structureboundingbox);
                    }

                    this.a(world, Blocks.WOOD, 0, 7, 3, i, structureboundingbox);
                    this.a(world, Blocks.WOOD, 0, 8, 3, i, structureboundingbox);
                }

                this.a(world, Blocks.LADDER, this.a(Blocks.LADDER, 4), 9, 1, 3, structureboundingbox);
                this.a(world, Blocks.LADDER, this.a(Blocks.LADDER, 4), 9, 2, 3, structureboundingbox);
                this.a(world, Blocks.LADDER, this.a(Blocks.LADDER, 4), 9, 3, 3, structureboundingbox);
                this.a(world, structureboundingbox, random, 3, 4, 8, StructurePieceTreasure.a(b, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 1 + random.nextInt(4));
            }

            return true;
        }
    }
}
