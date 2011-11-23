package net.minecraft.server;

public class InstantMobEffect extends MobEffectList {

    public InstantMobEffect(int i, boolean flag, int j) {
        super(i, flag, j);
    }

    public boolean b() {
        return true;
    }

    public boolean b(int i, int j) {
        return i >= 1;
    }
}
