package net.minecraft.server;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.ProfileLookupCallback;
import net.minecraft.util.com.mojang.authlib.yggdrasil.ProfileNotFoundException;

final class GameProfileBanListEntryConverter implements ProfileLookupCallback {

    final MinecraftServer a;
    final Map b;
    final GameProfileBanList c;

    GameProfileBanListEntryConverter(MinecraftServer minecraftserver, Map map, GameProfileBanList gameprofilebanlist) {
        this.a = minecraftserver;
        this.b = map;
        this.c = gameprofilebanlist;
    }

    public void onProfileLookupSucceeded(GameProfile gameprofile) {
        this.a.getUserCache().a(gameprofile);
        String[] astring = (String[]) this.b.get(gameprofile.getName().toLowerCase(Locale.ROOT));

        if (astring == null) {
            NameReferencingFileConverter.a().warn("Could not convert user banlist entry for " + gameprofile.getName());
            throw new FileConversionException("Profile not in the conversionlist", (PredicateEmptyList) null);
        } else {
            Date date = astring.length > 1 ? NameReferencingFileConverter.a(astring[1], (Date) null) : null;
            String s = astring.length > 2 ? astring[2] : null;
            Date date1 = astring.length > 3 ? NameReferencingFileConverter.a(astring[3], (Date) null) : null;
            String s1 = astring.length > 4 ? astring[4] : null;

            this.c.add(new GameProfileBanEntry(gameprofile, date, s, date1, s1));
        }
    }

    public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
        NameReferencingFileConverter.a().warn("Could not lookup user banlist entry for " + gameprofile.getName(), exception);
        if (!(exception instanceof ProfileNotFoundException)) {
            throw new FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, (PredicateEmptyList) null);
        }
    }
}
