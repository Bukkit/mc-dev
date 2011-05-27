package net.minecraft.server;

import java.util.List;

public class EntityHuman extends EntityLiving {

    public InventoryPlayer aj = new InventoryPlayer(this);
    public byte ak = 0;
    public int al = 0;
    public float am;
    public float an;
    public boolean ao = false;
    public int ap = 0;
    public String aq;
    private int a = 0;

    public EntityHuman(World world) {
        super(world);
        this.C = 1.62F;
        this.c((double) world.n + 0.5D, (double) (world.o + 1), (double) world.p + 0.5D, 0.0F, 0.0F);
        this.aM = 20;
        this.aF = "humanoid";
        this.aE = 180.0F;
        this.T = 20;
        this.aC = "/char.png";
    }

    public void v() {
        super.v();
        this.am = this.an;
        this.an = 0.0F;
    }

    protected void d_() {
        if (this.ao) {
            ++this.ap;
            if (this.ap == 8) {
                this.ap = 0;
                this.ao = false;
            }
        } else {
            this.ap = 0;
        }

        this.aL = (float) this.ap / 8.0F;
    }

    public void y() {
        if (this.h.l == 0 && this.aM < 20 && this.S % 20 * 4 == 0) {
            this.a(1);
        }

        this.aj.c();
        this.am = this.an;
        super.y();
        float f = MathHelper.a(this.o * this.o + this.q * this.q);
        float f1 = (float) Math.atan(-this.p * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.w || this.aM <= 0) {
            f = 0.0F;
        }

        if (this.w || this.aM <= 0) {
            f1 = 0.0F;
        }

        this.an += (f - this.an) * 0.4F;
        this.aU += (f1 - this.aU) * 0.8F;
        if (this.aM > 0) {
            List list = this.h.b((Entity) this, this.v.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    this.h((Entity) list.get(i));
                }
            }
        }
    }

    private void h(Entity entity) {
        entity.a(this);
    }

    public void f(Entity entity) {
        this.a(0.2F, 0.2F);
        this.a(this.l, this.m, this.n);
        this.p = 0.10000000149011612D;
        if (this.aq.equals("Notch")) {
            this.a(new ItemStack(Item.APPLE, 1), true);
        }

        this.aj.f();
        if (entity != null) {
            this.o = (double) (-MathHelper.b((this.aQ + this.r) * 3.1415927F / 180.0F) * 0.1F);
            this.q = (double) (-MathHelper.a((this.aQ + this.r) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.o = this.q = 0.0D;
        }

        this.C = 0.1F;
    }

    public void b(Entity entity, int i) {
        this.al += i;
    }

    public void a(ItemStack itemstack) {
        this.a(itemstack, false);
    }

    public void a(ItemStack itemstack, boolean flag) {
        if (itemstack != null) {
            EntityItem entityitem = new EntityItem(this.h, this.l, this.m - 0.30000001192092896D + (double) this.p(), this.n, itemstack);

            entityitem.ad = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.R.nextFloat() * 0.5F;
                float f2 = this.R.nextFloat() * 3.1415927F * 2.0F;

                entityitem.o = (double) (-MathHelper.a(f2) * f1);
                entityitem.q = (double) (MathHelper.b(f2) * f1);
                entityitem.p = 0.20000000298023224D;
            } else {
                f = 0.3F;
                entityitem.o = (double) (-MathHelper.a(this.r / 180.0F * 3.1415927F) * MathHelper.b(this.s / 180.0F * 3.1415927F) * f);
                entityitem.q = (double) (MathHelper.b(this.r / 180.0F * 3.1415927F) * MathHelper.b(this.s / 180.0F * 3.1415927F) * f);
                entityitem.p = (double) (-MathHelper.a(this.s / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.R.nextFloat() * 3.1415927F * 2.0F;
                f *= this.R.nextFloat();
                entityitem.o += Math.cos((double) f1) * (double) f;
                entityitem.p += (double) ((this.R.nextFloat() - this.R.nextFloat()) * 0.1F);
                entityitem.q += Math.sin((double) f1) * (double) f;
            }

            this.a(entityitem);
        }
    }

    protected void a(EntityItem entityitem) {
        this.h.a((Entity) entityitem);
    }

    public float a(Block block) {
        float f = this.aj.a(block);

        if (this.a(Material.f)) {
            f /= 5.0F;
        }

        if (!this.w) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(Block block) {
        return this.aj.b(block);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Inventory");

        this.aj.b(nbttaglist);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.aj.a(new NBTTagList()));
    }

    public void a(IInventory iinventory) {}

    public void A() {}

    public void c(Entity entity, int i) {}

    protected float p() {
        return 0.12F;
    }

    public boolean a(Entity entity, int i) {
        this.bc = 0;
        if (this.aM <= 0) {
            return false;
        } else if ((float) this.X > (float) this.ar / 2.0F) {
            return false;
        } else {
            if (entity instanceof EntityMonster || entity instanceof EntityArrow) {
                if (this.h.l == 0) {
                    i = 0;
                }

                if (this.h.l == 1) {
                    i = i / 3 + 1;
                }

                if (this.h.l == 3) {
                    i = i * 3 / 2;
                }
            }

            int j = 25 - this.aj.e();
            int k = i * j + this.a;

            this.aj.b(i);
            i = k / 25;
            this.a = k % 25;
            return i == 0 ? false : super.a(entity, i);
        }
    }

    public void a(TileEntityFurnace tileentityfurnace) {}

    public void a(TileEntitySign tileentitysign) {}

    public ItemStack B() {
        return this.aj.b();
    }

    public void C() {
        this.aj.a(this.aj.d, (ItemStack) null);
    }

    public double x() {
        return (double) (this.C - 0.5F);
    }

    public void z() {
        this.ap = -1;
        this.ao = true;
    }
}
