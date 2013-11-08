package net.minecraft.server;

import java.util.Iterator;
import java.util.Set;

import net.minecraft.util.com.google.common.collect.ForwardingSet;
import net.minecraft.util.com.google.common.collect.Sets;
import net.minecraft.util.com.google.gson.JsonArray;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonPrimitive;

public class AchievementSet extends ForwardingSet implements IJsonStatistic {

    private final Set a = Sets.newHashSet();

    public AchievementSet() {}

    public JsonElement a() {
        JsonArray jsonarray = new JsonArray();
        Iterator iterator = this.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            jsonarray.add(new JsonPrimitive(s));
        }

        return jsonarray;
    }

    protected Set delegate() {
        return this.a;
    }
}
