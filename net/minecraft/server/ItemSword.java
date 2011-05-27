package net.minecraft.server;

public class ItemSword extends Item {

    private int a;

    public ItemSword(int i, EnumToolMaterial enumtoolmaterial) {
        super(i);
        this.bb = 1;
        this.bc = enumtoolmaterial.a();
        this.a = 4 + enumtoolmaterial.c() * 2;
    }

    public float a(ItemStack itemstack, Block block) {
        return 1.5F;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving) {
        itemstack.b(1);
    }

    public void a(ItemStack itemstack, int i, int j, int k, int l) {
        itemstack.b(2);
    }

    public int a(Entity entity) {
        return this.a;
    }
}
