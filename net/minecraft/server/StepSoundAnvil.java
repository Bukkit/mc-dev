package net.minecraft.server;

final class StepSoundAnvil extends StepSound {

    StepSoundAnvil(String s, float f, float f1) {
        super(s, f, f1);
    }

    public String a() {
        return "dig.stone";
    }

    public String b() {
        return "random.anvil_land";
    }
}
