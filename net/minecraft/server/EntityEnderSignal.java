package net.minecraft.server;

public class EntityEnderSignal extends Entity {

    public int a = 0;
    private double b;
    private double c;
    private double d;
    private int e;
    private boolean f;

    public EntityEnderSignal(World world) {
        super(world);
        this.b(0.25F, 0.25F);
    }

    protected void b() {}

    public EntityEnderSignal(World world, double d0, double d1, double d2) {
        super(world);
        this.e = 0;
        this.b(0.25F, 0.25F);
        this.setPosition(d0, d1, d2);
        this.height = 0.0F;
    }

    public void a(double d0, int i, double d1) {
        double d2 = d0 - this.locX;
        double d3 = d1 - this.locZ;
        float f = MathHelper.a(d2 * d2 + d3 * d3);

        if (f > 12.0F) {
            this.b = this.locX + d2 / (double) f * 12.0D;
            this.d = this.locZ + d3 / (double) f * 12.0D;
            this.c = this.locY + 8.0D;
        } else {
            this.b = d0;
            this.c = (double) i;
            this.d = d1;
        }

        this.e = 0;
        this.f = this.random.nextInt(5) > 0;
    }

    public void w_() {
        this.bI = this.locX;
        this.bJ = this.locY;
        this.bK = this.locZ;
        super.w_();
        this.locX += this.motX;
        this.locY += this.motY;
        this.locZ += this.motZ;
        float f = MathHelper.a(this.motX * this.motX + this.motZ * this.motZ);

        this.yaw = (float) (Math.atan2(this.motX, this.motZ) * 180.0D / 3.1415927410125732D);

        for (this.pitch = (float) (Math.atan2(this.motY, (double) f) * 180.0D / 3.1415927410125732D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F) {
            ;
        }

        while (this.pitch - this.lastPitch >= 180.0F) {
            this.lastPitch += 360.0F;
        }

        while (this.yaw - this.lastYaw < -180.0F) {
            this.lastYaw -= 360.0F;
        }

        while (this.yaw - this.lastYaw >= 180.0F) {
            this.lastYaw += 360.0F;
        }

        this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
        this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
        if (!this.world.isStatic) {
            double d0 = this.b - this.locX;
            double d1 = this.d - this.locZ;
            float f1 = (float) Math.sqrt(d0 * d0 + d1 * d1);
            float f2 = (float) Math.atan2(d1, d0);
            double d2 = (double) f + (double) (f1 - f) * 0.0025D;

            if (f1 < 1.0F) {
                d2 *= 0.8D;
                this.motY *= 0.8D;
            }

            this.motX = Math.cos((double) f2) * d2;
            this.motZ = Math.sin((double) f2) * d2;
            if (this.locY < this.c) {
                this.motY += (1.0D - this.motY) * 0.014999999664723873D;
            } else {
                this.motY += (-1.0D - this.motY) * 0.014999999664723873D;
            }
        }

        float f3 = 0.25F;

        if (this.az()) {
            for (int i = 0; i < 4; ++i) {
                this.world.a("bubble", this.locX - this.motX * (double) f3, this.locY - this.motY * (double) f3, this.locZ - this.motZ * (double) f3, this.motX, this.motY, this.motZ);
            }
        } else {
            this.world.a("portal", this.locX - this.motX * (double) f3 + this.random.nextDouble() * 0.6D - 0.3D, this.locY - this.motY * (double) f3 - 0.5D, this.locZ - this.motZ * (double) f3 + this.random.nextDouble() * 0.6D - 0.3D, this.motX, this.motY, this.motZ);
        }

        if (!this.world.isStatic) {
            this.setPosition(this.locX, this.locY, this.locZ);
            ++this.e;
            if (this.e > 80 && !this.world.isStatic) {
                this.die();
                if (this.f) {
                    this.world.addEntity(new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Item.EYE_OF_ENDER)));
                } else {
                    this.world.f(2003, (int) Math.round(this.locX), (int) Math.round(this.locY), (int) Math.round(this.locZ), 0);
                }
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {}

    public void a(NBTTagCompound nbttagcompound) {}

    public void a_(EntityHuman entityhuman) {}

    public float a(float f) {
        return 1.0F;
    }
}
