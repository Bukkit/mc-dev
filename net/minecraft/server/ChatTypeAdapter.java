package net.minecraft.server;

import java.util.Map;

import net.minecraft.util.com.google.gson.TypeAdapter;
import net.minecraft.util.com.google.gson.stream.JsonReader;
import net.minecraft.util.com.google.gson.stream.JsonToken;
import net.minecraft.util.com.google.gson.stream.JsonWriter;

class ChatTypeAdapter extends TypeAdapter {

    final Map a;
    final ChatTypeAdapterFactory b;

    ChatTypeAdapter(ChatTypeAdapterFactory chattypeadapterfactory, Map map) {
        this.b = chattypeadapterfactory;
        this.a = map;
    }

    public void write(JsonWriter jsonwriter, Object object) {
        if (object == null) {
            jsonwriter.nullValue();
        } else {
            jsonwriter.value(ChatTypeAdapterFactory.a(this.b, object));
        }
    }

    public Object read(JsonReader jsonreader) {
        if (jsonreader.peek() == JsonToken.NULL) {
            jsonreader.nextNull();
            return null;
        } else {
            return this.a.get(jsonreader.nextString());
        }
    }
}
