package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.texture = "/mob/zombie.png";
        this.bw = 0.5F;
        this.damage = 50;
        this.height *= 6.0F;
        this.a(this.width * 6.0F, this.length * 6.0F);
    }

    public int getMaxHealth() {
        return 100;
    }

    public float a(int i, int j, int k) {
        return this.world.o(i, j, k) - 0.5F;
    }
}
