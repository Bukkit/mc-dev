package net.minecraft.server;

public class ItemInWorldManager {

    public World world;
    public EntityHuman player;
    private int c = -1;
    private float d = 0.0F;
    private int lastDigTick;
    private int f;
    private int g;
    private int h;
    private int currentTick;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;

    public ItemInWorldManager(World world) {
        this.world = world;
    }

    public void setGameMode(int i) {
        this.c = i;
        if (i == 0) {
            this.player.abilities.canFly = false;
            this.player.abilities.isFlying = false;
            this.player.abilities.canInstantlyBuild = false;
            this.player.abilities.isInvulnerable = false;
        } else {
            this.player.abilities.canFly = true;
            this.player.abilities.canInstantlyBuild = true;
            this.player.abilities.isInvulnerable = true;
        }
    }

    public int getGameMode() {
        return this.c;
    }

    public boolean b() {
        return this.c == 1;
    }

    public void b(int i) {
        if (this.c == -1) {
            this.c = i;
        }

        this.setGameMode(this.c);
    }

    public void c() {
        ++this.currentTick;
        if (this.j) {
            int i = this.currentTick - this.n;
            int j = this.world.getTypeId(this.k, this.l, this.m);

            if (j != 0) {
                Block block = Block.byId[j];
                float f = block.getDamage(this.player) * (float) (i + 1);

                if (f >= 1.0F) {
                    this.j = false;
                    this.c(this.k, this.l, this.m);
                }
            } else {
                this.j = false;
            }
        }
    }

    public void dig(int i, int j, int k, int l) {
        this.world.douseFire((EntityHuman) null, i, j, k, l);
        if (this.b()) {
            this.c(i, j, k);
        } else {
            this.lastDigTick = this.currentTick;
            int i1 = this.world.getTypeId(i, j, k);

            if (i1 > 0) {
                Block.byId[i1].b(this.world, i, j, k, this.player);
            }

            if (i1 > 0 && Block.byId[i1].getDamage(this.player) >= 1.0F) {
                this.c(i, j, k);
            } else {
                this.f = i;
                this.g = j;
                this.h = k;
            }
        }
    }

    public void a(int i, int j, int k) {
        if (i == this.f && j == this.g && k == this.h) {
            int l = this.currentTick - this.lastDigTick;
            int i1 = this.world.getTypeId(i, j, k);

            if (i1 != 0) {
                Block block = Block.byId[i1];
                float f = block.getDamage(this.player) * (float) (l + 1);

                if (f >= 0.7F) {
                    this.c(i, j, k);
                } else if (!this.j) {
                    this.j = true;
                    this.k = i;
                    this.l = j;
                    this.m = k;
                    this.n = this.lastDigTick;
                }
            }
        }

        this.d = 0.0F;
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

        if (this.b()) {
            ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
        } else {
            ItemStack itemstack = this.player.P();
            boolean flag1 = this.player.b(Block.byId[l]);

            if (itemstack != null) {
                itemstack.a(l, i, j, k, this.player);
                if (itemstack.count == 0) {
                    itemstack.a(this.player);
                    this.player.Q();
                }
            }

            if (flag && flag1) {
                Block.byId[l].a(this.world, this.player, i, j, k, i1);
            }
        }

        return flag;
    }

    public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack) {
        int i = itemstack.count;
        int j = itemstack.getData();
        ItemStack itemstack1 = itemstack.a(world, entityhuman);

        if (itemstack1 == itemstack && (itemstack1 == null || itemstack1.count == i) && (itemstack1 == null || itemstack1.l() <= 0)) {
            return false;
        } else {
            entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
            if (this.b()) {
                itemstack1.count = i;
                itemstack1.setData(j);
            }

            if (itemstack1.count == 0) {
                entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = null;
            }

            return true;
        }
    }

    public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);

        if (i1 > 0 && Block.byId[i1].interact(world, i, j, k, entityhuman)) {
            return true;
        } else if (itemstack == null) {
            return false;
        } else if (this.b()) {
            int j1 = itemstack.getData();
            int k1 = itemstack.count;
            boolean flag = itemstack.placeItem(entityhuman, world, i, j, k, l);

            itemstack.setData(j1);
            itemstack.count = k1;
            return flag;
        } else {
            return itemstack.placeItem(entityhuman, world, i, j, k, l);
        }
    }

    public void a(WorldServer worldserver) {
        this.world = worldserver;
    }
}
