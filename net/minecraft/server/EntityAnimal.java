package net.minecraft.server;

public abstract class EntityAnimal extends EntityCreature implements IAnimal {

    public EntityAnimal(World world) {
        super(world);
    }

    protected float a(int i, int j, int k) {
        return this.h.a(i, j - 1, k) == Block.GRASS.bc ? 10.0F : this.h.j(i, j, k) - 0.5F;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public boolean a() {
        int i = MathHelper.b(this.l);
        int j = MathHelper.b(this.v.b);
        int k = MathHelper.b(this.n);

        return this.h.a(i, j - 1, k) == Block.GRASS.bc && this.h.h(i, j, k) > 8 && super.a();
    }

    public int b() {
        return 120;
    }
}
