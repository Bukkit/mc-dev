package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece12 extends WorldGenNetherPiece {

    private boolean a;

    public WorldGenNetherPiece12(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenNetherPiece12 a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 8, 9, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece12(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 2, 0, 6, 7, 7, 0, 0, false);
        this.a(world, structureboundingbox, 1, 0, 0, 5, 1, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 1, 2, 1, 5, 2, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 1, 3, 2, 5, 3, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 1, 4, 3, 5, 4, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 1, 2, 0, 1, 4, 2, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 5, 2, 0, 5, 4, 2, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 1, 5, 2, 1, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 5, 5, 2, 5, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 0, 5, 3, 0, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 6, 5, 3, 6, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, structureboundingbox, 1, 5, 8, 5, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
        this.a(world, Block.NETHER_FENCE.id, 0, 1, 6, 3, structureboundingbox);
        this.a(world, Block.NETHER_FENCE.id, 0, 5, 6, 3, structureboundingbox);
        this.a(world, structureboundingbox, 0, 6, 3, 0, 6, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 6, 6, 3, 6, 6, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 1, 6, 8, 5, 7, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        this.a(world, structureboundingbox, 2, 8, 8, 4, 8, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
        int i;
        int j;

        if (!this.a) {
            i = this.b(5);
            j = this.a(3, 5);
            int k = this.b(3, 5);

            if (structureboundingbox.b(j, i, k)) {
                this.a = true;
                world.setTypeId(j, i, k, Block.MOB_SPAWNER.id);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(j, i, k);

                if (tileentitymobspawner != null) {
                    tileentitymobspawner.a("Blaze");
                }
            }
        }

        for (i = 0; i <= 6; ++i) {
            for (j = 0; j <= 6; ++j) {
                this.b(world, Block.NETHER_BRICK.id, 0, i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
