package net.minecraft.server;

public class EntityPig extends EntityAnimal {

    public EntityPig(World world) {
        super(world);
        this.aP = "/mob/pig.png";
        this.a(0.9F, 0.9F);
    }

    protected void a() {
        this.af.a(16, Byte.valueOf((byte) 0));
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Saddle", this.K());
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a(nbttagcompound.l("Saddle"));
    }

    protected String e() {
        return "mob.pig";
    }

    protected String f() {
        return "mob.pig";
    }

    protected String g() {
        return "mob.pigdeath";
    }

    public boolean a(EntityHuman entityhuman) {
        if (this.K() && !this.l.z && (this.j == null || this.j == entityhuman)) {
            entityhuman.e(this);
            return true;
        } else {
            return false;
        }
    }

    protected int h() {
        return Item.PORK.ba;
    }

    public boolean K() {
        return (this.af.a(16) & 1) != 0;
    }

    public void a(boolean flag) {
        if (flag) {
            this.af.b(16, Byte.valueOf((byte) 1));
        } else {
            this.af.b(16, Byte.valueOf((byte) 0));
        }
    }
}
