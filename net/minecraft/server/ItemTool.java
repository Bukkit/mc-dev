package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bg;
    private float bh = 4.0F;
    private int bi;
    protected EnumToolMaterial a;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.a = enumtoolmaterial;
        this.bg = ablock;
        this.maxStackSize = 1;
        this.durability = enumtoolmaterial.a();
        this.bh = enumtoolmaterial.b();
        this.bi = j + enumtoolmaterial.c();
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bg.length; ++i) {
            if (this.bg[i] == block) {
                return this.bh;
            }
        }

        return 1.0F;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving) {
        itemstack.b(2);
    }

    public void a(ItemStack itemstack, int i, int j, int k, int l) {
        itemstack.b(1);
    }

    public int a(Entity entity) {
        return this.bi;
    }
}
