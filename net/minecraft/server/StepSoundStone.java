package net.minecraft.server;

final class StepSoundStone extends StepSound {

    StepSoundStone(String s, float f, float f1) {
        super(s, f, f1);
    }

    public String a() {
        return "random.glass";
    }

    public String b() {
        return "step.stone";
    }
}
