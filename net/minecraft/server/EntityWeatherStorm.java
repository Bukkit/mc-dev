package net.minecraft.server;

import java.util.List;

public class EntityWeatherStorm extends EntityWeather {

    private int b;
    public long a = 0L;
    private int c;

    public EntityWeatherStorm(World world, double d0, double d1, double d2) {
        super(world);
        this.setPositionRotation(d0, d1, d2, 0.0F, 0.0F);
        this.b = 2;
        this.a = this.random.nextLong();
        this.c = this.random.nextInt(3) + 1;
        if (world.spawnMonsters >= 2 && world.a(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2), 10)) {
            int i = MathHelper.floor(d0);
            int j = MathHelper.floor(d1);
            int k = MathHelper.floor(d2);

            if (world.getTypeId(i, j, k) == 0 && Block.FIRE.canPlace(world, i, j, k)) {
                world.setTypeId(i, j, k, Block.FIRE.id);
            }

            for (i = 0; i < 4; ++i) {
                j = MathHelper.floor(d0) + this.random.nextInt(3) - 1;
                k = MathHelper.floor(d1) + this.random.nextInt(3) - 1;
                int l = MathHelper.floor(d2) + this.random.nextInt(3) - 1;

                if (world.getTypeId(j, k, l) == 0 && Block.FIRE.canPlace(world, j, k, l)) {
                    world.setTypeId(j, k, l, Block.FIRE.id);
                }
            }
        }
    }

    public void o_() {
        super.o_();
        if (this.b == 2) {
            this.world.makeSound(this.locX, this.locY, this.locZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.random.nextFloat() * 0.2F);
            this.world.makeSound(this.locX, this.locY, this.locZ, "random.explode", 2.0F, 0.5F + this.random.nextFloat() * 0.2F);
        }

        --this.b;
        if (this.b < 0) {
            if (this.c == 0) {
                this.die();
            } else if (this.b < -this.random.nextInt(10)) {
                --this.c;
                this.b = 1;
                this.a = this.random.nextLong();
                if (this.world.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 10)) {
                    int i = MathHelper.floor(this.locX);
                    int j = MathHelper.floor(this.locY);
                    int k = MathHelper.floor(this.locZ);

                    if (this.world.getTypeId(i, j, k) == 0 && Block.FIRE.canPlace(this.world, i, j, k)) {
                        this.world.setTypeId(i, j, k, Block.FIRE.id);
                    }
                }
            }
        }

        if (this.b >= 0) {
            double d0 = 3.0D;
            List list = this.world.b((Entity) this, AxisAlignedBB.b(this.locX - d0, this.locY - d0, this.locZ - d0, this.locX + d0, this.locY + 6.0D + d0, this.locZ + d0));

            for (int l = 0; l < list.size(); ++l) {
                Entity entity = (Entity) list.get(l);

                entity.a(this);
            }

            this.world.n = 2;
        }
    }

    protected void b() {}

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}
}
