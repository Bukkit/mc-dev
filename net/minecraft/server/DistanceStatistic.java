package net.minecraft.server;

import java.text.DecimalFormat;

public class DistanceStatistic extends Statistic {

    private static DecimalFormat a = new DecimalFormat("########0.00");

    public DistanceStatistic(int i, String s) {
        super(i, s);
    }
}
