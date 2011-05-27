package net.minecraft.server;

public class EntityWaterAnimal extends EntityCreature implements IAnimal {

    public EntityWaterAnimal(World world) {
        super(world);
    }

    public boolean b_() {
        return true;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public boolean b() {
        return this.world.containsEntity(this.boundingBox);
    }

    public int c() {
        return 120;
    }
}
