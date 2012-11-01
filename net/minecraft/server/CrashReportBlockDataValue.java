package net.minecraft.server;

import java.util.concurrent.Callable;

final class CrashReportBlockDataValue implements Callable {

    final int a;

    CrashReportBlockDataValue(int i) {
        this.a = i;
    }

    public String a() {
        if (this.a < 0) {
            return "Unknown? (Got " + this.a + ")";
        } else {
            String s = String.format("%4s", new Object[] { Integer.toBinaryString(this.a)}).replace(" ", "0");

            return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(this.a), s});
        }
    }

    public Object call() {
        return this.a();
    }
}
