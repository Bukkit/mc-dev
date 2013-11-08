package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Scoreboard {

    private final Map objectivesByName = new HashMap();
    private final Map objectivesByCriteria = new HashMap();
    private final Map playerScores = new HashMap();
    private final ScoreboardObjective[] displaySlots = new ScoreboardObjective[3];
    private final Map teamsByName = new HashMap();
    private final Map teamsByPlayer = new HashMap();

    public Scoreboard() {}

    public ScoreboardObjective getObjective(String s) {
        return (ScoreboardObjective) this.objectivesByName.get(s);
    }

    public ScoreboardObjective registerObjective(String s, IScoreboardCriteria iscoreboardcriteria) {
        ScoreboardObjective scoreboardobjective = this.getObjective(s);

        if (scoreboardobjective != null) {
            throw new IllegalArgumentException("An objective with the name \'" + s + "\' already exists!");
        } else {
            scoreboardobjective = new ScoreboardObjective(this, s, iscoreboardcriteria);
            Object object = (List) this.objectivesByCriteria.get(iscoreboardcriteria);

            if (object == null) {
                object = new ArrayList();
                this.objectivesByCriteria.put(iscoreboardcriteria, object);
            }

            ((List) object).add(scoreboardobjective);
            this.objectivesByName.put(s, scoreboardobjective);
            this.handleObjectiveAdded(scoreboardobjective);
            return scoreboardobjective;
        }
    }

    public Collection getObjectivesForCriteria(IScoreboardCriteria iscoreboardcriteria) {
        Collection collection = (Collection) this.objectivesByCriteria.get(iscoreboardcriteria);

        return collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public ScoreboardScore getPlayerScoreForObjective(String s, ScoreboardObjective scoreboardobjective) {
        Object object = (Map) this.playerScores.get(s);

        if (object == null) {
            object = new HashMap();
            this.playerScores.put(s, object);
        }

        ScoreboardScore scoreboardscore = (ScoreboardScore) ((Map) object).get(scoreboardobjective);

        if (scoreboardscore == null) {
            scoreboardscore = new ScoreboardScore(this, scoreboardobjective, s);
            ((Map) object).put(scoreboardobjective, scoreboardscore);
        }

        return scoreboardscore;
    }

    public Collection getScoresForObjective(ScoreboardObjective scoreboardobjective) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.playerScores.values().iterator();

        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();
            ScoreboardScore scoreboardscore = (ScoreboardScore) map.get(scoreboardobjective);

            if (scoreboardscore != null) {
                arraylist.add(scoreboardscore);
            }
        }

        Collections.sort(arraylist, ScoreboardScore.a);
        return arraylist;
    }

    public Collection getObjectives() {
        return this.objectivesByName.values();
    }

    public Collection getPlayers() {
        return this.playerScores.keySet();
    }

    public void resetPlayerScores(String s) {
        Map map = (Map) this.playerScores.remove(s);

        if (map != null) {
            this.handlePlayerRemoved(s);
        }
    }

    public Collection getScores() {
        Collection collection = this.playerScores.values();
        ArrayList arraylist = new ArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();

            arraylist.addAll(map.values());
        }

        return arraylist;
    }

    public Map getPlayerObjectives(String s) {
        Object object = (Map) this.playerScores.get(s);

        if (object == null) {
            object = new HashMap();
        }

        return (Map) object;
    }

    public void unregisterObjective(ScoreboardObjective scoreboardobjective) {
        this.objectivesByName.remove(scoreboardobjective.getName());

        for (int i = 0; i < 3; ++i) {
            if (this.getObjectiveForSlot(i) == scoreboardobjective) {
                this.setDisplaySlot(i, (ScoreboardObjective) null);
            }
        }

        List list = (List) this.objectivesByCriteria.get(scoreboardobjective.getCriteria());

        if (list != null) {
            list.remove(scoreboardobjective);
        }

        Iterator iterator = this.playerScores.values().iterator();

        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();

            map.remove(scoreboardobjective);
        }

        this.handleObjectiveRemoved(scoreboardobjective);
    }

    public void setDisplaySlot(int i, ScoreboardObjective scoreboardobjective) {
        this.displaySlots[i] = scoreboardobjective;
    }

    public ScoreboardObjective getObjectiveForSlot(int i) {
        return this.displaySlots[i];
    }

    public ScoreboardTeam getTeam(String s) {
        return (ScoreboardTeam) this.teamsByName.get(s);
    }

    public ScoreboardTeam createTeam(String s) {
        ScoreboardTeam scoreboardteam = this.getTeam(s);

        if (scoreboardteam != null) {
            throw new IllegalArgumentException("A team with the name \'" + s + "\' already exists!");
        } else {
            scoreboardteam = new ScoreboardTeam(this, s);
            this.teamsByName.put(s, scoreboardteam);
            this.handleTeamAdded(scoreboardteam);
            return scoreboardteam;
        }
    }

    public void removeTeam(ScoreboardTeam scoreboardteam) {
        this.teamsByName.remove(scoreboardteam.getName());
        Iterator iterator = scoreboardteam.getPlayerNameSet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            this.teamsByPlayer.remove(s);
        }

        this.handleTeamRemoved(scoreboardteam);
    }

    public boolean addPlayerToTeam(String s, String s1) {
        if (!this.teamsByName.containsKey(s1)) {
            return false;
        } else {
            ScoreboardTeam scoreboardteam = this.getTeam(s1);

            if (this.getPlayerTeam(s) != null) {
                this.removePlayerFromTeam(s);
            }

            this.teamsByPlayer.put(s, scoreboardteam);
            scoreboardteam.getPlayerNameSet().add(s);
            return true;
        }
    }

    public boolean removePlayerFromTeam(String s) {
        ScoreboardTeam scoreboardteam = this.getPlayerTeam(s);

        if (scoreboardteam != null) {
            this.removePlayerFromTeam(s, scoreboardteam);
            return true;
        } else {
            return false;
        }
    }

    public void removePlayerFromTeam(String s, ScoreboardTeam scoreboardteam) {
        if (this.getPlayerTeam(s) != scoreboardteam) {
            throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team \'" + scoreboardteam.getName() + "\'.");
        } else {
            this.teamsByPlayer.remove(s);
            scoreboardteam.getPlayerNameSet().remove(s);
        }
    }

    public Collection getTeamNames() {
        return this.teamsByName.keySet();
    }

    public Collection getTeams() {
        return this.teamsByName.values();
    }

    public ScoreboardTeam getPlayerTeam(String s) {
        return (ScoreboardTeam) this.teamsByPlayer.get(s);
    }

    public void handleObjectiveAdded(ScoreboardObjective scoreboardobjective) {}

    public void handleObjectiveChanged(ScoreboardObjective scoreboardobjective) {}

    public void handleObjectiveRemoved(ScoreboardObjective scoreboardobjective) {}

    public void handleScoreChanged(ScoreboardScore scoreboardscore) {}

    public void handlePlayerRemoved(String s) {}

    public void handleTeamAdded(ScoreboardTeam scoreboardteam) {}

    public void handleTeamChanged(ScoreboardTeam scoreboardteam) {}

    public void handleTeamRemoved(ScoreboardTeam scoreboardteam) {}

    public static String getSlotName(int i) {
        switch (i) {
        case 0:
            return "list";

        case 1:
            return "sidebar";

        case 2:
            return "belowName";

        default:
            return null;
        }
    }

    public static int getSlotForName(String s) {
        return s.equalsIgnoreCase("list") ? 0 : (s.equalsIgnoreCase("sidebar") ? 1 : (s.equalsIgnoreCase("belowName") ? 2 : -1));
    }
}
