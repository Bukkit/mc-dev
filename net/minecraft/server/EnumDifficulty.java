package net.minecraft.server;

public enum EnumDifficulty {

    PEACEFUL("PEACEFUL", 0, 0, "options.difficulty.peaceful"), EASY("EASY", 1, 1, "options.difficulty.easy"), NORMAL("NORMAL", 2, 2, "options.difficulty.normal"), HARD("HARD", 3, 3, "options.difficulty.hard");
    private static final EnumDifficulty[] e = new EnumDifficulty[values().length];
    private final int f;
    private final String g;
    private static final EnumDifficulty[] h = new EnumDifficulty[] { PEACEFUL, EASY, NORMAL, HARD};

    private EnumDifficulty(String s, int i, int j, String s1) {
        this.f = j;
        this.g = s1;
    }

    public int a() {
        return this.f;
    }

    public static EnumDifficulty a(int i) {
        return e[i % e.length];
    }

    public String b() {
        return this.g;
    }

    static {
        EnumDifficulty[] aenumdifficulty = values();
        int i = aenumdifficulty.length;

        for (int j = 0; j < i; ++j) {
            EnumDifficulty enumdifficulty = aenumdifficulty[j];

            e[enumdifficulty.f] = enumdifficulty;
        }
    }
}
