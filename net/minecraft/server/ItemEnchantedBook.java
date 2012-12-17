package net.minecraft.server;

import java.util.Random;

public class ItemEnchantedBook extends Item {

    public ItemEnchantedBook(int i) {
        super(i);
    }

    public boolean d_(ItemStack itemstack) {
        return false;
    }

    public NBTTagList g(ItemStack itemstack) {
        return itemstack.tag != null && itemstack.tag.hasKey("StoredEnchantments") ? (NBTTagList) itemstack.tag.get("StoredEnchantments") : new NBTTagList();
    }

    public void a(ItemStack itemstack, EnchantmentInstance enchantmentinstance) {
        NBTTagList nbttaglist = this.g(itemstack);
        boolean flag = true;

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.get(i);

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

    public ItemStack a(Random random) {
        Enchantment enchantment = Enchantment.c[random.nextInt(Enchantment.c.length)];
        ItemStack itemstack = new ItemStack(this.id, 1, 0);
        int i = MathHelper.nextInt(random, enchantment.getStartLevel(), enchantment.getMaxLevel());

        this.a(itemstack, new EnchantmentInstance(enchantment, i));
        return itemstack;
    }

    public StructurePieceTreasure b(Random random) {
        return this.a(random, 1, 1, 1);
    }

    public StructurePieceTreasure a(Random random, int i, int j, int k) {
        Enchantment enchantment = Enchantment.c[random.nextInt(Enchantment.c.length)];
        ItemStack itemstack = new ItemStack(this.id, 1, 0);
        int l = MathHelper.nextInt(random, enchantment.getStartLevel(), enchantment.getMaxLevel());

        this.a(itemstack, new EnchantmentInstance(enchantment, l));
        return new StructurePieceTreasure(itemstack, i, j, k);
    }
}
