package net.minecraft.server;

final class DispenseBehaviorMonsterEgg extends DispenseBehaviorItem {

    DispenseBehaviorMonsterEgg() {}

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.b(isourceblock.h());
        double d0 = isourceblock.getX() + (double) enumfacing.c();
        double d1 = (double) ((float) isourceblock.getBlockY() + 0.2F);
        double d2 = isourceblock.getZ() + (double) enumfacing.e();
        Entity entity = ItemMonsterEgg.a(isourceblock.k(), itemstack.getData(), d0, d1, d2);

        if (entity instanceof EntityLiving && itemstack.hasName()) {
            ((EntityInsentient) entity).setCustomName(itemstack.getName());
        }

        itemstack.a(1);
        return itemstack;
    }
}
