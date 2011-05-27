package net.minecraft.server;

public class ItemSaddle extends Item {

    public ItemSaddle(int i) {
        super(i);
        this.maxStackSize = 1;
        this.durability = 64;
    }

    public void b(ItemStack itemstack, EntityLiving entityliving) {
        if (entityliving instanceof EntityPig) {
            EntityPig entitypig = (EntityPig) entityliving;

            if (!entitypig.r()) {
                entitypig.a(true);
                --itemstack.count;
            }
        }
    }

    public void a(ItemStack itemstack, EntityLiving entityliving) {
        this.b(itemstack, entityliving);
    }
}
