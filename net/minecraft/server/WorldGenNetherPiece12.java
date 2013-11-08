package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece12 extends WorldGenNetherPiece {

    private boolean b;

    public WorldGenNetherPiece12() {}

    public WorldGenNetherPiece12(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getBoolean("Mob");
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Mob", this.b);
    }

    public static WorldGenNetherPiece12 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 8, 9, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece12(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 2, 0, 6, 7, 7, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, structureboundingbox, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
        this.a(world, Blocks.NETHER_FENCE, 0, 1, 6, 3, structureboundingbox);
        this.a(world, Blocks.NETHER_FENCE, 0, 5, 6, 3, structureboundingbox);
        this.a(world, structureboundingbox, 0, 6, 3, 0, 6, 8, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 6, 6, 3, 6, 6, 8, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 1, 6, 8, 5, 7, 8, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        this.a(world, structureboundingbox, 2, 8, 8, 4, 8, 8, Blocks.NETHER_FENCE, Blocks.NETHER_FENCE, false);
        int i;
        int j;

        if (!this.b) {
            i = this.a(5);
            j = this.a(3, 5);
            int k = this.b(3, 5);

            if (structureboundingbox.b(j, i, k)) {
                this.b = true;
                world.setTypeAndData(j, i, k, Blocks.MOB_SPAWNER, 0, 2);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(j, i, k);

                if (tileentitymobspawner != null) {
                    tileentitymobspawner.a().a("Blaze");
                }
            }
        }

        for (i = 0; i <= 6; ++i) {
            for (j = 0; j <= 6; ++j) {
                this.b(world, Blocks.NETHER_BRICK, 0, i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
