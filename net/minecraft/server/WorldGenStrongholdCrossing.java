package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdCrossing extends WorldGenStrongholdPiece {

    private boolean a;
    private boolean b;
    private boolean c;
    private boolean e;

    public WorldGenStrongholdCrossing() {}

    public WorldGenStrongholdCrossing(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.d = this.a(random);
        this.f = structureboundingbox;
        this.a = random.nextBoolean();
        this.b = random.nextBoolean();
        this.c = random.nextBoolean();
        this.e = random.nextInt(3) > 0;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("leftLow", this.a);
        nbttagcompound.setBoolean("leftHigh", this.b);
        nbttagcompound.setBoolean("rightLow", this.c);
        nbttagcompound.setBoolean("rightHigh", this.e);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getBoolean("leftLow");
        this.b = nbttagcompound.getBoolean("leftHigh");
        this.c = nbttagcompound.getBoolean("rightLow");
        this.e = nbttagcompound.getBoolean("rightHigh");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = 3;
        int j = 5;

        if (this.g == 1 || this.g == 2) {
            i = 8 - i;
            j = 8 - j;
        }

        this.a((WorldGenStrongholdStart) structurepiece, list, random, 5, 1);
        if (this.a) {
            this.b((WorldGenStrongholdStart) structurepiece, list, random, i, 1);
        }

        if (this.b) {
            this.b((WorldGenStrongholdStart) structurepiece, list, random, j, 7);
        }

        if (this.c) {
            this.c((WorldGenStrongholdStart) structurepiece, list, random, i, 1);
        }

        if (this.e) {
            this.c((WorldGenStrongholdStart) structurepiece, list, random, j, 7);
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
            this.a(world, structureboundingbox, 0, 0, 0, 9, 8, 10, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 4, 3, 0);
            if (this.a) {
                this.a(world, structureboundingbox, 0, 3, 1, 0, 5, 3, Blocks.AIR, Blocks.AIR, false);
            }

            if (this.c) {
                this.a(world, structureboundingbox, 9, 3, 1, 9, 5, 3, Blocks.AIR, Blocks.AIR, false);
            }

            if (this.b) {
                this.a(world, structureboundingbox, 0, 5, 7, 0, 7, 9, Blocks.AIR, Blocks.AIR, false);
            }

            if (this.e) {
                this.a(world, structureboundingbox, 9, 5, 7, 9, 7, 9, Blocks.AIR, Blocks.AIR, false);
            }

            this.a(world, structureboundingbox, 5, 1, 10, 7, 3, 10, Blocks.AIR, Blocks.AIR, false);
            this.a(world, structureboundingbox, 1, 2, 1, 8, 2, 6, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 4, 1, 5, 4, 4, 9, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 8, 1, 5, 8, 4, 9, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 1, 4, 7, 3, 4, 9, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 1, 3, 5, 3, 3, 6, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 1, 3, 4, 3, 3, 4, Blocks.STEP, Blocks.STEP, false);
            this.a(world, structureboundingbox, 1, 4, 6, 3, 4, 6, Blocks.STEP, Blocks.STEP, false);
            this.a(world, structureboundingbox, 5, 1, 7, 7, 1, 8, false, random, WorldGenStrongholdPieces.c());
            this.a(world, structureboundingbox, 5, 1, 9, 7, 1, 9, Blocks.STEP, Blocks.STEP, false);
            this.a(world, structureboundingbox, 5, 2, 7, 7, 2, 7, Blocks.STEP, Blocks.STEP, false);
            this.a(world, structureboundingbox, 4, 5, 7, 4, 5, 9, Blocks.STEP, Blocks.STEP, false);
            this.a(world, structureboundingbox, 8, 5, 7, 8, 5, 9, Blocks.STEP, Blocks.STEP, false);
            this.a(world, structureboundingbox, 5, 5, 7, 7, 5, 9, Blocks.DOUBLE_STEP, Blocks.DOUBLE_STEP, false);
            this.a(world, Blocks.TORCH, 0, 6, 5, 6, structureboundingbox);
            return true;
        }
    }
}
