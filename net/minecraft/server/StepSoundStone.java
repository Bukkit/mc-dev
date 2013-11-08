package net.minecraft.server;

final class StepSoundStone extends StepSound {

    StepSoundStone(String s, float f, float f1) {
        super(s, f, f1);
    }

    public String getBreakSound() {
        return "dig.glass";
    }

    public String getPlaceSound() {
        return "step.stone";
    }
}
