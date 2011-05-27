package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class EntityPainting extends Entity {

    private int c;
    public int a;
    private int d;
    private int e;
    private int f;
    public EnumArt b;

    public EntityPainting(World world) {
        super(world);
        this.c = 0;
        this.a = 0;
        this.G = 0.0F;
        this.a(0.5F, 0.5F);
    }

    public EntityPainting(World world, int i, int j, int k, int l) {
        this(world);
        this.d = i;
        this.e = j;
        this.f = k;
        ArrayList arraylist = new ArrayList();
        EnumArt[] aenumart = EnumArt.values();
        int i1 = aenumart.length;

        for (int j1 = 0; j1 < i1; ++j1) {
            EnumArt enumart = aenumart[j1];

            this.b = enumart;
            this.a(l);
            if (this.c()) {
                arraylist.add(enumart);
            }
        }

        if (arraylist.size() > 0) {
            this.b = (EnumArt) arraylist.get(this.V.nextInt(arraylist.size()));
        }

        this.a(l);
    }

    public void a(int i) {
        this.a = i;
        this.x = this.v = (float) (i * 90);
        float f = (float) this.b.z;
        float f1 = (float) this.b.A;
        float f2 = (float) this.b.z;

        if (i != 0 && i != 2) {
            f = 0.5F;
        } else {
            f2 = 0.5F;
        }

        f /= 32.0F;
        f1 /= 32.0F;
        f2 /= 32.0F;
        float f3 = (float) this.d + 0.5F;
        float f4 = (float) this.e + 0.5F;
        float f5 = (float) this.f + 0.5F;
        float f6 = 0.5625F;

        if (i == 0) {
            f5 -= f6;
        }

        if (i == 1) {
            f3 -= f6;
        }

        if (i == 2) {
            f5 += f6;
        }

        if (i == 3) {
            f3 += f6;
        }

        if (i == 0) {
            f3 -= this.c(this.b.z);
        }

        if (i == 1) {
            f5 += this.c(this.b.z);
        }

        if (i == 2) {
            f3 += this.c(this.b.z);
        }

        if (i == 3) {
            f5 -= this.c(this.b.z);
        }

        f4 += this.c(this.b.A);
        this.a((double) f3, (double) f4, (double) f5);
        float f7 = -0.00625F;

        this.z.c((double) (f3 - f - f7), (double) (f4 - f1 - f7), (double) (f5 - f2 - f7), (double) (f3 + f + f7), (double) (f4 + f1 + f7), (double) (f5 + f2 + f7));
    }

    private float c(int i) {
        return i == 32 ? 0.5F : (i == 64 ? 0.5F : 0.0F);
    }

    public void b_() {
        if (this.c++ == 100 && !this.c()) {
            this.c = 0;
            this.l();
            this.l.a((Entity) (new EntityItem(this.l, this.p, this.q, this.r, new ItemStack(Item.PAINTING))));
        }
    }

    public boolean c() {
        if (this.l.a((Entity) this, this.z).size() > 0) {
            return false;
        } else {
            int i = this.b.z / 16;
            int j = this.b.A / 16;
            int k = this.d;
            int l = this.e;
            int i1 = this.f;

            if (this.a == 0) {
                k = MathHelper.b(this.p - (double) ((float) this.b.z / 32.0F));
            }

            if (this.a == 1) {
                i1 = MathHelper.b(this.r - (double) ((float) this.b.z / 32.0F));
            }

            if (this.a == 2) {
                k = MathHelper.b(this.p - (double) ((float) this.b.z / 32.0F));
            }

            if (this.a == 3) {
                i1 = MathHelper.b(this.r - (double) ((float) this.b.z / 32.0F));
            }

            l = MathHelper.b(this.q - (double) ((float) this.b.A / 32.0F));

            int j1;

            for (int k1 = 0; k1 < i; ++k1) {
                for (j1 = 0; j1 < j; ++j1) {
                    Material material;

                    if (this.a != 0 && this.a != 2) {
                        material = this.l.c(this.d, l + j1, i1 + k1);
                    } else {
                        material = this.l.c(k + k1, l + j1, this.f);
                    }

                    if (!material.a()) {
                        return false;
                    }
                }
            }

            List list = this.l.b((Entity) this, this.z);

            for (j1 = 0; j1 < list.size(); ++j1) {
                if (list.get(j1) instanceof EntityPainting) {
                    return false;
                }
            }

            return true;
        }
    }

    public boolean c_() {
        return true;
    }

    public boolean a(Entity entity, int i) {
        this.l();
        this.l.a((Entity) (new EntityItem(this.l, this.p, this.q, this.r, new ItemStack(Item.PAINTING))));
        return true;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Dir", (byte) this.a);
        nbttagcompound.a("Motive", this.b.y);
        nbttagcompound.a("TileX", this.d);
        nbttagcompound.a("TileY", this.e);
        nbttagcompound.a("TileZ", this.f);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.b("Dir");
        this.d = nbttagcompound.d("TileX");
        this.e = nbttagcompound.d("TileY");
        this.f = nbttagcompound.d("TileZ");
        String s = nbttagcompound.h("Motive");
        EnumArt[] aenumart = EnumArt.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j) {
            EnumArt enumart = aenumart[j];

            if (enumart.y.equals(s)) {
                this.b = enumart;
            }
        }

        if (this.b == null) {
            this.b = EnumArt.KEBAB;
        }

        this.a(this.a);
    }
}
