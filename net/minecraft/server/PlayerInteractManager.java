package net.minecraft.server;

public class PlayerInteractManager {

    public World world;
    public EntityPlayer player;
    private EnumGamemode gamemode;
    private boolean d;
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
    private int o;

    public PlayerInteractManager(World world) {
        this.gamemode = EnumGamemode.NONE;
        this.o = -1;
        this.world = world;
    }

    public void setGameMode(EnumGamemode enumgamemode) {
        this.gamemode = enumgamemode;
        enumgamemode.a(this.player.abilities);
        this.player.updateAbilities();
    }

    public EnumGamemode getGameMode() {
        return this.gamemode;
    }

    public boolean isCreative() {
        return this.gamemode.d();
    }

    public void b(EnumGamemode enumgamemode) {
        if (this.gamemode == EnumGamemode.NONE) {
            this.gamemode = enumgamemode;
        }

        this.setGameMode(this.gamemode);
    }

    public void a() {
        ++this.currentTick;
        int i;
        float f;
        int j;

        if (this.j) {
            i = this.currentTick - this.n;
            int k = this.world.getTypeId(this.k, this.l, this.m);

            if (k == 0) {
                this.j = false;
            } else {
                Block block = Block.byId[k];

                f = block.getDamage(this.player, this.player.world, this.k, this.l, this.m) * (float) (i + 1);
                j = (int) (f * 10.0F);
                if (j != this.o) {
                    this.world.f(this.player.id, this.k, this.l, this.m, j);
                    this.o = j;
                }

                if (f >= 1.0F) {
                    this.j = false;
                    this.breakBlock(this.k, this.l, this.m);
                }
            }
        } else if (this.d) {
            i = this.world.getTypeId(this.f, this.g, this.h);
            Block block1 = Block.byId[i];

            if (block1 == null) {
                this.world.f(this.player.id, this.f, this.g, this.h, -1);
                this.o = -1;
                this.d = false;
            } else {
                int l = this.currentTick - this.lastDigTick;

                f = block1.getDamage(this.player, this.player.world, this.f, this.g, this.h) * (float) (l + 1);
                j = (int) (f * 10.0F);
                if (j != this.o) {
                    this.world.f(this.player.id, this.f, this.g, this.h, j);
                    this.o = j;
                }
            }
        }
    }

    public void dig(int i, int j, int k, int l) {
        if (!this.gamemode.isAdventure() || this.player.e(i, j, k)) {
            if (this.isCreative()) {
                if (!this.world.douseFire((EntityHuman) null, i, j, k, l)) {
                    this.breakBlock(i, j, k);
                }
            } else {
                this.world.douseFire((EntityHuman) null, i, j, k, l);
                this.lastDigTick = this.currentTick;
                float f = 1.0F;
                int i1 = this.world.getTypeId(i, j, k);

                if (i1 > 0) {
                    Block.byId[i1].attack(this.world, i, j, k, this.player);
                    f = Block.byId[i1].getDamage(this.player, this.player.world, i, j, k);
                }

                if (i1 > 0 && f >= 1.0F) {
                    this.breakBlock(i, j, k);
                } else {
                    this.d = true;
                    this.f = i;
                    this.g = j;
                    this.h = k;
                    int j1 = (int) (f * 10.0F);

                    this.world.f(this.player.id, i, j, k, j1);
                    this.o = j1;
                }
            }
        }
    }

    public void a(int i, int j, int k) {
        if (i == this.f && j == this.g && k == this.h) {
            int l = this.currentTick - this.lastDigTick;
            int i1 = this.world.getTypeId(i, j, k);

            if (i1 != 0) {
                Block block = Block.byId[i1];
                float f = block.getDamage(this.player, this.player.world, i, j, k) * (float) (l + 1);

                if (f >= 0.7F) {
                    this.d = false;
                    this.world.f(this.player.id, i, j, k, -1);
                    this.breakBlock(i, j, k);
                } else if (!this.j) {
                    this.d = false;
                    this.j = true;
                    this.k = i;
                    this.l = j;
                    this.m = k;
                    this.n = this.lastDigTick;
                }
            }
        }
    }

    public void c(int i, int j, int k) {
        this.d = false;
        this.world.f(this.player.id, this.f, this.g, this.h, -1);
    }

    private boolean d(int i, int j, int k) {
        Block block = Block.byId[this.world.getTypeId(i, j, k)];
        int l = this.world.getData(i, j, k);

        if (block != null) {
            block.a(this.world, i, j, k, l, this.player);
        }

        boolean flag = this.world.setAir(i, j, k);

        if (block != null && flag) {
            block.postBreak(this.world, i, j, k, l);
        }

        return flag;
    }

    public boolean breakBlock(int i, int j, int k) {
        if (this.gamemode.isAdventure() && !this.player.e(i, j, k)) {
            return false;
        } else {
            int l = this.world.getTypeId(i, j, k);
            int i1 = this.world.getData(i, j, k);

            this.world.a(this.player, 2001, i, j, k, l + (this.world.getData(i, j, k) << 12));
            boolean flag = this.d(i, j, k);

            if (this.isCreative()) {
                this.player.playerConnection.sendPacket(new Packet53BlockChange(i, j, k, this.world));
            } else {
                ItemStack itemstack = this.player.cd();
                boolean flag1 = this.player.a(Block.byId[l]);

                if (itemstack != null) {
                    itemstack.a(this.world, l, i, j, k, this.player);
                    if (itemstack.count == 0) {
                        this.player.ce();
                    }
                }

                if (flag && flag1) {
                    Block.byId[l].a(this.world, this.player, i, j, k, i1);
                }
            }

            return flag;
        }
    }

    public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack) {
        int i = itemstack.count;
        int j = itemstack.getData();
        ItemStack itemstack1 = itemstack.a(world, entityhuman);

        if (itemstack1 == itemstack && (itemstack1 == null || itemstack1.count == i && itemstack1.n() <= 0 && itemstack1.getData() == j)) {
            return false;
        } else {
            entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
            if (this.isCreative()) {
                itemstack1.count = i;
                if (itemstack1.g()) {
                    itemstack1.setData(j);
                }
            }

            if (itemstack1.count == 0) {
                entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = null;
            }

            if (!entityhuman.bX()) {
                ((EntityPlayer) entityhuman).updateInventory(entityhuman.defaultContainer);
            }

            return true;
        }
    }

    public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1;

        if (!entityhuman.isSneaking() || entityhuman.bG() == null) {
            i1 = world.getTypeId(i, j, k);
            if (i1 > 0 && Block.byId[i1].interact(world, i, j, k, entityhuman, l, f, f1, f2)) {
                return true;
            }
        }

        if (itemstack == null) {
            return false;
        } else if (this.isCreative()) {
            i1 = itemstack.getData();
            int j1 = itemstack.count;
            boolean flag = itemstack.placeItem(entityhuman, world, i, j, k, l, f, f1, f2);

            itemstack.setData(i1);
            itemstack.count = j1;
            return flag;
        } else {
            return itemstack.placeItem(entityhuman, world, i, j, k, l, f, f1, f2);
        }
    }

    public void a(WorldServer worldserver) {
        this.world = worldserver;
    }
}
