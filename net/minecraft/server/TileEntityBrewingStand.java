package net.minecraft.server;

import java.util.List;

public class TileEntityBrewingStand extends TileEntity implements IInventory {

    private ItemStack[] items = new ItemStack[4];
    private int brewTime;
    private int c;
    private int d;

    public TileEntityBrewingStand() {}

    public String getName() {
        return "container.brewing";
    }

    public int getSize() {
        return this.items.length;
    }

    public void g() {
        if (this.brewTime > 0) {
            --this.brewTime;
            if (this.brewTime == 0) {
                this.u();
                this.update();
            } else if (!this.k()) {
                this.brewTime = 0;
                this.update();
            } else if (this.d != this.items[3].id) {
                this.brewTime = 0;
                this.update();
            }
        } else if (this.k()) {
            this.brewTime = 400;
            this.d = this.items[3].id;
        }

        int i = this.i();

        if (i != this.c) {
            this.c = i;
            this.world.setData(this.x, this.y, this.z, i);
        }

        super.g();
    }

    public int x_() {
        return this.brewTime;
    }

    private boolean k() {
        if (this.items[3] != null && this.items[3].count > 0) {
            ItemStack itemstack = this.items[3];

            if (!Item.byId[itemstack.id].v()) {
                return false;
            } else {
                boolean flag = false;

                for (int i = 0; i < 3; ++i) {
                    if (this.items[i] != null && this.items[i].id == Item.POTION.id) {
                        int j = this.items[i].getData();
                        int k = this.b(j, itemstack);

                        if (!ItemPotion.g(j) && ItemPotion.g(k)) {
                            flag = true;
                            break;
                        }

                        List list = Item.POTION.f(j);
                        List list1 = Item.POTION.f(k);

                        if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null) && j != k) {
                            flag = true;
                            break;
                        }
                    }
                }

                return flag;
            }
        } else {
            return false;
        }
    }

    private void u() {
        if (this.k()) {
            ItemStack itemstack = this.items[3];

            for (int i = 0; i < 3; ++i) {
                if (this.items[i] != null && this.items[i].id == Item.POTION.id) {
                    int j = this.items[i].getData();
                    int k = this.b(j, itemstack);
                    List list = Item.POTION.f(j);
                    List list1 = Item.POTION.f(k);

                    if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) {
                        if (j != k) {
                            this.items[i].setData(k);
                        }
                    } else if (!ItemPotion.g(j) && ItemPotion.g(k)) {
                        this.items[i].setData(k);
                    }
                }
            }

            if (Item.byId[itemstack.id].s()) {
                this.items[3] = new ItemStack(Item.byId[itemstack.id].r());
            } else {
                --this.items[3].count;
                if (this.items[3].count <= 0) {
                    this.items[3] = null;
                }
            }
        }
    }

    private int b(int i, ItemStack itemstack) {
        return itemstack == null ? i : (Item.byId[itemstack.id].v() ? PotionBrewer.a(i, Item.byId[itemstack.id].u()) : i);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getList("Items");

        this.items = new ItemStack[this.getSize()];

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.get(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.items.length) {
                this.items[b0] = ItemStack.createStack(nbttagcompound1);
            }
        }

        this.brewTime = nbttagcompound.getShort("BrewTime");
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setShort("BrewTime", (short) this.brewTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.setByte("Slot", (byte) i);
                this.items[i].save(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        nbttagcompound.set("Items", nbttaglist);
    }

    public ItemStack getItem(int i) {
        return i >= 0 && i < this.items.length ? this.items[i] : null;
    }

    public ItemStack splitStack(int i, int j) {
        if (i >= 0 && i < this.items.length) {
            ItemStack itemstack = this.items[i];

            this.items[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public ItemStack splitWithoutUpdate(int i) {
        if (i >= 0 && i < this.items.length) {
            ItemStack itemstack = this.items[i];

            this.items[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack) {
        if (i >= 0 && i < this.items.length) {
            this.items[i] = itemstack;
        }
    }

    public int getMaxStackSize() {
        return 1;
    }

    public boolean a_(EntityHuman entityhuman) {
        return this.world.getTileEntity(this.x, this.y, this.z) != this ? false : entityhuman.e((double) this.x + 0.5D, (double) this.y + 0.5D, (double) this.z + 0.5D) <= 64.0D;
    }

    public void startOpen() {}

    public void f() {}

    public int i() {
        int i = 0;

        for (int j = 0; j < 3; ++j) {
            if (this.items[j] != null) {
                i |= 1 << j;
            }
        }

        return i;
    }
}
