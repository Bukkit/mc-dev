package net.minecraft.server;

import java.util.Set;

import net.minecraft.util.com.google.common.collect.Multimap;

public class ItemTool extends Item {

    private Set c;
    protected float a = 4.0F;
    private float d;
    protected EnumToolMaterial b;

    protected ItemTool(float f, EnumToolMaterial enumtoolmaterial, Set set) {
        this.b = enumtoolmaterial;
        this.c = set;
        this.maxStackSize = 1;
        this.setMaxDurability(enumtoolmaterial.a());
        this.a = enumtoolmaterial.b();
        this.d = f + enumtoolmaterial.c();
        this.a(CreativeModeTab.i);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return this.c.contains(block) ? this.a : 1.0F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(2, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, World world, Block block, int i, int j, int k, EntityLiving entityliving) {
        if ((double) block.f(world, i, j, k) != 0.0D) {
            itemstack.damage(1, entityliving);
        }

        return true;
    }

    public EnumToolMaterial i() {
        return this.b;
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

        multimap.put(GenericAttributes.e.a(), new AttributeModifier(f, "Tool modifier", (double) this.d, 0));
        return multimap;
    }
}
