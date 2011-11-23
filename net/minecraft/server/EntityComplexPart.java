package net.minecraft.server;

public class EntityComplexPart extends Entity {

    public final EntityComplex a;
    public final String b;

    public EntityComplexPart(EntityComplex entitycomplex, String s, float f, float f1) {
        super(entitycomplex.world);
        this.b(f, f1);
        this.a = entitycomplex;
        this.b = s;
    }

    protected void b() {}

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}

    public boolean e_() {
        return true;
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        return this.a.a(this, damagesource, i);
    }

    public boolean a(Entity entity) {
        return this == entity || this.a == entity;
    }
}
