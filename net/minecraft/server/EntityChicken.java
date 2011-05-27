package net.minecraft.server;

public class EntityChicken extends EntityAnimal {

    public boolean a = false;
    public float b = 0.0F;
    public float ad = 0.0F;
    public float ae;
    public float af;
    public float ai = 1.0F;
    public int aj;

    public EntityChicken(World world) {
        super(world);
        this.aC = "/mob/chicken.png";
        this.a(0.3F, 0.4F);
        this.aM = 4;
        this.aj = this.R.nextInt(6000) + 6000;
    }

    public void y() {
        super.y();
        this.af = this.b;
        this.ae = this.ad;
        this.ad = (float) ((double) this.ad + (double) (this.w ? -1 : 4) * 0.3D);
        if (this.ad < 0.0F) {
            this.ad = 0.0F;
        }

        if (this.ad > 1.0F) {
            this.ad = 1.0F;
        }

        if (!this.w && this.ai < 1.0F) {
            this.ai = 1.0F;
        }

        this.ai = (float) ((double) this.ai * 0.9D);
        if (!this.w && this.p < 0.0D) {
            this.p *= 0.6D;
        }

        this.b += this.ai * 2.0F;
        if (!this.h.x && --this.aj <= 0) {
            this.h.a(this, "mob.chickenplop", 1.0F, (this.R.nextFloat() - this.R.nextFloat()) * 0.2F + 1.0F);
            this.a(Item.EGG.aS, 1);
            this.aj = this.R.nextInt(6000) + 6000;
        }
    }

    protected void a(float f) {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected String c() {
        return "mob.chicken";
    }

    protected String d() {
        return "mob.chickenhurt";
    }

    protected String e() {
        return "mob.chickenhurt";
    }

    protected int g() {
        return Item.FEATHER.aS;
    }
}
