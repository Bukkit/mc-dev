package net.minecraft.server;

public enum MonsterType {

    UNDEFINED("UNDEFINED", 0), UNDEAD("UNDEAD", 1), ARTHROPOD("ARTHROPOD", 2);

    private static final MonsterType[] d = new MonsterType[] { UNDEFINED, UNDEAD, ARTHROPOD};

    private MonsterType(String s, int i) {}
}
