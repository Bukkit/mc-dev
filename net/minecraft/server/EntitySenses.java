package net.minecraft.server;

import java.util.ArrayList;

public class EntitySenses {

    EntityLiving entity;
    ArrayList seenEntities = new ArrayList();
    ArrayList unseenEntities = new ArrayList();

    public EntitySenses(EntityLiving entityliving) {
        this.entity = entityliving;
    }

    public void a() {
        this.seenEntities.clear();
        this.unseenEntities.clear();
    }

    public boolean canSee(Entity entity) {
        if (this.seenEntities.contains(entity)) {
            return true;
        } else if (this.unseenEntities.contains(entity)) {
            return false;
        } else {
            MethodProfiler.a("canSee");
            boolean flag = this.entity.h(entity);

            MethodProfiler.a();
            if (flag) {
                this.seenEntities.add(entity);
            } else {
                this.unseenEntities.add(entity);
            }

            return flag;
        }
    }
}
