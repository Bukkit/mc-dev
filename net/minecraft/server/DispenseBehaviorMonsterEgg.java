package net.minecraft.server;

public class DispenseBehaviorMonsterEgg extends DispenseBehaviorItem {

    final MinecraftServer b;

    public DispenseBehaviorMonsterEgg(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
    }

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = EnumFacing.a(isourceblock.h());
        double d0 = isourceblock.getX() + (double) enumfacing.c();
        double d1 = (double) ((float) isourceblock.getBlockY() + 0.2F);
        double d2 = isourceblock.getZ() + (double) enumfacing.e();

        ItemMonsterEgg.a(isourceblock.k(), itemstack.getData(), d0, d1, d2);
        itemstack.a(1);
        return itemstack;
    }
}
