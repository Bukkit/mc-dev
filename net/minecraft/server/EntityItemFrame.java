package net.minecraft.server;

public class EntityItemFrame extends EntityHanging {

    private float e = 1.0F;

    public EntityItemFrame(World world) {
        super(world);
    }

    public EntityItemFrame(World world, int i, int j, int k, int l) {
        super(world, i, j, k, l);
        this.setDirection(l);
    }

    protected void a() {
        this.getDataWatcher().a(2, 5);
        this.getDataWatcher().a(3, Byte.valueOf((byte) 0));
    }

    public int d() {
        return 9;
    }

    public int g() {
        return 9;
    }

    public void h() {
        this.a(new ItemStack(Item.ITEM_FRAME), 0.0F);
        ItemStack itemstack = this.i();

        if (itemstack != null && this.random.nextFloat() < this.e) {
            itemstack = itemstack.cloneItemStack();
            itemstack.a((EntityItemFrame) null);
            this.a(itemstack, 0.0F);
        }
    }

    public ItemStack i() {
        return this.getDataWatcher().getItemStack(2);
    }

    public void a(ItemStack itemstack) {
        itemstack = itemstack.cloneItemStack();
        itemstack.count = 1;
        itemstack.a(this);
        this.getDataWatcher().watch(2, itemstack);
        this.getDataWatcher().h(2);
    }

    public int j() {
        return this.getDataWatcher().getByte(3);
    }

    public void setRotation(int i) {
        this.getDataWatcher().watch(3, Byte.valueOf((byte) (i % 4)));
    }

    public void b(NBTTagCompound nbttagcompound) {
        if (this.i() != null) {
            nbttagcompound.setCompound("Item", this.i().save(new NBTTagCompound()));
            nbttagcompound.setByte("ItemRotation", (byte) this.j());
            nbttagcompound.setFloat("ItemDropChance", this.e);
        }

        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");

        if (nbttagcompound1 != null && !nbttagcompound1.isEmpty()) {
            this.a(ItemStack.createStack(nbttagcompound1));
            this.setRotation(nbttagcompound.getByte("ItemRotation"));
            if (nbttagcompound.hasKey("ItemDropChance")) {
                this.e = nbttagcompound.getFloat("ItemDropChance");
            }
        }

        super.a(nbttagcompound);
    }

    public boolean a_(EntityHuman entityhuman) {
        if (this.i() == null) {
            ItemStack itemstack = entityhuman.bG();

            if (itemstack != null && !this.world.isStatic) {
                this.a(itemstack);
                if (!entityhuman.abilities.canInstantlyBuild && --itemstack.count <= 0) {
                    entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                }
            }
        } else if (!this.world.isStatic) {
            this.setRotation(this.j() + 1);
        }

        return true;
    }
}
