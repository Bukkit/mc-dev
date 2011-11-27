package net.minecraft.server;

public class EntityChicken extends EntityAnimal {

    public boolean a = false;
    public float b = 0.0F;
    public float c = 0.0F;
    public float g;
    public float h;
    public float i = 1.0F;
    public int j;

    public EntityChicken(World world) {
        super(world);
        this.texture = "/mob/chicken.png";
        this.b(0.3F, 0.7F);
        this.j = this.random.nextInt(6000) + 6000;
    }

    public int getMaxHealth() {
        return 4;
    }

    public void d() {
        super.d();
        this.h = this.b;
        this.g = this.c;
        this.c = (float) ((double) this.c + (double) (this.onGround ? -1 : 4) * 0.3D);
        if (this.c < 0.0F) {
            this.c = 0.0F;
        }

        if (this.c > 1.0F) {
            this.c = 1.0F;
        }

        if (!this.onGround && this.i < 1.0F) {
            this.i = 1.0F;
        }

        this.i = (float) ((double) this.i * 0.9D);
        if (!this.onGround && this.motY < 0.0D) {
            this.motY *= 0.6D;
        }

        this.b += this.i * 2.0F;
        if (!this.l() && !this.world.isStatic && --this.j <= 0) {
            this.world.makeSound(this, "mob.chickenplop", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.b(Item.EGG.id, 1);
            this.j = this.random.nextInt(6000) + 6000;
        }
    }

    protected void b(float f) {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected String c_() {
        return "mob.chicken";
    }

    protected String m() {
        return "mob.chickenhurt";
    }

    protected String n() {
        return "mob.chickenhurt";
    }

    protected int e() {
        return Item.FEATHER.id;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3) + this.random.nextInt(1 + i);

        for (int k = 0; k < j; ++k) {
            this.b(Item.FEATHER.id, 1);
        }

        if (this.z()) {
            this.b(Item.COOKED_CHICKEN.id, 1);
        } else {
            this.b(Item.RAW_CHICKEN.id, 1);
        }
    }

    protected EntityAnimal createChild(EntityAnimal entityanimal) {
        return new EntityChicken(this.world);
    }
}
