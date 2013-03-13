package net.minecraft.server;

public class ItemEnderEye extends Item {

    public ItemEnderEye(int i) {
        super(i);
        this.a(CreativeModeTab.f);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = world.getTypeId(i, j, k);
        int j1 = world.getData(i, j, k);

        if (entityhuman.a(i, j, k, l, itemstack) && i1 == Block.ENDER_PORTAL_FRAME.id && !BlockEnderPortalFrame.d(j1)) {
            if (world.isStatic) {
                return true;
            } else {
                world.setData(i, j, k, j1 + 4, 2);
                --itemstack.count;

                int k1;

                for (k1 = 0; k1 < 16; ++k1) {
                    double d0 = (double) ((float) i + (5.0F + e.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double) ((float) j + 0.8125F);
                    double d2 = (double) ((float) k + (5.0F + e.nextFloat() * 6.0F) / 16.0F);
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;

                    world.addParticle("smoke", d0, d1, d2, d3, d4, d5);
                }

                k1 = j1 & 3;
                int l1 = 0;
                int i2 = 0;
                boolean flag = false;
                boolean flag1 = true;
                int j2 = Direction.g[k1];

                int k2;
                int l2;
                int i3;
                int j3;
                int k3;

                for (k2 = -2; k2 <= 2; ++k2) {
                    j3 = i + Direction.a[j2] * k2;
                    k3 = k + Direction.b[j2] * k2;
                    l2 = world.getTypeId(j3, j, k3);
                    if (l2 == Block.ENDER_PORTAL_FRAME.id) {
                        i3 = world.getData(j3, j, k3);
                        if (!BlockEnderPortalFrame.d(i3)) {
                            flag1 = false;
                            break;
                        }

                        i2 = k2;
                        if (!flag) {
                            l1 = k2;
                            flag = true;
                        }
                    }
                }

                if (flag1 && i2 == l1 + 2) {
                    for (k2 = l1; k2 <= i2; ++k2) {
                        j3 = i + Direction.a[j2] * k2;
                        k3 = k + Direction.b[j2] * k2;
                        j3 += Direction.a[k1] * 4;
                        k3 += Direction.b[k1] * 4;
                        l2 = world.getTypeId(j3, j, k3);
                        i3 = world.getData(j3, j, k3);
                        if (l2 != Block.ENDER_PORTAL_FRAME.id || !BlockEnderPortalFrame.d(i3)) {
                            flag1 = false;
                            break;
                        }
                    }

                    for (k2 = l1 - 1; k2 <= i2 + 1; k2 += 4) {
                        for (j3 = 1; j3 <= 3; ++j3) {
                            k3 = i + Direction.a[j2] * k2;
                            l2 = k + Direction.b[j2] * k2;
                            k3 += Direction.a[k1] * j3;
                            l2 += Direction.b[k1] * j3;
                            i3 = world.getTypeId(k3, j, l2);
                            int l3 = world.getData(k3, j, l2);

                            if (i3 != Block.ENDER_PORTAL_FRAME.id || !BlockEnderPortalFrame.d(l3)) {
                                flag1 = false;
                                break;
                            }
                        }
                    }

                    if (flag1) {
                        for (k2 = l1; k2 <= i2; ++k2) {
                            for (j3 = 1; j3 <= 3; ++j3) {
                                k3 = i + Direction.a[j2] * k2;
                                l2 = k + Direction.b[j2] * k2;
                                k3 += Direction.a[k1] * j3;
                                l2 += Direction.b[k1] * j3;
                                world.setTypeIdAndData(k3, j, l2, Block.ENDER_PORTAL.id, 0, 2);
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

        if (movingobjectposition != null && movingobjectposition.type == EnumMovingObjectType.TILE) {
            int i = world.getTypeId(movingobjectposition.b, movingobjectposition.c, movingobjectposition.d);

            if (i == Block.ENDER_PORTAL_FRAME.id) {
                return itemstack;
            }
        }

        if (!world.isStatic) {
            ChunkPosition chunkposition = world.b("Stronghold", (int) entityhuman.locX, (int) entityhuman.locY, (int) entityhuman.locZ);

            if (chunkposition != null) {
                EntityEnderSignal entityendersignal = new EntityEnderSignal(world, entityhuman.locX, entityhuman.locY + 1.62D - (double) entityhuman.height, entityhuman.locZ);

                entityendersignal.a((double) chunkposition.x, chunkposition.y, (double) chunkposition.z);
                world.addEntity(entityendersignal);
                world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (e.nextFloat() * 0.4F + 0.8F));
                world.a((EntityHuman) null, 1002, (int) entityhuman.locX, (int) entityhuman.locY, (int) entityhuman.locZ, 0);
                if (!entityhuman.abilities.canInstantlyBuild) {
                    --itemstack.count;
                }
            }
        }

        return itemstack;
    }
}
