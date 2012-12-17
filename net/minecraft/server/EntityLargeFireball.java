package net.minecraft.server;

public class EntityLargeFireball extends EntityFireball {

    public int e = 1;

    public EntityLargeFireball(World world) {
        super(world);
    }

    public EntityLargeFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(world, entityliving, d0, d1, d2);
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isStatic) {
            if (movingobjectposition.entity != null) {
                movingobjectposition.entity.damageEntity(DamageSource.fireball(this, this.shooter), 6);
            }

            this.world.createExplosion((Entity) null, this.locX, this.locY, this.locZ, (float) this.e, true, this.world.getGameRules().getBoolean("mobGriefing"));
            this.die();
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("ExplosionPower", this.e);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKey("ExplosionPower")) {
            this.e = nbttagcompound.getInt("ExplosionPower");
        }
    }
}
