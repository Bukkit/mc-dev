package net.minecraft.server;

import java.util.Random;

public class WorldGenLakes extends WorldGenerator {

    private Block a;

    public WorldGenLakes(Block block) {
        this.a = block;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        i -= 8;

        for (k -= 8; j > 5 && world.isEmpty(i, j, k); --j) {
            ;
        }

        if (j <= 4) {
            return false;
        } else {
            j -= 4;
            boolean[] aboolean = new boolean[2048];
            int l = random.nextInt(4) + 4;

            int i1;

            for (i1 = 0; i1 < l; ++i1) {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int j1 = 1; j1 < 15; ++j1) {
                    for (int k1 = 1; k1 < 15; ++k1) {
                        for (int l1 = 1; l1 < 7; ++l1) {
                            double d6 = ((double) j1 - d3) / (d0 / 2.0D);
                            double d7 = ((double) l1 - d4) / (d1 / 2.0D);
                            double d8 = ((double) k1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D) {
                                aboolean[(j1 * 16 + k1) * 8 + l1] = true;
                            }
                        }
                    }
                }
            }

            boolean flag;
            int i2;
            int j2;

            for (i1 = 0; i1 < 16; ++i1) {
                for (i2 = 0; i2 < 16; ++i2) {
                    for (j2 = 0; j2 < 8; ++j2) {
                        flag = !aboolean[(i1 * 16 + i2) * 8 + j2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + i2) * 8 + j2] || i1 > 0 && aboolean[((i1 - 1) * 16 + i2) * 8 + j2] || i2 < 15 && aboolean[(i1 * 16 + i2 + 1) * 8 + j2] || i2 > 0 && aboolean[(i1 * 16 + (i2 - 1)) * 8 + j2] || j2 < 7 && aboolean[(i1 * 16 + i2) * 8 + j2 + 1] || j2 > 0 && aboolean[(i1 * 16 + i2) * 8 + (j2 - 1)]);
                        if (flag) {
                            Material material = world.getType(i + i1, j + j2, k + i2).getMaterial();

                            if (j2 >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (j2 < 4 && !material.isBuildable() && world.getType(i + i1, j + j2, k + i2) != this.a) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (i1 = 0; i1 < 16; ++i1) {
                for (i2 = 0; i2 < 16; ++i2) {
                    for (j2 = 0; j2 < 8; ++j2) {
                        if (aboolean[(i1 * 16 + i2) * 8 + j2]) {
                            world.setTypeAndData(i + i1, j + j2, k + i2, j2 >= 4 ? Blocks.AIR : this.a, 0, 2);
                        }
                    }
                }
            }

            for (i1 = 0; i1 < 16; ++i1) {
                for (i2 = 0; i2 < 16; ++i2) {
                    for (j2 = 4; j2 < 8; ++j2) {
                        if (aboolean[(i1 * 16 + i2) * 8 + j2] && world.getType(i + i1, j + j2 - 1, k + i2) == Blocks.DIRT && world.b(EnumSkyBlock.SKY, i + i1, j + j2, k + i2) > 0) {
                            BiomeBase biomebase = world.getBiome(i + i1, k + i2);

                            if (biomebase.ai == Blocks.MYCEL) {
                                world.setTypeAndData(i + i1, j + j2 - 1, k + i2, Blocks.MYCEL, 0, 2);
                            } else {
                                world.setTypeAndData(i + i1, j + j2 - 1, k + i2, Blocks.GRASS, 0, 2);
                            }
                        }
                    }
                }
            }

            if (this.a.getMaterial() == Material.LAVA) {
                for (i1 = 0; i1 < 16; ++i1) {
                    for (i2 = 0; i2 < 16; ++i2) {
                        for (j2 = 0; j2 < 8; ++j2) {
                            flag = !aboolean[(i1 * 16 + i2) * 8 + j2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + i2) * 8 + j2] || i1 > 0 && aboolean[((i1 - 1) * 16 + i2) * 8 + j2] || i2 < 15 && aboolean[(i1 * 16 + i2 + 1) * 8 + j2] || i2 > 0 && aboolean[(i1 * 16 + (i2 - 1)) * 8 + j2] || j2 < 7 && aboolean[(i1 * 16 + i2) * 8 + j2 + 1] || j2 > 0 && aboolean[(i1 * 16 + i2) * 8 + (j2 - 1)]);
                            if (flag && (j2 < 4 || random.nextInt(2) != 0) && world.getType(i + i1, j + j2, k + i2).getMaterial().isBuildable()) {
                                world.setTypeAndData(i + i1, j + j2, k + i2, Blocks.STONE, 0, 2);
                            }
                        }
                    }
                }
            }

            if (this.a.getMaterial() == Material.WATER) {
                for (i1 = 0; i1 < 16; ++i1) {
                    for (i2 = 0; i2 < 16; ++i2) {
                        byte b0 = 4;

                        if (world.r(i + i1, j + b0, k + i2)) {
                            world.setTypeAndData(i + i1, j + b0, k + i2, Blocks.ICE, 0, 2);
                        }
                    }
                }
            }

            return true;
        }
    }
}
