package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.aQ = "/mob/zombie.png";
        this.bD = 0.5F;
        this.f = 50;
        this.ba *= 10;
        this.H *= 6.0F;
        this.a(this.I * 6.0F, this.J * 6.0F);
    }

    protected float a(int i, int j, int k) {
        return this.l.k(i, j, k) - 0.5F;
    }
}
