package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void a(float f) {}

    protected String aX() {
        return "none";
    }

    protected String aY() {
        return "none";
    }

    protected String aZ() {
        return "none";
    }

    public int aM() {
        return 120;
    }

    protected boolean bi() {
        return false;
    }
}
