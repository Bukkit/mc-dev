package net.minecraft.server;

public class DemoPlayerInteractManager extends PlayerInteractManager {

    private boolean c;
    private boolean d;
    private int e;
    private int f;

    public DemoPlayerInteractManager(World world) {
        super(world);
    }

    public void a() {
        super.a();
        ++this.f;
        long i = this.world.getTime();
        long j = i / 24000L + 1L;

        if (!this.c && this.f > 20) {
            this.c = true;
            this.player.playerConnection.sendPacket(new Packet70Bed(5, 0));
        }

        this.d = i > 120500L;
        if (this.d) {
            ++this.e;
        }

        if (i % 24000L == 500L) {
            if (j <= 6L) {
                this.player.sendMessage(ChatMessage.e("demo.day." + j));
            }
        } else if (j == 1L) {
            if (i == 100L) {
                this.player.playerConnection.sendPacket(new Packet70Bed(5, 101));
            } else if (i == 175L) {
                this.player.playerConnection.sendPacket(new Packet70Bed(5, 102));
            } else if (i == 250L) {
                this.player.playerConnection.sendPacket(new Packet70Bed(5, 103));
            }
        } else if (j == 5L && i % 24000L == 22000L) {
            this.player.sendMessage(ChatMessage.e("demo.day.warning"));
        }
    }

    private void e() {
        if (this.e > 100) {
            this.player.sendMessage(ChatMessage.e("demo.reminder"));
            this.e = 0;
        }
    }

    public void dig(int i, int j, int k, int l) {
        if (this.d) {
            this.e();
        } else {
            super.dig(i, j, k, l);
        }
    }

    public void a(int i, int j, int k) {
        if (!this.d) {
            super.a(i, j, k);
        }
    }

    public boolean breakBlock(int i, int j, int k) {
        return this.d ? false : super.breakBlock(i, j, k);
    }

    public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack) {
        if (this.d) {
            this.e();
            return false;
        } else {
            return super.useItem(entityhuman, world, itemstack);
        }
    }

    public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l, float f, float f1, float f2) {
        if (this.d) {
            this.e();
            return false;
        } else {
            return super.interact(entityhuman, world, itemstack, i, j, k, l, f, f1, f2);
        }
    }
}
