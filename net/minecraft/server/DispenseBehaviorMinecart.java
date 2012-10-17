package net.minecraft.server;

public class DispenseBehaviorMinecart extends DispenseBehaviorItem {

    private final DispenseBehaviorItem c;

    final MinecraftServer b;

    public DispenseBehaviorMinecart(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
        this.c = new DispenseBehaviorItem();
    }

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = EnumFacing.a(isourceblock.h());
        World world = isourceblock.k();
        double d0 = isourceblock.getX() + (double) ((float) enumfacing.c() * 1.125F);
        double d1 = isourceblock.getY();
        double d2 = isourceblock.getZ() + (double) ((float) enumfacing.e() * 1.125F);
        int i = isourceblock.getBlockX() + enumfacing.c();
        int j = isourceblock.getBlockY();
        int k = isourceblock.getBlockZ() + enumfacing.e();
        int l = world.getTypeId(i, j, k);
        double d3;

        if (BlockMinecartTrack.d(l)) {
            d3 = 0.0D;
        } else {
            if (l != 0 || !BlockMinecartTrack.d(world.getTypeId(i, j - 1, k))) {
                return this.c.a(isourceblock, itemstack);
            }

            d3 = -1.0D;
        }

        EntityMinecart entityminecart = new EntityMinecart(world, d0, d1 + d3, d2, ((ItemMinecart) itemstack.getItem()).a);

        world.addEntity(entityminecart);
        itemstack.a(1);
        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {
        isourceblock.k().triggerEffect(1000, isourceblock.getBlockX(), isourceblock.getBlockY(), isourceblock.getBlockZ(), 0);
    }
}
