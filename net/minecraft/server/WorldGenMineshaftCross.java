package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCross extends StructurePiece {

    private int a;
    private boolean b;

    public WorldGenMineshaftCross() {}

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setBoolean("tf", this.b);
        nbttagcompound.setInt("D", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.b = nbttagcompound.getBoolean("tf");
        this.a = nbttagcompound.getInt("D");
    }

    public WorldGenMineshaftCross(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.a = j;
        this.f = structureboundingbox;
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
        int i = this.d();

        switch (this.a) {
        case 0:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b, this.f.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b, this.f.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b, this.f.c + 1, 3, i);
            break;

        case 1:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b, this.f.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b, this.f.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b, this.f.c + 1, 1, i);
            break;

        case 2:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b, this.f.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b, this.f.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b, this.f.c + 1, 3, i);
            break;

        case 3:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b, this.f.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b, this.f.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b, this.f.c + 1, 3, i);
        }

        if (this.b) {
            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b + 3 + 1, this.f.c - 1, 2, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b + 3 + 1, this.f.c + 1, 1, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b + 3 + 1, this.f.c + 1, 3, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + 1, this.f.b + 3 + 1, this.f.f + 1, 0, i);
            }
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            if (this.b) {
                this.a(world, structureboundingbox, this.f.a + 1, this.f.b, this.f.c, this.f.d - 1, this.f.b + 3 - 1, this.f.f, Blocks.AIR, Blocks.AIR, false);
                this.a(world, structureboundingbox, this.f.a, this.f.b, this.f.c + 1, this.f.d, this.f.b + 3 - 1, this.f.f - 1, Blocks.AIR, Blocks.AIR, false);
                this.a(world, structureboundingbox, this.f.a + 1, this.f.e - 2, this.f.c, this.f.d - 1, this.f.e, this.f.f, Blocks.AIR, Blocks.AIR, false);
                this.a(world, structureboundingbox, this.f.a, this.f.e - 2, this.f.c + 1, this.f.d, this.f.e, this.f.f - 1, Blocks.AIR, Blocks.AIR, false);
                this.a(world, structureboundingbox, this.f.a + 1, this.f.b + 3, this.f.c + 1, this.f.d - 1, this.f.b + 3, this.f.f - 1, Blocks.AIR, Blocks.AIR, false);
            } else {
                this.a(world, structureboundingbox, this.f.a + 1, this.f.b, this.f.c, this.f.d - 1, this.f.e, this.f.f, Blocks.AIR, Blocks.AIR, false);
                this.a(world, structureboundingbox, this.f.a, this.f.b, this.f.c + 1, this.f.d, this.f.e, this.f.f - 1, Blocks.AIR, Blocks.AIR, false);
            }

            this.a(world, structureboundingbox, this.f.a + 1, this.f.b, this.f.c + 1, this.f.a + 1, this.f.e, this.f.c + 1, Blocks.WOOD, Blocks.AIR, false);
            this.a(world, structureboundingbox, this.f.a + 1, this.f.b, this.f.f - 1, this.f.a + 1, this.f.e, this.f.f - 1, Blocks.WOOD, Blocks.AIR, false);
            this.a(world, structureboundingbox, this.f.d - 1, this.f.b, this.f.c + 1, this.f.d - 1, this.f.e, this.f.c + 1, Blocks.WOOD, Blocks.AIR, false);
            this.a(world, structureboundingbox, this.f.d - 1, this.f.b, this.f.f - 1, this.f.d - 1, this.f.e, this.f.f - 1, Blocks.WOOD, Blocks.AIR, false);

            for (int i = this.f.a; i <= this.f.d; ++i) {
                for (int j = this.f.c; j <= this.f.f; ++j) {
                    if (this.a(world, i, this.f.b - 1, j, structureboundingbox).getMaterial() == Material.AIR) {
                        this.a(world, Blocks.WOOD, 0, i, this.f.b - 1, j, structureboundingbox);
                    }
                }
            }

            return true;
        }
    }
}
