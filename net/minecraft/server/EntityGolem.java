package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void a(float f) {}

    protected String aQ() {
        return "none";
    }

    protected String aR() {
        return "none";
    }

    protected String aS() {
        return "none";
    }

    public int aG() {
        return 120;
    }

    protected boolean ba() {
        return false;
    }
}
