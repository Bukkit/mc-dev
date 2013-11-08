package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece2 extends WorldGenNetherPiece {

    private int b;

    public WorldGenNetherPiece2() {}

    public WorldGenNetherPiece2(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
        this.b = random.nextInt();
    }

    public static WorldGenNetherPiece2 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -3, 0, 5, 10, 8, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece2(i1, random, structureboundingbox, l) : null;
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getInt("Seed");
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("Seed", this.b);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        Random random1 = new Random((long) this.b);

        int i;
        int j;
        int k;

        for (i = 0; i <= 4; ++i) {
            for (j = 3; j <= 4; ++j) {
                k = random1.nextInt(8);
                this.a(world, structureboundingbox, i, j, 0, i, j, k, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
            }
        }

        i = random1.nextInt(8);
        this.a(world, structureboundingbox, 0, 5, 0, 0, 5, i, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        i = random1.nextInt(8);
        this.a(world, structureboundingbox, 4, 5, 0, 4, 5, i, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

        for (i = 0; i <= 4; ++i) {
            j = random1.nextInt(5);
            this.a(world, structureboundingbox, i, 2, 0, i, 2, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        }

        for (i = 0; i <= 4; ++i) {
            for (j = 0; j <= 1; ++j) {
                k = random1.nextInt(3);
                this.a(world, structureboundingbox, i, j, 0, i, j, k, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
            }
        }

        return true;
    }
}
