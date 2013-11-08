package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPortalRoom extends WorldGenStrongholdPiece {

    private boolean a;

    public WorldGenStrongholdPortalRoom() {}

    public WorldGenStrongholdPortalRoom(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Mob", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getBoolean("Mob");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        if (structurepiece != null) {
            ((WorldGenStrongholdStart) structurepiece).b = this;
        }
    }

    public static WorldGenStrongholdPortalRoom a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 11, 8, 16, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPortalRoom(i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 10, 7, 15, false, random, WorldGenStrongholdPieces.c());
        this.a(world, random, structureboundingbox, WorldGenStrongholdDoorType.c, 4, 1, 0);
        byte b0 = 6;

        this.a(world, structureboundingbox, 1, b0, 1, 1, b0, 14, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 9, b0, 1, 9, b0, 14, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 2, b0, 1, 8, b0, 2, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 2, b0, 14, 8, b0, 14, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 1, 1, 1, 2, 1, 4, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 8, 1, 1, 9, 1, 4, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 1, 1, 1, 1, 1, 3, Blocks.LAVA, Blocks.LAVA, false);
        this.a(world, structureboundingbox, 9, 1, 1, 9, 1, 3, Blocks.LAVA, Blocks.LAVA, false);
        this.a(world, structureboundingbox, 3, 1, 8, 7, 1, 12, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 4, 1, 9, 6, 1, 11, Blocks.LAVA, Blocks.LAVA, false);

        int i;

        for (i = 3; i < 14; i += 2) {
            this.a(world, structureboundingbox, 0, 3, i, 0, 4, i, Blocks.IRON_FENCE, Blocks.IRON_FENCE, false);
            this.a(world, structureboundingbox, 10, 3, i, 10, 4, i, Blocks.IRON_FENCE, Blocks.IRON_FENCE, false);
        }

        for (i = 2; i < 9; i += 2) {
            this.a(world, structureboundingbox, i, 3, 15, i, 4, 15, Blocks.IRON_FENCE, Blocks.IRON_FENCE, false);
        }

        i = this.a(Blocks.STONE_STAIRS, 3);
        this.a(world, structureboundingbox, 4, 1, 5, 6, 1, 7, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 4, 2, 6, 6, 2, 7, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 4, 3, 7, 6, 3, 7, false, random, WorldGenStrongholdPieces.c());

        for (int j = 4; j <= 6; ++j) {
            this.a(world, Blocks.STONE_STAIRS, i, j, 1, 4, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS, i, j, 2, 5, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS, i, j, 3, 6, structureboundingbox);
        }

        byte b1 = 2;
        byte b2 = 0;
        byte b3 = 3;
        byte b4 = 1;

        switch (this.g) {
        case 0:
            b1 = 0;
            b2 = 2;
            break;

        case 1:
            b1 = 1;
            b2 = 3;
            b3 = 0;
            b4 = 2;

        case 2:
        default:
            break;

        case 3:
            b1 = 3;
            b2 = 1;
            b3 = 0;
            b4 = 2;
        }

        this.a(world, Blocks.ENDER_PORTAL_FRAME, b1 + (random.nextFloat() > 0.9F ? 4 : 0), 4, 3, 8, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b1 + (random.nextFloat() > 0.9F ? 4 : 0), 5, 3, 8, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b1 + (random.nextFloat() > 0.9F ? 4 : 0), 6, 3, 8, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b2 + (random.nextFloat() > 0.9F ? 4 : 0), 4, 3, 12, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b2 + (random.nextFloat() > 0.9F ? 4 : 0), 5, 3, 12, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b2 + (random.nextFloat() > 0.9F ? 4 : 0), 6, 3, 12, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b3 + (random.nextFloat() > 0.9F ? 4 : 0), 3, 3, 9, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b3 + (random.nextFloat() > 0.9F ? 4 : 0), 3, 3, 10, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b3 + (random.nextFloat() > 0.9F ? 4 : 0), 3, 3, 11, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b4 + (random.nextFloat() > 0.9F ? 4 : 0), 7, 3, 9, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b4 + (random.nextFloat() > 0.9F ? 4 : 0), 7, 3, 10, structureboundingbox);
        this.a(world, Blocks.ENDER_PORTAL_FRAME, b4 + (random.nextFloat() > 0.9F ? 4 : 0), 7, 3, 11, structureboundingbox);
        if (!this.a) {
            int k = this.a(3);
            int l = this.a(5, 6);
            int i1 = this.b(5, 6);

            if (structureboundingbox.b(l, k, i1)) {
                this.a = true;
                world.setTypeAndData(l, k, i1, Blocks.MOB_SPAWNER, 0, 2);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(l, k, i1);

                if (tileentitymobspawner != null) {
                    tileentitymobspawner.a().a("Silverfish");
                }
            }
        }

        return true;
    }
}
