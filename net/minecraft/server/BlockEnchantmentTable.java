package net.minecraft.server;

public class BlockEnchantmentTable extends BlockContainer {

    protected BlockEnchantmentTable(int i) {
        super(i, 166, Material.STONE);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.g(0);
    }

    public boolean b() {
        return false;
    }

    public boolean a() {
        return false;
    }

    public int a(int i, int j) {
        return this.a(i);
    }

    public int a(int i) {
        return i == 0 ? this.textureId + 17 : (i == 1 ? this.textureId : this.textureId + 16);
    }

    public TileEntity a_() {
        return new TileEntityEnchantTable();
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (world.isStatic) {
            return true;
        } else {
            entityhuman.c(i, j, k);
            return true;
        }
    }
}
