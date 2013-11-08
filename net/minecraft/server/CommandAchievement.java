package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.util.com.google.common.collect.Lists;

public class CommandAchievement extends CommandAbstract {

    public CommandAchievement() {}

    public String c() {
        return "achievement";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.achievement.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 2) {
            Statistic statistic = StatisticList.a(astring[1]);

            if (statistic == null && !astring[1].equals("*")) {
                throw new CommandException("commands.achievement.unknownAchievement", new Object[] { astring[1]});
            }

            EntityPlayer entityplayer;

            if (astring.length >= 3) {
                entityplayer = d(icommandlistener, astring[2]);
            } else {
                entityplayer = b(icommandlistener);
            }

            if (astring[0].equalsIgnoreCase("give")) {
                if (statistic == null) {
                    Iterator iterator = AchievementList.e.iterator();

                    while (iterator.hasNext()) {
                        Achievement achievement = (Achievement) iterator.next();

                        entityplayer.a((Statistic) achievement);
                    }

                    a(icommandlistener, "commands.achievement.give.success.all", new Object[] { entityplayer.getName()});
                } else {
                    if (statistic instanceof Achievement) {
                        Achievement achievement1 = (Achievement) statistic;

                        ArrayList arraylist;

                        for (arraylist = Lists.newArrayList(); achievement1.c != null && !entityplayer.x().a(achievement1.c); achievement1 = achievement1.c) {
                            arraylist.add(achievement1.c);
                        }

                        Iterator iterator1 = Lists.reverse(arraylist).iterator();

                        while (iterator1.hasNext()) {
                            Achievement achievement2 = (Achievement) iterator1.next();

                            entityplayer.a((Statistic) achievement2);
                        }
                    }

                    entityplayer.a(statistic);
                    a(icommandlistener, "commands.achievement.give.success.one", new Object[] { entityplayer.getName(), statistic.j()});
                }

                return;
            }
        }

        throw new ExceptionUsage("commands.achievement.usage", new Object[0]);
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1) {
            return a(astring, new String[] { "give"});
        } else if (astring.length != 2) {
            return astring.length == 3 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
        } else {
            ArrayList arraylist = Lists.newArrayList();
            Iterator iterator = StatisticList.b.iterator();

            while (iterator.hasNext()) {
                Statistic statistic = (Statistic) iterator.next();

                arraylist.add(statistic.e);
            }

            return a(astring, arraylist);
        }
    }

    public boolean a(String[] astring, int i) {
        return i == 2;
    }
}
