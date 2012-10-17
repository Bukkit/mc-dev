package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class GameRules {

    private TreeMap a = new TreeMap();

    public GameRules() {
        this.a("doFireTick", "true");
        this.a("mobGriefing", "true");
        this.a("keepInventory", "false");
        this.a("doMobSpawning", "true");
        this.a("doMobLoot", "true");
        this.a("doTileDrops", "true");
        this.a("commandBlockOutput", "true");
    }

    public void a(String s, String s1) {
        this.a.put(s, new GameRuleValue(s1));
    }

    public void set(String s, String s1) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        if (gamerulevalue != null) {
            gamerulevalue.a(s1);
        } else {
            this.a(s, s1);
        }
    }

    public String get(String s) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        return gamerulevalue != null ? gamerulevalue.a() : "";
    }

    public boolean getBoolean(String s) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        return gamerulevalue != null ? gamerulevalue.b() : false;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound("GameRules");
        Iterator iterator = this.a.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

            nbttagcompound.setString(s, gamerulevalue.a());
        }

        return nbttagcompound;
    }

    public void a(NBTTagCompound nbttagcompound) {
        Collection collection = nbttagcompound.c();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            NBTBase nbtbase = (NBTBase) iterator.next();
            String s = nbtbase.getName();
            String s1 = nbttagcompound.getString(nbtbase.getName());

            this.set(s, s1);
        }
    }

    public String[] b() {
        return (String[]) this.a.keySet().toArray(new String[0]);
    }

    public boolean e(String s) {
        return this.a.containsKey(s);
    }
}
