package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom {

    public WeightedRandom() {}

    public static int a(Collection collection) {
        int i = 0;

        WeightedRandomChoice weightedrandomchoice;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += weightedrandomchoice.d) {
            weightedrandomchoice = (WeightedRandomChoice) iterator.next();
        }

        return i;
    }

    public static WeightedRandomChoice a(Random random, Collection collection, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        } else {
            int j = random.nextInt(i);
            Iterator iterator = collection.iterator();

            WeightedRandomChoice weightedrandomchoice;

            do {
                if (!iterator.hasNext()) {
                    return null;
                }

                weightedrandomchoice = (WeightedRandomChoice) iterator.next();
                j -= weightedrandomchoice.d;
            } while (j >= 0);

            return weightedrandomchoice;
        }
    }

    public static WeightedRandomChoice a(Random random, Collection collection) {
        return a(random, collection, a(collection));
    }

    public static int a(WeightedRandomChoice[] aweightedrandomchoice) {
        int i = 0;
        WeightedRandomChoice[] aweightedrandomchoice1 = aweightedrandomchoice;
        int j = aweightedrandomchoice.length;

        for (int k = 0; k < j; ++k) {
            WeightedRandomChoice weightedrandomchoice = aweightedrandomchoice1[k];

            i += weightedrandomchoice.d;
        }

        return i;
    }

    public static WeightedRandomChoice a(Random random, WeightedRandomChoice[] aweightedrandomchoice, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        } else {
            int j = random.nextInt(i);
            WeightedRandomChoice[] aweightedrandomchoice1 = aweightedrandomchoice;
            int k = aweightedrandomchoice.length;

            for (int l = 0; l < k; ++l) {
                WeightedRandomChoice weightedrandomchoice = aweightedrandomchoice1[l];

                j -= weightedrandomchoice.d;
                if (j < 0) {
                    return weightedrandomchoice;
                }
            }

            return null;
        }
    }

    public static WeightedRandomChoice a(Random random, WeightedRandomChoice[] aweightedrandomchoice) {
        return a(random, aweightedrandomchoice, a(aweightedrandomchoice));
    }
}
