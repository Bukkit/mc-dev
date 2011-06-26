package net.minecraft.server;

public class EntityMonster extends EntityCreature implements IMonster {

    protected int damage = 2;

    public EntityMonster(World world) {
        super(world);
        this.health = 20;
    }

    public void u() {
        float f = this.c(1.0F);

        if (f > 0.5F) {
            this.ay += 2;
        }

        super.u();
    }

    public void o_() {
        super.o_();
        if (!this.world.isStatic && this.world.spawnMonsters == 0) {
            this.die();
        }
    }

    protected Entity findTarget() {
        EntityHuman entityhuman = this.world.findNearbyPlayer(this, 16.0D);

        return entityhuman != null && this.e(entityhuman) ? entityhuman : null;
    }

    public boolean damageEntity(Entity entity, int i) {
        if (super.damageEntity(entity, i)) {
            if (this.passenger != entity && this.vehicle != entity) {
                if (entity != this) {
                    this.target = entity;
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
        if (this.attackTicks <= 0 && f < 2.0F && entity.boundingBox.e > this.boundingBox.b && entity.boundingBox.b < this.boundingBox.e) {
            this.attackTicks = 20;
            entity.damageEntity(this, this.damage);
        }
    }

    protected float a(int i, int j, int k) {
        return 0.5F - this.world.m(i, j, k);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public boolean d() {
        int i = MathHelper.floor(this.locX);
        int j = MathHelper.floor(this.boundingBox.b);
        int k = MathHelper.floor(this.locZ);

        if (this.world.a(EnumSkyBlock.SKY, i, j, k) > this.random.nextInt(32)) {
            return false;
        } else {
            int l = this.world.getLightLevel(i, j, k);

            if (this.world.u()) {
                int i1 = this.world.f;

                this.world.f = 10;
                l = this.world.getLightLevel(i, j, k);
                this.world.f = i1;
            }

            return l <= this.random.nextInt(8) && super.d();
        }
    }
}
