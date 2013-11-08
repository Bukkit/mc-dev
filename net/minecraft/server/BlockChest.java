package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class BlockChest extends BlockContainer {

    private final Random b = new Random();
    public final int a;

    protected BlockChest(int i) {
        super(Material.WOOD);
        this.a = i;
        this.a(CreativeModeTab.c);
        this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public int b() {
        return 22;
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        if (iblockaccess.getType(i, j, k - 1) == this) {
            this.a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        } else if (iblockaccess.getType(i, j, k + 1) == this) {
            this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        } else if (iblockaccess.getType(i - 1, j, k) == this) {
            this.a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        } else if (iblockaccess.getType(i + 1, j, k) == this) {
            this.a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        } else {
            this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        this.e(world, i, j, k);
        Block block = world.getType(i, j, k - 1);
        Block block1 = world.getType(i, j, k + 1);
        Block block2 = world.getType(i - 1, j, k);
        Block block3 = world.getType(i + 1, j, k);

        if (block == this) {
            this.e(world, i, j, k - 1);
        }

        if (block1 == this) {
            this.e(world, i, j, k + 1);
        }

        if (block2 == this) {
            this.e(world, i - 1, j, k);
        }

        if (block3 == this) {
            this.e(world, i + 1, j, k);
        }
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        Block block = world.getType(i, j, k - 1);
        Block block1 = world.getType(i, j, k + 1);
        Block block2 = world.getType(i - 1, j, k);
        Block block3 = world.getType(i + 1, j, k);
        byte b0 = 0;
        int l = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            b0 = 2;
        }

        if (l == 1) {
            b0 = 5;
        }

        if (l == 2) {
            b0 = 3;
        }

        if (l == 3) {
            b0 = 4;
        }

        if (block != this && block1 != this && block2 != this && block3 != this) {
            world.setData(i, j, k, b0, 3);
        } else {
            if ((block == this || block1 == this) && (b0 == 4 || b0 == 5)) {
                if (block == this) {
                    world.setData(i, j, k - 1, b0, 3);
                } else {
                    world.setData(i, j, k + 1, b0, 3);
                }

                world.setData(i, j, k, b0, 3);
            }

            if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3)) {
                if (block2 == this) {
                    world.setData(i - 1, j, k, b0, 3);
                } else {
                    world.setData(i + 1, j, k, b0, 3);
                }

                world.setData(i, j, k, b0, 3);
            }
        }

        if (itemstack.hasName()) {
            ((TileEntityChest) world.getTileEntity(i, j, k)).a(itemstack.getName());
        }
    }

    public void e(World world, int i, int j, int k) {
        if (!world.isStatic) {
            Block block = world.getType(i, j, k - 1);
            Block block1 = world.getType(i, j, k + 1);
            Block block2 = world.getType(i - 1, j, k);
            Block block3 = world.getType(i + 1, j, k);
            boolean flag = true;
            int l;
            Block block4;
            int i1;
            Block block5;
            boolean flag1;
            byte b0;
            int j1;

            if (block != this && block1 != this) {
                if (block2 != this && block3 != this) {
                    b0 = 3;
                    if (block.j() && !block1.j()) {
                        b0 = 3;
                    }

                    if (block1.j() && !block.j()) {
                        b0 = 2;
                    }

                    if (block2.j() && !block3.j()) {
                        b0 = 5;
                    }

                    if (block3.j() && !block2.j()) {
                        b0 = 4;
                    }
                } else {
                    l = block2 == this ? i - 1 : i + 1;
                    block4 = world.getType(l, j, k - 1);
                    i1 = block2 == this ? i - 1 : i + 1;
                    block5 = world.getType(i1, j, k + 1);
                    b0 = 3;
                    flag1 = true;
                    if (block2 == this) {
                        j1 = world.getData(i - 1, j, k);
                    } else {
                        j1 = world.getData(i + 1, j, k);
                    }

                    if (j1 == 2) {
                        b0 = 2;
                    }

                    if ((block.j() || block4.j()) && !block1.j() && !block5.j()) {
                        b0 = 3;
                    }

                    if ((block1.j() || block5.j()) && !block.j() && !block4.j()) {
                        b0 = 2;
                    }
                }
            } else {
                l = block == this ? k - 1 : k + 1;
                block4 = world.getType(i - 1, j, l);
                i1 = block == this ? k - 1 : k + 1;
                block5 = world.getType(i + 1, j, i1);
                b0 = 5;
                flag1 = true;
                if (block == this) {
                    j1 = world.getData(i, j, k - 1);
                } else {
                    j1 = world.getData(i, j, k + 1);
                }

                if (j1 == 4) {
                    b0 = 4;
                }

                if ((block2.j() || block4.j()) && !block3.j() && !block5.j()) {
                    b0 = 5;
                }

                if ((block3.j() || block5.j()) && !block2.j() && !block4.j()) {
                    b0 = 4;
                }
            }

            world.setData(i, j, k, b0, 3);
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        int l = 0;

        if (world.getType(i - 1, j, k) == this) {
            ++l;
        }

        if (world.getType(i + 1, j, k) == this) {
            ++l;
        }

        if (world.getType(i, j, k - 1) == this) {
            ++l;
        }

        if (world.getType(i, j, k + 1) == this) {
            ++l;
        }

        return l > 1 ? false : (this.n(world, i - 1, j, k) ? false : (this.n(world, i + 1, j, k) ? false : (this.n(world, i, j, k - 1) ? false : !this.n(world, i, j, k + 1))));
    }

    private boolean n(World world, int i, int j, int k) {
        return world.getType(i, j, k) != this ? false : (world.getType(i - 1, j, k) == this ? true : (world.getType(i + 1, j, k) == this ? true : (world.getType(i, j, k - 1) == this ? true : world.getType(i, j, k + 1) == this)));
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        super.doPhysics(world, i, j, k, block);
        TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i, j, k);

        if (tileentitychest != null) {
            tileentitychest.u();
        }
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i, j, k);

        if (tileentitychest != null) {
            for (int i1 = 0; i1 < tileentitychest.getSize(); ++i1) {
                ItemStack itemstack = tileentitychest.getItem(i1);

                if (itemstack != null) {
                    float f = this.b.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.b.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityitem;

                    for (float f2 = this.b.nextFloat() * 0.8F + 0.1F; itemstack.count > 0; world.addEntity(entityitem)) {
                        int j1 = this.b.nextInt(21) + 10;

                        if (j1 > itemstack.count) {
                            j1 = itemstack.count;
                        }

                        itemstack.count -= j1;
                        entityitem = new EntityItem(world, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getData()));
                        float f3 = 0.05F;

                        entityitem.motX = (double) ((float) this.b.nextGaussian() * f3);
                        entityitem.motY = (double) ((float) this.b.nextGaussian() * f3 + 0.2F);
                        entityitem.motZ = (double) ((float) this.b.nextGaussian() * f3);
                        if (itemstack.hasTag()) {
                            entityitem.getItemStack().setTag((NBTTagCompound) itemstack.getTag().clone());
                        }
                    }
                }
            }

            world.f(i, j, k, block);
        }

        super.remove(world, i, j, k, block, l);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            IInventory iinventory = this.m(world, i, j, k);

            if (iinventory != null) {
                entityhuman.openContainer(iinventory);
            }

            return true;
        }
    }

    public IInventory m(World world, int i, int j, int k) {
        Object object = (TileEntityChest) world.getTileEntity(i, j, k);

        if (object == null) {
            return null;
        } else if (world.getType(i, j + 1, k).r()) {
            return null;
        } else if (o(world, i, j, k)) {
            return null;
        } else if (world.getType(i - 1, j, k) == this && (world.getType(i - 1, j + 1, k).r() || o(world, i - 1, j, k))) {
            return null;
        } else if (world.getType(i + 1, j, k) == this && (world.getType(i + 1, j + 1, k).r() || o(world, i + 1, j, k))) {
            return null;
        } else if (world.getType(i, j, k - 1) == this && (world.getType(i, j + 1, k - 1).r() || o(world, i, j, k - 1))) {
            return null;
        } else if (world.getType(i, j, k + 1) == this && (world.getType(i, j + 1, k + 1).r() || o(world, i, j, k + 1))) {
            return null;
        } else {
            if (world.getType(i - 1, j, k) == this) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(i - 1, j, k), (IInventory) object);
            }

            if (world.getType(i + 1, j, k) == this) {
                object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.getTileEntity(i + 1, j, k));
            }

            if (world.getType(i, j, k - 1) == this) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(i, j, k - 1), (IInventory) object);
            }

            if (world.getType(i, j, k + 1) == this) {
                object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.getTileEntity(i, j, k + 1));
            }

            return (IInventory) object;
        }
    }

    public TileEntity a(World world, int i) {
        TileEntityChest tileentitychest = new TileEntityChest();

        return tileentitychest;
    }

    public boolean isPowerSource() {
        return this.a == 1;
    }

    public int b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        if (!this.isPowerSource()) {
            return 0;
        } else {
            int i1 = ((TileEntityChest) iblockaccess.getTileEntity(i, j, k)).o;

            return MathHelper.a(i1, 0, 15);
        }
    }

    public int c(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return l == 1 ? this.b(iblockaccess, i, j, k, l) : 0;
    }

    private static boolean o(World world, int i, int j, int k) {
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

    public boolean M() {
        return true;
    }

    public int g(World world, int i, int j, int k, int l) {
        return Container.b(this.m(world, i, j, k));
    }
}
