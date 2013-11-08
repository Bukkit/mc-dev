package net.minecraft.server;

public class CraftingStatistic extends Statistic {

    private final Item a;

    public CraftingStatistic(String s, IChatBaseComponent ichatbasecomponent, Item item) {
        super(s, ichatbasecomponent);
        this.a = item;
    }
}
