package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class StructurePiece {

    protected StructureBoundingBox f;
    protected int g;
    protected int h;

    public StructurePiece() {}

    protected StructurePiece(int i) {
        this.h = i;
        this.g = -1;
    }

    public NBTTagCompound b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setString("id", WorldGenFactory.a(this));
        nbttagcompound.set("BB", this.f.h());
        nbttagcompound.setInt("O", this.g);
        nbttagcompound.setInt("GD", this.h);
        this.a(nbttagcompound);
        return nbttagcompound;
    }

    protected abstract void a(NBTTagCompound nbttagcompound);

    public void a(World world, NBTTagCompound nbttagcompound) {
        if (nbttagcompound.hasKey("BB")) {
            this.f = new StructureBoundingBox(nbttagcompound.getIntArray("BB"));
        }

        this.g = nbttagcompound.getInt("O");
        this.h = nbttagcompound.getInt("GD");
        this.b(nbttagcompound);
    }

    protected abstract void b(NBTTagCompound nbttagcompound);

    public void a(StructurePiece structurepiece, List list, Random random) {}

    public abstract boolean a(World world, Random random, StructureBoundingBox structureboundingbox);

    public StructureBoundingBox c() {
        return this.f;
    }

    public int d() {
        return this.h;
    }

    public static StructurePiece a(List list, StructureBoundingBox structureboundingbox) {
        Iterator iterator = list.iterator();

        StructurePiece structurepiece;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            structurepiece = (StructurePiece) iterator.next();
        } while (structurepiece.c() == null || !structurepiece.c().a(structureboundingbox));

        return structurepiece;
    }

    public ChunkPosition a() {
        return new ChunkPosition(this.f.e(), this.f.f(), this.f.g());
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox) {
        int i = Math.max(this.f.a - 1, structureboundingbox.a);
        int j = Math.max(this.f.b - 1, structureboundingbox.b);
        int k = Math.max(this.f.c - 1, structureboundingbox.c);
        int l = Math.min(this.f.d + 1, structureboundingbox.d);
        int i1 = Math.min(this.f.e + 1, structureboundingbox.e);
        int j1 = Math.min(this.f.f + 1, structureboundingbox.f);

        int k1;
        int l1;

        for (k1 = i; k1 <= l; ++k1) {
            for (l1 = k; l1 <= j1; ++l1) {
                if (world.getType(k1, j, l1).getMaterial().isLiquid()) {
                    return true;
                }

                if (world.getType(k1, i1, l1).getMaterial().isLiquid()) {
                    return true;
                }
            }
        }

        for (k1 = i; k1 <= l; ++k1) {
            for (l1 = j; l1 <= i1; ++l1) {
                if (world.getType(k1, l1, k).getMaterial().isLiquid()) {
                    return true;
                }

                if (world.getType(k1, l1, j1).getMaterial().isLiquid()) {
                    return true;
                }
            }
        }

        for (k1 = k; k1 <= j1; ++k1) {
            for (l1 = j; l1 <= i1; ++l1) {
                if (world.getType(i, l1, k1).getMaterial().isLiquid()) {
                    return true;
                }

                if (world.getType(l, l1, k1).getMaterial().isLiquid()) {
                    return true;
                }
            }
        }

        return false;
    }

    protected int a(int i, int j) {
        switch (this.g) {
        case 0:
        case 2:
            return this.f.a + i;

        case 1:
            return this.f.d - j;

        case 3:
            return this.f.a + j;

        default:
            return i;
        }
    }

    protected int a(int i) {
        return this.g == -1 ? i : i + this.f.b;
    }

    protected int b(int i, int j) {
        switch (this.g) {
        case 0:
            return this.f.c + j;

        case 1:
        case 3:
            return this.f.c + i;

        case 2:
            return this.f.f - j;

        default:
            return j;
        }
    }

    protected int a(Block block, int i) {
        if (block == Blocks.RAILS) {
            if (this.g == 1 || this.g == 3) {
                if (i == 1) {
                    return 0;
                }

                return 1;
            }
        } else if (block != Blocks.WOODEN_DOOR && block != Blocks.IRON_DOOR_BLOCK) {
            if (block != Blocks.COBBLESTONE_STAIRS && block != Blocks.WOOD_STAIRS && block != Blocks.NETHER_BRICK_STAIRS && block != Blocks.STONE_STAIRS && block != Blocks.SANDSTONE_STAIRS) {
                if (block == Blocks.LADDER) {
                    if (this.g == 0) {
                        if (i == 2) {
                            return 3;
                        }

                        if (i == 3) {
                            return 2;
                        }
                    } else if (this.g == 1) {
                        if (i == 2) {
                            return 4;
                        }

                        if (i == 3) {
                            return 5;
                        }

                        if (i == 4) {
                            return 2;
                        }

                        if (i == 5) {
                            return 3;
                        }
                    } else if (this.g == 3) {
                        if (i == 2) {
                            return 5;
                        }

                        if (i == 3) {
                            return 4;
                        }

                        if (i == 4) {
                            return 2;
                        }

                        if (i == 5) {
                            return 3;
                        }
                    }
                } else if (block == Blocks.STONE_BUTTON) {
                    if (this.g == 0) {
                        if (i == 3) {
                            return 4;
                        }

                        if (i == 4) {
                            return 3;
                        }
                    } else if (this.g == 1) {
                        if (i == 3) {
                            return 1;
                        }

                        if (i == 4) {
                            return 2;
                        }

                        if (i == 2) {
                            return 3;
                        }

                        if (i == 1) {
                            return 4;
                        }
                    } else if (this.g == 3) {
                        if (i == 3) {
                            return 2;
                        }

                        if (i == 4) {
                            return 1;
                        }

                        if (i == 2) {
                            return 3;
                        }

                        if (i == 1) {
                            return 4;
                        }
                    }
                } else if (block != Blocks.TRIPWIRE_SOURCE && !(block instanceof BlockDirectional)) {
                    if (block == Blocks.PISTON || block == Blocks.PISTON_STICKY || block == Blocks.LEVER || block == Blocks.DISPENSER) {
                        if (this.g == 0) {
                            if (i == 2 || i == 3) {
                                return Facing.OPPOSITE_FACING[i];
                            }
                        } else if (this.g == 1) {
                            if (i == 2) {
                                return 4;
                            }

                            if (i == 3) {
                                return 5;
                            }

                            if (i == 4) {
                                return 2;
                            }

                            if (i == 5) {
                                return 3;
                            }
                        } else if (this.g == 3) {
                            if (i == 2) {
                                return 5;
                            }

                            if (i == 3) {
                                return 4;
                            }

                            if (i == 4) {
                                return 2;
                            }

                            if (i == 5) {
                                return 3;
                            }
                        }
                    }
                } else if (this.g == 0) {
                    if (i == 0 || i == 2) {
                        return Direction.f[i];
                    }
                } else if (this.g == 1) {
                    if (i == 2) {
                        return 1;
                    }

                    if (i == 0) {
                        return 3;
                    }

                    if (i == 1) {
                        return 2;
                    }

                    if (i == 3) {
                        return 0;
                    }
                } else if (this.g == 3) {
                    if (i == 2) {
                        return 3;
                    }

                    if (i == 0) {
                        return 1;
                    }

                    if (i == 1) {
                        return 2;
                    }

                    if (i == 3) {
                        return 0;
                    }
                }
            } else if (this.g == 0) {
                if (i == 2) {
                    return 3;
                }

                if (i == 3) {
                    return 2;
                }
            } else if (this.g == 1) {
                if (i == 0) {
                    return 2;
                }

                if (i == 1) {
                    return 3;
                }

                if (i == 2) {
                    return 0;
                }

                if (i == 3) {
                    return 1;
                }
            } else if (this.g == 3) {
                if (i == 0) {
                    return 2;
                }

                if (i == 1) {
                    return 3;
                }

                if (i == 2) {
                    return 1;
                }

                if (i == 3) {
                    return 0;
                }
            }
        } else if (this.g == 0) {
            if (i == 0) {
                return 2;
            }

            if (i == 2) {
                return 0;
            }
        } else {
            if (this.g == 1) {
                return i + 1 & 3;
            }

            if (this.g == 3) {
                return i + 3 & 3;
            }
        }

        return i;
    }

    protected void a(World world, Block block, int i, int j, int k, int l, StructureBoundingBox structureboundingbox) {
        int i1 = this.a(j, l);
        int j1 = this.a(k);
        int k1 = this.b(j, l);

        if (structureboundingbox.b(i1, j1, k1)) {
            world.setTypeAndData(i1, j1, k1, block, i, 2);
        }
    }

    protected Block a(World world, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        int l = this.a(i, k);
        int i1 = this.a(j);
        int j1 = this.b(i, k);

        return !structureboundingbox.b(l, i1, j1) ? Blocks.AIR : world.getType(l, i1, j1);
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1) {
        for (int k1 = j; k1 <= i1; ++k1) {
            for (int l1 = i; l1 <= l; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    this.a(world, Blocks.AIR, 0, l1, k1, i2, structureboundingbox);
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, Block block, Block block1, boolean flag) {
        for (int k1 = j; k1 <= i1; ++k1) {
            for (int l1 = i; l1 <= l; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    if (!flag || this.a(world, l1, k1, i2, structureboundingbox).getMaterial() != Material.AIR) {
                        if (k1 != j && k1 != i1 && l1 != i && l1 != l && i2 != k && i2 != j1) {
                            this.a(world, block1, 0, l1, k1, i2, structureboundingbox);
                        } else {
                            this.a(world, block, 0, l1, k1, i2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, Block block, int k1, Block block1, int l1, boolean flag) {
        for (int i2 = j; i2 <= i1; ++i2) {
            for (int j2 = i; j2 <= l; ++j2) {
                for (int k2 = k; k2 <= j1; ++k2) {
                    if (!flag || this.a(world, j2, i2, k2, structureboundingbox).getMaterial() != Material.AIR) {
                        if (i2 != j && i2 != i1 && j2 != i && j2 != l && k2 != k && k2 != j1) {
                            this.a(world, block1, l1, j2, i2, k2, structureboundingbox);
                        } else {
                            this.a(world, block, k1, j2, i2, k2, structureboundingbox);
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
                    if (!flag || this.a(world, l1, k1, i2, structureboundingbox).getMaterial() != Material.AIR) {
                        structurepieceblockselector.a(random, l1, k1, i2, k1 == j || k1 == i1 || l1 == i || l1 == l || i2 == k || i2 == j1);
                        this.a(world, structurepieceblockselector.a(), structurepieceblockselector.b(), l1, k1, i2, structureboundingbox);
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int i, int j, int k, int l, int i1, int j1, Block block, Block block1, boolean flag) {
        for (int k1 = j; k1 <= i1; ++k1) {
            for (int l1 = i; l1 <= l; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    if (random.nextFloat() <= f && (!flag || this.a(world, l1, k1, i2, structureboundingbox).getMaterial() != Material.AIR)) {
                        if (k1 != j && k1 != i1 && l1 != i && l1 != l && i2 != k && i2 != j1) {
                            this.a(world, block1, 0, l1, k1, i2, structureboundingbox);
                        } else {
                            this.a(world, block, 0, l1, k1, i2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int i, int j, int k, Block block, int l) {
        if (random.nextFloat() < f) {
            this.a(world, block, l, i, j, k, structureboundingbox);
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, Block block, boolean flag) {
        float f = (float) (l - i + 1);
        float f1 = (float) (i1 - j + 1);
        float f2 = (float) (j1 - k + 1);
        float f3 = (float) i + f / 2.0F;
        float f4 = (float) k + f2 / 2.0F;

        for (int k1 = j; k1 <= i1; ++k1) {
            float f5 = (float) (k1 - j) / f1;

            for (int l1 = i; l1 <= l; ++l1) {
                float f6 = ((float) l1 - f3) / (f * 0.5F);

                for (int i2 = k; i2 <= j1; ++i2) {
                    float f7 = ((float) i2 - f4) / (f2 * 0.5F);

                    if (!flag || this.a(world, l1, k1, i2, structureboundingbox).getMaterial() != Material.AIR) {
                        float f8 = f6 * f6 + f5 * f5 + f7 * f7;

                        if (f8 <= 1.05F) {
                            this.a(world, block, 0, l1, k1, i2, structureboundingbox);
                        }
                    }
                }
            }
        }
    }

    protected void b(World world, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        int l = this.a(i, k);
        int i1 = this.a(j);
        int j1 = this.b(i, k);

        if (structureboundingbox.b(l, i1, j1)) {
            while (!world.isEmpty(l, i1, j1) && i1 < 255) {
                world.setTypeAndData(l, i1, j1, Blocks.AIR, 0, 2);
                ++i1;
            }
        }
    }

    protected void b(World world, Block block, int i, int j, int k, int l, StructureBoundingBox structureboundingbox) {
        int i1 = this.a(j, l);
        int j1 = this.a(k);
        int k1 = this.b(j, l);

        if (structureboundingbox.b(i1, j1, k1)) {
            while ((world.isEmpty(i1, j1, k1) || world.getType(i1, j1, k1).getMaterial().isLiquid()) && j1 > 1) {
                world.setTypeAndData(i1, j1, k1, block, i, 2);
                --j1;
            }
        }
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, StructurePieceTreasure[] astructurepiecetreasure, int l) {
        int i1 = this.a(i, k);
        int j1 = this.a(j);
        int k1 = this.b(i, k);

        if (structureboundingbox.b(i1, j1, k1) && world.getType(i1, j1, k1) != Blocks.CHEST) {
            world.setTypeAndData(i1, j1, k1, Blocks.CHEST, 0, 2);
            TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i1, j1, k1);

            if (tileentitychest != null) {
                StructurePieceTreasure.a(random, astructurepiecetreasure, (IInventory) tileentitychest, l);
            }

            return true;
        } else {
            return false;
        }
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, int l, StructurePieceTreasure[] astructurepiecetreasure, int i1) {
        int j1 = this.a(i, k);
        int k1 = this.a(j);
        int l1 = this.b(i, k);

        if (structureboundingbox.b(j1, k1, l1) && world.getType(j1, k1, l1) != Blocks.DISPENSER) {
            world.setTypeAndData(j1, k1, l1, Blocks.DISPENSER, this.a(Blocks.DISPENSER, l), 2);
            TileEntityDispenser tileentitydispenser = (TileEntityDispenser) world.getTileEntity(j1, k1, l1);

            if (tileentitydispenser != null) {
                StructurePieceTreasure.a(random, astructurepiecetreasure, tileentitydispenser, i1);
            }

            return true;
        } else {
            return false;
        }
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, int l) {
        int i1 = this.a(i, k);
        int j1 = this.a(j);
        int k1 = this.b(i, k);

        if (structureboundingbox.b(i1, j1, k1)) {
            ItemDoor.place(world, i1, j1, k1, l, Blocks.WOODEN_DOOR);
        }
    }
}
