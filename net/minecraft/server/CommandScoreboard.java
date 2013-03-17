package net.minecraft.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommandScoreboard extends CommandAbstract {

    public CommandScoreboard() {}

    public String c() {
        return "scoreboard";
    }

    public int a() {
        return 2;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1) {
            if (astring[0].equalsIgnoreCase("objectives")) {
                if (astring.length == 1) {
                    throw new ExceptionUsage("commands.scoreboard.objectives.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    this.d(icommandlistener);
                } else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length < 4) {
                        throw new ExceptionUsage("commands.scoreboard.objectives.add.usage", new Object[0]);
                    }

                    this.b(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.scoreboard.objectives.remove.usage", new Object[0]);
                    }

                    this.e(icommandlistener, astring[2]);
                } else {
                    if (!astring[1].equalsIgnoreCase("setdisplay")) {
                        throw new ExceptionUsage("commands.scoreboard.objectives.usage", new Object[0]);
                    }

                    if (astring.length != 3 && astring.length != 4) {
                        throw new ExceptionUsage("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
                    }

                    this.j(icommandlistener, astring, 2);
                }

                return;
            }

            if (astring[0].equalsIgnoreCase("players")) {
                if (astring.length == 1) {
                    throw new ExceptionUsage("commands.scoreboard.players.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    if (astring.length > 3) {
                        throw new ExceptionUsage("commands.scoreboard.players.list.usage", new Object[0]);
                    }

                    this.k(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length != 5) {
                        throw new ExceptionUsage("commands.scoreboard.players.add.usage", new Object[0]);
                    }

                    this.l(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 5) {
                        throw new ExceptionUsage("commands.scoreboard.players.remove.usage", new Object[0]);
                    }

                    this.l(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("set")) {
                    if (astring.length != 5) {
                        throw new ExceptionUsage("commands.scoreboard.players.set.usage", new Object[0]);
                    }

                    this.l(icommandlistener, astring, 2);
                } else {
                    if (!astring[1].equalsIgnoreCase("reset")) {
                        throw new ExceptionUsage("commands.scoreboard.players.usage", new Object[0]);
                    }

                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.scoreboard.players.reset.usage", new Object[0]);
                    }

                    this.m(icommandlistener, astring, 2);
                }

                return;
            }

            if (astring[0].equalsIgnoreCase("teams")) {
                if (astring.length == 1) {
                    throw new ExceptionUsage("commands.scoreboard.teams.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    if (astring.length > 3) {
                        throw new ExceptionUsage("commands.scoreboard.teams.list.usage", new Object[0]);
                    }

                    this.f(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length < 3) {
                        throw new ExceptionUsage("commands.scoreboard.teams.add.usage", new Object[0]);
                    }

                    this.c(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.scoreboard.teams.remove.usage", new Object[0]);
                    }

                    this.e(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("empty")) {
                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.scoreboard.teams.empty.usage", new Object[0]);
                    }

                    this.i(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("join")) {
                    if (astring.length < 4 && (astring.length != 3 || !(icommandlistener instanceof EntityHuman))) {
                        throw new ExceptionUsage("commands.scoreboard.teams.join.usage", new Object[0]);
                    }

                    this.g(icommandlistener, astring, 2);
                } else if (astring[1].equalsIgnoreCase("leave")) {
                    if (astring.length < 3 && !(icommandlistener instanceof EntityHuman)) {
                        throw new ExceptionUsage("commands.scoreboard.teams.leave.usage", new Object[0]);
                    }

                    this.h(icommandlistener, astring, 2);
                } else {
                    if (!astring[1].equalsIgnoreCase("option")) {
                        throw new ExceptionUsage("commands.scoreboard.teams.usage", new Object[0]);
                    }

                    if (astring.length != 4 && astring.length != 5) {
                        throw new ExceptionUsage("commands.scoreboard.teams.option.usage", new Object[0]);
                    }

                    this.d(icommandlistener, astring, 2);
                }

                return;
            }
        }

        throw new ExceptionUsage("commands.scoreboard.usage", new Object[0]);
    }

    protected Scoreboard d() {
        return MinecraftServer.getServer().getWorldServer(0).getScoreboard();
    }

    protected ScoreboardObjective a(String s, boolean flag) {
        Scoreboard scoreboard = this.d();
        ScoreboardObjective scoreboardobjective = scoreboard.getObjective(s);

        if (scoreboardobjective == null) {
            throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[] { s});
        } else if (flag && scoreboardobjective.getCriteria().isReadOnly()) {
            throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[] { s});
        } else {
            return scoreboardobjective;
        }
    }

    protected ScoreboardTeam a(String s) {
        Scoreboard scoreboard = this.d();
        ScoreboardTeam scoreboardteam = scoreboard.getTeam(s);

        if (scoreboardteam == null) {
            throw new CommandException("commands.scoreboard.teamNotFound", new Object[] { s});
        } else {
            return scoreboardteam;
        }
    }

    protected void b(ICommandListener icommandlistener, String[] astring, int i) {
        String s = astring[i++];
        String s1 = astring[i++];
        Scoreboard scoreboard = this.d();
        IScoreboardCriteria iscoreboardcriteria = (IScoreboardCriteria) IScoreboardCriteria.a.get(s1);

        if (iscoreboardcriteria == null) {
            String[] astring1 = (String[]) IScoreboardCriteria.a.keySet().toArray(new String[0]);

            throw new ExceptionUsage("commands.scoreboard.objectives.add.wrongType", new Object[] { a(astring1)});
        } else if (scoreboard.getObjective(s) != null) {
            throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[] { s});
        } else if (s.length() > 16) {
            throw new ExceptionInvalidSyntax("commands.scoreboard.objectives.add.tooLong", new Object[] { s, Integer.valueOf(16)});
        } else {
            ScoreboardObjective scoreboardobjective = scoreboard.registerObjective(s, iscoreboardcriteria);

            if (astring.length > i) {
                String s2 = a(icommandlistener, astring, i);

                if (s2.length() > 32) {
                    throw new ExceptionInvalidSyntax("commands.scoreboard.objectives.add.displayTooLong", new Object[] { s2, Integer.valueOf(32)});
                }

                if (s2.length() > 0) {
                    scoreboardobjective.setDisplayName(s2);
                }
            }

            a(icommandlistener, "commands.scoreboard.objectives.add.success", new Object[] { s});
        }
    }

    protected void c(ICommandListener icommandlistener, String[] astring, int i) {
        String s = astring[i++];
        Scoreboard scoreboard = this.d();

        if (scoreboard.getTeam(s) != null) {
            throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[] { s});
        } else if (s.length() > 16) {
            throw new ExceptionInvalidSyntax("commands.scoreboard.teams.add.tooLong", new Object[] { s, Integer.valueOf(16)});
        } else {
            ScoreboardTeam scoreboardteam = scoreboard.createTeam(s);

            if (astring.length > i) {
                String s1 = a(icommandlistener, astring, i);

                if (s1.length() > 32) {
                    throw new ExceptionInvalidSyntax("commands.scoreboard.teams.add.displayTooLong", new Object[] { s1, Integer.valueOf(32)});
                }

                if (s1.length() > 0) {
                    scoreboardteam.setDisplayName(s1);
                }
            }

            a(icommandlistener, "commands.scoreboard.teams.add.success", new Object[] { s});
        }
    }

    protected void d(ICommandListener icommandlistener, String[] astring, int i) {
        ScoreboardTeam scoreboardteam = this.a(astring[i++]);
        String s = astring[i++].toLowerCase();

        if (!s.equalsIgnoreCase("color") && !s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles")) {
            throw new ExceptionUsage("commands.scoreboard.teams.option.usage", new Object[0]);
        } else if (astring.length == 4) {
            if (s.equalsIgnoreCase("color")) {
                throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { s, a(EnumChatFormat.a(true, false))});
            } else if (!s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles")) {
                throw new ExceptionUsage("commands.scoreboard.teams.option.usage", new Object[0]);
            } else {
                throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { s, a(Arrays.asList(new String[] { "true", "false"}))});
            }
        } else {
            String s1 = astring[i++];

            if (s.equalsIgnoreCase("color")) {
                EnumChatFormat enumchatformat = EnumChatFormat.b(s1);

                if (s1 == null) {
                    throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { s, a(EnumChatFormat.a(true, false))});
                }

                scoreboardteam.setPrefix(enumchatformat.toString());
                scoreboardteam.setSuffix(EnumChatFormat.RESET.toString());
            } else if (s.equalsIgnoreCase("friendlyfire")) {
                if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                    throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { s, a(Arrays.asList(new String[] { "true", "false"}))});
                }

                scoreboardteam.setAllowFriendlyFire(s1.equalsIgnoreCase("true"));
            } else if (s.equalsIgnoreCase("seeFriendlyInvisibles")) {
                if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                    throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { s, a(Arrays.asList(new String[] { "true", "false"}))});
                }

                scoreboardteam.setCanSeeFriendlyInvisibles(s1.equalsIgnoreCase("true"));
            }

            a(icommandlistener, "commands.scoreboard.teams.option.success", new Object[] { s, scoreboardteam.getName(), s1});
        }
    }

    protected void e(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();
        ScoreboardTeam scoreboardteam = this.a(astring[i++]);

        scoreboard.removeTeam(scoreboardteam);
        a(icommandlistener, "commands.scoreboard.teams.remove.success", new Object[] { scoreboardteam.getName()});
    }

    protected void f(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();

        if (astring.length > i) {
            ScoreboardTeam scoreboardteam = this.a(astring[i++]);
            Collection collection = scoreboardteam.getPlayerNameSet();

            if (collection.size() <= 0) {
                throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[] { scoreboardteam.getName()});
            }

            icommandlistener.sendMessage(EnumChatFormat.DARK_GREEN + icommandlistener.a("commands.scoreboard.teams.list.player.count", new Object[] { Integer.valueOf(collection.size()), scoreboardteam.getName()}));
            icommandlistener.sendMessage(a(collection.toArray()));
        } else {
            Collection collection1 = scoreboard.getTeams();

            if (collection1.size() <= 0) {
                throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
            }

            icommandlistener.sendMessage(EnumChatFormat.DARK_GREEN + icommandlistener.a("commands.scoreboard.teams.list.count", new Object[] { Integer.valueOf(collection1.size())}));
            Iterator iterator = collection1.iterator();

            while (iterator.hasNext()) {
                ScoreboardTeam scoreboardteam1 = (ScoreboardTeam) iterator.next();

                icommandlistener.sendMessage(icommandlistener.a("commands.scoreboard.teams.list.entry", new Object[] { scoreboardteam1.getName(), scoreboardteam1.getDisplayName(), Integer.valueOf(scoreboardteam1.getPlayerNameSet().size())}));
            }
        }
    }

    protected void g(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();
        ScoreboardTeam scoreboardteam = scoreboard.getTeam(astring[i++]);
        HashSet hashset = new HashSet();
        String s;

        if (icommandlistener instanceof EntityHuman && i == astring.length) {
            s = c(icommandlistener).getLocalizedName();
            scoreboard.addPlayerToTeam(s, scoreboardteam);
            hashset.add(s);
        } else {
            while (i < astring.length) {
                s = d(icommandlistener, astring[i++]);
                scoreboard.addPlayerToTeam(s, scoreboardteam);
                hashset.add(s);
            }
        }

        if (!hashset.isEmpty()) {
            a(icommandlistener, "commands.scoreboard.teams.join.success", new Object[] { Integer.valueOf(hashset.size()), scoreboardteam.getName(), a(hashset.toArray(new String[0]))});
        }
    }

    protected void h(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();
        HashSet hashset = new HashSet();
        HashSet hashset1 = new HashSet();
        String s;

        if (icommandlistener instanceof EntityHuman && i == astring.length) {
            s = c(icommandlistener).getLocalizedName();
            if (scoreboard.removePlayerFromTeam(s)) {
                hashset.add(s);
            } else {
                hashset1.add(s);
            }
        } else {
            while (i < astring.length) {
                s = d(icommandlistener, astring[i++]);
                if (scoreboard.removePlayerFromTeam(s)) {
                    hashset.add(s);
                } else {
                    hashset1.add(s);
                }
            }
        }

        if (!hashset.isEmpty()) {
            a(icommandlistener, "commands.scoreboard.teams.leave.success", new Object[] { Integer.valueOf(hashset.size()), a(hashset.toArray(new String[0]))});
        }

        if (!hashset1.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[] { Integer.valueOf(hashset1.size()), a(hashset1.toArray(new String[0]))});
        }
    }

    protected void i(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();
        ScoreboardTeam scoreboardteam = this.a(astring[i++]);
        ArrayList arraylist = new ArrayList(scoreboardteam.getPlayerNameSet());

        if (arraylist.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[] { scoreboardteam.getName()});
        } else {
            Iterator iterator = arraylist.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                scoreboard.removePlayerFromTeam(s, scoreboardteam);
            }

            a(icommandlistener, "commands.scoreboard.teams.empty.success", new Object[] { Integer.valueOf(arraylist.size()), scoreboardteam.getName()});
        }
    }

    protected void e(ICommandListener icommandlistener, String s) {
        Scoreboard scoreboard = this.d();
        ScoreboardObjective scoreboardobjective = this.a(s, false);

        scoreboard.unregisterObjective(scoreboardobjective);
        a(icommandlistener, "commands.scoreboard.objectives.remove.success", new Object[] { s});
    }

    protected void d(ICommandListener icommandlistener) {
        Scoreboard scoreboard = this.d();
        Collection collection = scoreboard.getObjectives();

        if (collection.size() <= 0) {
            throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
        } else {
            icommandlistener.sendMessage(EnumChatFormat.DARK_GREEN + icommandlistener.a("commands.scoreboard.objectives.list.count", new Object[] { Integer.valueOf(collection.size())}));
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

                icommandlistener.sendMessage(icommandlistener.a("commands.scoreboard.objectives.list.entry", new Object[] { scoreboardobjective.getName(), scoreboardobjective.getDisplayName(), scoreboardobjective.getCriteria().getName()}));
            }
        }
    }

    protected void j(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();
        String s = astring[i++];
        int j = Scoreboard.getSlotForName(s);
        ScoreboardObjective scoreboardobjective = null;

        if (astring.length == 4) {
            scoreboardobjective = this.a(astring[i++], false);
        }

        if (j < 0) {
            throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[] { s});
        } else {
            scoreboard.setDisplaySlot(j, scoreboardobjective);
            if (scoreboardobjective != null) {
                a(icommandlistener, "commands.scoreboard.objectives.setdisplay.successSet", new Object[] { Scoreboard.getSlotName(j), scoreboardobjective.getName()});
            } else {
                a(icommandlistener, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[] { Scoreboard.getSlotName(j)});
            }
        }
    }

    protected void k(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();

        if (astring.length > i) {
            String s = d(icommandlistener, astring[i++]);
            Map map = scoreboard.getPlayerObjectives(s);

            if (map.size() <= 0) {
                throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[] { s});
            }

            icommandlistener.sendMessage(EnumChatFormat.DARK_GREEN + icommandlistener.a("commands.scoreboard.players.list.player.count", new Object[] { Integer.valueOf(map.size()), s}));
            Iterator iterator = map.values().iterator();

            while (iterator.hasNext()) {
                ScoreboardScore scoreboardscore = (ScoreboardScore) iterator.next();

                icommandlistener.sendMessage(icommandlistener.a("commands.scoreboard.players.list.player.entry", new Object[] { Integer.valueOf(scoreboardscore.getScore()), scoreboardscore.getObjective().getDisplayName(), scoreboardscore.getObjective().getName()}));
            }
        } else {
            Collection collection = scoreboard.getPlayers();

            if (collection.size() <= 0) {
                throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
            }

            icommandlistener.sendMessage(EnumChatFormat.DARK_GREEN + icommandlistener.a("commands.scoreboard.players.list.count", new Object[] { Integer.valueOf(collection.size())}));
            icommandlistener.sendMessage(a(collection.toArray()));
        }
    }

    protected void l(ICommandListener icommandlistener, String[] astring, int i) {
        String s = astring[i - 1];
        String s1 = d(icommandlistener, astring[i++]);
        ScoreboardObjective scoreboardobjective = this.a(astring[i++], true);
        int j = s.equalsIgnoreCase("set") ? a(icommandlistener, astring[i++]) : a(icommandlistener, astring[i++], 1);
        Scoreboard scoreboard = this.d();
        ScoreboardScore scoreboardscore = scoreboard.getPlayerScoreForObjective(s1, scoreboardobjective);

        if (s.equalsIgnoreCase("set")) {
            scoreboardscore.setScore(j);
        } else if (s.equalsIgnoreCase("add")) {
            scoreboardscore.addScore(j);
        } else {
            scoreboardscore.removeScore(j);
        }

        a(icommandlistener, "commands.scoreboard.players.set.success", new Object[] { scoreboardobjective.getName(), s1, Integer.valueOf(scoreboardscore.getScore())});
    }

    protected void m(ICommandListener icommandlistener, String[] astring, int i) {
        Scoreboard scoreboard = this.d();
        String s = d(icommandlistener, astring[i++]);

        scoreboard.resetPlayerScores(s);
        a(icommandlistener, "commands.scoreboard.players.reset.success", new Object[] { s});
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1) {
            return a(astring, new String[] { "objectives", "players", "teams"});
        } else {
            if (astring[0].equalsIgnoreCase("objectives")) {
                if (astring.length == 2) {
                    return a(astring, new String[] { "list", "add", "remove", "setdisplay"});
                }

                if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length == 4) {
                        return a(astring, IScoreboardCriteria.a.keySet());
                    }
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length == 3) {
                        return a(astring, this.a(false));
                    }
                } else if (astring[1].equalsIgnoreCase("setdisplay")) {
                    if (astring.length == 3) {
                        return a(astring, new String[] { "list", "sidebar", "belowName"});
                    }

                    if (astring.length == 4) {
                        return a(astring, this.a(false));
                    }
                }
            } else if (astring[0].equalsIgnoreCase("players")) {
                if (astring.length == 2) {
                    return a(astring, new String[] { "set", "add", "remove", "reset", "list"});
                }

                if (!astring[1].equalsIgnoreCase("set") && !astring[1].equalsIgnoreCase("add") && !astring[1].equalsIgnoreCase("remove")) {
                    if ((astring[1].equalsIgnoreCase("reset") || astring[1].equalsIgnoreCase("list")) && astring.length == 3) {
                        return a(astring, this.d().getPlayers());
                    }
                } else {
                    if (astring.length == 3) {
                        return a(astring, MinecraftServer.getServer().getPlayers());
                    }

                    if (astring.length == 4) {
                        return a(astring, this.a(true));
                    }
                }
            } else if (astring[0].equalsIgnoreCase("teams")) {
                if (astring.length == 2) {
                    return a(astring, new String[] { "add", "remove", "join", "leave", "empty", "list", "option"});
                }

                if (astring[1].equalsIgnoreCase("join")) {
                    if (astring.length == 3) {
                        return a(astring, this.d().getTeamNames());
                    }

                    if (astring.length >= 4) {
                        return a(astring, MinecraftServer.getServer().getPlayers());
                    }
                } else {
                    if (astring[1].equalsIgnoreCase("leave")) {
                        return a(astring, MinecraftServer.getServer().getPlayers());
                    }

                    if (!astring[1].equalsIgnoreCase("empty") && !astring[1].equalsIgnoreCase("list") && !astring[1].equalsIgnoreCase("remove")) {
                        if (astring[1].equalsIgnoreCase("option")) {
                            if (astring.length == 3) {
                                return a(astring, this.d().getTeamNames());
                            }

                            if (astring.length == 4) {
                                return a(astring, new String[] { "color", "friendlyfire", "seeFriendlyInvisibles"});
                            }

                            if (astring.length == 5) {
                                if (astring[3].equalsIgnoreCase("color")) {
                                    return a(astring, EnumChatFormat.a(true, false));
                                }

                                if (astring[3].equalsIgnoreCase("friendlyfire") || astring[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                                    return a(astring, new String[] { "true", "false"});
                                }
                            }
                        }
                    } else if (astring.length == 3) {
                        return a(astring, this.d().getTeamNames());
                    }
                }
            }

            return null;
        }
    }

    protected List a(boolean flag) {
        Collection collection = this.d().getObjectives();
        ArrayList arraylist = new ArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

            if (!flag || !scoreboardobjective.getCriteria().isReadOnly()) {
                arraylist.add(scoreboardobjective.getName());
            }
        }

        return arraylist;
    }

    public boolean a(String[] astring, int i) {
        return astring[0].equalsIgnoreCase("players") ? i == 2 : (!astring[0].equalsIgnoreCase("teams") ? false : i == 2 || i == 3);
    }
}
