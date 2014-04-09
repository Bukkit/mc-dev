package net.minecraft.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

final class JsonListType implements ParameterizedType {

    JsonListType() {}

    public Type[] getActualTypeArguments() {
        return new Type[] { JsonListEntry.class};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }
}
