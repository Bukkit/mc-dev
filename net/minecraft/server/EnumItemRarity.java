package net.minecraft.server;

public enum EnumItemRarity {

    COMMON("common", 0, EnumChatFormat.WHITE, "Common"), UNCOMMON("uncommon", 1, EnumChatFormat.YELLOW, "Uncommon"), RARE("rare", 2, EnumChatFormat.AQUA, "Rare"), EPIC("epic", 3, EnumChatFormat.LIGHT_PURPLE, "Epic");
    public final EnumChatFormat e;
    public final String f;
    private static final EnumItemRarity[] g = new EnumItemRarity[] { COMMON, UNCOMMON, RARE, EPIC};

    private EnumItemRarity(String s, int i, EnumChatFormat enumchatformat, String s1) {
        this.e = enumchatformat;
        this.f = s1;
    }
}
