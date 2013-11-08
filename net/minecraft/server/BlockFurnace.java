package net.minecraft.server;

import java.util.Random;

public class BlockFurnace extends BlockContainer {

    private final Random a = new Random();
    private final boolean b;
    private static boolean M;

    protected BlockFurnace(boolean flag) {
        super(Material.STONE);
        this.b = flag;
    }

    public Item getDropType(int i, Random random, int j) {
        return Item.getItemOf(Blocks.FURNACE);
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        this.e(world, i, j, k);
    }

    private void e(World world, int i, int j, int k) {
        if (!world.isStatic) {
            Block block = world.getType(i, j, k - 1);
            Block block1 = world.getType(i, j, k + 1);
            Block block2 = world.getType(i - 1, j, k);
            Block block3 = world.getType(i + 1, j, k);
            byte b0 = 3;

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

            world.setData(i, j, k, b0, 2);
        }
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace) world.getTileEntity(i, j, k);

            if (tileentityfurnace != null) {
                entityhuman.openFurnace(tileentityfurnace);
            }

            return true;
        }
    }

    public static void a(boolean flag, World world, int i, int j, int k) {
        int l = world.getData(i, j, k);
        TileEntity tileentity = world.getTileEntity(i, j, k);

        M = true;
        if (flag) {
            world.setTypeUpdate(i, j, k, Blocks.BURNING_FURNACE);
        } else {
            world.setTypeUpdate(i, j, k, Blocks.FURNACE);
        }

        M = false;
        world.setData(i, j, k, l, 2);
        if (tileentity != null) {
            tileentity.t();
            world.setTileEntity(i, j, k, tileentity);
        }
    }

    public TileEntity a(World world, int i) {
        return new TileEntityFurnace();
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        int l = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setData(i, j, k, 2, 2);
        }

        if (l == 1) {
            world.setData(i, j, k, 5, 2);
        }

        if (l == 2) {
            world.setData(i, j, k, 3, 2);
        }

        if (l == 3) {
            world.setData(i, j, k, 4, 2);
        }

        if (itemstack.hasName()) {
            ((TileEntityFurnace) world.getTileEntity(i, j, k)).a(itemstack.getName());
        }
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        if (!M) {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace) world.getTileEntity(i, j, k);

            if (tileentityfurnace != null) {
                for (int i1 = 0; i1 < tileentityfurnace.getSize(); ++i1) {
                    ItemStack itemstack = tileentityfurnace.getItem(i1);

                    if (itemstack != null) {
                        float f = this.a.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.a.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.a.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.count > 0) {
                            int j1 = this.a.nextInt(21) + 10;

                            if (j1 > itemstack.count) {
                                j1 = itemstack.count;
                            }

                            itemstack.count -= j1;
                            EntityItem entityitem = new EntityItem(world, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getData()));

                            if (itemstack.hasTag()) {
                                entityitem.getItemStack().setTag((NBTTagCompound) itemstack.getTag().clone());
                            }

                            float f3 = 0.05F;

                            entityitem.motX = (double) ((float) this.a.nextGaussian() * f3);
                            entityitem.motY = (double) ((float) this.a.nextGaussian() * f3 + 0.2F);
                            entityitem.motZ = (double) ((float) this.a.nextGaussian() * f3);
                            world.addEntity(entityitem);
                        }
                    }
                }

                world.f(i, j, k, block);
            }
        }

        super.remove(world, i, j, k, block, l);
    }

    public boolean M() {
        return true;
    }

    public int g(World world, int i, int j, int k, int l) {
        return Container.b((IInventory) world.getTileEntity(i, j, k));
    }
}
