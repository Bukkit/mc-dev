package net.minecraft.server;

public class EntityPig extends EntityAnimal {

    public boolean a = false;

    public EntityPig(World world) {
        super(world);
        this.aG = "/mob/pig.png";
        this.a(0.9F, 0.9F);
        this.a = false;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Saddle", this.a);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.l("Saddle");
    }

    protected String d() {
        return "mob.pig";
    }

    protected String e() {
        return "mob.pig";
    }

    protected String f() {
        return "mob.pigdeath";
    }

    public boolean a(EntityHuman entityhuman) {
        if (this.a) {
            entityhuman.e(this);
            return true;
        } else {
            return false;
        }
    }

    protected int g() {
        return Item.PORK.aW;
    }
}
