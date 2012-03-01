package net.minecraft.server;

public enum WorldType {

    NORMAL("DEFAULT", 0, "default"), FLAT("FLAT", 1, "flat");
    private String c;

    private static final WorldType[] d = new WorldType[] { NORMAL, FLAT};

    private WorldType(String s, int i, String s1) {
        this.c = s1;
    }

    public static WorldType getType(String s) {
        WorldType[] aworldtype = values();
        int i = aworldtype.length;

        for (int j = 0; j < i; ++j) {
            WorldType worldtype = aworldtype[j];

            if (worldtype.name().equals(s)) {
                return worldtype;
            }
        }

        return null;
    }
}
