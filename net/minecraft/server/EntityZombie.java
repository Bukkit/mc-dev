package net.minecraft.server;

public class EntityZombie extends EntityMonster {

    public EntityZombie(World world) {
        super(world);
        this.aC = "/mob/zombie.png";
        this.bi = 0.5F;
        this.af = 5;
    }

    public void y() {
        if (this.h.a()) {
            float f = this.b(1.0F);

            if (f > 0.5F && this.h.g(MathHelper.b(this.l), MathHelper.b(this.m), MathHelper.b(this.n)) && this.R.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.U = 300;
            }
        }

        super.y();
    }

    protected String c() {
        return "mob.zombie";
    }

    protected String d() {
        return "mob.zombiehurt";
    }

    protected String e() {
        return "mob.zombiedeath";
    }

    protected int g() {
        return Item.FEATHER.aS;
    }
}
