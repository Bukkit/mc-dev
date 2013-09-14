package net.minecraft.server;

public abstract class EntityWaterAnimal extends EntityCreature implements IAnimal {

    public EntityWaterAnimal(World world) {
        super(world);
    }

    public boolean aA() {
        return true;
    }

    public boolean canSpawn() {
        return this.world.b(this.boundingBox);
    }

    public int o() {
        return 120;
    }

    protected boolean isTypeNotPersistent() {
        return true;
    }

    protected int getExpValue(EntityHuman entityhuman) {
        return 1 + this.world.random.nextInt(3);
    }

    public void y() {
        int i = this.getAirTicks();

        super.y();
        if (this.isAlive() && !this.H()) {
            --i;
            this.setAirTicks(i);
            if (this.getAirTicks() == -20) {
                this.setAirTicks(0);
                this.damageEntity(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.setAirTicks(300);
        }
    }
}
