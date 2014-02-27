package net.minecraft.server;

import java.util.Map;
import net.minecraft.util.com.google.common.collect.Maps;

public enum EnumClickAction {

    OPEN_URL("OPEN_URL", 0, "open_url", true), OPEN_FILE("OPEN_FILE", 1, "open_file", false), RUN_COMMAND("RUN_COMMAND", 2, "run_command", true), TWITCH_USER_INFO("TWITCH_USER_INFO", 3, "twitch_user_info", false), SUGGEST_COMMAND("SUGGEST_COMMAND", 4, "suggest_command", true);
    private static final Map f = Maps.newHashMap();
    private final boolean g;
    private final String h;
    private static final EnumClickAction[] i = new EnumClickAction[] { OPEN_URL, OPEN_FILE, RUN_COMMAND, TWITCH_USER_INFO, SUGGEST_COMMAND};

    private EnumClickAction(String s, int i, String s1, boolean flag) {
        this.h = s1;
        this.g = flag;
    }

    public boolean a() {
        return this.g;
    }

    public String b() {
        return this.h;
    }

    public static EnumClickAction a(String s) {
        return (EnumClickAction) f.get(s);
    }

    static {
        EnumClickAction[] aenumclickaction = values();
        int i = aenumclickaction.length;

        for (int j = 0; j < i; ++j) {
            EnumClickAction enumclickaction = aenumclickaction[j];

            f.put(enumclickaction.b(), enumclickaction);
        }
    }
}
