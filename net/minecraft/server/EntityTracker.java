package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EntityTracker {

    private Set a = new HashSet();
    private EntityList b = new EntityList();
    private MinecraftServer c;
    private int d;

    public EntityTracker(MinecraftServer minecraftserver) {
        this.c = minecraftserver;
        this.d = minecraftserver.f.a();
    }

    public void a(Entity entity) {
        if (entity instanceof EntityPlayer) {
            this.a(entity, 512, 2);
            EntityPlayer entityplayer = (EntityPlayer) entity;
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

                if (entitytrackerentry.a != entityplayer) {
                    entitytrackerentry.a(entityplayer);
                }
            }
        } else if (entity instanceof EntityFish) {
            this.a(entity, 64, 5, true);
        } else if (entity instanceof EntityItem) {
            this.a(entity, 64, 20, true);
        } else if (entity instanceof EntityMinecart) {
            this.a(entity, 160, 5, true);
        } else if (entity instanceof EntityBoat) {
            this.a(entity, 160, 5, true);
        } else if (entity instanceof IAnimal) {
            this.a(entity, 160, 3);
        }
    }

    public void a(Entity entity, int i, int j) {
        this.a(entity, i, j, false);
    }

    public void a(Entity entity, int i, int j, boolean flag) {
        if (i > this.d) {
            i = this.d;
        }

        if (this.b.b(entity.g)) {
            throw new IllegalStateException("Entity is already tracked!");
        } else {
            EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entity, i, j, flag);

            this.a.add(entitytrackerentry);
            this.b.a(entity.g, entitytrackerentry);
            entitytrackerentry.b(this.c.e.d);
        }
    }

    public void b(Entity entity) {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) this.b.d(entity.g);

        if (entitytrackerentry != null) {
            this.a.remove(entitytrackerentry);
            entitytrackerentry.a();
        }
    }

    public void a() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

            entitytrackerentry.a(this.c.e.d);
            if (entitytrackerentry.n && entitytrackerentry.a instanceof EntityPlayer) {
                arraylist.add((EntityPlayer) entitytrackerentry.a);
            }
        }

        for (int i = 0; i < arraylist.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) arraylist.get(i);
            Iterator iterator1 = this.a.iterator();

            while (iterator1.hasNext()) {
                EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry) iterator1.next();

                if (entitytrackerentry1.a != entityplayer) {
                    entitytrackerentry1.a(entityplayer);
                }
            }
        }
    }

    public void a(Entity entity, Packet packet) {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) this.b.a(entity.g);

        if (entitytrackerentry != null) {
            entitytrackerentry.a(packet);
        }
    }
}
