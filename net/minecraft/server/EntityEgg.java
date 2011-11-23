package net.minecraft.server;

public class EntityEgg extends EntityProjectile {

    public EntityEgg(World world) {
        super(world);
    }

    public EntityEgg(World world, EntityLiving entityliving) {
        super(world, entityliving);
    }

    public EntityEgg(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.entity != null && movingobjectposition.entity.damageEntity(DamageSource.projectile(this, this.shooter), 0)) {
            ;
        }

        if (!this.world.isStatic && this.random.nextInt(8) == 0) {
            byte b0 = 1;

            if (this.random.nextInt(32) == 0) {
                b0 = 4;
            }

            for (int i = 0; i < b0; ++i) {
                EntityChicken entitychicken = new EntityChicken(this.world);

                entitychicken.setAge(-24000);
                entitychicken.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
                this.world.addEntity(entitychicken);
            }
        }

        for (int j = 0; j < 8; ++j) {
            this.world.a("snowballpoof", this.locX, this.locY, this.locZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isStatic) {
            this.die();
        }
    }
}
