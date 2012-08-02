package net.minecraft.server;

final class WorldGenStrongholdPiece2 extends WorldGenStrongholdPieceWeight {

    WorldGenStrongholdPiece2(Class oclass, int i, int j) {
        super(oclass, i, j);
    }

    public boolean a(int i) {
        return super.a(i) && i > 5;
    }
}
