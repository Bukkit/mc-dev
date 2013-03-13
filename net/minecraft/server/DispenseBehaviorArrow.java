package net.minecraft.server;

final class DispenseBehaviorArrow extends DispenseBehaviorProjectile {

    DispenseBehaviorArrow() {}

    protected IProjectile a(World world, IPosition iposition) {
        EntityArrow entityarrow = new EntityArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());

        entityarrow.fromPlayer = 1;
        return entityarrow;
    }
}
