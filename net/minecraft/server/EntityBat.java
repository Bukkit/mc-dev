package net.minecraft.server;

import java.util.Calendar;

public class EntityBat extends EntityAmbient {

    private ChunkCoordinates h;

    public EntityBat(World world) {
        super(world);
        this.a(0.5F, 0.9F);
        this.a(true);
    }

    protected void c() {
        super.c();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    protected float bf() {
        return 0.1F;
    }

    protected float bg() {
        return super.bg() * 0.95F;
    }

    protected String t() {
        return this.bN() && this.random.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    protected String aT() {
        return "mob.bat.hurt";
    }

    protected String aU() {
        return "mob.bat.death";
    }

    public boolean S() {
        return false;
    }

    protected void n(Entity entity) {}

    protected void bo() {}

    protected void aD() {
        super.aD();
        this.getAttributeInstance(GenericAttributes.a).setValue(6.0D);
    }

    public boolean bN() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void a(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    protected boolean bk() {
        return true;
    }

    public void h() {
        super.h();
        if (this.bN()) {
            this.motX = this.motY = this.motZ = 0.0D;
            this.locY = (double) MathHelper.floor(this.locY) + 1.0D - (double) this.length;
        } else {
            this.motY *= 0.6000000238418579D;
        }
    }

    protected void bn() {
        super.bn();
        if (this.bN()) {
            if (!this.world.getType(MathHelper.floor(this.locX), (int) this.locY + 1, MathHelper.floor(this.locZ)).r()) {
                this.a(false);
                this.world.a((EntityHuman) null, 1015, (int) this.locX, (int) this.locY, (int) this.locZ, 0);
            } else {
                if (this.random.nextInt(200) == 0) {
                    this.aP = (float) this.random.nextInt(360);
                }

                if (this.world.findNearbyPlayer(this, 4.0D) != null) {
                    this.a(false);
                    this.world.a((EntityHuman) null, 1015, (int) this.locX, (int) this.locY, (int) this.locZ, 0);
                }
            }
        } else {
            if (this.h != null && (!this.world.isEmpty(this.h.x, this.h.y, this.h.z) || this.h.y < 1)) {
                this.h = null;
            }

            if (this.h == null || this.random.nextInt(30) == 0 || this.h.e((int) this.locX, (int) this.locY, (int) this.locZ) < 4.0F) {
                this.h = new ChunkCoordinates((int) this.locX + this.random.nextInt(7) - this.random.nextInt(7), (int) this.locY + this.random.nextInt(6) - 2, (int) this.locZ + this.random.nextInt(7) - this.random.nextInt(7));
            }

            double d0 = (double) this.h.x + 0.5D - this.locX;
            double d1 = (double) this.h.y + 0.1D - this.locY;
            double d2 = (double) this.h.z + 0.5D - this.locZ;

            this.motX += (Math.signum(d0) * 0.5D - this.motX) * 0.10000000149011612D;
            this.motY += (Math.signum(d1) * 0.699999988079071D - this.motY) * 0.10000000149011612D;
            this.motZ += (Math.signum(d2) * 0.5D - this.motZ) * 0.10000000149011612D;
            float f = (float) (Math.atan2(this.motZ, this.motX) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f1 = MathHelper.g(f - this.yaw);

            this.bf = 0.5F;
            this.yaw += f1;
            if (this.random.nextInt(100) == 0 && this.world.getType(MathHelper.floor(this.locX), (int) this.locY + 1, MathHelper.floor(this.locZ)).r()) {
                this.a(true);
            }
        }
    }

    protected boolean g_() {
        return false;
    }

    protected void b(float f) {}

    protected void a(double d0, boolean flag) {}

    public boolean az() {
        return true;
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable()) {
            return false;
        } else {
            if (!this.world.isStatic && this.bN()) {
                this.a(false);
            }

            return super.damageEntity(damagesource, f);
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
            Calendar calendar = this.world.V();

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
}
