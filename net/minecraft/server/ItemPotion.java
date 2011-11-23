package net.minecraft.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ItemPotion extends Item {

    private HashMap a = new HashMap();

    public ItemPotion(int i) {
        super(i);
        this.e(1);
        this.a(true);
        this.f(0);
    }

    public List b(ItemStack itemstack) {
        return this.b(itemstack.getData());
    }

    public List b(int i) {
        List list = (List) this.a.get(Integer.valueOf(i));

        if (list == null) {
            list = PotionBrewer.a(i, false);
            this.a.put(Integer.valueOf(i), list);
        }

        return list;
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.count;
        if (!world.isStatic) {
            List list = this.b(itemstack);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    MobEffect mobeffect = (MobEffect) iterator.next();

                    entityhuman.addEffect(new MobEffect(mobeffect));
                }
            }
        }

        if (itemstack.count <= 0) {
            return new ItemStack(Item.GLASS_BOTTLE);
        } else {
            entityhuman.inventory.pickup(new ItemStack(Item.GLASS_BOTTLE));
            return itemstack;
        }
    }

    public int c(ItemStack itemstack) {
        return 32;
    }

    public EnumAnimation d(ItemStack itemstack) {
        return EnumAnimation.c;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (c(itemstack.getData())) {
            --itemstack.count;
            world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
            if (!world.isStatic) {
                world.addEntity(new EntityPotion(world, entityhuman, itemstack.getData()));
            }

            return itemstack;
        } else {
            entityhuman.a(itemstack, this.c(itemstack));
            return itemstack;
        }
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        return false;
    }

    public static boolean c(int i) {
        return (i & 16384) != 0;
    }
}
