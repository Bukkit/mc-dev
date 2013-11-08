package net.minecraft.server;

public enum EnumCreatureType {

    MONSTER("monster", 0, IMonster.class, 70, Material.AIR, false, false), CREATURE("creature", 1, EntityAnimal.class, 10, Material.AIR, true, true), AMBIENT("ambient", 2, EntityAmbient.class, 15, Material.AIR, true, false), WATER_CREATURE("waterCreature", 3, EntityWaterAnimal.class, 5, Material.WATER, true, false);
    private final Class e;
    private final int f;
    private final Material g;
    private final boolean h;
    private final boolean i;
    private static final EnumCreatureType[] j = new EnumCreatureType[] { MONSTER, CREATURE, AMBIENT, WATER_CREATURE};

    private EnumCreatureType(String s, int i, Class oclass, int j, Material material, boolean flag, boolean flag1) {
        this.e = oclass;
        this.f = j;
        this.g = material;
        this.h = flag;
        this.i = flag1;
    }

    public Class a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public Material c() {
        return this.g;
    }

    public boolean d() {
        return this.h;
    }

    public boolean e() {
        return this.i;
    }
}
