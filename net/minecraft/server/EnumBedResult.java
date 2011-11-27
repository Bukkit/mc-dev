package net.minecraft.server;

public enum EnumBedResult {

    OK("OK", 0), NOT_POSSIBLE_HERE("NOT_POSSIBLE_HERE", 1), NOT_POSSIBLE_NOW("NOT_POSSIBLE_NOW", 2), TOO_FAR_AWAY("TOO_FAR_AWAY", 3), OTHER_PROBLEM("OTHER_PROBLEM", 4), NOT_SAFE("NOT_SAFE", 5);

    private static final EnumBedResult[] g = new EnumBedResult[] { OK, NOT_POSSIBLE_HERE, NOT_POSSIBLE_NOW, TOO_FAR_AWAY, OTHER_PROBLEM, NOT_SAFE};

    private EnumBedResult(String s, int i) {}
}
