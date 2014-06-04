package net.minecraft.server;

import java.util.Iterator;
import java.util.UUID;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.properties.Property;

public final class GameProfileSerializer {

    public static GameProfile deserialize(NBTTagCompound nbttagcompound) {
        String s = null;
        String s1 = null;

        if (nbttagcompound.hasKeyOfType("Name", 8)) {
            s = nbttagcompound.getString("Name");
        }

        if (nbttagcompound.hasKeyOfType("Id", 8)) {
            s1 = nbttagcompound.getString("Id");
        }

        if (UtilColor.b(s) && UtilColor.b(s1)) {
            return null;
        } else {
            UUID uuid;

            try {
                uuid = UUID.fromString(s1);
            } catch (Throwable throwable) {
                uuid = null;
            }

            GameProfile gameprofile = new GameProfile(uuid, s);

            if (nbttagcompound.hasKeyOfType("Properties", 10)) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Properties");
                Iterator iterator = nbttagcompound1.c().iterator();

                while (iterator.hasNext()) {
                    String s2 = (String) iterator.next();
                    NBTTagList nbttaglist = nbttagcompound1.getList(s2, 10);

                    for (int i = 0; i < nbttaglist.size(); ++i) {
                        NBTTagCompound nbttagcompound2 = nbttaglist.get(i);
                        String s3 = nbttagcompound2.getString("Value");

                        if (nbttagcompound2.hasKeyOfType("Signature", 8)) {
                            gameprofile.getProperties().put(s2, new Property(s2, s3, nbttagcompound2.getString("Signature")));
                        } else {
                            gameprofile.getProperties().put(s2, new Property(s2, s3));
                        }
                    }
                }
            }

            return gameprofile;
        }
    }

    public static void serialize(NBTTagCompound nbttagcompound, GameProfile gameprofile) {
        if (!UtilColor.b(gameprofile.getName())) {
            nbttagcompound.setString("Name", gameprofile.getName());
        }

        if (gameprofile.getId() != null) {
            nbttagcompound.setString("Id", gameprofile.getId().toString());
        }

        if (!gameprofile.getProperties().isEmpty()) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            Iterator iterator = gameprofile.getProperties().keySet().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                NBTTagList nbttaglist = new NBTTagList();

                NBTTagCompound nbttagcompound2;

                for (Iterator iterator1 = gameprofile.getProperties().get(s).iterator(); iterator1.hasNext(); nbttaglist.add(nbttagcompound2)) {
                    Property property = (Property) iterator1.next();

                    nbttagcompound2 = new NBTTagCompound();
                    nbttagcompound2.setString("Value", property.getValue());
                    if (property.hasSignature()) {
                        nbttagcompound2.setString("Signature", property.getSignature());
                    }
                }

                nbttagcompound1.set(s, nbttaglist);
            }

            nbttagcompound.set("Properties", nbttagcompound1);
        }
    }
}
