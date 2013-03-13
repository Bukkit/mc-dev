package net.minecraft.server;

public enum EnumAnimation {

    NONE("none", 0), EAT("eat", 1), DRINK("drink", 2), BLOCK("block", 3), BOW("bow", 4);

    private static final EnumAnimation[] f = new EnumAnimation[] { NONE, EAT, DRINK, BLOCK, BOW};

    private EnumAnimation(String s, int i) {}
}
