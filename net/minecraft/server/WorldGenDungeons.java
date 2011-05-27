package net.minecraft.server;

import java.util.Random;

public class WorldGenDungeons extends WorldGenerator {

    public WorldGenDungeons() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        byte b0 = 3;
        int l = random.nextInt(2) + 2;
        int i1 = random.nextInt(2) + 2;
        int j1 = 0;

        int k1;
        int l1;
        int i2;

        for (k1 = i - l - 1; k1 <= i + l + 1; ++k1) {
            for (l1 = j - 1; l1 <= j + b0 + 1; ++l1) {
                for (i2 = k - i1 - 1; i2 <= k + i1 + 1; ++i2) {
                    Material material = world.getMaterial(k1, l1, i2);

                    if (l1 == j - 1 && !material.isBuildable()) {
                        return false;
                    }

                    if (l1 == j + b0 + 1 && !material.isBuildable()) {
                        return false;
                    }

                    if ((k1 == i - l - 1 || k1 == i + l + 1 || i2 == k - i1 - 1 || i2 == k + i1 + 1) && l1 == j && world.isEmpty(k1, l1, i2) && world.isEmpty(k1, l1 + 1, i2)) {
                        ++j1;
                    }
                }
            }
        }

        if (j1 >= 1 && j1 <= 5) {
            for (k1 = i - l - 1; k1 <= i + l + 1; ++k1) {
                for (l1 = j + b0; l1 >= j - 1; --l1) {
                    for (i2 = k - i1 - 1; i2 <= k + i1 + 1; ++i2) {
                        if (k1 != i - l - 1 && l1 != j - 1 && i2 != k - i1 - 1 && k1 != i + l + 1 && l1 != j + b0 + 1 && i2 != k + i1 + 1) {
                            world.setTypeId(k1, l1, i2, 0);
                        } else if (l1 >= 0 && !world.getMaterial(k1, l1 - 1, i2).isBuildable()) {
                            world.setTypeId(k1, l1, i2, 0);
                        } else if (world.getMaterial(k1, l1, i2).isBuildable()) {
                            if (l1 == j - 1 && random.nextInt(4) != 0) {
                                world.setTypeId(k1, l1, i2, Block.MOSSY_COBBLESTONE.id);
                            } else {
                                world.setTypeId(k1, l1, i2, Block.COBBLESTONE.id);
                            }
                        }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2) {
                l1 = 0;

                while (true) {
                    if (l1 < 3) {
                        label204: {
                            i2 = i + random.nextInt(l * 2 + 1) - l;
                            int j2 = k + random.nextInt(i1 * 2 + 1) - i1;

                            if (world.isEmpty(i2, j, j2)) {
                                int k2 = 0;

                                if (world.getMaterial(i2 - 1, j, j2).isBuildable()) {
                                    ++k2;
                                }

                                if (world.getMaterial(i2 + 1, j, j2).isBuildable()) {
                                    ++k2;
                                }

                                if (world.getMaterial(i2, j, j2 - 1).isBuildable()) {
                                    ++k2;
                                }

                                if (world.getMaterial(i2, j, j2 + 1).isBuildable()) {
                                    ++k2;
                                }

                                if (k2 == 1) {
                                    world.setTypeId(i2, j, j2, Block.CHEST.id);
                                    TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i2, j, j2);

                                    for (int l2 = 0; l2 < 8; ++l2) {
                                        ItemStack itemstack = this.a(random);

                                        if (itemstack != null) {
                                            tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), itemstack);
                                        }
                                    }
                                    break label204;
                                }
                            }

                            ++l1;
                            continue;
                        }
                    }

                    ++k1;
                    break;
                }
            }

            world.setTypeId(i, j, k, Block.MOB_SPAWNER.id);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(i, j, k);

            tileentitymobspawner.a(this.b(random));
            return true;
        } else {
            return false;
        }
    }

    private ItemStack a(Random random) {
        int i = random.nextInt(11);

        return i == 0 ? new ItemStack(Item.SADDLE) : (i == 1 ? new ItemStack(Item.IRON_INGOT, random.nextInt(4) + 1) : (i == 2 ? new ItemStack(Item.BREAD) : (i == 3 ? new ItemStack(Item.WHEAT, random.nextInt(4) + 1) : (i == 4 ? new ItemStack(Item.SULPHUR, random.nextInt(4) + 1) : (i == 5 ? new ItemStack(Item.STRING, random.nextInt(4) + 1) : (i == 6 ? new ItemStack(Item.BUCKET) : (i == 7 && random.nextInt(100) == 0 ? new ItemStack(Item.GOLDEN_APPLE) : (i == 8 && random.nextInt(2) == 0 ? new ItemStack(Item.REDSTONE, random.nextInt(4) + 1) : (i == 9 && random.nextInt(10) == 0 ? new ItemStack(Item.byId[Item.GOLD_RECORD.id + random.nextInt(2)]) : (i == 10 ? new ItemStack(Item.INK_SACK, 1, 3) : null))))))))));
    }

    private String b(Random random) {
        int i = random.nextInt(4);

        return i == 0 ? "Skeleton" : (i == 1 ? "Zombie" : (i == 2 ? "Zombie" : (i == 3 ? "Spider" : "")));
    }
}
