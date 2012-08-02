package net.minecraft.server;

public class PathfinderGoalInteract extends PathfinderGoalLookAtPlayer {

    public PathfinderGoalInteract(EntityLiving entityliving, Class oclass, float f, float f1) {
        super(entityliving, oclass, f, f1);
        this.a(3);
    }
}
