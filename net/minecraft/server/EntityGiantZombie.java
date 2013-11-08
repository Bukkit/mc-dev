package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster {

    public EntityGiantZombie(World world) {
        super(world);
        this.height *= 6.0F;
        this.a(this.width * 6.0F, this.length * 6.0F);
    }

    protected void aD() {
        super.aD();
        this.getAttributeInstance(GenericAttributes.a).setValue(100.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.5D);
        this.getAttributeInstance(GenericAttributes.e).setValue(50.0D);
    }

    public float a(int i, int j, int k) {
        return this.world.n(i, j, k) - 0.5F;
    }
}
