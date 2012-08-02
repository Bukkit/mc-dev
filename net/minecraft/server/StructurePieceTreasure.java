package net.minecraft.server;

import java.util.Random;

public class StructurePieceTreasure extends WeightedRandomChoice {

    private int b;
    private int c;
    private int d;
    private int e;

    public StructurePieceTreasure(int i, int j, int k, int l, int i1) {
        super(i1);
        this.b = i;
        this.c = j;
        this.d = k;
        this.e = l;
    }

    public static void a(Random random, StructurePieceTreasure[] astructurepiecetreasure, TileEntityChest tileentitychest, int i) {
        for (int j = 0; j < i; ++j) {
            StructurePieceTreasure structurepiecetreasure = (StructurePieceTreasure) WeightedRandom.a(random, (WeightedRandomChoice[]) astructurepiecetreasure);
            int k = structurepiecetreasure.d + random.nextInt(structurepiecetreasure.e - structurepiecetreasure.d + 1);

            if (Item.byId[structurepiecetreasure.b].getMaxStackSize() >= k) {
                tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), new ItemStack(structurepiecetreasure.b, k, structurepiecetreasure.c));
            } else {
                for (int l = 0; l < k; ++l) {
                    tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), new ItemStack(structurepiecetreasure.b, 1, structurepiecetreasure.c));
                }
            }
        }
    }

    public static void a(Random random, StructurePieceTreasure[] astructurepiecetreasure, TileEntityDispenser tileentitydispenser, int i) {
        for (int j = 0; j < i; ++j) {
            StructurePieceTreasure structurepiecetreasure = (StructurePieceTreasure) WeightedRandom.a(random, (WeightedRandomChoice[]) astructurepiecetreasure);
            int k = structurepiecetreasure.d + random.nextInt(structurepiecetreasure.e - structurepiecetreasure.d + 1);

            if (Item.byId[structurepiecetreasure.b].getMaxStackSize() >= k) {
                tileentitydispenser.setItem(random.nextInt(tileentitydispenser.getSize()), new ItemStack(structurepiecetreasure.b, k, structurepiecetreasure.c));
            } else {
                for (int l = 0; l < k; ++l) {
                    tileentitydispenser.setItem(random.nextInt(tileentitydispenser.getSize()), new ItemStack(structurepiecetreasure.b, 1, structurepiecetreasure.c));
                }
            }
        }
    }
}
