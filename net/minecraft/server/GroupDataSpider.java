package net.minecraft.server;

import java.util.Random;

public class GroupDataSpider implements GroupDataEntity {

    public int a;

    public GroupDataSpider() {}

    public void a(Random random) {
        int i = random.nextInt(5);

        if (i <= 1) {
            this.a = MobEffectList.FASTER_MOVEMENT.id;
        } else if (i <= 2) {
            this.a = MobEffectList.INCREASE_DAMAGE.id;
        } else if (i <= 3) {
            this.a = MobEffectList.REGENERATION.id;
        } else if (i <= 4) {
            this.a = MobEffectList.INVISIBILITY.id;
        }
    }
}
