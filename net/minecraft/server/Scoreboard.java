package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Scoreboard {

    private final Map a = new HashMap();
    private final Map b = new HashMap();
    private final Map c = new HashMap();
    private final ScoreboardObjective[] d = new ScoreboardObjective[3];
    private final Map e = new HashMap();
    private final Map f = new HashMap();

    public Scoreboard() {}

    public ScoreboardObjective b(String s) {
        return (ScoreboardObjective) this.a.get(s);
    }

    public ScoreboardObjective a(String s, IObjective iobjective) {
        ScoreboardObjective scoreboardobjective = this.b(s);

        if (scoreboardobjective != null) {
            throw new IllegalArgumentException("An objective with the name \'" + s + "\' already exists!");
        } else {
            scoreboardobjective = new ScoreboardObjective(this, s, iobjective);
            Object object = (List) this.b.get(iobjective);

            if (object == null) {
                object = new ArrayList();
                this.b.put(iobjective, object);
            }

            ((List) object).add(scoreboardobjective);
            this.a.put(s, scoreboardobjective);
            this.a(scoreboardobjective);
            return scoreboardobjective;
        }
    }

    public Collection a(IObjective iobjective) {
        Collection collection = (Collection) this.b.get(iobjective);

        return collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public ScoreboardScore a(String s, ScoreboardObjective scoreboardobjective) {
        Object object = (Map) this.c.get(s);

        if (object == null) {
            object = new HashMap();
            this.c.put(s, object);
        }

        ScoreboardScore scoreboardscore = (ScoreboardScore) ((Map) object).get(scoreboardobjective);

        if (scoreboardscore == null) {
            scoreboardscore = new ScoreboardScore(this, scoreboardobjective, s);
            ((Map) object).put(scoreboardobjective, scoreboardscore);
        }

        return scoreboardscore;
    }

    public Collection i(ScoreboardObjective scoreboardobjective) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.c.values().iterator();

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

    public Collection c() {
        return this.a.values();
    }

    public Collection d() {
        return this.c.keySet();
    }

    public void c(String s) {
        Map map = (Map) this.c.remove(s);

        if (map != null) {
            this.a(s);
        }
    }

    public Collection e() {
        Collection collection = this.c.values();
        ArrayList arraylist = new ArrayList();

        if (collection != null) {
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                Map map = (Map) iterator.next();

                arraylist.addAll(map.values());
            }
        }

        return arraylist;
    }

    public Map d(String s) {
        Object object = (Map) this.c.get(s);

        if (object == null) {
            object = new HashMap();
        }

        return (Map) object;
    }

    public void k(ScoreboardObjective scoreboardobjective) {
        this.a.remove(scoreboardobjective.b());

        for (int i = 0; i < 3; ++i) {
            if (this.a(i) == scoreboardobjective) {
                this.a(i, (ScoreboardObjective) null);
            }
        }

        List list = (List) this.b.get(scoreboardobjective.c());

        if (list != null) {
            list.remove(scoreboardobjective);
        }

        Iterator iterator = this.c.values().iterator();

        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();

            map.remove(scoreboardobjective);
        }

        this.c(scoreboardobjective);
    }

    public void a(int i, ScoreboardObjective scoreboardobjective) {
        this.d[i] = scoreboardobjective;
    }

    public ScoreboardObjective a(int i) {
        return this.d[i];
    }

    public ScoreboardTeam e(String s) {
        return (ScoreboardTeam) this.e.get(s);
    }

    public ScoreboardTeam f(String s) {
        ScoreboardTeam scoreboardteam = this.e(s);

        if (scoreboardteam != null) {
            throw new IllegalArgumentException("An objective with the name \'" + s + "\' already exists!");
        } else {
            scoreboardteam = new ScoreboardTeam(this, s);
            this.e.put(s, scoreboardteam);
            this.a(scoreboardteam);
            return scoreboardteam;
        }
    }

    public void d(ScoreboardTeam scoreboardteam) {
        this.e.remove(scoreboardteam.b());
        Iterator iterator = scoreboardteam.d().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            this.f.remove(s);
        }

        this.c(scoreboardteam);
    }

    public void a(String s, ScoreboardTeam scoreboardteam) {
        if (this.i(s) != null) {
            this.g(s);
        }

        this.f.put(s, scoreboardteam);
        scoreboardteam.d().add(s);
    }

    public boolean g(String s) {
        ScoreboardTeam scoreboardteam = this.i(s);

        if (scoreboardteam != null) {
            this.b(s, scoreboardteam);
            return true;
        } else {
            return false;
        }
    }

    public void b(String s, ScoreboardTeam scoreboardteam) {
        if (this.i(s) != scoreboardteam) {
            throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team \'" + scoreboardteam.b() + "\'.");
        } else {
            this.f.remove(s);
            scoreboardteam.d().remove(s);
        }
    }

    public Collection f() {
        return this.e.keySet();
    }

    public Collection g() {
        return this.e.values();
    }

    public ScoreboardTeam i(String s) {
        return (ScoreboardTeam) this.f.get(s);
    }

    public void a(ScoreboardObjective scoreboardobjective) {}

    public void b(ScoreboardObjective scoreboardobjective) {}

    public void c(ScoreboardObjective scoreboardobjective) {}

    public void a(ScoreboardScore scoreboardscore) {}

    public void a(String s) {}

    public void a(ScoreboardTeam scoreboardteam) {}

    public void b(ScoreboardTeam scoreboardteam) {}

    public void c(ScoreboardTeam scoreboardteam) {}

    public static String b(int i) {
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

    public static int j(String s) {
        return s.equalsIgnoreCase("list") ? 0 : (s.equalsIgnoreCase("sidebar") ? 1 : (s.equalsIgnoreCase("belowName") ? 2 : -1));
    }
}
