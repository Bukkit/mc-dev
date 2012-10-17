package net.minecraft.server;

public class BlockCarrots extends BlockCrops {

    public BlockCarrots(int i) {
        super(i, 200);
    }

    public int a(int i, int j) {
        if (j < 7) {
            if (j == 6) {
                j = 5;
            }

            return this.textureId + (j >> 1);
        } else {
            return this.textureId + 3;
        }
    }

    protected int h() {
        return Item.CARROT.id;
    }

    protected int j() {
        return Item.CARROT.id;
    }
}
