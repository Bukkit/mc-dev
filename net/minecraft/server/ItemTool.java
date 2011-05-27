package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bi;
    private float bj = 4.0F;
    private int bk;
    protected EnumToolMaterial a;

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block[] ablock) {
        super(i);
        this.a = enumtoolmaterial;
        this.bi = ablock;
        this.maxStackSize = 1;
        this.durability = enumtoolmaterial.a();
        this.bj = enumtoolmaterial.b();
        this.bk = j + enumtoolmaterial.c();
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bi.length; ++i) {
            if (this.bi[i] == block) {
                return this.bj;
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
        return this.bk;
    }
}
