package net.minecraft.server;

import java.util.Random;

public class BlockFurnace extends BlockContainer {

    private Random a = new Random();
    private final boolean b;
    private static boolean c = false;

    protected BlockFurnace(int i, boolean flag) {
        super(i, Material.STONE);
        this.b = flag;
        this.textureId = 45;
    }

    public int getDropType(int i, Random random, int j) {
        return Block.FURNACE.id;
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        this.l(world, i, j, k);
    }

    private void l(World world, int i, int j, int k) {
        if (!world.isStatic) {
            int l = world.getTypeId(i, j, k - 1);
            int i1 = world.getTypeId(i, j, k + 1);
            int j1 = world.getTypeId(i - 1, j, k);
            int k1 = world.getTypeId(i + 1, j, k);
            byte b0 = 3;

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

            world.setData(i, j, k, b0);
        }
    }

    public int a(int i) {
        return i == 1 ? this.textureId + 17 : (i == 0 ? this.textureId + 17 : (i == 3 ? this.textureId - 1 : this.textureId));
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

        c = true;
        if (flag) {
            world.setTypeId(i, j, k, Block.BURNING_FURNACE.id);
        } else {
            world.setTypeId(i, j, k, Block.FURNACE.id);
        }

        c = false;
        world.setData(i, j, k, l);
        if (tileentity != null) {
            tileentity.s();
            world.setTileEntity(i, j, k, tileentity);
        }
    }

    public TileEntity a(World world) {
        return new TileEntityFurnace();
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setData(i, j, k, 2);
        }

        if (l == 1) {
            world.setData(i, j, k, 5);
        }

        if (l == 2) {
            world.setData(i, j, k, 3);
        }

        if (l == 3) {
            world.setData(i, j, k, 4);
        }
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        if (!c) {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace) world.getTileEntity(i, j, k);

            if (tileentityfurnace != null) {
                for (int j1 = 0; j1 < tileentityfurnace.getSize(); ++j1) {
                    ItemStack itemstack = tileentityfurnace.getItem(j1);

                    if (itemstack != null) {
                        float f = this.a.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.a.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.a.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.count > 0) {
                            int k1 = this.a.nextInt(21) + 10;

                            if (k1 > itemstack.count) {
                                k1 = itemstack.count;
                            }

                            itemstack.count -= k1;
                            EntityItem entityitem = new EntityItem(world, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new ItemStack(itemstack.id, k1, itemstack.getData()));

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
            }
        }

        super.remove(world, i, j, k, l, i1);
    }
}
