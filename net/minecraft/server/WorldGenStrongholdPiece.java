package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenStrongholdPiece extends StructurePiece {

    protected WorldGenStrongholdPiece(int i) {
        super(i);
    }

    protected void a(World world, Random random, StructureBoundingBox structureboundingbox, WorldGenStrongholdDoorType worldgenstrongholddoortype, int i, int j, int k) {
        switch (WorldGenStrongholdPieceWeight2.a[worldgenstrongholddoortype.ordinal()]) {
        case 1:
        default:
            this.a(world, structureboundingbox, i, j, k, i + 3 - 1, j + 3 - 1, k, 0, 0, false);
            break;

        case 2:
            this.a(world, Block.SMOOTH_BRICK.id, 0, i, j, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i, j + 1, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i, j + 2, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 1, j + 2, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 2, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 1, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j, k, structureboundingbox);
            this.a(world, Block.WOODEN_DOOR.id, 0, i + 1, j, k, structureboundingbox);
            this.a(world, Block.WOODEN_DOOR.id, 8, i + 1, j + 1, k, structureboundingbox);
            break;

        case 3:
            this.a(world, 0, 0, i + 1, j, k, structureboundingbox);
            this.a(world, 0, 0, i + 1, j + 1, k, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, i, j, k, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, i, j + 1, k, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, i, j + 2, k, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, i + 1, j + 2, k, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, i + 2, j + 2, k, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, i + 2, j + 1, k, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, i + 2, j, k, structureboundingbox);
            break;

        case 4:
            this.a(world, Block.SMOOTH_BRICK.id, 0, i, j, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i, j + 1, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i, j + 2, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 1, j + 2, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 2, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 1, k, structureboundingbox);
            this.a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j, k, structureboundingbox);
            this.a(world, Block.IRON_DOOR_BLOCK.id, 0, i + 1, j, k, structureboundingbox);
            this.a(world, Block.IRON_DOOR_BLOCK.id, 8, i + 1, j + 1, k, structureboundingbox);
            this.a(world, Block.STONE_BUTTON.id, this.c(Block.STONE_BUTTON.id, 4), i + 2, j + 1, k + 1, structureboundingbox);
            this.a(world, Block.STONE_BUTTON.id, this.c(Block.STONE_BUTTON.id, 3), i + 2, j + 1, k - 1, structureboundingbox);
        }
    }

    protected WorldGenStrongholdDoorType a(Random random) {
        int i = random.nextInt(5);

        switch (i) {
        case 0:
        case 1:
        default:
            return WorldGenStrongholdDoorType.a;

        case 2:
            return WorldGenStrongholdDoorType.b;

        case 3:
            return WorldGenStrongholdDoorType.c;

        case 4:
            return WorldGenStrongholdDoorType.d;
        }
    }

    protected StructurePiece a(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j) {
        switch (this.h) {
        case 0:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a + i, this.g.b + j, this.g.f + 1, this.h, this.c());

        case 1:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a - 1, this.g.b + j, this.g.c + i, this.h, this.c());

        case 2:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a + i, this.g.b + j, this.g.c - 1, this.h, this.c());

        case 3:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.d + 1, this.g.b + j, this.g.c + i, this.h, this.c());

        default:
            return null;
        }
    }

    protected StructurePiece b(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j) {
        switch (this.h) {
        case 0:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a - 1, this.g.b + i, this.g.c + j, 1, this.c());

        case 1:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a + j, this.g.b + i, this.g.c - 1, 2, this.c());

        case 2:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a - 1, this.g.b + i, this.g.c + j, 1, this.c());

        case 3:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a + j, this.g.b + i, this.g.c - 1, 2, this.c());

        default:
            return null;
        }
    }

    protected StructurePiece c(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j) {
        switch (this.h) {
        case 0:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.d + 1, this.g.b + i, this.g.c + j, 3, this.c());

        case 1:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a + j, this.g.b + i, this.g.f + 1, 0, this.c());

        case 2:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.d + 1, this.g.b + i, this.g.c + j, 3, this.c());

        case 3:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, this.g.a + j, this.g.b + i, this.g.f + 1, 0, this.c());

        default:
            return null;
        }
    }

    protected static boolean a(StructureBoundingBox structureboundingbox) {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
