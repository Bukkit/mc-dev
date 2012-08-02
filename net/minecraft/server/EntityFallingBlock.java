package net.minecraft.server;

public class EntityFallingBlock extends Entity {

    public int id;
    public int data;
    public int c;
    public boolean dropItem;

    public EntityFallingBlock(World world) {
        super(world);
        this.c = 0;
        this.dropItem = true;
    }

    public EntityFallingBlock(World world, double d0, double d1, double d2, int i) {
        this(world, d0, d1, d2, i, 0);
    }

    public EntityFallingBlock(World world, double d0, double d1, double d2, int i, int j) {
        super(world);
        this.c = 0;
        this.dropItem = true;
        this.id = i;
        this.data = j;
        this.m = true;
        this.a(0.98F, 0.98F);
        this.height = this.length / 2.0F;
        this.setPosition(d0, d1, d2);
        this.motX = 0.0D;
        this.motY = 0.0D;
        this.motZ = 0.0D;
        this.lastX = d0;
        this.lastY = d1;
        this.lastZ = d2;
    }

    protected boolean e_() {
        return false;
    }

    protected void a() {}

    public boolean L() {
        return !this.dead;
    }

    public void h_() {
        if (this.id == 0) {
            this.die();
        } else {
            this.lastX = this.locX;
            this.lastY = this.locY;
            this.lastZ = this.locZ;
            ++this.c;
            this.motY -= 0.03999999910593033D;
            this.move(this.motX, this.motY, this.motZ);
            this.motX *= 0.9800000190734863D;
            this.motY *= 0.9800000190734863D;
            this.motZ *= 0.9800000190734863D;
            if (!this.world.isStatic) {
                int i = MathHelper.floor(this.locX);
                int j = MathHelper.floor(this.locY);
                int k = MathHelper.floor(this.locZ);

                if (this.c == 1) {
                    if (this.c == 1 && this.world.getTypeId(i, j, k) == this.id) {
                        this.world.setTypeId(i, j, k, 0);
                    } else {
                        this.die();
                    }
                }

                if (this.onGround) {
                    this.motX *= 0.699999988079071D;
                    this.motZ *= 0.699999988079071D;
                    this.motY *= -0.5D;
                    if (this.world.getTypeId(i, j, k) != Block.PISTON_MOVING.id) {
                        this.die();
                        if ((!this.world.mayPlace(this.id, i, j, k, true, 1, (Entity) null) || BlockSand.canFall(this.world, i, j - 1, k) || !this.world.setTypeIdAndData(i, j, k, this.id, this.data)) && !this.world.isStatic && this.dropItem) {
                            this.a(new ItemStack(this.id, 1, this.data), 0.0F);
                        }
                    }
                } else if (this.c > 100 && !this.world.isStatic && (j < 1 || j > 256) || this.c > 600) {
                    if (this.dropItem) {
                        this.b(this.id, 1);
                    }

                    this.die();
                }
            }
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setByte("Tile", (byte) this.id);
        nbttagcompound.setByte("Data", (byte) this.data);
        nbttagcompound.setByte("Time", (byte) this.c);
        nbttagcompound.setBoolean("DropItem", this.dropItem);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        this.id = nbttagcompound.getByte("Tile") & 255;
        this.data = nbttagcompound.getByte("Data") & 255;
        this.c = nbttagcompound.getByte("Time") & 255;
        if (nbttagcompound.hasKey("DropItem")) {
            this.dropItem = nbttagcompound.getBoolean("DropItem");
        }

        if (this.id == 0) {
            this.id = Block.SAND.id;
        }
    }
}
