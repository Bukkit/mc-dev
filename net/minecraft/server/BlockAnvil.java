package net.minecraft.server;

public class BlockAnvil extends BlockSand {

    public static final String[] a = new String[] { "intact", "slightlyDamaged", "veryDamaged"};
    public int b = 0;

    protected BlockAnvil(int i) {
        super(i, 215, Material.HEAVY);
        this.h(0);
        this.a(CreativeModeTab.c);
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public int a(int i, int j) {
        if (this.b == 3 && i == 1) {
            int k = j >> 2;

            switch (k) {
            case 1:
                return this.textureId + 1;

            case 2:
                return this.textureId + 16 + 1;

            default:
                return this.textureId + 16;
            }
        } else {
            return this.textureId;
        }
    }

    public int a(int i) {
        return super.a(i);
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = world.getData(i, j, k) >> 2;

        ++l;
        l %= 4;
        if (l == 0) {
            world.setData(i, j, k, 2 | i1 << 2);
        }

        if (l == 1) {
            world.setData(i, j, k, 3 | i1 << 2);
        }

        if (l == 2) {
            world.setData(i, j, k, 0 | i1 << 2);
        }

        if (l == 3) {
            world.setData(i, j, k, 1 | i1 << 2);
        }
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            entityhuman.openAnvil(i, j, k);
            return true;
        }
    }

    public int d() {
        return 35;
    }

    public int getDropData(int i) {
        return i >> 2;
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k) & 3;

        if (l != 3 && l != 1) {
            this.a(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
        }
    }

    protected void a(EntityFallingBlock entityfallingblock) {
        entityfallingblock.e(true);
    }

    public void a_(World world, int i, int j, int k, int l) {
        world.triggerEffect(1022, i, j, k, 0);
    }
}
