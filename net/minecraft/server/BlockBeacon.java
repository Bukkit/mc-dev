package net.minecraft.server;

public class BlockBeacon extends BlockContainer {

    public BlockBeacon(int i) {
        super(i, 41, Material.SHATTERABLE);
        this.c(3.0F);
        this.a(CreativeModeTab.f);
    }

    public TileEntity a(World world) {
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

    public boolean b() {
        return false;
    }

    public int d() {
        return 34;
    }
}
