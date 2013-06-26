package net.minecraft.server;

public class VillageDoor {

    public final int locX;
    public final int locY;
    public final int locZ;
    public final int d;
    public final int e;
    public int addedTime;
    public boolean removed;
    private int bookings;

    public VillageDoor(int i, int j, int k, int l, int i1, int j1) {
        this.locX = i;
        this.locY = j;
        this.locZ = k;
        this.d = l;
        this.e = i1;
        this.addedTime = j1;
    }

    public int b(int i, int j, int k) {
        int l = i - this.locX;
        int i1 = j - this.locY;
        int j1 = k - this.locZ;

        return l * l + i1 * i1 + j1 * j1;
    }

    public int c(int i, int j, int k) {
        int l = i - this.locX - this.d;
        int i1 = j - this.locY;
        int j1 = k - this.locZ - this.e;

        return l * l + i1 * i1 + j1 * j1;
    }

    public int getIndoorsX() {
        return this.locX + this.d;
    }

    public int getIndoorsY() {
        return this.locY;
    }

    public int getIndoorsZ() {
        return this.locZ + this.e;
    }

    public boolean a(int i, int j) {
        int k = i - this.locX;
        int l = j - this.locZ;

        return k * this.d + l * this.e >= 0;
    }

    public void d() {
        this.bookings = 0;
    }

    public void e() {
        ++this.bookings;
    }

    public int f() {
        return this.bookings;
    }
}
