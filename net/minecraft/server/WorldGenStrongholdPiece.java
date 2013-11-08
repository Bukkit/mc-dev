package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenStrongholdPiece extends StructurePiece {

    protected WorldGenStrongholdDoorType d;

    public WorldGenStrongholdPiece() {
        this.d = WorldGenStrongholdDoorType.a;
    }

    protected WorldGenStrongholdPiece(int i) {
        super(i);
        this.d = WorldGenStrongholdDoorType.a;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setString("EntryDoor", this.d.name());
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.d = WorldGenStrongholdDoorType.valueOf(nbttagcompound.getString("EntryDoor"));
    }

    protected void a(World world, Random random, StructureBoundingBox structureboundingbox, WorldGenStrongholdDoorType worldgenstrongholddoortype, int i, int j, int k) {
        switch (WorldGenStrongholdPieceWeight3.a[worldgenstrongholddoortype.ordinal()]) {
        case 1:
        default:
            this.a(world, structureboundingbox, i, j, k, i + 3 - 1, j + 3 - 1, k, Blocks.AIR, Blocks.AIR, false);
            break;

        case 2:
            this.a(world, Blocks.SMOOTH_BRICK, 0, i, j, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i, j + 1, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i, j + 2, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 1, j + 2, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 2, j + 2, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 2, j + 1, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 2, j, k, structureboundingbox);
            this.a(world, Blocks.WOODEN_DOOR, 0, i + 1, j, k, structureboundingbox);
            this.a(world, Blocks.WOODEN_DOOR, 8, i + 1, j + 1, k, structureboundingbox);
            break;

        case 3:
            this.a(world, Blocks.AIR, 0, i + 1, j, k, structureboundingbox);
            this.a(world, Blocks.AIR, 0, i + 1, j + 1, k, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, i, j, k, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, i, j + 1, k, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, i, j + 2, k, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, i + 1, j + 2, k, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, i + 2, j + 2, k, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, i + 2, j + 1, k, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, i + 2, j, k, structureboundingbox);
            break;

        case 4:
            this.a(world, Blocks.SMOOTH_BRICK, 0, i, j, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i, j + 1, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i, j + 2, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 1, j + 2, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 2, j + 2, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 2, j + 1, k, structureboundingbox);
            this.a(world, Blocks.SMOOTH_BRICK, 0, i + 2, j, k, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR_BLOCK, 0, i + 1, j, k, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR_BLOCK, 8, i + 1, j + 1, k, structureboundingbox);
            this.a(world, Blocks.STONE_BUTTON, this.a(Blocks.STONE_BUTTON, 4), i + 2, j + 1, k + 1, structureboundingbox);
            this.a(world, Blocks.STONE_BUTTON, this.a(Blocks.STONE_BUTTON, 3), i + 2, j + 1, k - 1, structureboundingbox);
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

    protected StructurePiece a(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j) {
        switch (this.g) {
        case 0:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a + i, this.f.b + j, this.f.f + 1, this.g, this.d());

        case 1:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a - 1, this.f.b + j, this.f.c + i, this.g, this.d());

        case 2:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a + i, this.f.b + j, this.f.c - 1, this.g, this.d());

        case 3:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.d + 1, this.f.b + j, this.f.c + i, this.g, this.d());

        default:
            return null;
        }
    }

    protected StructurePiece b(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j) {
        switch (this.g) {
        case 0:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a - 1, this.f.b + i, this.f.c + j, 1, this.d());

        case 1:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a + j, this.f.b + i, this.f.c - 1, 2, this.d());

        case 2:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a - 1, this.f.b + i, this.f.c + j, 1, this.d());

        case 3:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a + j, this.f.b + i, this.f.c - 1, 2, this.d());

        default:
            return null;
        }
    }

    protected StructurePiece c(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j) {
        switch (this.g) {
        case 0:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.d + 1, this.f.b + i, this.f.c + j, 3, this.d());

        case 1:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a + j, this.f.b + i, this.f.f + 1, 0, this.d());

        case 2:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.d + 1, this.f.b + i, this.f.c + j, 3, this.d());

        case 3:
            return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.f.a + j, this.f.b + i, this.f.f + 1, 0, this.d());

        default:
            return null;
        }
    }

    protected static boolean a(StructureBoundingBox structureboundingbox) {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
