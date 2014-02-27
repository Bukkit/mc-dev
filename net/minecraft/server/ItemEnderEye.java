package net.minecraft.server;

public class ItemEnderEye extends Item {

    public ItemEnderEye() {
        this.a(CreativeModeTab.f);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        Block block = world.getType(i, j, k);
        int i1 = world.getData(i, j, k);

        if (entityhuman.a(i, j, k, l, itemstack) && block == Blocks.ENDER_PORTAL_FRAME && !BlockEnderPortalFrame.b(i1)) {
            if (world.isStatic) {
                return true;
            } else {
                world.setData(i, j, k, i1 + 4, 2);
                world.f(i, j, k, Blocks.ENDER_PORTAL_FRAME);
                --itemstack.count;

                int j1;

                for (j1 = 0; j1 < 16; ++j1) {
                    double d0 = (double) ((float) i + (5.0F + g.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double) ((float) j + 0.8125F);
                    double d2 = (double) ((float) k + (5.0F + g.nextFloat() * 6.0F) / 16.0F);
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;

                    world.addParticle("smoke", d0, d1, d2, d3, d4, d5);
                }

                j1 = i1 & 3;
                int k1 = 0;
                int l1 = 0;
                boolean flag = false;
                boolean flag1 = true;
                int i2 = Direction.g[j1];

                int j2;
                int k2;
                int l2;

                for (l2 = -2; l2 <= 2; ++l2) {
                    j2 = i + Direction.a[i2] * l2;
                    k2 = k + Direction.b[i2] * l2;
                    if (world.getType(j2, j, k2) == Blocks.ENDER_PORTAL_FRAME) {
                        if (!BlockEnderPortalFrame.b(world.getData(j2, j, k2))) {
                            flag1 = false;
                            break;
                        }

                        l1 = l2;
                        if (!flag) {
                            k1 = l2;
                            flag = true;
                        }
                    }
                }

                if (flag1 && l1 == k1 + 2) {
                    for (l2 = k1; l2 <= l1; ++l2) {
                        j2 = i + Direction.a[i2] * l2;
                        k2 = k + Direction.b[i2] * l2;
                        j2 += Direction.a[j1] * 4;
                        k2 += Direction.b[j1] * 4;
                        if (world.getType(j2, j, k2) != Blocks.ENDER_PORTAL_FRAME || !BlockEnderPortalFrame.b(world.getData(j2, j, k2))) {
                            flag1 = false;
                            break;
                        }
                    }

                    int i3;

                    for (l2 = k1 - 1; l2 <= l1 + 1; l2 += 4) {
                        for (j2 = 1; j2 <= 3; ++j2) {
                            k2 = i + Direction.a[i2] * l2;
                            i3 = k + Direction.b[i2] * l2;
                            k2 += Direction.a[j1] * j2;
                            i3 += Direction.b[j1] * j2;
                            if (world.getType(k2, j, i3) != Blocks.ENDER_PORTAL_FRAME || !BlockEnderPortalFrame.b(world.getData(k2, j, i3))) {
                                flag1 = false;
                                break;
                            }
                        }
                    }

                    if (flag1) {
                        for (l2 = k1; l2 <= l1; ++l2) {
                            for (j2 = 1; j2 <= 3; ++j2) {
                                k2 = i + Direction.a[i2] * l2;
                                i3 = k + Direction.b[i2] * l2;
                                k2 += Direction.a[j1] * j2;
                                i3 += Direction.b[j1] * j2;
                                world.setTypeAndData(k2, j, i3, Blocks.ENDER_PORTAL, 0, 2);
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        MovingObjectPosition movingobjectposition = this.a(world, entityhuman, false);

        if (movingobjectposition != null && movingobjectposition.type == EnumMovingObjectType.BLOCK && world.getType(movingobjectposition.b, movingobjectposition.c, movingobjectposition.d) == Blocks.ENDER_PORTAL_FRAME) {
            return itemstack;
        } else {
            if (!world.isStatic) {
                ChunkPosition chunkposition = world.b("Stronghold", (int) entityhuman.locX, (int) entityhuman.locY, (int) entityhuman.locZ);

                if (chunkposition != null) {
                    EntityEnderSignal entityendersignal = new EntityEnderSignal(world, entityhuman.locX, entityhuman.locY + 1.62D - (double) entityhuman.height, entityhuman.locZ);

                    entityendersignal.a((double) chunkposition.x, chunkposition.y, (double) chunkposition.z);
                    world.addEntity(entityendersignal);
                    world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
                    world.a((EntityHuman) null, 1002, (int) entityhuman.locX, (int) entityhuman.locY, (int) entityhuman.locZ, 0);
                    if (!entityhuman.abilities.canInstantlyBuild) {
                        --itemstack.count;
                    }
                }
            }

            return itemstack;
        }
    }
}
