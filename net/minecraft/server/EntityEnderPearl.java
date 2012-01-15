package net.minecraft.server;

public class EntityEnderPearl extends EntityProjectile {

    public EntityEnderPearl(World world) {
        super(world);
    }

    public EntityEnderPearl(World world, EntityLiving entityliving) {
        super(world, entityliving);
    }

    public EntityEnderPearl(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.entity != null && movingobjectposition.entity.damageEntity(DamageSource.projectile(this, this.shooter), 0)) {
            ;
        }

        for (int i = 0; i < 32; ++i) {
            this.world.a("portal", this.locX, this.locY + this.random.nextDouble() * 2.0D, this.locZ, this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
        }

        if (!this.world.isStatic) {
            if (this.shooter != null) {
                this.shooter.enderTeleportTo(this.locX, this.locY, this.locZ);
                this.shooter.fallDistance = 0.0F;
                this.shooter.damageEntity(DamageSource.FALL, 5);
            }

            this.die();
        }
    }
}
