package net.minecraft.server;

public class PathfinderGoalInteract extends PathfinderGoalLookAtPlayer {

    public PathfinderGoalInteract(EntityLiving entityliving, Class oclass, float f) {
        super(entityliving, oclass, f);
        this.a(3);
    }

    public PathfinderGoalInteract(EntityLiving entityliving, Class oclass, float f, float f1) {
        super(entityliving, oclass, f, f1);
        this.a(3);
    }
}
