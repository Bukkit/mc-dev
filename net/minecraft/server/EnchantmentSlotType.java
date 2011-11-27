package net.minecraft.server;

public enum EnchantmentSlotType {

    ALL("all", 0), ARMOR("armor", 1), ARMOR_FEET("armor_feet", 2), ARMOR_LEGS("armor_legs", 3), ARMOR_TORSO("armor_torso", 4), ARMOR_HEAD("armor_head", 5), WEAPON("weapon", 6), DIGGER("digger", 7);

    private static final EnchantmentSlotType[] i = new EnchantmentSlotType[] { ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER};

    private EnchantmentSlotType(String s, int i) {}

    public boolean canEnchant(Item item) {
        if (this == ALL) {
            return true;
        } else if (item instanceof ItemArmor) {
            if (this == ARMOR) {
                return true;
            } else {
                ItemArmor itemarmor = (ItemArmor) item;

                return itemarmor.a == 0 ? this == ARMOR_HEAD : (itemarmor.a == 2 ? this == ARMOR_LEGS : (itemarmor.a == 1 ? this == ARMOR_TORSO : (itemarmor.a == 3 ? this == ARMOR_FEET : false)));
            }
        } else {
            return item instanceof ItemSword ? this == WEAPON : (item instanceof ItemTool ? this == DIGGER : false);
        }
    }
}
