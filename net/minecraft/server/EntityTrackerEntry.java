package net.minecraft.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EntityTrackerEntry {

    public Entity a;
    public boolean b = false;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public double j;
    public double k;
    public double l;
    public int m = 0;
    private double p;
    private double q;
    private double r;
    private boolean s = false;
    private boolean t;
    public boolean n = false;
    public Set o = new HashSet();

    public EntityTrackerEntry(Entity entity, int i, int j, boolean flag) {
        this.a = entity;
        this.c = i;
        this.d = j;
        this.t = flag;
        this.e = MathHelper.b(entity.p * 32.0D);
        this.f = MathHelper.b(entity.q * 32.0D);
        this.g = MathHelper.b(entity.r * 32.0D);
        this.h = MathHelper.d(entity.v * 256.0F / 360.0F);
        this.i = MathHelper.d(entity.w * 256.0F / 360.0F);
    }

    public boolean equals(Object object) {
        return object instanceof EntityTrackerEntry ? ((EntityTrackerEntry) object).a.g == this.a.g : false;
    }

    public int hashCode() {
        return this.a.g;
    }

    public void a(List list) {
        this.n = false;
        if (!this.s || this.a.d(this.p, this.q, this.r) > 16.0D) {
            this.b(list);
            this.p = this.a.p;
            this.q = this.a.q;
            this.r = this.a.r;
            this.s = true;
            this.n = true;
        }

        if (this.m++ % this.d == 0) {
            int i = MathHelper.b(this.a.p * 32.0D);
            int j = MathHelper.b(this.a.q * 32.0D);
            int k = MathHelper.b(this.a.r * 32.0D);
            int l = MathHelper.d(this.a.v * 256.0F / 360.0F);
            int i1 = MathHelper.d(this.a.w * 256.0F / 360.0F);
            boolean flag = i != this.e || j != this.f || k != this.g;
            boolean flag1 = l != this.h || i1 != this.i;
            int j1 = i - this.e;
            int k1 = j - this.f;
            int l1 = k - this.g;
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

            if (this.t) {
                double d0 = this.a.s - this.j;
                double d1 = this.a.t - this.k;
                double d2 = this.a.u - this.l;
                double d3 = 0.02D;
                double d4 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d4 > d3 * d3 || d4 > 0.0D && this.a.s == 0.0D && this.a.t == 0.0D && this.a.u == 0.0D) {
                    this.j = this.a.s;
                    this.k = this.a.t;
                    this.l = this.a.u;
                    this.a((Packet) (new Packet28(this.a.g, this.j, this.k, this.l)));
                }
            }

            if (object != null) {
                this.a((Packet) object);
            }

            if (this.b && this.a.k == null) {
                this.b = false;
                this.a((Packet) (new Packet18ArmAnimation(this.a, 3)));
            } else if (!this.b && this.a.k != null) {
                this.b = true;
                this.a((Packet) (new Packet18ArmAnimation(this.a, 2)));
            }

            this.e = i;
            this.f = j;
            this.g = k;
            this.h = l;
            this.i = i1;
        }
    }

    public void a(Packet packet) {
        Iterator iterator = this.o.iterator();

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
            double d0 = entityplayer.p - (double) (this.e / 32);
            double d1 = entityplayer.r - (double) (this.g / 32);

            if (d0 >= (double) (-this.c) && d0 <= (double) this.c && d1 >= (double) (-this.c) && d1 <= (double) this.c) {
                if (!this.o.contains(entityplayer)) {
                    this.o.add(entityplayer);
                    entityplayer.a.b(this.b());
                    if (this.b) {
                        entityplayer.a.b((Packet) (new Packet18ArmAnimation(this.a, 2)));
                    }

                    if (this.t) {
                        entityplayer.a.b((Packet) (new Packet28(this.a.g, this.a.s, this.a.t, this.a.u)));
                    }
                }
            } else if (this.o.contains(entityplayer)) {
                this.o.remove(entityplayer);
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
            } else {
                throw new IllegalArgumentException("Don\'t know how to add " + this.a.getClass() + "!");
            }
        }
    }
}
