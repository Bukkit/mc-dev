package net.minecraft.server;

public final class ItemStack {

    public int a;
    public int b;
    public int c;
    private int d;

    public ItemStack(Block block) {
        this(block, 1);
    }

    public ItemStack(Block block, int i) {
        this(block.bi, i, 0);
    }

    public ItemStack(Block block, int i, int j) {
        this(block.bi, i, j);
    }

    public ItemStack(Item item) {
        this(item.ba, 1, 0);
    }

    public ItemStack(Item item, int i) {
        this(item.ba, i, 0);
    }

    public ItemStack(Item item, int i, int j) {
        this(item.ba, i, j);
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

    public ItemStack a(int i) {
        this.a -= i;
        return new ItemStack(this.c, i, this.d);
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

    public ItemStack a(World world, EntityHuman entityhuman) {
        return this.a().a(this, world, entityhuman);
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
        return this.a().b();
    }

    public boolean c() {
        return this.b() > 1 && (!this.d() || !this.f());
    }

    public boolean d() {
        return Item.c[this.c].d() > 0;
    }

    public boolean e() {
        return Item.c[this.c].c();
    }

    public boolean f() {
        return this.d() && this.d > 0;
    }

    public int g() {
        return this.d;
    }

    public int h() {
        return this.d;
    }

    public int i() {
        return Item.c[this.c].d();
    }

    public void b(int i) {
        if (this.d()) {
            this.d += i;
            if (this.d > this.i()) {
                --this.a;
                if (this.a < 0) {
                    this.a = 0;
                }

                this.d = 0;
            }
        }
    }

    public void a(EntityLiving entityliving) {
        Item.c[this.c].a(this, entityliving);
    }

    public void a(int i, int j, int k, int l) {
        Item.c[this.c].a(this, i, j, k, l);
    }

    public int a(Entity entity) {
        return Item.c[this.c].a(entity);
    }

    public boolean b(Block block) {
        return Item.c[this.c].a(block);
    }

    public void a(EntityHuman entityhuman) {}

    public void b(EntityLiving entityliving) {
        Item.c[this.c].b(this, entityliving);
    }

    public ItemStack j() {
        return new ItemStack(this.c, this.a, this.d);
    }

    public static boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack == null && itemstack1 == null ? true : (itemstack != null && itemstack1 != null ? itemstack.c(itemstack1) : false);
    }

    private boolean c(ItemStack itemstack) {
        return this.a != itemstack.a ? false : (this.c != itemstack.c ? false : this.d == itemstack.d);
    }

    public boolean a(ItemStack itemstack) {
        return this.c == itemstack.c && this.d == itemstack.d;
    }

    public static ItemStack b(ItemStack itemstack) {
        return itemstack == null ? null : itemstack.j();
    }

    public String toString() {
        return this.a + "x" + Item.c[this.c].a() + "@" + this.d;
    }
}
