package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersistentScoreboard extends PersistentBase {

    private static final Logger a = LogManager.getLogger();
    private Scoreboard b;
    private NBTTagCompound c;

    public PersistentScoreboard() {
        this("scoreboard");
    }

    public PersistentScoreboard(String s) {
        super(s);
    }

    public void a(Scoreboard scoreboard) {
        this.b = scoreboard;
        if (this.c != null) {
            this.a(this.c);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (this.b == null) {
            this.c = nbttagcompound;
        } else {
            this.b(nbttagcompound.getList("Objectives", 10));
            this.c(nbttagcompound.getList("PlayerScores", 10));
            if (nbttagcompound.hasKeyOfType("DisplaySlots", 10)) {
                this.c(nbttagcompound.getCompound("DisplaySlots"));
            }

            if (nbttagcompound.hasKeyOfType("Teams", 9)) {
                this.a(nbttagcompound.getList("Teams", 10));
            }
        }
    }

    protected void a(NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.get(i);
            ScoreboardTeam scoreboardteam = this.b.createTeam(nbttagcompound.getString("Name"));

            scoreboardteam.setDisplayName(nbttagcompound.getString("DisplayName"));
            scoreboardteam.setPrefix(nbttagcompound.getString("Prefix"));
            scoreboardteam.setSuffix(nbttagcompound.getString("Suffix"));
            if (nbttagcompound.hasKeyOfType("AllowFriendlyFire", 99)) {
                scoreboardteam.setAllowFriendlyFire(nbttagcompound.getBoolean("AllowFriendlyFire"));
            }

            if (nbttagcompound.hasKeyOfType("SeeFriendlyInvisibles", 99)) {
                scoreboardteam.setCanSeeFriendlyInvisibles(nbttagcompound.getBoolean("SeeFriendlyInvisibles"));
            }

            this.a(scoreboardteam, nbttagcompound.getList("Players", 8));
        }
    }

    protected void a(ScoreboardTeam scoreboardteam, NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            this.b.addPlayerToTeam(nbttaglist.f(i), scoreboardteam.getName());
        }
    }

    protected void c(NBTTagCompound nbttagcompound) {
        for (int i = 0; i < 3; ++i) {
            if (nbttagcompound.hasKeyOfType("slot_" + i, 8)) {
                String s = nbttagcompound.getString("slot_" + i);
                ScoreboardObjective scoreboardobjective = this.b.getObjective(s);

                this.b.setDisplaySlot(i, scoreboardobjective);
            }
        }
    }

    protected void b(NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.get(i);
            IScoreboardCriteria iscoreboardcriteria = (IScoreboardCriteria) IScoreboardCriteria.a.get(nbttagcompound.getString("CriteriaName"));
            ScoreboardObjective scoreboardobjective = this.b.registerObjective(nbttagcompound.getString("Name"), iscoreboardcriteria);

            scoreboardobjective.setDisplayName(nbttagcompound.getString("DisplayName"));
        }
    }

    protected void c(NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.get(i);
            ScoreboardObjective scoreboardobjective = this.b.getObjective(nbttagcompound.getString("Objective"));
            ScoreboardScore scoreboardscore = this.b.getPlayerScoreForObjective(nbttagcompound.getString("Name"), scoreboardobjective);

            scoreboardscore.setScore(nbttagcompound.getInt("Score"));
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        if (this.b == null) {
            a.warn("Tried to save scoreboard without having a scoreboard...");
        } else {
            nbttagcompound.set("Objectives", this.b());
            nbttagcompound.set("PlayerScores", this.e());
            nbttagcompound.set("Teams", this.a());
            this.d(nbttagcompound);
        }
    }

    protected NBTTagList a() {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.b.getTeams();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardTeam scoreboardteam = (ScoreboardTeam) iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.setString("Name", scoreboardteam.getName());
            nbttagcompound.setString("DisplayName", scoreboardteam.getDisplayName());
            nbttagcompound.setString("Prefix", scoreboardteam.getPrefix());
            nbttagcompound.setString("Suffix", scoreboardteam.getSuffix());
            nbttagcompound.setBoolean("AllowFriendlyFire", scoreboardteam.allowFriendlyFire());
            nbttagcompound.setBoolean("SeeFriendlyInvisibles", scoreboardteam.canSeeFriendlyInvisibles());
            NBTTagList nbttaglist1 = new NBTTagList();
            Iterator iterator1 = scoreboardteam.getPlayerNameSet().iterator();

            while (iterator1.hasNext()) {
                String s = (String) iterator1.next();

                nbttaglist1.add(new NBTTagString(s));
            }

            nbttagcompound.set("Players", nbttaglist1);
            nbttaglist.add(nbttagcompound);
        }

        return nbttaglist;
    }

    protected void d(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        boolean flag = false;

        for (int i = 0; i < 3; ++i) {
            ScoreboardObjective scoreboardobjective = this.b.getObjectiveForSlot(i);

            if (scoreboardobjective != null) {
                nbttagcompound1.setString("slot_" + i, scoreboardobjective.getName());
                flag = true;
            }
        }

        if (flag) {
            nbttagcompound.set("DisplaySlots", nbttagcompound1);
        }
    }

    protected NBTTagList b() {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.b.getObjectives();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.setString("Name", scoreboardobjective.getName());
            nbttagcompound.setString("CriteriaName", scoreboardobjective.getCriteria().getName());
            nbttagcompound.setString("DisplayName", scoreboardobjective.getDisplayName());
            nbttaglist.add(nbttagcompound);
        }

        return nbttaglist;
    }

    protected NBTTagList e() {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.b.getScores();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardScore scoreboardscore = (ScoreboardScore) iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.setString("Name", scoreboardscore.getPlayerName());
            nbttagcompound.setString("Objective", scoreboardscore.getObjective().getName());
            nbttagcompound.setInt("Score", scoreboardscore.getScore());
            nbttaglist.add(nbttagcompound);
        }

        return nbttaglist;
    }
}
