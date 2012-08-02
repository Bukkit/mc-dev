package net.minecraft.server;

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

    public List l(ItemStack itemstack) {
        return this.f(itemstack.getData());
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
            List list = this.l(itemstack);

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

    public int a(ItemStack itemstack) {
        return 32;
    }

    public EnumAnimation b(ItemStack itemstack) {
        return EnumAnimation.c;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (g(itemstack.getData())) {
            if (!entityhuman.abilities.canInstantlyBuild) {
                --itemstack.count;
            }

            world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
            if (!world.isStatic) {
                world.addEntity(new EntityPotion(world, entityhuman, itemstack.getData()));
            }

            return itemstack;
        } else {
            entityhuman.a(itemstack, this.a(itemstack));
            return itemstack;
        }
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        return false;
    }

    public static boolean g(int i) {
        return (i & 16384) != 0;
    }
}
