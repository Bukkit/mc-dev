package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;

public class ScoreboardSaveData extends WorldMapBase {

    private Scoreboard a;
    private NBTTagCompound b;

    public ScoreboardSaveData() {
        this("scoreboard");
    }

    public ScoreboardSaveData(String s) {
        super(s);
    }

    public void a(Scoreboard scoreboard) {
        this.a = scoreboard;
        if (this.b != null) {
            this.a(this.b);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (this.a == null) {
            this.b = nbttagcompound;
        } else {
            this.b(nbttagcompound.getList("Objectives"));
            this.c(nbttagcompound.getList("PlayerScores"));
            if (nbttagcompound.hasKey("DisplaySlots")) {
                this.c(nbttagcompound.getCompound("DisplaySlots"));
            }

            if (nbttagcompound.hasKey("Teams")) {
                this.a(nbttagcompound.getList("Teams"));
            }
        }
    }

    protected void a(NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.get(i);
            ScoreboardTeam scoreboardteam = this.a.f(nbttagcompound.getString("Name"));

            scoreboardteam.a(nbttagcompound.getString("DisplayName"));
            scoreboardteam.b(nbttagcompound.getString("Prefix"));
            scoreboardteam.c(nbttagcompound.getString("Suffix"));
            if (nbttagcompound.hasKey("AllowFriendlyFire")) {
                scoreboardteam.a(nbttagcompound.getBoolean("AllowFriendlyFire"));
            }

            if (nbttagcompound.hasKey("SeeFriendlyInvisibles")) {
                scoreboardteam.b(nbttagcompound.getBoolean("SeeFriendlyInvisibles"));
            }

            this.a(scoreboardteam, nbttagcompound.getList("Players"));
        }
    }

    protected void a(ScoreboardTeam scoreboardteam, NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            this.a.a(((NBTTagString) nbttaglist.get(i)).data, scoreboardteam);
        }
    }

    protected void c(NBTTagCompound nbttagcompound) {
        for (int i = 0; i < 3; ++i) {
            if (nbttagcompound.hasKey("slot_" + i)) {
                String s = nbttagcompound.getString("slot_" + i);
                ScoreboardObjective scoreboardobjective = this.a.b(s);

                this.a.a(i, scoreboardobjective);
            }
        }
    }

    protected void b(NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.get(i);
            IObjective iobjective = (IObjective) IObjective.a.get(nbttagcompound.getString("CriteriaName"));
            ScoreboardObjective scoreboardobjective = this.a.a(nbttagcompound.getString("Name"), iobjective);

            scoreboardobjective.a(nbttagcompound.getString("DisplayName"));
        }
    }

    protected void c(NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.get(i);
            ScoreboardObjective scoreboardobjective = this.a.b(nbttagcompound.getString("Objective"));
            ScoreboardScore scoreboardscore = this.a.a(nbttagcompound.getString("Name"), scoreboardobjective);

            scoreboardscore.c(nbttagcompound.getInt("Score"));
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        if (this.a == null) {
            MinecraftServer.getServer().getLogger().warning("Tried to save scoreboard without having a scoreboard...");
        } else {
            nbttagcompound.set("Objectives", this.b());
            nbttagcompound.set("PlayerScores", this.e());
            nbttagcompound.set("Teams", this.a());
            this.d(nbttagcompound);
        }
    }

    protected NBTTagList a() {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.a.g();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardTeam scoreboardteam = (ScoreboardTeam) iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.setString("Name", scoreboardteam.b());
            nbttagcompound.setString("DisplayName", scoreboardteam.c());
            nbttagcompound.setString("Prefix", scoreboardteam.e());
            nbttagcompound.setString("Suffix", scoreboardteam.f());
            nbttagcompound.setBoolean("AllowFriendlyFire", scoreboardteam.g());
            nbttagcompound.setBoolean("SeeFriendlyInvisibles", scoreboardteam.h());
            NBTTagList nbttaglist1 = new NBTTagList();
            Iterator iterator1 = scoreboardteam.d().iterator();

            while (iterator1.hasNext()) {
                String s = (String) iterator1.next();

                nbttaglist1.add(new NBTTagString("", s));
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
            ScoreboardObjective scoreboardobjective = this.a.a(i);

            if (scoreboardobjective != null) {
                nbttagcompound1.setString("slot_" + i, scoreboardobjective.b());
                flag = true;
            }
        }

        if (flag) {
            nbttagcompound.setCompound("DisplaySlots", nbttagcompound1);
        }
    }

    protected NBTTagList b() {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.a.c();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.setString("Name", scoreboardobjective.b());
            nbttagcompound.setString("CriteriaName", scoreboardobjective.c().a());
            nbttagcompound.setString("DisplayName", scoreboardobjective.d());
            nbttaglist.add(nbttagcompound);
        }

        return nbttaglist;
    }

    protected NBTTagList e() {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.a.e();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardScore scoreboardscore = (ScoreboardScore) iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.setString("Name", scoreboardscore.e());
            nbttagcompound.setString("Objective", scoreboardscore.d().b());
            nbttagcompound.setInt("Score", scoreboardscore.c());
            nbttaglist.add(nbttagcompound);
        }

        return nbttaglist;
    }
}
