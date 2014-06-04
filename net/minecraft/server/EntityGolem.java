package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void b(float f) {}

    protected String t() {
        return "none";
    }

    protected String aT() {
        return "none";
    }

    protected String aU() {
        return "none";
    }

    public int q() {
        return 120;
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }
}
