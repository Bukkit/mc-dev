package net.minecraft.server;

public class ItemBucket extends Item {

    private int a;

    public ItemBucket(int i, int j) {
        super(i);
        this.aX = 1;
        this.aY = 64;
        this.a = j;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        float f = 1.0F;
        float f1 = entityhuman.y + (entityhuman.w - entityhuman.y) * f;
        float f2 = entityhuman.x + (entityhuman.v - entityhuman.x) * f;
        double d0 = entityhuman.m + (entityhuman.p - entityhuman.m) * (double) f;
        double d1 = entityhuman.n + (entityhuman.q - entityhuman.n) * (double) f + 1.62D - (double) entityhuman.H;
        double d2 = entityhuman.o + (entityhuman.r - entityhuman.o) * (double) f;
        Vec3D vec3d = Vec3D.b(d0, d1, d2);
        float f3 = MathHelper.b(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.a(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.b(-f1 * 0.017453292F);
        float f6 = MathHelper.a(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3D vec3d1 = vec3d.c((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        MovingObjectPosition movingobjectposition = world.a(vec3d, vec3d1, this.a == 0);

        if (movingobjectposition == null) {
            return itemstack;
        } else {
            if (movingobjectposition.a == 0) {
                int i = movingobjectposition.b;
                int j = movingobjectposition.c;
                int k = movingobjectposition.d;

                if (!world.a(entityhuman, i, j, k)) {
                    return itemstack;
                }

                if (this.a == 0) {
                    if (world.c(i, j, k) == Material.f && world.b(i, j, k) == 0) {
                        world.d(i, j, k, 0);
                        return new ItemStack(Item.WATER_BUCKET);
                    }

                    if (world.c(i, j, k) == Material.g && world.b(i, j, k) == 0) {
                        world.d(i, j, k, 0);
                        return new ItemStack(Item.LAVA_BUCKET);
                    }
                } else {
                    if (this.a < 0) {
                        return new ItemStack(Item.BUCKET);
                    }

                    if (movingobjectposition.e == 0) {
                        --j;
                    }

                    if (movingobjectposition.e == 1) {
                        ++j;
                    }

                    if (movingobjectposition.e == 2) {
                        --k;
                    }

                    if (movingobjectposition.e == 3) {
                        ++k;
                    }

                    if (movingobjectposition.e == 4) {
                        --i;
                    }

                    if (movingobjectposition.e == 5) {
                        ++i;
                    }

                    if (world.e(i, j, k) || !world.c(i, j, k).a()) {
                        if (world.q.d && this.a == Block.WATER.bh) {
                            world.a(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (world.l.nextFloat() - world.l.nextFloat()) * 0.8F);

                            for (int l = 0; l < 8; ++l) {
                                world.a("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                            }
                        } else {
                            world.b(i, j, k, this.a, 0);
                        }

                        return new ItemStack(Item.BUCKET);
                    }
                }
            } else if (this.a == 0 && movingobjectposition.g instanceof EntityCow) {
                return new ItemStack(Item.MILK_BUCKET);
            }

            return itemstack;
        }
    }
}
