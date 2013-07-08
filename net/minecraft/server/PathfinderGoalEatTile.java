package net.minecraft.server;

public class PathfinderGoalEatTile extends PathfinderGoal {

    private EntityInsentient b;
    private World c;
    int a;

    public PathfinderGoalEatTile(EntityInsentient entityinsentient) {
        this.b = entityinsentient;
        this.c = entityinsentient.world;
        this.a(7);
    }

    public boolean a() {
        if (this.b.aC().nextInt(this.b.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            int i = MathHelper.floor(this.b.locX);
            int j = MathHelper.floor(this.b.locY);
            int k = MathHelper.floor(this.b.locZ);

            return this.c.getTypeId(i, j, k) == Block.LONG_GRASS.id && this.c.getData(i, j, k) == 1 ? true : this.c.getTypeId(i, j - 1, k) == Block.GRASS.id;
        }
    }

    public void c() {
        this.a = 40;
        this.c.broadcastEntityEffect(this.b, (byte) 10);
        this.b.getNavigation().h();
    }

    public void d() {
        this.a = 0;
    }

    public boolean b() {
        return this.a > 0;
    }

    public int f() {
        return this.a;
    }

    public void e() {
        this.a = Math.max(0, this.a - 1);
        if (this.a == 4) {
            int i = MathHelper.floor(this.b.locX);
            int j = MathHelper.floor(this.b.locY);
            int k = MathHelper.floor(this.b.locZ);

            if (this.c.getTypeId(i, j, k) == Block.LONG_GRASS.id) {
                this.c.setAir(i, j, k, false);
                this.b.n();
            } else if (this.c.getTypeId(i, j - 1, k) == Block.GRASS.id) {
                this.c.triggerEffect(2001, i, j - 1, k, Block.GRASS.id);
                this.c.setTypeIdAndData(i, j - 1, k, Block.DIRT.id, 0, 2);
                this.b.n();
            }
        }
    }
}
