package net.minecraft.server;

public class EntitySlime extends EntityLiving implements IMonster {

    public float a;
    public float b;
    private int size = 0;

    public EntitySlime(World world) {
        super(world);
        this.texture = "/mob/slime.png";
        int i = 1 << this.random.nextInt(3);

        this.height = 0.0F;
        this.size = this.random.nextInt(20) + 10;
        this.setSize(i);
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, new Byte((byte) 1));
    }

    public void setSize(int i) {
        this.datawatcher.b(16, new Byte((byte) i));
        this.b(0.6F * (float) i, 0.6F * (float) i);
        this.health = i * i;
        this.setPosition(this.locX, this.locY, this.locZ);
    }

    public int m() {
        return this.datawatcher.a(16);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Size", this.m() - 1);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.setSize(nbttagcompound.e("Size") + 1);
    }

    public void f_() {
        this.b = this.a;
        boolean flag = this.onGround;

        super.f_();
        if (this.onGround && !flag) {
            int i = this.m();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;

                this.world.a("slime", this.locX + (double) f2, this.boundingBox.b, this.locZ + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (i > 2) {
                this.world.makeSound(this, "mob.slime", this.i(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.a = -0.5F;
        }

        this.a *= 0.6F;
    }

    protected void c_() {
        EntityHuman entityhuman = this.world.a(this, 16.0D);

        if (entityhuman != null) {
            this.a(entityhuman, 10.0F, 20.0F);
        }

        if (this.onGround && this.size-- <= 0) {
            this.size = this.random.nextInt(20) + 10;
            if (entityhuman != null) {
                this.size /= 3;
            }

            this.ax = true;
            if (this.m() > 1) {
                this.world.makeSound(this, "mob.slime", this.i(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.a = 1.0F;
            this.au = 1.0F - this.random.nextFloat() * 2.0F;
            this.av = (float) (1 * this.m());
        } else {
            this.ax = false;
            if (this.onGround) {
                this.au = this.av = 0.0F;
            }
        }
    }

    public void die() {
        int i = this.m();

        if (!this.world.isStatic && i > 1 && this.health == 0) {
            for (int j = 0; j < 4; ++j) {
                float f = ((float) (j % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (j / 2) - 0.5F) * (float) i / 4.0F;
                EntitySlime entityslime = new EntitySlime(this.world);

                entityslime.setSize(i / 2);
                entityslime.setPositionRotation(this.locX + (double) f, this.locY + 0.5D, this.locZ + (double) f1, this.random.nextFloat() * 360.0F, 0.0F);
                this.world.addEntity(entityslime);
            }
        }

        super.die();
    }

    public void b(EntityHuman entityhuman) {
        int i = this.m();

        if (i > 1 && this.e(entityhuman) && (double) this.f(entityhuman) < 0.6D * (double) i && entityhuman.damageEntity(this, i)) {
            this.world.makeSound(this, "mob.slimeattack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected String f() {
        return "mob.slime";
    }

    protected String g() {
        return "mob.slime";
    }

    protected int h() {
        return this.m() == 1 ? Item.SLIME_BALL.id : 0;
    }

    public boolean b() {
        Chunk chunk = this.world.b(MathHelper.floor(this.locX), MathHelper.floor(this.locZ));

        return (this.m() == 1 || this.world.spawnMonsters > 0) && this.random.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.locY < 16.0D;
    }

    protected float i() {
        return 0.6F;
    }
}
