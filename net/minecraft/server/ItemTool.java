package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] c;
    protected float a = 4.0F;
    private int cl;
    protected EnumToolMaterial b;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.b = enumtoolmaterial;
        this.c = ablock;
        this.maxStackSize = 1;
        this.setMaxDurability(enumtoolmaterial.a());
        this.a = enumtoolmaterial.b();
        this.cl = j + enumtoolmaterial.c();
        this.a(CreativeModeTab.i);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        Block[] ablock = this.c;
        int i = ablock.length;

        for (int j = 0; j < i; ++j) {
            Block block1 = ablock[j];

            if (block1 == block) {
                return this.a;
            }
        }

        return 1.0F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(2, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, World world, int i, int j, int k, int l, EntityLiving entityliving) {
        if ((double) Block.byId[i].m(world, j, k, l) != 0.0D) {
            itemstack.damage(1, entityliving);
        }

        return true;
    }

    public int a(Entity entity) {
        return this.cl;
    }

    public int c() {
        return this.b.e();
    }

    public String g() {
        return this.b.toString();
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.b.f() == itemstack1.id ? true : super.a(itemstack, itemstack1);
    }
}
