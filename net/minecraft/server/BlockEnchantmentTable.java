package net.minecraft.server;

public class BlockEnchantmentTable extends BlockContainer {

    protected BlockEnchantmentTable(int i) {
        super(i, 166, Material.STONE);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.h(0);
        this.a(CreativeModeTab.c);
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public int a(int i, int j) {
        return this.a(i);
    }

    public int a(int i) {
        return i == 0 ? this.textureId + 17 : (i == 1 ? this.textureId : this.textureId + 16);
    }

    public TileEntity a(World world) {
        return new TileEntityEnchantTable();
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            entityhuman.startEnchanting(i, j, k);
            return true;
        }
    }
}
