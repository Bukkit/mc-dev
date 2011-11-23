package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bR;
    protected float a = 4.0F;
    private int bS;
    protected EnumToolMaterial b;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.b = enumtoolmaterial;
        this.bR = ablock;
        this.maxStackSize = 1;
        this.f(enumtoolmaterial.a());
        this.a = enumtoolmaterial.b();
        this.bS = j + enumtoolmaterial.c();
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bR.length; ++i) {
            if (this.bR[i] == block) {
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
        return this.bS;
    }

    public int c() {
        return this.b.e();
    }
}
