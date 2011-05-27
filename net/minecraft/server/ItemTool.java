package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bj;
    private float bk = 4.0F;
    private int bl;
    protected EnumToolMaterial a;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.a = enumtoolmaterial;
        this.bj = ablock;
        this.maxStackSize = 1;
        this.d(enumtoolmaterial.a());
        this.bk = enumtoolmaterial.b();
        this.bl = j + enumtoolmaterial.c();
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bj.length; ++i) {
            if (this.bj[i] == block) {
                return this.bk;
            }
        }

        return 1.0F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(2, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        itemstack.damage(1, entityliving);
        return true;
    }

    public int a(Entity entity) {
        return this.bl;
    }
}
