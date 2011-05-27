package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeBase {

    public static final BiomeBase RAINFOREST = (new BiomeRainforest()).b(588342).a("Rainforest").a(2094168);
    public static final BiomeBase SWAMPLAND = (new BiomeSwamp()).b(522674).a("Swampland").a(9154376);
    public static final BiomeBase SEASONAL_FOREST = (new BiomeBase()).b(10215459).a("Seasonal Forest");
    public static final BiomeBase FOREST = (new BiomeForest()).b(353825).a("Forest").a(5159473);
    public static final BiomeBase SAVANNA = (new BiomeDesert()).b(14278691).a("Savanna");
    public static final BiomeBase SHRUBLAND = (new BiomeBase()).b(10595616).a("Shrubland");
    public static final BiomeBase TAIGA = (new BiomeTaiga()).b(3060051).a("Taiga").b().a(8107825);
    public static final BiomeBase DESERT = (new BiomeDesert()).b(16421912).a("Desert").e();
    public static final BiomeBase PLAINS = (new BiomeDesert()).b(16767248).a("Plains");
    public static final BiomeBase ICE_DESERT = (new BiomeDesert()).b(16772499).a("Ice Desert").b().e().a(12899129);
    public static final BiomeBase TUNDRA = (new BiomeBase()).b(5762041).a("Tundra").b().a(12899129);
    public static final BiomeBase HELL = (new BiomeHell()).b(16711680).a("Hell").e();
    public String m;
    public int n;
    public byte o;
    public byte p;
    public int q;
    protected List r;
    protected List s;
    protected List t;
    private boolean u;
    private boolean v;
    private static BiomeBase[] w = new BiomeBase[4096];

    protected BiomeBase() {
        this.o = (byte) Block.GRASS.id;
        this.p = (byte) Block.DIRT.id;
        this.q = 5169201;
        this.r = new ArrayList();
        this.s = new ArrayList();
        this.t = new ArrayList();
        this.v = true;
        this.r.add(new BiomeMeta(EntitySpider.class, 10));
        this.r.add(new BiomeMeta(EntityZombie.class, 10));
        this.r.add(new BiomeMeta(EntitySkeleton.class, 10));
        this.r.add(new BiomeMeta(EntityCreeper.class, 10));
        this.r.add(new BiomeMeta(EntitySlime.class, 10));
        this.s.add(new BiomeMeta(EntitySheep.class, 12));
        this.s.add(new BiomeMeta(EntityPig.class, 10));
        this.s.add(new BiomeMeta(EntityChicken.class, 10));
        this.s.add(new BiomeMeta(EntityCow.class, 8));
        this.t.add(new BiomeMeta(EntitySquid.class, 10));
    }

    private BiomeBase e() {
        this.v = false;
        return this;
    }

    public static void a() {
        for (int i = 0; i < 64; ++i) {
            for (int j = 0; j < 64; ++j) {
                w[i + j * 64] = a((float) i / 63.0F, (float) j / 63.0F);
            }
        }

        DESERT.o = DESERT.p = (byte) Block.SAND.id;
        ICE_DESERT.o = ICE_DESERT.p = (byte) Block.SAND.id;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(10) == 0 ? new WorldGenBigTree() : new WorldGenTrees());
    }

    protected BiomeBase b() {
        this.u = true;
        return this;
    }

    protected BiomeBase a(String s) {
        this.m = s;
        return this;
    }

    protected BiomeBase a(int i) {
        this.q = i;
        return this;
    }

    protected BiomeBase b(int i) {
        this.n = i;
        return this;
    }

    public static BiomeBase a(double d0, double d1) {
        int i = (int) (d0 * 63.0D);
        int j = (int) (d1 * 63.0D);

        return w[i + j * 64];
    }

    public static BiomeBase a(float f, float f1) {
        f1 *= f;
        return f < 0.1F ? TUNDRA : (f1 < 0.2F ? (f < 0.5F ? TUNDRA : (f < 0.95F ? SAVANNA : DESERT)) : (f1 > 0.5F && f < 0.7F ? SWAMPLAND : (f < 0.5F ? TAIGA : (f < 0.97F ? (f1 < 0.35F ? SHRUBLAND : FOREST) : (f1 < 0.45F ? PLAINS : (f1 < 0.9F ? SEASONAL_FOREST : RAINFOREST))))));
    }

    public List a(EnumCreatureType enumcreaturetype) {
        return enumcreaturetype == EnumCreatureType.MONSTER ? this.r : (enumcreaturetype == EnumCreatureType.CREATURE ? this.s : (enumcreaturetype == EnumCreatureType.WATER_CREATURE ? this.t : null));
    }

    public boolean c() {
        return this.u;
    }

    public boolean d() {
        return this.u ? false : this.v;
    }

    static {
        a();
    }
}
