package net.minecraft.server;

final class StepSoundLadder extends StepSound {

    StepSoundLadder(String s, float f, float f1) {
        super(s, f, f1);
    }

    public String getBreakSound() {
        return "dig.wood";
    }
}
