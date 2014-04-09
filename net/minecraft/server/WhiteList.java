package net.minecraft.server;

import java.io.File;
import java.util.Iterator;

import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class WhiteList extends JsonList {

    public WhiteList(File file1) {
        super(file1);
    }

    protected JsonListEntry a(JsonObject jsonobject) {
        return new WhiteListEntry(jsonobject);
    }

    public boolean isWhitelisted(GameProfile gameprofile) {
        return this.d(gameprofile);
    }

    public String[] getEntries() {
        String[] astring = new String[this.e().size()];
        int i = 0;

        WhiteListEntry whitelistentry;

        for (Iterator iterator = this.e().values().iterator(); iterator.hasNext(); astring[i++] = ((GameProfile) whitelistentry.f()).getName()) {
            whitelistentry = (WhiteListEntry) iterator.next();
        }

        return astring;
    }

    protected String b(GameProfile gameprofile) {
        return gameprofile.getId().toString();
    }

    public GameProfile a(String s) {
        Iterator iterator = this.e().values().iterator();

        WhiteListEntry whitelistentry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            whitelistentry = (WhiteListEntry) iterator.next();
        } while (!s.equalsIgnoreCase(((GameProfile) whitelistentry.f()).getName()));

        return (GameProfile) whitelistentry.f();
    }

    protected String a(Object object) {
        return this.b((GameProfile) object);
    }
}
