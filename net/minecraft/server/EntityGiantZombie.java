package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.height *= 6.0F;
        this.a(this.width * 6.0F, this.length * 6.0F);
    }

    protected void ax() {
        super.ax();
        this.a(GenericAttributes.a).a(100.0D);
        this.a(GenericAttributes.d).a(0.5D);
        this.a(GenericAttributes.e).a(50.0D);
    }

    public float a(int i, int j, int k) {
        return this.world.q(i, j, k) - 0.5F;
    }
}
