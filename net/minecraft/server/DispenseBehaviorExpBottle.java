package net.minecraft.server;

public class DispenseBehaviorExpBottle extends DispenseBehaviorProjectile {

    final MinecraftServer b;

    public DispenseBehaviorExpBottle(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
    }

    protected IProjectile a(World world, IPosition iposition) {
        return new EntityThrownExpBottle(world, iposition.getX(), iposition.getY(), iposition.getZ());
    }

    protected float a() {
        return super.a() * 0.5F;
    }

    protected float b() {
        return super.b() * 1.25F;
    }
}
