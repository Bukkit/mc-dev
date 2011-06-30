package net.minecraft.server;

public class ItemSword extends Item {

    private int a;

    public ItemSword(int i, EnumToolMaterial enumtoolmaterial) {
        super(i);
        this.maxStackSize = 1;
        this.d(enumtoolmaterial.a());
        this.a = 4 + enumtoolmaterial.c() * 2;
    }

    public float a(ItemStack itemstack, Block block) {
        return block.id == Block.WEB.id ? 15.0F : 1.5F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(1, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        itemstack.damage(2, entityliving);
        return true;
    }

    public int a(Entity entity) {
        return this.a;
    }

    public boolean a(Block block) {
        return block.id == Block.WEB.id;
    }
}
