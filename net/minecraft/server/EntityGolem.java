package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void a(float f) {}

    protected String aY() {
        return "none";
    }

    protected String aZ() {
        return "none";
    }

    protected String ba() {
        return "none";
    }

    public int aN() {
        return 120;
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }
}
