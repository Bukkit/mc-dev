package net.minecraft.server;

public class MobEffect {

    private int a;
    private int b;
    private int c;

    public MobEffect(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public void a(MobEffect mobeffect) {
        if (this.a != mobeffect.a) {
            System.err.println("This method should only be called for matching effects!");
        }

        if (mobeffect.c >= this.c) {
            this.c = mobeffect.c;
            this.b = mobeffect.b;
        }
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean a(EntityLiving entityliving) {
        if (this.b > 0) {
            if (MobEffectList.a[this.a].a(this.b, this.c)) {
                this.b(entityliving);
            }

            this.d();
        }

        return this.b > 0;
    }

    private int d() {
        return --this.b;
    }

    public void b(EntityLiving entityliving) {
        if (this.b > 0) {
            MobEffectList.a[this.a].a(entityliving, this.c);
        }
    }

    public int hashCode() {
        return this.a;
    }
}
