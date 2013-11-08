package net.minecraft.server;

public class EntityMinecartCommandBlock extends EntityMinecartAbstract {

    private final CommandBlockListenerAbstract a = new EntityMinecartCommandBlockListener(this);
    private int b = 0;

    public EntityMinecartCommandBlock(World world) {
        super(world);
    }

    public EntityMinecartCommandBlock(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    protected void c() {
        super.c();
        this.getDataWatcher().a(23, "");
        this.getDataWatcher().a(24, "");
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a.b(nbttagcompound);
        this.getDataWatcher().watch(23, this.e().i());
        this.getDataWatcher().watch(24, ChatSerializer.a(this.e().h()));
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a.a(nbttagcompound);
    }

    public int m() {
        return 6;
    }

    public Block o() {
        return Blocks.COMMAND;
    }

    public CommandBlockListenerAbstract e() {
        return this.a;
    }

    public void a(int i, int j, int k, boolean flag) {
        if (flag && this.ticksLived - this.b >= 4) {
            this.e().a(this.world);
            this.b = this.ticksLived;
        }
    }

    public boolean c(EntityHuman entityhuman) {
        if (this.world.isStatic) {
            entityhuman.a(this.e());
        }

        return super.c(entityhuman);
    }

    public void i(int i) {
        super.i(i);
        if (i == 24) {
            try {
                this.a.b(ChatSerializer.a(this.getDataWatcher().getString(24)));
            } catch (Throwable throwable) {
                ;
            }
        } else if (i == 23) {
            this.a.a(this.getDataWatcher().getString(23));
        }
    }
}
