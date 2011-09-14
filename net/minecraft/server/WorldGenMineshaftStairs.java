package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftStairs extends StructurePiece {

    public WorldGenMineshaftStairs(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j - 5, k, i, j + 2, k);

        switch (l) {
        case 0:
            structureboundingbox.d = i + 2;
            structureboundingbox.f = k + 8;
            break;

        case 1:
            structureboundingbox.a = i - 8;
            structureboundingbox.f = k + 2;
            break;

        case 2:
            structureboundingbox.d = i + 2;
            structureboundingbox.c = k - 8;
            break;

        case 3:
            structureboundingbox.d = i + 8;
            structureboundingbox.f = k + 2;
        }

        return StructurePiece.a(list, structureboundingbox) != null ? null : structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.c();

        switch (this.h) {
        case 0:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a, this.g.b, this.g.f + 1, 0, i);
            break;

        case 1:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b, this.g.c, 1, i);
            break;

        case 2:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a, this.g.b, this.g.c - 1, 2, i);
            break;

        case 3:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b, this.g.c, 3, i);
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 5, 0, 2, 7, 1, 0, 0, false);
            this.a(world, structureboundingbox, 0, 0, 7, 2, 2, 8, 0, 0, false);

            for (int i = 0; i < 5; ++i) {
                this.a(world, structureboundingbox, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, 0, 0, false);
            }

            return true;
        }
    }
}
