package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdCorridor extends WorldGenStrongholdPiece {

    private final int a;

    public WorldGenStrongholdCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
        this.a = j != 2 && j != 0 ? structureboundingbox.b() : structureboundingbox.d();
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 4, l);
        StructurePiece structurepiece = StructurePiece.a(list, structureboundingbox);

        if (structurepiece == null) {
            return null;
        } else {
            if (structurepiece.b().b == structureboundingbox.b) {
                for (int i1 = 3; i1 >= 1; --i1) {
                    structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, i1 - 1, l);
                    if (!structurepiece.b().a(structureboundingbox)) {
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
                this.a(world, Block.SMOOTH_BRICK.id, 0, 0, 0, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 1, 0, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 2, 0, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 3, 0, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 4, 0, i, structureboundingbox);

                for (int j = 1; j <= 3; ++j) {
                    this.a(world, Block.SMOOTH_BRICK.id, 0, 0, j, i, structureboundingbox);
                    this.a(world, 0, 0, 1, j, i, structureboundingbox);
                    this.a(world, 0, 0, 2, j, i, structureboundingbox);
                    this.a(world, 0, 0, 3, j, i, structureboundingbox);
                    this.a(world, Block.SMOOTH_BRICK.id, 0, 4, j, i, structureboundingbox);
                }

                this.a(world, Block.SMOOTH_BRICK.id, 0, 0, 4, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 1, 4, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 2, 4, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 3, 4, i, structureboundingbox);
                this.a(world, Block.SMOOTH_BRICK.id, 0, 4, 4, i, structureboundingbox);
            }

            return true;
        }
    }
}
