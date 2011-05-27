package net.minecraft.server;

public class StepSound {

    public final String a;
    public final float b;
    public final float c;

    public StepSound(String s, float f, float f1) {
        this.a = s;
        this.b = f;
        this.c = f1;
    }

    public float getVolume1() {
        return this.b;
    }

    public float getVolume2() {
        return this.c;
    }

    public String getName() {
        return "step." + this.a;
    }
}
