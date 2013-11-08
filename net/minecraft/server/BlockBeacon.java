package net.minecraft.server;

public class BlockBeacon extends BlockContainer {

    public BlockBeacon() {
        super(Material.SHATTERABLE);
        this.c(3.0F);
        this.a(CreativeModeTab.f);
    }

    public TileEntity a(World world, int i) {
        return new TileEntityBeacon();
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            TileEntityBeacon tileentitybeacon = (TileEntityBeacon) world.getTileEntity(i, j, k);

            if (tileentitybeacon != null) {
                entityhuman.openBeacon(tileentitybeacon);
            }

            return true;
        }
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public int b() {
        return 34;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        super.postPlace(world, i, j, k, entityliving, itemstack);
        if (itemstack.hasName()) {
            ((TileEntityBeacon) world.getTileEntity(i, j, k)).a(itemstack.getName());
        }
    }
}
