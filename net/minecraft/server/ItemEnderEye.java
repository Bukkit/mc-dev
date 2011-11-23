package net.minecraft.server;

public class ItemEnderEye extends Item {

    public ItemEnderEye(int i) {
        super(i);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);
        int j1 = world.getData(i, j, k);

        if (entityhuman.d(i, j, k) && i1 == Block.ENDER_PORTAL_FRAME.id && !BlockEnderPortalFrame.d(j1)) {
            if (world.isStatic) {
                return true;
            } else {
                world.setData(i, j, k, j1 + 4);
                --itemstack.count;

                int k1;

                for (k1 = 0; k1 < 16; ++k1) {
                    double d0 = (double) ((float) i + (5.0F + c.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double) ((float) j + 0.8125F);
                    double d2 = (double) ((float) k + (5.0F + c.nextFloat() * 6.0F) / 16.0F);
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;

                    world.a("smoke", d0, d1, d2, d3, d4, d5);
                }

                k1 = j1 & 3;
                int l1 = 0;
                int i2 = 0;
                boolean flag = false;
                boolean flag1 = true;
                int j2 = Direction.f[k1];

                int k2;
                int l2;
                int i3;
                int j3;
                int k3;

                for (l2 = -2; l2 <= 2; ++l2) {
                    k2 = i + Direction.a[j2] * l2;
                    j3 = k + Direction.b[j2] * l2;
                    i3 = world.getTypeId(k2, j, j3);
                    if (i3 == Block.ENDER_PORTAL_FRAME.id) {
                        k3 = world.getData(k2, j, j3);
                        if (!BlockEnderPortalFrame.d(k3)) {
                            flag1 = false;
                            break;
                        }

                        if (!flag) {
                            l1 = l2;
                            i2 = l2;
                            flag = true;
                        } else {
                            i2 = l2;
                        }
                    }
                }

                if (flag1 && i2 == l1 + 2) {
                    for (l2 = l1; l2 <= i2; ++l2) {
                        k2 = i + Direction.a[j2] * l2;
                        j3 = k + Direction.b[j2] * l2;
                        k2 += Direction.a[k1] * 4;
                        j3 += Direction.b[k1] * 4;
                        i3 = world.getTypeId(k2, j, j3);
                        k3 = world.getData(k2, j, j3);
                        if (i3 != Block.ENDER_PORTAL_FRAME.id || !BlockEnderPortalFrame.d(k3)) {
                            flag1 = false;
                            break;
                        }
                    }

                    for (l2 = l1 - 1; l2 <= i2 + 1; l2 += 4) {
                        for (k2 = 1; k2 <= 3; ++k2) {
                            j3 = i + Direction.a[j2] * l2;
                            i3 = k + Direction.b[j2] * l2;
                            j3 += Direction.a[k1] * k2;
                            i3 += Direction.b[k1] * k2;
                            k3 = world.getTypeId(j3, j, i3);
                            int l3 = world.getData(j3, j, i3);

                            if (k3 != Block.ENDER_PORTAL_FRAME.id || !BlockEnderPortalFrame.d(l3)) {
                                flag1 = false;
                                break;
                            }
                        }
                    }

                    if (flag1) {
                        for (l2 = l1; l2 <= i2; ++l2) {
                            for (k2 = 1; k2 <= 3; ++k2) {
                                j3 = i + Direction.a[j2] * l2;
                                i3 = k + Direction.b[j2] * l2;
                                j3 += Direction.a[k1] * k2;
                                i3 += Direction.b[k1] * k2;
                                world.setTypeId(j3, j, i3, Block.ENDER_PORTAL.id);
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
                world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
                world.a((EntityHuman) null, 1002, (int) entityhuman.locX, (int) entityhuman.locY, (int) entityhuman.locZ, 0);
                if (!entityhuman.abilities.canInstantlyBuild) {
                    --itemstack.count;
                }
            }
        }

        return itemstack;
    }
}
