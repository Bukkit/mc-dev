package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatSerializer implements JsonDeserializer, JsonSerializer {

    public ChatSerializer() {}

    public ChatMessage a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        ChatMessage chatmessage = new ChatMessage();
        JsonObject jsonobject = (JsonObject) jsonelement;
        JsonElement jsonelement1 = jsonobject.get("text");
        JsonElement jsonelement2 = jsonobject.get("translate");
        JsonElement jsonelement3 = jsonobject.get("color");
        JsonElement jsonelement4 = jsonobject.get("bold");
        JsonElement jsonelement5 = jsonobject.get("italic");
        JsonElement jsonelement6 = jsonobject.get("underlined");
        JsonElement jsonelement7 = jsonobject.get("obfuscated");

        if (jsonelement3 != null && jsonelement3.isJsonPrimitive()) {
            EnumChatFormat enumchatformat = EnumChatFormat.b(jsonelement3.getAsString());

            if (enumchatformat == null || !enumchatformat.c()) {
                throw new JsonParseException("Given color (" + jsonelement3.getAsString() + ") is not a valid selection");
            }

            chatmessage.a(enumchatformat);
        }

        if (jsonelement4 != null && jsonelement4.isJsonPrimitive()) {
            chatmessage.a(Boolean.valueOf(jsonelement4.getAsBoolean()));
        }

        if (jsonelement5 != null && jsonelement5.isJsonPrimitive()) {
            chatmessage.b(Boolean.valueOf(jsonelement5.getAsBoolean()));
        }

        if (jsonelement6 != null && jsonelement6.isJsonPrimitive()) {
            chatmessage.c(Boolean.valueOf(jsonelement6.getAsBoolean()));
        }

        if (jsonelement7 != null && jsonelement7.isJsonPrimitive()) {
            chatmessage.d(Boolean.valueOf(jsonelement7.getAsBoolean()));
        }

        if (jsonelement1 != null) {
            if (jsonelement1.isJsonArray()) {
                JsonArray jsonarray = jsonelement1.getAsJsonArray();
                Iterator iterator = jsonarray.iterator();

                while (iterator.hasNext()) {
                    JsonElement jsonelement8 = (JsonElement) iterator.next();

                    if (jsonelement8.isJsonPrimitive()) {
                        chatmessage.a(jsonelement8.getAsString());
                    } else if (jsonelement8.isJsonObject()) {
                        chatmessage.a(this.a(jsonelement8, type, jsondeserializationcontext));
                    }
                }
            } else if (jsonelement1.isJsonPrimitive()) {
                chatmessage.a(jsonelement1.getAsString());
            }
        } else if (jsonelement2 != null && jsonelement2.isJsonPrimitive()) {
            JsonElement jsonelement9 = jsonobject.get("using");

            if (jsonelement9 != null) {
                if (jsonelement9.isJsonArray()) {
                    ArrayList arraylist = Lists.newArrayList();
                    Iterator iterator1 = jsonelement9.getAsJsonArray().iterator();

                    while (iterator1.hasNext()) {
                        JsonElement jsonelement10 = (JsonElement) iterator1.next();

                        if (jsonelement10.isJsonPrimitive()) {
                            arraylist.add(jsonelement10.getAsString());
                        } else if (jsonelement10.isJsonObject()) {
                            arraylist.add(this.a(jsonelement10, type, jsondeserializationcontext));
                        }
                    }

                    chatmessage.a(jsonelement2.getAsString(), arraylist.toArray());
                } else if (jsonelement9.isJsonPrimitive()) {
                    chatmessage.a(jsonelement2.getAsString(), new Object[] { jsonelement9.getAsString()});
                }
            } else {
                chatmessage.b(jsonelement2.getAsString());
            }
        }

        return chatmessage;
    }

    public JsonElement a(ChatMessage chatmessage, Type type, JsonSerializationContext jsonserializationcontext) {
        JsonObject jsonobject = new JsonObject();

        if (chatmessage.a() != null) {
            jsonobject.addProperty("color", chatmessage.a().d());
        }

        if (chatmessage.b() != null) {
            jsonobject.addProperty("bold", chatmessage.b());
        }

        if (chatmessage.c() != null) {
            jsonobject.addProperty("italic", chatmessage.c());
        }

        if (chatmessage.d() != null) {
            jsonobject.addProperty("underlined", chatmessage.d());
        }

        if (chatmessage.e() != null) {
            jsonobject.addProperty("obfuscated", chatmessage.e());
        }

        if (chatmessage.f() != null) {
            jsonobject.addProperty("text", chatmessage.f());
        } else if (chatmessage.g() != null) {
            jsonobject.addProperty("translate", chatmessage.g());
            if (chatmessage.h() != null && !chatmessage.h().isEmpty()) {
                jsonobject.add("using", this.b(chatmessage, type, jsonserializationcontext));
            }
        } else if (chatmessage.h() != null && !chatmessage.h().isEmpty()) {
            jsonobject.add("text", this.b(chatmessage, type, jsonserializationcontext));
        }

        return jsonobject;
    }

    private JsonArray b(ChatMessage chatmessage, Type type, JsonSerializationContext jsonserializationcontext) {
        JsonArray jsonarray = new JsonArray();
        Iterator iterator = chatmessage.h().iterator();

        while (iterator.hasNext()) {
            ChatMessage chatmessage1 = (ChatMessage) iterator.next();

            if (chatmessage1.f() != null) {
                jsonarray.add(new JsonPrimitive(chatmessage1.f()));
            } else {
                jsonarray.add(this.a(chatmessage1, type, jsonserializationcontext));
            }
        }

        return jsonarray;
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((ChatMessage) object, type, jsonserializationcontext);
    }
}
