package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.util.com.google.common.collect.Lists;
import net.minecraft.util.com.google.common.collect.Maps;
import net.minecraft.util.com.google.common.collect.Sets;

public class CommandSpreadPlayers extends CommandAbstract {

    public CommandSpreadPlayers() {}

    public String c() {
        return "spreadplayers";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.spreadplayers.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 6) {
            throw new ExceptionUsage("commands.spreadplayers.usage", new Object[0]);
        } else {
            byte b0 = 0;
            int i = b0 + 1;
            double d0 = a(icommandlistener, Double.NaN, astring[b0]);
            double d1 = a(icommandlistener, Double.NaN, astring[i++]);
            double d2 = a(icommandlistener, astring[i++], 0.0D);
            double d3 = a(icommandlistener, astring[i++], d2 + 1.0D);
            boolean flag = c(icommandlistener, astring[i++]);
            ArrayList arraylist = Lists.newArrayList();

            while (true) {
                while (i < astring.length) {
                    String s = astring[i++];

                    if (PlayerSelector.isPattern(s)) {
                        EntityPlayer[] aentityplayer = PlayerSelector.getPlayers(icommandlistener, s);

                        if (aentityplayer == null || aentityplayer.length == 0) {
                            throw new ExceptionPlayerNotFound();
                        }

                        Collections.addAll(arraylist, aentityplayer);
                    } else {
                        EntityPlayer entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(s);

                        if (entityplayer == null) {
                            throw new ExceptionPlayerNotFound();
                        }

                        arraylist.add(entityplayer);
                    }
                }

                if (arraylist.isEmpty()) {
                    throw new ExceptionPlayerNotFound();
                }

                icommandlistener.sendMessage(new ChatMessage("commands.spreadplayers.spreading." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(arraylist.size()), Double.valueOf(d3), Double.valueOf(d0), Double.valueOf(d1), Double.valueOf(d2)}));
                this.a(icommandlistener, arraylist, new Location2D(d0, d1), d2, d3, ((EntityLiving) arraylist.get(0)).world, flag);
                return;
            }
        }
    }

    private void a(ICommandListener icommandlistener, List list, Location2D location2d, double d0, double d1, World world, boolean flag) {
        Random random = new Random();
        double d2 = location2d.a - d1;
        double d3 = location2d.b - d1;
        double d4 = location2d.a + d1;
        double d5 = location2d.b + d1;
        Location2D[] alocation2d = this.a(random, flag ? this.a(list) : list.size(), d2, d3, d4, d5);
        int i = this.a(location2d, d0, world, random, d2, d3, d4, d5, alocation2d, flag);
        double d6 = this.a(list, world, alocation2d, flag);

        a(icommandlistener, "commands.spreadplayers.success." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(alocation2d.length), Double.valueOf(location2d.a), Double.valueOf(location2d.b)});
        if (alocation2d.length > 1) {
            icommandlistener.sendMessage(new ChatMessage("commands.spreadplayers.info." + (flag ? "teams" : "players"), new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d6)}), Integer.valueOf(i)}));
        }
    }

    private int a(List list) {
        HashSet hashset = Sets.newHashSet();
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            EntityLiving entityliving = (EntityLiving) iterator.next();

            if (entityliving instanceof EntityHuman) {
                hashset.add(entityliving.getScoreboardTeam());
            } else {
                hashset.add(null);
            }
        }

        return hashset.size();
    }

    private int a(Location2D location2d, double d0, World world, Random random, double d1, double d2, double d3, double d4, Location2D[] alocation2d, boolean flag) {
        boolean flag1 = true;
        double d5 = 3.4028234663852886E38D;

        int i;

        for (i = 0; i < 10000 && flag1; ++i) {
            flag1 = false;
            d5 = 3.4028234663852886E38D;

            Location2D location2d1;
            int j;

            for (int k = 0; k < alocation2d.length; ++k) {
                Location2D location2d2 = alocation2d[k];

                j = 0;
                location2d1 = new Location2D();

                for (int l = 0; l < alocation2d.length; ++l) {
                    if (k != l) {
                        Location2D location2d3 = alocation2d[l];
                        double d6 = location2d2.a(location2d3);

                        d5 = Math.min(d6, d5);
                        if (d6 < d0) {
                            ++j;
                            location2d1.a += location2d3.a - location2d2.a;
                            location2d1.b += location2d3.b - location2d2.b;
                        }
                    }
                }

                if (j > 0) {
                    location2d1.a /= (double) j;
                    location2d1.b /= (double) j;
                    double d7 = (double) location2d1.b();

                    if (d7 > 0.0D) {
                        location2d1.a();
                        location2d2.b(location2d1);
                    } else {
                        location2d2.a(random, d1, d2, d3, d4);
                    }

                    flag1 = true;
                }

                if (location2d2.a(d1, d2, d3, d4)) {
                    flag1 = true;
                }
            }

            if (!flag1) {
                Location2D[] alocation2d1 = alocation2d;
                int i1 = alocation2d.length;

                for (j = 0; j < i1; ++j) {
                    location2d1 = alocation2d1[j];
                    if (!location2d1.b(world)) {
                        location2d1.a(random, d1, d2, d3, d4);
                        flag1 = true;
                    }
                }
            }
        }

        if (i >= 10000) {
            throw new CommandException("commands.spreadplayers.failure." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(alocation2d.length), Double.valueOf(location2d.a), Double.valueOf(location2d.b), String.format("%.2f", new Object[] { Double.valueOf(d5)})});
        } else {
            return i;
        }
    }

    private double a(List list, World world, Location2D[] alocation2d, boolean flag) {
        double d0 = 0.0D;
        int i = 0;
        HashMap hashmap = Maps.newHashMap();

        for (int j = 0; j < list.size(); ++j) {
            EntityLiving entityliving = (EntityLiving) list.get(j);
            Location2D location2d;

            if (flag) {
                ScoreboardTeamBase scoreboardteambase = entityliving instanceof EntityHuman ? entityliving.getScoreboardTeam() : null;

                if (!hashmap.containsKey(scoreboardteambase)) {
                    hashmap.put(scoreboardteambase, alocation2d[i++]);
                }

                location2d = (Location2D) hashmap.get(scoreboardteambase);
            } else {
                location2d = alocation2d[i++];
            }

            entityliving.enderTeleportTo((double) ((float) MathHelper.floor(location2d.a) + 0.5F), (double) location2d.a(world), (double) MathHelper.floor(location2d.b) + 0.5D);
            double d1 = Double.MAX_VALUE;

            for (int k = 0; k < alocation2d.length; ++k) {
                if (location2d != alocation2d[k]) {
                    double d2 = location2d.a(alocation2d[k]);

                    d1 = Math.min(d2, d1);
                }
            }

            d0 += d1;
        }

        d0 /= (double) list.size();
        return d0;
    }

    private Location2D[] a(Random random, int i, double d0, double d1, double d2, double d3) {
        Location2D[] alocation2d = new Location2D[i];

        for (int j = 0; j < alocation2d.length; ++j) {
            Location2D location2d = new Location2D();

            location2d.a(random, d0, d1, d2, d3);
            alocation2d[j] = location2d;
        }

        return alocation2d;
    }
}
