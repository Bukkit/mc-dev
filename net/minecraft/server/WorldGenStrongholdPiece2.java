package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPiece2 extends WorldGenStrongholdPiece {

    private static final StructurePieceTreasure[] a = new StructurePieceTreasure[] { new StructurePieceTreasure(Item.ENDER_PEARL.id, 0, 1, 1, 10), new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.APPLE.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_SWORD.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_CHESTPLATE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_HELMET.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_LEGGINGS.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_BOOTS.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.GOLDEN_APPLE.id, 0, 1, 1, 1)};
    private final WorldGenStrongholdDoorType b;
    private boolean c;

    public WorldGenStrongholdPiece2(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.b = this.a(random);
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdPiece2 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 7, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPiece2(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 4, 6, true, random, WorldGenStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.b, 1, 1, 0);
            this.a(world, random, structureboundingbox, WorldGenStrongholdDoorType.a, 1, 1, 6);
            this.a(world, structureboundingbox, 3, 1, 2, 3, 1, 4, Block.SMOOTH_BRICK.id, Block.SMOOTH_BRICK.id, false);
            this.a(world, Block.STEP.id, 5, 3, 1, 1, structureboundingbox);
            this.a(world, Block.STEP.id, 5, 3, 1, 5, structureboundingbox);
            this.a(world, Block.STEP.id, 5, 3, 2, 2, structureboundingbox);
            this.a(world, Block.STEP.id, 5, 3, 2, 4, structureboundingbox);

            int i;

            for (i = 2; i <= 4; ++i) {
                this.a(world, Block.STEP.id, 5, 2, 1, i, structureboundingbox);
            }

            if (!this.c) {
                i = this.b(2);
                int j = this.a(3, 3);
                int k = this.b(3, 3);

                if (structureboundingbox.b(j, i, k)) {
                    this.c = true;
                    this.a(world, structureboundingbox, random, 3, 2, 3, a, 2 + random.nextInt(2));
                }
            }

            return true;
        }
    }
}
