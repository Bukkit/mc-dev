package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdCorridor extends WorldGenStrongholdPiece {

    private int a;

    public WorldGenStrongholdCorridor() {}

    public WorldGenStrongholdCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
        this.a = j != 2 && j != 0 ? structureboundingbox.b() : structureboundingbox.d();
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("Steps", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getInt("Steps");
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l) {
        boolean flag = true;
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 4, l);
        StructurePiece structurepiece = StructurePiece.a(list, structureboundingbox);

        if (structurepiece == null) {
            return null;
        } else {
            if (structurepiece.c().b == structureboundingbox.b) {
                for (int i1 = 3; i1 >= 1; --i1) {
                    structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, i1 - 1, l);
                    if (!structurepiece.c().a(structureboundingbox)) {
                        return StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, i1, l);
                    }
                }
            }

            return null;
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            for (int i = 0; i < this.a; ++i) {
                this.a(world, Blocks.SMOOTH_BRICK, 0, 0, 0, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 1, 0, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 2, 0, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 3, 0, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 4, 0, i, structureboundingbox);

                for (int j = 1; j <= 3; ++j) {
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 0, j, i, structureboundingbox);
                    this.a(world, Blocks.AIR, 0, 1, j, i, structureboundingbox);
                    this.a(world, Blocks.AIR, 0, 2, j, i, structureboundingbox);
                    this.a(world, Blocks.AIR, 0, 3, j, i, structureboundingbox);
                    this.a(world, Blocks.SMOOTH_BRICK, 0, 4, j, i, structureboundingbox);
                }

                this.a(world, Blocks.SMOOTH_BRICK, 0, 0, 4, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 1, 4, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 2, 4, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 3, 4, i, structureboundingbox);
                this.a(world, Blocks.SMOOTH_BRICK, 0, 4, 4, i, structureboundingbox);
            }

            return true;
        }
    }
}
