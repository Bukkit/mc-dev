package net.minecraft.server;

public enum EnumClientCommand {

    PERFORM_RESPAWN("PERFORM_RESPAWN", 0, 0), REQUEST_STATS("REQUEST_STATS", 1, 1), OPEN_INVENTORY_ACHIEVEMENT("OPEN_INVENTORY_ACHIEVEMENT", 2, 2);
    private final int d;
    private static final EnumClientCommand[] e = new EnumClientCommand[values().length];
    private static final EnumClientCommand[] f = new EnumClientCommand[] { PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT};

    private EnumClientCommand(String s, int i, int j) {
        this.d = j;
    }

    static EnumClientCommand[] a() {
        return e;
    }

    static int a(EnumClientCommand enumclientcommand) {
        return enumclientcommand.d;
    }

    static {
        EnumClientCommand[] aenumclientcommand = values();
        int i = aenumclientcommand.length;

        for (int j = 0; j < i; ++j) {
            EnumClientCommand enumclientcommand = aenumclientcommand[j];

            e[enumclientcommand.d] = enumclientcommand;
        }
    }
}
