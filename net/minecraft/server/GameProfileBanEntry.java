package net.minecraft.server;

import java.util.Date;
import java.util.UUID;

import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class GameProfileBanEntry extends ExpirableListEntry {

    public GameProfileBanEntry(GameProfile gameprofile) {
        this(gameprofile, (Date) null, (String) null, (Date) null, (String) null);
    }

    public GameProfileBanEntry(GameProfile gameprofile, Date date, String s, Date date1, String s1) {
        super(gameprofile, date1, s, date1, s1);
    }

    public GameProfileBanEntry(JsonObject jsonobject) {
        super(b(jsonobject), jsonobject);
    }

    protected void a(JsonObject jsonobject) {
        if (this.getKey() != null) {
            jsonobject.addProperty("uuid", ((GameProfile) this.getKey()).getId() == null ? "" : ((GameProfile) this.getKey()).getId().toString());
            jsonobject.addProperty("name", ((GameProfile) this.getKey()).getName());
            super.a(jsonobject);
        }
    }

    private static GameProfile b(JsonObject jsonobject) {
        if (jsonobject.has("uuid") && jsonobject.has("name")) {
            String s = jsonobject.get("uuid").getAsString();

            UUID uuid;

            try {
                uuid = UUID.fromString(s);
            } catch (Throwable throwable) {
                return null;
            }

            return new GameProfile(uuid, jsonobject.get("name").getAsString());
        } else {
            return null;
        }
    }
}
