package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void a(float f) {}

    protected String bb() {
        return "none";
    }

    protected String bc() {
        return "none";
    }

    protected String bd() {
        return "none";
    }

    public int aQ() {
        return 120;
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }
}
