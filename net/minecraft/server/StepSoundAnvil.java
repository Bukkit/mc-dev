package net.minecraft.server;

final class StepSoundAnvil extends StepSound {

    StepSoundAnvil(String s, float f, float f1) {
        super(s, f, f1);
    }

    public String getBreakSound() {
        return "dig.stone";
    }

    public String getPlaceSound() {
        return "random.anvil_land";
    }
}
