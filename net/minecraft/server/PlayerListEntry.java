package net.minecraft.server;

class PlayerListEntry {

    final long a;
    Object b;
    PlayerListEntry c;
    final int d;

    PlayerListEntry(int i, long j, Object object, PlayerListEntry playerlistentry) {
        this.b = object;
        this.c = playerlistentry;
        this.a = j;
        this.d = i;
    }

    public final long a() {
        return this.a;
    }

    public final Object b() {
        return this.b;
    }

    public final boolean equals(Object object) {
        if (!(object instanceof PlayerListEntry)) {
            return false;
        } else {
            PlayerListEntry playerlistentry = (PlayerListEntry) object;
            Long olong = Long.valueOf(this.a());
            Long olong1 = Long.valueOf(playerlistentry.a());

            if (olong == olong1 || olong != null && olong.equals(olong1)) {
                Object object1 = this.b();
                Object object2 = playerlistentry.b();

                if (object1 == object2 || object1 != null && object1.equals(object2)) {
                    return true;
                }
            }

            return false;
        }
    }

    public final int hashCode() {
        return PlayerList.d(this.a);
    }

    public final String toString() {
        return this.a() + "=" + this.b();
    }
}
