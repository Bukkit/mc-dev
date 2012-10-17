package net.minecraft.server;

public class DispenseBehaviorSnowBall extends DispenseBehaviorProjectile {

    final MinecraftServer b;

    public DispenseBehaviorSnowBall(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
    }

    protected IProjectile a(World world, IPosition iposition) {
        return new EntitySnowball(world, iposition.getX(), iposition.getY(), iposition.getZ());
    }
}
