package net.minecraft.server;

import java.util.Random;

public class StructurePieceTreasure extends WeightedRandomChoice {

    private ItemStack b = null;
    private int c;
    private int d;

    public StructurePieceTreasure(int i, int j, int k, int l, int i1) {
        super(i1);
        this.b = new ItemStack(i, 1, j);
        this.c = k;
        this.d = l;
    }

    public StructurePieceTreasure(ItemStack itemstack, int i, int j, int k) {
        super(k);
        this.b = itemstack;
        this.c = i;
        this.d = j;
    }

    public static void a(Random random, StructurePieceTreasure[] astructurepiecetreasure, TileEntityChest tileentitychest, int i) {
        for (int j = 0; j < i; ++j) {
            StructurePieceTreasure structurepiecetreasure = (StructurePieceTreasure) WeightedRandom.a(random, (WeightedRandomChoice[]) astructurepiecetreasure);
            int k = structurepiecetreasure.c + random.nextInt(structurepiecetreasure.d - structurepiecetreasure.c + 1);

            if (structurepiecetreasure.b.getMaxStackSize() >= k) {
                ItemStack itemstack = structurepiecetreasure.b.cloneItemStack();

                itemstack.count = k;
                tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), itemstack);
            } else {
                for (int l = 0; l < k; ++l) {
                    ItemStack itemstack1 = structurepiecetreasure.b.cloneItemStack();

                    itemstack1.count = 1;
                    tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), itemstack1);
                }
            }
        }
    }

    public static void a(Random random, StructurePieceTreasure[] astructurepiecetreasure, TileEntityDispenser tileentitydispenser, int i) {
        for (int j = 0; j < i; ++j) {
            StructurePieceTreasure structurepiecetreasure = (StructurePieceTreasure) WeightedRandom.a(random, (WeightedRandomChoice[]) astructurepiecetreasure);
            int k = structurepiecetreasure.c + random.nextInt(structurepiecetreasure.d - structurepiecetreasure.c + 1);

            if (structurepiecetreasure.b.getMaxStackSize() >= k) {
                ItemStack itemstack = structurepiecetreasure.b.cloneItemStack();

                itemstack.count = k;
                tileentitydispenser.setItem(random.nextInt(tileentitydispenser.getSize()), itemstack);
            } else {
                for (int l = 0; l < k; ++l) {
                    ItemStack itemstack1 = structurepiecetreasure.b.cloneItemStack();

                    itemstack1.count = 1;
                    tileentitydispenser.setItem(random.nextInt(tileentitydispenser.getSize()), itemstack1);
                }
            }
        }
    }

    public static StructurePieceTreasure[] a(StructurePieceTreasure[] astructurepiecetreasure, StructurePieceTreasure... astructurepiecetreasure) {
        StructurePieceTreasure[] astructurepiecetreasure1 = new StructurePieceTreasure[astructurepiecetreasure.length + astructurepiecetreasure.length];
        int i = 0;

        for (int j = 0; j < astructurepiecetreasure.length; ++j) {
            astructurepiecetreasure1[i++] = astructurepiecetreasure[j];
        }

        StructurePieceTreasure[] astructurepiecetreasure2 = astructurepiecetreasure;
        int k = astructurepiecetreasure.length;

        for (int l = 0; l < k; ++l) {
            StructurePieceTreasure structurepiecetreasure = astructurepiecetreasure2[l];

            astructurepiecetreasure1[i++] = structurepiecetreasure;
        }

        return astructurepiecetreasure1;
    }
}
