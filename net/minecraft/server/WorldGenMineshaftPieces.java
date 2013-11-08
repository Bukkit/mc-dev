package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftPieces {

    private static final StructurePieceTreasure[] a = new StructurePieceTreasure[] { new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 10), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 1, 3, 5), new StructurePieceTreasure(Items.REDSTONE, 0, 4, 9, 5), new StructurePieceTreasure(Items.INK_SACK, 4, 4, 9, 5), new StructurePieceTreasure(Items.DIAMOND, 0, 1, 2, 3), new StructurePieceTreasure(Items.COAL, 0, 3, 8, 10), new StructurePieceTreasure(Items.BREAD, 0, 1, 3, 15), new StructurePieceTreasure(Items.IRON_PICKAXE, 0, 1, 1, 1), new StructurePieceTreasure(Item.getItemOf(Blocks.RAILS), 0, 4, 8, 1), new StructurePieceTreasure(Items.MELON_SEEDS, 0, 2, 4, 10), new StructurePieceTreasure(Items.PUMPKIN_SEEDS, 0, 2, 4, 10), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 3), new StructurePieceTreasure(Items.HORSE_ARMOR_IRON, 0, 1, 1, 1)};

    public static void a() {
        WorldGenFactory.a(WorldGenMineshaftCorridor.class, "MSCorridor");
        WorldGenFactory.a(WorldGenMineshaftCross.class, "MSCrossing");
        WorldGenFactory.a(WorldGenMineshaftRoom.class, "MSRoom");
        WorldGenFactory.a(WorldGenMineshaftStairs.class, "MSStairs");
    }

    private static StructurePiece a(List list, Random random, int i, int j, int k, int l, int i1) {
        int j1 = random.nextInt(100);
        StructureBoundingBox structureboundingbox;

        if (j1 >= 80) {
            structureboundingbox = WorldGenMineshaftCross.a(list, random, i, j, k, l);
            if (structureboundingbox != null) {
                return new WorldGenMineshaftCross(i1, random, structureboundingbox, l);
            }
        } else if (j1 >= 70) {
            structureboundingbox = WorldGenMineshaftStairs.a(list, random, i, j, k, l);
            if (structureboundingbox != null) {
                return new WorldGenMineshaftStairs(i1, random, structureboundingbox, l);
            }
        } else {
            structureboundingbox = WorldGenMineshaftCorridor.a(list, random, i, j, k, l);
            if (structureboundingbox != null) {
                return new WorldGenMineshaftCorridor(i1, random, structureboundingbox, l);
            }
        }

        return null;
    }

    private static StructurePiece b(StructurePiece structurepiece, List list, Random random, int i, int j, int k, int l, int i1) {
        if (i1 > 8) {
            return null;
        } else if (Math.abs(i - structurepiece.c().a) <= 80 && Math.abs(k - structurepiece.c().c) <= 80) {
            StructurePiece structurepiece1 = a(list, random, i, j, k, l, i1 + 1);

            if (structurepiece1 != null) {
                list.add(structurepiece1);
                structurepiece1.a(structurepiece, list, random);
            }

            return structurepiece1;
        } else {
            return null;
        }
    }

    static StructurePiece a(StructurePiece structurepiece, List list, Random random, int i, int j, int k, int l, int i1) {
        return b(structurepiece, list, random, i, j, k, l, i1);
    }

    static StructurePieceTreasure[] b() {
        return a;
    }
}
