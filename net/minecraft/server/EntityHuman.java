package net.minecraft.server;

import java.util.List;

public class EntityHuman extends EntityLiving {

    public InventoryPlayer am = new InventoryPlayer(this);
    public byte an = 0;
    public int ao = 0;
    public float ap;
    public float aq;
    public boolean ar = false;
    public int as = 0;
    public String at;
    public int au;
    private int a = 0;
    public EntityFish av = null;

    public EntityHuman(World world) {
        super(world);
        this.H = 1.62F;
        this.c((double) world.m + 0.5D, (double) (world.n + 1), (double) world.o + 0.5D, 0.0F, 0.0F);
        this.aR = 20;
        this.aK = "humanoid";
        this.aJ = 180.0F;
        this.Y = 20;
        this.aH = "/mob/char.png";
    }

    public void z() {
        super.z();
        this.ap = this.aq;
        this.aq = 0.0F;
    }

    protected void c() {
        if (this.ar) {
            ++this.as;
            if (this.as == 8) {
                this.as = 0;
                this.ar = false;
            }
        } else {
            this.as = 0;
        }

        this.aQ = (float) this.as / 8.0F;
    }

    public void E() {
        if (this.l.k == 0 && this.aR < 20 && this.X % 20 * 4 == 0) {
            this.a(1);
        }

        this.am.c();
        this.ap = this.aq;
        super.E();
        float f = MathHelper.a(this.s * this.s + this.u * this.u);
        float f1 = (float) Math.atan(-this.t * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.A || this.aR <= 0) {
            f = 0.0F;
        }

        if (this.A || this.aR <= 0) {
            f1 = 0.0F;
        }

        this.aq += (f - this.aq) * 0.4F;
        this.aZ += (f1 - this.aZ) * 0.8F;
        if (this.aR > 0) {
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
        if (this.at.equals("Notch")) {
            this.a(new ItemStack(Item.APPLE, 1), true);
        }

        this.am.f();
        if (entity != null) {
            this.s = (double) (-MathHelper.b((this.aV + this.v) * 3.1415927F / 180.0F) * 0.1F);
            this.u = (double) (-MathHelper.a((this.aV + this.v) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.s = this.u = 0.0D;
        }

        this.H = 0.1F;
    }

    public void b(Entity entity, int i) {
        this.ao += i;
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
        float f = this.am.a(block);

        if (this.a(Material.f)) {
            f /= 5.0F;
        }

        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(Block block) {
        return this.am.b(block);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Inventory");

        this.am.b(nbttaglist);
        this.au = nbttagcompound.d("Dimension");
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.am.a(new NBTTagList()));
        nbttagcompound.a("Dimension", this.au);
    }

    public void a(IInventory iinventory) {}

    public void G() {}

    public void c(Entity entity, int i) {}

    public float s() {
        return 0.12F;
    }

    public boolean a(Entity entity, int i) {
        this.bo = 0;
        if (this.aR <= 0) {
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
        int j = 25 - this.am.e();
        int k = i * j + this.a;

        this.am.c(i);
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
        return this.am.b();
    }

    public void I() {
        this.am.a(this.am.d, (ItemStack) null);
    }

    public double B() {
        return (double) (this.H - 0.5F);
    }

    public void F() {
        this.as = -1;
        this.ar = true;
    }

    public void h(Entity entity) {
        int i = this.am.a(entity);

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
