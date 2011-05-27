package net.minecraft.server;

import java.util.List;

public abstract class EntityHuman extends EntityLiving {

    public InventoryPlayer an = new InventoryPlayer(this);
    public Container ao;
    public Container ap;
    public byte aq = 0;
    public int ar = 0;
    public float as;
    public float at;
    public boolean au = false;
    public int av = 0;
    public String aw;
    public int ax;
    public double ay;
    public double az;
    public double aA;
    public double aB;
    public double aC;
    public double aD;
    private int a = 0;
    public EntityFish aE = null;

    public EntityHuman(World world) {
        super(world);
        this.ao = new ContainerPlayer(this.an, !world.z);
        this.ap = this.ao;
        this.H = 1.62F;
        this.c((double) world.m + 0.5D, (double) (world.n + 1), (double) world.o + 0.5D, 0.0F, 0.0F);
        this.aZ = 20;
        this.aS = "humanoid";
        this.aR = 180.0F;
        this.Y = 20;
        this.aP = "/mob/char.png";
    }

    public void b_() {
        super.b_();
        if (!this.l.z && this.ap != null && !this.ap.b(this)) {
            this.L();
            this.ap = this.ao;
        }

        this.ay = this.aB;
        this.az = this.aC;
        this.aA = this.aD;
        double d0 = this.p - this.aB;
        double d1 = this.q - this.aC;
        double d2 = this.r - this.aD;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.ay = this.aB = this.p;
        }

        if (d2 > d3) {
            this.aA = this.aD = this.r;
        }

        if (d1 > d3) {
            this.az = this.aC = this.q;
        }

        if (d0 < -d3) {
            this.ay = this.aB = this.p;
        }

        if (d2 < -d3) {
            this.aA = this.aD = this.r;
        }

        if (d1 < -d3) {
            this.az = this.aC = this.q;
        }

