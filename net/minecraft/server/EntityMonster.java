package net.minecraft.server;

public class EntityMonster extends EntityCreature implements IMonster {

    protected int af = 2;

    public EntityMonster(World world) {
        super(world);
        this.aM = 20;
    }

    public void y() {
        float f = this.b(1.0F);

        if (f > 0.5F) {
            this.bc += 2;
        }

        super.y();
    }

    public void b_() {
        super.b_();
        if (this.h.l == 0) {
            this.j();
        }
    }

    protected Entity i() {
        EntityHuman entityhuman = this.h.a(this, 16.0D);

        return entityhuman != null && this.g(entityhuman) ? entityhuman : null;
    }

    public boolean a(Entity entity, int i) {
        if (super.a(entity, i)) {
            if (this.f != entity && this.g != entity) {
                if (entity != this) {
                    this.ag = entity;
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    protected void a(Entity entity, float f) {
        if ((double) f < 2.5D && entity.v.e > this.v.b && entity.v.b < this.v.e) {
            this.aS = 20;
            entity.a(this, this.af);
        }
    }

    protected float a(int i, int j, int k) {
        return 0.5F - this.h.j(i, j, k);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public boolean a() {
        int i = MathHelper.b(this.l);
        int j = MathHelper.b(this.v.b);
        int k = MathHelper.b(this.n);

        if (this.h.a(EnumSkyBlock.SKY, i, j, k) > this.R.nextInt(32)) {
            return false;
        } else {
            int l = this.h.h(i, j, k);

            return l <= this.R.nextInt(8) && super.a();
        }
    }
}
