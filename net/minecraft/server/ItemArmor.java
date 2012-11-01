package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] cl = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int b;
    public final int c;
    private final EnumArmorMaterial cm;

    public ItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
        super(i);
        this.cm = enumarmormaterial;
        this.a = k;
        this.c = j;
        this.b = enumarmormaterial.b(k);
        this.setMaxDurability(enumarmormaterial.a(k));
        this.maxStackSize = 1;
        this.a(CreativeModeTab.j);
    }

    public int c() {
        return this.cm.a();
    }

    public EnumArmorMaterial d() {
        return this.cm;
    }

    public boolean b_(ItemStack itemstack) {
        return this.cm != EnumArmorMaterial.CLOTH ? false : (!itemstack.hasTag() ? false : (!itemstack.getTag().hasKey("display") ? false : itemstack.getTag().getCompound("display").hasKey("color")));
    }

    public int b(ItemStack itemstack) {
        if (this.cm != EnumArmorMaterial.CLOTH) {
            return -1;
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound == null) {
                return 10511680;
            } else {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.hasKey("color") ? nbttagcompound1.getInt("color") : 10511680);
            }
        }
    }

    public void c(ItemStack itemstack) {
        if (this.cm == EnumArmorMaterial.CLOTH) {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1.hasKey("color")) {
                    nbttagcompound1.o("color");
                }
            }
        }
    }

    public void b(ItemStack itemstack, int i) {
        if (this.cm != EnumArmorMaterial.CLOTH) {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound == null) {
                nbttagcompound = new NBTTagCompound();
                itemstack.setTag(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

            if (!nbttagcompound.hasKey("display")) {
                nbttagcompound.setCompound("display", nbttagcompound1);
            }

            nbttagcompound1.setInt("color", i);
        }
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.cm.b() == itemstack1.id ? true : super.a(itemstack, itemstack1);
    }

    static int[] e() {
        return cl;
    }
}
