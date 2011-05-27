package net.minecraft.server;

public enum EnumCreatureType {

    MONSTER("monster", 0, IMonster.class, 100), CREATURE("creature", 1, EntityAnimal.class, 20);
    public final Class WATER_CREATURE;
    public final int d;

    private static final EnumCreatureType[] e = new EnumCreatureType[] { MONSTER, CREATURE};

    private EnumCreatureType(String s, int i, Class oclass, int j) {
        this.WATER_CREATURE = oclass;
        this.d = j;
    }
}
