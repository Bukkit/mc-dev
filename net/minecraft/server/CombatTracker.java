package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombatTracker {

    private final List a = new ArrayList();
    private final EntityLiving b;
    private int c;
    private boolean d;
    private boolean e;
    private String f;

    public CombatTracker(EntityLiving entityliving) {
        this.b = entityliving;
    }

    public void a() {
        this.g();
        if (this.b.h_()) {
            Block block = this.b.world.getType(MathHelper.floor(this.b.locX), MathHelper.floor(this.b.boundingBox.b), MathHelper.floor(this.b.locZ));

            if (block == Blocks.LADDER) {
                this.f = "ladder";
            } else if (block == Blocks.VINE) {
                this.f = "vines";
            }
        } else if (this.b.M()) {
            this.f = "water";
        }
    }

    public void a(DamageSource damagesource, float f, float f1) {
        this.h();
        this.a();
        CombatEntry combatentry = new CombatEntry(damagesource, this.b.ticksLived, f, f1, this.f, this.b.fallDistance);

        this.a.add(combatentry);
        this.c = this.b.ticksLived;
        this.e = true;
        this.d |= combatentry.f();
    }

    public IChatBaseComponent b() {
        if (this.a.size() == 0) {
            return new ChatMessage("death.attack.generic", new Object[] { this.b.getScoreboardDisplayName()});
        } else {
            CombatEntry combatentry = this.f();
            CombatEntry combatentry1 = (CombatEntry) this.a.get(this.a.size() - 1);
            IChatBaseComponent ichatbasecomponent = combatentry1.h();
            Entity entity = combatentry1.a().getEntity();
            Object object;

            if (combatentry != null && combatentry1.a() == DamageSource.FALL) {
                IChatBaseComponent ichatbasecomponent1 = combatentry.h();

                if (combatentry.a() != DamageSource.FALL && combatentry.a() != DamageSource.OUT_OF_WORLD) {
                    if (ichatbasecomponent1 != null && (ichatbasecomponent == null || !ichatbasecomponent1.equals(ichatbasecomponent))) {
                        Entity entity1 = combatentry.a().getEntity();
                        ItemStack itemstack = entity1 instanceof EntityLiving ? ((EntityLiving) entity1).be() : null;

                        if (itemstack != null && itemstack.hasName()) {
                            object = new ChatMessage("death.fell.assist.item", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent1, itemstack.E()});
                        } else {
                            object = new ChatMessage("death.fell.assist", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent1});
                        }
                    } else if (ichatbasecomponent != null) {
                        ItemStack itemstack1 = entity instanceof EntityLiving ? ((EntityLiving) entity).be() : null;

                        if (itemstack1 != null && itemstack1.hasName()) {
                            object = new ChatMessage("death.fell.finish.item", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent, itemstack1.E()});
                        } else {
                            object = new ChatMessage("death.fell.finish", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent});
                        }
                    } else {
                        object = new ChatMessage("death.fell.killer", new Object[] { this.b.getScoreboardDisplayName()});
                    }
                } else {
                    object = new ChatMessage("death.fell.accident." + this.a(combatentry), new Object[] { this.b.getScoreboardDisplayName()});
                }
            } else {
                object = combatentry1.a().getLocalizedDeathMessage(this.b);
            }

            return (IChatBaseComponent) object;
        }
    }

    public EntityLiving c() {
        EntityLiving entityliving = null;
        EntityHuman entityhuman = null;
        float f = 0.0F;
        float f1 = 0.0F;
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            CombatEntry combatentry = (CombatEntry) iterator.next();

            if (combatentry.a().getEntity() instanceof EntityHuman && (entityhuman == null || combatentry.c() > f1)) {
                f1 = combatentry.c();
                entityhuman = (EntityHuman) combatentry.a().getEntity();
            }

            if (combatentry.a().getEntity() instanceof EntityLiving && (entityliving == null || combatentry.c() > f)) {
                f = combatentry.c();
                entityliving = (EntityLiving) combatentry.a().getEntity();
            }
        }

        if (entityhuman != null && f1 >= f / 3.0F) {
            return entityhuman;
        } else {
            return entityliving;
        }
    }

    private CombatEntry f() {
        CombatEntry combatentry = null;
        CombatEntry combatentry1 = null;
        byte b0 = 0;
        float f = 0.0F;

        for (int i = 0; i < this.a.size(); ++i) {
            CombatEntry combatentry2 = (CombatEntry) this.a.get(i);
            CombatEntry combatentry3 = i > 0 ? (CombatEntry) this.a.get(i - 1) : null;

            if ((combatentry2.a() == DamageSource.FALL || combatentry2.a() == DamageSource.OUT_OF_WORLD) && combatentry2.i() > 0.0F && (combatentry == null || combatentry2.i() > f)) {
                if (i > 0) {
                    combatentry = combatentry3;
                } else {
                    combatentry = combatentry2;
                }

                f = combatentry2.i();
            }

            if (combatentry2.g() != null && (combatentry1 == null || combatentry2.c() > (float) b0)) {
                combatentry1 = combatentry2;
            }
        }

        if (f > 5.0F && combatentry != null) {
            return combatentry;
        } else if (b0 > 5 && combatentry1 != null) {
            return combatentry1;
        } else {
            return null;
        }
    }

    private String a(CombatEntry combatentry) {
        return combatentry.g() == null ? "generic" : combatentry.g();
    }

    private void g() {
        this.f = null;
    }

    private void h() {
        int i = this.d ? 300 : 100;

        if (this.e && this.b.ticksLived - this.c > i) {
            this.a.clear();
            this.e = false;
            this.d = false;
        }
    }
}
