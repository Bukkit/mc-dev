package net.minecraft.server;

public class ItemSaddle extends Item {

    public ItemSaddle(int i) {
        super(i);
        this.aX = 1;
        this.aY = 64;
    }

    public void b(ItemStack itemstack, EntityLiving entityliving) {
        if (entityliving instanceof EntityPig) {
            EntityPig entitypig = (EntityPig) entityliving;

            if (!entitypig.a) {
                entitypig.a = true;
                --itemstack.a;
            }
        }
    }

    public void a(ItemStack itemstack, EntityLiving entityliving) {
        this.b(itemstack, entityliving);
    }
}
