package net.minecraft.server;

public class EntityChicken extends EntityAnimal {

    public boolean a = false;
    public float b = 0.0F;
    public float c = 0.0F;
    public float d;
    public float e;
    public float aj = 1.0F;
    public int ak;

    public EntityChicken(World world) {
        super(world);
        this.aF = "/mob/chicken.png";
        this.a(0.3F, 0.4F);
        this.aP = 4;
        this.ak = this.V.nextInt(6000) + 6000;
    }

    public void D() {
        super.D();
        this.e = this.b;
        this.d = this.c;
        this.c = (float) ((double) this.c + (double) (this.A ? -1 : 4) * 0.3D);
        if (this.c < 0.0F) {
            this.c = 0.0F;
        }

        if (this.c > 1.0F) {
            this.c = 1.0F;
        }

        if (!this.A && this.aj < 1.0F) {
            this.aj = 1.0F;
        }

        this.aj = (float) ((double) this.aj * 0.9D);
        if (!this.A && this.t < 0.0D) {
            this.t *= 0.6D;
        }

        this.b += this.aj * 2.0F;
        if (!this.l.z && --this.ak <= 0) {
            this.l.a(this, "mob.chickenplop", 1.0F, (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
            this.a(Item.EGG.aW, 1);
            this.ak = this.V.nextInt(6000) + 6000;
        }
    }

    protected void a(float f) {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected String d() {
        return "mob.chicken";
    }

    protected String e() {
        return "mob.chickenhurt";
    }

    protected String f() {
        return "mob.chickenhurt";
    }

    protected int g() {
        return Item.FEATHER.aW;
    }
}
