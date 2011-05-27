package net.minecraft.server;

public class EntityList {

    private transient EntityListEntry[] a = new EntityListEntry[16];
    private transient int b;
    private int c = 12;
    private final float d = 0.75F;
    private transient volatile int e;

    public EntityList() {}

    private static int g(int i) {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j) {
        return i & j - 1;
    }

    public Object a(int i) {
        int j = g(i);

        for (EntityListEntry entitylistentry = this.a[a(j, this.a.length)]; entitylistentry != null; entitylistentry = entitylistentry.c) {
            if (entitylistentry.a == i) {
                return entitylistentry.b;
            }
        }

        return null;
    }

    public boolean b(int i) {
        return this.c(i) != null;
    }

    final EntityListEntry c(int i) {
        int j = g(i);

        for (EntityListEntry entitylistentry = this.a[a(j, this.a.length)]; entitylistentry != null; entitylistentry = entitylistentry.c) {
            if (entitylistentry.a == i) {
                return entitylistentry;
            }
        }

        return null;
    }

    public void a(int i, Object object) {
        int j = g(i);
        int k = a(j, this.a.length);

        for (EntityListEntry entitylistentry = this.a[k]; entitylistentry != null; entitylistentry = entitylistentry.c) {
            if (entitylistentry.a == i) {
                entitylistentry.b = object;
            }
        }

        ++this.e;
        this.a(j, i, object, k);
    }

    private void h(int i) {
        EntityListEntry[] aentitylistentry = this.a;
        int j = aentitylistentry.length;

        if (j == 1073741824) {
            this.c = Integer.MAX_VALUE;
        } else {
            EntityListEntry[] aentitylistentry1 = new EntityListEntry[i];

            this.a(aentitylistentry1);
            this.a = aentitylistentry1;
            this.c = (int) ((float) i * this.d);
        }
    }

    private void a(EntityListEntry[] aentitylistentry) {
        EntityListEntry[] aentitylistentry1 = this.a;
        int i = aentitylistentry.length;

        for (int j = 0; j < aentitylistentry1.length; ++j) {
            EntityListEntry entitylistentry = aentitylistentry1[j];

            if (entitylistentry != null) {
                aentitylistentry1[j] = null;

                EntityListEntry entitylistentry1;

                do {
                    entitylistentry1 = entitylistentry.c;
                    int k = a(entitylistentry.d, i);

                    entitylistentry.c = aentitylistentry[k];
                    aentitylistentry[k] = entitylistentry;
                    entitylistentry = entitylistentry1;
                } while (entitylistentry1 != null);
            }
        }
    }

    public Object d(int i) {
        EntityListEntry entitylistentry = this.e(i);

        return entitylistentry == null ? null : entitylistentry.b;
    }

    final EntityListEntry e(int i) {
        int j = g(i);
        int k = a(j, this.a.length);
        EntityListEntry entitylistentry = this.a[k];

        EntityListEntry entitylistentry1;
        EntityListEntry entitylistentry2;

        for (entitylistentry1 = entitylistentry; entitylistentry1 != null; entitylistentry1 = entitylistentry2) {
            entitylistentry2 = entitylistentry1.c;
            if (entitylistentry1.a == i) {
                ++this.e;
                --this.b;
                if (entitylistentry == entitylistentry1) {
                    this.a[k] = entitylistentry2;
                } else {
                    entitylistentry.c = entitylistentry2;
                }

                return entitylistentry1;
            }

            entitylistentry = entitylistentry1;
        }

        return entitylistentry1;
    }

    public void a() {
        ++this.e;
        EntityListEntry[] aentitylistentry = this.a;

        for (int i = 0; i < aentitylistentry.length; ++i) {
            aentitylistentry[i] = null;
        }

        this.b = 0;
    }

    private void a(int i, int j, Object object, int k) {
        EntityListEntry entitylistentry = this.a[k];

        this.a[k] = new EntityListEntry(i, j, object, entitylistentry);
        if (this.b++ >= this.c) {
            this.h(2 * this.a.length);
        }
    }

    static int f(int i) {
        return g(i);
    }
}
