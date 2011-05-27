package net.minecraft.server;

public class EntitySkeleton extends EntityMonster {

    private static final ItemStack a = new ItemStack(Item.BOW, 1);

    public EntitySkeleton(World world) {
        super(world);
        this.texture = "/mob/skeleton.png";
    }

    protected String g() {
        return "mob.skeleton";
    }

    protected String h() {
        return "mob.skeletonhurt";
    }

    protected String i() {
        return "mob.skeletonhurt";
    }

    public void u() {
        if (this.world.d()) {
            float f = this.c(1.0F);

            if (f > 0.5F && this.world.isChunkLoaded(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.fireTicks = 300;
            }
        }

        super.u();
    }

    protected void a(Entity entity, float f) {
        if (f < 10.0F) {
            double d0 = entity.locX - this.locX;
            double d1 = entity.locZ - this.locZ;

            if (this.attackTicks == 0) {
                EntityArrow entityarrow = new EntityArrow(this.world, this);

                ++entityarrow.locY;
                double d2 = entity.locY + (double) entity.s() - 0.20000000298023224D - entityarrow.locY;
                float f1 = MathHelper.a(d0 * d0 + d1 * d1) * 0.2F;

                this.world.makeSound(this, "random.bow", 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
                this.world.addEntity(entityarrow);
                entityarrow.a(d0, d2 + (double) f1, d1, 0.6F, 12.0F);
                this.attackTicks = 30;
            }

            this.yaw = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.e = true;
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected int j() {
        return Item.ARROW.id;
    }

    protected void r() {
        int i = this.random.nextInt(3);

        int j;

        for (j = 0; j < i; ++j) {
            this.b(Item.ARROW.id, 1);
        }

        i = this.random.nextInt(3);

        for (j = 0; j < i; ++j) {
            this.b(Item.BONE.id, 1);
        }
    }
}
