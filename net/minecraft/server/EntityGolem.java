package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void a(float f) {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected String i() {
        return "none";
    }

    protected String j() {
        return "none";
    }

    protected String k() {
        return "none";
    }

    public int m() {
        return 120;
    }

    protected boolean n() {
        return false;
    }
}
