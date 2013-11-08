package net.minecraft.server;

import java.util.Random;

public class ItemEnchantedBook extends Item {

    public ItemEnchantedBook() {}

    public boolean e_(ItemStack itemstack) {
        return false;
    }

    public EnumItemRarity f(ItemStack itemstack) {
        return this.g(itemstack).size() > 0 ? EnumItemRarity.UNCOMMON : super.f(itemstack);
    }

    public NBTTagList g(ItemStack itemstack) {
        return itemstack.tag != null && itemstack.tag.hasKeyOfType("StoredEnchantments", 9) ? (NBTTagList) itemstack.tag.get("StoredEnchantments") : new NBTTagList();
    }

    public void a(ItemStack itemstack, EnchantmentInstance enchantmentinstance) {
        NBTTagList nbttaglist = this.g(itemstack);
        boolean flag = true;

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.get(i);

            if (nbttagcompound.getShort("id") == enchantmentinstance.enchantment.id) {
                if (nbttagcompound.getShort("lvl") < enchantmentinstance.level) {
                    nbttagcompound.setShort("lvl", (short) enchantmentinstance.level);
                }

                flag = false;
                break;
            }
        }

        if (flag) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            nbttagcompound1.setShort("id", (short) enchantmentinstance.enchantment.id);
            nbttagcompound1.setShort("lvl", (short) enchantmentinstance.level);
            nbttaglist.add(nbttagcompound1);
        }

        if (!itemstack.hasTag()) {
            itemstack.setTag(new NBTTagCompound());
        }

        itemstack.getTag().set("StoredEnchantments", nbttaglist);
    }

    public ItemStack a(EnchantmentInstance enchantmentinstance) {
        ItemStack itemstack = new ItemStack(this);

        this.a(itemstack, enchantmentinstance);
        return itemstack;
    }

    public StructurePieceTreasure b(Random random) {
        return this.a(random, 1, 1, 1);
    }

    public StructurePieceTreasure a(Random random, int i, int j, int k) {
        ItemStack itemstack = new ItemStack(Items.BOOK, 1, 0);

        EnchantmentManager.a(random, itemstack, 30);
        return new StructurePieceTreasure(itemstack, i, j, k);
    }
}
