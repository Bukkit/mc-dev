package net.minecraft.server;

import java.util.Random;

final class DispenseBehaviorFireball extends DispenseBehaviorItem {

    DispenseBehaviorFireball() {}

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.b(isourceblock.h());
        IPosition iposition = BlockDispenser.a(isourceblock);
        double d0 = iposition.getX() + (double) ((float) enumfacing.c() * 0.3F);
        double d1 = iposition.getY() + (double) ((float) enumfacing.c() * 0.3F);
        double d2 = iposition.getZ() + (double) ((float) enumfacing.e() * 0.3F);
        World world = isourceblock.k();
        Random random = world.random;
        double d3 = random.nextGaussian() * 0.05D + (double) enumfacing.c();
        double d4 = random.nextGaussian() * 0.05D + (double) enumfacing.d();
        double d5 = random.nextGaussian() * 0.05D + (double) enumfacing.e();

        world.addEntity(new EntitySmallFireball(world, d0, d1, d2, d3, d4, d5));
        itemstack.a(1);
        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {
        isourceblock.k().triggerEffect(1009, isourceblock.getBlockX(), isourceblock.getBlockY(), isourceblock.getBlockZ(), 0);
    }
}
