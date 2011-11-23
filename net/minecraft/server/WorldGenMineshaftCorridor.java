package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCorridor extends StructurePiece {

    private final boolean a;
    private final boolean b;
    private boolean c;
    private int d;

    public WorldGenMineshaftCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j) {
        super(i);
        this.h = j;
        this.g = structureboundingbox;
        this.a = random.nextInt(3) == 0;
        this.b = !this.a && random.nextInt(23) == 0;
        if (this.h != 2 && this.h != 0) {
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
        int i = this.c();
        int j = random.nextInt(4);

        switch (this.h) {
        case 0:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a, this.g.b - 1 + random.nextInt(3), this.g.f + 1, this.h, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b - 1 + random.nextInt(3), this.g.f - 3, 1, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b - 1 + random.nextInt(3), this.g.f - 3, 3, i);
            }
            break;

        case 1:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b - 1 + random.nextInt(3), this.g.c, this.h, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a, this.g.b - 1 + random.nextInt(3), this.g.c - 1, 2, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a, this.g.b - 1 + random.nextInt(3), this.g.f + 1, 0, i);
            }
            break;

        case 2:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a, this.g.b - 1 + random.nextInt(3), this.g.c - 1, this.h, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b - 1 + random.nextInt(3), this.g.c, 1, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b - 1 + random.nextInt(3), this.g.c, 3, i);
            }
            break;

        case 3:
            if (j <= 1) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b - 1 + random.nextInt(3), this.g.c, this.h, i);
            } else if (j == 2) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d - 3, this.g.b - 1 + random.nextInt(3), this.g.c - 1, 2, i);
            } else {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d - 3, this.g.b - 1 + random.nextInt(3), this.g.f + 1, 0, i);
            }
        }

        if (i < 8) {
            int k;
            int l;

            if (this.h != 2 && this.h != 0) {
                for (k = this.g.a + 3; k + 3 <= this.g.d; k += 5) {
                    l = random.nextInt(5);
                    if (l == 0) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, k, this.g.b, this.g.c - 1, 2, i + 1);
                    } else if (l == 1) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, k, this.g.b, this.g.f + 1, 0, i + 1);
                    }
                }
            } else {
                for (k = this.g.c + 3; k + 3 <= this.g.f; k += 5) {
                    l = random.nextInt(5);
                    if (l == 0) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b, k, 1, i + 1);
                    } else if (l == 1) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b, k, 3, i + 1);
                    }
                }
            }
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            int i = this.d * 5 - 1;

            this.a(world, structureboundingbox, 0, 0, 0, 2, 1, i, 0, 0, false);
            this.a(world, structureboundingbox, random, 0.8F, 0, 2, 0, 2, 2, i, 0, 0, false);
            if (this.b) {
                this.a(world, structureboundingbox, random, 0.6F, 0, 0, 0, 2, 1, i, Block.WEB.id, 0, false);
            }

            int j;
            int k;

            for (j = 0; j < this.d; ++j) {
                k = 2 + j * 5;
                this.a(world, structureboundingbox, 0, 0, k, 0, 1, k, Block.FENCE.id, 0, false);
                this.a(world, structureboundingbox, 2, 0, k, 2, 1, k, Block.FENCE.id, 0, false);
                if (random.nextInt(4) != 0) {
                    this.a(world, structureboundingbox, 0, 2, k, 2, 2, k, Block.WOOD.id, 0, false);
                } else {
                    this.a(world, structureboundingbox, 0, 2, k, 0, 2, k, Block.WOOD.id, 0, false);
                    this.a(world, structureboundingbox, 2, 2, k, 2, 2, k, Block.WOOD.id, 0, false);
                }

                this.a(world, structureboundingbox, random, 0.1F, 0, 2, k - 1, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.1F, 2, 2, k - 1, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.1F, 0, 2, k + 1, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.1F, 2, 2, k + 1, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.05F, 0, 2, k - 2, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.05F, 2, 2, k - 2, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.05F, 0, 2, k + 2, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.05F, 2, 2, k + 2, Block.WEB.id, 0);
                this.a(world, structureboundingbox, random, 0.05F, 1, 2, k - 1, Block.TORCH.id, 0);
                this.a(world, structureboundingbox, random, 0.05F, 1, 2, k + 1, Block.TORCH.id, 0);
                if (random.nextInt(100) == 0) {
                    this.a(world, structureboundingbox, random, 2, 0, k - 1, WorldGenMineshaftPieces.a(), 3 + random.nextInt(4));
                }

                if (random.nextInt(100) == 0) {
                    this.a(world, structureboundingbox, random, 0, 0, k + 1, WorldGenMineshaftPieces.a(), 3 + random.nextInt(4));
                }

                if (this.b && !this.c) {
                    int l = this.b(0);
                    int i1 = k - 1 + random.nextInt(3);
                    int j1 = this.a(1, i1);

                    i1 = this.b(1, i1);
                    if (structureboundingbox.b(j1, l, i1)) {
                        this.c = true;
                        world.setTypeId(j1, l, i1, Block.MOB_SPAWNER.id);
                        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(j1, l, i1);

                        if (tileentitymobspawner != null) {
                            tileentitymobspawner.a("CaveSpider");
                        }
                    }
                }
            }

            if (this.a) {
                for (j = 0; j <= i; ++j) {
                    k = this.a(world, 1, -1, j, structureboundingbox);
                    if (k > 0 && Block.o[k]) {
                        this.a(world, structureboundingbox, random, 0.7F, 1, 0, j, Block.RAILS.id, this.c(Block.RAILS.id, 0));
                    }
                }
            }

            return true;
        }
    }
}
