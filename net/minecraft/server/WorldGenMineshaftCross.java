package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCross extends StructurePiece {

    private final int a;
    private final boolean b;

    public WorldGenMineshaftCross(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.a = j;
        this.g = structureboundingbox;
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
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b, this.g.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b, this.g.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b, this.g.c + 1, 3, i);
            break;

        case 1:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b, this.g.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b, this.g.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b, this.g.c + 1, 1, i);
            break;

        case 2:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b, this.g.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b, this.g.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b, this.g.c + 1, 3, i);
            break;

        case 3:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b, this.g.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b, this.g.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b, this.g.c + 1, 3, i);
        }

        if (this.b) {
            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b + 3 + 1, this.g.c - 1, 2, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b + 3 + 1, this.g.c + 1, 1, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b + 3 + 1, this.g.c + 1, 3, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + 1, this.g.b + 3 + 1, this.g.f + 1, 0, i);
            }
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            if (this.b) {
                this.a(world, structureboundingbox, this.g.a + 1, this.g.b, this.g.c, this.g.d - 1, this.g.b + 3 - 1, this.g.f, 0, 0, false);
                this.a(world, structureboundingbox, this.g.a, this.g.b, this.g.c + 1, this.g.d, this.g.b + 3 - 1, this.g.f - 1, 0, 0, false);
                this.a(world, structureboundingbox, this.g.a + 1, this.g.e - 2, this.g.c, this.g.d - 1, this.g.e, this.g.f, 0, 0, false);
                this.a(world, structureboundingbox, this.g.a, this.g.e - 2, this.g.c + 1, this.g.d, this.g.e, this.g.f - 1, 0, 0, false);
                this.a(world, structureboundingbox, this.g.a + 1, this.g.b + 3, this.g.c + 1, this.g.d - 1, this.g.b + 3, this.g.f - 1, 0, 0, false);
            } else {
                this.a(world, structureboundingbox, this.g.a + 1, this.g.b, this.g.c, this.g.d - 1, this.g.e, this.g.f, 0, 0, false);
                this.a(world, structureboundingbox, this.g.a, this.g.b, this.g.c + 1, this.g.d, this.g.e, this.g.f - 1, 0, 0, false);
            }

            this.a(world, structureboundingbox, this.g.a + 1, this.g.b, this.g.c + 1, this.g.a + 1, this.g.e, this.g.c + 1, Block.WOOD.id, 0, false);
            this.a(world, structureboundingbox, this.g.a + 1, this.g.b, this.g.f - 1, this.g.a + 1, this.g.e, this.g.f - 1, Block.WOOD.id, 0, false);
            this.a(world, structureboundingbox, this.g.d - 1, this.g.b, this.g.c + 1, this.g.d - 1, this.g.e, this.g.c + 1, Block.WOOD.id, 0, false);
            this.a(world, structureboundingbox, this.g.d - 1, this.g.b, this.g.f - 1, this.g.d - 1, this.g.e, this.g.f - 1, Block.WOOD.id, 0, false);
            return true;
        }
    }
}
