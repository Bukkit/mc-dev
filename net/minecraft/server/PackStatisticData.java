package net.minecraft.server;

class PackStatisticData {

    private final long a;
    private final int b;
    private final double c;

    private PackStatisticData(long i, int j, double d0) {
        this.a = i;
        this.b = j;
        this.c = d0;
    }

    public PackStatisticData a(long i) {
        return new PackStatisticData(i + this.a, this.b + 1, (double) ((i + this.a) / (long) (this.b + 1)));
    }

    public long a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return "{totalBytes=" + this.a + ", count=" + this.b + ", averageBytes=" + this.c + '}';
    }

    PackStatisticData(long i, int j, double d0, ModdingApi moddingapi) {
        this(i, j, d0);
    }

    static long a(PackStatisticData packstatisticdata) {
        return packstatisticdata.a;
    }

    static int b(PackStatisticData packstatisticdata) {
        return packstatisticdata.b;
    }
}
