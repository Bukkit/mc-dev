package net.minecraft.server;

public class PathfinderGoalLookAtTradingPlayer extends PathfinderGoalLookAtPlayer {

    private final EntityVillager b;

    public PathfinderGoalLookAtTradingPlayer(EntityVillager entityvillager) {
        super(entityvillager, EntityHuman.class, 8.0F);
        this.b = entityvillager;
    }

    public boolean a() {
        if (this.b.p()) {
            this.a = this.b.m_();
            return true;
        } else {
            return false;
        }
    }
}
