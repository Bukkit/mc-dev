package net.minecraft.server;

final class DispenseBehaviorMinecart extends DispenseBehaviorItem {

    private final DispenseBehaviorItem b = new DispenseBehaviorItem();

    DispenseBehaviorMinecart() {}

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.b(isourceblock.h());
        World world = isourceblock.k();
        double d0 = isourceblock.getX() + (double) ((float) enumfacing.c() * 1.125F);
        double d1 = isourceblock.getY() + (double) ((float) enumfacing.d() * 1.125F);
        double d2 = isourceblock.getZ() + (double) ((float) enumfacing.e() * 1.125F);
        int i = isourceblock.getBlockX() + enumfacing.c();
        int j = isourceblock.getBlockY() + enumfacing.d();
        int k = isourceblock.getBlockZ() + enumfacing.e();
        Block block = world.getType(i, j, k);
        double d3;

        if (BlockMinecartTrackAbstract.a(block)) {
            d3 = 0.0D;
        } else {
            if (block.getMaterial() != Material.AIR || !BlockMinecartTrackAbstract.a(world.getType(i, j - 1, k))) {
                return this.b.a(isourceblock, itemstack);
            }

            d3 = -1.0D;
        }

        EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, d0, d1 + d3, d2, ((ItemMinecart) itemstack.getItem()).a);

        if (itemstack.hasName()) {
            entityminecartabstract.a(itemstack.getName());
        }

        world.addEntity(entityminecartabstract);
        itemstack.a(1);
        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {
        isourceblock.k().triggerEffect(1000, isourceblock.getBlockX(), isourceblock.getBlockY(), isourceblock.getBlockZ(), 0);
    }
}
