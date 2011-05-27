package net.minecraft.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EntityTrackerEntry {

    public Entity a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i = 0;
    private double l;
    private double m;
    private double n;
    private boolean o = false;
    public boolean j = false;
    public Set k = new HashSet();

    public EntityTrackerEntry(Entity entity, int i, int j) {
        this.a = entity;
        this.b = i;
        this.c = j;
        this.d = MathHelper.b(entity.p * 32.0D);
        this.e = MathHelper.b(entity.q * 32.0D);
        this.f = MathHelper.b(entity.r * 32.0D);
        this.g = MathHelper.d(entity.v * 256.0F / 360.0F);
        this.h = MathHelper.d(entity.w * 256.0F / 360.0F);
    }

    public boolean equals(Object object) {
        return object instanceof EntityTrackerEntry ? ((EntityTrackerEntry) object).a.g == this.a.g : false;
    }

    public int hashCode() {
        return this.a.g;
    }

    public void a(List list) {
        this.j = false;
        if (!this.o || this.a.d(this.l, this.m, this.n) > 16.0D) {
            this.b(list);
            this.l = this.a.p;
            this.m = this.a.q;
            this.n = this.a.r;
            this.o = true;
            this.j = true;
        }

        if (this.i++ % this.c == 0) {
            int i = MathHelper.b(this.a.p * 32.0D);
            int j = MathHelper.b(this.a.q * 32.0D);
            int k = MathHelper.b(this.a.r * 32.0D);
            int l = MathHelper.d(this.a.v * 256.0F / 360.0F);
            int i1 = MathHelper.d(this.a.w * 256.0F / 360.0F);
            boolean flag = i != this.d || j != this.e || k != this.f;
            boolean flag1 = l != this.g || i1 != this.h;
            int j1 = i - this.d;
            int k1 = j - this.e;
            int l1 = k - this.f;
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

            if (object != null) {
                this.a((Packet) object);
            }

            this.d = i;
            this.e = j;
            this.f = k;
            this.g = l;
            this.h = i1;
        }
    }

    public void a(Packet packet) {
        Iterator iterator = this.k.iterator();

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
            double d0 = entityplayer.p - (double) (this.d / 32);
            double d1 = entityplayer.r - (double) (this.f / 32);

            if (d0 >= (double) (-this.b) && d0 <= (double) this.b && d1 >= (double) (-this.b) && d1 <= (double) this.b) {
                if (!this.k.contains(entityplayer)) {
                    this.k.add(entityplayer);
                    entityplayer.a.b(this.b());
                }
            } else if (this.k.contains(entityplayer)) {
                this.k.remove(entityplayer);
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
            entityitem.s = (double) packet21pickupspawn.e / 128.0D;
            entityitem.t = (double) packet21pickupspawn.f / 128.0D;
            entityitem.u = (double) packet21pickupspawn.g / 128.0D;
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
                return new PacketUnusedFishing(this.a, 1);
            } else {
                throw new IllegalArgumentException("Don\'t know how to add " + this.a.getClass() + "!");
            }
        }
    }
}
