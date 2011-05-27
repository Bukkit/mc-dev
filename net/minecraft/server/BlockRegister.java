package net.minecraft.server;

public class BlockRegister {

    private static byte[] a = new byte[256];

    public BlockRegister() {}

    public static void a(byte[] abyte) {
        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = a[abyte[i] & 255];
        }
    }

    static {
        try {
            for (int i = 0; i < 256; ++i) {
                byte b0 = (byte) i;

                if (b0 != 0 && Block.byId[b0 & 255] == null) {
                    b0 = 0;
                }

                a[i] = b0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
