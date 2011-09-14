package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageRoad extends WorldGenVillageRoadPiece {

    private int a;

    public WorldGenVillageRoad(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
        this.a = Math.max(structureboundingbox.b(), structureboundingbox.d());
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        boolean flag = false;

        int i;
        StructurePiece structurepiece1;

        for (i = random.nextInt(5); i < this.a - 8; i += 2 + random.nextInt(5)) {
            structurepiece1 = this.a((WorldGenVillageStartPiece) structurepiece, list, random, 0, i);
            if (structurepiece1 != null) {
                i += Math.max(structurepiece1.g.b(), structurepiece1.g.d());
                flag = true;
            }
        }

        for (i = random.nextInt(5); i < this.a - 8; i += 2 + random.nextInt(5)) {
            structurepiece1 = this.b((WorldGenVillageStartPiece) structurepiece, list, random, 0, i);
            if (structurepiece1 != null) {
                i += Math.max(structurepiece1.g.b(), structurepiece1.g.d());
                flag = true;
            }
        }

        if (flag && random.nextInt(3) > 0) {
            switch (this.h) {
            case 0:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.a - 1, this.g.b, this.g.f - 2, 1, this.c());
                break;

            case 1:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.a, this.g.b, this.g.c - 1, 2, this.c());
                break;

            case 2:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.a - 1, this.g.b, this.g.c, 1, this.c());
                break;

            case 3:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.d - 2, this.g.b, this.g.c - 1, 2, this.c());
            }
        }

        if (flag && random.nextInt(3) > 0) {
            switch (this.h) {
            case 0:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.d + 1, this.g.b, this.g.f - 2, 3, this.c());
                break;

            case 1:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.a, this.g.b, this.g.f + 1, 0, this.c());
                break;

            case 2:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.d + 1, this.g.b, this.g.c, 3, this.c());
                break;

            case 3:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.g.d - 2, this.g.b, this.g.f + 1, 0, this.c());
            }
        }
    }

    public static StructureBoundingBox a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l) {
        for (int i1 = 7 * MathHelper.a(random, 3, 5); i1 >= 7; i1 -= 7) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 3, 3, i1, l);

            if (StructurePiece.a(list, structureboundingbox) == null) {
                return structureboundingbox;
            }
        }

        return null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        for (int i = this.g.a; i <= this.g.d; ++i) {
            for (int j = this.g.c; j <= this.g.f; ++j) {
                if (structureboundingbox.b(i, 64, j)) {
                    int k = world.f(i, j) - 1;

                    world.setRawTypeId(i, k, j, Block.GRAVEL.id);
                }
            }
        }

        return true;
    }
}
