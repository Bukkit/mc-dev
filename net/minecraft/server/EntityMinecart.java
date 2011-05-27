package net.minecraft.server;

import java.util.List;

public class EntityMinecart extends Entity implements IInventory {

    private ItemStack[] aj;
    public int a;
    public int b;
    public int c;
    private boolean ak;
    public int d;
    public int e;
    public double f;
    public double ai;
    private static final int[][][] al = new int[][][] { { { 0, 0, -1}, { 0, 0, 1}}, { { -1, 0, 0}, { 1, 0, 0}}, { { -1, -1, 0}, { 1, 0, 0}}, { { -1, 0, 0}, { 1, -1, 0}}, { { 0, 0, -1}, { 0, -1, 1}}, { { 0, -1, -1}, { 0, 0, 1}}, { { 0, 0, 1}, { 1, 0, 0}}, { { 0, 0, 1}, { -1, 0, 0}}, { { 0, 0, -1}, { -1, 0, 0}}, { { 0, 0, -1}, { 1, 0, 0}}};
    private int am;
    private double an;
    private double ao;
    private double ap;
    private double aq;
    private double ar;

    public EntityMinecart(World world) {
        super(world);
        this.aj = new ItemStack[36];
        this.a = 0;
        this.b = 0;
        this.c = 1;
        this.ak = false;
        this.i = true;
        this.a(0.98F, 0.7F);
        this.G = this.I / 2.0F;
        this.L = false;
    }

    public AxisAlignedBB d(Entity entity) {
        return entity.z;
    }

    public AxisAlignedBB q() {
        return this.z;
    }

    public boolean u() {
        return true;
    }

    public EntityMinecart(World world, double d0, double d1, double d2, int i) {
        this(world);
        this.a(d0, d1 + (double) this.G, d2);
        this.s = 0.0D;
        this.t = 0.0D;
        this.u = 0.0D;
        this.m = d0;
        this.n = d1;
        this.o = d2;
        this.d = i;
    }

    public double j() {
        return (double) this.I * 0.0D - 0.30000001192092896D;
    }

    public boolean a(Entity entity, int i) {
        this.c = -this.c;
        this.b = 10;
        this.a += i * 10;
        if (this.a > 40) {
            this.a(Item.MINECART.aW, 1, 0.0F);
            if (this.d == 1) {
                this.a(Block.CHEST.bi, 1, 0.0F);
            } else if (this.d == 2) {
                this.a(Block.FURNACE.bi, 1, 0.0F);
            }

            this.l();
        }

        return true;
    }

    public boolean c_() {
        return !this.F;
    }

    public void l() {
        for (int i = 0; i < this.a(); ++i) {
            ItemStack itemstack = this.a(i);

            if (itemstack != null) {
                float f = this.V.nextFloat() * 0.8F + 0.1F;
                float f1 = this.V.nextFloat() * 0.8F + 0.1F;
                float f2 = this.V.nextFloat() * 0.8F + 0.1F;

                while (itemstack.a > 0) {
                    int j = this.V.nextInt(21) + 10;

                    if (j > itemstack.a) {
                        j = itemstack.a;
                    }

                    itemstack.a -= j;
                    EntityItem entityitem = new EntityItem(this.l, this.p + (double) f, this.q + (double) f1, this.r + (double) f2, new ItemStack(itemstack.c, j, itemstack.d));
                    float f3 = 0.05F;

                    entityitem.s = (double) ((float) this.V.nextGaussian() * f3);
                    entityitem.t = (double) ((float) this.V.nextGaussian() * f3 + 0.2F);
                    entityitem.u = (double) ((float) this.V.nextGaussian() * f3);
                    this.l.a((Entity) entityitem);
                }
            }
        }

        super.l();
    }

