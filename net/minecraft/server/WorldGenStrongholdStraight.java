package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdStraight extends WorldGenStrongholdPiece {

    private final WorldGenStrongholdDoorType a;
    private final boolean b;
    private final boolean c;

    public WorldGenStrongholdStraight(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.a = this.a(random);
        this.g = structureboundingbox;
        this.b = random.nextInt(2) == 0;
        this.c = random.nextInt(2) == 0;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 1);
        if (this.b) {
            this.b((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 2);
        }

        if (this.c) {
            this.c((WorldGenStrongholdStairs2) structurepiece, list, random, 1, 2);
        }
    }

    public static WorldGenStrongholdStraight a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 7, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdStraight(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 4, 6, true, random, WorldGenStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.a, 1, 1, 0);
            this.a(world, random, structureboundingbox, WorldGenStrongholdDoorType.a, 1, 1, 6);
            this.a(world, structureboundingbox, random, 0.1F, 1, 2, 1, Block.TORCH.id, 0);
            this.a(world, structureboundingbox, random, 0.1F, 3, 2, 1, Block.TORCH.id, 0);
            this.a(world, structureboundingbox, random, 0.1F, 1, 2, 5, Block.TORCH.id, 0);
            this.a(world, structureboundingbox, random, 0.1F, 3, 2, 5, Block.TORCH.id, 0);
            if (this.b) {
                this.a(world, structureboundingbox, 0, 1, 2, 0, 3, 4, 0, 0, false);
            }

            if (this.c) {
                this.a(world, structureboundingbox, 4, 1, 2, 4, 3, 4, 0, 0, false);
            }

            return true;
        }
    }
}
