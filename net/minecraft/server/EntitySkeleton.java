package net.minecraft.server;

public class EntitySkeleton extends EntityMonster {

    private static final ItemStack a = new ItemStack(Item.BOW, 1);

    public EntitySkeleton(World world) {
        super(world);
        this.aP = "/mob/skeleton.png";
    }

    protected String e() {
        return "mob.skeleton";
    }

    protected String f() {
        return "mob.skeletonhurt";
    }

    protected String g() {
        return "mob.skeletonhurt";
    }

    public void o() {
        if (this.l.b()) {
            float f = this.b(1.0F);

            if (f > 0.5F && this.l.i(MathHelper.b(this.p), MathHelper.b(this.q), MathHelper.b(this.r)) && this.W.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.Z = 300;
            }
        }

        super.o();
    }

    protected void a(Entity entity, float f) {
        if (f < 10.0F) {
            double d0 = entity.p - this.p;
            double d1 = entity.r - this.r;

            if (this.bf == 0) {
                EntityArrow entityarrow = new EntityArrow(this.l, this);

                ++entityarrow.q;
                double d2 = entity.q - 0.20000000298023224D - entityarrow.q;
                float f1 = MathHelper.a(d0 * d0 + d1 * d1) * 0.2F;

                this.l.a(this, "random.bow", 1.0F, 1.0F / (this.W.nextFloat() * 0.4F + 0.8F));
                this.l.a((Entity) entityarrow);
                entityarrow.a(d0, d2 + (double) f1, d1, 0.6F, 12.0F);
                this.bf = 30;
            }

            this.v = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.e = true;
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected int h() {
        return Item.ARROW.ba;
    }

    protected void g_() {
        int i = this.W.nextInt(3);

        int j;

        for (j = 0; j < i; ++j) {
            this.a(Item.ARROW.ba, 1);
        }

        i = this.W.nextInt(3);

        for (j = 0; j < i; ++j) {
            this.a(Item.BONE.ba, 1);
        }
    }
}
