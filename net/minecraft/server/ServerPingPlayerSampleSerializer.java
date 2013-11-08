package net.minecraft.server;

import java.lang.reflect.Type;

import net.minecraft.util.com.google.gson.JsonArray;
import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.google.gson.JsonSerializationContext;
import net.minecraft.util.com.google.gson.JsonSerializer;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class ServerPingPlayerSampleSerializer implements JsonDeserializer, JsonSerializer {

    public ServerPingPlayerSampleSerializer() {}

    public ServerPingPlayerSample a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        JsonObject jsonobject = ChatDeserializer.l(jsonelement, "players");
        ServerPingPlayerSample serverpingplayersample = new ServerPingPlayerSample(ChatDeserializer.m(jsonobject, "max"), ChatDeserializer.m(jsonobject, "online"));

        if (ChatDeserializer.d(jsonobject, "sample")) {
            JsonArray jsonarray = ChatDeserializer.t(jsonobject, "sample");

            if (jsonarray.size() > 0) {
                GameProfile[] agameprofile = new GameProfile[jsonarray.size()];

                for (int i = 0; i < agameprofile.length; ++i) {
                    JsonObject jsonobject1 = ChatDeserializer.l(jsonarray.get(i), "player[" + i + "]");

                    agameprofile[i] = new GameProfile(ChatDeserializer.h(jsonobject1, "id"), ChatDeserializer.h(jsonobject1, "name"));
                }

                serverpingplayersample.a(agameprofile);
            }
        }

        return serverpingplayersample;
    }

    public JsonElement a(ServerPingPlayerSample serverpingplayersample, Type type, JsonSerializationContext jsonserializationcontext) {
        JsonObject jsonobject = new JsonObject();

        jsonobject.addProperty("max", Integer.valueOf(serverpingplayersample.a()));
        jsonobject.addProperty("online", Integer.valueOf(serverpingplayersample.b()));
        if (serverpingplayersample.c() != null && serverpingplayersample.c().length > 0) {
            JsonArray jsonarray = new JsonArray();

            for (int i = 0; i < serverpingplayersample.c().length; ++i) {
                JsonObject jsonobject1 = new JsonObject();

                jsonobject1.addProperty("id", serverpingplayersample.c()[i].getId());
                jsonobject1.addProperty("name", serverpingplayersample.c()[i].getName());
                jsonarray.add(jsonobject1);
            }

            jsonobject.add("sample", jsonarray);
        }

        return jsonobject;
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((ServerPingPlayerSample) object, type, jsonserializationcontext);
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }
}
