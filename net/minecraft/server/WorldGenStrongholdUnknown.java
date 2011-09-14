package net.minecraft.server;

final class WorldGenStrongholdUnknown extends WorldGenStrongholdPieceWeight {

    WorldGenStrongholdUnknown(Class oclass, int i, int j) {
        super(oclass, i, j);
    }

    public boolean a(int i) {
        return super.a(i) && i > 4;
    }
}
