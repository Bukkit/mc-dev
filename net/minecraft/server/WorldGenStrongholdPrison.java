package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPrison extends WorldGenStrongholdPiece {

    public WorldGenStrongholdPrison() {}

    public WorldGenStrongholdPrison(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.d = this.a(random);
        this.f = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdPrison a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 9, 5, 11, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPrison(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 8, 4, 10, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 1, 1, 0);
            this.a(world, structureboundingbox, 1, 1, 10, 3, 3, 10, Blocks.AIR, Blocks.AIR, false);
            this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 1, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 4, 1, 3, 4, 3, 3, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 4, 1, 7, 4, 3, 7, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 4, 1, 9, 4, 3, 9, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 4, 1, 4, 4, 3, 6, Blocks.IRON_FENCE, Blocks.IRON_FENCE, false);
            this.a(world, structureboundingbox, 5, 1, 5, 7, 3, 5, Blocks.IRON_FENCE, Blocks.IRON_FENCE, false);
            this.a(world, Blocks.IRON_FENCE, 0, 4, 3, 2, structureboundingbox);
            this.a(world, Blocks.IRON_FENCE, 0, 4, 3, 8, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR_BLOCK, this.a(Blocks.IRON_DOOR_BLOCK, 3), 4, 1, 2, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR_BLOCK, this.a(Blocks.IRON_DOOR_BLOCK, 3) + 8, 4, 2, 2, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR_BLOCK, this.a(Blocks.IRON_DOOR_BLOCK, 3), 4, 1, 8, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR_BLOCK, this.a(Blocks.IRON_DOOR_BLOCK, 3) + 8, 4, 2, 8, structureboundingbox);
            return true;
        }
    }
}
