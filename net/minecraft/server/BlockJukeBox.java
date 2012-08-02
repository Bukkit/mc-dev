package net.minecraft.server;

public class BlockJukeBox extends BlockContainer {

    protected BlockJukeBox(int i, int j) {
        super(i, j, Material.WOOD);
        this.a(CreativeModeTab.c);
    }

    public int a(int i) {
        return this.textureId + (i == 1 ? 1 : 0);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.getData(i, j, k) == 0) {
            return false;
        } else {
            this.dropRecord(world, i, j, k);
            return true;
        }
    }

    public void e(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            TileEntityRecordPlayer tileentityrecordplayer = (TileEntityRecordPlayer) world.getTileEntity(i, j, k);

            if (tileentityrecordplayer != null) {
                tileentityrecordplayer.record = l;
                tileentityrecordplayer.update();
                world.setData(i, j, k, 1);
            }
        }
    }

    public void dropRecord(World world, int i, int j, int k) {
        if (!world.isStatic) {
            TileEntityRecordPlayer tileentityrecordplayer = (TileEntityRecordPlayer) world.getTileEntity(i, j, k);

            if (tileentityrecordplayer != null) {
                int l = tileentityrecordplayer.record;

                if (l != 0) {
                    world.triggerEffect(1005, i, j, k, 0);
                    world.a((String) null, i, j, k);
                    tileentityrecordplayer.record = 0;
                    tileentityrecordplayer.update();
                    world.setData(i, j, k, 0);
                    float f = 0.7F;
                    double d0 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d1 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.2D + 0.6D;
                    double d2 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, (double) i + d0, (double) j + d1, (double) k + d2, new ItemStack(l, 1, 0));

                    entityitem.pickupDelay = 10;
                    world.addEntity(entityitem);
                }
            }
        }
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        this.dropRecord(world, i, j, k);
        super.remove(world, i, j, k, l, i1);
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        if (!world.isStatic) {
            super.dropNaturally(world, i, j, k, l, f, 0);
        }
    }

    public TileEntity a(World world) {
        return new TileEntityRecordPlayer();
    }
}
