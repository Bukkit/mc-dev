package net.minecraft.server;

public enum EnumEntitySize {

    SIZE_1("SIZE_1", 0), SIZE_2("SIZE_2", 1), SIZE_3("SIZE_3", 2), SIZE_4("SIZE_4", 3), SIZE_5("SIZE_5", 4), SIZE_6("SIZE_6", 5);
    private static final EnumEntitySize[] g = new EnumEntitySize[] { SIZE_1, SIZE_2, SIZE_3, SIZE_4, SIZE_5, SIZE_6};

    private EnumEntitySize(String s, int i) {}

    public int a(double d0) {
        double d1 = d0 - ((double) MathHelper.floor(d0) + 0.5D);

        switch (EntitySizes.a[this.ordinal()]) {
        case 1:
            if (d1 < 0.0D) {
                if (d1 < -0.3125D) {
                    return MathHelper.f(d0 * 32.0D);
                }
            } else if (d1 < 0.3125D) {
                return MathHelper.f(d0 * 32.0D);
            }

            return MathHelper.floor(d0 * 32.0D);

        case 2:
            if (d1 < 0.0D) {
                if (d1 < -0.3125D) {
                    return MathHelper.floor(d0 * 32.0D);
                }
            } else if (d1 < 0.3125D) {
                return MathHelper.floor(d0 * 32.0D);
            }

            return MathHelper.f(d0 * 32.0D);

        case 3:
            if (d1 > 0.0D) {
                return MathHelper.floor(d0 * 32.0D);
            }

            return MathHelper.f(d0 * 32.0D);

        case 4:
            if (d1 < 0.0D) {
                if (d1 < -0.1875D) {
                    return MathHelper.f(d0 * 32.0D);
                }
            } else if (d1 < 0.1875D) {
                return MathHelper.f(d0 * 32.0D);
            }

            return MathHelper.floor(d0 * 32.0D);

        case 5:
            if (d1 < 0.0D) {
                if (d1 < -0.1875D) {
                    return MathHelper.floor(d0 * 32.0D);
                }
            } else if (d1 < 0.1875D) {
                return MathHelper.floor(d0 * 32.0D);
            }

            return MathHelper.f(d0 * 32.0D);

        case 6:
        default:
            if (d1 > 0.0D) {
                return MathHelper.f(d0 * 32.0D);
            } else {
                return MathHelper.floor(d0 * 32.0D);
            }
        }
    }
}
