package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCorridor extends StructurePiece {

    private boolean a;
    private boolean b;
    private boolean c;
    private int d;

    public WorldGenMineshaftCorridor() {}

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setBoolean("hr", this.a);
        nbttagcompound.setBoolean("sc", this.b);
        nbttagcompound.setBoolean("hps", this.c);
        nbttagcompound.setInt("Num", this.d);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.getBoolean("hr");
        this.b = nbttagcompound.getBoolean("sc");
        this.c = nbttagcompound.getBoolean("hps");
        this.d = nbttagcompound.getInt("Num");
    }

    public WorldGenMineshaftCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.g = j;
        this.f = structureboundingbox;
        this.a = random.nextInt(3) == 0;
        this.b = !this.a && random.nextInt(23) == 0;
        if (this.g != 2 && this.g != 0) {
            this.d = structureboundingbox.b() / 5;
        } else {
            this.d = structureboundingbox.d() / 5;
        }
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);

        int i1;

        for (i1 = random.nextInt(3) + 2; i1 > 0; --i1) {
            int j1 = i1 * 5;

            switch (l) {
            case 0:
                structureboundingbox.d = i + 2;
                structureboundingbox.f = k + (j1 - 1);
                break;

            case 1:
                structureboundingbox.a = i - (j1 - 1);
                structureboundingbox.f = k + 2;
                break;

            case 2:
                structureboundingbox.d = i + 2;
                structureboundingbox.c = k - (j1 - 1);
                break;

            case 3:
                structureboundingbox.d = i + (j1 - 1);
                structureboundingbox.f = k + 2;
            }

            if (StructurePiece.a(list, structureboundingbox) == null) {
                break;
            }
        }

        return i1 > 0 ? structureboundingbox : null;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.d();
        int j = random.nextInt(4);

        switch (this.g) {
        case 0:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a, this.f.b - 1 + random.nextInt(3), this.f.f + 1, this.g, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b - 1 + random.nextInt(3), this.f.f - 3, 1, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b - 1 + random.nextInt(3), this.f.f - 3, 3, i);
            }
            break;

        case 1:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b - 1 + random.nextInt(3), this.f.c, this.g, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a, this.f.b - 1 + random.nextInt(3), this.f.c - 1, 2, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a, this.f.b - 1 + random.nextInt(3), this.f.f + 1, 0, i);
            }
            break;

        case 2:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a, this.f.b - 1 + random.nextInt(3), this.f.c - 1, this.g, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b - 1 + random.nextInt(3), this.f.c, 1, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b - 1 + random.nextInt(3), this.f.c, 3, i);
            }
            break;

        case 3:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b - 1 + random.nextInt(3), this.f.c, this.g, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d - 3, this.f.b - 1 + random.nextInt(3), this.f.c - 1, 2, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d - 3, this.f.b - 1 + random.nextInt(3), this.f.f + 1, 0, i);
            }
        }

        if (i < 8) {
            int k;
            int l;

            if (this.g != 2 && this.g != 0) {
                for (k = this.f.a + 3; k + 3 <= this.f.d; k += 5) {
                    l = random.nextInt(5);
                    if (l == 0) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, k, this.f.b, this.f.c - 1, 2, i + 1);
                    } else if (l == 1) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, k, this.f.b, this.f.f + 1, 0, i + 1);
                    }
                }
            } else {
                for (k = this.f.c + 3; k + 3 <= this.f.f; k += 5) {
                    l = random.nextInt(5);
                    if (l == 0) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b, k, 1, i + 1);
                    } else if (l == 1) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b, k, 3, i + 1);
                    }
                }
            }
        }
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, StructurePieceTreasure[] astructurepiecetreasure, int l) {
        int i1 = this.a(i, k);
        int j1 = this.a(j);
        int k1 = this.b(i, k);

        if (structureboundingbox.b(i1, j1, k1) && world.getType(i1, j1, k1).getMaterial() == Material.AIR) {
            int l1 = random.nextBoolean() ? 1 : 0;

            world.setTypeAndData(i1, j1, k1, Blocks.RAILS, this.a(Blocks.RAILS, l1), 2);
            EntityMinecartChest entityminecartchest = new EntityMinecartChest(world, (double) ((float) i1 + 0.5F), (double) ((float) j1 + 0.5F), (double) ((float) k1 + 0.5F));

            StructurePieceTreasure.a(random, astructurepiecetreasure, (IInventory) entityminecartchest, l);
            world.addEntity(entityminecartchest);
            return true;
        } else {
            return false;
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            boolean flag = false;
            boolean flag1 = true;
            boolean flag2 = false;
            boolean flag3 = true;
            int i = this.d * 5 - 1;

            this.a(world, structureboundingbox, 0, 0, 0, 2, 1, i, Blocks.AIR, Blocks.AIR, false);
            this.a(world, structureboundingbox, random, 0.8F, 0, 2, 0, 2, 2, i, Blocks.AIR, Blocks.AIR, false);
            if (this.b) {
                this.a(world, structureboundingbox, random, 0.6F, 0, 0, 0, 2, 1, i, Blocks.WEB, Blocks.AIR, false);
            }

            int j;
            int k;

            for (j = 0; j < this.d; ++j) {
                k = 2 + j * 5;
                this.a(world, structureboundingbox, 0, 0, k, 0, 1, k, Blocks.FENCE, Blocks.AIR, false);
                this.a(world, structureboundingbox, 2, 0, k, 2, 1, k, Blocks.FENCE, Blocks.AIR, false);
                if (random.nextInt(4) == 0) {
                    this.a(world, structureboundingbox, 0, 2, k, 0, 2, k, Blocks.WOOD, Blocks.AIR, false);
                    this.a(world, structureboundingbox, 2, 2, k, 2, 2, k, Blocks.WOOD, Blocks.AIR, false);
                } else {
                    this.a(world, structureboundingbox, 0, 2, k, 2, 2, k, Blocks.WOOD, Blocks.AIR, false);
                }

                this.a(world, structureboundingbox, random, 0.1F, 0, 2, k - 1, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.1F, 2, 2, k - 1, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.1F, 0, 2, k + 1, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.1F, 2, 2, k + 1, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.05F, 0, 2, k - 2, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.05F, 2, 2, k - 2, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.05F, 0, 2, k + 2, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.05F, 2, 2, k + 2, Blocks.WEB, 0);
                this.a(world, structureboundingbox, random, 0.05F, 1, 2, k - 1, Blocks.TORCH, 0);
                this.a(world, structureboundingbox, random, 0.05F, 1, 2, k + 1, Blocks.TORCH, 0);
                if (random.nextInt(100) == 0) {
                    this.a(world, structureboundingbox, random, 2, 0, k - 1, StructurePieceTreasure.a(WorldGenMineshaftPieces.b(), new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 3 + random.nextInt(4));
                }

                if (random.nextInt(100) == 0) {
                    this.a(world, structureboundingbox, random, 0, 0, k + 1, StructurePieceTreasure.a(WorldGenMineshaftPieces.b(), new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 3 + random.nextInt(4));
                }

                if (this.b && !this.c) {
                    int l = this.a(0);
                    int i1 = k - 1 + random.nextInt(3);
                    int j1 = this.a(1, i1);

                    i1 = this.b(1, i1);
                    if (structureboundingbox.b(j1, l, i1)) {
                        this.c = true;
                        world.setTypeAndData(j1, l, i1, Blocks.MOB_SPAWNER, 0, 2);
                        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(j1, l, i1);

                        if (tileentitymobspawner != null) {
                            tileentitymobspawner.a().a("CaveSpider");
                        }
                    }
                }
            }

            for (j = 0; j <= 2; ++j) {
                for (k = 0; k <= i; ++k) {
                    byte b0 = -1;
                    Block block = this.a(world, j, b0, k, structureboundingbox);

                    if (block.getMaterial() == Material.AIR) {
                        byte b1 = -1;

                        this.a(world, Blocks.WOOD, 0, j, b1, k, structureboundingbox);
                    }
                }
            }

            if (this.a) {
                for (j = 0; j <= i; ++j) {
                    Block block1 = this.a(world, 1, -1, j, structureboundingbox);

                    if (block1.getMaterial() != Material.AIR && block1.j()) {
                        this.a(world, structureboundingbox, random, 0.7F, 1, 0, j, Blocks.RAILS, this.a(Blocks.RAILS, 0));
                    }
                }
            }

            return true;
        }
    }
}
