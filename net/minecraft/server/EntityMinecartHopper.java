package net.minecraft.server;

import java.util.List;

public class EntityMinecartHopper extends EntityMinecartContainer implements IHopper {

    private boolean a = true;
    private int b = -1;

    public EntityMinecartHopper(World world) {
        super(world);
    }

    public EntityMinecartHopper(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public int m() {
        return 5;
    }

    public Block o() {
        return Blocks.HOPPER;
    }

    public int s() {
        return 1;
    }

    public int getSize() {
        return 5;
    }

    public boolean c(EntityHuman entityhuman) {
        if (!this.world.isStatic) {
            entityhuman.openMinecartHopper(this);
        }

        return true;
    }

    public void a(int i, int j, int k, boolean flag) {
        boolean flag1 = !flag;

        if (flag1 != this.v()) {
            this.f(flag1);
        }
    }

    public boolean v() {
        return this.a;
    }

    public void f(boolean flag) {
        this.a = flag;
    }

    public World getWorld() {
        return this.world;
    }

    public double x() {
        return this.locX;
    }

    public double aD() {
        return this.locY;
    }

    public double aE() {
        return this.locZ;
    }

    public void h() {
        super.h();
        if (!this.world.isStatic && this.isAlive() && this.v()) {
            --this.b;
            if (!this.aG()) {
                this.n(0);
                if (this.aF()) {
                    this.n(4);
                    this.update();
                }
            }
        }
    }

    public boolean aF() {
        if (TileEntityHopper.suckInItems(this)) {
            return true;
        } else {
            List list = this.world.a(EntityItem.class, this.boundingBox.grow(0.25D, 0.0D, 0.25D), IEntitySelector.a);

            if (list.size() > 0) {
                TileEntityHopper.addEntityItem(this, (EntityItem) list.get(0));
            }

            return false;
        }
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        this.a(Item.getItemOf(Blocks.HOPPER), 1, 0.0F);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("TransferCooldown", this.b);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.b = nbttagcompound.getInt("TransferCooldown");
    }

    public void n(int i) {
        this.b = i;
    }

    public boolean aG() {
        return this.b > 0;
    }
}
