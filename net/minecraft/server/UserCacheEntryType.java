package net.minecraft.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

final class UserCacheEntryType implements ParameterizedType {

    UserCacheEntryType() {}

    public Type[] getActualTypeArguments() {
        return new Type[] { UserCacheEntry.class};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }
}
