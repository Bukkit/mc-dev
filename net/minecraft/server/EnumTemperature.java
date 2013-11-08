package net.minecraft.server;

public enum EnumTemperature {

    OCEAN("OCEAN", 0), COLD("COLD", 1), MEDIUM("MEDIUM", 2), WARM("WARM", 3);
    private static final EnumTemperature[] e = new EnumTemperature[] { OCEAN, COLD, MEDIUM, WARM};

    private EnumTemperature(String s, int i) {}
}
