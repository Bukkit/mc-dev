package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class BlockChest extends BlockContainer {

    private Random a = new Random();

    protected BlockChest(int i) {
        super(i, Material.WOOD);
        this.textureId = 26;
        this.a(CreativeModeTab.c);
        this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 22;
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        if (iblockaccess.getTypeId(i, j, k - 1) == this.id) {
            this.a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        } else if (iblockaccess.getTypeId(i, j, k + 1) == this.id) {
            this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        } else if (iblockaccess.getTypeId(i - 1, j, k) == this.id) {
            this.a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        } else if (iblockaccess.getTypeId(i + 1, j, k) == this.id) {
            this.a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        } else {
            this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        this.d_(world, i, j, k);
        int l = world.getTypeId(i, j, k - 1);
        int i1 = world.getTypeId(i, j, k + 1);
        int j1 = world.getTypeId(i - 1, j, k);
        int k1 = world.getTypeId(i + 1, j, k);

        if (l == this.id) {
            this.d_(world, i, j, k - 1);
        }

        if (i1 == this.id) {
            this.d_(world, i, j, k + 1);
        }

        if (j1 == this.id) {
            this.d_(world, i - 1, j, k);
        }

        if (k1 == this.id) {
            this.d_(world, i + 1, j, k);
        }
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = world.getTypeId(i, j, k - 1);
        int i1 = world.getTypeId(i, j, k + 1);
        int j1 = world.getTypeId(i - 1, j, k);
        int k1 = world.getTypeId(i + 1, j, k);
        byte b0 = 0;
        int l1 = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l1 == 0) {
            b0 = 2;
        }

        if (l1 == 1) {
            b0 = 5;
        }

        if (l1 == 2) {
            b0 = 3;
        }

        if (l1 == 3) {
            b0 = 4;
        }

        if (l != this.id && i1 != this.id && j1 != this.id && k1 != this.id) {
            world.setData(i, j, k, b0);
        } else {
            if ((l == this.id || i1 == this.id) && (b0 == 4 || b0 == 5)) {
                if (l == this.id) {
                    world.setData(i, j, k - 1, b0);
                } else {
                    world.setData(i, j, k + 1, b0);
                }

                world.setData(i, j, k, b0);
            }

            if ((j1 == this.id || k1 == this.id) && (b0 == 2 || b0 == 3)) {
                if (j1 == this.id) {
                    world.setData(i - 1, j, k, b0);
                } else {
                    world.setData(i + 1, j, k, b0);
                }

                world.setData(i, j, k, b0);
            }
        }
    }

    public void d_(World world, int i, int j, int k) {
        if (!world.isStatic) {
            int l = world.getTypeId(i, j, k - 1);
            int i1 = world.getTypeId(i, j, k + 1);
            int j1 = world.getTypeId(i - 1, j, k);
            int k1 = world.getTypeId(i + 1, j, k);
            boolean flag = true;
            int l1;
            int i2;
            boolean flag1;
            byte b0;
            int j2;

            if (l != this.id && i1 != this.id) {
                if (j1 != this.id && k1 != this.id) {
                    b0 = 3;
                    if (Block.q[l] && !Block.q[i1]) {
                        b0 = 3;
                    }

                    if (Block.q[i1] && !Block.q[l]) {
                        b0 = 2;
                    }

                    if (Block.q[j1] && !Block.q[k1]) {
                        b0 = 5;
                    }

                    if (Block.q[k1] && !Block.q[j1]) {
                        b0 = 4;
                    }
                } else {
                    l1 = world.getTypeId(j1 == this.id ? i - 1 : i + 1, j, k - 1);
                    i2 = world.getTypeId(j1 == this.id ? i - 1 : i + 1, j, k + 1);
                    b0 = 3;
                    flag1 = true;
                    if (j1 == this.id) {
                        j2 = world.getData(i - 1, j, k);
                    } else {
                        j2 = world.getData(i + 1, j, k);
                    }

                    if (j2 == 2) {
                        b0 = 2;
                    }

                    if ((Block.q[l] || Block.q[l1]) && !Block.q[i1] && !Block.q[i2]) {
                        b0 = 3;
                    }

                    if ((Block.q[i1] || Block.q[i2]) && !Block.q[l] && !Block.q[l1]) {
                        b0 = 2;
                    }
                }
            } else {
                l1 = world.getTypeId(i - 1, j, l == this.id ? k - 1 : k + 1);
                i2 = world.getTypeId(i + 1, j, l == this.id ? k - 1 : k + 1);
                b0 = 5;
                flag1 = true;
                if (l == this.id) {
                    j2 = world.getData(i, j, k - 1);
                } else {
                    j2 = world.getData(i, j, k + 1);
                }

                if (j2 == 4) {
                    b0 = 4;
                }

                if ((Block.q[j1] || Block.q[l1]) && !Block.q[k1] && !Block.q[i2]) {
                    b0 = 5;
                }

                if ((Block.q[k1] || Block.q[i2]) && !Block.q[j1] && !Block.q[l1]) {
                    b0 = 4;
                }
            }

            world.setData(i, j, k, b0);
        }
    }

    public int a(int i) {
        return 4;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        int l = 0;

        if (world.getTypeId(i - 1, j, k) == this.id) {
            ++l;
        }

        if (world.getTypeId(i + 1, j, k) == this.id) {
            ++l;
        }

        if (world.getTypeId(i, j, k - 1) == this.id) {
            ++l;
        }

        if (world.getTypeId(i, j, k + 1) == this.id) {
            ++l;
        }

        return l > 1 ? false : (this.l(world, i - 1, j, k) ? false : (this.l(world, i + 1, j, k) ? false : (this.l(world, i, j, k - 1) ? false : !this.l(world, i, j, k + 1))));
    }

    private boolean l(World world, int i, int j, int k) {
        return world.getTypeId(i, j, k) != this.id ? false : (world.getTypeId(i - 1, j, k) == this.id ? true : (world.getTypeId(i + 1, j, k) == this.id ? true : (world.getTypeId(i, j, k - 1) == this.id ? true : world.getTypeId(i, j, k + 1) == this.id)));
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        super.doPhysics(world, i, j, k, l);
        TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i, j, k);

        if (tileentitychest != null) {
            tileentitychest.h();
        }
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i, j, k);

        if (tileentitychest != null) {
            for (int j1 = 0; j1 < tileentitychest.getSize(); ++j1) {
                ItemStack itemstack = tileentitychest.getItem(j1);

                if (itemstack != null) {
                    float f = this.a.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.a.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityitem;

                    for (float f2 = this.a.nextFloat() * 0.8F + 0.1F; itemstack.count > 0; world.addEntity(entityitem)) {
                        int k1 = this.a.nextInt(21) + 10;

                        if (k1 > itemstack.count) {
                            k1 = itemstack.count;
                        }

                        itemstack.count -= k1;
                        entityitem = new EntityItem(world, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new ItemStack(itemstack.id, k1, itemstack.getData()));
                        float f3 = 0.05F;

                        entityitem.motX = (double) ((float) this.a.nextGaussian() * f3);
                        entityitem.motY = (double) ((float) this.a.nextGaussian() * f3 + 0.2F);
                        entityitem.motZ = (double) ((float) this.a.nextGaussian() * f3);
                        if (itemstack.hasTag()) {
                            entityitem.getItemStack().setTag((NBTTagCompound) itemstack.getTag().clone());
                        }
                    }
                }
            }
        }

        super.remove(world, i, j, k, l, i1);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        Object object = (TileEntityChest) world.getTileEntity(i, j, k);

        if (object == null) {
            return true;
        } else if (world.t(i, j + 1, k)) {
            return true;
        } else if (n(world, i, j, k)) {
            return true;
        } else if (world.getTypeId(i - 1, j, k) == this.id && (world.t(i - 1, j + 1, k) || n(world, i - 1, j, k))) {
            return true;
        } else if (world.getTypeId(i + 1, j, k) == this.id && (world.t(i + 1, j + 1, k) || n(world, i + 1, j, k))) {
            return true;
        } else if (world.getTypeId(i, j, k - 1) == this.id && (world.t(i, j + 1, k - 1) || n(world, i, j, k - 1))) {
            return true;
        } else if (world.getTypeId(i, j, k + 1) == this.id && (world.t(i, j + 1, k + 1) || n(world, i, j, k + 1))) {
            return true;
        } else {
            if (world.getTypeId(i - 1, j, k) == this.id) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(i - 1, j, k), (IInventory) object);
            }

            if (world.getTypeId(i + 1, j, k) == this.id) {
                object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.getTileEntity(i + 1, j, k));
            }

            if (world.getTypeId(i, j, k - 1) == this.id) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(i, j, k - 1), (IInventory) object);
            }

            if (world.getTypeId(i, j, k + 1) == this.id) {
                object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.getTileEntity(i, j, k + 1));
            }

            if (world.isStatic) {
                return true;
            } else {
                entityhuman.openContainer((IInventory) object);
                return true;
            }
        }
    }

    public TileEntity a(World world) {
        return new TileEntityChest();
    }

    private static boolean n(World world, int i, int j, int k) {
        Iterator iterator = world.a(EntityOcelot.class, AxisAlignedBB.a().a((double) i, (double) (j + 1), (double) k, (double) (i + 1), (double) (j + 2), (double) (k + 1))).iterator();

        EntityOcelot entityocelot;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();

            entityocelot = (EntityOcelot) entityocelot1;
        } while (!entityocelot.isSitting());

        return true;
    }
}
