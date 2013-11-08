package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class BiomeBaseSub extends BiomeBase {

    protected BiomeBase aD;

    public BiomeBaseSub(int i, BiomeBase biomebase) {
        super(i);
        this.aD = biomebase;
        this.a(biomebase.ag, true);
        this.af = biomebase.af + " M";
        this.ai = biomebase.ai;
        this.ak = biomebase.ak;
        this.al = biomebase.al;
        this.am = biomebase.am;
        this.an = biomebase.an;
        this.temperature = biomebase.temperature;
        this.humidity = biomebase.humidity;
        this.aq = biomebase.aq;
        this.aw = biomebase.aw;
        this.ax = biomebase.ax;
        this.at = new ArrayList(biomebase.at);
        this.as = new ArrayList(biomebase.as);
        this.av = new ArrayList(biomebase.av);
        this.au = new ArrayList(biomebase.au);
        this.temperature = biomebase.temperature;
        this.humidity = biomebase.humidity;
        this.am = biomebase.am + 0.1F;
        this.an = biomebase.an + 0.2F;
    }

    public void a(World world, Random random, int i, int j) {
        this.aD.ar.a(world, random, this, i, j);
    }

    public void a(World world, Random random, Block[] ablock, byte[] abyte, int i, int j, double d0) {
        this.aD.a(world, random, ablock, abyte, i, j, d0);
    }

    public float g() {
        return this.aD.g();
    }

    public WorldGenTreeAbstract a(Random random) {
        return this.aD.a(random);
    }

    public Class l() {
        return this.aD.l();
    }

    public boolean a(BiomeBase biomebase) {
        return this.aD.a(biomebase);
    }

    public EnumTemperature m() {
        return this.aD.m();
    }
}
