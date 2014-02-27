package net.minecraft.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Statistic {

    public final String name;
    private final IChatBaseComponent a;
    public boolean f;
    private final Counter b;
    private final IScoreboardCriteria c;
    private Class d;
    private static NumberFormat k = NumberFormat.getIntegerInstance(Locale.US);
    public static Counter g = new UnknownCounter();
    private static DecimalFormat l = new DecimalFormat("########0.00");
    public static Counter h = new TimeCounter();
    public static Counter i = new DistancesCounter();
    public static Counter j = new DamageCounter();

    public Statistic(String s, IChatBaseComponent ichatbasecomponent, Counter counter) {
        this.name = s;
        this.a = ichatbasecomponent;
        this.b = counter;
        this.c = new ScoreboardStatisticCriteria(this);
        IScoreboardCriteria.a.put(this.c.getName(), this.c);
    }

    public Statistic(String s, IChatBaseComponent ichatbasecomponent) {
        this(s, ichatbasecomponent, g);
    }

    public Statistic i() {
        this.f = true;
        return this;
    }

    public Statistic h() {
        if (StatisticList.a.containsKey(this.name)) {
            throw new RuntimeException("Duplicate stat id: \"" + ((Statistic) StatisticList.a.get(this.name)).a + "\" and \"" + this.a + "\" at id " + this.name);
        } else {
            StatisticList.b.add(this);
            StatisticList.a.put(this.name, this);
            return this;
        }
    }

    public boolean d() {
        return false;
    }

    public IChatBaseComponent e() {
        IChatBaseComponent ichatbasecomponent = this.a.f();

        ichatbasecomponent.getChatModifier().setColor(EnumChatFormat.GRAY);
        ichatbasecomponent.getChatModifier().a(new ChatHoverable(EnumHoverAction.SHOW_ACHIEVEMENT, new ChatComponentText(this.name)));
        return ichatbasecomponent;
    }

    public IChatBaseComponent j() {
        IChatBaseComponent ichatbasecomponent = this.e();
        IChatBaseComponent ichatbasecomponent1 = (new ChatComponentText("[")).a(ichatbasecomponent).a("]");

        ichatbasecomponent1.setChatModifier(ichatbasecomponent.getChatModifier());
        return ichatbasecomponent1;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            Statistic statistic = (Statistic) object;

            return this.name.equals(statistic.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "Stat{id=" + this.name + ", nameId=" + this.a + ", awardLocallyOnly=" + this.f + ", formatter=" + this.b + ", objectiveCriteria=" + this.c + '}';
    }

    public IScoreboardCriteria k() {
        return this.c;
    }

    public Class l() {
        return this.d;
    }

    public Statistic b(Class oclass) {
        this.d = oclass;
        return this;
    }
}
