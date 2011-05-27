package net.minecraft.server;

import java.util.List;

public class EntityHuman extends EntityLiving {

    public InventoryPlayer al = new InventoryPlayer(this);
    public byte am = 0;
    public int an = 0;
    public float ao;
    public float ap;
    public boolean aq = false;
    public int ar = 0;
    public String as;
    public int at;
    private int a = 0;
    public EntityFish au = null;

    public EntityHuman(World world) {
        super(world);
        this.H = 1.62F;
        this.c((double) world.m + 0.5D, (double) (world.n + 1), (double) world.o + 0.5D, 0.0F, 0.0F);
        this.aQ = 20;
        this.aJ = "humanoid";
        this.aI = 180.0F;
        this.Y = 20;
        this.aG = "/mob/char.png";
    }

    public void z() {
        super.z();
        this.ao = this.ap;
        this.ap = 0.0F;
    }

    protected void c() {
        if (this.aq) {
            ++this.ar;
            if (this.ar == 8) {
                this.ar = 0;
                this.aq = false;
            }
        } else {
            this.ar = 0;
        }

        this.aP = (float) this.ar / 8.0F;
    }

    public void E() {
        if (this.l.k == 0 && this.aQ < 20 && this.X % 20 * 4 == 0) {
            this.a(1);
        }

        this.al.c();
        this.ao = this.ap;
        super.E();
        float f = MathHelper.a(this.s * this.s + this.u * this.u);
        float f1 = (float) Math.atan(-this.t * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.A || this.aQ <= 0) {
            f = 0.0F;
        }

        if (this.A || this.aQ <= 0) {
            f1 = 0.0F;
        }

        this.ap += (f - this.ap) * 0.4F;
        this.aY += (f1 - this.aY) * 0.8F;
        if (this.aQ > 0) {
            List list = this.l.b((Entity) this, this.z.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    this.j((Entity) list.get(i));
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
        if (this.as.equals("Notch")) {
            this.a(new ItemStack(Item.APPLE, 1), true);
        }

        this.al.f();
        if (entity != null) {
            this.s = (double) (-MathHelper.b((this.aU + this.v) * 3.1415927F / 180.0F) * 0.1F);
            this.u = (double) (-MathHelper.a((this.aU + this.v) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.s = this.u = 0.0D;
        }

        this.H = 0.1F;
    }

    public void b(Entity entity, int i) {
        this.an += i;
    }

    public void a(ItemStack itemstack) {
        this.a(itemstack, false);
    }

    public void a(ItemStack itemstack, boolean flag) {
        if (itemstack != null) {
            EntityItem entityitem = new EntityItem(this.l, this.p, this.q - 0.30000001192092896D + (double) this.s(), this.r, itemstack);

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
        float f = this.al.a(block);

        if (this.a(Material.f)) {
            f /= 5.0F;
        }

        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(Block block) {
        return this.al.b(block);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Inventory");

        this.al.b(nbttaglist);
        this.at = nbttagcompound.d("Dimension");
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.al.a(new NBTTagList()));
        nbttagcompound.a("Dimension", this.at);
    }

    public void a(IInventory iinventory) {}

    public void G() {}

    public void c(Entity entity, int i) {}

    public float s() {
        return 0.12F;
    }

    public boolean a(Entity entity, int i) {
        this.bn = 0;
        if (this.aQ <= 0) {
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

    protected void c(int i) {
        int j = 25 - this.al.e();
        int k = i * j + this.a;

        this.al.c(i);
        i = k / 25;
        this.a = k % 25;
        super.c(i);
    }

    public void a(TileEntityFurnace tileentityfurnace) {}

    public void a(TileEntitySign tileentitysign) {}

    public void g(Entity entity) {
        entity.a(this);
    }

    public ItemStack H() {
        return this.al.b();
    }

    public void I() {
        this.al.a(this.al.d, (ItemStack) null);
    }

    public double B() {
        return (double) (this.H - 0.5F);
    }

    public void F() {
        this.ar = -1;
        this.aq = true;
    }

    public void h(Entity entity) {
        int i = this.al.a(entity);

        if (i > 0) {
            entity.a(this, i);
            ItemStack itemstack = this.H();

            if (itemstack != null && entity instanceof EntityLiving) {
                itemstack.a((EntityLiving) entity);
                if (itemstack.a <= 0) {
                    itemstack.a(this);
                    this.I();
                }
            }
        }
    }
}
