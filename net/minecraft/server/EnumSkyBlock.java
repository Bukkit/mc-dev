package net.minecraft.server;

public enum EnumSkyBlock {

    SKY("Sky", 0, 15), BLOCK("Block", 1, 0);
    public final int c;

    private static final EnumSkyBlock[] d = new EnumSkyBlock[] { SKY, BLOCK};

    private EnumSkyBlock(String s, int i, int j) {
        this.c = j;
    }
}
