package net.minecraft.server;

public class ItemCarrotStick extends Item {

    public ItemCarrotStick() {
        this.a(CreativeModeTab.e);
        this.e(1);
        this.setMaxDurability(25);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.am() && entityhuman.vehicle instanceof EntityPig) {
            EntityPig entitypig = (EntityPig) entityhuman.vehicle;

            if (entitypig.bY().h() && itemstack.l() - itemstack.getData() >= 7) {
                entitypig.bY().g();
                itemstack.damage(7, entityhuman);
                if (itemstack.count == 0) {
                    ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);

                    itemstack1.setTag(itemstack.tag);
                    return itemstack1;
                }
            }
        }

        return itemstack;
    }
}
