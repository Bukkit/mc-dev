package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class Block {

    public static final StepSound e = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound f = new StepSound("wood", 1.0F, 1.0F);
    public static final StepSound g = new StepSound("gravel", 1.0F, 1.0F);
    public static final StepSound h = new StepSound("grass", 1.0F, 1.0F);
    public static final StepSound i = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound j = new StepSound("stone", 1.0F, 1.5F);
    public static final StepSound k = new StepSoundStone("stone", 1.0F, 1.0F);
    public static final StepSound l = new StepSound("cloth", 1.0F, 1.0F);
    public static final StepSound m = new StepSoundSand("sand", 1.0F, 1.0F);
    public static final Block[] n = new Block[256];
    public static final boolean[] o = new boolean[256];
    public static final boolean[] p = new boolean[256];
    public static final boolean[] q = new boolean[256];
    public static final int[] r = new int[256];
    public static final boolean[] s = new boolean[256];
    public static final int[] t = new int[256];
    public static final Block STONE = (new BlockStone(1, 1)).c(1.5F).b(10.0F).a(i);
    public static final BlockGrass GRASS = (BlockGrass) (new BlockGrass(2)).c(0.6F).a(h);
    public static final Block DIRT = (new BlockDirt(3, 2)).c(0.5F).a(g);
    public static final Block COBBLESTONE = (new Block(4, 16, Material.d)).c(2.0F).b(10.0F).a(i);
    public static final Block WOOD = (new Block(5, 4, Material.c)).c(2.0F).b(5.0F).a(f);
    public static final Block SAPLING = (new BlockSapling(6, 15)).c(0.0F).a(h);
    public static final Block BEDROCK = (new Block(7, 17, Material.d)).c(-1.0F).b(6000000.0F).a(i);
    public static final Block WATER = (new BlockFlowing(8, Material.f)).c(100.0F).c(3);
    public static final Block STATIONARY_WATER = (new BlockStationary(9, Material.f)).c(100.0F).c(3);
    public static final Block LAVA = (new BlockFlowing(10, Material.g)).c(0.0F).a(1.0F).c(255);
    public static final Block STATIONARY_LAVA = (new BlockStationary(11, Material.g)).c(100.0F).a(1.0F).c(255);
    public static final Block SAND = (new BlockSand(12, 18)).c(0.5F).a(m);
    public static final Block GRAVEL = (new BlockGravel(13, 19)).c(0.6F).a(g);
    public static final Block GOLD_ORE = (new BlockOre(14, 32)).c(3.0F).b(5.0F).a(i);
    public static final Block IRON_ORE = (new BlockOre(15, 33)).c(3.0F).b(5.0F).a(i);
    public static final Block COAL_ORE = (new BlockOre(16, 34)).c(3.0F).b(5.0F).a(i);
    public static final Block LOG = (new BlockLog(17)).c(2.0F).a(f);
    public static final BlockLeaves LEAVES = (BlockLeaves) (new BlockLeaves(18, 52)).c(0.2F).c(1).a(h);
    public static final Block SPONGE = (new BlockSponge(19)).c(0.6F).a(h);
    public static final Block GLASS = (new BlockGlass(20, 49, Material.o, false)).c(0.3F).a(k);
    public static final Block O = null;
    public static final Block P = null;
    public static final Block Q = null;
    public static final Block R = null;
    public static final Block S = null;
    public static final Block T = null;
    public static final Block U = null;
    public static final Block V = null;
    public static final Block W = null;
    public static final Block X = null;
    public static final Block Y = null;
    public static final Block Z = null;
    public static final Block aa = null;
    public static final Block ab = null;
    public static final Block WOOL = (new Block(35, 64, Material.k)).c(0.8F).a(l);
    public static final Block ad = null;
    public static final BlockFlower YELLOW_FLOWER = (BlockFlower) (new BlockFlower(37, 13)).c(0.0F).a(h);
    public static final BlockFlower RED_ROSE = (BlockFlower) (new BlockFlower(38, 12)).c(0.0F).a(h);
    public static final BlockFlower BROWN_MUSHROOM = (BlockFlower) (new BlockMushroom(39, 29)).c(0.0F).a(h).a(0.125F);
    public static final BlockFlower RED_MUSHROOM = (BlockFlower) (new BlockMushroom(40, 28)).c(0.0F).a(h);
    public static final Block GOLD_BLOCK = (new BlockOreBlock(41, 39)).c(3.0F).b(10.0F).a(j);
    public static final Block IRON_BLOCK = (new BlockOreBlock(42, 38)).c(5.0F).b(10.0F).a(j);
    public static final Block DOUBLE_STEP = (new BlockStep(43, true)).c(2.0F).b(10.0F).a(i);
    public static final Block STEP = (new BlockStep(44, false)).c(2.0F).b(10.0F).a(i);
    public static final Block BRICK = (new Block(45, 7, Material.d)).c(2.0F).b(10.0F).a(i);
    public static final Block TNT = (new BlockTNT(46, 8)).c(0.0F).a(h);
    public static final Block BOOKSHELF = (new BlockBookshelf(47, 35)).c(1.5F).a(f);
    public static final Block MOSSY_COBBLESTONE = (new Block(48, 36, Material.d)).c(2.0F).b(10.0F).a(i);
    public static final Block OBSIDIAN = (new BlockObsidian(49, 37)).c(10.0F).b(2000.0F).a(i);
    public static final Block TORCH = (new BlockTorch(50, 80)).c(0.0F).a(0.9375F).a(f);
    public static final BlockFire FIRE = (BlockFire) ((BlockFire) (new BlockFire(51, 31)).c(0.0F).a(1.0F).a(f));
    public static final Block MOB_SPAWNER = (new BlockMobSpawner(52, 65)).c(5.0F).a(j);
    public static final Block WOOD_STAIRS = new BlockStairs(53, WOOD);
    public static final Block CHEST = (new BlockChest(54)).c(2.5F).a(f);
    public static final Block REDSTONE_WIRE = (new BlockRedstoneWire(55, 84)).c(0.0F).a(e);
    public static final Block DIAMOND_ORE = (new BlockOre(56, 50)).c(3.0F).b(5.0F).a(i);
    public static final Block DIAMOND_BLOCK = (new BlockOreBlock(57, 40)).c(5.0F).b(10.0F).a(j);
    public static final Block WORKBENCH = (new BlockWorkbench(58)).c(2.5F).a(f);
    public static final Block CROPS = (new BlockCrops(59, 88)).c(0.0F).a(h);
    public static final Block SOIL = (new BlockSoil(60)).c(0.6F).a(g);
    public static final Block FURNACE = (new BlockFurnace(61, false)).c(3.5F).a(i);
    public static final Block BURNING_FURNACE = (new BlockFurnace(62, true)).c(3.5F).a(i).a(0.875F);
    public static final Block SIGN_POST = (new BlockSign(63, TileEntitySign.class, true)).c(1.0F).a(f);
    public static final Block WOODEN_DOOR = (new BlockDoor(64, Material.c)).c(3.0F).a(f);
    public static final Block LADDER = (new BlockLadder(65, 83)).c(0.4F).a(f);
    public static final Block RAILS = (new BlockMinecartTrack(66, 128)).c(0.7F).a(j);
    public static final Block COBBLESTONE_STAIRS = new BlockStairs(67, COBBLESTONE);
    public static final Block WALL_SIGN = (new BlockSign(68, TileEntitySign.class, false)).c(1.0F).a(f);
    public static final Block LEVER = (new BlockLever(69, 96)).c(0.5F).a(f);
    public static final Block STONE_PLATE = (new BlockPressurePlate(70, STONE.bh, EnumMobType.MOBS)).c(0.5F).a(i);
    public static final Block IRON_DOOR_BLOCK = (new BlockDoor(71, Material.e)).c(5.0F).a(j);
    public static final Block WOOD_PLATE = (new BlockPressurePlate(72, WOOD.bh, EnumMobType.EVERYTHING)).c(0.5F).a(f);
    public static final Block REDSTONE_ORE = (new BlockRedstoneOre(73, 51, false)).c(3.0F).b(5.0F).a(i);
    public static final Block GLOWING_REDSTONE_ORE = (new BlockRedstoneOre(74, 51, true)).a(0.625F).c(3.0F).b(5.0F).a(i);
    public static final Block REDSTONE_TORCH_OFF = (new BlockRedstoneTorch(75, 115, false)).c(0.0F).a(f);
    public static final Block REDSTONE_TORCH_ON = (new BlockRedstoneTorch(76, 99, true)).c(0.0F).a(0.5F).a(f);
    public static final Block STONE_BUTTON = (new BlockButton(77, STONE.bh)).c(0.5F).a(i);
    public static final Block SNOW = (new BlockSnow(78, 66)).c(0.1F).a(l);
    public static final Block ICE = (new BlockIce(79, 67)).c(0.5F).c(3).a(k);
    public static final Block SNOW_BLOCK = (new BlockSnowBlock(80, 66)).c(0.2F).a(l);
    public static final Block CACTUS = (new BlockCactus(81, 70)).c(0.4F).a(l);
    public static final Block CLAY = (new BlockClay(82, 72)).c(0.6F).a(g);
    public static final Block SUGAR_CANE_BLOCK = (new BlockReed(83, 73)).c(0.0F).a(h);
    public static final Block JUKEBOX = (new BlockJukeBox(84, 74)).c(2.0F).b(10.0F).a(i);
    public static final Block FENCE = (new BlockFence(85, 4)).c(2.0F).b(5.0F).a(f);
    public static final Block PUMPKIN = (new BlockPumpkin(86, 102, false)).c(1.0F).a(f);
    public static final Block NETHERRACK = (new BlockBloodStone(87, 103)).c(0.4F).a(i);
    public static final Block SOUL_SAND = (new BlockSlowSand(88, 104)).c(0.5F).a(m);
    public static final Block GLOWSTONE = (new BlockLightStone(89, 105, Material.o)).c(0.3F).a(k).a(1.0F);
    public static final BlockPortal PORTAL = (BlockPortal) ((BlockPortal) (new BlockPortal(90, 14)).c(-1.0F).a(k).a(0.75F));
    public static final Block JACK_O_LANTERN = (new BlockPumpkin(91, 102, true)).c(1.0F).a(f).a(1.0F);
    public int bh;
    public final int bi;
    protected float bj;
    protected float bk;
    public double bl;
    public double bm;
    public double bn;
    public double bo;
    public double bp;
    public double bq;
    public StepSound br;
    public float bs;
    public final Material bt;
    public float bu;

    protected Block(int i, Material material) {
        this.br = e;
        this.bs = 1.0F;
        this.bu = 0.6F;
        if (n[i] != null) {
            throw new IllegalArgumentException("Slot " + i + " is already occupied by " + n[i] + " when adding " + this);
        } else {
            this.bt = material;
            n[i] = this;
            this.bi = i;
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            p[i] = this.a();
            r[i] = this.a() ? 255 : 0;
            s[i] = this.e();
            q[i] = false;
        }
    }

    protected Block(int i, int j, Material material) {
        this(i, material);
        this.bh = j;
    }

    protected Block a(StepSound stepsound) {
        this.br = stepsound;
        return this;
    }

    protected Block c(int i) {
        r[this.bi] = i;
        return this;
    }

    protected Block a(float f) {
        t[this.bi] = (int) (15.0F * f);
        return this;
    }

    protected Block b(float f) {
        this.bk = f * 3.0F;
        return this;
    }

    private boolean e() {
        return false;
    }

    protected Block c(float f) {
        this.bj = f;
        if (this.bk < f * 5.0F) {
            this.bk = f * 5.0F;
        }

        return this;
    }

    protected void a(boolean flag) {
        o[this.bi] = flag;
    }

    public void a(float f, float f1, float f2, float f3, float f4, float f5) {
        this.bl = (double) f;
        this.bm = (double) f1;
        this.bn = (double) f2;
        this.bo = (double) f3;
        this.bp = (double) f4;
        this.bq = (double) f5;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return l == 0 && this.bm > 0.0D ? true : (l == 1 && this.bp < 1.0D ? true : (l == 2 && this.bn > 0.0D ? true : (l == 3 && this.bq < 1.0D ? true : (l == 4 && this.bl > 0.0D ? true : (l == 5 && this.bo < 1.0D ? true : !iblockaccess.d(i, j, k))))));
    }

    public int a(int i) {
        return this.bh;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        AxisAlignedBB axisalignedbb1 = this.d(world, i, j, k);

        if (axisalignedbb1 != null && axisalignedbb.a(axisalignedbb1)) {
            arraylist.add(axisalignedbb1);
        }
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return AxisAlignedBB.b((double) i + this.bl, (double) j + this.bm, (double) k + this.bn, (double) i + this.bo, (double) j + this.bp, (double) k + this.bq);
    }

    public boolean a() {
        return true;
    }

    public boolean a(int i, boolean flag) {
        return this.d();
    }

    public boolean d() {
        return true;
    }

    public void a(World world, int i, int j, int k, Random random) {}

    public void a(World world, int i, int j, int k, int l) {}

    public void b(World world, int i, int j, int k, int l) {}

    public int b() {
        return 10;
    }

    public void e(World world, int i, int j, int k) {}

    public void b(World world, int i, int j, int k) {}

    public int a(Random random) {
        return 1;
    }

    public int a(int i, Random random) {
        return this.bi;
    }

    public float a(EntityHuman entityhuman) {
        return this.bj < 0.0F ? 0.0F : (!entityhuman.b(this) ? 1.0F / this.bj / 100.0F : entityhuman.a(this) / this.bj / 30.0F);
    }

    public void a_(World world, int i, int j, int k, int l) {
        this.a(world, i, j, k, l, 1.0F);
    }

    public void a(World world, int i, int j, int k, int l, float f) {
        if (!world.z) {
            int i1 = this.a(world.l);

            for (int j1 = 0; j1 < i1; ++j1) {
                if (world.l.nextFloat() <= f) {
                    int k1 = this.a(l, world.l);

                    if (k1 > 0) {
                        float f1 = 0.7F;
                        double d0 = (double) (world.l.nextFloat() * f1) + (double) (1.0F - f1) * 0.5D;
                        double d1 = (double) (world.l.nextFloat() * f1) + (double) (1.0F - f1) * 0.5D;
                        double d2 = (double) (world.l.nextFloat() * f1) + (double) (1.0F - f1) * 0.5D;
                        EntityItem entityitem = new EntityItem(world, (double) i + d0, (double) j + d1, (double) k + d2, new ItemStack(k1));

                        entityitem.c = 10;
                        world.a((Entity) entityitem);
                    }
                }
            }
        }
    }

    public float a(Entity entity) {
        return this.bk / 5.0F;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        this.a((IBlockAccess) world, i, j, k);
        vec3d = vec3d.c((double) (-i), (double) (-j), (double) (-k));
        vec3d1 = vec3d1.c((double) (-i), (double) (-j), (double) (-k));
        Vec3D vec3d2 = vec3d.a(vec3d1, this.bl);
        Vec3D vec3d3 = vec3d.a(vec3d1, this.bo);
        Vec3D vec3d4 = vec3d.b(vec3d1, this.bm);
        Vec3D vec3d5 = vec3d.b(vec3d1, this.bp);
        Vec3D vec3d6 = vec3d.c(vec3d1, this.bn);
        Vec3D vec3d7 = vec3d.c(vec3d1, this.bq);

        if (!this.a(vec3d2)) {
            vec3d2 = null;
        }

        if (!this.a(vec3d3)) {
            vec3d3 = null;
        }

        if (!this.b(vec3d4)) {
            vec3d4 = null;
        }

        if (!this.b(vec3d5)) {
            vec3d5 = null;
        }

        if (!this.c(vec3d6)) {
            vec3d6 = null;
        }

        if (!this.c(vec3d7)) {
            vec3d7 = null;
        }

        Vec3D vec3d8 = null;

        if (vec3d2 != null && (vec3d8 == null || vec3d.a(vec3d2) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d2;
        }

        if (vec3d3 != null && (vec3d8 == null || vec3d.a(vec3d3) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d3;
        }

        if (vec3d4 != null && (vec3d8 == null || vec3d.a(vec3d4) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d4;
        }

        if (vec3d5 != null && (vec3d8 == null || vec3d.a(vec3d5) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d5;
        }

        if (vec3d6 != null && (vec3d8 == null || vec3d.a(vec3d6) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d6;
        }

        if (vec3d7 != null && (vec3d8 == null || vec3d.a(vec3d7) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d7;
        }

        if (vec3d8 == null) {
            return null;
        } else {
            byte b0 = -1;

            if (vec3d8 == vec3d2) {
                b0 = 4;
            }

            if (vec3d8 == vec3d3) {
                b0 = 5;
            }

            if (vec3d8 == vec3d4) {
                b0 = 0;
            }

            if (vec3d8 == vec3d5) {
                b0 = 1;
            }

            if (vec3d8 == vec3d6) {
                b0 = 2;
            }

            if (vec3d8 == vec3d7) {
                b0 = 3;
            }

            return new MovingObjectPosition(i, j, k, b0, vec3d8.c((double) i, (double) j, (double) k));
        }
    }

    private boolean a(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.b >= this.bm && vec3d.b <= this.bp && vec3d.c >= this.bn && vec3d.c <= this.bq;
    }

    private boolean b(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.bl && vec3d.a <= this.bo && vec3d.c >= this.bn && vec3d.c <= this.bq;
    }

    private boolean c(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.bl && vec3d.a <= this.bo && vec3d.b >= this.bm && vec3d.b <= this.bp;
    }

    public void c(World world, int i, int j, int k) {}

    public boolean a(World world, int i, int j, int k) {
        int l = world.a(i, j, k);

        return l == 0 || n[l].bt.d();
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        return false;
    }

    public void b(World world, int i, int j, int k, Entity entity) {}

    public void c(World world, int i, int j, int k, int l) {}

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {}

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {}

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {}

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void a(World world, int i, int j, int k, Entity entity) {}

    public boolean d(World world, int i, int j, int k, int l) {
        return false;
    }

    public boolean f(World world, int i, int j, int k) {
        return true;
    }

    public void a(World world, int i, int j, int k, EntityLiving entityliving) {}

    static {
        for (int i = 0; i < 256; ++i) {
            if (n[i] != null) {
                Item.c[i] = new ItemBlock(i - 256);
            }
        }
    }
}
