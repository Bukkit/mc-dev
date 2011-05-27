package net.minecraft.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EntityTrackerEntry {

    public Entity a;
    public boolean b = false;
    public boolean c = false;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public double k;
    public double l;
    public double m;
    public int n = 0;
    private double q;
    private double r;
    private double s;
    private boolean t = false;
    private boolean u;
    public boolean o = false;
    public Set p = new HashSet();

    public EntityTrackerEntry(Entity entity, int i, int j, boolean flag) {
        this.a = entity;
        this.d = i;
        this.e = j;
        this.u = flag;
        this.f = MathHelper.b(entity.p * 32.0D);
        this.g = MathHelper.b(entity.q * 32.0D);
        this.h = MathHelper.b(entity.r * 32.0D);
        this.i = MathHelper.d(entity.v * 256.0F / 360.0F);
        this.j = MathHelper.d(entity.w * 256.0F / 360.0F);
    }

    public boolean equals(Object object) {
        return object instanceof EntityTrackerEntry ? ((EntityTrackerEntry) object).a.g == this.a.g : false;
    }

    public int hashCode() {
        return this.a.g;
    }

    public void a(List list) {
        if (this.a.E) {
            this.a((Packet) (new Packet18ArmAnimation(this.a, 2)));
            this.a.E = false;
        }

        this.o = false;
        if (!this.t || this.a.d(this.q, this.r, this.s) > 16.0D) {
            this.b(list);
            this.q = this.a.p;
            this.r = this.a.q;
            this.s = this.a.r;
            this.t = true;
            this.o = true;
        }

        if (this.n++ % this.e == 0) {
            int i = MathHelper.b(this.a.p * 32.0D);
            int j = MathHelper.b(this.a.q * 32.0D);
            int k = MathHelper.b(this.a.r * 32.0D);
            int l = MathHelper.d(this.a.v * 256.0F / 360.0F);
            int i1 = MathHelper.d(this.a.w * 256.0F / 360.0F);
            boolean flag = i != this.f || j != this.g || k != this.h;
            boolean flag1 = l != this.i || i1 != this.j;
            int j1 = i - this.f;
            int k1 = j - this.g;
            int l1 = k - this.h;
            Object object = null;

            if (j1 >= -128 && j1 < 128 && k1 >= -128 && k1 < 128 && l1 >= -128 && l1 < 128) {
                if (flag && flag1) {
                    object = new Packet33RelEntityMoveLook(this.a.g, (byte) j1, (byte) k1, (byte) l1, (byte) l, (byte) i1);
                } else if (flag) {
                    object = new Packet31RelEntityMove(this.a.g, (byte) j1, (byte) k1, (byte) l1);
                } else if (flag1) {
                    object = new Packet32EntityLook(this.a.g, (byte) l, (byte) i1);
                } else {
                    object = new Packet30Entity(this.a.g);
                }
            } else {
                object = new Packet34EntityTeleport(this.a.g, i, j, k, (byte) l, (byte) i1);
            }

            if (this.u) {
                double d0 = this.a.s - this.k;
                double d1 = this.a.t - this.l;
                double d2 = this.a.u - this.m;
                double d3 = 0.02D;
                double d4 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d4 > d3 * d3 || d4 > 0.0D && this.a.s == 0.0D && this.a.t == 0.0D && this.a.u == 0.0D) {
                    this.k = this.a.s;
                    this.l = this.a.t;
                    this.m = this.a.u;
                    this.a((Packet) (new Packet28(this.a.g, this.k, this.l, this.m)));
                }
            }

            if (object != null) {
                this.a((Packet) object);
            }

            if (this.b && this.a.k == null) {
                this.b = false;
                this.a((Packet) (new Packet18ArmAnimation(this.a, 101)));
            } else if (!this.b && this.a.k != null) {
                this.b = true;
                this.a((Packet) (new Packet18ArmAnimation(this.a, 100)));
            }

            if (this.c && this.a.Z <= 0) {
                this.c = false;
                this.a((Packet) (new Packet18ArmAnimation(this.a, 103)));
            } else if (!this.c && this.a.Z > 0) {
                this.c = true;
                this.a((Packet) (new Packet18ArmAnimation(this.a, 102)));
            }

            this.f = i;
            this.g = j;
            this.h = k;
            this.i = l;
            this.j = i1;
        }
    }

    public void a(Packet packet) {
        Iterator iterator = this.p.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            entityplayer.a.b(packet);
        }
    }

    public void a() {
        this.a((Packet) (new Packet29DestroyEntity(this.a.g)));
    }

    public void a(EntityPlayer entityplayer) {
        if (entityplayer != this.a) {
            double d0 = entityplayer.p - (double) (this.f / 32);
            double d1 = entityplayer.r - (double) (this.h / 32);

            if (d0 >= (double) (-this.d) && d0 <= (double) this.d && d1 >= (double) (-this.d) && d1 <= (double) this.d) {
                if (!this.p.contains(entityplayer)) {
                    this.p.add(entityplayer);
                    entityplayer.a.b(this.b());
                    if (this.b) {
                        entityplayer.a.b((Packet) (new Packet18ArmAnimation(this.a, 100)));
                    }

                    if (this.c) {
                        entityplayer.a.b((Packet) (new Packet18ArmAnimation(this.a, 102)));
                    }

                    if (this.u) {
                        entityplayer.a.b((Packet) (new Packet28(this.a.g, this.a.s, this.a.t, this.a.u)));
                    }
                }
            } else if (this.p.contains(entityplayer)) {
                this.p.remove(entityplayer);
                entityplayer.a.b((Packet) (new Packet29DestroyEntity(this.a.g)));
            }
        }
    }

    public void b(List list) {
        for (int i = 0; i < list.size(); ++i) {
            this.a((EntityPlayer) list.get(i));
        }
    }

    private Packet b() {
        if (this.a instanceof EntityItem) {
            EntityItem entityitem = (EntityItem) this.a;
            Packet21PickupSpawn packet21pickupspawn = new Packet21PickupSpawn(entityitem);

            entityitem.p = (double) packet21pickupspawn.b / 32.0D;
            entityitem.q = (double) packet21pickupspawn.c / 32.0D;
            entityitem.r = (double) packet21pickupspawn.d / 32.0D;
            return packet21pickupspawn;
        } else if (this.a instanceof EntityPlayer) {
            return new Packet20NamedEntitySpawn((EntityHuman) this.a);
        } else {
            if (this.a instanceof EntityMinecart) {
                EntityMinecart entityminecart = (EntityMinecart) this.a;

                if (entityminecart.d == 0) {
                    return new Packet23VehicleSpawn(this.a, 10);
                }

                if (entityminecart.d == 1) {
                    return new Packet23VehicleSpawn(this.a, 11);
                }

                if (entityminecart.d == 2) {
                    return new Packet23VehicleSpawn(this.a, 12);
                }
            }

            if (this.a instanceof EntityBoat) {
                return new Packet23VehicleSpawn(this.a, 1);
            } else if (this.a instanceof IAnimal) {
                return new Packet24MobSpawn((EntityLiving) this.a);
            } else if (this.a instanceof EntityFish) {
                return new Packet23VehicleSpawn(this.a, 90);
            } else if (this.a instanceof EntityTNTPrimed) {
                return new Packet23VehicleSpawn(this.a, 50);
            } else {
                throw new IllegalArgumentException("Don\'t know how to add " + this.a.getClass() + "!");
            }
        }
    }
}
