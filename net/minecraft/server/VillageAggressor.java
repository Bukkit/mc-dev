package net.minecraft.server;

class VillageAggressor {

    public EntityLiving a;
    public int b;
    final Village c;

    VillageAggressor(Village village, EntityLiving entityliving, int i) {
        this.c = village;
        this.a = entityliving;
        this.b = i;
    }
}
