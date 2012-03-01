package net.minecraft.server;

class VillageAgressor {

    public EntityLiving a;
    public int b;

    final Village c;

    VillageAgressor(Village village, EntityLiving entityliving, int i) {
        this.c = village;
        this.a = entityliving;
        this.b = i;
    }
}
