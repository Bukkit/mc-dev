package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdLibrary extends WorldGenStrongholdPiece {

    private static final StructurePieceTreasure[] b = new StructurePieceTreasure[] { new StructurePieceTreasure(Item.BOOK.id, 0, 1, 3, 20), new StructurePieceTreasure(Item.PAPER.id, 0, 2, 7, 20), new StructurePieceTreasure(Item.MAP.id, 0, 1, 1, 1), new StructurePieceTreasure(Item.COMPASS.id, 0, 1, 1, 1)};
    protected final WorldGenStrongholdDoorType a;
    private final boolean c;

    public WorldGenStrongholdLibrary(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.a = this.a(random);
        this.g = structureboundingbox;
        this.c = structureboundingbox.c() > 6;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public static WorldGenStrongholdLibrary a(List list, Random random, int i, int j, int k, int l, int i1) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 11, 15, l);

        if (!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null) {
            structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 6, 15, l);
            if (!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null) {
                return null;
            }
        }

        return new WorldGenStrongholdLibrary(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            byte b0 = 11;

            if (!this.c) {
                b0 = 6;
            }

            this.a(world, structureboundingbox, 0, 0, 0, 13, b0 - 1, 14, true, random, WorldGenStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.a, 4, 1, 0);
            this.a(world, structureboundingbox, random, 0.07F, 2, 1, 1, 11, 4, 13, Block.WEB.id, Block.WEB.id, false);

            int i;

            for (i = 1; i <= 13; ++i) {
                if ((i - 1) % 4 == 0) {
                    this.a(world, structureboundingbox, 1, 1, i, 1, 4, i, Block.WOOD.id, Block.WOOD.id, false);
                    this.a(world, structureboundingbox, 12, 1, i, 12, 4, i, Block.WOOD.id, Block.WOOD.id, false);
                    this.a(world, Block.TORCH.id, 0, 2, 3, i, structureboundingbox);
                    this.a(world, Block.TORCH.id, 0, 11, 3, i, structureboundingbox);
                    if (this.c) {
                        this.a(world, structureboundingbox, 1, 6, i, 1, 9, i, Block.WOOD.id, Block.WOOD.id, false);
                        this.a(world, structureboundingbox, 12, 6, i, 12, 9, i, Block.WOOD.id, Block.WOOD.id, false);
                    }
                } else {
                    this.a(world, structureboundingbox, 1, 1, i, 1, 4, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
                    this.a(world, structureboundingbox, 12, 1, i, 12, 4, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
                    if (this.c) {
                        this.a(world, structureboundingbox, 1, 6, i, 1, 9, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
                        this.a(world, structureboundingbox, 12, 6, i, 12, 9, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
                    }
                }
            }

            for (i = 3; i < 12; i += 2) {
                this.a(world, structureboundingbox, 3, 1, i, 4, 3, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
                this.a(world, structureboundingbox, 6, 1, i, 7, 3, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
                this.a(world, structureboundingbox, 9, 1, i, 10, 3, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
            }

            if (this.c) {
                this.a(world, structureboundingbox, 1, 5, 1, 3, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
                this.a(world, structureboundingbox, 10, 5, 1, 12, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
                this.a(world, structureboundingbox, 4, 5, 1, 9, 5, 2, Block.WOOD.id, Block.WOOD.id, false);
                this.a(world, structureboundingbox, 4, 5, 12, 9, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
                this.a(world, Block.WOOD.id, 0, 9, 5, 11, structureboundingbox);
                this.a(world, Block.WOOD.id, 0, 8, 5, 11, structureboundingbox);
                this.a(world, Block.WOOD.id, 0, 9, 5, 10, structureboundingbox);
                this.a(world, structureboundingbox, 3, 6, 2, 3, 6, 12, Block.FENCE.id, Block.FENCE.id, false);
                this.a(world, structureboundingbox, 10, 6, 2, 10, 6, 10, Block.FENCE.id, Block.FENCE.id, false);
                this.a(world, structureboundingbox, 4, 6, 2, 9, 6, 2, Block.FENCE.id, Block.FENCE.id, false);
                this.a(world, structureboundingbox, 4, 6, 12, 8, 6, 12, Block.FENCE.id, Block.FENCE.id, false);
                this.a(world, Block.FENCE.id, 0, 9, 6, 11, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, 8, 6, 11, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, 9, 6, 10, structureboundingbox);
                i = this.c(Block.LADDER.id, 3);
                this.a(world, Block.LADDER.id, i, 10, 1, 13, structureboundingbox);
                this.a(world, Block.LADDER.id, i, 10, 2, 13, structureboundingbox);
                this.a(world, Block.LADDER.id, i, 10, 3, 13, structureboundingbox);
                this.a(world, Block.LADDER.id, i, 10, 4, 13, structureboundingbox);
                this.a(world, Block.LADDER.id, i, 10, 5, 13, structureboundingbox);
                this.a(world, Block.LADDER.id, i, 10, 6, 13, structureboundingbox);
                this.a(world, Block.LADDER.id, i, 10, 7, 13, structureboundingbox);
                byte b1 = 7;
                byte b2 = 7;

                this.a(world, Block.FENCE.id, 0, b1 - 1, 9, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1, 9, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1 - 1, 8, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1, 8, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1 - 1, 7, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1, 7, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1 - 2, 7, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1 + 1, 7, b2, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1 - 1, 7, b2 - 1, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1 - 1, 7, b2 + 1, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1, 7, b2 - 1, structureboundingbox);
                this.a(world, Block.FENCE.id, 0, b1, 7, b2 + 1, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, b1 - 2, 8, b2, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, b1 + 1, 8, b2, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, b1 - 1, 8, b2 - 1, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, b1 - 1, 8, b2 + 1, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, b1, 8, b2 - 1, structureboundingbox);
                this.a(world, Block.TORCH.id, 0, b1, 8, b2 + 1, structureboundingbox);
            }

            this.a(world, structureboundingbox, random, 3, 3, 5, b, 1 + random.nextInt(4));
            if (this.c) {
                this.a(world, 0, 0, 12, 9, 1, structureboundingbox);
                this.a(world, structureboundingbox, random, 12, 8, 1, b, 1 + random.nextInt(4));
            }

            return true;
        }
    }
}
