package net.minecraft.server;

import java.util.UUID;

import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class WhiteListEntry extends JsonListEntry {

    public WhiteListEntry(GameProfile gameprofile) {
        super(gameprofile);
    }

    public WhiteListEntry(JsonObject jsonobject) {
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
