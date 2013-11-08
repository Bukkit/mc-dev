package net.minecraft.server;

import java.util.Map;
import net.minecraft.util.com.google.common.collect.Maps;

public enum EnumHoverAction {

    SHOW_TEXT("SHOW_TEXT", 0, "show_text", true), SHOW_ACHIEVEMENT("SHOW_ACHIEVEMENT", 1, "show_achievement", true), SHOW_ITEM("SHOW_ITEM", 2, "show_item", true);
    private static final Map d = Maps.newHashMap();
    private final boolean e;
    private final String f;
    private static final EnumHoverAction[] g = new EnumHoverAction[] { SHOW_TEXT, SHOW_ACHIEVEMENT, SHOW_ITEM};

    private EnumHoverAction(String s, int i, String s1, boolean flag) {
        this.f = s1;
        this.e = flag;
    }

    public boolean a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    public static EnumHoverAction a(String s) {
        return (EnumHoverAction) d.get(s);
    }

    static {
        EnumHoverAction[] aenumhoveraction = values();
        int i = aenumhoveraction.length;

        for (int j = 0; j < i; ++j) {
            EnumHoverAction enumhoveraction = aenumhoveraction[j];

            d.put(enumhoveraction.b(), enumhoveraction);
        }
    }
}
