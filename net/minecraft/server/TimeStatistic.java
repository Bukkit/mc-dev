package net.minecraft.server;

import java.text.DecimalFormat;

public class TimeStatistic extends Statistic {

    private static DecimalFormat a = new DecimalFormat("########0.00");

    public TimeStatistic(int i, String s) {
        super(i, s);
    }
}
