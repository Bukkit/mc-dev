package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] m = new int[] { 11, 16, 15, 13};
    private static final String[] n = new String[] { "leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay"};
    public static final String[] a = new String[] { "empty_armor_slot_helmet", "empty_armor_slot_chestplate", "empty_armor_slot_leggings", "empty_armor_slot_boots"};
    private static final IDispenseBehavior o = new DispenseBehaviorArmor();
    public final int b;
    public final int c;
    public final int d;
    private final EnumArmorMaterial p;

    public ItemArmor(EnumArmorMaterial enumarmormaterial, int i, int j) {
        this.p = enumarmormaterial;
        this.b = j;
        this.d = i;
        this.c = enumarmormaterial.b(j);
        this.setMaxDurability(enumarmormaterial.a(j));
        this.maxStackSize = 1;
        this.a(CreativeModeTab.j);
        BlockDispenser.a.a(this, o);
    }

    public int c() {
        return this.p.a();
    }

    public EnumArmorMaterial m_() {
        return this.p;
    }

    public boolean c_(ItemStack itemstack) {
        return this.p != EnumArmorMaterial.CLOTH ? false : (!itemstack.hasTag() ? false : (!itemstack.getTag().hasKeyOfType("display", 10) ? false : itemstack.getTag().getCompound("display").hasKeyOfType("color", 3)));
    }

    public int b(ItemStack itemstack) {
        if (this.p != EnumArmorMaterial.CLOTH) {
            return -1;
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound == null) {
                return 10511680;
            } else {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.hasKeyOfType("color", 3) ? nbttagcompound1.getInt("color") : 10511680);
            }
        }
    }

    public void c(ItemStack itemstack) {
        if (this.p == EnumArmorMaterial.CLOTH) {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1.hasKey("color")) {
                    nbttagcompound1.remove("color");
                }
            }
        }
    }

    public void b(ItemStack itemstack, int i) {
        if (this.p != EnumArmorMaterial.CLOTH) {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound == null) {
                nbttagcompound = new NBTTagCompound();
                itemstack.setTag(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

            if (!nbttagcompound.hasKeyOfType("display", 10)) {
                nbttagcompound.set("display", nbttagcompound1);
            }

            nbttagcompound1.setInt("color", i);
        }
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.p.b() == itemstack1.getItem() ? true : super.a(itemstack, itemstack1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        int i = EntityInsentient.b(itemstack) - 1;
        ItemStack itemstack1 = entityhuman.r(i);

        if (itemstack1 == null) {
            entityhuman.setEquipment(i, itemstack.cloneItemStack());
            itemstack.count = 0;
        }

        return itemstack;
    }

    static int[] e() {
        return m;
    }
}
