package net.minecraft.server;

import java.util.HashMap;
import java.util.Locale;

import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.TypeAdapter;
import net.minecraft.util.com.google.gson.TypeAdapterFactory;
import net.minecraft.util.com.google.gson.reflect.TypeToken;

public class ChatTypeAdapterFactory implements TypeAdapterFactory {

    public ChatTypeAdapterFactory() {}

    public TypeAdapter create(Gson gson, TypeToken typetoken) {
        Class oclass = typetoken.getRawType();

        if (!oclass.isEnum()) {
            return null;
        } else {
            HashMap hashmap = new HashMap();
            Object[] aobject = oclass.getEnumConstants();
            int i = aobject.length;

            for (int j = 0; j < i; ++j) {
                Object object = aobject[j];

                hashmap.put(this.a(object), object);
            }

            return new ChatTypeAdapter(this, hashmap);
        }
    }

    private String a(Object object) {
        return object instanceof Enum ? ((Enum) object).name().toLowerCase(Locale.US) : object.toString().toLowerCase(Locale.US);
    }

    static String a(ChatTypeAdapterFactory chattypeadapterfactory, Object object) {
        return chattypeadapterfactory.a(object);
    }
}
