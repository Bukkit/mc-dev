package net.minecraft.server;

public class ItemInWorldManager {

    private WorldServer world;
    public EntityHuman player;
    private float c = 0.0F;
    private int d;
    private int e;
    private int f;
    private int g;
    private int currentTick;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;

    public ItemInWorldManager(WorldServer worldserver) {
        this.world = worldserver;
    }

    public void a() {
        ++this.currentTick;
        if (this.i) {
            int i = this.currentTick - this.m;
            int j = this.world.getTypeId(this.j, this.k, this.l);

            if (j != 0) {
                Block block = Block.byId[j];
                float f = block.getDamage(this.player) * (float) (i + 1);

                if (f >= 1.0F) {
                    this.i = false;
                    this.c(this.j, this.k, this.l);
                }
            } else {
                this.i = false;
            }
        }
    }

    public void dig(int i, int j, int k, int l) {
        this.world.a((EntityHuman) null, i, j, k, l);
        this.d = this.currentTick;
        int i1 = this.world.getTypeId(i, j, k);

        if (i1 > 0) {
            Block.byId[i1].b(this.world, i, j, k, this.player);
        }

        if (i1 > 0 && Block.byId[i1].getDamage(this.player) >= 1.0F) {
            this.c(i, j, k);
        } else {
            this.e = i;
            this.f = j;
            this.g = k;
        }
    }

    public void a(int i, int j, int k) {
        if (i == this.e && j == this.f && k == this.g) {
            int l = this.currentTick - this.d;
            int i1 = this.world.getTypeId(i, j, k);

            if (i1 != 0) {
                Block block = Block.byId[i1];
                float f = block.getDamage(this.player) * (float) (l + 1);

                if (f >= 0.7F) {
                    this.c(i, j, k);
                } else if (!this.i) {
                    this.i = true;
                    this.j = i;
                    this.k = j;
                    this.l = k;
                    this.m = this.d;
                }
            }
        }

        this.c = 0.0F;
    }

    public boolean b(int i, int j, int k) {
        Block block = Block.byId[this.world.getTypeId(i, j, k)];
        int l = this.world.getData(i, j, k);
        boolean flag = this.world.setTypeId(i, j, k, 0);

        if (block != null && flag) {
            block.postBreak(this.world, i, j, k, l);
        }

        return flag;
    }

    public boolean c(int i, int j, int k) {
        int l = this.world.getTypeId(i, j, k);
        int i1 = this.world.getData(i, j, k);

        this.world.a(this.player, 2001, i, j, k, l + this.world.getData(i, j, k) * 256);
        boolean flag = this.b(i, j, k);
        ItemStack itemstack = this.player.F();

        if (itemstack != null) {
            itemstack.a(l, i, j, k, this.player);
            if (itemstack.count == 0) {
                itemstack.a(this.player);
                this.player.G();
            }
        }

        if (flag && this.player.b(Block.byId[l])) {
            Block.byId[l].a(this.world, this.player, i, j, k, i1);
            ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
        }

        return flag;
    }

    public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack) {
        int i = itemstack.count;
        ItemStack itemstack1 = itemstack.a(world, entityhuman);

        if (itemstack1 == itemstack && (itemstack1 == null || itemstack1.count == i)) {
            return false;
        } else {
            entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
            if (itemstack1.count == 0) {
                entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = null;
            }

            return true;
        }
    }

    public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);

        return i1 > 0 && Block.byId[i1].interact(world, i, j, k, entityhuman) ? true : (itemstack == null ? false : itemstack.placeItem(entityhuman, world, i, j, k, l));
    }
}