    public void b_() {
        double d0;

        if (this.l.z) {
            if (this.am > 0) {
                double d1 = this.p + (this.an - this.p) / (double) this.am;
                double d2 = this.q + (this.ao - this.q) / (double) this.am;
                double d3 = this.r + (this.ap - this.r) / (double) this.am;

                for (d0 = this.aq - (double) this.v; d0 < -180.0D; d0 += 360.0D) {
                    ;
                }

                while (d0 >= 180.0D) {
                    d0 -= 360.0D;
                }

                this.v = (float) ((double) this.v + d0 / (double) this.am);
                this.w = (float) ((double) this.w + (this.ar - (double) this.w) / (double) this.am);
                --this.am;
                this.a(d1, d2, d3);
                this.b(this.v, this.w);
            } else {
                this.a(this.p, this.q, this.r);
                this.b(this.v, this.w);
            }
        } else {
            if (this.b > 0) {
                --this.b;
            }

            if (this.a > 0) {
                --this.a;
            }

            this.m = this.p;
            this.n = this.q;
            this.o = this.r;
            this.t -= 0.03999999910593033D;
            int i = MathHelper.b(this.p);
            int j = MathHelper.b(this.q);
            int k = MathHelper.b(this.r);

            if (this.l.a(i, j - 1, k) == Block.RAILS.bi) {
                --j;
            }

            double d4 = 0.4D;
            boolean flag = false;

            d0 = 0.0078125D;
            if (this.l.a(i, j, k) == Block.RAILS.bi) {
                Vec3D vec3d = this.g(this.p, this.q, this.r);
                int l = this.l.b(i, j, k);

                this.q = (double) j;
                if (l >= 2 && l <= 5) {
                    this.q = (double) (j + 1);
                }

                if (l == 2) {
                    this.s -= d0;
                }

                if (l == 3) {
                    this.s += d0;
                }

                if (l == 4) {
                    this.u += d0;
                }

                if (l == 5) {
                    this.u -= d0;
                }

                int[][] aint = al[l];
                double d5 = (double) (aint[1][0] - aint[0][0]);
                double d6 = (double) (aint[1][2] - aint[0][2]);
                double d7 = Math.sqrt(d5 * d5 + d6 * d6);
                double d8 = this.s * d5 + this.u * d6;

                if (d8 < 0.0D) {
                    d5 = -d5;
                    d6 = -d6;
                }

                double d9 = Math.sqrt(this.s * this.s + this.u * this.u);

                this.s = d9 * d5 / d7;
                this.u = d9 * d6 / d7;
                double d10 = 0.0D;
                double d11 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
                double d12 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
                double d13 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
                double d14 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;

                d5 = d13 - d11;
                d6 = d14 - d12;
                double d15;
                double d16;
                double d17;

                if (d5 == 0.0D) {
                    this.p = (double) i + 0.5D;
                    d10 = this.r - (double) k;
                } else if (d6 == 0.0D) {
                    this.r = (double) k + 0.5D;
                    d10 = this.p - (double) i;
                } else {
                    d15 = this.p - d11;
                    d17 = this.r - d12;
                    d16 = (d15 * d5 + d17 * d6) * 2.0D;
                    d10 = d16;
                }

                this.p = d11 + d5 * d10;
                this.r = d12 + d6 * d10;
                this.a(this.p, this.q + (double) this.G, this.r);
                d15 = this.s;
                d17 = this.u;
                if (this.j != null) {
                    d15 *= 0.75D;
                    d17 *= 0.75D;
                }

                if (d15 < -d4) {
                    d15 = -d4;
                }

                if (d15 > d4) {
                    d15 = d4;
                }

                if (d17 < -d4) {
                    d17 = -d4;
                }

                if (d17 > d4) {
                    d17 = d4;
                }

                this.c(d15, 0.0D, d17);
                if (aint[0][1] != 0 && MathHelper.b(this.p) - i == aint[0][0] && MathHelper.b(this.r) - k == aint[0][2]) {
                    this.a(this.p, this.q + (double) aint[0][1], this.r);
                } else if (aint[1][1] != 0 && MathHelper.b(this.p) - i == aint[1][0] && MathHelper.b(this.r) - k == aint[1][2]) {
                    this.a(this.p, this.q + (double) aint[1][1], this.r);
                }

                if (this.j != null) {
                    this.s *= 0.996999979019165D;
                    this.t *= 0.0D;
                    this.u *= 0.996999979019165D;
                } else {
                    if (this.d == 2) {
                        d16 = (double) MathHelper.a(this.f * this.f + this.ai * this.ai);
                        if (d16 > 0.01D) {
                            flag = true;
                            this.f /= d16;
                            this.ai /= d16;
                            double d18 = 0.04D;

                            this.s *= 0.800000011920929D;
                            this.t *= 0.0D;
                            this.u *= 0.800000011920929D;
                            this.s += this.f * d18;
                            this.u += this.ai * d18;
                        } else {
                            this.s *= 0.8999999761581421D;
                            this.t *= 0.0D;
                            this.u *= 0.8999999761581421D;
                        }
                    }

                    this.s *= 0.9599999785423279D;
                    this.t *= 0.0D;
                    this.u *= 0.9599999785423279D;
                }

                Vec3D vec3d1 = this.g(this.p, this.q, this.r);

                if (vec3d1 != null && vec3d != null) {
                    double d19 = (vec3d.b - vec3d1.b) * 0.05D;

                    d9 = Math.sqrt(this.s * this.s + this.u * this.u);
                    if (d9 > 0.0D) {
                        this.s = this.s / d9 * (d9 + d19);
                        this.u = this.u / d9 * (d9 + d19);
                    }

                    this.a(this.p, vec3d1.b, this.r);
                }

                int i1 = MathHelper.b(this.p);
                int j1 = MathHelper.b(this.r);

                if (i1 != i || j1 != k) {
                    d9 = Math.sqrt(this.s * this.s + this.u * this.u);
                    this.s = d9 * (double) (i1 - i);
                    this.u = d9 * (double) (j1 - k);
                }

                if (this.d == 2) {
                    double d20 = (double) MathHelper.a(this.f * this.f + this.ai * this.ai);

                    if (d20 > 0.01D && this.s * this.s + this.u * this.u > 0.0010D) {
                        this.f /= d20;
                        this.ai /= d20;
                        if (this.f * this.s + this.ai * this.u < 0.0D) {
                            this.f = 0.0D;
                            this.ai = 0.0D;
                        } else {
                            this.f = this.s;
                            this.ai = this.u;
                        }
                    }
                }
            } else {
                if (this.s < -d4) {
                    this.s = -d4;
                }

                if (this.s > d4) {
                    this.s = d4;
                }

                if (this.u < -d4) {
                    this.u = -d4;
                }

                if (this.u > d4) {
                    this.u = d4;
                }

                if (this.A) {
                    this.s *= 0.5D;
                    this.t *= 0.5D;
                    this.u *= 0.5D;
                }

                this.c(this.s, this.t, this.u);
                if (!this.A) {
                    this.s *= 0.949999988079071D;
                    this.t *= 0.949999988079071D;
                    this.u *= 0.949999988079071D;
                }
            }

            this.w = 0.0F;
            double d21 = this.m - this.p;
            double d22 = this.o - this.r;

            if (d21 * d21 + d22 * d22 > 0.0010D) {
                this.v = (float) (Math.atan2(d22, d21) * 180.0D / 3.141592653589793D);
                if (this.ak) {
                    this.v += 180.0F;
                }
            }

            double d23;

            for (d23 = (double) (this.v - this.x); d23 >= 180.0D; d23 -= 360.0D) {
                ;
            }

            while (d23 < -180.0D) {
                d23 += 360.0D;
            }

            if (d23 < -170.0D || d23 >= 170.0D) {
                this.v += 180.0F;
                this.ak = !this.ak;
            }

            this.b(this.v, this.w);
            List list = this.l.b((Entity) this, this.z.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && list.size() > 0) {
                for (int k1 = 0; k1 < list.size(); ++k1) {
                    Entity entity = (Entity) list.get(k1);

                    if (entity != this.j && entity.u() && entity instanceof EntityMinecart) {
                        entity.c((Entity) this);
                    }
                }
            }

            if (this.j != null && this.j.F) {
                this.j = null;
            }

            if (flag && this.V.nextInt(4) == 0) {
                --this.e;
                if (this.e < 0) {
                    this.f = this.ai = 0.0D;
                }

                this.l.a("largesmoke", this.p, this.q + 0.8D, this.r, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public Vec3D g(double d0, double d1, double d2) {
        int i = MathHelper.b(d0);
        int j = MathHelper.b(d1);
        int k = MathHelper.b(d2);

        if (this.l.a(i, j - 1, k) == Block.RAILS.bi) {
            --j;
        }

        if (this.l.a(i, j, k) == Block.RAILS.bi) {
            int l = this.l.b(i, j, k);

            d1 = (double) j;
            if (l >= 2 && l <= 5) {
                d1 = (double) (j + 1);
            }

            int[][] aint = al[l];
            double d3 = 0.0D;
            double d4 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
            double d5 = (double) j + 0.5D + (double) aint[0][1] * 0.5D;
            double d6 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
            double d7 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
            double d8 = (double) j + 0.5D + (double) aint[1][1] * 0.5D;
            double d9 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2.0D;
            double d12 = d9 - d6;

            if (d10 == 0.0D) {
                d0 = (double) i + 0.5D;
                d3 = d2 - (double) k;
            } else if (d12 == 0.0D) {
                d2 = (double) k + 0.5D;
                d3 = d0 - (double) i;
            } else {
                double d13 = d0 - d4;
                double d14 = d2 - d6;
                double d15 = (d13 * d10 + d14 * d12) * 2.0D;

                d3 = d15;
            }

            d0 = d4 + d10 * d3;
            d1 = d5 + d11 * d3;
            d2 = d6 + d12 * d3;
            if (d11 < 0.0D) {
                ++d1;
            }

            if (d11 > 0.0D) {
                d1 += 0.5D;
            }

            return Vec3D.b(d0, d1, d2);
        } else {
            return null;
        }
    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Type", this.d);
        if (this.d == 2) {
            nbttagcompound.a("PushX", this.f);
            nbttagcompound.a("PushZ", this.ai);
            nbttagcompound.a("Fuel", (short) this.e);
        } else if (this.d == 1) {
            NBTTagList nbttaglist = new NBTTagList();

            for (int i = 0; i < this.aj.length; ++i) {
                if (this.aj[i] != null) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                    nbttagcompound1.a("Slot", (byte) i);
                    this.aj[i].a(nbttagcompound1);
                    nbttaglist.a((NBTBase) nbttagcompound1);
                }
            }

            nbttagcompound.a("Items", (NBTBase) nbttaglist);
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.d("Type");
        if (this.d == 2) {
            this.f = nbttagcompound.g("PushX");
            this.ai = nbttagcompound.g("PushZ");
            this.e = nbttagcompound.c("Fuel");
        } else if (this.d == 1) {
            NBTTagList nbttaglist = nbttagcompound.k("Items");

            this.aj = new ItemStack[this.a()];

            for (int i = 0; i < nbttaglist.b(); ++i) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(i);
                int j = nbttagcompound1.b("Slot") & 255;

                if (j >= 0 && j < this.aj.length) {
                    this.aj[j] = new ItemStack(nbttagcompound1);
                }
            }
        }
    }

    public void c(Entity entity) {
        if (entity != this.j) {
            if (entity instanceof EntityLiving && !(entity instanceof EntityHuman) && this.d == 0 && this.s * this.s + this.u * this.u > 0.01D && this.j == null && entity.k == null) {
                entity.e((Entity) this);
            }

            double d0 = entity.p - this.p;
            double d1 = entity.r - this.r;
            double d2 = d0 * d0 + d1 * d1;

            if (d2 >= 9.999999747378752E-5D) {
                d2 = (double) MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.10000000149011612D;
                d1 *= 0.10000000149011612D;
                d0 *= (double) (1.0F - this.T);
                d1 *= (double) (1.0F - this.T);
                d0 *= 0.5D;
                d1 *= 0.5D;
                if (entity instanceof EntityMinecart) {
                    double d4 = entity.s + this.s;
                    double d5 = entity.u + this.u;

                    if (((EntityMinecart) entity).d == 2 && this.d != 2) {
                        this.s *= 0.20000000298023224D;
                        this.u *= 0.20000000298023224D;
                        this.f(entity.s - d0, 0.0D, entity.u - d1);
                        entity.s *= 0.699999988079071D;
                        entity.u *= 0.699999988079071D;
                    } else if (((EntityMinecart) entity).d != 2 && this.d == 2) {
                        entity.s *= 0.20000000298023224D;
                        entity.u *= 0.20000000298023224D;
                        entity.f(this.s + d0, 0.0D, this.u + d1);
                        this.s *= 0.699999988079071D;
                        this.u *= 0.699999988079071D;
                    } else {
                        d4 /= 2.0D;
                        d5 /= 2.0D;
                        this.s *= 0.20000000298023224D;
                        this.u *= 0.20000000298023224D;
                        this.f(d4 - d0, 0.0D, d5 - d1);
                        entity.s *= 0.20000000298023224D;
                        entity.u *= 0.20000000298023224D;
                        entity.f(d4 + d0, 0.0D, d5 + d1);
                    }
                } else {
                    this.f(-d0, 0.0D, -d1);
                    entity.f(d0 / 4.0D, 0.0D, d1 / 4.0D);
                }
            }
        }
    }

    public int a() {
        return 27;
    }

    public ItemStack a(int i) {
        return this.aj[i];
    }
}
