package net.minecraft.server;

public class EntityZombie extends EntityMonster {

    public EntityZombie(World world) {
        super(world);
        this.aH = "/mob/zombie.png";
        this.bu = 0.5F;
        this.f = 5;
    }

    public void E() {
        if (this.l.b()) {
            float f = this.b(1.0F);

            if (f > 0.5F && this.l.g(MathHelper.b(this.p), MathHelper.b(this.q), MathHelper.b(this.r)) && this.W.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.Z = 300;
            }
        }

        super.E();
    }

    protected String d() {
        return "mob.zombie";
    }

    protected String e() {
        return "mob.zombiehurt";
    }

    protected String f() {
        return "mob.zombiedeath";
    }

    protected int g() {
        return Item.FEATHER.aW;
    }
}
