package net.minecraft.server;

public class PathfinderGoalJumpOnBlock extends PathfinderGoal {

    private final EntityOcelot a;
    private final float b;
    private int c = 0;
    private int h = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;

    public PathfinderGoalJumpOnBlock(EntityOcelot entityocelot, float f) {
        this.a = entityocelot;
        this.b = f;
        this.a(5);
    }

    public boolean a() {
        return this.a.isTamed() && !this.a.isSitting() && this.a.an().nextDouble() <= 0.006500000134110451D && this.f();
    }

    public boolean b() {
        return this.c <= this.d && this.h <= 60 && this.a(this.a.world, this.e, this.f, this.g);
    }

    public void c() {
        this.a.al().a((double) ((float) this.e) + 0.5D, (double) (this.f + 1), (double) ((float) this.g) + 0.5D, this.b);
        this.c = 0;
        this.h = 0;
        this.d = this.a.an().nextInt(this.a.an().nextInt(1200) + 1200) + 1200;
        this.a.C().a(false);
    }

    public void d() {
        this.a.setSitting(false);
    }

    public void e() {
        ++this.c;
        this.a.C().a(false);
        if (this.a.e((double) this.e, (double) (this.f + 1), (double) this.g) > 1.0D) {
            this.a.setSitting(false);
            this.a.al().a((double) ((float) this.e) + 0.5D, (double) (this.f + 1), (double) ((float) this.g) + 0.5D, this.b);
            ++this.h;
        } else if (!this.a.isSitting()) {
            this.a.setSitting(true);
        } else {
            --this.h;
        }
    }

    private boolean f() {
        int i = (int) this.a.locY;
        double d0 = 2.147483647E9D;

        for (int j = (int) this.a.locX - 8; (double) j < this.a.locX + 8.0D; ++j) {
            for (int k = (int) this.a.locZ - 8; (double) k < this.a.locZ + 8.0D; ++k) {
                if (this.a(this.a.world, j, i, k) && this.a.world.isEmpty(j, i + 1, k)) {
                    double d1 = this.a.e((double) j, (double) i, (double) k);

                    if (d1 < d0) {
                        this.e = j;
                        this.f = i;
                        this.g = k;
                        d0 = d1;
                    }
                }
            }
        }

        return d0 < 2.147483647E9D;
    }

    private boolean a(World world, int i, int j, int k) {
        int l = world.getTypeId(i, j, k);
        int i1 = world.getData(i, j, k);

        if (l == Block.CHEST.id) {
            TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i, j, k);

            if (tileentitychest.h < 1) {
                return true;
            }
        } else {
            if (l == Block.BURNING_FURNACE.id) {
                return true;
            }

            if (l == Block.BED.id && !BlockBed.d(i1)) {
                return true;
            }
        }

        return false;
    }
}
