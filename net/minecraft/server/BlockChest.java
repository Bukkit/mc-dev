package net.minecraft.server;

import java.util.Random;

public class BlockChest extends BlockContainer {

    private Random a = new Random();

    protected BlockChest(int i) {
        super(i, Material.WOOD);
        this.textureId = 26;
    }

    public int a(int i) {
        return i == 1 ? this.textureId - 1 : (i == 0 ? this.textureId - 1 : (i == 3 ? this.textureId + 1 : this.textureId));
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

        return l > 1 ? false : (this.g(world, i - 1, j, k) ? false : (this.g(world, i + 1, j, k) ? false : (this.g(world, i, j, k - 1) ? false : !this.g(world, i, j, k + 1))));
    }

    private boolean g(World world, int i, int j, int k) {
        return world.getTypeId(i, j, k) != this.id ? false : (world.getTypeId(i - 1, j, k) == this.id ? true : (world.getTypeId(i + 1, j, k) == this.id ? true : (world.getTypeId(i, j, k - 1) == this.id ? true : world.getTypeId(i, j, k + 1) == this.id)));
    }

    public void remove(World world, int i, int j, int k) {
        TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i, j, k);

        for (int l = 0; l < tileentitychest.getSize(); ++l) {
            ItemStack itemstack = tileentitychest.getItem(l);

            if (itemstack != null) {
                float f = this.a.nextFloat() * 0.8F + 0.1F;
                float f1 = this.a.nextFloat() * 0.8F + 0.1F;
                float f2 = this.a.nextFloat() * 0.8F + 0.1F;

                while (itemstack.count > 0) {
                    int i1 = this.a.nextInt(21) + 10;

                    if (i1 > itemstack.count) {
                        i1 = itemstack.count;
                    }

                    itemstack.count -= i1;
                    EntityItem entityitem = new EntityItem(world, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new ItemStack(itemstack.id, i1, itemstack.getData()));
                    float f3 = 0.05F;

                    entityitem.motX = (double) ((float) this.a.nextGaussian() * f3);
                    entityitem.motY = (double) ((float) this.a.nextGaussian() * f3 + 0.2F);
                    entityitem.motZ = (double) ((float) this.a.nextGaussian() * f3);
                    world.addEntity(entityitem);
                }
            }
        }

        super.remove(world, i, j, k);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        Object object = (TileEntityChest) world.getTileEntity(i, j, k);

        if (world.e(i, j + 1, k)) {
            return true;
        } else if (world.getTypeId(i - 1, j, k) == this.id && world.e(i - 1, j + 1, k)) {
            return true;
        } else if (world.getTypeId(i + 1, j, k) == this.id && world.e(i + 1, j + 1, k)) {
            return true;
        } else if (world.getTypeId(i, j, k - 1) == this.id && world.e(i, j + 1, k - 1)) {
            return true;
        } else if (world.getTypeId(i, j, k + 1) == this.id && world.e(i, j + 1, k + 1)) {
            return true;
        } else {
            if (world.getTypeId(i - 1, j, k) == this.id) {
                object = new InventoryLargeChest("Large chest", (TileEntityChest) world.getTileEntity(i - 1, j, k), (IInventory) object);
            }

            if (world.getTypeId(i + 1, j, k) == this.id) {
                object = new InventoryLargeChest("Large chest", (IInventory) object, (TileEntityChest) world.getTileEntity(i + 1, j, k));
            }

            if (world.getTypeId(i, j, k - 1) == this.id) {
                object = new InventoryLargeChest("Large chest", (TileEntityChest) world.getTileEntity(i, j, k - 1), (IInventory) object);
            }

            if (world.getTypeId(i, j, k + 1) == this.id) {
                object = new InventoryLargeChest("Large chest", (IInventory) object, (TileEntityChest) world.getTileEntity(i, j, k + 1));
            }

            if (world.isStatic) {
                return true;
            } else {
                entityhuman.a((IInventory) object);
                return true;
            }
        }
    }

    protected TileEntity a_() {
        return new TileEntityChest();
    }
}
