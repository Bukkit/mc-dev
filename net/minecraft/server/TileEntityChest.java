package net.minecraft.server;

public class TileEntityChest extends TileEntity implements IInventory {

    private ItemStack[] e = new ItemStack[36];

    public TileEntityChest() {}

    public int a() {
        return 27;
    }

    public ItemStack a(int i) {
        return this.e[i];
    }

    public void a(int i, ItemStack itemstack) {
        this.e[i] = itemstack;
        if (itemstack != null && itemstack.a > this.d()) {
            itemstack.a = this.d();
        }

        this.c();
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Items");

        this.e = new ItemStack[this.a()];

        for (int i = 0; i < nbttaglist.b(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(i);
            int j = nbttagcompound1.b("Slot") & 255;

            if (j >= 0 && j < this.e.length) {
                this.e[j] = new ItemStack(nbttagcompound1);
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.e.length; ++i) {
            if (this.e[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i);
                this.e[i].a(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
    }

    public int d() {
        return 64;
    }
}
