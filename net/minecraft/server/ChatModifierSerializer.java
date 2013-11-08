package net.minecraft.server;

import java.lang.reflect.Type;

import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.google.gson.JsonSerializationContext;
import net.minecraft.util.com.google.gson.JsonSerializer;

public class ChatModifierSerializer implements JsonDeserializer, JsonSerializer {

    public ChatModifierSerializer() {}

    public ChatModifier a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        if (jsonelement.isJsonObject()) {
            ChatModifier chatmodifier = new ChatModifier();
            JsonObject jsonobject = jsonelement.getAsJsonObject();

            if (jsonobject.has("bold")) {
                ChatModifier.a(chatmodifier, Boolean.valueOf(jsonobject.get("bold").getAsBoolean()));
            }

            if (jsonobject.has("italic")) {
                ChatModifier.b(chatmodifier, Boolean.valueOf(jsonobject.get("italic").getAsBoolean()));
            }

            if (jsonobject.has("underlined")) {
                ChatModifier.c(chatmodifier, Boolean.valueOf(jsonobject.get("underlined").getAsBoolean()));
            }

            if (jsonobject.has("strikethrough")) {
                ChatModifier.d(chatmodifier, Boolean.valueOf(jsonobject.get("strikethrough").getAsBoolean()));
            }

            if (jsonobject.has("obfuscated")) {
                ChatModifier.e(chatmodifier, Boolean.valueOf(jsonobject.get("obfuscated").getAsBoolean()));
            }

            if (jsonobject.has("color")) {
                ChatModifier.a(chatmodifier, (EnumChatFormat) jsondeserializationcontext.deserialize(jsonobject.get("color"), EnumChatFormat.class));
            }

            JsonObject jsonobject1;

            if (jsonobject.has("clickEvent")) {
                jsonobject1 = jsonobject.getAsJsonObject("clickEvent");
                EnumClickAction enumclickaction = EnumClickAction.a(jsonobject1.getAsJsonPrimitive("action").getAsString());
                String s = jsonobject1.getAsJsonPrimitive("value").getAsString();

                if (enumclickaction != null && s != null && enumclickaction.a()) {
                    ChatModifier.a(chatmodifier, new ChatClickable(enumclickaction, s));
                }
            }

            if (jsonobject.has("hoverEvent")) {
                jsonobject1 = jsonobject.getAsJsonObject("hoverEvent");
                EnumHoverAction enumhoveraction = EnumHoverAction.a(jsonobject1.getAsJsonPrimitive("action").getAsString());
                IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) jsondeserializationcontext.deserialize(jsonobject1.get("value"), IChatBaseComponent.class);

                if (enumhoveraction != null && ichatbasecomponent != null && enumhoveraction.a()) {
                    ChatModifier.a(chatmodifier, new ChatHoverable(enumhoveraction, ichatbasecomponent));
                }
            }

            return chatmodifier;
        } else {
            return null;
        }
    }

    public JsonElement a(ChatModifier chatmodifier, Type type, JsonSerializationContext jsonserializationcontext) {
        if (chatmodifier.g()) {
            return null;
        } else {
            JsonObject jsonobject = new JsonObject();

            if (ChatModifier.b(chatmodifier) != null) {
                jsonobject.addProperty("bold", ChatModifier.b(chatmodifier));
            }

            if (ChatModifier.c(chatmodifier) != null) {
                jsonobject.addProperty("italic", ChatModifier.c(chatmodifier));
            }

            if (ChatModifier.d(chatmodifier) != null) {
                jsonobject.addProperty("underlined", ChatModifier.d(chatmodifier));
            }

            if (ChatModifier.e(chatmodifier) != null) {
                jsonobject.addProperty("strikethrough", ChatModifier.e(chatmodifier));
            }

            if (ChatModifier.f(chatmodifier) != null) {
                jsonobject.addProperty("obfuscated", ChatModifier.f(chatmodifier));
            }

            if (ChatModifier.g(chatmodifier) != null) {
                jsonobject.add("color", jsonserializationcontext.serialize(ChatModifier.g(chatmodifier)));
            }

            JsonObject jsonobject1;

            if (ChatModifier.h(chatmodifier) != null) {
                jsonobject1 = new JsonObject();
                jsonobject1.addProperty("action", ChatModifier.h(chatmodifier).a().b());
                jsonobject1.addProperty("value", ChatModifier.h(chatmodifier).b());
                jsonobject.add("clickEvent", jsonobject1);
            }

            if (ChatModifier.i(chatmodifier) != null) {
                jsonobject1 = new JsonObject();
                jsonobject1.addProperty("action", ChatModifier.i(chatmodifier).a().b());
                jsonobject1.add("value", jsonserializationcontext.serialize(ChatModifier.i(chatmodifier).b()));
                jsonobject.add("hoverEvent", jsonobject1);
            }

            return jsonobject;
        }
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((ChatModifier) object, type, jsonserializationcontext);
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }
}
