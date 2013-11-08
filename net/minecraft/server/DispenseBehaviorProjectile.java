package net.minecraft.server;

public abstract class DispenseBehaviorProjectile extends DispenseBehaviorItem {

    public DispenseBehaviorProjectile() {}

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        World world = isourceblock.k();
        IPosition iposition = BlockDispenser.a(isourceblock);
        EnumFacing enumfacing = BlockDispenser.b(isourceblock.h());
        IProjectile iprojectile = this.a(world, iposition);

        iprojectile.shoot((double) enumfacing.c(), (double) ((float) enumfacing.d() + 0.1F), (double) enumfacing.e(), this.b(), this.a());
        world.addEntity((Entity) iprojectile);
        itemstack.a(1);
        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {
        isourceblock.k().triggerEffect(1002, isourceblock.getBlockX(), isourceblock.getBlockY(), isourceblock.getBlockZ(), 0);
    }

    protected abstract IProjectile a(World world, IPosition iposition);

    protected float a() {
        return 6.0F;
    }

    protected float b() {
        return 1.1F;
    }
}
