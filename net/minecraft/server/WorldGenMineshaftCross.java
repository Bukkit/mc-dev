package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCross extends StructurePiece {

    private final int a;
    private final boolean b;

    public WorldGenMineshaftCross(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.a = j;
        this.e = structureboundingbox;
        this.b = structureboundingbox.c() > 3;
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);

        if (random.nextInt(4) == 0) {
            structureboundingbox.e += 4;
        }

        switch (l) {
        case 0:
            structureboundingbox.a = i - 1;
            structureboundingbox.d = i + 3;
            structureboundingbox.f = k + 4;
            break;

        case 1:
            structureboundingbox.a = i - 4;
            structureboundingbox.c = k - 1;
            structureboundingbox.f = k + 3;
            break;

        case 2:
            structureboundingbox.a = i - 1;
            structureboundingbox.d = i + 3;
            structureboundingbox.c = k - 4;
            break;

        case 3:
            structureboundingbox.d = i + 4;
            structureboundingbox.c = k - 1;
            structureboundingbox.f = k + 3;
        }

        return StructurePiece.a(list, structureboundingbox) != null ? null : structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.c();

        switch (this.a) {
        case 0:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b, this.e.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a - 1, this.e.b, this.e.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.d + 1, this.e.b, this.e.c + 1, 3, i);
            break;

        case 1:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b, this.e.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b, this.e.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a - 1, this.e.b, this.e.c + 1, 1, i);
            break;

        case 2:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b, this.e.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a - 1, this.e.b, this.e.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.d + 1, this.e.b, this.e.c + 1, 3, i);
            break;

        case 3:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b, this.e.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b, this.e.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.d + 1, this.e.b, this.e.c + 1, 3, i);
        }

        if (this.b) {
            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b + 3 + 1, this.e.c - 1, 2, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a - 1, this.e.b + 3 + 1, this.e.c + 1, 1, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.d + 1, this.e.b + 3 + 1, this.e.c + 1, 3, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + 1, this.e.b + 3 + 1, this.e.f + 1, 0, i);
            }
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            if (this.b) {
                this.a(world, structureboundingbox, this.e.a + 1, this.e.b, this.e.c, this.e.d - 1, this.e.b + 3 - 1, this.e.f, 0, 0, false);
                this.a(world, structureboundingbox, this.e.a, this.e.b, this.e.c + 1, this.e.d, this.e.b + 3 - 1, this.e.f - 1, 0, 0, false);
                this.a(world, structureboundingbox, this.e.a + 1, this.e.e - 2, this.e.c, this.e.d - 1, this.e.e, this.e.f, 0, 0, false);
                this.a(world, structureboundingbox, this.e.a, this.e.e - 2, this.e.c + 1, this.e.d, this.e.e, this.e.f - 1, 0, 0, false);
                this.a(world, structureboundingbox, this.e.a + 1, this.e.b + 3, this.e.c + 1, this.e.d - 1, this.e.b + 3, this.e.f - 1, 0, 0, false);
            } else {
                this.a(world, structureboundingbox, this.e.a + 1, this.e.b, this.e.c, this.e.d - 1, this.e.e, this.e.f, 0, 0, false);
                this.a(world, structureboundingbox, this.e.a, this.e.b, this.e.c + 1, this.e.d, this.e.e, this.e.f - 1, 0, 0, false);
            }

            this.a(world, structureboundingbox, this.e.a + 1, this.e.b, this.e.c + 1, this.e.a + 1, this.e.e, this.e.c + 1, Block.WOOD.id, 0, false);
            this.a(world, structureboundingbox, this.e.a + 1, this.e.b, this.e.f - 1, this.e.a + 1, this.e.e, this.e.f - 1, Block.WOOD.id, 0, false);
            this.a(world, structureboundingbox, this.e.d - 1, this.e.b, this.e.c + 1, this.e.d - 1, this.e.e, this.e.c + 1, Block.WOOD.id, 0, false);
            this.a(world, structureboundingbox, this.e.d - 1, this.e.b, this.e.f - 1, this.e.d - 1, this.e.e, this.e.f - 1, Block.WOOD.id, 0, false);

            for (int i = this.e.a; i <= this.e.d; ++i) {
                for (int j = this.e.c; j <= this.e.f; ++j) {
                    int k = this.a(world, i, this.e.b - 1, j, structureboundingbox);

                    if (k == 0) {
                        this.a(world, Block.WOOD.id, 0, i, this.e.b - 1, j, structureboundingbox);
                    }
                }
            }

            return true;
        }
    }
}
