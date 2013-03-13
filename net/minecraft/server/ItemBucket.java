package net.minecraft.server;

public class ItemBucket extends Item {

    private int a;

    public ItemBucket(int i, int j) {
        super(i);
        this.maxStackSize = 1;
        this.a = j;
        this.a(CreativeModeTab.f);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        float f = 1.0F;
        double d0 = entityhuman.lastX + (entityhuman.locX - entityhuman.lastX) * (double) f;
        double d1 = entityhuman.lastY + (entityhuman.locY - entityhuman.lastY) * (double) f + 1.62D - (double) entityhuman.height;
        double d2 = entityhuman.lastZ + (entityhuman.locZ - entityhuman.lastZ) * (double) f;
        boolean flag = this.a == 0;
        MovingObjectPosition movingobjectposition = this.a(world, entityhuman, flag);

        if (movingobjectposition == null) {
            return itemstack;
        } else {
            if (movingobjectposition.type == EnumMovingObjectType.TILE) {
                int i = movingobjectposition.b;
                int j = movingobjectposition.c;
                int k = movingobjectposition.d;

                if (!world.a(entityhuman, i, j, k)) {
                    return itemstack;
                }

                if (this.a == 0) {
                    if (!entityhuman.a(i, j, k, movingobjectposition.face, itemstack)) {
                        return itemstack;
                    }

                    if (world.getMaterial(i, j, k) == Material.WATER && world.getData(i, j, k) == 0) {
                        world.setAir(i, j, k);
                        if (entityhuman.abilities.canInstantlyBuild) {
                            return itemstack;
                        }

                        if (--itemstack.count <= 0) {
                            return new ItemStack(Item.WATER_BUCKET);
                        }

                        if (!entityhuman.inventory.pickup(new ItemStack(Item.WATER_BUCKET))) {
                            entityhuman.drop(new ItemStack(Item.WATER_BUCKET.id, 1, 0));
                        }

                        return itemstack;
                    }

                    if (world.getMaterial(i, j, k) == Material.LAVA && world.getData(i, j, k) == 0) {
                        world.setAir(i, j, k);
                        if (entityhuman.abilities.canInstantlyBuild) {
                            return itemstack;
                        }

                        if (--itemstack.count <= 0) {
                            return new ItemStack(Item.LAVA_BUCKET);
                        }

                        if (!entityhuman.inventory.pickup(new ItemStack(Item.LAVA_BUCKET))) {
                            entityhuman.drop(new ItemStack(Item.LAVA_BUCKET.id, 1, 0));
                        }

                        return itemstack;
                    }
                } else {
                    if (this.a < 0) {
                        return new ItemStack(Item.BUCKET);
                    }

                    if (movingobjectposition.face == 0) {
                        --j;
                    }

                    if (movingobjectposition.face == 1) {
                        ++j;
                    }

                    if (movingobjectposition.face == 2) {
                        --k;
                    }

                    if (movingobjectposition.face == 3) {
                        ++k;
                    }

                    if (movingobjectposition.face == 4) {
                        --i;
                    }

                    if (movingobjectposition.face == 5) {
                        ++i;
                    }

                    if (!entityhuman.a(i, j, k, movingobjectposition.face, itemstack)) {
                        return itemstack;
                    }

                    if (this.a(world, d0, d1, d2, i, j, k) && !entityhuman.abilities.canInstantlyBuild) {
                        return new ItemStack(Item.BUCKET);
                    }
                }
            } else if (this.a == 0 && movingobjectposition.entity instanceof EntityCow) {
                return new ItemStack(Item.MILK_BUCKET);
            }

            return itemstack;
        }
    }

    public boolean a(World world, double d0, double d1, double d2, int i, int j, int k) {
        if (this.a <= 0) {
            return false;
        } else if (!world.isEmpty(i, j, k) && world.getMaterial(i, j, k).isBuildable()) {
            return false;
        } else {
            if (world.worldProvider.e && this.a == Block.WATER.id) {
                world.makeSound(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

                for (int l = 0; l < 8; ++l) {
                    world.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            } else {
                world.setTypeIdAndData(i, j, k, this.a, 0, 3);
            }

            return true;
        }
    }
}
