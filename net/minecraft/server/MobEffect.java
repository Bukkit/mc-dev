package net.minecraft.server;

public class MobEffect {

    private int effectId;
    private int duration;
    private int amplification;

    public MobEffect(int i, int j, int k) {
        this.effectId = i;
        this.duration = j;
        this.amplification = k;
    }

    public void a(MobEffect mobeffect) {
        if (this.effectId != mobeffect.effectId) {
            System.err.println("This method should only be called for matching effects!");
        }

        if (mobeffect.amplification >= this.amplification) {
            this.amplification = mobeffect.amplification;
            this.duration = mobeffect.duration;
        }
    }

    public int getEffectId() {
        return this.effectId;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getAmplifier() {
        return this.amplification;
    }

    public boolean tick(EntityLiving entityliving) {
        if (this.duration > 0) {
            if (MobEffectList.byId[this.effectId].a(this.duration, this.amplification)) {
                this.b(entityliving);
            }

            this.d();
        }

        return this.duration > 0;
    }

    private int d() {
        return --this.duration;
    }

    public void b(EntityLiving entityliving) {
        if (this.duration > 0) {
            MobEffectList.byId[this.effectId].tick(entityliving, this.amplification);
        }
    }

    public int hashCode() {
        return this.effectId;
    }
}
