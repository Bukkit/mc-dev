package net.minecraft.server;

public enum EnumMovingObjectType {

    MISS("MISS", 0), BLOCK("BLOCK", 1), ENTITY("ENTITY", 2);
    private static final EnumMovingObjectType[] d = new EnumMovingObjectType[] { MISS, BLOCK, ENTITY};

    private EnumMovingObjectType(String s, int i) {}
}
