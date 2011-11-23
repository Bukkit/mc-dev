package net.minecraft.server;

public abstract class EntityGolem extends EntityCreature implements IAnimal {

    public EntityGolem(World world) {
        super(world);
    }

    protected void b(float f) {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected String c_() {
        return "none";
    }

    protected String m() {
        return "none";
    }

    protected String n() {
        return "none";
    }

    public int h() {
        return 120;
    }

    protected boolean d_() {
        return false;
    }
}
