package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class StructurePiece {

    protected StructureBoundingBox g;
    protected int h;
    protected int i;

    protected StructurePiece(int i) {
        this.i = i;
        this.h = -1;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public abstract boolean a(World world, Random random, StructureBoundingBox structureboundingbox);

    public StructureBoundingBox b() {
        return this.g;
    }

    public int c() {
        return this.i;
    }

    public static StructurePiece a(List list, StructureBoundingBox structureboundingbox) {
        Iterator iterator = list.iterator();

        StructurePiece structurepiece;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            structurepiece = (StructurePiece) iterator.next();
        } while (structurepiece.b() == null || !structurepiece.b().a(structureboundingbox));

        return structurepiece;
    }

    public ChunkPosition b_() {
        return new ChunkPosition(this.g.e(), this.g.f(), this.g.g());
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox) {
        int i = Math.max(this.g.a - 1, structureboundingbox.a);
        int j = Math.max(this.g.b - 1, structureboundingbox.b);
        int k = Math.max(this.g.c - 1, structureboundingbox.c);
        int l = Math.min(this.g.d + 1, structureboundingbox.d);
        int i1 = Math.min(this.g.e + 1, structureboundingbox.e);
        int j1 = Math.min(this.g.f + 1, structureboundingbox.f);

        int k1;
        int l1;
        int i2;

        for (k1 = i; k1 <= l; ++k1) {
            for (l1 = k; l1 <= j1; ++l1) {
                i2 = world.getTypeId(k1, j, l1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }

                i2 = world.getTypeId(k1, i1, l1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }
            }
        }

        for (k1 = i; k1 <= l; ++k1) {
            for (l1 = j; l1 <= i1; ++l1) {
                i2 = world.getTypeId(k1, l1, k);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }

                i2 = world.getTypeId(k1, l1, j1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }
            }
        }

        for (k1 = k; k1 <= j1; ++k1) {
            for (l1 = j; l1 <= i1; ++l1) {
                i2 = world.getTypeId(i, l1, k1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }

                i2 = world.getTypeId(l, l1, k1);
                if (i2 > 0 && Block.byId[i2].material.isLiquid()) {
                    return true;
                }
            }
        }

        return false;
    }

    protected int a(int i, int j) {
        switch (this.h) {
        case 0:
        case 2:
            return this.g.a + i;

        case 1:
            return this.g.d - j;

        case 3:
            return this.g.a + j;

        default:
            return i;
        }
    }

    protected int b(int i) {
        return this.h == -1 ? i : i + this.g.b;
    }

    protected int b(int i, int j) {
        switch (this.h) {
        case 0:
            return this.g.c + j;

        case 1:
        case 3:
            return this.g.c + i;

        case 2:
            return this.g.f - j;

        default:
            return j;
        }
    }

    protected int c(int i, int j) {
        if (i == Block.RAILS.id) {
            if (this.h == 1 || this.h == 3) {
                if (j == 1) {
                    return 0;
                }

                return 1;
            }
        } else if (i != Block.WOODEN_DOOR.id && i != Block.IRON_DOOR_BLOCK.id) {
            if (i != Block.COBBLESTONE_STAIRS.id && i != Block.WOOD_STAIRS.id && i != Block.NETHER_BRICK_STAIRS.id && i != Block.STONE_STAIRS.id) {
                if (i == Block.LADDER.id) {
                    if (this.h == 0) {
                        if (j == 2) {
                            return 3;
                        }

                        if (j == 3) {
                            return 2;
                        }
                    } else if (this.h == 1) {
                        if (j == 2) {
                            return 4;
                        }

                        if (j == 3) {
                            return 5;
                        }

                        if (j == 4) {
                            return 2;
                        }

                        if (j == 5) {
                            return 3;
                        }
                    } else if (this.h == 3) {
                        if (j == 2) {
                            return 5;
                        }

                        if (j == 3) {
                            return 4;
                        }

                        if (j == 4) {
                            return 2;
                        }

                        if (j == 5) {
                            return 3;
                        }
                    }
                } else if (i == Block.STONE_BUTTON.id) {
                    if (this.h == 0) {
                        if (j == 3) {
                            return 4;
                        }

                        if (j == 4) {
                            return 3;
                        }
                    } else if (this.h == 1) {
                        if (j == 3) {
                            return 1;
                        }

                        if (j == 4) {
                            return 2;
                        }

                        if (j == 2) {
                            return 3;
                        }

                        if (j == 1) {
                            return 4;
                        }
                    } else if (this.h == 3) {
                        if (j == 3) {
                            return 2;
                        }

                        if (j == 4) {
                            return 1;
                        }

                        if (j == 2) {
                            return 3;
                        }

                        if (j == 1) {
                            return 4;
                        }
                    }
                }
            } else if (this.h == 0) {
                if (j == 2) {
                    return 3;
                }

                if (j == 3) {
                    return 2;
                }
            } else if (this.h == 1) {
                if (j == 0) {
                    return 2;
                }

                if (j == 1) {
                    return 3;
                }

                if (j == 2) {
                    return 0;
                }

                if (j == 3) {
                    return 1;
                }
            } else if (this.h == 3) {
                if (j == 0) {
                    return 2;
                }

                if (j == 1) {
                    return 3;
                }

                if (j == 2) {
                    return 1;
                }

                if (j == 3) {
                    return 0;
                }
            }
        } else if (this.h == 0) {
            if (j == 0) {
                return 2;
            }

            if (j == 2) {
                return 0;
            }
        } else {
            if (this.h == 1) {
                return j + 1 & 3;
            }

            if (this.h == 3) {
                return j + 3 & 3;
            }
        }

        return j;
    }

    protected void a(World world, int i, int j, int k, int l, int i1, StructureBoundingBox structureboundingbox) {
        int j1 = this.a(k, i1);
        int k1 = this.b(l);
        int l1 = this.b(k, i1);

        if (structureboundingbox.b(j1, k1, l1)) {
            world.setRawTypeIdAndData(j1, k1, l1, i, j);
        }
    }

    protected int a(World world, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        int l = this.a(i, k);
        int i1 = this.b(j);
        int j1 = this.b(i, k);

        return !structureboundingbox.b(l, i1, j1) ? 0 : world.getTypeId(l, i1, j1);
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, int k1, int l1, boolean flag) {
        for (int i2 = j; i2 <= i1; ++i2) {
            for (int j2 = i; j2 <= l; ++j2) {
                for (int k2 = k; k2 <= j1; ++k2) {
                    if (!flag || this.a(world, j2, i2, k2, structureboundingbox) != 0) {
                        if (i2 != j && i2 != i1 && j2 != i && j2 != l && k2 != k && k2 != j1) {
                            this.a(world, l1, 0, j2, i2, k2, structureboundingbox);
                        } else {
                            this.a(world, k1, 0, j2, i2, k2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, boolean flag, Random random, StructurePieceBlockSelector structurepieceblockselector) {
        for (int k1 = j; k1 <= i1; ++k1) {
            for (int l1 = i; l1 <= l; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    if (!flag || this.a(world, l1, k1, i2, structureboundingbox) != 0) {
                        structurepieceblockselector.a(random, l1, k1, i2, k1 == j || k1 == i1 || l1 == i || l1 == l || i2 == k || i2 == j1);
                        this.a(world, structurepieceblockselector.a(), structurepieceblockselector.b(), l1, k1, i2, structureboundingbox);
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int i, int j, int k, int l, int i1, int j1, int k1, int l1, boolean flag) {
        for (int i2 = j; i2 <= i1; ++i2) {
            for (int j2 = i; j2 <= l; ++j2) {
                for (int k2 = k; k2 <= j1; ++k2) {
                    if (random.nextFloat() <= f && (!flag || this.a(world, j2, i2, k2, structureboundingbox) != 0)) {
                        if (i2 != j && i2 != i1 && j2 != i && j2 != l && k2 != k && k2 != j1) {
                            this.a(world, l1, 0, j2, i2, k2, structureboundingbox);
                        } else {
                            this.a(world, k1, 0, j2, i2, k2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int i, int j, int k, int l, int i1) {
        if (random.nextFloat() < f) {
            this.a(world, l, i1, i, j, k, structureboundingbox);
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, int k1, boolean flag) {
        float f = (float) (l - i + 1);
        float f1 = (float) (i1 - j + 1);
        float f2 = (float) (j1 - k + 1);
        float f3 = (float) i + f / 2.0F;
        float f4 = (float) k + f2 / 2.0F;

        for (int l1 = j; l1 <= i1; ++l1) {
            float f5 = (float) (l1 - j) / f1;

            for (int i2 = i; i2 <= l; ++i2) {
                float f6 = ((float) i2 - f3) / (f * 0.5F);

                for (int j2 = k; j2 <= j1; ++j2) {
                    float f7 = ((float) j2 - f4) / (f2 * 0.5F);

                    if (!flag || this.a(world, i2, l1, j2, structureboundingbox) != 0) {
                        float f8 = f6 * f6 + f5 * f5 + f7 * f7;

                        if (f8 <= 1.05F) {
                            this.a(world, k1, 0, i2, l1, j2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void b(World world, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        int l = this.a(i, k);
        int i1 = this.b(j);
        int j1 = this.b(i, k);

        if (structureboundingbox.b(l, i1, j1)) {
            while (!world.isEmpty(l, i1, j1) && i1 < world.heightMinusOne) {
                world.setRawTypeIdAndData(l, i1, j1, 0, 0);
                ++i1;
            }
        }
    }

    protected void b(World world, int i, int j, int k, int l, int i1, StructureBoundingBox structureboundingbox) {
        int j1 = this.a(k, i1);
        int k1 = this.b(l);
        int l1 = this.b(k, i1);

        if (structureboundingbox.b(j1, k1, l1)) {
            while ((world.isEmpty(j1, k1, l1) || world.getMaterial(j1, k1, l1).isLiquid()) && k1 > 1) {
                world.setRawTypeIdAndData(j1, k1, l1, i, j);
                --k1;
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, StructurePieceTreasure[] astructurepiecetreasure, int l) {
        int i1 = this.a(i, k);
        int j1 = this.b(j);
        int k1 = this.b(i, k);

        if (structureboundingbox.b(i1, j1, k1) && world.getTypeId(i1, j1, k1) != Block.CHEST.id) {
            world.setTypeId(i1, j1, k1, Block.CHEST.id);
            TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i1, j1, k1);

            if (tileentitychest != null) {
                a(random, astructurepiecetreasure, tileentitychest, l);
            }
        }
    }

    private static void a(Random random, StructurePieceTreasure[] astructurepiecetreasure, TileEntityChest tileentitychest, int i) {
        for (int j = 0; j < i; ++j) {
            StructurePieceTreasure structurepiecetreasure = (StructurePieceTreasure) WeightedRandom.a(random, (WeightedRandomChoice[]) astructurepiecetreasure);
            int k = structurepiecetreasure.c + random.nextInt(structurepiecetreasure.e - structurepiecetreasure.c + 1);

            if (Item.byId[structurepiecetreasure.a].getMaxStackSize() >= k) {
                tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), new ItemStack(structurepiecetreasure.a, k, structurepiecetreasure.b));
            } else {
                for (int l = 0; l < k; ++l) {
                    tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), new ItemStack(structurepiecetreasure.a, 1, structurepiecetreasure.b));
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, int l) {
        int i1 = this.a(i, k);
        int j1 = this.b(j);
        int k1 = this.b(i, k);

        if (structureboundingbox.b(i1, j1, k1)) {
            ItemDoor.a(world, i1, j1, k1, l, Block.WOODEN_DOOR);
        }
    }
}
