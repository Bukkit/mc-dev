package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BiomeBase {

    public static final BiomeBase[] biomes = new BiomeBase[256];
    public static final BiomeBase OCEAN = (new BiomeOcean(0)).b(112).a("Ocean").b(-1.0F, 0.4F);
    public static final BiomeBase PLAINS = (new BiomePlains(1)).b(9286496).a("Plains").a(0.8F, 0.4F);
    public static final BiomeBase DESERT = (new BiomeDesert(2)).b(16421912).a("Desert").g().a(2.0F, 0.0F).b(0.1F, 0.2F);
    public static final BiomeBase EXTREME_HILLS = (new BiomeBigHills(3)).b(6316128).a("Extreme Hills").b(0.2F, 1.3F).a(0.2F, 0.3F);
    public static final BiomeBase FOREST = (new BiomeForest(4)).b(353825).a("Forest").a(5159473).a(0.7F, 0.8F);
    public static final BiomeBase TAIGA = (new BiomeTaiga(5)).b(747097).a("Taiga").a(5159473).a(0.05F, 0.8F).b(0.1F, 0.4F);
    public static final BiomeBase SWAMPLAND = (new BiomeSwamp(6)).b(522674).a("Swampland").a(9154376).b(-0.2F, 0.1F).a(0.8F, 0.9F);
    public static final BiomeBase RIVER = (new BiomeRiver(7)).b(255).a("River").b(-0.5F, 0.0F);
    public static final BiomeBase HELL = (new BiomeHell(8)).b(16711680).a("Hell").g().a(2.0F, 0.0F);
    public static final BiomeBase SKY = (new BiomeTheEnd(9)).b(8421631).a("Sky").g();
    public static final BiomeBase FROZEN_OCEAN = (new BiomeOcean(10)).b(9474208).a("FrozenOcean").b(-1.0F, 0.5F).a(0.0F, 0.5F);
    public static final BiomeBase FROZEN_RIVER = (new BiomeRiver(11)).b(10526975).a("FrozenRiver").b(-0.5F, 0.0F).a(0.0F, 0.5F);
    public static final BiomeBase ICE_PLAINS = (new BiomeIcePlains(12)).b(16777215).a("Ice Plains").a(0.0F, 0.5F);
    public static final BiomeBase ICE_MOUNTAINS = (new BiomeIcePlains(13)).b(10526880).a("Ice Mountains").b(0.2F, 1.2F).a(0.0F, 0.5F);
    public static final BiomeBase MUSHROOM_ISLAND = (new BiomeMushrooms(14)).b(16711935).a("MushroomIsland").a(0.9F, 1.0F).b(0.2F, 1.0F);
    public static final BiomeBase MUSHROOM_SHORE = (new BiomeMushrooms(15)).b(10486015).a("MushroomIslandShore").a(0.9F, 1.0F).b(-1.0F, 0.1F);
    public static final BiomeBase BEACH = (new BiomeBeach(16)).b(16440917).a("Beach").a(0.8F, 0.4F).b(0.0F, 0.1F);
    public static final BiomeBase DESERT_HILLS = (new BiomeDesert(17)).b(13786898).a("DesertHills").g().a(2.0F, 0.0F).b(0.2F, 0.7F);
    public static final BiomeBase FOREST_HILLS = (new BiomeForest(18)).b(2250012).a("ForestHills").a(5159473).a(0.7F, 0.8F).b(0.2F, 0.6F);
    public static final BiomeBase TAIGA_HILLS = (new BiomeTaiga(19)).b(1456435).a("TaigaHills").a(5159473).a(0.05F, 0.8F).b(0.2F, 0.7F);
    public static final BiomeBase SMALL_MOUNTAINS = (new BiomeBigHills(20)).b(7501978).a("Extreme Hills Edge").b(0.2F, 0.8F).a(0.2F, 0.3F);
    public String w;
    public int x;
    public byte y;
    public byte z;
    public int A;
    public float B;
    public float C;
    public float D;
    public float E;
    public int F;
    public BiomeDecorator G;
    protected List H;
    protected List I;
    protected List J;
    private boolean P;
    private boolean Q;
    public final int id;
    protected WorldGenTrees L;
    protected WorldGenBigTree M;
    protected WorldGenForest N;
    protected WorldGenSwampTree O;

    protected BiomeBase(int i) {
        this.y = (byte) Block.GRASS.id;
        this.z = (byte) Block.DIRT.id;
        this.A = 5169201;
        this.B = 0.1F;
        this.C = 0.3F;
        this.D = 0.5F;
        this.E = 0.5F;
        this.F = 16777215;
        this.H = new ArrayList();
        this.I = new ArrayList();
        this.J = new ArrayList();
        this.Q = true;
        this.L = new WorldGenTrees(false);
        this.M = new WorldGenBigTree(false);
        this.N = new WorldGenForest(false);
        this.O = new WorldGenSwampTree();
        this.id = i;
        biomes[i] = this;
        this.G = this.a();
        this.I.add(new BiomeMeta(EntitySheep.class, 12, 4, 4));
        this.I.add(new BiomeMeta(EntityPig.class, 10, 4, 4));
        this.I.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
        this.I.add(new BiomeMeta(EntityCow.class, 8, 4, 4));
        this.H.add(new BiomeMeta(EntitySpider.class, 10, 4, 4));
        this.H.add(new BiomeMeta(EntityZombie.class, 10, 4, 4));
        this.H.add(new BiomeMeta(EntitySkeleton.class, 10, 4, 4));
        this.H.add(new BiomeMeta(EntityCreeper.class, 10, 4, 4));
        this.H.add(new BiomeMeta(EntitySlime.class, 10, 4, 4));
        this.H.add(new BiomeMeta(EntityEnderman.class, 1, 1, 4));
        this.J.add(new BiomeMeta(EntitySquid.class, 10, 4, 4));
    }

    protected BiomeDecorator a() {
        return new BiomeDecorator(this);
    }

    private BiomeBase a(float f, float f1) {
        if (f > 0.1F && f < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        } else {
            this.D = f;
            this.E = f1;
            return this;
        }
    }

    private BiomeBase b(float f, float f1) {
        this.B = f;
        this.C = f1;
        return this;
    }

    private BiomeBase g() {
        this.Q = false;
        return this;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(10) == 0 ? this.M : this.L);
    }

    protected BiomeBase a(String s) {
        this.w = s;
        return this;
    }

    protected BiomeBase a(int i) {
        this.A = i;
        return this;
    }

    protected BiomeBase b(int i) {
        this.x = i;
        return this;
    }

    public List getMobs(EnumCreatureType enumcreaturetype) {
        return enumcreaturetype == EnumCreatureType.MONSTER ? this.H : (enumcreaturetype == EnumCreatureType.CREATURE ? this.I : (enumcreaturetype == EnumCreatureType.WATER_CREATURE ? this.J : null));
    }

    public boolean b() {
        return this.P;
    }

    public boolean c() {
        return this.P ? false : this.Q;
    }

    public float d() {
        return 0.1F;
    }

    public final int e() {
        return (int) (this.E * 65536.0F);
    }

    public final int f() {
        return (int) (this.D * 65536.0F);
    }

    public void a(World world, Random random, int i, int j) {
        this.G.a(world, random, i, j);
    }
}
