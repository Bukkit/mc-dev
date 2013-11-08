package net.minecraft.server;

public enum EnumEntityUseAction {

    INTERACT("INTERACT", 0, 0), ATTACK("ATTACK", 1, 1);
    private static final EnumEntityUseAction[] c = new EnumEntityUseAction[values().length];
    private final int d;
    private static final EnumEntityUseAction[] e = new EnumEntityUseAction[] { INTERACT, ATTACK};

    private EnumEntityUseAction(String s, int i, int j) {
        this.d = j;
    }

    static EnumEntityUseAction[] a() {
        return c;
    }

    static int a(EnumEntityUseAction enumentityuseaction) {
        return enumentityuseaction.d;
    }

    static {
        EnumEntityUseAction[] aenumentityuseaction = values();
        int i = aenumentityuseaction.length;

        for (int j = 0; j < i; ++j) {
            EnumEntityUseAction enumentityuseaction = aenumentityuseaction[j];

            c[enumentityuseaction.d] = enumentityuseaction;
        }
    }
}
