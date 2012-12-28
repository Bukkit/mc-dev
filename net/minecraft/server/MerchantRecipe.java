package net.minecraft.server;

public class MerchantRecipe {

    private ItemStack buyingItem1;
    private ItemStack buyingItem2;
    private ItemStack sellingItem;
    private int uses;
    private int maxUses;

    public MerchantRecipe(NBTTagCompound nbttagcompound) {
        this.a(nbttagcompound);
    }

    public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2) {
        this.buyingItem1 = itemstack;
        this.buyingItem2 = itemstack1;
        this.sellingItem = itemstack2;
        this.maxUses = 7;
    }

    public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1) {
        this(itemstack, (ItemStack) null, itemstack1);
    }

    public MerchantRecipe(ItemStack itemstack, Item item) {
        this(itemstack, new ItemStack(item));
    }

    public ItemStack getBuyItem1() {
        return this.buyingItem1;
    }

    public ItemStack getBuyItem2() {
        return this.buyingItem2;
    }

    public boolean hasSecondItem() {
        return this.buyingItem2 != null;
    }

    public ItemStack getBuyItem3() {
        return this.sellingItem;
    }

    public boolean a(MerchantRecipe merchantrecipe) {
        return this.buyingItem1.id == merchantrecipe.buyingItem1.id && this.sellingItem.id == merchantrecipe.sellingItem.id ? this.buyingItem2 == null && merchantrecipe.buyingItem2 == null || this.buyingItem2 != null && merchantrecipe.buyingItem2 != null && this.buyingItem2.id == merchantrecipe.buyingItem2.id : false;
    }

    public boolean b(MerchantRecipe merchantrecipe) {
        return this.a(merchantrecipe) && (this.buyingItem1.count < merchantrecipe.buyingItem1.count || this.buyingItem2 != null && this.buyingItem2.count < merchantrecipe.buyingItem2.count);
    }

    public void f() {
        ++this.uses;
    }

    public void a(int i) {
        this.maxUses += i;
    }

    public boolean g() {
        return this.uses >= this.maxUses;
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("buy");

        this.buyingItem1 = ItemStack.createStack(nbttagcompound1);
        NBTTagCompound nbttagcompound2 = nbttagcompound.getCompound("sell");

        this.sellingItem = ItemStack.createStack(nbttagcompound2);
        if (nbttagcompound.hasKey("buyB")) {
            this.buyingItem2 = ItemStack.createStack(nbttagcompound.getCompound("buyB"));
        }

        if (nbttagcompound.hasKey("uses")) {
            this.uses = nbttagcompound.getInt("uses");
        }

        if (nbttagcompound.hasKey("maxUses")) {
            this.maxUses = nbttagcompound.getInt("maxUses");
        } else {
            this.maxUses = 7;
        }
    }

    public NBTTagCompound i() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setCompound("buy", this.buyingItem1.save(new NBTTagCompound("buy")));
        nbttagcompound.setCompound("sell", this.sellingItem.save(new NBTTagCompound("sell")));
        if (this.buyingItem2 != null) {
            nbttagcompound.setCompound("buyB", this.buyingItem2.save(new NBTTagCompound("buyB")));
        }

        nbttagcompound.setInt("uses", this.uses);
        nbttagcompound.setInt("maxUses", this.maxUses);
        return nbttagcompound;
    }
}
