package net.minecraft.server;

class WorldGenStrongholdPieceWeight2 {

    static final int[] a = new int[WorldGenStrongholdDoorType.values().length];

    static {
        try {
            a[WorldGenStrongholdDoorType.a.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            a[WorldGenStrongholdDoorType.b.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            a[WorldGenStrongholdDoorType.c.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            a[WorldGenStrongholdDoorType.d.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }
    }
}
