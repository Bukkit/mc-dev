package net.minecraft.server;

public abstract class EntityAnimal extends EntityCreature implements IAnimal {

    public EntityAnimal(World world) {
        super(world);
    }

    protected float a(int i, int j, int k) {
        return this.world.getTypeId(i, j - 1, k) == Block.GRASS.id ? 10.0F : this.world.n(i, j, k) - 0.5F;
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

        return this.world.getTypeId(i, j - 1, k) == Block.GRASS.id && this.world.k(i, j, k) > 8 && super.d();
    }

    public int e() {
        return 120;
    }
}
