package net.minecraft.server;

public class EntityChicken extends EntityAnimal {

    public boolean a = false;
    public float b = 0.0F;
    public float c = 0.0F;
    public float f;
    public float ak;
    public float al = 1.0F;
    public int am;

    public EntityChicken(World world) {
        super(world);
        this.aP = "/mob/chicken.png";
        this.a(0.3F, 0.4F);
        this.aZ = 4;
        this.am = this.W.nextInt(6000) + 6000;
    }

    public void o() {
        super.o();
        this.ak = this.b;
        this.f = this.c;
        this.c = (float) ((double) this.c + (double) (this.A ? -1 : 4) * 0.3D);
        if (this.c < 0.0F) {
            this.c = 0.0F;
        }

        if (this.c > 1.0F) {
            this.c = 1.0F;
        }

        if (!this.A && this.al < 1.0F) {
            this.al = 1.0F;
        }

        this.al = (float) ((double) this.al * 0.9D);
        if (!this.A && this.t < 0.0D) {
            this.t *= 0.6D;
        }

        this.b += this.al * 2.0F;
        if (!this.l.z && --this.am <= 0) {
            this.l.a(this, "mob.chickenplop", 1.0F, (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            this.a(Item.EGG.ba, 1);
            this.am = this.W.nextInt(6000) + 6000;
        }
    }

    protected void a(float f) {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected String e() {
        return "mob.chicken";
    }

    protected String f() {
        return "mob.chickenhurt";
    }

    protected String g() {
        return "mob.chickenhurt";
    }

    protected int h() {
        return Item.FEATHER.ba;
    }
}
