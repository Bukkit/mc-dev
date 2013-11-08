package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHouse extends WorldGenVillagePiece {

    private boolean a;

    public WorldGenVillageHouse() {}

    public WorldGenVillageHouse(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(worldgenvillagestartpiece, i);
        this.g = j;
        this.f = structureboundingbox;
        this.a = random.nextBoolean();
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Terrace", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getBoolean("Terrace");
    }

    public static WorldGenVillageHouse a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 5, 6, 5, l);

        return StructurePiece.a(list, structureboundingbox) != null ? null : new WorldGenVillageHouse(worldgenvillagestartpiece, i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k < 0) {
            this.k = this.b(world, structureboundingbox);
            if (this.k < 0) {
                return true;
            }

            this.f.a(0, this.k - this.f.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 0, 0, 4, 0, 4, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
        this.a(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Blocks.LOG, Blocks.LOG, false);
        this.a(world, structureboundingbox, 1, 4, 1, 3, 4, 3, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, Blocks.COBBLESTONE, 0, 0, 1, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 0, 2, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 0, 3, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 4, 1, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 4, 2, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 4, 3, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 0, 1, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 0, 2, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 0, 3, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 4, 1, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 4, 2, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE, 0, 4, 3, 4, structureboundingbox);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, structureboundingbox, 1, 1, 4, 3, 3, 4, Blocks.WOOD, Blocks.WOOD, false);
        this.a(world, Blocks.THIN_GLASS, 0, 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 2, 2, 4, structureboundingbox);
        this.a(world, Blocks.THIN_GLASS, 0, 4, 2, 2, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 1, 1, 0, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 1, 2, 0, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 1, 3, 0, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 2, 3, 0, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 3, 3, 0, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 3, 2, 0, structureboundingbox);
        this.a(world, Blocks.WOOD, 0, 3, 1, 0, structureboundingbox);
        if (this.a(world, 2, 0, -1, structureboundingbox).getMaterial() == Material.AIR && this.a(world, 2, -1, -1, structureboundingbox).getMaterial() != Material.AIR) {
            this.a(world, Blocks.COBBLESTONE_STAIRS, this.a(Blocks.COBBLESTONE_STAIRS, 3), 2, 0, -1, structureboundingbox);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 3, 3, Blocks.AIR, Blocks.AIR, false);
        if (this.a) {
            this.a(world, Blocks.FENCE, 0, 0, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 1, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 2, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 3, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 4, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 0, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 1, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 2, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 3, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 4, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 4, 5, 1, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 4, 5, 2, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 4, 5, 3, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 0, 5, 1, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 0, 5, 2, structureboundingbox);
            this.a(world, Blocks.FENCE, 0, 0, 5, 3, structureboundingbox);
        }

        int i;

        if (this.a) {
            i = this.a(Blocks.LADDER, 3);
            this.a(world, Blocks.LADDER, i, 3, 1, 3, structureboundingbox);
            this.a(world, Blocks.LADDER, i, 3, 2, 3, structureboundingbox);
            this.a(world, Blocks.LADDER, i, 3, 3, 3, structureboundingbox);
            this.a(world, Blocks.LADDER, i, 3, 4, 3, structureboundingbox);
        }

        this.a(world, Blocks.TORCH, 0, 2, 3, 1, structureboundingbox);

        for (i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE, 0, j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 1, 1, 2, 1);
        return true;
    }
}
