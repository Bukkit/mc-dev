package net.minecraft.server;

public class TileEntityFurnace extends TileEntity implements IInventory {

    private ItemStack[] items = new ItemStack[3];
    public int burnTime = 0;
    public int ticksForCurrentFuel = 0;
    public int cookTime = 0;

    public TileEntityFurnace() {}

    public int getSize() {
        return this.items.length;
    }

    public ItemStack getItem(int i) {
        return this.items[i];
    }

    public ItemStack splitStack(int i, int j) {
        if (this.items[i] != null) {
            ItemStack itemstack;

            if (this.items[i].count <= j) {
                itemstack = this.items[i];
                this.items[i] = null;
                return itemstack;
            } else {
                itemstack = this.items[i].a(j);
                if (this.items[i].count == 0) {
                    this.items[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack splitWithoutUpdate(int i) {
        if (this.items[i] != null) {
            ItemStack itemstack = this.items[i];

            this.items[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
        if (itemstack != null && itemstack.count > this.getMaxStackSize()) {
            itemstack.count = this.getMaxStackSize();
        }
    }

    public String getName() {
        return "container.furnace";
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getList("Items");

        this.items = new ItemStack[this.getSize()];

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.get(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.items.length) {
                this.items[b0] = ItemStack.a(nbttagcompound1);
            }
        }

        this.burnTime = nbttagcompound.getShort("BurnTime");
        this.cookTime = nbttagcompound.getShort("CookTime");
        this.ticksForCurrentFuel = this.fuelTime(this.items[1]);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short) this.burnTime);
        nbttagcompound.setShort("CookTime", (short) this.cookTime);
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

    public int getMaxStackSize() {
        return 64;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public void q_() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.burnTime > 0) {
            --this.burnTime;
        }

        if (!this.world.isStatic) {
            if (this.burnTime == 0 && this.canBurn()) {
                this.ticksForCurrentFuel = this.burnTime = this.fuelTime(this.items[1]);
                if (this.burnTime > 0) {
                    flag1 = true;
                    if (this.items[1] != null) {
                        --this.items[1].count;
                        if (this.items[1].count == 0) {
                            this.items[1] = null;
                        }
                    }
                }
            }

            if (this.isBurning() && this.canBurn()) {
                ++this.cookTime;
                if (this.cookTime == 200) {
                    this.cookTime = 0;
                    this.burn();
                    flag1 = true;
                }
            } else {
                this.cookTime = 0;
            }

            if (flag != this.burnTime > 0) {
                flag1 = true;
                BlockFurnace.a(this.burnTime > 0, this.world, this.x, this.y, this.z);
            }
        }

        if (flag1) {
            this.update();
        }
    }

    private boolean canBurn() {
        if (this.items[0] == null) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.getInstance().getResult(this.items[0].getItem().id);

            return itemstack == null ? false : (this.items[2] == null ? true : (!this.items[2].doMaterialsMatch(itemstack) ? false : (this.items[2].count < this.getMaxStackSize() && this.items[2].count < this.items[2].getMaxStackSize() ? true : this.items[2].count < itemstack.getMaxStackSize())));
        }
    }

    public void burn() {
        if (this.canBurn()) {
            ItemStack itemstack = FurnaceRecipes.getInstance().getResult(this.items[0].getItem().id);

            if (this.items[2] == null) {
                this.items[2] = itemstack.cloneItemStack();
            } else if (this.items[2].id == itemstack.id) {
                ++this.items[2].count;
            }

            --this.items[0].count;
            if (this.items[0].count <= 0) {
                this.items[0] = null;
            }
        }
    }

    private int fuelTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            int i = itemstack.getItem().id;

            return i < 256 && Block.byId[i].material == Material.WOOD ? 300 : (i == Item.STICK.id ? 100 : (i == Item.COAL.id ? 1600 : (i == Item.LAVA_BUCKET.id ? 20000 : (i == Block.SAPLING.id ? 100 : (i == Item.BLAZE_ROD.id ? 2400 : 0)))));
        }
    }

    public boolean a(EntityHuman entityhuman) {
        return this.world.getTileEntity(this.x, this.y, this.z) != this ? false : entityhuman.e((double) this.x + 0.5D, (double) this.y + 0.5D, (double) this.z + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}
}
