package net.minecraft.server;

public class InstantMobEffect extends MobEffectList {

    public InstantMobEffect(int i, boolean flag, int j) {
        super(i, flag, j);
    }

    public boolean isInstant() {
        return true;
    }

    public boolean a(int i, int j) {
        return i >= 1;
    }
}
