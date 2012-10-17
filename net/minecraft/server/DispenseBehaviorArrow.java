package net.minecraft.server;

public class DispenseBehaviorArrow extends DispenseBehaviorProjectile {

    final MinecraftServer b;

    public DispenseBehaviorArrow(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
    }

    protected IProjectile a(World world, IPosition iposition) {
        EntityArrow entityarrow = new EntityArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());

        entityarrow.fromPlayer = 1;
        return entityarrow;
    }
}
