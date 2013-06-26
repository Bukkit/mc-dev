package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] cB = new int[] { 11, 16, 15, 13};
    private static final String[] cC = new String[] { "leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay"};
    public static final String[] a = new String[] { "empty_armor_slot_helmet", "empty_armor_slot_chestplate", "empty_armor_slot_leggings", "empty_armor_slot_boots"};
    private static final IDispenseBehavior cD = new DispenseBehaviorArmor();
    public final int b;
    public final int c;
    public final int d;
    private final EnumArmorMaterial cE;

    public ItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
        super(i);
        this.cE = enumarmormaterial;
        this.b = k;
        this.d = j;
        this.c = enumarmormaterial.b(k);
        this.setMaxDurability(enumarmormaterial.a(k));
        this.maxStackSize = 1;
        this.a(CreativeModeTab.j);
        BlockDispenser.a.a(this, cD);
    }

    public int c() {
        return this.cE.a();
    }

    public EnumArmorMaterial d() {
        return this.cE;
    }

    public boolean a(ItemStack itemstack) {
        return this.cE != EnumArmorMaterial.CLOTH ? false : (!itemstack.hasTag() ? false : (!itemstack.getTag().hasKey("display") ? false : itemstack.getTag().getCompound("display").hasKey("color")));
    }

    public int b(ItemStack itemstack) {
        if (this.cE != EnumArmorMaterial.CLOTH) {
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
        if (this.cE == EnumArmorMaterial.CLOTH) {
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
        if (this.cE != EnumArmorMaterial.CLOTH) {
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
        return this.cE.b() == itemstack1.id ? true : super.a(itemstack, itemstack1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        int i = EntityInsentient.b(itemstack) - 1;
        ItemStack itemstack1 = entityhuman.o(i);

        if (itemstack1 == null) {
            entityhuman.setEquipment(i, itemstack.cloneItemStack());
            itemstack.count = 0;
        }

        return itemstack;
    }

    static int[] e() {
        return cB;
    }
}
