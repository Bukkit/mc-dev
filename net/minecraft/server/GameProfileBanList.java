package net.minecraft.server;

import java.io.File;
import java.util.Iterator;

import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class GameProfileBanList extends JsonList {

    public GameProfileBanList(File file1) {
        super(file1);
    }

    protected JsonListEntry a(JsonObject jsonobject) {
        return new GameProfileBanEntry(jsonobject);
    }

    public boolean isBanned(GameProfile gameprofile) {
        return this.d(gameprofile);
    }

    public String[] getEntries() {
        String[] astring = new String[this.e().size()];
        int i = 0;

        GameProfileBanEntry gameprofilebanentry;

        for (Iterator iterator = this.e().values().iterator(); iterator.hasNext(); astring[i++] = ((GameProfile) gameprofilebanentry.f()).getName()) {
            gameprofilebanentry = (GameProfileBanEntry) iterator.next();
        }

        return astring;
    }

    protected String b(GameProfile gameprofile) {
        return gameprofile.getId().toString();
    }

    public GameProfile a(String s) {
        Iterator iterator = this.e().values().iterator();

        GameProfileBanEntry gameprofilebanentry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            gameprofilebanentry = (GameProfileBanEntry) iterator.next();
        } while (!s.equalsIgnoreCase(((GameProfile) gameprofilebanentry.f()).getName()));

        return (GameProfile) gameprofilebanentry.f();
    }

    protected String a(Object object) {
        return this.b((GameProfile) object);
    }
}
