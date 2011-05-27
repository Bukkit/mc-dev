package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.aP = "/mob/zombie.png";
        this.bC = 0.5F;
        this.c = 50;
        this.aZ *= 10;
        this.H *= 6.0F;
        this.a(this.I * 6.0F, this.J * 6.0F);
    }

    protected float a(int i, int j, int k) {
        return this.l.l(i, j, k) - 0.5F;
    }
}
