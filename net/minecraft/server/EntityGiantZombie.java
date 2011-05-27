package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.aH = "/mob/zombie.png";
        this.bu = 0.5F;
        this.f = 50;
        this.aR *= 10;
        this.H *= 6.0F;
        this.a(this.I * 6.0F, this.J * 6.0F);
    }

    protected float a(int i, int j, int k) {
        return this.l.j(i, j, k) - 0.5F;
    }
}
