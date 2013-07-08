package net.minecraft.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ScoreboardTeam extends ScoreboardTeamBase {

    private final Scoreboard a;
    private final String b;
    private final Set c = new HashSet();
    private String d;
    private String e = "";
    private String f = "";
    private boolean g = true;
    private boolean h = true;

    public ScoreboardTeam(Scoreboard scoreboard, String s) {
        this.a = scoreboard;
        this.b = s;
        this.d = s;
    }

    public String getName() {
        return this.b;
    }

    public String getDisplayName() {
        return this.d;
    }

    public void setDisplayName(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Name cannot be null");
        } else {
            this.d = s;
            this.a.handleTeamChanged(this);
        }
    }

    public Collection getPlayerNameSet() {
        return this.c;
    }

    public String getPrefix() {
        return this.e;
    }

    public void setPrefix(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        } else {
            this.e = s;
            this.a.handleTeamChanged(this);
        }
    }

    public String getSuffix() {
        return this.f;
    }

    public void setSuffix(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Suffix cannot be null");
        } else {
            this.f = s;
            this.a.handleTeamChanged(this);
        }
    }

    public String getFormattedName(String s) {
        return this.getPrefix() + s + this.getSuffix();
    }

    public static String getPlayerDisplayName(ScoreboardTeamBase scoreboardteambase, String s) {
        return scoreboardteambase == null ? s : scoreboardteambase.getFormattedName(s);
    }

    public boolean allowFriendlyFire() {
        return this.g;
    }

    public void setAllowFriendlyFire(boolean flag) {
        this.g = flag;
        this.a.handleTeamChanged(this);
    }

    public boolean canSeeFriendlyInvisibles() {
        return this.h;
    }

    public void setCanSeeFriendlyInvisibles(boolean flag) {
        this.h = flag;
        this.a.handleTeamChanged(this);
    }

    public int packOptionData() {
        int i = 0;

        if (this.allowFriendlyFire()) {
            i |= 1;
        }

        if (this.canSeeFriendlyInvisibles()) {
            i |= 2;
        }

        return i;
    }
}
