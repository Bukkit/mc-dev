package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericAttributes {

    private static final Logger f = LogManager.getLogger();
    public static final IAttribute a = (new AttributeRanged("generic.maxHealth", 20.0D, 0.0D, Double.MAX_VALUE)).a("Max Health").a(true);
    public static final IAttribute b = (new AttributeRanged("generic.followRange", 32.0D, 0.0D, 2048.0D)).a("Follow Range");
    public static final IAttribute c = (new AttributeRanged("generic.knockbackResistance", 0.0D, 0.0D, 1.0D)).a("Knockback Resistance");
    public static final IAttribute d = (new AttributeRanged("generic.movementSpeed", 0.699999988079071D, 0.0D, Double.MAX_VALUE)).a("Movement Speed").a(true);
    public static final IAttribute e = new AttributeRanged("generic.attackDamage", 2.0D, 0.0D, Double.MAX_VALUE);

    public static NBTTagList a(AttributeMapBase attributemapbase) {
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = attributemapbase.a().iterator();

        while (iterator.hasNext()) {
            AttributeInstance attributeinstance = (AttributeInstance) iterator.next();

            nbttaglist.add(a(attributeinstance));
        }

        return nbttaglist;
    }

    private static NBTTagCompound a(AttributeInstance attributeinstance) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        IAttribute iattribute = attributeinstance.a();

        nbttagcompound.setString("Name", iattribute.a());
        nbttagcompound.setDouble("Base", attributeinstance.b());
        Collection collection = attributeinstance.c();

        if (collection != null && !collection.isEmpty()) {
            NBTTagList nbttaglist = new NBTTagList();
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                AttributeModifier attributemodifier = (AttributeModifier) iterator.next();

                if (attributemodifier.e()) {
                    nbttaglist.add(a(attributemodifier));
                }
            }

            nbttagcompound.set("Modifiers", nbttaglist);
        }

        return nbttagcompound;
    }

    private static NBTTagCompound a(AttributeModifier attributemodifier) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setString("Name", attributemodifier.b());
        nbttagcompound.setDouble("Amount", attributemodifier.d());
        nbttagcompound.setInt("Operation", attributemodifier.c());
        nbttagcompound.setLong("UUIDMost", attributemodifier.a().getMostSignificantBits());
        nbttagcompound.setLong("UUIDLeast", attributemodifier.a().getLeastSignificantBits());
        return nbttagcompound;
    }

    public static void a(AttributeMapBase attributemapbase, NBTTagList nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.get(i);
            AttributeInstance attributeinstance = attributemapbase.a(nbttagcompound.getString("Name"));

            if (attributeinstance != null) {
                a(attributeinstance, nbttagcompound);
            } else {
                f.warn("Ignoring unknown attribute \'" + nbttagcompound.getString("Name") + "\'");
            }
        }
    }

    private static void a(AttributeInstance attributeinstance, NBTTagCompound nbttagcompound) {
        attributeinstance.setValue(nbttagcompound.getDouble("Base"));
        if (nbttagcompound.hasKeyOfType("Modifiers", 9)) {
            NBTTagList nbttaglist = nbttagcompound.getList("Modifiers", 10);

            for (int i = 0; i < nbttaglist.size(); ++i) {
                AttributeModifier attributemodifier = a(nbttaglist.get(i));
                AttributeModifier attributemodifier1 = attributeinstance.a(attributemodifier.a());

                if (attributemodifier1 != null) {
                    attributeinstance.b(attributemodifier1);
                }

                attributeinstance.a(attributemodifier);
            }
        }
    }

    public static AttributeModifier a(NBTTagCompound nbttagcompound) {
        UUID uuid = new UUID(nbttagcompound.getLong("UUIDMost"), nbttagcompound.getLong("UUIDLeast"));

        return new AttributeModifier(uuid, nbttagcompound.getString("Name"), nbttagcompound.getDouble("Amount"), nbttagcompound.getInt("Operation"));
    }
}
