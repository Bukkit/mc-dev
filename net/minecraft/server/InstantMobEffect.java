package net.minecraft.server;

public class InstantMobEffect extends MobEffectList {

    public InstantMobEffect(int i) {
        super(i);
    }

    public boolean a(int i, int j) {
        return i >= 1;
    }
}