        this.aB += d0 * 0.25D;
        this.aD += d2 * 0.25D;
        this.aC += d1 * 0.25D;
    }

    protected void L() {
        this.ap = this.ao;
    }

    public void D() {
        super.D();
        this.as = this.at;
        this.at = 0.0F;
    }

    protected void d() {
        if (this.au) {
            ++this.av;
            if (this.av == 8) {
                this.av = 0;
                this.au = false;
            }
        } else {
            this.av = 0;
        }

        this.aY = (float) this.av / 8.0F;
    }

    public void o() {
        if (this.l.k == 0 && this.aZ < 20 && this.X % 20 * 12 == 0) {
            this.d(1);
        }

        this.an.f();
        this.as = this.at;
        super.o();
        float f = MathHelper.a(this.s * this.s + this.u * this.u);
        float f1 = (float) Math.atan(-this.t * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.A || this.aZ <= 0) {
            f = 0.0F;
        }

        if (this.A || this.aZ <= 0) {
            f1 = 0.0F;
        }

        this.at += (f - this.at) * 0.4F;
        this.bh += (f1 - this.bh) * 0.8F;
        if (this.aZ > 0) {
            List list = this.l.b((Entity) this, this.z.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    Entity entity = (Entity) list.get(i);

                    if (!entity.G) {
                        this.j(entity);
                    }
                }
            }
        }
    }

    private void j(Entity entity) {
        entity.b(this);
    }

    public void f(Entity entity) {
        super.f(entity);
        this.a(0.2F, 0.2F);
        this.a(this.p, this.q, this.r);
        this.t = 0.10000000149011612D;
        if (this.aw.equals("Notch")) {
            this.a(new ItemStack(Item.APPLE, 1), true);
        }

        this.an.h();
        if (entity != null) {
            this.s = (double) (-MathHelper.b((this.bd + this.v) * 3.1415927F / 180.0F) * 0.1F);
            this.u = (double) (-MathHelper.a((this.bd + this.v) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.s = this.u = 0.0D;
        }

        this.H = 0.1F;
    }

    public void b(Entity entity, int i) {
        this.ar += i;
    }

    public void O() {
        this.a(this.an.b(this.an.c, 1), false);
    }

    public void b(ItemStack itemstack) {
        this.a(itemstack, false);
    }

    public void a(ItemStack itemstack, boolean flag) {
        if (itemstack != null) {
            EntityItem entityitem = new EntityItem(this.l, this.p, this.q - 0.30000001192092896D + (double) this.w(), this.r, itemstack);

            entityitem.c = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.W.nextFloat() * 0.5F;
                float f2 = this.W.nextFloat() * 3.1415927F * 2.0F;

                entityitem.s = (double) (-MathHelper.a(f2) * f1);
                entityitem.u = (double) (MathHelper.b(f2) * f1);
                entityitem.t = 0.20000000298023224D;
            } else {
                f = 0.3F;
                entityitem.s = (double) (-MathHelper.a(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
                entityitem.u = (double) (MathHelper.b(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
                entityitem.t = (double) (-MathHelper.a(this.w / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.W.nextFloat() * 3.1415927F * 2.0F;
                f *= this.W.nextFloat();
                entityitem.s += Math.cos((double) f1) * (double) f;
                entityitem.t += (double) ((this.W.nextFloat() - this.W.nextFloat()) * 0.1F);
                entityitem.u += Math.sin((double) f1) * (double) f;
            }

            this.a(entityitem);
        }
    }

    protected void a(EntityItem entityitem) {
        this.l.a((Entity) entityitem);
    }

    public float a(Block block) {
        float f = this.an.a(block);

        if (this.a(Material.f)) {
            f /= 5.0F;
        }

        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(Block block) {
        return this.an.b(block);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Inventory");

        this.an.b(nbttaglist);
        this.ax = nbttagcompound.d("Dimension");
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.an.a(new NBTTagList()));
        nbttagcompound.a("Dimension", this.ax);
    }

    public void a(IInventory iinventory) {}

    public void a(int i, int j, int k) {}

    public void c(Entity entity, int i) {}

    public float w() {
        return 0.12F;
    }

    public boolean a(Entity entity, int i) {
        this.bw = 0;
        if (this.aZ <= 0) {
            return false;
        } else {
            if (entity instanceof EntityMonster || entity instanceof EntityArrow) {
                if (this.l.k == 0) {
                    i = 0;
                }

                if (this.l.k == 1) {
                    i = i / 3 + 1;
                }

                if (this.l.k == 3) {
                    i = i * 3 / 2;
                }
            }

            return i == 0 ? false : super.a(entity, i);
        }
    }

    protected void e(int i) {
        int j = 25 - this.an.g();
        int k = i * j + this.a;

        this.an.c(i);
        i = k / 25;
        this.a = k % 25;
        super.e(i);
    }

    public void a(TileEntityFurnace tileentityfurnace) {}

    public void a(TileEntityDispenser tileentitydispenser) {}

    public void a(TileEntitySign tileentitysign) {}

    public void g(Entity entity) {
        if (!entity.a(this)) {
            ItemStack itemstack = this.P();

            if (itemstack != null && entity instanceof EntityLiving) {
                itemstack.b((EntityLiving) entity);
                if (itemstack.a <= 0) {
                    itemstack.a(this);
                    this.Q();
                }
            }
        }
    }

    public ItemStack P() {
        return this.an.e();
    }

    public void Q() {
        this.an.a(this.an.c, (ItemStack) null);
    }

    public double F() {
        return (double) (this.H - 0.5F);
    }

    public void K() {
        this.av = -1;
        this.au = true;
    }

    public void h(Entity entity) {
        int i = this.an.a(entity);

        if (i > 0) {
            entity.a(this, i);
            ItemStack itemstack = this.P();

            if (itemstack != null && entity instanceof EntityLiving) {
                itemstack.a((EntityLiving) entity);
                if (itemstack.a <= 0) {
                    itemstack.a(this);
                    this.Q();
                }
            }
        }
    }

    public void a(ItemStack itemstack) {}

    public void q() {
        super.q();
        this.ao.a(this);
        if (this.ap != null) {
            this.ap.a(this);
        }
    }
}
