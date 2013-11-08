package net.minecraft.server;

public class EntityEnderPearl extends EntityProjectile {

    public EntityEnderPearl(World world) {
        super(world);
    }

    public EntityEnderPearl(World world, EntityLiving entityliving) {
        super(world, entityliving);
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.entity != null) {
            movingobjectposition.entity.damageEntity(DamageSource.projectile(this, this.getShooter()), 0.0F);
        }

        for (int i = 0; i < 32; ++i) {
            this.world.addParticle("portal", this.locX, this.locY + this.random.nextDouble() * 2.0D, this.locZ, this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
        }

        if (!this.world.isStatic) {
            if (this.getShooter() != null && this.getShooter() instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) this.getShooter();

                if (entityplayer.playerConnection.b().d() && entityplayer.world == this.world) {
                    if (this.getShooter().am()) {
                        this.getShooter().mount((Entity) null);
                    }

                    this.getShooter().enderTeleportTo(this.locX, this.locY, this.locZ);
                    this.getShooter().fallDistance = 0.0F;
                    this.getShooter().damageEntity(DamageSource.FALL, 5.0F);
                }
            }

            this.die();
        }
    }
}
