package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombatTracker {

    private final List a = new ArrayList();
    private final EntityLiving b;
    private int c = 0;
    private boolean d = false;
    private boolean e = false;
    private String f;

    public CombatTracker(EntityLiving entityliving) {
        this.b = entityliving;
    }

    public void a() {
        this.g();
        if (this.b.g_()) {
            int i = this.b.world.getTypeId(MathHelper.floor(this.b.locX), MathHelper.floor(this.b.boundingBox.b), MathHelper.floor(this.b.locZ));

            if (i == Block.LADDER.id) {
                this.f = "ladder";
            } else if (i == Block.VINE.id) {
                this.f = "vines";
            }
        } else if (this.b.G()) {
            this.f = "water";
        }
    }

    public void a(DamageSource damagesource, int i, int j) {
        this.h();
        this.a();
        CombatEntry combatentry = new CombatEntry(damagesource, this.b.ticksLived, i, j, this.f, this.b.fallDistance);

        this.a.add(combatentry);
        this.c = this.b.ticksLived;
        this.e = true;
        this.d |= combatentry.f();
    }

    public String b() {
        if (this.a.size() == 0) {
            return this.b.getScoreboardDisplayName() + " died";
        } else {
            CombatEntry combatentry = this.f();
            CombatEntry combatentry1 = (CombatEntry) this.a.get(this.a.size() - 1);
            String s = "";
            String s1 = combatentry1.h();
            Entity entity = combatentry1.a().getEntity();

            if (combatentry != null && combatentry1.a() == DamageSource.FALL) {
                String s2 = combatentry.h();

                if (combatentry.a() != DamageSource.FALL && combatentry.a() != DamageSource.OUT_OF_WORLD) {
                    if (s2 != null && (s1 == null || !s2.equals(s1))) {
                        Entity entity1 = combatentry.a().getEntity();
                        ItemStack itemstack = entity1 instanceof EntityLiving ? ((EntityLiving) entity1).bG() : null;

                        if (itemstack != null && itemstack.hasName()) {
                            s = LocaleI18n.get("death.fell.assist.item", new Object[] { this.b.getScoreboardDisplayName(), s1, itemstack.getName()});
                        } else {
                            s = LocaleI18n.get("death.fell.assist", new Object[] { this.b.getScoreboardDisplayName(), s2});
                        }
                    } else if (s1 != null) {
                        ItemStack itemstack1 = entity instanceof EntityLiving ? ((EntityLiving) entity).bG() : null;

                        if (itemstack1 != null && itemstack1.hasName()) {
                            s = LocaleI18n.get("death.fell.finish.item", new Object[] { this.b.getScoreboardDisplayName(), s1, itemstack1.getName()});
                        } else {
                            s = LocaleI18n.get("death.fell.finish", new Object[] { this.b.getScoreboardDisplayName(), s1});
                        }
                    } else {
                        s = LocaleI18n.get("death.fell.killer", new Object[] { this.b.getScoreboardDisplayName()});
                    }
                } else {
                    s = LocaleI18n.get("death.fell.accident." + this.a(combatentry), new Object[] { this.b.getScoreboardDisplayName()});
                }
            } else {
                s = combatentry1.a().getLocalizedDeathMessage(this.b);
            }

            return s;
        }
    }

    public EntityLiving c() {
        EntityLiving entityliving = null;
        EntityHuman entityhuman = null;
        int i = 0;
        int j = 0;
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            CombatEntry combatentry = (CombatEntry) iterator.next();

            if (combatentry.a().getEntity() instanceof EntityHuman && (entityhuman == null || combatentry.c() > j)) {
                j = combatentry.c();
                entityhuman = (EntityHuman) combatentry.a().getEntity();
            }

            if (combatentry.a().getEntity() instanceof EntityLiving && (entityliving == null || combatentry.c() > i)) {
                i = combatentry.c();
                entityliving = (EntityLiving) combatentry.a().getEntity();
            }
        }

        if (entityhuman != null && j >= i / 3) {
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

            if (combatentry2.g() != null && (combatentry1 == null || combatentry2.c() > b0)) {
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
