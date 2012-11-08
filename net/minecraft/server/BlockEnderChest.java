package net.minecraft.server;

import java.util.Random;

public class BlockEnderChest extends BlockContainer {

    protected BlockEnderChest(int i) {
        super(i, Material.STONE);
        this.textureId = 37;
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

    public int getDropType(int i, Random random, int j) {
        return Block.OBSIDIAN.id;
    }

    public int a(Random random) {
        return 8;
    }

    protected boolean s_() {
        return true;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
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

        world.setData(i, j, k, b0);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        InventoryEnderChest inventoryenderchest = entityhuman.getEnderChest();
        TileEntityEnderChest tileentityenderchest = (TileEntityEnderChest) world.getTileEntity(i, j, k);

        if (inventoryenderchest != null && tileentityenderchest != null) {
            if (world.t(i, j + 1, k)) {
                return true;
            } else if (world.isStatic) {
                return true;
            } else {
                inventoryenderchest.a(tileentityenderchest);
                entityhuman.openContainer(inventoryenderchest);
                return true;
            }
        } else {
            return true;
        }
    }

    public TileEntity a(World world) {
        return new TileEntityEnderChest();
    }
}
