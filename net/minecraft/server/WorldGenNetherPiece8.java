package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece8 extends WorldGenNetherPiece {

    private boolean b;

    public WorldGenNetherPiece8() {}

    public WorldGenNetherPiece8(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
        this.b = random.nextInt(3) == 0;
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getBoolean("Chest");
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Chest", this.b);
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.b((WorldGenNetherPiece15) structurepiece, list, random, 0, 1, true);
    }

    public static WorldGenNetherPiece8 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece8(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 4, 3, 1, 4, 4, 1, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 4, 3, 3, 4, 4, 3, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 3, 4, 1, 4, 4, Blocks.NETHER_FENCE, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 3, 3, 4, 3, 4, 4, Blocks.NETHER_FENCE, Blocks.NETHER_BRICK, false);
        int i;
        int j;

        if (this.b) {
            i = this.a(2);
            j = this.a(3, 3);
            int k = this.b(3, 3);

            if (structureboundingbox.b(j, i, k)) {
                this.b = false;
                this.a(world, structureboundingbox, random, 3, 2, 3, a, 2 + random.nextInt(4));
            }
        }

        this.a(world, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

        for (i = 0; i <= 4; ++i) {
            for (j = 0; j <= 4; ++j) {
                this.b(world, Blocks.NETHER_BRICK, 0, i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
