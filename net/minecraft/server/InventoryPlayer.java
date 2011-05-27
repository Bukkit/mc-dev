package net.minecraft.server;

public class InventoryPlayer implements IInventory {

    public ItemStack[] a = new ItemStack[37];
    public ItemStack[] b = new ItemStack[4];
    public ItemStack[] c = new ItemStack[4];
    public int d = 0;
    private EntityHuman f;
    public boolean e = false;

    public InventoryPlayer(EntityHuman entityhuman) {
        this.f = entityhuman;
    }

    public ItemStack b() {
        return this.a[this.d];
    }

    private int c(int i) {
        for (int j = 0; j < this.a.length; ++j) {
            if (this.a[j] != null && this.a[j].c == i && this.a[j].a < this.a[j].b() && this.a[j].a < this.d()) {
                return j;
            }
        }

        return -1;
    }

    private int g() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] == null) {
                return i;
            }
        }

        return -1;
    }

    private int a(int i, int j) {
        int k = this.c(i);

        if (k < 0) {
            k = this.g();
        }

        if (k < 0) {
            return j;
        } else {
            if (this.a[k] == null) {
                this.a[k] = new ItemStack(i, 0);
            }

            int l = j;

            if (j > this.a[k].b() - this.a[k].a) {
                l = this.a[k].b() - this.a[k].a;
            }

            if (l > this.d() - this.a[k].a) {
                l = this.d() - this.a[k].a;
            }

            if (l == 0) {
                return j;
            } else {
                j -= l;
                this.a[k].a += l;
                this.a[k].b = 5;
                return j;
            }
        }
    }

    public void c() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].b > 0) {
                --this.a[i].b;
            }
        }
    }

    public boolean a(ItemStack itemstack) {
        if (itemstack.d == 0) {
            itemstack.a = this.a(itemstack.c, itemstack.a);
            if (itemstack.a == 0) {
                return true;
            }
        }

        int i = this.g();

        if (i >= 0) {
            this.a[i] = itemstack;
            this.a[i].b = 5;
            return true;
        } else {
            return false;
        }
    }

    public void a(int i, ItemStack itemstack) {
        ItemStack[] aitemstack = this.a;

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.b;
        }

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.c;
        }

        aitemstack[i] = itemstack;
    }

    public float a(Block block) {
        float f = 1.0F;

        if (this.a[this.d] != null) {
            f *= this.a[this.d].a(block);
        }

        return f;
    }

    public NBTTagList a(NBTTagList nbttaglist) {
        int i;
        NBTTagCompound nbttagcompound;

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) i);
                this.a[i].a(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) (i + 100));
                this.b[i].a(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        for (i = 0; i < this.c.length; ++i) {
            if (this.c[i] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) (i + 80));
                this.c[i].a(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        return nbttaglist;
    }

    public void b(NBTTagList nbttaglist) {
        this.a = new ItemStack[36];
        this.b = new ItemStack[4];
        this.c = new ItemStack[4];

        for (int i = 0; i < nbttaglist.b(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.a(i);
            int j = nbttagcompound.b("Slot") & 255;

            if (j >= 0 && j < this.a.length) {
                this.a[j] = new ItemStack(nbttagcompound);
            }

            if (j >= 80 && j < this.c.length + 80) {
                this.c[j - 80] = new ItemStack(nbttagcompound);
            }

            if (j >= 100 && j < this.b.length + 100) {
                this.b[j - 100] = new ItemStack(nbttagcompound);
            }
        }
    }

    public int a() {
        return this.a.length + 4;
    }

    public ItemStack a(int i) {
        ItemStack[] aitemstack = this.a;

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.b;
        }

        if (i >= aitemstack.length) {
            i -= aitemstack.length;
            aitemstack = this.c;
        }

        return aitemstack[i];
    }

    public int d() {
        return 64;
    }

    public boolean b(Block block) {
        if (block.bt != Material.d && block.bt != Material.e && block.bt != Material.t && block.bt != Material.s) {
            return true;
        } else {
            ItemStack itemstack = this.a(this.d);

            return itemstack != null ? itemstack.b(block) : false;
        }
    }

    public int e() {
        int i = 0;
        int j = 0;
        int k = 0;

        for (int l = 0; l < this.b.length; ++l) {
            if (this.b[l] != null && this.b[l].a() instanceof ItemArmor) {
                int i1 = this.b[l].c();
                int j1 = this.b[l].d;
                int k1 = i1 - j1;

                j += k1;
                k += i1;
                int l1 = ((ItemArmor) this.b[l].a()).bc;

                i += l1;
            }
        }

        if (k == 0) {
            return 0;
        } else {
            return (i - 1) * j / k + 1;
        }
    }

    public void b(int i) {
        for (int j = 0; j < this.b.length; ++j) {
            if (this.b[j] != null && this.b[j].a() instanceof ItemArmor) {
                this.b[j].a(i);
                if (this.b[j].a == 0) {
                    this.b[j].a(this.f);
                    this.b[j] = null;
                }
            }
        }
    }

    public void f() {
        int i;

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.f.a(this.a[i], true);
                this.a[i] = null;
            }
        }

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                this.f.a(this.b[i], true);
                this.b[i] = null;
            }
        }
    }
}
