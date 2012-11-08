package net.minecraft.server;

public class InventoryEnderChest extends InventorySubcontainer {

    private TileEntityEnderChest a;

    public InventoryEnderChest() {
        super("container.enderchest", 27);
    }

    public void a(TileEntityEnderChest tileentityenderchest) {
        this.a = tileentityenderchest;
    }

    public void a(NBTTagList nbttaglist) {
        int i;

        for (i = 0; i < this.getSize(); ++i) {
            this.setItem(i, (ItemStack) null);
        }

        for (i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.get(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < this.getSize()) {
                this.setItem(j, ItemStack.a(nbttagcompound));
            }
        }
    }

    public NBTTagList g() {
        NBTTagList nbttaglist = new NBTTagList("EnderItems");

        for (int i = 0; i < this.getSize(); ++i) {
            ItemStack itemstack = this.getItem(i);

            if (itemstack != null) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();

                nbttagcompound.setByte("Slot", (byte) i);
                itemstack.save(nbttagcompound);
                nbttaglist.add(nbttagcompound);
            }
        }

        return nbttaglist;
    }

    public boolean a_(EntityHuman entityhuman) {
        return this.a != null && !this.a.a(entityhuman) ? false : super.a_(entityhuman);
    }

    public void startOpen() {
        if (this.a != null) {
            this.a.a();
        }

        super.startOpen();
    }

    public void f() {
        if (this.a != null) {
            this.a.b();
        }

        super.f();
        this.a = null;
    }
}
