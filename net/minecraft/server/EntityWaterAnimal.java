package net.minecraft.server;

public abstract class EntityWaterAnimal extends EntityCreature implements IAnimal {

    public EntityWaterAnimal(World world) {
        super(world);
    }

    public boolean ba() {
        return true;
    }

    public boolean canSpawn() {
        return this.world.b(this.boundingBox);
    }

    public int aM() {
        return 120;
    }

    protected boolean bg() {
        return true;
    }

    protected int getExpValue(EntityHuman entityhuman) {
        return 1 + this.world.random.nextInt(3);
    }
}
