package net.minecraft.server;

final class DispenseBehaviorSnowBall extends DispenseBehaviorProjectile {

    DispenseBehaviorSnowBall() {}

    protected IProjectile a(World world, IPosition iposition) {
        return new EntitySnowball(world, iposition.getX(), iposition.getY(), iposition.getZ());
    }
}
