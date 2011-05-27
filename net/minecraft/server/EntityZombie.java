package net.minecraft.server;

public class EntityZombie extends EntityMonster {

    public EntityZombie(World world) {
        super(world);
        this.texture = "/mob/zombie.png";
        this.az = 0.5F;
        this.c = 5;
    }

    public void q() {
        if (this.world.c()) {
            float f = this.c(1.0F);

            if (f > 0.5F && this.world.i(MathHelper.b(this.locX), MathHelper.b(this.locY), MathHelper.b(this.locZ)) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.fireTicks = 300;
            }
        }

        super.q();
    }

    protected String e() {
        return "mob.zombie";
    }

    protected String f() {
        return "mob.zombiehurt";
    }

    protected String g() {
        return "mob.zombiedeath";
    }

    protected int h() {
        return Item.FEATHER.id;
    }
}
