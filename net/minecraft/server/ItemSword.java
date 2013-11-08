package net.minecraft.server;

import net.minecraft.util.com.google.common.collect.Multimap;

public class ItemSword extends Item {

    private float damage;
    private final EnumToolMaterial b;

    public ItemSword(EnumToolMaterial enumtoolmaterial) {
        this.b = enumtoolmaterial;
        this.maxStackSize = 1;
        this.setMaxDurability(enumtoolmaterial.a());
        this.a(CreativeModeTab.j);
        this.damage = 4.0F + enumtoolmaterial.c();
    }

    public float i() {
        return this.b.c();
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        if (block == Blocks.WEB) {
            return 15.0F;
        } else {
            Material material = block.getMaterial();

            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && material != Material.LEAVES && material != Material.PUMPKIN ? 1.0F : 1.5F;
        }
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(1, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, World world, Block block, int i, int j, int k, EntityLiving entityliving) {
        if ((double) block.f(world, i, j, k) != 0.0D) {
            itemstack.damage(2, entityliving);
        }

        return true;
    }

    public EnumAnimation d(ItemStack itemstack) {
        return EnumAnimation.BLOCK;
    }

    public int d_(ItemStack itemstack) {
        return 72000;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.a(itemstack, this.d_(itemstack));
        return itemstack;
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block == Blocks.WEB;
    }

    public int c() {
        return this.b.e();
    }

    public String j() {
        return this.b.toString();
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.b.f() == itemstack1.getItem() ? true : super.a(itemstack, itemstack1);
    }

    public Multimap k() {
        Multimap multimap = super.k();

        multimap.put(GenericAttributes.e.a(), new AttributeModifier(f, "Weapon modifier", (double) this.damage, 0));
        return multimap;
    }
}
