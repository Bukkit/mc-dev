package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.aC = "/mob/zombie.png";
        this.bi = 0.5F;
        this.af = 50;
        this.aM *= 10;
        this.C *= 6.0F;
        this.a(this.D * 6.0F, this.E * 6.0F);
    }

    protected float a(int i, int j, int k) {
        return this.h.j(i, j, k) - 0.5F;
    }
}
