package net.minecraft.server;

public enum EnumChatVisibility {

    FULL("FULL", 0, 0, "options.chat.visibility.full"), SYSTEM("SYSTEM", 1, 1, "options.chat.visibility.system"), HIDDEN("HIDDEN", 2, 2, "options.chat.visibility.hidden");
    private static final EnumChatVisibility[] d = new EnumChatVisibility[values().length];
    private final int e;
    private final String f;
    private static final EnumChatVisibility[] g = new EnumChatVisibility[] { FULL, SYSTEM, HIDDEN};

    private EnumChatVisibility(String s, int i, int j, String s1) {
        this.e = j;
        this.f = s1;
    }

    public int a() {
        return this.e;
    }

    public static EnumChatVisibility a(int i) {
        return d[i % d.length];
    }

    static {
        EnumChatVisibility[] aenumchatvisibility = values();
        int i = aenumchatvisibility.length;

        for (int j = 0; j < i; ++j) {
            EnumChatVisibility enumchatvisibility = aenumchatvisibility[j];

            d[enumchatvisibility.e] = enumchatvisibility;
        }
    }
}
