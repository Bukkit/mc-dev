package net.minecraft.server;

public class EntityMinecartFurnace extends EntityMinecartAbstract {

    private int c;
    public double a;
    public double b;

    public EntityMinecartFurnace(World world) {
        super(world);
    }

    public EntityMinecartFurnace(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public int m() {
        return 2;
    }

    protected void c() {
        super.c();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    public void h() {
        super.h();
        if (this.c > 0) {
            --this.c;
        }

        if (this.c <= 0) {
            this.a = this.b = 0.0D;
        }

        this.f(this.c > 0);
        if (this.e() && this.random.nextInt(4) == 0) {
            this.world.addParticle("largesmoke", this.locX, this.locY + 0.8D, this.locZ, 0.0D, 0.0D, 0.0D);
        }
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        if (!damagesource.c()) {
            this.a(new ItemStack(Blocks.FURNACE, 1), 0.0F);
        }
    }

    protected void a(int i, int j, int k, double d0, double d1, Block block, int l) {
        super.a(i, j, k, d0, d1, block, l);
        double d2 = this.a * this.a + this.b * this.b;

        if (d2 > 1.0E-4D && this.motX * this.motX + this.motZ * this.motZ > 0.001D) {
            d2 = (double) MathHelper.sqrt(d2);
            this.a /= d2;
            this.b /= d2;
            if (this.a * this.motX + this.b * this.motZ < 0.0D) {
                this.a = 0.0D;
                this.b = 0.0D;
            } else {
                this.a = this.motX;
                this.b = this.motZ;
            }
        }
    }

    protected void i() {
        double d0 = this.a * this.a + this.b * this.b;

        if (d0 > 1.0E-4D) {
            d0 = (double) MathHelper.sqrt(d0);
            this.a /= d0;
            this.b /= d0;
            double d1 = 0.05D;

            this.motX *= 0.800000011920929D;
            this.motY *= 0.0D;
            this.motZ *= 0.800000011920929D;
            this.motX += this.a * d1;
            this.motZ += this.b * d1;
        } else {
            this.motX *= 0.9800000190734863D;
            this.motY *= 0.0D;
            this.motZ *= 0.9800000190734863D;
        }

        super.i();
    }

    public boolean c(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.getItem() == Items.COAL) {
            if (!entityhuman.abilities.canInstantlyBuild && --itemstack.count == 0) {
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
            }

            this.c += 3600;
        }

        this.a = this.locX - entityhuman.locX;
        this.b = this.locZ - entityhuman.locZ;
        return true;
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setDouble("PushX", this.a);
        nbttagcompound.setDouble("PushZ", this.b);
        nbttagcompound.setShort("Fuel", (short) this.c);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.getDouble("PushX");
        this.b = nbttagcompound.getDouble("PushZ");
        this.c = nbttagcompound.getShort("Fuel");
    }

    protected boolean e() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    protected void f(boolean flag) {
        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (this.datawatcher.getByte(16) | 1)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (this.datawatcher.getByte(16) & -2)));
        }
    }

    public Block o() {
        return Blocks.BURNING_FURNACE;
    }

    public int q() {
        return 2;
    }
}
