package net.minecraft.server;

public class ChunkSection {

    private int yPos;
    private int nonEmptyBlockCount;
    private int tickingBlockCount;
    private byte[] blockIds;
    private NibbleArray extBlockIds;
    private NibbleArray blockData;
    private NibbleArray blockLight;
    private NibbleArray skyLight;

    public ChunkSection(int i) {
        this.yPos = i;
        this.blockIds = new byte[4096];
        this.blockData = new NibbleArray(this.blockIds.length, 4);
        this.skyLight = new NibbleArray(this.blockIds.length, 4);
        this.blockLight = new NibbleArray(this.blockIds.length, 4);
    }

    public int a(int i, int j, int k) {
        int l = this.blockIds[j << 8 | k << 4 | i] & 255;

        return this.extBlockIds != null ? this.extBlockIds.a(i, j, k) << 8 | l : l;
    }

    public void a(int i, int j, int k, int l) {
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

    public int b(int i, int j, int k) {
        return this.blockData.a(i, j, k);
    }

    public void b(int i, int j, int k, int l) {
        this.blockData.a(i, j, k, l);
    }

    public boolean a() {
        return this.nonEmptyBlockCount == 0;
    }

    public boolean b() {
        return this.tickingBlockCount > 0;
    }

    public int d() {
        return this.yPos;
    }

    public void c(int i, int j, int k, int l) {
        this.skyLight.a(i, j, k, l);
    }

    public int c(int i, int j, int k) {
        return this.skyLight.a(i, j, k);
    }

    public void d(int i, int j, int k, int l) {
        this.blockLight.a(i, j, k, l);
    }

    public int d(int i, int j, int k) {
        return this.blockLight.a(i, j, k);
    }

    public void recalcBlockCounts() {
        this.nonEmptyBlockCount = 0;
        this.tickingBlockCount = 0;

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    int l = this.a(i, j, k);

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

    public byte[] g() {
        return this.blockIds;
    }

    public NibbleArray i() {
        return this.extBlockIds;
    }

    public NibbleArray j() {
        return this.blockData;
    }

    public NibbleArray k() {
        return this.blockLight;
    }

    public NibbleArray l() {
        return this.skyLight;
    }

    public void a(byte[] abyte) {
        this.blockIds = abyte;
    }

    public void a(NibbleArray nibblearray) {
        this.extBlockIds = nibblearray;
    }

    public void b(NibbleArray nibblearray) {
        this.blockData = nibblearray;
    }

    public void c(NibbleArray nibblearray) {
        this.blockLight = nibblearray;
    }

    public void d(NibbleArray nibblearray) {
        this.skyLight = nibblearray;
    }
}
