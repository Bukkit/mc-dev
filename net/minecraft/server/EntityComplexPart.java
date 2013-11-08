package net.minecraft.server;

public class EntityComplexPart extends Entity {

    public final IComplex owner;
    public final String b;

    public EntityComplexPart(IComplex icomplex, String s, float f, float f1) {
        super(icomplex.a());
        this.a(f, f1);
        this.owner = icomplex;
        this.b = s;
    }

    protected void c() {}

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}

    public boolean R() {
        return true;
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        return this.isInvulnerable() ? false : this.owner.a(this, damagesource, f);
    }

    public boolean h(Entity entity) {
        return this == entity || this.owner == entity;
    }
}
