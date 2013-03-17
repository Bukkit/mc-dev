package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class ScoreboardHealthCriteria extends ScoreboardBaseCriteria {

    public ScoreboardHealthCriteria(String s) {
        super(s);
    }

    public int getScoreModifier(List list) {
        float f = 0.0F;

        int i;
        float f1;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); f += (float) i / f1) {
            EntityHuman entityhuman = (EntityHuman) iterator.next();

            i = entityhuman.getHealth();
            f1 = (float) entityhuman.getMaxHealth();
            if (i < 0) {
                i = 0;
            }

            if ((float) i > f1) {
                i = entityhuman.getMaxHealth();
            }
        }

        if (list.size() > 0) {
            f /= (float) list.size();
        }

        return MathHelper.d(f * 19.0F) + (f > 0.0F ? 1 : 0);
    }

    public boolean isReadOnly() {
        return true;
    }
}
