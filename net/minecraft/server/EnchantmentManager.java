package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EnchantmentManager {

    private static final Random random = new Random();
    private static final EnchantmentModifierProtection b = new EnchantmentModifierProtection((EmptyClass) null);
    private static final EnchantmentModifierDamage c = new EnchantmentModifierDamage((EmptyClass) null);

    public static int getEnchantmentLevel(int i, ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            NBTTagList nbttaglist = itemstack.getEnchantments();

            if (nbttaglist == null) {
                return 0;
            } else {
                for (int j = 0; j < nbttaglist.size(); ++j) {
                    short short1 = ((NBTTagCompound) nbttaglist.get(j)).getShort("id");
                    short short2 = ((NBTTagCompound) nbttaglist.get(j)).getShort("lvl");

                    if (short1 == i) {
                        return short2;
                    }
                }

                return 0;
            }
        }
    }

    public static Map a(ItemStack itemstack) {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        NBTTagList nbttaglist = itemstack.id == Item.ENCHANTED_BOOK.id ? Item.ENCHANTED_BOOK.g(itemstack) : itemstack.getEnchantments();

        if (nbttaglist != null) {
            for (int i = 0; i < nbttaglist.size(); ++i) {
                short short1 = ((NBTTagCompound) nbttaglist.get(i)).getShort("id");
                short short2 = ((NBTTagCompound) nbttaglist.get(i)).getShort("lvl");

                linkedhashmap.put(Integer.valueOf(short1), Integer.valueOf(short2));
            }
        }

        return linkedhashmap;
    }

    public static void a(Map map, ItemStack itemstack) {
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            int i = ((Integer) iterator.next()).intValue();
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.setShort("id", (short) i);
            nbttagcompound.setShort("lvl", (short) ((Integer) map.get(Integer.valueOf(i))).intValue());
            nbttaglist.add(nbttagcompound);
            if (itemstack.id == Item.ENCHANTED_BOOK.id) {
                Item.ENCHANTED_BOOK.a(itemstack, new EnchantmentInstance(i, ((Integer) map.get(Integer.valueOf(i))).intValue()));
            }
        }

        if (nbttaglist.size() > 0) {
            if (itemstack.id != Item.ENCHANTED_BOOK.id) {
                itemstack.a("ench", (NBTBase) nbttaglist);
            }
        } else if (itemstack.hasTag()) {
            itemstack.getTag().remove("ench");
        }
    }

    public static int getEnchantmentLevel(int i, ItemStack[] aitemstack) {
        if (aitemstack == null) {
            return 0;
        } else {
            int j = 0;
            ItemStack[] aitemstack1 = aitemstack;
            int k = aitemstack.length;

            for (int l = 0; l < k; ++l) {
                ItemStack itemstack = aitemstack1[l];
                int i1 = getEnchantmentLevel(i, itemstack);

                if (i1 > j) {
                    j = i1;
                }
            }

            return j;
        }
    }

    private static void a(EnchantmentModifier enchantmentmodifier, ItemStack itemstack) {
        if (itemstack != null) {
            NBTTagList nbttaglist = itemstack.getEnchantments();

            if (nbttaglist != null) {
                for (int i = 0; i < nbttaglist.size(); ++i) {
                    short short1 = ((NBTTagCompound) nbttaglist.get(i)).getShort("id");
                    short short2 = ((NBTTagCompound) nbttaglist.get(i)).getShort("lvl");

                    if (Enchantment.byId[short1] != null) {
                        enchantmentmodifier.a(Enchantment.byId[short1], short2);
                    }
                }
            }
        }
    }

    private static void a(EnchantmentModifier enchantmentmodifier, ItemStack[] aitemstack) {
        ItemStack[] aitemstack1 = aitemstack;
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j) {
            ItemStack itemstack = aitemstack1[j];

            a(enchantmentmodifier, itemstack);
        }
    }

    public static int a(ItemStack[] aitemstack, DamageSource damagesource) {
        b.a = 0;
        b.b = damagesource;
        a((EnchantmentModifier) b, aitemstack);
        if (b.a > 25) {
            b.a = 25;
        }

        return (b.a + 1 >> 1) + random.nextInt((b.a >> 1) + 1);
    }

    public static int a(EntityLiving entityliving, EntityLiving entityliving1) {
        c.a = 0;
        c.b = entityliving1;
        a((EnchantmentModifier) c, entityliving.bG());
        return c.a > 0 ? 1 + random.nextInt(c.a) : 0;
    }

    public static int getKnockbackEnchantmentLevel(EntityLiving entityliving, EntityLiving entityliving1) {
        return getEnchantmentLevel(Enchantment.KNOCKBACK.id, entityliving.bG());
    }

    public static int getFireAspectEnchantmentLevel(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.FIRE_ASPECT.id, entityliving.bG());
    }

    public static int getOxygenEnchantmentLevel(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.OXYGEN.id, entityliving.getEquipment());
    }

    public static int getDigSpeedEnchantmentLevel(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.DIG_SPEED.id, entityliving.bG());
    }

    public static boolean hasSilkTouchEnchantment(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.SILK_TOUCH.id, entityliving.bG()) > 0;
    }

    public static int getBonusBlockLootEnchantmentLevel(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS.id, entityliving.bG());
    }

    public static int getBonusMonsterLootEnchantmentLevel(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS.id, entityliving.bG());
    }

    public static boolean hasWaterWorkerEnchantment(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.WATER_WORKER.id, entityliving.getEquipment()) > 0;
    }

    public static int getThornsEnchantmentLevel(EntityLiving entityliving) {
        return getEnchantmentLevel(Enchantment.THORNS.id, entityliving.getEquipment());
    }

    public static ItemStack a(Enchantment enchantment, EntityLiving entityliving) {
        ItemStack[] aitemstack = entityliving.getEquipment();
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j) {
            ItemStack itemstack = aitemstack[j];

            if (itemstack != null && getEnchantmentLevel(enchantment.id, itemstack) > 0) {
                return itemstack;
            }
        }

        return null;
    }

    public static int a(Random random, int i, int j, ItemStack itemstack) {
        Item item = itemstack.getItem();
        int k = item.c();

        if (k <= 0) {
            return 0;
        } else {
            if (j > 15) {
                j = 15;
            }

            int l = random.nextInt(8) + 1 + (j >> 1) + random.nextInt(j + 1);

            return i == 0 ? Math.max(l / 3, 1) : (i == 1 ? l * 2 / 3 + 1 : Math.max(l, j * 2));
        }
    }

    public static ItemStack a(Random random, ItemStack itemstack, int i) {
        List list = b(random, itemstack, i);
        boolean flag = itemstack.id == Item.BOOK.id;

        if (flag) {
            itemstack.id = Item.ENCHANTED_BOOK.id;
        }

        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EnchantmentInstance enchantmentinstance = (EnchantmentInstance) iterator.next();

                if (flag) {
                    Item.ENCHANTED_BOOK.a(itemstack, enchantmentinstance);
                } else {
                    itemstack.addEnchantment(enchantmentinstance.enchantment, enchantmentinstance.level);
                }
            }
        }

        return itemstack;
    }

    public static List b(Random random, ItemStack itemstack, int i) {
        Item item = itemstack.getItem();
        int j = item.c();

        if (j <= 0) {
            return null;
        } else {
            j /= 2;
            j = 1 + random.nextInt((j >> 1) + 1) + random.nextInt((j >> 1) + 1);
            int k = j + i;
            float f = (random.nextFloat() + random.nextFloat() - 1.0F) * 0.15F;
            int l = (int) ((float) k * (1.0F + f) + 0.5F);

            if (l < 1) {
                l = 1;
            }

            ArrayList arraylist = null;
            Map map = b(l, itemstack);

            if (map != null && !map.isEmpty()) {
                EnchantmentInstance enchantmentinstance = (EnchantmentInstance) WeightedRandom.a(random, map.values());

                if (enchantmentinstance != null) {
                    arraylist = new ArrayList();
                    arraylist.add(enchantmentinstance);

                    for (int i1 = l; random.nextInt(50) <= i1; i1 >>= 1) {
                        Iterator iterator = map.keySet().iterator();

                        while (iterator.hasNext()) {
                            Integer integer = (Integer) iterator.next();
                            boolean flag = true;
                            Iterator iterator1 = arraylist.iterator();

                            while (true) {
                                if (iterator1.hasNext()) {
                                    EnchantmentInstance enchantmentinstance1 = (EnchantmentInstance) iterator1.next();

                                    if (enchantmentinstance1.enchantment.a(Enchantment.byId[integer.intValue()])) {
                                        continue;
                                    }

                                    flag = false;
                                }

                                if (!flag) {
                                    iterator.remove();
                                }
                                break;
                            }
                        }

                        if (!map.isEmpty()) {
                            EnchantmentInstance enchantmentinstance2 = (EnchantmentInstance) WeightedRandom.a(random, map.values());

                            arraylist.add(enchantmentinstance2);
                        }
                    }
                }
            }

            return arraylist;
        }
    }

    public static Map b(int i, ItemStack itemstack) {
        Item item = itemstack.getItem();
        HashMap hashmap = null;
        boolean flag = itemstack.id == Item.BOOK.id;
        Enchantment[] aenchantment = Enchantment.byId;
        int j = aenchantment.length;

        for (int k = 0; k < j; ++k) {
            Enchantment enchantment = aenchantment[k];

            if (enchantment != null && (enchantment.slot.canEnchant(item) || flag)) {
                for (int l = enchantment.getStartLevel(); l <= enchantment.getMaxLevel(); ++l) {
                    if (i >= enchantment.a(l) && i <= enchantment.b(l)) {
                        if (hashmap == null) {
                            hashmap = new HashMap();
                        }

                        hashmap.put(Integer.valueOf(enchantment.id), new EnchantmentInstance(enchantment, l));
                    }
                }
            }
        }

        return hashmap;
    }
}
