package net.minecraft.server;

public abstract class EntityWaterAnimal extends EntityCreature implements IAnimal {

    public EntityWaterAnimal(World world) {
        super(world);
    }

    public boolean f_() {
        return true;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public boolean canSpawn() {
        return this.world.containsEntity(this.boundingBox);
    }

    public int m() {
        return 120;
    }

    protected boolean n() {
        return true;
    }

    protected int getExpValue(EntityHuman entityhuman) {
        return 1 + this.world.random.nextInt(3);
    }
}
