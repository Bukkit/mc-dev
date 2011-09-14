package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPrison extends WorldGenStrongholdPiece {

    protected final WorldGenStrongholdDoorType a;

    public WorldGenStrongholdPrison(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.a = this.a(random);
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdPrison a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 9, 5, 11, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPrison(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 8, 4, 10, true, random, WorldGenStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.a, 1, 1, 0);
            this.a(world, structureboundingbox, 1, 1, 10, 3, 3, 10, 0, 0, false);
            this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 1, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 4, 1, 3, 4, 3, 3, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 4, 1, 7, 4, 3, 7, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 4, 1, 9, 4, 3, 9, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 4, 1, 4, 4, 3, 6, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
            this.a(world, structureboundingbox, 5, 1, 5, 7, 3, 5, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
            this.a(world, Block.IRON_FENCE.id, 0, 4, 3, 2, structureboundingbox);
            this.a(world, Block.IRON_FENCE.id, 0, 4, 3, 8, structureboundingbox);
            this.a(world, Block.IRON_DOOR_BLOCK.id, this.c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 2, structureboundingbox);
            this.a(world, Block.IRON_DOOR_BLOCK.id, this.c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 2, structureboundingbox);
            this.a(world, Block.IRON_DOOR_BLOCK.id, this.c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 8, structureboundingbox);
            this.a(world, Block.IRON_DOOR_BLOCK.id, this.c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 8, structureboundingbox);
            return true;
        }
    }
}
