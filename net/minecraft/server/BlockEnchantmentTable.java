package net.minecraft.server;

public class BlockEnchantmentTable extends BlockContainer {

    protected BlockEnchantmentTable() {
        super(Material.STONE);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.g(0);
        this.a(CreativeModeTab.c);
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public TileEntity a(World world, int i) {
        return new TileEntityEnchantTable();
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            TileEntityEnchantTable tileentityenchanttable = (TileEntityEnchantTable) world.getTileEntity(i, j, k);

            entityhuman.startEnchanting(i, j, k, tileentityenchanttable.b() ? tileentityenchanttable.a() : null);
            return true;
        }
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        super.postPlace(world, i, j, k, entityliving, itemstack);
        if (itemstack.hasName()) {
            ((TileEntityEnchantTable) world.getTileEntity(i, j, k)).a(itemstack.getName());
        }
    }
}
