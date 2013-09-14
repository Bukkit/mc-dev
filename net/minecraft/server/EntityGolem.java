package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void b(float f) {}

    protected String r() {
        return "none";
    }

    protected String aO() {
        return "none";
    }

    protected String aP() {
        return "none";
    }

    public int o() {
        return 120;
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }
}
