package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemPotion extends Item {

    private HashMap a = new HashMap();
    private static final Map b = new LinkedHashMap();

    public ItemPotion(int i) {
        super(i);
        this.d(1);
        this.a(true);
        this.setMaxDurability(0);
        this.a(CreativeModeTab.k);
    }

    public List g(ItemStack itemstack) {
        if (itemstack.hasTag() && itemstack.getTag().hasKey("CustomPotionEffects")) {
            ArrayList arraylist = new ArrayList();
            NBTTagList nbttaglist = itemstack.getTag().getList("CustomPotionEffects");

            for (int i = 0; i < nbttaglist.size(); ++i) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.get(i);

                arraylist.add(MobEffect.b(nbttagcompound));
            }

            return arraylist;
        } else {
            List list = (List) this.a.get(Integer.valueOf(itemstack.getData()));

            if (list == null) {
                list = PotionBrewer.getEffects(itemstack.getData(), false);
                this.a.put(Integer.valueOf(itemstack.getData()), list);
            }

            return list;
        }
    }

    public List f(int i) {
        List list = (List) this.a.get(Integer.valueOf(i));

        if (list == null) {
            list = PotionBrewer.getEffects(i, false);
            this.a.put(Integer.valueOf(i), list);
        }

        return list;
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!entityhuman.abilities.canInstantlyBuild) {
            --itemstack.count;
        }

        if (!world.isStatic) {
            List list = this.g(itemstack);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    MobEffect mobeffect = (MobEffect) iterator.next();

                    entityhuman.addEffect(new MobEffect(mobeffect));
                }
            }
        }

        if (!entityhuman.abilities.canInstantlyBuild) {
            if (itemstack.count <= 0) {
                return new ItemStack(Item.GLASS_BOTTLE);
            }

            entityhuman.inventory.pickup(new ItemStack(Item.GLASS_BOTTLE));
        }

        return itemstack;
    }

    public int c_(ItemStack itemstack) {
        return 32;
    }

    public EnumAnimation b_(ItemStack itemstack) {
        return EnumAnimation.c;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (g(itemstack.getData())) {
            if (!entityhuman.abilities.canInstantlyBuild) {
                --itemstack.count;
            }

            world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
            if (!world.isStatic) {
                world.addEntity(new EntityPotion(world, entityhuman, itemstack));
            }

            return itemstack;
        } else {
            entityhuman.a(itemstack, this.c_(itemstack));
            return itemstack;
        }
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        return false;
    }

    public static boolean g(int i) {
        return (i & 16384) != 0;
    }

    public String l(ItemStack itemstack) {
        if (itemstack.getData() == 0) {
            return LocaleI18n.get("item.emptyPotion.name").trim();
        } else {
            String s = "";

            if (g(itemstack.getData())) {
                s = LocaleI18n.get("potion.prefix.grenade").trim() + " ";
            }

            List list = Item.POTION.g(itemstack);
            String s1;

            if (list != null && !list.isEmpty()) {
                s1 = ((MobEffect) list.get(0)).f();
                s1 = s1 + ".postfix";
                return s + LocaleI18n.get(s1).trim();
            } else {
                s1 = PotionBrewer.c(itemstack.getData());
                return LocaleI18n.get(s1).trim() + " " + super.l(itemstack);
            }
        }
    }
}
