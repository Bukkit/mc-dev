package net.minecraft.server;

import java.util.List;

public class EntityMinecart extends Entity implements IInventory {

    private ItemStack[] ai;
    public int a;
    public int b;
    public int ad;
    private boolean aj;
    public int ae;
    public int af;
    public double ag;
    public double ah;
    private static final int[][][] ak = new int[][][] { { { 0, 0, -1}, { 0, 0, 1}}, { { -1, 0, 0}, { 1, 0, 0}}, { { -1, -1, 0}, { 1, 0, 0}}, { { -1, 0, 0}, { 1, -1, 0}}, { { 0, 0, -1}, { 0, -1, 1}}, { { 0, -1, -1}, { 0, 0, 1}}, { { 0, 0, 1}, { 1, 0, 0}}, { { 0, 0, 1}, { -1, 0, 0}}, { { 0, 0, -1}, { -1, 0, 0}}, { { 0, 0, -1}, { 1, 0, 0}}};
    private int al;
    private double am;
    private double an;
    private double ao;
    private double ap;
    private double aq;

    public EntityMinecart(World world) {
        super(world);
        this.ai = new ItemStack[36];
        this.a = 0;
        this.b = 0;
        this.ad = 1;
        this.aj = false;
        this.e = true;
        this.a(0.98F, 0.7F);
        this.C = this.E / 2.0F;
        this.H = false;
    }

    public AxisAlignedBB d(Entity entity) {
        return entity.v;
    }

    public AxisAlignedBB n() {
        return this.v;
    }

    public boolean r() {
        return true;
    }

    public EntityMinecart(World world, double d0, double d1, double d2, int i) {
        this(world);
        this.a(d0, d1 + (double) this.C, d2);
        this.o = 0.0D;
        this.p = 0.0D;
        this.q = 0.0D;
        this.i = d0;
        this.j = d1;
        this.k = d2;
        this.ae = i;
    }

    public double h() {
        return (double) this.E * 0.0D - 0.30000001192092896D;
    }

    public boolean a(Entity entity, int i) {
        this.ad = -this.ad;
        this.b = 10;
        this.a += i * 10;
        if (this.a > 40) {
            this.a(Item.MINECART.aS, 1, 0.0F);
            if (this.ae == 1) {
                this.a(Block.CHEST.bc, 1, 0.0F);
            } else if (this.ae == 2) {
                this.a(Block.FURNACE.bc, 1, 0.0F);
            }

            this.j();
        }

        return true;
    }

    public boolean c_() {
        return !this.B;
    }

    public void j() {
        for (int i = 0; i < this.a(); ++i) {
            ItemStack itemstack = this.a(i);

            if (itemstack != null) {
                float f = this.R.nextFloat() * 0.8F + 0.1F;
                float f1 = this.R.nextFloat() * 0.8F + 0.1F;
                float f2 = this.R.nextFloat() * 0.8F + 0.1F;

                while (itemstack.a > 0) {
                    int j = this.R.nextInt(21) + 10;

                    if (j > itemstack.a) {
                        j = itemstack.a;
                    }

                    itemstack.a -= j;
                    EntityItem entityitem = new EntityItem(this.h, this.l + (double) f, this.m + (double) f1, this.n + (double) f2, new ItemStack(itemstack.c, j, itemstack.d));
                    float f3 = 0.05F;

                    entityitem.o = (double) ((float) this.R.nextGaussian() * f3);
                    entityitem.p = (double) ((float) this.R.nextGaussian() * f3 + 0.2F);
                    entityitem.q = (double) ((float) this.R.nextGaussian() * f3);
                    this.h.a((Entity) entityitem);
                }
            }
        }

        super.j();
    }

