package net.minecraft.server;

public enum EnumMonsterType {

    UNDEFINED("UNDEFINED", 0), UNDEAD("UNDEAD", 1), ARTHROPOD("ARTHROPOD", 2);

    private static final EnumMonsterType[] d = new EnumMonsterType[] { UNDEFINED, UNDEAD, ARTHROPOD};

    private EnumMonsterType(String s, int i) {}
}
