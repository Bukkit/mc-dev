package net.minecraft.server;

final class WorldGenStrongholdPieceWeight3 extends WorldGenStrongholdPieceWeight {

    WorldGenStrongholdPieceWeight3(Class oclass, int i, int j) {
        super(oclass, i, j);
    }

    public boolean a(int i) {
        return super.a(i) && i > 5;
    }
}
