package net.minecraft.server;

public class ItemFood extends Item {

    public final int a;
    private final int b;
    private final float c;
    private final boolean d;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private float q;

    public ItemFood(int i, float f, boolean flag) {
        this.a = 32;
        this.b = i;
        this.d = flag;
        this.c = f;
        this.a(CreativeModeTab.h);
    }

    public ItemFood(int i, boolean flag) {
        this(i, 0.6F, flag);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.count;
        entityhuman.getFoodData().a(this, itemstack);
        world.makeSound(entityhuman, "random.burp", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
        this.c(itemstack, world, entityhuman);
        return itemstack;
    }

    protected void c(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!world.isStatic && this.n > 0 && world.random.nextFloat() < this.q) {
            entityhuman.addEffect(new MobEffect(this.n, this.o * 20, this.p));
        }
    }

    public int d_(ItemStack itemstack) {
        return 32;
    }

    public EnumAnimation d(ItemStack itemstack) {
        return EnumAnimation.EAT;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.g(this.m)) {
            entityhuman.a(itemstack, this.d_(itemstack));
        }

        return itemstack;
    }

    public int getNutrition(ItemStack itemstack) {
        return this.b;
    }

    public float getSaturationModifier(ItemStack itemstack) {
        return this.c;
    }

    public boolean i() {
        return this.d;
    }

    public ItemFood a(int i, int j, int k, float f) {
        this.n = i;
        this.o = j;
        this.p = k;
        this.q = f;
        return this;
    }

    public ItemFood j() {
        this.m = true;
        return this;
    }
}
