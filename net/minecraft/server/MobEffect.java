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

    public MobEffect(MobEffect mobeffect) {
        this.effectId = mobeffect.effectId;
        this.duration = mobeffect.duration;
        this.amplification = mobeffect.amplification;
    }

    public void a(MobEffect mobeffect) {
        if (this.effectId != mobeffect.effectId) {
            System.err.println("This method should only be called for matching effects!");
        }

        if (mobeffect.amplification > this.amplification) {
            this.amplification = mobeffect.amplification;
            this.duration = mobeffect.duration;
        } else if (mobeffect.amplification == this.amplification && this.duration < mobeffect.duration) {
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
            if (MobEffectList.byId[this.effectId].b(this.duration, this.amplification)) {
                this.b(entityliving);
            }

            this.e();
        }

        return this.duration > 0;
    }

    private int e() {
        return --this.duration;
    }

    public void b(EntityLiving entityliving) {
        if (this.duration > 0) {
            MobEffectList.byId[this.effectId].tick(entityliving, this.amplification);
        }
    }

    public String d() {
        return MobEffectList.byId[this.effectId].c();
    }

    public int hashCode() {
        return this.effectId;
    }

    public String toString() {
        String s = "";

        if (this.getAmplifier() > 0) {
            s = this.d() + " x " + (this.getAmplifier() + 1) + ", Duration: " + this.getDuration();
        } else {
            s = this.d() + ", Duration: " + this.getDuration();
        }

        return MobEffectList.byId[this.effectId].f() ? "(" + s + ")" : s;
    }

    public boolean equals(Object object) {
        if (!(object instanceof MobEffect)) {
            return false;
        } else {
            MobEffect mobeffect = (MobEffect) object;

            return this.effectId == mobeffect.effectId && this.amplification == mobeffect.amplification && this.duration == mobeffect.duration;
        }
    }
}
