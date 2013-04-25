package net.minecraft.server;

public class ChunkSection {

    private int yPos;
    private int nonEmptyBlockCount;
    private int tickingBlockCount;
    private byte[] blockIds;
    private NibbleArray extBlockIds;
    private NibbleArray blockData;
    private NibbleArray emittedLight;
    private NibbleArray skyLight;

    public ChunkSection(int i, boolean flag) {
        this.yPos = i;
        this.blockIds = new byte[4096];
        this.blockData = new NibbleArray(this.blockIds.length, 4);
        this.emittedLight = new NibbleArray(this.blockIds.length, 4);
        if (flag) {
            this.skyLight = new NibbleArray(this.blockIds.length, 4);
        }
    }

    public int getTypeId(int i, int j, int k) {
        int l = this.blockIds[j << 8 | k << 4 | i] & 255;

        return this.extBlockIds != null ? this.extBlockIds.a(i, j, k) << 8 | l : l;
    }

    public void setTypeId(int i, int j, int k, int l) {
        int i1 = this.blockIds[j << 8 | k << 4 | i] & 255;

        if (this.extBlockIds != null) {
            i1 |= this.extBlockIds.a(i, j, k) << 8;
        }

        if (i1 == 0 && l != 0) {
            ++this.nonEmptyBlockCount;
            if (Block.byId[l] != null && Block.byId[l].isTicking()) {
                ++this.tickingBlockCount;
            }
        } else if (i1 != 0 && l == 0) {
            --this.nonEmptyBlockCount;
            if (Block.byId[i1] != null && Block.byId[i1].isTicking()) {
                --this.tickingBlockCount;
            }
        } else if (Block.byId[i1] != null && Block.byId[i1].isTicking() && (Block.byId[l] == null || !Block.byId[l].isTicking())) {
            --this.tickingBlockCount;
        } else if ((Block.byId[i1] == null || !Block.byId[i1].isTicking()) && Block.byId[l] != null && Block.byId[l].isTicking()) {
            ++this.tickingBlockCount;
        }

        this.blockIds[j << 8 | k << 4 | i] = (byte) (l & 255);
        if (l > 255) {
            if (this.extBlockIds == null) {
                this.extBlockIds = new NibbleArray(this.blockIds.length, 4);
            }

            this.extBlockIds.a(i, j, k, (l & 3840) >> 8);
        } else if (this.extBlockIds != null) {
            this.extBlockIds.a(i, j, k, 0);
        }
    }

    public int getData(int i, int j, int k) {
        return this.blockData.a(i, j, k);
    }

    public void setData(int i, int j, int k, int l) {
        this.blockData.a(i, j, k, l);
    }

    public boolean isEmpty() {
        return this.nonEmptyBlockCount == 0;
    }

    public boolean shouldTick() {
        return this.tickingBlockCount > 0;
    }

    public int getYPosition() {
        return this.yPos;
    }

    public void setSkyLight(int i, int j, int k, int l) {
        this.skyLight.a(i, j, k, l);
    }

    public int getSkyLight(int i, int j, int k) {
        return this.skyLight.a(i, j, k);
    }

    public void setEmittedLight(int i, int j, int k, int l) {
        this.emittedLight.a(i, j, k, l);
    }

    public int getEmittedLight(int i, int j, int k) {
        return this.emittedLight.a(i, j, k);
    }

    public void recalcBlockCounts() {
        this.nonEmptyBlockCount = 0;
        this.tickingBlockCount = 0;

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    int l = this.getTypeId(i, j, k);

                    if (l > 0) {
                        if (Block.byId[l] == null) {
                            this.blockIds[j << 8 | k << 4 | i] = 0;
                            if (this.extBlockIds != null) {
                                this.extBlockIds.a(i, j, k, 0);
                            }
                        } else {
                            ++this.nonEmptyBlockCount;
                            if (Block.byId[l].isTicking()) {
                                ++this.tickingBlockCount;
                            }
                        }
                    }
                }
            }
        }
    }

    public byte[] getIdArray() {
        return this.blockIds;
    }

    public NibbleArray getExtendedIdArray() {
        return this.extBlockIds;
    }

    public NibbleArray getDataArray() {
        return this.blockData;
    }

    public NibbleArray getEmittedLightArray() {
        return this.emittedLight;
    }

    public NibbleArray getSkyLightArray() {
        return this.skyLight;
    }

    public void setIdArray(byte[] abyte) {
        this.blockIds = abyte;
    }

    public void setExtendedIdArray(NibbleArray nibblearray) {
        this.extBlockIds = nibblearray;
    }

    public void setDataArray(NibbleArray nibblearray) {
        this.blockData = nibblearray;
    }

    public void setEmittedLightArray(NibbleArray nibblearray) {
        this.emittedLight = nibblearray;
    }

    public void setSkyLightArray(NibbleArray nibblearray) {
        this.skyLight = nibblearray;
    }
}
