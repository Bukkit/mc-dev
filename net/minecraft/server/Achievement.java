package net.minecraft.server;

public class Achievement extends Statistic {

    public final int a;
    public final int b;
    public final Achievement c;

    public Achievement(int i, String s, int j, int k, Achievement achievement) {
        super(i, s);
        this.a = j + 46;
        this.b = k + 23;
        this.c = achievement;
    }

    public boolean a() {
        return true;
    }
}
