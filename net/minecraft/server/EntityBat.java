package net.minecraft.server;

import java.util.Calendar;

public class EntityBat extends EntityAmbient {

    private ChunkCoordinates a;

    public EntityBat(World world) {
        super(world);
        this.texture = "/mob/bat.png";
        this.a(0.5F, 0.9F);
        this.f(true);
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    protected float aX() {
        return 0.1F;
    }

    protected float aV() {
        return super.aV() * 0.95F;
    }

    protected String aY() {
        return this.h() && this.random.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    protected String aZ() {
        return "mob.bat.hurt";
    }

    protected String ba() {
        return "mob.bat.death";
    }

    public boolean M() {
        return false;
    }

    protected void o(Entity entity) {}

    protected void bd() {}

    public int getMaxHealth() {
        return 6;
    }

    public boolean h() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void f(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    protected boolean be() {
        return true;
    }

    public void j_() {
        super.j_();
        if (this.h()) {
            this.motX = this.motY = this.motZ = 0.0D;
            this.locY = (double) MathHelper.floor(this.locY) + 1.0D - (double) this.length;
        } else {
            this.motY *= 0.6000000238418579D;
        }
    }

    protected void bl() {
        super.bl();
        if (this.h()) {
            if (!this.world.t(MathHelper.floor(this.locX), (int) this.locY + 1, MathHelper.floor(this.locZ))) {
                this.f(false);
                this.world.a((EntityHuman) null, 1015, (int) this.locX, (int) this.locY, (int) this.locZ, 0);
            } else {
                if (this.random.nextInt(200) == 0) {
                    this.az = (float) this.random.nextInt(360);
                }

                if (this.world.findNearbyPlayer(this, 4.0D) != null) {
                    this.f(false);
                    this.world.a((EntityHuman) null, 1015, (int) this.locX, (int) this.locY, (int) this.locZ, 0);
                }
            }
        } else {
            if (this.a != null && (!this.world.isEmpty(this.a.x, this.a.y, this.a.z) || this.a.y < 1)) {
                this.a = null;
            }

            if (this.a == null || this.random.nextInt(30) == 0 || this.a.e((int) this.locX, (int) this.locY, (int) this.locZ) < 4.0F) {
                this.a = new ChunkCoordinates((int) this.locX + this.random.nextInt(7) - this.random.nextInt(7), (int) this.locY + this.random.nextInt(6) - 2, (int) this.locZ + this.random.nextInt(7) - this.random.nextInt(7));
            }

            double d0 = (double) this.a.x + 0.5D - this.locX;
            double d1 = (double) this.a.y + 0.1D - this.locY;
            double d2 = (double) this.a.z + 0.5D - this.locZ;

            this.motX += (Math.signum(d0) * 0.5D - this.motX) * 0.10000000149011612D;
            this.motY += (Math.signum(d1) * 0.699999988079071D - this.motY) * 0.10000000149011612D;
            this.motZ += (Math.signum(d2) * 0.5D - this.motZ) * 0.10000000149011612D;
            float f = (float) (Math.atan2(this.motZ, this.motX) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f1 = MathHelper.g(f - this.yaw);

            this.bD = 0.5F;
            this.yaw += f1;
            if (this.random.nextInt(100) == 0 && this.world.t(MathHelper.floor(this.locX), (int) this.locY + 1, MathHelper.floor(this.locZ))) {
                this.f(true);
            }
        }
    }

    protected boolean f_() {
        return false;
    }

    protected void a(float f) {}

    protected void a(double d0, boolean flag) {}

    public boolean au() {
        return true;
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        if (this.isInvulnerable()) {
            return false;
        } else {
            if (!this.world.isStatic && this.h()) {
                this.f(false);
            }

            return super.damageEntity(damagesource, i);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.datawatcher.watch(16, Byte.valueOf(nbttagcompound.getByte("BatFlags")));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setByte("BatFlags", this.datawatcher.getByte(16));
    }

    public boolean canSpawn() {
        int i = MathHelper.floor(this.boundingBox.b);

        if (i >= 63) {
            return false;
        } else {
            int j = MathHelper.floor(this.locX);
            int k = MathHelper.floor(this.locZ);
            int l = this.world.getLightLevel(j, i, k);
            byte b0 = 4;
            Calendar calendar = this.world.T();

            if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20) && (calendar.get(2) + 1 != 11 || calendar.get(5) > 3)) {
                if (this.random.nextBoolean()) {
                    return false;
                }
            } else {
                b0 = 7;
            }

            return l > this.random.nextInt(b0) ? false : super.canSpawn();
        }
    }

    public void bG() {}
}
