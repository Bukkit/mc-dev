package net.minecraft.server;

import java.util.Random;

abstract class WorldGenScatteredPiece extends StructurePiece {

    protected final int a;
    protected final int b;
    protected final int c;
    protected int d = -1;

    protected WorldGenScatteredPiece(Random random, int i, int j, int k, int l, int i1, int j1) {
        super(0);
        this.a = l;
        this.b = i1;
        this.c = j1;
        this.f = random.nextInt(4);
        switch (this.f) {
        case 0:
        case 2:
            this.e = new StructureBoundingBox(i, j, k, i + l - 1, j + i1 - 1, k + j1 - 1);
            break;

        default:
            this.e = new StructureBoundingBox(i, j, k, i + j1 - 1, j + i1 - 1, k + l - 1);
        }
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, int i) {
        if (this.d >= 0) {
            return true;
        } else {
            int j = 0;
            int k = 0;

            for (int l = this.e.c; l <= this.e.f; ++l) {
                for (int i1 = this.e.a; i1 <= this.e.d; ++i1) {
                    if (structureboundingbox.b(i1, 64, l)) {
                        j += Math.max(world.i(i1, l), world.worldProvider.getSeaLevel());
                        ++k;
                    }
                }
            }

            if (k == 0) {
                return false;
            } else {
                this.d = j / k;
                this.e.a(0, this.d - this.e.b + i, 0);
                return true;
            }
        }
    }
}
