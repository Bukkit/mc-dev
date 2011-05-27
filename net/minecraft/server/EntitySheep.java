package net.minecraft.server;

public class EntitySheep extends EntityAnimal {

    public boolean a = false;

    public EntitySheep(World world) {
        super(world);
        this.aC = "/mob/sheep.png";
        this.a(0.9F, 1.3F);
    }

    public boolean a(Entity entity, int i) {
        if (!this.a && entity instanceof EntityLiving) {
            this.a = true;
            int j = 1 + this.R.nextInt(3);

            for (int k = 0; k < j; ++k) {
                EntityItem entityitem = this.a(Block.WOOL.bc, 1, 1.0F);

                entityitem.p += (double) (this.R.nextFloat() * 0.05F);
                entityitem.o += (double) ((this.R.nextFloat() - this.R.nextFloat()) * 0.1F);
                entityitem.q += (double) ((this.R.nextFloat() - this.R.nextFloat()) * 0.1F);
            }
        }

        return super.a(entity, i);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Sheared", this.a);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.l("Sheared");
    }

    protected String c() {
        return "mob.sheep";
    }

    protected String d() {
        return "mob.sheep";
    }

    protected String e() {
        return "mob.sheep";
    }
}
