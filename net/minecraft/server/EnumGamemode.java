package net.minecraft.server;

public enum EnumGamemode {

    NONE("NOT_SET", 0, -1, ""), SURVIVAL("SURVIVAL", 1, 0, "survival"), CREATIVE("CREATIVE", 2, 1, "creative"), ADVENTURE("ADVENTURE", 3, 2, "adventure");
    int e;
    String f;
    private static final EnumGamemode[] g = new EnumGamemode[] { NONE, SURVIVAL, CREATIVE, ADVENTURE};

    private EnumGamemode(String s, int i, int j, String s1) {
        this.e = j;
        this.f = s1;
    }

    public int getId() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    public void a(PlayerAbilities playerabilities) {
        if (this == CREATIVE) {
            playerabilities.canFly = true;
            playerabilities.canInstantlyBuild = true;
            playerabilities.isInvulnerable = true;
        } else {
            playerabilities.canFly = false;
            playerabilities.canInstantlyBuild = false;
            playerabilities.isInvulnerable = false;
            playerabilities.isFlying = false;
        }

        playerabilities.mayBuild = !this.isAdventure();
    }

    public boolean isAdventure() {
        return this == ADVENTURE;
    }

    public boolean d() {
        return this == CREATIVE;
    }

    public static EnumGamemode getById(int i) {
        EnumGamemode[] aenumgamemode = values();
        int j = aenumgamemode.length;

        for (int k = 0; k < j; ++k) {
            EnumGamemode enumgamemode = aenumgamemode[k];

            if (enumgamemode.e == i) {
                return enumgamemode;
            }
        }

        return SURVIVAL;
    }
}
