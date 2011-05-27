package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.texture = "/mob/zombie.png";
        this.az = 0.5F;
        this.c = 50;
        this.health *= 10;
        this.height *= 6.0F;
        this.a(this.length * 6.0F, this.width * 6.0F);
    }

    protected float a(int i, int j, int k) {
        return this.world.l(i, j, k) - 0.5F;
    }
}
