package net.minecraft.server;

public abstract class EntityWaterAnimal extends EntityCreature implements IAnimal {

    public EntityWaterAnimal(World world) {
        super(world);
    }

    public boolean f() {
        return true;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public boolean g() {
        return this.world.containsEntity(this.boundingBox);
    }

    public int h() {
        return 120;
    }

    protected boolean d_() {
        return true;
    }

    protected int a(EntityHuman entityhuman) {
        return 1 + this.world.random.nextInt(3);
    }
}
