package net.minecraft.server;

public class EntityPig extends EntityAnimal {

    public EntityPig(World world) {
        super(world);
        this.texture = "/mob/pig.png";
        this.a(0.9F, 0.9F);
    }

    protected void a() {
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Saddle", this.r());
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a(nbttagcompound.m("Saddle"));
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
        if (this.r() && !this.world.isStatic && (this.passenger == null || this.passenger == entityhuman)) {
            entityhuman.b((Entity) this);
            return true;
        } else {
            return false;
        }
    }

    protected int h() {
        return Item.PORK.id;
    }

    public boolean r() {
        return (this.datawatcher.a(16) & 1) != 0;
    }

    public void a(boolean flag) {
        if (flag) {
            this.datawatcher.b(16, Byte.valueOf((byte) 1));
        } else {
            this.datawatcher.b(16, Byte.valueOf((byte) 0));
        }
    }
}
