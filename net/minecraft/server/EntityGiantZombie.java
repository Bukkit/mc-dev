package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.texture = "/mob/zombie.png";
        this.aY = 0.5F;
        this.damage = 50;
        this.height *= 6.0F;
        this.b(this.width * 6.0F, this.length * 6.0F);
    }

    public int getMaxHealth() {
        return 100;
    }

    protected float a(int i, int j, int k) {
        return this.world.m(i, j, k) - 0.5F;
    }
}
