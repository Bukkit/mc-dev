package net.minecraft.server;

public enum EnumMobType {

    EVERYTHING("everything", 0), MOBS("mobs", 1), PLAYERS("players", 2);

    private static final EnumMobType[] d = new EnumMobType[] { EVERYTHING, MOBS, PLAYERS};

    private EnumMobType(String s, int i) {}
}
