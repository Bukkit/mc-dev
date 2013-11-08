package net.minecraft.server;

import net.minecraft.util.com.google.common.base.Charsets;

public class StatusChallengeUtils {

    public static char[] a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] abyte, int i, int j) {
        int k = j - 1;

        int l;

        for (l = i > k ? k : i; 0 != abyte[l] && l < k; ++l) {
            ;
        }

        return new String(abyte, i, l - i, Charsets.UTF_8);
    }

    public static int b(byte[] abyte, int i) {
        return b(abyte, i, abyte.length);
    }

    public static int b(byte[] abyte, int i, int j) {
        return 0 > j - i - 4 ? 0 : abyte[i + 3] << 24 | (abyte[i + 2] & 255) << 16 | (abyte[i + 1] & 255) << 8 | abyte[i] & 255;
    }

    public static int c(byte[] abyte, int i, int j) {
        return 0 > j - i - 4 ? 0 : abyte[i] << 24 | (abyte[i + 1] & 255) << 16 | (abyte[i + 2] & 255) << 8 | abyte[i + 3] & 255;
    }

    public static String a(byte b0) {
        return "" + a[(b0 & 240) >>> 4] + a[b0 & 15];
    }
}
