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

    public int getType() {
        return 5;
    }

    public Block n() {
        return Block.HOPPER;
    }

    public int r() {
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

        if (flag1 != this.u()) {
            this.f(flag1);
        }
    }

    public boolean u() {
        return this.a;
    }

    public void f(boolean flag) {
        this.a = flag;
    }

    public World getWorld() {
        return this.world;
    }

    public double aA() {
        return this.locX;
    }

    public double aB() {
        return this.locY;
    }

    public double aC() {
        return this.locZ;
    }

    public void l_() {
        super.l_();
        if (!this.world.isStatic && this.isAlive() && this.u()) {
            --this.b;
            if (!this.aE()) {
                this.l(0);
                if (this.aD()) {
                    this.l(4);
                    this.update();
                }
            }
        }
    }

    public boolean aD() {
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
        this.a(Block.HOPPER.id, 1, 0.0F);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("TransferCooldown", this.b);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.b = nbttagcompound.getInt("TransferCooldown");
    }

    public void l(int i) {
        this.b = i;
    }

    public boolean aE() {
        return this.b > 0;
    }
}
