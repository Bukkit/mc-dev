package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bi;
    private float bj = 4.0F;
    private int bk;
    protected EnumToolMaterial a;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.a = enumtoolmaterial;
        this.bi = ablock;
        this.maxStackSize = 1;
        this.d(enumtoolmaterial.a());
        this.bj = enumtoolmaterial.b();
        this.bk = j + enumtoolmaterial.c();
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bi.length; ++i) {
            if (this.bi[i] == block) {
                return this.bj;
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
        return this.bk;
    }
}
