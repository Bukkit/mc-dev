package net.minecraft.server;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.GsonBuilder;
import net.minecraft.util.com.google.gson.JsonArray;
import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.google.gson.JsonParseException;
import net.minecraft.util.com.google.gson.JsonPrimitive;
import net.minecraft.util.com.google.gson.JsonSerializationContext;
import net.minecraft.util.com.google.gson.JsonSerializer;

public class ChatSerializer implements JsonDeserializer, JsonSerializer {

    private static final Gson a;

    public ChatSerializer() {}

    public IChatBaseComponent a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        if (jsonelement.isJsonPrimitive()) {
            return new ChatComponentText(jsonelement.getAsString());
        } else if (!jsonelement.isJsonObject()) {
            if (jsonelement.isJsonArray()) {
                JsonArray jsonarray = jsonelement.getAsJsonArray();
                IChatBaseComponent ichatbasecomponent = null;
                Iterator iterator = jsonarray.iterator();

                while (iterator.hasNext()) {
                    JsonElement jsonelement1 = (JsonElement) iterator.next();
                    IChatBaseComponent ichatbasecomponent1 = this.a(jsonelement1, (Type) jsonelement1.getClass(), jsondeserializationcontext);

                    if (ichatbasecomponent == null) {
                        ichatbasecomponent = ichatbasecomponent1;
                    } else {
                        ichatbasecomponent.a(ichatbasecomponent1);
                    }
                }

                return ichatbasecomponent;
            } else {
                throw new JsonParseException("Don\'t know how to turn " + jsonelement.toString() + " into a Component");
            }
        } else {
            JsonObject jsonobject = jsonelement.getAsJsonObject();
            Object object;

            if (jsonobject.has("text")) {
                object = new ChatComponentText(jsonobject.get("text").getAsString());
            } else {
                if (!jsonobject.has("translate")) {
                    throw new JsonParseException("Don\'t know how to turn " + jsonelement.toString() + " into a Component");
                }

                String s = jsonobject.get("translate").getAsString();

                if (jsonobject.has("with")) {
                    JsonArray jsonarray1 = jsonobject.getAsJsonArray("with");
                    Object[] aobject = new Object[jsonarray1.size()];

                    for (int i = 0; i < aobject.length; ++i) {
                        aobject[i] = this.a(jsonarray1.get(i), type, jsondeserializationcontext);
                        if (aobject[i] instanceof ChatComponentText) {
                            ChatComponentText chatcomponenttext = (ChatComponentText) aobject[i];

                            if (chatcomponenttext.b().g() && chatcomponenttext.a().isEmpty()) {
                                aobject[i] = chatcomponenttext.g();
                            }
                        }
                    }

                    object = new ChatMessage(s, aobject);
                } else {
                    object = new ChatMessage(s, new Object[0]);
                }
            }

            if (jsonobject.has("extra")) {
                JsonArray jsonarray2 = jsonobject.getAsJsonArray("extra");

                if (jsonarray2.size() <= 0) {
                    throw new JsonParseException("Unexpected empty array of components");
                }

                for (int j = 0; j < jsonarray2.size(); ++j) {
                    ((IChatBaseComponent) object).a(this.a(jsonarray2.get(j), type, jsondeserializationcontext));
                }
            }

            ((IChatBaseComponent) object).setChatModifier((ChatModifier) jsondeserializationcontext.deserialize(jsonelement, ChatModifier.class));
            return (IChatBaseComponent) object;
        }
    }

    private void a(ChatModifier chatmodifier, JsonObject jsonobject, JsonSerializationContext jsonserializationcontext) {
        JsonElement jsonelement = jsonserializationcontext.serialize(chatmodifier);

        if (jsonelement.isJsonObject()) {
            JsonObject jsonobject1 = (JsonObject) jsonelement;
            Iterator iterator = jsonobject1.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();

                jsonobject.add((String) entry.getKey(), (JsonElement) entry.getValue());
            }
        }
    }

    public JsonElement a(IChatBaseComponent ichatbasecomponent, Type type, JsonSerializationContext jsonserializationcontext) {
        if (ichatbasecomponent instanceof ChatComponentText && ichatbasecomponent.b().g() && ichatbasecomponent.a().isEmpty()) {
            return new JsonPrimitive(((ChatComponentText) ichatbasecomponent).g());
        } else {
            JsonObject jsonobject = new JsonObject();

            if (!ichatbasecomponent.b().g()) {
                this.a(ichatbasecomponent.b(), jsonobject, jsonserializationcontext);
            }

            if (!ichatbasecomponent.a().isEmpty()) {
                JsonArray jsonarray = new JsonArray();
                Iterator iterator = ichatbasecomponent.a().iterator();

                while (iterator.hasNext()) {
                    IChatBaseComponent ichatbasecomponent1 = (IChatBaseComponent) iterator.next();

                    jsonarray.add(this.a(ichatbasecomponent1, (Type) ichatbasecomponent1.getClass(), jsonserializationcontext));
                }

                jsonobject.add("extra", jsonarray);
            }

            if (ichatbasecomponent instanceof ChatComponentText) {
                jsonobject.addProperty("text", ((ChatComponentText) ichatbasecomponent).g());
            } else {
                if (!(ichatbasecomponent instanceof ChatMessage)) {
                    throw new IllegalArgumentException("Don\'t know how to serialize " + ichatbasecomponent + " as a Component");
                }

                ChatMessage chatmessage = (ChatMessage) ichatbasecomponent;

                jsonobject.addProperty("translate", chatmessage.i());
                if (chatmessage.j() != null && chatmessage.j().length > 0) {
                    JsonArray jsonarray1 = new JsonArray();
                    Object[] aobject = chatmessage.j();
                    int i = aobject.length;

                    for (int j = 0; j < i; ++j) {
                        Object object = aobject[j];

                        if (object instanceof IChatBaseComponent) {
                            jsonarray1.add(this.a((IChatBaseComponent) object, (Type) object.getClass(), jsonserializationcontext));
                        } else {
                            jsonarray1.add(new JsonPrimitive(String.valueOf(object)));
                        }
                    }

                    jsonobject.add("with", jsonarray1);
                }
            }

            return jsonobject;
        }
    }

    public static String a(IChatBaseComponent ichatbasecomponent) {
        return a.toJson(ichatbasecomponent);
    }

    public static IChatBaseComponent a(String s) {
        return (IChatBaseComponent) a.fromJson(s, IChatBaseComponent.class);
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((IChatBaseComponent) object, type, jsonserializationcontext);
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }

    static {
        GsonBuilder gsonbuilder = new GsonBuilder();

        gsonbuilder.registerTypeHierarchyAdapter(IChatBaseComponent.class, new ChatSerializer());
        gsonbuilder.registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifierSerializer());
        gsonbuilder.registerTypeAdapterFactory(new ChatTypeAdapterFactory());
        a = gsonbuilder.create();
    }
}
