package net.minecraft.server;

import java.util.Random;

public class EntitySheep extends EntityAnimal {

    public static final float[][] a = new float[][] { { 1.0F, 1.0F, 1.0F}, { 0.95F, 0.7F, 0.2F}, { 0.9F, 0.5F, 0.85F}, { 0.6F, 0.7F, 0.95F}, { 0.9F, 0.9F, 0.2F}, { 0.5F, 0.8F, 0.1F}, { 0.95F, 0.7F, 0.8F}, { 0.3F, 0.3F, 0.3F}, { 0.6F, 0.6F, 0.6F}, { 0.3F, 0.6F, 0.7F}, { 0.7F, 0.4F, 0.9F}, { 0.2F, 0.4F, 0.8F}, { 0.5F, 0.4F, 0.3F}, { 0.4F, 0.5F, 0.2F}, { 0.8F, 0.3F, 0.3F}, { 0.1F, 0.1F, 0.1F}};

    public EntitySheep(World world) {
        super(world);
        this.aP = "/mob/sheep.png";
        this.a(0.9F, 1.3F);
    }

    protected void a() {
        super.a();
        this.af.a(16, new Byte((byte) 0));
    }

    public boolean a(Entity entity, int i) {
        if (!this.l.z && !this.f_() && entity instanceof EntityLiving) {
            this.a(true);
            int j = 1 + this.W.nextInt(3);

            for (int k = 0; k < j; ++k) {
                EntityItem entityitem = this.a(new ItemStack(Block.WOOL.bi, 1, this.e_()), 1.0F);

                entityitem.t += (double) (this.W.nextFloat() * 0.05F);
                entityitem.s += (double) ((this.W.nextFloat() - this.W.nextFloat()) * 0.1F);
                entityitem.u += (double) ((this.W.nextFloat() - this.W.nextFloat()) * 0.1F);
            }
        }

        return super.a(entity, i);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Sheared", this.f_());
        nbttagcompound.a("Color", (byte) this.e_());
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a(nbttagcompound.l("Sheared"));
        this.a(nbttagcompound.b("Color"));
    }

    protected String e() {
        return "mob.sheep";
    }

    protected String f() {
        return "mob.sheep";
    }

    protected String g() {
        return "mob.sheep";
    }

    public int e_() {
        return this.af.a(16) & 15;
    }

    public void a(int i) {
        byte b0 = this.af.a(16);

        this.af.b(16, Byte.valueOf((byte) (b0 & 240 | i & 15)));
    }

    public boolean f_() {
        return (this.af.a(16) & 16) != 0;
    }

    public void a(boolean flag) {
        byte b0 = this.af.a(16);

        if (flag) {
            this.af.b(16, Byte.valueOf((byte) (b0 | 16)));
        } else {
            this.af.b(16, Byte.valueOf((byte) (b0 & -17)));
        }
    }

    public static int a(Random random) {
        int i = random.nextInt(100);

        return i < 5 ? 15 : (i < 10 ? 7 : (i < 15 ? 8 : 0));
    }
}
