package net.minecraft.server;

public class TileEntityFurnace extends TileEntity implements IInventory {

    private ItemStack[] h = new ItemStack[3];
    public int e = 0;
    public int f = 0;
    public int g = 0;

    public TileEntityFurnace() {}

    public int a() {
        return this.h.length;
    }

    public ItemStack a(int i) {
        return this.h[i];
    }

    public ItemStack a(int i, int j) {
        if (this.h[i] != null) {
            ItemStack itemstack;

            if (this.h[i].a <= j) {
                itemstack = this.h[i];
                this.h[i] = null;
                return itemstack;
            } else {
                itemstack = this.h[i].a(j);
                if (this.h[i].a == 0) {
                    this.h[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public void a(int i, ItemStack itemstack) {
        this.h[i] = itemstack;
        if (itemstack != null && itemstack.a > this.c()) {
            itemstack.a = this.c();
        }
    }

    public String b() {
        return "Furnace";
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Items");

        this.h = new ItemStack[this.a()];

        for (int i = 0; i < nbttaglist.b(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(i);
            byte b0 = nbttagcompound1.b("Slot");

            if (b0 >= 0 && b0 < this.h.length) {
                this.h[b0] = new ItemStack(nbttagcompound1);
            }
        }

        this.e = nbttagcompound.c("BurnTime");
        this.g = nbttagcompound.c("CookTime");
        this.f = this.a(this.h[1]);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("BurnTime", (short) this.e);
        nbttagcompound.a("CookTime", (short) this.g);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.h.length; ++i) {
            if (this.h[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i);
                this.h[i].a(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
    }

    public int c() {
        return 64;
    }

    public boolean g() {
        return this.e > 0;
    }

    public void e() {
        boolean flag = this.e > 0;
        boolean flag1 = false;

        if (this.e > 0) {
            --this.e;
        }

        if (!this.a.z) {
            if (this.e == 0 && this.i()) {
                this.f = this.e = this.a(this.h[1]);
                if (this.e > 0) {
                    flag1 = true;
                    if (this.h[1] != null) {
                        --this.h[1].a;
                        if (this.h[1].a == 0) {
                            this.h[1] = null;
                        }
                    }
                }
            }

            if (this.g() && this.i()) {
                ++this.g;
                if (this.g == 200) {
                    this.g = 0;
                    this.h();
                    flag1 = true;
                }
            } else {
                this.g = 0;
            }

            if (flag != this.e > 0) {
                flag1 = true;
                BlockFurnace.a(this.e > 0, this.a, this.b, this.c, this.d);
            }
        }

        if (flag1) {
            this.d();
        }
    }

    private boolean i() {
        if (this.h[0] == null) {
            return false;
        } else {
            int i = this.b(this.h[0].a().aW);

            return i < 0 ? false : (this.h[2] == null ? true : (this.h[2].c != i ? false : (this.h[2].a < this.c() && this.h[2].a < this.h[2].b() ? true : this.h[2].a < Item.c[i].b())));
        }
    }

    public void h() {
        if (this.i()) {
            int i = this.b(this.h[0].a().aW);

            if (this.h[2] == null) {
                this.h[2] = new ItemStack(i, 1);
            } else if (this.h[2].c == i) {
                ++this.h[2].a;
            }

            --this.h[0].a;
            if (this.h[0].a <= 0) {
                this.h[0] = null;
            }
        }
    }

    private int b(int i) {
        return i == Block.IRON_ORE.bh ? Item.IRON_INGOT.aW : (i == Block.GOLD_ORE.bh ? Item.GOLD_INGOT.aW : (i == Block.DIAMOND_ORE.bh ? Item.DIAMOND.aW : (i == Block.SAND.bh ? Block.GLASS.bh : (i == Item.PORK.aW ? Item.GRILLED_PORK.aW : (i == Item.RAW_FISH.aW ? Item.COOKED_FISH.aW : (i == Block.COBBLESTONE.bh ? Block.STONE.bh : (i == Item.CLAY_BALL.aW ? Item.CLAY_BRICK.aW : -1)))))));
    }

    private int a(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            int i = itemstack.a().aW;

            return i < 256 && Block.m[i].bs == Material.c ? 300 : (i == Item.STICK.aW ? 100 : (i == Item.COAL.aW ? 1600 : (i == Item.LAVA_BUCKET.aW ? 20000 : 0)));
        }
    }

    public boolean a_(EntityHuman entityhuman) {
        return this.a.l(this.b, this.c, this.d) != this ? false : entityhuman.d((double) this.b + 0.5D, (double) this.c + 0.5D, (double) this.d + 0.5D) <= 64.0D;
    }
}
