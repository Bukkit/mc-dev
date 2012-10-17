package net.minecraft.server;

public class DispenseBehaviorEgg extends DispenseBehaviorProjectile {

    final MinecraftServer b;

    public DispenseBehaviorEgg(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
    }

    protected IProjectile a(World world, IPosition iposition) {
        return new EntityEgg(world, iposition.getX(), iposition.getY(), iposition.getZ());
    }
}
