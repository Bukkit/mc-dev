package net.minecraft.server;

public enum EnchantmentSlotType {

    ALL("all", 0), ARMOR("armor", 1), ARMOR_FEET("armor_feet", 2), ARMOR_LEGS("armor_legs", 3), ARMOR_TORSO("armor_torso", 4), ARMOR_HEAD("armor_head", 5), WEAPON("weapon", 6), DIGGER("digger", 7), FISHING_ROD("fishing_rod", 8), BREAKABLE("breakable", 9), BOW("bow", 10);
    private static final EnchantmentSlotType[] l = new EnchantmentSlotType[] { ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW};

    private EnchantmentSlotType(String s, int i) {}

    public boolean canEnchant(Item item) {
        if (this == ALL) {
            return true;
        } else if (this == BREAKABLE && item.usesDurability()) {
            return true;
        } else if (item instanceof ItemArmor) {
            if (this == ARMOR) {
                return true;
            } else {
                ItemArmor itemarmor = (ItemArmor) item;

                return itemarmor.b == 0 ? this == ARMOR_HEAD : (itemarmor.b == 2 ? this == ARMOR_LEGS : (itemarmor.b == 1 ? this == ARMOR_TORSO : (itemarmor.b == 3 ? this == ARMOR_FEET : false)));
            }
        } else {
            return item instanceof ItemSword ? this == WEAPON : (item instanceof ItemTool ? this == DIGGER : (item instanceof ItemBow ? this == BOW : (item instanceof ItemFishingRod ? this == FISHING_ROD : false)));
        }
    }
}
