package net.minecraft.server;

public final class ItemStack {

    public int a;
    public int b;
    public int c;
    public int d;

    public ItemStack(Block block) {
        this(block, 1);
    }

    public ItemStack(Block block, int i) {
        this(block.bi, i);
    }

    public ItemStack(Item item) {
        this(item, 1);
    }

    public ItemStack(Item item, int i) {
        this(item.aW, i);
    }

    public ItemStack(int i) {
        this(i, 1);
    }

    public ItemStack(int i, int j) {
        this.a = 0;
        this.c = i;
        this.a = j;
    }

    public ItemStack(int i, int j, int k) {
        this.a = 0;
        this.c = i;
        this.a = j;
        this.d = k;
    }

    public ItemStack(NBTTagCompound nbttagcompound) {
        this.a = 0;
        this.b(nbttagcompound);
    }

    public Item a() {
        return Item.c[this.c];
    }

    public boolean a(EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        return this.a().a(this, entityhuman, world, i, j, k, l);
    }

    public float a(Block block) {
        return this.a().a(this, block);
    }

    public NBTTagCompound a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("id", (short) this.c);
        nbttagcompound.a("Count", (byte) this.a);
        nbttagcompound.a("Damage", (short) this.d);
        return nbttagcompound;
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.c = nbttagcompound.c("id");
        this.a = nbttagcompound.b("Count");
        this.d = nbttagcompound.c("Damage");
    }

    public int b() {
        return this.a().a();
    }

    public int c() {
        return Item.c[this.c].b();
    }

    public void a(int i) {
        this.d += i;
        if (this.d > this.c()) {
            --this.a;
            if (this.a < 0) {
                this.a = 0;
            }

            this.d = 0;
        }
    }

    public void a(int i, int j, int k, int l) {
        Item.c[this.c].a(this, i, j, k, l);
    }

    public boolean b(Block block) {
        return Item.c[this.c].a(block);
    }

    public void a(EntityHuman entityhuman) {}

    public ItemStack d() {
        return new ItemStack(this.c, this.a, this.d);
    }
}
