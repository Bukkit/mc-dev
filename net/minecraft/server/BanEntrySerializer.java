package net.minecraft.server;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.google.gson.JsonSerializationContext;
import net.minecraft.util.com.google.gson.JsonSerializer;
import net.minecraft.util.com.mojang.authlib.GameProfile;

class BanEntrySerializer implements JsonDeserializer, JsonSerializer {

    final UserCache a;

    private BanEntrySerializer(UserCache usercache) {
        this.a = usercache;
    }

    public JsonElement a(UserCacheEntry usercacheentry, Type type, JsonSerializationContext jsonserializationcontext) {
        JsonObject jsonobject = new JsonObject();

        jsonobject.addProperty("name", usercacheentry.a().getName());
        UUID uuid = usercacheentry.a().getId();

        jsonobject.addProperty("uuid", uuid == null ? "" : uuid.toString());
        jsonobject.addProperty("expiresOn", UserCache.a.format(usercacheentry.b()));
        return jsonobject;
    }

    public UserCacheEntry a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        if (jsonelement.isJsonObject()) {
            JsonObject jsonobject = jsonelement.getAsJsonObject();
            JsonElement jsonelement1 = jsonobject.get("name");
            JsonElement jsonelement2 = jsonobject.get("uuid");
            JsonElement jsonelement3 = jsonobject.get("expiresOn");

            if (jsonelement1 != null && jsonelement2 != null) {
                String s = jsonelement2.getAsString();
                String s1 = jsonelement1.getAsString();
                Date date = null;

                if (jsonelement3 != null) {
                    try {
                        date = UserCache.a.parse(jsonelement3.getAsString());
                    } catch (ParseException parseexception) {
                        date = null;
                    }
                }

                if (s1 != null && s != null) {
                    UUID uuid;

                    try {
                        uuid = UUID.fromString(s);
                    } catch (Throwable throwable) {
                        return null;
                    }

                    UserCacheEntry usercacheentry = new UserCacheEntry(this.a, new GameProfile(uuid, s1), date, (GameProfileLookup) null);

                    return usercacheentry;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((UserCacheEntry) object, type, jsonserializationcontext);
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }

    BanEntrySerializer(UserCache usercache, GameProfileLookup gameprofilelookup) {
        this(usercache);
    }
}
