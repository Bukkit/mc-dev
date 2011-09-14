package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bt;
    private float bu = 4.0F;
    private int bv;
    protected EnumToolMaterial a;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.a = enumtoolmaterial;
        this.bt = ablock;
        this.maxStackSize = 1;
        this.d(enumtoolmaterial.a());
        this.bu = enumtoolmaterial.b();
        this.bv = j + enumtoolmaterial.c();
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bt.length; ++i) {
            if (this.bt[i] == block) {
                return this.bu;
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
        return this.bv;
    }
}
