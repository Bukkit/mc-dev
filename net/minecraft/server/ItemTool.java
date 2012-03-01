package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bU;
    protected float a = 4.0F;
    private int bV;
    protected EnumToolMaterial b;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.b = enumtoolmaterial;
        this.bU = ablock;
        this.maxStackSize = 1;
        this.setMaxDurability(enumtoolmaterial.a());
        this.a = enumtoolmaterial.b();
        this.bV = j + enumtoolmaterial.c();
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bU.length; ++i) {
            if (this.bU[i] == block) {
                return this.a;
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
        return this.bV;
    }

    public int c() {
        return this.b.e();
    }
}
