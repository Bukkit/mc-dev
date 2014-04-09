package net.minecraft.server;

import java.util.UUID;

import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class OpListEntry extends JsonListEntry {

    private final int a;

    public OpListEntry(GameProfile gameprofile, int i) {
        super(gameprofile);
        this.a = i;
    }

    public OpListEntry(JsonObject jsonobject) {
        super(b(jsonobject), jsonobject);
        this.a = jsonobject.has("level") ? jsonobject.get("level").getAsInt() : 0;
    }

    public int a() {
        return this.a;
    }

    protected void a(JsonObject jsonobject) {
        if (this.f() != null) {
            jsonobject.addProperty("uuid", ((GameProfile) this.f()).getId() == null ? "" : ((GameProfile) this.f()).getId().toString());
            jsonobject.addProperty("name", ((GameProfile) this.f()).getName());
            super.a(jsonobject);
            jsonobject.addProperty("level", Integer.valueOf(this.a));
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
