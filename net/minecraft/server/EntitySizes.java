package net.minecraft.server;

class EntitySizes {

    static final int[] a = new int[EnumEntitySize.values().length];

    static {
        try {
            a[EnumEntitySize.SIZE_1.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            a[EnumEntitySize.SIZE_2.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            a[EnumEntitySize.SIZE_3.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            a[EnumEntitySize.SIZE_4.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            a[EnumEntitySize.SIZE_5.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            a[EnumEntitySize.SIZE_6.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }
    }
}
