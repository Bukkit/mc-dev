package net.minecraft.server;

import java.util.Random;

public class BlockFlowerPot extends Block {

    public BlockFlowerPot(int i) {
        super(i, Material.ORIENTABLE);
        this.g();
    }

    public void g() {
        float f = 0.375F;
        float f1 = f / 2.0F;

        this.a(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, f, 0.5F + f1);
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 33;
    }

    public boolean b() {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack == null) {
            return false;
        } else if (world.getData(i, j, k) != 0) {
            return false;
        } else {
            int i1 = a(itemstack);

            if (i1 > 0) {
                world.setData(i, j, k, i1, 2);
                if (!entityhuman.abilities.canInstantlyBuild && --itemstack.count <= 0) {
                    entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public int getDropData(World world, int i, int j, int k) {
        ItemStack itemstack = p_(world.getData(i, j, k));

        return itemstack == null ? Item.FLOWER_POT.id : itemstack.getData();
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return super.canPlace(world, i, j, k) && world.w(i, j - 1, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.w(i, j - 1, k)) {
            this.c(world, i, j, k, world.getData(i, j, k), 0);
            world.setAir(i, j, k);
        }
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        super.dropNaturally(world, i, j, k, l, f, i1);
        if (l > 0) {
            ItemStack itemstack = p_(l);

            if (itemstack != null) {
                this.b(world, i, j, k, itemstack);
            }
        }
    }

    public int getDropType(int i, Random random, int j) {
        return Item.FLOWER_POT.id;
    }

    public static ItemStack p_(int i) {
        switch (i) {
        case 1:
            return new ItemStack(Block.RED_ROSE);

        case 2:
            return new ItemStack(Block.YELLOW_FLOWER);

        case 3:
            return new ItemStack(Block.SAPLING, 1, 0);

        case 4:
            return new ItemStack(Block.SAPLING, 1, 1);

        case 5:
            return new ItemStack(Block.SAPLING, 1, 2);

        case 6:
            return new ItemStack(Block.SAPLING, 1, 3);

        case 7:
            return new ItemStack(Block.RED_MUSHROOM);

        case 8:
            return new ItemStack(Block.BROWN_MUSHROOM);

        case 9:
            return new ItemStack(Block.CACTUS);

        case 10:
            return new ItemStack(Block.DEAD_BUSH);

        case 11:
            return new ItemStack(Block.LONG_GRASS, 1, 2);

        default:
            return null;
        }
    }

    public static int a(ItemStack itemstack) {
        int i = itemstack.getItem().id;

        if (i == Block.RED_ROSE.id) {
            return 1;
        } else if (i == Block.YELLOW_FLOWER.id) {
            return 2;
        } else if (i == Block.CACTUS.id) {
            return 9;
        } else if (i == Block.BROWN_MUSHROOM.id) {
            return 8;
        } else if (i == Block.RED_MUSHROOM.id) {
            return 7;
        } else if (i == Block.DEAD_BUSH.id) {
            return 10;
        } else {
            if (i == Block.SAPLING.id) {
                switch (itemstack.getData()) {
                case 0:
                    return 3;

                case 1:
                    return 4;

                case 2:
                    return 5;

                case 3:
                    return 6;
                }
            }

            if (i == Block.LONG_GRASS.id) {
                switch (itemstack.getData()) {
                case 2:
                    return 11;
                }
            }

            return 0;
        }
    }
}
