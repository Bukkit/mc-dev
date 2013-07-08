package net.minecraft.server;

public abstract class EntityAmbient extends EntityInsentient implements IAnimal {

    public EntityAmbient(World world) {
        super(world);
    }

    public boolean bG() {
        return false;
    }

    protected boolean a(EntityHuman entityhuman) {
        return false;
    }
}
