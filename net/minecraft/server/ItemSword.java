package net.minecraft.server;

public class ItemSword extends Item {

    private int damage;
    private final EnumToolMaterial b;

    public ItemSword(int i, EnumToolMaterial enumtoolmaterial) {
        super(i);
        this.b = enumtoolmaterial;
        this.maxStackSize = 1;
        this.setMaxDurability(enumtoolmaterial.a());
        this.a(CreativeModeTab.j);
        this.damage = 4 + enumtoolmaterial.c();
    }

    public int g() {
        return this.b.c();
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        if (block.id == Block.WEB.id) {
            return 15.0F;
        } else {
            Material material = block.material;

            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && material != Material.LEAVES && material != Material.PUMPKIN ? 1.0F : 1.5F;
        }
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(1, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, World world, int i, int j, int k, int l, EntityLiving entityliving) {
        if ((double) Block.byId[i].l(world, j, k, l) != 0.0D) {
            itemstack.damage(2, entityliving);
        }

        return true;
    }

    public int a(Entity entity) {
        return this.damage;
    }

    public EnumAnimation b_(ItemStack itemstack) {
        return EnumAnimation.BLOCK;
    }

    public int c_(ItemStack itemstack) {
        return 72000;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.a(itemstack, this.c_(itemstack));
        return itemstack;
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block.id == Block.WEB.id;
    }

    public int c() {
        return this.b.e();
    }

    public String h() {
        return this.b.toString();
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.b.f() == itemstack1.id ? true : super.a(itemstack, itemstack1);
    }
}
