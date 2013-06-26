package net.minecraft.server;

public class ItemCarrotStick extends Item {

    public ItemCarrotStick(int i) {
        super(i);
        this.a(CreativeModeTab.e);
        this.d(1);
        this.setMaxDurability(25);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.ae() && entityhuman.vehicle instanceof EntityPig) {
            EntityPig entitypig = (EntityPig) entityhuman.vehicle;

            if (entitypig.bQ().h() && itemstack.l() - itemstack.getData() >= 7) {
                entitypig.bQ().g();
                itemstack.damage(7, entityhuman);
                if (itemstack.count == 0) {
                    ItemStack itemstack1 = new ItemStack(Item.FISHING_ROD);

                    itemstack1.setTag(itemstack.tag);
                    return itemstack1;
                }
            }
        }

        return itemstack;
    }
}
