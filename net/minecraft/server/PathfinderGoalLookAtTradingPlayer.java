package net.minecraft.server;

public class PathfinderGoalLookAtTradingPlayer extends PathfinderGoalLookAtPlayer {

    private final EntityVillager b;

    public PathfinderGoalLookAtTradingPlayer(EntityVillager entityvillager) {
        super(entityvillager, EntityHuman.class, 8.0F);
        this.b = entityvillager;
    }

    public boolean a() {
        if (this.b.ca()) {
            this.a = this.b.b();
            return true;
        } else {
            return false;
        }
    }
}
