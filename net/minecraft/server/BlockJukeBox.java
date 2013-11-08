package net.minecraft.server;

public class BlockJukeBox extends BlockContainer {

    protected BlockJukeBox() {
        super(Material.WOOD);
        this.a(CreativeModeTab.c);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.getData(i, j, k) == 0) {
            return false;
        } else {
            this.dropRecord(world, i, j, k);
            return true;
        }
    }

    public void b(World world, int i, int j, int k, ItemStack itemstack) {
        if (!world.isStatic) {
            TileEntityRecordPlayer tileentityrecordplayer = (TileEntityRecordPlayer) world.getTileEntity(i, j, k);

            if (tileentityrecordplayer != null) {
                tileentityrecordplayer.setRecord(itemstack.cloneItemStack());
                world.setData(i, j, k, 1, 2);
            }
        }
    }

    public void dropRecord(World world, int i, int j, int k) {
        if (!world.isStatic) {
            TileEntityRecordPlayer tileentityrecordplayer = (TileEntityRecordPlayer) world.getTileEntity(i, j, k);

            if (tileentityrecordplayer != null) {
                ItemStack itemstack = tileentityrecordplayer.getRecord();

                if (itemstack != null) {
                    world.triggerEffect(1005, i, j, k, 0);
                    world.a((String) null, i, j, k);
                    tileentityrecordplayer.setRecord((ItemStack) null);
                    world.setData(i, j, k, 0, 2);
                    float f = 0.7F;
                    double d0 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d1 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.2D + 0.6D;
                    double d2 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    ItemStack itemstack1 = itemstack.cloneItemStack();
                    EntityItem entityitem = new EntityItem(world, (double) i + d0, (double) j + d1, (double) k + d2, itemstack1);

                    entityitem.pickupDelay = 10;
                    world.addEntity(entityitem);
                }
            }
        }
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        this.dropRecord(world, i, j, k);
        super.remove(world, i, j, k, block, l);
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        if (!world.isStatic) {
            super.dropNaturally(world, i, j, k, l, f, 0);
        }
    }

    public TileEntity a(World world, int i) {
        return new TileEntityRecordPlayer();
    }

    public boolean M() {
        return true;
    }

    public int g(World world, int i, int j, int k, int l) {
        ItemStack itemstack = ((TileEntityRecordPlayer) world.getTileEntity(i, j, k)).getRecord();

        return itemstack == null ? 0 : Item.b(itemstack.getItem()) + 1 - Item.b(Items.RECORD_1);
    }
}
