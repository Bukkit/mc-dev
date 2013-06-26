package net.minecraft.server;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.util.ArrayList;

public class MerchantRecipeList extends ArrayList {

    public MerchantRecipeList() {}

    public MerchantRecipeList(NBTTagCompound nbttagcompound) {
        this.a(nbttagcompound);
    }

    public MerchantRecipe a(ItemStack itemstack, ItemStack itemstack1, int i) {
        if (i > 0 && i < this.size()) {
            MerchantRecipe merchantrecipe = (MerchantRecipe) this.get(i);

            return itemstack.id == merchantrecipe.getBuyItem1().id && (itemstack1 == null && !merchantrecipe.hasSecondItem() || merchantrecipe.hasSecondItem() && itemstack1 != null && merchantrecipe.getBuyItem2().id == itemstack1.id) && itemstack.count >= merchantrecipe.getBuyItem1().count && (!merchantrecipe.hasSecondItem() || itemstack1.count >= merchantrecipe.getBuyItem2().count) ? merchantrecipe : null;
        } else {
            for (int j = 0; j < this.size(); ++j) {
                MerchantRecipe merchantrecipe1 = (MerchantRecipe) this.get(j);

                if (itemstack.id == merchantrecipe1.getBuyItem1().id && itemstack.count >= merchantrecipe1.getBuyItem1().count && (!merchantrecipe1.hasSecondItem() && itemstack1 == null || merchantrecipe1.hasSecondItem() && itemstack1 != null && merchantrecipe1.getBuyItem2().id == itemstack1.id && itemstack1.count >= merchantrecipe1.getBuyItem2().count)) {
                    return merchantrecipe1;
                }
            }

            return null;
        }
    }

    public void a(MerchantRecipe merchantrecipe) {
        for (int i = 0; i < this.size(); ++i) {
            MerchantRecipe merchantrecipe1 = (MerchantRecipe) this.get(i);

            if (merchantrecipe.a(merchantrecipe1)) {
                if (merchantrecipe.b(merchantrecipe1)) {
                    this.set(i, merchantrecipe);
                }

                return;
            }
        }

        this.add(merchantrecipe);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte((byte) (this.size() & 255));

        for (int i = 0; i < this.size(); ++i) {
            MerchantRecipe merchantrecipe = (MerchantRecipe) this.get(i);

            Packet.a(merchantrecipe.getBuyItem1(), (DataOutput) dataoutputstream);
            Packet.a(merchantrecipe.getBuyItem3(), (DataOutput) dataoutputstream);
            ItemStack itemstack = merchantrecipe.getBuyItem2();

            dataoutputstream.writeBoolean(itemstack != null);
            if (itemstack != null) {
                Packet.a(itemstack, (DataOutput) dataoutputstream);
            }

            dataoutputstream.writeBoolean(merchantrecipe.g());
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.getList("Recipes");

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.get(i);

            this.add(new MerchantRecipe(nbttagcompound1));
        }
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList("Recipes");

        for (int i = 0; i < this.size(); ++i) {
            MerchantRecipe merchantrecipe = (MerchantRecipe) this.get(i);

            nbttaglist.add(merchantrecipe.i());
        }

        nbttagcompound.set("Recipes", nbttaglist);
        return nbttagcompound;
    }
}