    public void b_() {
        double d0;

        if (this.h.x) {
            if (this.al > 0) {
                double d1 = this.l + (this.am - this.l) / (double) this.al;
                double d2 = this.m + (this.an - this.m) / (double) this.al;
                double d3 = this.n + (this.ao - this.n) / (double) this.al;

                for (d0 = this.ap - (double) this.r; d0 < -180.0D; d0 += 360.0D) {
                    ;
                }

                while (d0 >= 180.0D) {
                    d0 -= 360.0D;
                }

                this.r = (float) ((double) this.r + d0 / (double) this.al);
                this.s = (float) ((double) this.s + (this.aq - (double) this.s) / (double) this.al);
                --this.al;
                this.a(d1, d2, d3);
                this.b(this.r, this.s);
            } else {
                this.a(this.l, this.m, this.n);
                this.b(this.r, this.s);
            }
        } else {
            if (this.b > 0) {
                --this.b;
            }

            if (this.a > 0) {
                --this.a;
            }

            this.i = this.l;
            this.j = this.m;
            this.k = this.n;
            this.p -= 0.03999999910593033D;
            int i = MathHelper.b(this.l);
            int j = MathHelper.b(this.m);
            int k = MathHelper.b(this.n);

            if (this.h.a(i, j - 1, k) == Block.RAILS.bc) {
                --j;
            }

            double d4 = 0.4D;
            boolean flag = false;

            d0 = 0.0078125D;
            if (this.h.a(i, j, k) == Block.RAILS.bc) {
                Vec3D vec3d = this.g(this.l, this.m, this.n);
                int l = this.h.b(i, j, k);

                this.m = (double) j;
                if (l >= 2 && l <= 5) {
                    this.m = (double) (j + 1);
                }

                if (l == 2) {
                    this.o -= d0;
                }

                if (l == 3) {
                    this.o += d0;
                }

                if (l == 4) {
                    this.q += d0;
                }

                if (l == 5) {
                    this.q -= d0;
                }

                int[][] aint = ak[l];
                double d5 = (double) (aint[1][0] - aint[0][0]);
                double d6 = (double) (aint[1][2] - aint[0][2]);
                double d7 = Math.sqrt(d5 * d5 + d6 * d6);
                double d8 = this.o * d5 + this.q * d6;

                if (d8 < 0.0D) {
                    d5 = -d5;
                    d6 = -d6;
                }

                double d9 = Math.sqrt(this.o * this.o + this.q * this.q);

                this.o = d9 * d5 / d7;
                this.q = d9 * d6 / d7;
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
                    this.l = (double) i + 0.5D;
                    d10 = this.n - (double) k;
                } else if (d6 == 0.0D) {
                    this.n = (double) k + 0.5D;
                    d10 = this.l - (double) i;
                } else {
                    d15 = this.l - d11;
                    d17 = this.n - d12;
                    d16 = (d15 * d5 + d17 * d6) * 2.0D;
                    d10 = d16;
                }

                this.l = d11 + d5 * d10;
                this.n = d12 + d6 * d10;
                this.a(this.l, this.m + (double) this.C, this.n);
                d15 = this.o;
                d17 = this.q;
                if (this.f != null) {
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
                if (aint[0][1] != 0 && MathHelper.b(this.l) - i == aint[0][0] && MathHelper.b(this.n) - k == aint[0][2]) {
                    this.a(this.l, this.m + (double) aint[0][1], this.n);
                } else if (aint[1][1] != 0 && MathHelper.b(this.l) - i == aint[1][0] && MathHelper.b(this.n) - k == aint[1][2]) {
                    this.a(this.l, this.m + (double) aint[1][1], this.n);
                }

                if (this.f != null) {
                    this.o *= 0.996999979019165D;
                    this.p *= 0.0D;
                    this.q *= 0.996999979019165D;
                } else {
                    if (this.ae == 2) {
                        d16 = (double) MathHelper.a(this.ag * this.ag + this.ah * this.ah);
                        if (d16 > 0.01D) {
                            flag = true;
                            this.ag /= d16;
                            this.ah /= d16;
                            double d18 = 0.04D;

                            this.o *= 0.800000011920929D;
                            this.p *= 0.0D;
                            this.q *= 0.800000011920929D;
                            this.o += this.ag * d18;
                            this.q += this.ah * d18;
                        } else {
                            this.o *= 0.8999999761581421D;
                            this.p *= 0.0D;
                            this.q *= 0.8999999761581421D;
                        }
                    }

                    this.o *= 0.9599999785423279D;
                    this.p *= 0.0D;
                    this.q *= 0.9599999785423279D;
                }

                Vec3D vec3d1 = this.g(this.l, this.m, this.n);

                if (vec3d1 != null && vec3d != null) {
                    double d19 = (vec3d.b - vec3d1.b) * 0.05D;

                    d9 = Math.sqrt(this.o * this.o + this.q * this.q);
                    if (d9 > 0.0D) {
                        this.o = this.o / d9 * (d9 + d19);
                        this.q = this.q / d9 * (d9 + d19);
                    }

                    this.a(this.l, vec3d1.b, this.n);
                }

                int i1 = MathHelper.b(this.l);
                int j1 = MathHelper.b(this.n);

                if (i1 != i || j1 != k) {
                    d9 = Math.sqrt(this.o * this.o + this.q * this.q);
                    this.o = d9 * (double) (i1 - i);
                    this.q = d9 * (double) (j1 - k);
                }

                if (this.ae == 2) {
                    double d20 = (double) MathHelper.a(this.ag * this.ag + this.ah * this.ah);

                    if (d20 > 0.01D && this.o * this.o + this.q * this.q > 0.0010D) {
                        this.ag /= d20;
                        this.ah /= d20;
                        if (this.ag * this.o + this.ah * this.q < 0.0D) {
                            this.ag = 0.0D;
                            this.ah = 0.0D;
                        } else {
                            this.ag = this.o;
                            this.ah = this.q;
                        }
                    }
                }
            } else {
                if (this.o < -d4) {
                    this.o = -d4;
                }

                if (this.o > d4) {
                    this.o = d4;
                }

                if (this.q < -d4) {
                    this.q = -d4;
                }

                if (this.q > d4) {
                    this.q = d4;
                }

                if (this.w) {
                    this.o *= 0.5D;
                    this.p *= 0.5D;
                    this.q *= 0.5D;
                }

                this.c(this.o, this.p, this.q);
                if (!this.w) {
                    this.o *= 0.949999988079071D;
                    this.p *= 0.949999988079071D;
                    this.q *= 0.949999988079071D;
                }
            }

            this.s = 0.0F;
            double d21 = this.i - this.l;
            double d22 = this.k - this.n;

            if (d21 * d21 + d22 * d22 > 0.0010D) {
                this.r = (float) (Math.atan2(d22, d21) * 180.0D / 3.141592653589793D);
                if (this.aj) {
                    this.r += 180.0F;
                }
            }

            double d23;

            for (d23 = (double) (this.r - this.t); d23 >= 180.0D; d23 -= 360.0D) {
                ;
            }

            while (d23 < -180.0D) {
                d23 += 360.0D;
            }

            if (d23 < -170.0D || d23 >= 170.0D) {
                this.r += 180.0F;
                this.aj = !this.aj;
            }

            this.b(this.r, this.s);
            List list = this.h.b((Entity) this, this.v.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && list.size() > 0) {
                for (int k1 = 0; k1 < list.size(); ++k1) {
                    Entity entity = (Entity) list.get(k1);

                    if (entity != this.f && entity.r() && entity instanceof EntityMinecart) {
                        entity.c((Entity) this);
                    }
                }
            }

            if (this.f != null && this.f.B) {
                this.f = null;
            }

            if (flag && this.R.nextInt(4) == 0) {
                --this.af;
                if (this.af < 0) {
                    this.ag = this.ah = 0.0D;
                }

                this.h.a("largesmoke", this.l, this.m + 0.8D, this.n, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public Vec3D g(double d0, double d1, double d2) {
        int i = MathHelper.b(d0);
        int j = MathHelper.b(d1);
        int k = MathHelper.b(d2);

        if (this.h.a(i, j - 1, k) == Block.RAILS.bc) {
            --j;
        }

        if (this.h.a(i, j, k) == Block.RAILS.bc) {
            int l = this.h.b(i, j, k);

            d1 = (double) j;
            if (l >= 2 && l <= 5) {
                d1 = (double) (j + 1);
            }

            int[][] aint = ak[l];
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
        nbttagcompound.a("Type", this.ae);
        if (this.ae == 2) {
            nbttagcompound.a("PushX", this.ag);
            nbttagcompound.a("PushZ", this.ah);
            nbttagcompound.a("Fuel", (short) this.af);
        } else if (this.ae == 1) {
            NBTTagList nbttaglist = new NBTTagList();

            for (int i = 0; i < this.ai.length; ++i) {
                if (this.ai[i] != null) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                    nbttagcompound1.a("Slot", (byte) i);
                    this.ai[i].a(nbttagcompound1);
                    nbttaglist.a((NBTBase) nbttagcompound1);
                }
            }

            nbttagcompound.a("Items", (NBTBase) nbttaglist);
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.ae = nbttagcompound.d("Type");
        if (this.ae == 2) {
            this.ag = nbttagcompound.g("PushX");
            this.ah = nbttagcompound.g("PushZ");
            this.af = nbttagcompound.c("Fuel");
        } else if (this.ae == 1) {
            NBTTagList nbttaglist = nbttagcompound.k("Items");

            this.ai = new ItemStack[this.a()];

            for (int i = 0; i < nbttaglist.b(); ++i) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(i);
                int j = nbttagcompound1.b("Slot") & 255;

                if (j >= 0 && j < this.ai.length) {
                    this.ai[j] = new ItemStack(nbttagcompound1);
                }
            }
        }
    }

    public void c(Entity entity) {
        if (entity != this.f) {
            if (entity instanceof EntityLiving && !(entity instanceof EntityHuman) && this.ae == 0 && this.o * this.o + this.q * this.q > 0.01D && this.f == null && entity.g == null) {
                entity.e((Entity) this);
            }

            double d0 = entity.l - this.l;
            double d1 = entity.n - this.n;
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
                d0 *= (double) (1.0F - this.P);
                d1 *= (double) (1.0F - this.P);
                d0 *= 0.5D;
                d1 *= 0.5D;
                if (entity instanceof EntityMinecart) {
                    double d4 = entity.o + this.o;
                    double d5 = entity.q + this.q;

                    if (((EntityMinecart) entity).ae == 2 && this.ae != 2) {
                        this.o *= 0.20000000298023224D;
                        this.q *= 0.20000000298023224D;
                        this.f(entity.o - d0, 0.0D, entity.q - d1);
                        entity.o *= 0.699999988079071D;
                        entity.q *= 0.699999988079071D;
                    } else if (((EntityMinecart) entity).ae != 2 && this.ae == 2) {
                        entity.o *= 0.20000000298023224D;
                        entity.q *= 0.20000000298023224D;
                        entity.f(this.o + d0, 0.0D, this.q + d1);
                        this.o *= 0.699999988079071D;
                        this.q *= 0.699999988079071D;
                    } else {
                        d4 /= 2.0D;
                        d5 /= 2.0D;
                        this.o *= 0.20000000298023224D;
                        this.q *= 0.20000000298023224D;
                        this.f(d4 - d0, 0.0D, d5 - d1);
                        entity.o *= 0.20000000298023224D;
                        entity.q *= 0.20000000298023224D;
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
        return this.ai[i];
    }
}
