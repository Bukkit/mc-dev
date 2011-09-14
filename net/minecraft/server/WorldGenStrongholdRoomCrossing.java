package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdRoomCrossing extends WorldGenStrongholdPiece {

    private static final StructurePieceTreasure[] c = new StructurePieceTreasure[] { new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.COAL.id, 0, 3, 8, 10), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.APPLE.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 1)};
    protected final WorldGenStrongholdDoorType a;
    protected final int b;

    public WorldGenStrongholdRoomCrossing(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.a = this.a(random);
        this.g = structureboundingbox;
        this.b = random.nextInt(5);
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStairs2) structurepiece, list, random, 4, 1);
        this.b((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 4);
        this.c((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 4);
    }

    public static WorldGenStrongholdRoomCrossing a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 11, 7, 11, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdRoomCrossing(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 10, 6, 10, true, random, WorldGenStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.a, 4, 1, 0);
            this.a(world, structureboundingbox, 4, 1, 10, 6, 3, 10, 0, 0, false);
            this.a(world, structureboundingbox, 0, 1, 4, 0, 3, 6, 0, 0, false);
            this.a(world, structureboundingbox, 10, 1, 4, 10, 3, 6, 0, 0, false);
            int i;

            switch (this.b) {
            case 0:
                this.a(world, Block.SMOOTH_BRICK.id, 0, 5, 1, 5, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 5, 2, 5, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 5, 3, 5, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, 4, 3, 5, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, 6, 3, 5, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, 5, 3, 4, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, 5, 3, 6, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 4, 1, 4, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 4, 1, 5, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 4, 1, 6, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 6, 1, 4, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 6, 1, 5, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 6, 1, 6, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 5, 1, 4, structureboundingbox);
                this.a(world, Block.STEP.id, 0, 5, 1, 6, structureboundingbox);
                break;

            case 1:
                for (i = 0; i < 5; ++i) {
                    this.a(world, Block.SMOOTH_BRICK.id, 0, 3, 1, 3 + i, structureboundingbox);
                    this.a(world, Block.SMOOTH_BRICK.id, 0, 7, 1, 3 + i, structureboundingbox);
                    this.a(world, Block.SMOOTH_BRICK.id, 0, 3 + i, 1, 3, structureboundingbox);
                    this.a(world, Block.SMOOTH_BRICK.id, 0, 3 + i, 1, 7, structureboundingbox);
                }

                this.a(world, Block.SMOOTH_BRICK.id, 0, 5, 1, 5, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 5, 2, 5, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 5, 3, 5, structureboundingbox);
                this.a(world, Block.WATER.id, 0, 5, 4, 5, structureboundingbox);
                break;

            case 2:
                for (i = 1; i <= 9; ++i) {
                    this.a(world, Block.COBBLESTONE.id, 0, 1, 3, i, structureboundingbox);
                    this.a(world, Block.COBBLESTONE.id, 0, 9, 3, i, structureboundingbox);
                }

                for (i = 1; i <= 9; ++i) {
                    this.a(world, Block.COBBLESTONE.id, 0, i, 3, 1, structureboundingbox);
                    this.a(world, Block.COBBLESTONE.id, 0, i, 3, 9, structureboundingbox);
                }

                this.a(world, Block.COBBLESTONE.id, 0, 5, 1, 4, structureboundingbox);
                this.a(world, Block.COBBLESTONE.id, 0, 5, 1, 6, structureboundingbox);
                this.a(world, Block.COBBLESTONE.id, 0, 5, 3, 4, structureboundingbox);
                this.a(world, Block.COBBLESTONE.id, 0, 5, 3, 6, structureboundingbox);
                this.a(world, Block.COBBLESTONE.id, 0, 4, 1, 5, structureboundingbox);
                this.a(world, Block.COBBLESTONE.id, 0, 6, 1, 5, structureboundingbox);
                this.a(world, Block.COBBLESTONE.id, 0, 4, 3, 5, structureboundingbox);
                this.a(world, Block.COBBLESTONE.id, 0, 6, 3, 5, structureboundingbox);

                for (i = 1; i <= 3; ++i) {
                    this.a(world, Block.COBBLESTONE.id, 0, 4, i, 4, structureboundingbox);
                    this.a(world, Block.COBBLESTONE.id, 0, 6, i, 4, structureboundingbox);
                    this.a(world, Block.COBBLESTONE.id, 0, 4, i, 6, structureboundingbox);
                    this.a(world, Block.COBBLESTONE.id, 0, 6, i, 6, structureboundingbox);
                }

                this.a(world, Block.TORCH.id, 0, 5, 3, 5, structureboundingbox);

                for (i = 2; i <= 8; ++i) {
                    this.a(world, Block.WOOD.id, 0, 2, 3, i, structureboundingbox);
                    this.a(world, Block.WOOD.id, 0, 3, 3, i, structureboundingbox);
                    if (i <= 3 || i >= 7) {
                        this.a(world, Block.WOOD.id, 0, 4, 3, i, structureboundingbox);
                        this.a(world, Block.WOOD.id, 0, 5, 3, i, structureboundingbox);
                        this.a(world, Block.WOOD.id, 0, 6, 3, i, structureboundingbox);
                    }

                    this.a(world, Block.WOOD.id, 0, 7, 3, i, structureboundingbox);
                    this.a(world, Block.WOOD.id, 0, 8, 3, i, structureboundingbox);
                }

                this.a(world, Block.LADDER.id, this.c(Block.LADDER.id, 4), 9, 1, 3, structureboundingbox);
                this.a(world, Block.LADDER.id, this.c(Block.LADDER.id, 4), 9, 2, 3, structureboundingbox);
                this.a(world, Block.LADDER.id, this.c(Block.LADDER.id, 4), 9, 3, 3, structureboundingbox);
                this.a(world, structureboundingbox, random, 3, 4, 8, c, 1 + random.nextInt(4));
            }

            return true;
        }
    }
}
