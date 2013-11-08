package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageRoad extends WorldGenVillageRoadPiece {

    private int a;

    public WorldGenVillageRoad() {}

    public WorldGenVillageRoad(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(worldgenvillagestartpiece, i);
        this.g = j;
        this.f = structureboundingbox;
        this.a = Math.max(structureboundingbox.b(), structureboundingbox.d());
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("Length", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getInt("Length");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        boolean flag = false;

        int i;
        StructurePiece structurepiece1;

        for (i = random.nextInt(5); i < this.a - 8; i += 2 + random.nextInt(5)) {
            structurepiece1 = this.a((WorldGenVillageStartPiece) structurepiece, list, random, 0, i);
            if (structurepiece1 != null) {
                i += Math.max(structurepiece1.f.b(), structurepiece1.f.d());
                flag = true;
            }
        }

        for (i = random.nextInt(5); i < this.a - 8; i += 2 + random.nextInt(5)) {
            structurepiece1 = this.b((WorldGenVillageStartPiece) structurepiece, list, random, 0, i);
            if (structurepiece1 != null) {
                i += Math.max(structurepiece1.f.b(), structurepiece1.f.d());
                flag = true;
            }
        }

        if (flag && random.nextInt(3) > 0) {
            switch (this.g) {
            case 0:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.a - 1, this.f.b, this.f.f - 2, 1, this.d());
                break;

            case 1:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.a, this.f.b, this.f.c - 1, 2, this.d());
                break;

            case 2:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.a - 1, this.f.b, this.f.c, 1, this.d());
                break;

            case 3:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.d - 2, this.f.b, this.f.c - 1, 2, this.d());
            }
        }

        if (flag && random.nextInt(3) > 0) {
            switch (this.g) {
            case 0:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.d + 1, this.f.b, this.f.f - 2, 3, this.d());
                break;

            case 1:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.a, this.f.b, this.f.f + 1, 0, this.d());
                break;

            case 2:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.d + 1, this.f.b, this.f.c, 3, this.d());
                break;

            case 3:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.f.d - 2, this.f.b, this.f.f + 1, 0, this.d());
            }
        }
    }

    public static StructureBoundingBox a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l) {
        for (int i1 = 7 * MathHelper.nextInt(random, 3, 5); i1 >= 7; i1 -= 7) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 3, 3, i1, l);

            if (StructurePiece.a(list, structureboundingbox) == null) {
                return structureboundingbox;
            }
        }

        return null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        Block block = this.b(Blocks.GRAVEL, 0);

        for (int i = this.f.a; i <= this.f.d; ++i) {
            for (int j = this.f.c; j <= this.f.f; ++j) {
                if (structureboundingbox.b(i, 64, j)) {
                    int k = world.i(i, j) - 1;

                    world.setTypeAndData(i, k, j, block, 0, 2);
                }
            }
        }

        return true;
    }
}
