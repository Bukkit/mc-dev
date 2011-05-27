package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.aG = "/mob/zombie.png";
        this.bt = 0.5F;
        this.e = 50;
        this.aQ *= 10;
        this.H *= 6.0F;
        this.a(this.I * 6.0F, this.J * 6.0F);
    }

    protected float a(int i, int j, int k) {
        return this.l.j(i, j, k) - 0.5F;
    }
}
