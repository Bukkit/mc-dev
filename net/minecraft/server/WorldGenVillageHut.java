package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHut extends WorldGenVillagePiece {

    private boolean a;
    private int b;

    public WorldGenVillageHut() {}

    public WorldGenVillageHut(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(worldgenvillagestartpiece, i);
        this.g = j;
        this.f = structureboundingbox;
        this.a = random.nextBoolean();
        this.b = random.nextInt(3);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("T", this.b);
        nbttagcompound.setBoolean("C", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getInt("T");
        this.a = nbttagcompound.getBoolean("C");
    }

    public static WorldGenVillageHut a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 4, 6, 5, l);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageHut(worldgenvillagestartpiece, i1, random, structureboundingbox, l) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k < 0) {
            this.k = this.b(world, structureboundingbox);
            if (this.k < 0) {
                return true;
            }

            this.f.a(0, this.k - this.f.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 5, 4, Blocks.AIR, Blocks.AIR, false);
        this.a(world, structureboundingbox, 0, 0, 0, 3, 0, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 1, 0, 1, 2, 0, 3, Blocks.DIRT, Blocks.DIRT, false);
        if (this.a) {
            this.a(world, structureboundingbox, 1, 4, 1, 2, 4, 3, Blocks.LOG, Blocks.LOG, false);
        } else {
            this.a(world, structureboundingbox, 1, 5, 1, 2, 5, 3, Blocks.LOG, Blocks.LOG, false);
        }

        this.a(world, Blocks.LOG, 0, 1, 4, 0, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 2, 4, 0, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 1, 4, 4, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 2, 4, 4, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 0, 4, 1, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 0, 4, 2, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 0, 4, 3, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 3, 4, 1, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 3, 4, 2, structureboundingbox);
        this.a(world, Blocks.LOG, 0, 3, 4, 3, structureboundingbox);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 3, 0, Blocks.LOG, Blocks.LOG, false);
        this.a(world, structureboundingbox, 3, 1, 0, 3, 3, 0, Blocks.LOG, Blocks.LOG, false);
        this.a(world, structureboundingbox, 0, 1, 4, 0, 3, 4, Blocks.LOG, Blocks.LOG, false);
        this.a(world, structureboundingbox, 3, 1, 4, 3, 3, 4, Blocks.LOG, Blocks.LOG, false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 3, 1, 1, 3, 3, 3, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 1, 4, 2, 3, 4, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 3, 2, 2, structureboundingbox);
        if (this.b > 0) {
            this.a(world, Blocks.FENCE, 0, this.b, 1, 3, structureboundingbox);
            this.a(world, Blocks.WOOD_PLATE, 0, this.b, 2, 3, structureboundingbox);
        }

        this.a(world, Blocks.AIR, 0, 1, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR, 0, 1, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 1, 1, 0, this.a(Blocks.WOODEN_DOOR, 1));
        if (this.a(world, 1, 0, -1, structureboundingbox).getMaterial() == Material.AIR && this.a(world, 1, -1, -1, structureboundingbox).getMaterial() != Material.AIR) {
            this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 1, 0, -1, structureboundingbox);
        }

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE, 0, j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 1, 1, 2, 1);
        return true;
    }
}
