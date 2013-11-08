package net.minecraft.server;

import java.lang.reflect.Type;

import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.google.gson.JsonSerializationContext;
import net.minecraft.util.com.google.gson.JsonSerializer;

public class ServerPingSerializer implements JsonDeserializer, JsonSerializer {

    public ServerPingSerializer() {}

    public ServerPing a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        JsonObject jsonobject = ChatDeserializer.l(jsonelement, "status");
        ServerPing serverping = new ServerPing();

        if (jsonobject.has("description")) {
            serverping.setMOTD((IChatBaseComponent) jsondeserializationcontext.deserialize(jsonobject.get("description"), IChatBaseComponent.class));
        }

        if (jsonobject.has("players")) {
            serverping.setPlayerSample((ServerPingPlayerSample) jsondeserializationcontext.deserialize(jsonobject.get("players"), ServerPingPlayerSample.class));
        }

        if (jsonobject.has("version")) {
            serverping.setServerInfo((ServerPingServerData) jsondeserializationcontext.deserialize(jsonobject.get("version"), ServerPingServerData.class));
        }

        if (jsonobject.has("favicon")) {
            serverping.setFavicon(ChatDeserializer.h(jsonobject, "favicon"));
        }

        return serverping;
    }

    public JsonElement a(ServerPing serverping, Type type, JsonSerializationContext jsonserializationcontext) {
        JsonObject jsonobject = new JsonObject();

        if (serverping.a() != null) {
            jsonobject.add("description", jsonserializationcontext.serialize(serverping.a()));
        }

        if (serverping.b() != null) {
            jsonobject.add("players", jsonserializationcontext.serialize(serverping.b()));
        }

        if (serverping.c() != null) {
            jsonobject.add("version", jsonserializationcontext.serialize(serverping.c()));
        }

        if (serverping.d() != null) {
            jsonobject.addProperty("favicon", serverping.d());
        }

        return jsonobject;
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((ServerPing) object, type, jsonserializationcontext);
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }
}
