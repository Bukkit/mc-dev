package net.minecraft.server;

public class Achievement extends Statistic {

    public final int a;
    public final int b;
    public final Achievement c;
    private final String k;
    public final ItemStack d;
    private boolean l;

    public Achievement(int i, String s, int j, int k, Item item, Achievement achievement) {
        this(i, s, j, k, new ItemStack(item), achievement);
    }

    public Achievement(int i, String s, int j, int k, Block block, Achievement achievement) {
        this(i, s, j, k, new ItemStack(block), achievement);
    }

    public Achievement(int i, String s, int j, int k, ItemStack itemstack, Achievement achievement) {
        super(5242880 + i, "achievement." + s);
        this.d = itemstack;
        this.k = "achievement." + s + ".desc";
        this.a = j;
        this.b = k;
        if (j < AchievementList.a) {
            a = j;
        }

        if (k < AchievementList.b) {
            b = k;
        }

        if (j > AchievementList.c) {
            AchievementList.c = j;
        }

        if (k > AchievementList.d) {
            AchievementList.d = k;
        }

        this.c = achievement;
    }

    public Achievement a() {
        this.f = true;
        return this;
    }

    public Achievement b() {
        this.l = true;
        return this;
    }

    public Achievement c() {
        super.d();
        AchievementList.e.add(this);
        return this;
    }
}
