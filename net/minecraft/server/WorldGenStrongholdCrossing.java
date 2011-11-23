package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdCrossing extends WorldGenStrongholdPiece {

    protected final WorldGenStrongholdDoorType a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;

    public WorldGenStrongholdCrossing(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.a = this.a(random);
        this.g = structureboundingbox;
        this.b = random.nextBoolean();
        this.c = random.nextBoolean();
        this.d = random.nextBoolean();
        this.e = random.nextInt(3) > 0;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = 3;
        int j = 5;

        if (this.h == 1 || this.h == 2) {
            i = 8 - i;
            j = 8 - j;
        }

        this.a((WorldGenStrongholdStairs2) structurepiece, list, random, 5, 1);
        if (this.b) {
            this.b((WorldGenStrongholdStairs2) structurepiece, list, random, i, 1);
        }

        if (this.c) {
            this.b((WorldGenStrongholdStairs2) structurepiece, list, random, j, 7);
        }

        if (this.d) {
            this.c((WorldGenStrongholdStairs2) structurepiece, list, random, i, 1);
        }

        if (this.e) {
            this.c((WorldGenStrongholdStairs2) structurepiece, list, random, j, 7);
        }
    }

    public static WorldGenStrongholdCrossing a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -3, 0, 10, 9, 11, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdCrossing(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 9, 8, 10, true, random, WorldGenStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.a, 4, 3, 0);
            if (this.b) {
                this.a(world, structureboundingbox, 0, 3, 1, 0, 5, 3, 0, 0, false);
            }

            if (this.d) {
                this.a(world, structureboundingbox, 9, 3, 1, 9, 5, 3, 0, 0, false);
            }

            if (this.c) {
                this.a(world, structureboundingbox, 0, 5, 7, 0, 7, 9, 0, 0, false);
            }

            if (this.e) {
                this.a(world, structureboundingbox, 9, 5, 7, 9, 7, 9, 0, 0, false);
            }

            this.a(world, structureboundingbox, 5, 1, 10, 7, 3, 10, 0, 0, false);
            this.a(world, structureboundingbox, 1, 2, 1, 8, 2, 6, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 4, 1, 5, 4, 4, 9, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 8, 1, 5, 8, 4, 9, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 1, 4, 7, 3, 4, 9, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 1, 3, 5, 3, 3, 6, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 1, 3, 4, 3, 3, 4, Block.STEP.id, Block.STEP.id, false);
            this.a(world, structureboundingbox, 1, 4, 6, 3, 4, 6, Block.STEP.id, Block.STEP.id, false);
            this.a(world, structureboundingbox, 5, 1, 7, 7, 1, 8, false, random, WorldGenStrongholdPieces.b());
            this.a(world, structureboundingbox, 5, 1, 9, 7, 1, 9, Block.STEP.id, Block.STEP.id, false);
            this.a(world, structureboundingbox, 5, 2, 7, 7, 2, 7, Block.STEP.id, Block.STEP.id, false);
            this.a(world, structureboundingbox, 4, 5, 7, 4, 5, 9, Block.STEP.id, Block.STEP.id, false);
            this.a(world, structureboundingbox, 8, 5, 7, 8, 5, 9, Block.STEP.id, Block.STEP.id, false);
            this.a(world, structureboundingbox, 5, 5, 7, 7, 5, 9, Block.DOUBLE_STEP.id, Block.DOUBLE_STEP.id, false);
            this.a(world, Block.TORCH.id, 0, 6, 5, 6, structureboundingbox);
            return true;
        }
    }
}
