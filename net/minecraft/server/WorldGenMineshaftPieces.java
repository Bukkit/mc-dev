package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftPieces {

    private static final StructurePieceTreasure[] a = new StructurePieceTreasure[] { new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.INK_SACK.id, 4, 4, 9, 5), new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 2, 3), new StructurePieceTreasure(Item.COAL.id, 0, 3, 8, 10), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 1), new StructurePieceTreasure(Block.RAILS.id, 0, 4, 8, 1), new StructurePieceTreasure(Item.MELON_SEEDS.id, 0, 2, 4, 10), new StructurePieceTreasure(Item.PUMPKIN_SEEDS.id, 0, 2, 4, 10)};

    public WorldGenMineshaftPieces() {}

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
        } else if (Math.abs(i - structurepiece.b().a) <= 80 && Math.abs(k - structurepiece.b().c) <= 80) {
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

    static StructurePieceTreasure[] a() {
        return a;
    }
}
