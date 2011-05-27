package net.minecraft.server;

import java.util.List;

public class EntityHuman extends EntityLiving {

    public InventoryPlayer ak = new InventoryPlayer(this);
    public byte al = 0;
    public int am = 0;
    public float an;
    public float ao;
    public boolean ap = false;
    public int aq = 0;
    public String ar;
    public int as;
    private int a = 0;
    public EntityFish at = null;

    public EntityHuman(World world) {
        super(world);
        this.G = 1.62F;
        this.c((double) world.m + 0.5D, (double) (world.n + 1), (double) world.o + 0.5D, 0.0F, 0.0F);
        this.aP = 20;
        this.aI = "humanoid";
        this.aH = 180.0F;
        this.X = 20;
        this.aF = "/mob/char.png";
    }

    public void y() {
        super.y();
        this.an = this.ao;
        this.ao = 0.0F;
    }

    protected void c() {
        if (this.ap) {
            ++this.aq;
            if (this.aq == 8) {
                this.aq = 0;
                this.ap = false;
            }
        } else {
            this.aq = 0;
        }

        this.aO = (float) this.aq / 8.0F;
    }

    public void D() {
        if (this.l.k == 0 && this.aP < 20 && this.W % 20 * 4 == 0) {
            this.a(1);
        }

        this.ak.c();
        this.an = this.ao;
        super.D();
        float f = MathHelper.a(this.s * this.s + this.u * this.u);
        float f1 = (float) Math.atan(-this.t * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.A || this.aP <= 0) {
            f = 0.0F;
        }

        if (this.A || this.aP <= 0) {
            f1 = 0.0F;
        }

        this.ao += (f - this.ao) * 0.4F;
        this.aX += (f1 - this.aX) * 0.8F;
        if (this.aP > 0) {
            List list = this.l.b((Entity) this, this.z.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    this.h((Entity) list.get(i));
                }
            }
        }
    }

    private void h(Entity entity) {
        entity.b(this);
    }

    public void f(Entity entity) {
        this.a(0.2F, 0.2F);
        this.a(this.p, this.q, this.r);
        this.t = 0.10000000149011612D;
        if (this.ar.equals("Notch")) {
            this.a(new ItemStack(Item.APPLE, 1), true);
        }

        this.ak.f();
        if (entity != null) {
            this.s = (double) (-MathHelper.b((this.aT + this.v) * 3.1415927F / 180.0F) * 0.1F);
            this.u = (double) (-MathHelper.a((this.aT + this.v) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.s = this.u = 0.0D;
        }

        this.G = 0.1F;
    }

    public void b(Entity entity, int i) {
        this.am += i;
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
                f1 = this.V.nextFloat() * 0.5F;
                float f2 = this.V.nextFloat() * 3.1415927F * 2.0F;

                entityitem.s = (double) (-MathHelper.a(f2) * f1);
                entityitem.u = (double) (MathHelper.b(f2) * f1);
                entityitem.t = 0.20000000298023224D;
            } else {
                f = 0.3F;
                entityitem.s = (double) (-MathHelper.a(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
                entityitem.u = (double) (MathHelper.b(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
                entityitem.t = (double) (-MathHelper.a(this.w / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.V.nextFloat() * 3.1415927F * 2.0F;
                f *= this.V.nextFloat();
                entityitem.s += Math.cos((double) f1) * (double) f;
                entityitem.t += (double) ((this.V.nextFloat() - this.V.nextFloat()) * 0.1F);
                entityitem.u += Math.sin((double) f1) * (double) f;
            }

            this.a(entityitem);
        }
    }

    protected void a(EntityItem entityitem) {
        this.l.a((Entity) entityitem);
    }

    public float a(Block block) {
        float f = this.ak.a(block);

        if (this.a(Material.f)) {
            f /= 5.0F;
        }

        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(Block block) {
        return this.ak.b(block);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Inventory");

        this.ak.b(nbttaglist);
        this.as = nbttagcompound.d("Dimension");
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.ak.a(new NBTTagList()));
        nbttagcompound.a("Dimension", this.as);
    }

    public void a(IInventory iinventory) {}

    public void F() {}

    public void c(Entity entity, int i) {}

    public float s() {
        return 0.12F;
    }

    public boolean a(Entity entity, int i) {
        this.bl = 0;
        if (this.aP <= 0) {
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
        int j = 25 - this.ak.e();
        int k = i * j + this.a;

        this.ak.c(i);
        i = k / 25;
        this.a = k % 25;
        super.c(i);
    }

    public void a(TileEntityFurnace tileentityfurnace) {}

    public void a(TileEntitySign tileentitysign) {}

    public ItemStack G() {
        return this.ak.b();
    }

    public void H() {
        this.ak.a(this.ak.d, (ItemStack) null);
    }

    public double A() {
        return (double) (this.G - 0.5F);
    }

    public void E() {
        this.aq = -1;
        this.ap = true;
    }
}
