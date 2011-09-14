package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BiomeBase {

    public static final BiomeBase[] a = new BiomeBase[256];
    public static final BiomeBase OCEAN = (new BiomeOcean(0)).b(112).a("Ocean").b(-1.0F, 0.5F);
    public static final BiomeBase PLAINS = (new BiomePlains(1)).b(9286496).a("Plains").a(0.8F, 0.4F);
    public static final BiomeBase DESERT = (new BiomeDesert(2)).b(16421912).a("Desert").g().a(2.0F, 0.0F).b(0.1F, 0.2F);
    public static final BiomeBase EXTREME_HILLS = (new BiomeBigHills(3)).b(6316128).a("Extreme Hills").b(0.2F, 1.8F).a(0.2F, 0.3F);
    public static final BiomeBase FOREST = (new BiomeForest(4)).b(353825).a("Forest").a(5159473).a(0.7F, 0.8F);
    public static final BiomeBase TAIGA = (new BiomeTaiga(5)).b(747097).a("Taiga").a(5159473).a(0.3F, 0.8F).b(0.1F, 0.4F);
    public static final BiomeBase SWAMPLAND = (new BiomeSwamp(6)).b(522674).a("Swampland").a(9154376).b(-0.2F, 0.1F).a(0.8F, 0.9F);
    public static final BiomeBase RIVER = (new BiomeRiver(7)).b(255).a("River").b(-0.5F, 0.0F);
    public static final BiomeBase HELL = (new BiomeHell(8)).b(16711680).a("Hell").g();
    public static final BiomeBase SKY = (new BiomeSky(9)).b(8421631).a("Sky").g();
    public String l;
    public int m;
    public byte n;
    public byte o;
    public int p;
    public float q;
    public float r;
    public float s;
    public float t;
    public BiomeDecorator u;
    protected List v;
    protected List w;
    protected List x;
    private boolean D;
    private boolean E;
    public final int y;
    protected WorldGenTrees z;
    protected WorldGenBigTree A;
    protected WorldGenForest B;
    protected WorldGenSwampTree C;

    protected BiomeBase(int i) {
        this.n = (byte) Block.GRASS.id;
        this.o = (byte) Block.DIRT.id;
        this.p = 5169201;
        this.q = 0.1F;
        this.r = 0.3F;
        this.s = 0.5F;
        this.t = 0.5F;
        this.v = new ArrayList();
        this.w = new ArrayList();
        this.x = new ArrayList();
        this.E = true;
        this.z = new WorldGenTrees();
        this.A = new WorldGenBigTree();
        this.B = new WorldGenForest();
        this.C = new WorldGenSwampTree();
        this.y = i;
        a[i] = this;
        this.u = this.a();
        this.w.add(new BiomeMeta(EntitySheep.class, 12, 4, 4));
        this.w.add(new BiomeMeta(EntityPig.class, 10, 4, 4));
        this.w.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
        this.w.add(new BiomeMeta(EntityCow.class, 8, 4, 4));
        this.v.add(new BiomeMeta(EntitySpider.class, 10, 4, 4));
        this.v.add(new BiomeMeta(EntityZombie.class, 10, 4, 4));
        this.v.add(new BiomeMeta(EntitySkeleton.class, 10, 4, 4));
        this.v.add(new BiomeMeta(EntityCreeper.class, 10, 4, 4));
        this.v.add(new BiomeMeta(EntitySlime.class, 10, 4, 4));
        this.v.add(new BiomeMeta(EntityEnderman.class, 2, 4, 4));
        this.x.add(new BiomeMeta(EntitySquid.class, 10, 4, 4));
    }

    protected BiomeDecorator a() {
        return new BiomeDecorator(this);
    }

    private BiomeBase a(float f, float f1) {
        this.s = f;
        this.t = f1;
        return this;
    }

    private BiomeBase b(float f, float f1) {
        this.q = f;
        this.r = f1;
        return this;
    }

    private BiomeBase g() {
        this.E = false;
        return this;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(10) == 0 ? this.A : this.z);
    }

    protected BiomeBase a(String s) {
        this.l = s;
        return this;
    }

    protected BiomeBase a(int i) {
        this.p = i;
        return this;
    }

    protected BiomeBase b(int i) {
        this.m = i;
        return this;
    }

    public List a(EnumCreatureType enumcreaturetype) {
        return enumcreaturetype == EnumCreatureType.MONSTER ? this.v : (enumcreaturetype == EnumCreatureType.CREATURE ? this.w : (enumcreaturetype == EnumCreatureType.WATER_CREATURE ? this.x : null));
    }

    public boolean b() {
        return this.D;
    }

    public boolean c() {
        return this.D ? false : this.E;
    }

    public float d() {
        return 0.1F;
    }

    public final int e() {
        return (int) (this.t * 65536.0F);
    }

    public final int f() {
        return (int) (this.s * 65536.0F);
    }

    public void a(World world, Random random, int i, int j) {
        this.u.a(world, random, i, j);
    }
}
