package net.minecraft.server;

import java.util.Map;
import net.minecraft.util.com.google.common.collect.Maps;

public enum EnumClickAction {

    OPEN_URL("OPEN_URL", 0, "open_url", true), OPEN_FILE("OPEN_FILE", 1, "open_file", false), RUN_COMMAND("RUN_COMMAND", 2, "run_command", true), SUGGEST_COMMAND("SUGGEST_COMMAND", 3, "suggest_command", true);
    private static final Map e = Maps.newHashMap();
    private final boolean f;
    private final String g;
    private static final EnumClickAction[] h = new EnumClickAction[] { OPEN_URL, OPEN_FILE, RUN_COMMAND, SUGGEST_COMMAND};

    private EnumClickAction(String s, int i, String s1, boolean flag) {
        this.g = s1;
        this.f = flag;
    }

    public boolean a() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public static EnumClickAction a(String s) {
        return (EnumClickAction) e.get(s);
    }

    static {
        EnumClickAction[] aenumclickaction = values();
        int i = aenumclickaction.length;

        for (int j = 0; j < i; ++j) {
            EnumClickAction enumclickaction = aenumclickaction[j];

            e.put(enumclickaction.b(), enumclickaction);
        }
    }
}
