package net.minecraft.server;

import java.util.Random;

public class WorldGenWitchHut extends WorldGenScatteredPiece {

    private boolean e;

    public WorldGenWitchHut() {}

    public WorldGenWitchHut(Random random, int i, int j) {
        super(random, i, 64, j, 7, 5, 9);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Witch", this.e);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.e = nbttagcompound.getBoolean("Witch");
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (!this.a(world, structureboundingbox, 0)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 1, 1, 1, 5, 1, 7, Block.WOOD.id, 1, Block.WOOD.id, 1, false);
            this.a(world, structureboundingbox, 1, 4, 2, 5, 4, 7, Block.WOOD.id, 1, Block.WOOD.id, 1, false);
            this.a(world, structureboundingbox, 2, 1, 0, 4, 1, 0, Block.WOOD.id, 1, Block.WOOD.id, 1, false);
            this.a(world, structureboundingbox, 2, 2, 2, 3, 3, 2, Block.WOOD.id, 1, Block.WOOD.id, 1, false);
            this.a(world, structureboundingbox, 1, 2, 3, 1, 3, 6, Block.WOOD.id, 1, Block.WOOD.id, 1, false);
            this.a(world, structureboundingbox, 5, 2, 3, 5, 3, 6, Block.WOOD.id, 1, Block.WOOD.id, 1, false);
            this.a(world, structureboundingbox, 2, 2, 7, 4, 3, 7, Block.WOOD.id, 1, Block.WOOD.id, 1, false);
            this.a(world, structureboundingbox, 1, 0, 2, 1, 3, 2, Block.LOG.id, Block.LOG.id, false);
            this.a(world, structureboundingbox, 5, 0, 2, 5, 3, 2, Block.LOG.id, Block.LOG.id, false);
            this.a(world, structureboundingbox, 1, 0, 7, 1, 3, 7, Block.LOG.id, Block.LOG.id, false);
            this.a(world, structureboundingbox, 5, 0, 7, 5, 3, 7, Block.LOG.id, Block.LOG.id, false);
            this.a(world, Block.FENCE.id, 0, 2, 3, 2, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 3, 3, 7, structureboundingbox);
            this.a(world, 0, 0, 1, 3, 4, structureboundingbox);
            this.a(world, 0, 0, 5, 3, 4, structureboundingbox);
            this.a(world, 0, 0, 5, 3, 5, structureboundingbox);
            this.a(world, Block.FLOWER_POT.id, 7, 1, 3, 5, structureboundingbox);
            this.a(world, Block.WORKBENCH.id, 0, 3, 2, 6, structureboundingbox);
            this.a(world, Block.CAULDRON.id, 0, 4, 2, 6, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 1, 2, 1, structureboundingbox);
            this.a(world, Block.FENCE.id, 0, 5, 2, 1, structureboundingbox);
            int i = this.c(Block.WOOD_STAIRS.id, 3);
            int j = this.c(Block.WOOD_STAIRS.id, 1);
            int k = this.c(Block.WOOD_STAIRS.id, 0);
            int l = this.c(Block.WOOD_STAIRS.id, 2);

            this.a(world, structureboundingbox, 0, 4, 1, 6, 4, 1, Block.SPRUCE_WOOD_STAIRS.id, i, Block.SPRUCE_WOOD_STAIRS.id, i, false);
            this.a(world, structureboundingbox, 0, 4, 2, 0, 4, 7, Block.SPRUCE_WOOD_STAIRS.id, k, Block.SPRUCE_WOOD_STAIRS.id, k, false);
            this.a(world, structureboundingbox, 6, 4, 2, 6, 4, 7, Block.SPRUCE_WOOD_STAIRS.id, j, Block.SPRUCE_WOOD_STAIRS.id, j, false);
            this.a(world, structureboundingbox, 0, 4, 8, 6, 4, 8, Block.SPRUCE_WOOD_STAIRS.id, l, Block.SPRUCE_WOOD_STAIRS.id, l, false);

            int i1;
            int j1;

            for (i1 = 2; i1 <= 7; i1 += 5) {
                for (j1 = 1; j1 <= 5; j1 += 4) {
                    this.b(world, Block.LOG.id, 0, j1, -1, i1, structureboundingbox);
                }
            }

            if (!this.e) {
                i1 = this.a(2, 5);
                j1 = this.a(2);
                int k1 = this.b(2, 5);

                if (structureboundingbox.b(i1, j1, k1)) {
                    this.e = true;
                    EntityWitch entitywitch = new EntityWitch(world);

                    entitywitch.setPositionRotation((double) i1 + 0.5D, (double) j1, (double) k1 + 0.5D, 0.0F, 0.0F);
                    entitywitch.a((GroupDataEntity) null);
                    world.addEntity(entitywitch);
                }
            }

            return true;
        }
    }
}
