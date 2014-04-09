package net.minecraft.server;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.ProfileLookupCallback;
import net.minecraft.util.com.mojang.authlib.yggdrasil.ProfileNotFoundException;

final class OpListProfileCallback implements ProfileLookupCallback {

    final MinecraftServer a;
    final OpList b;

    OpListProfileCallback(MinecraftServer minecraftserver, OpList oplist) {
        this.a = minecraftserver;
        this.b = oplist;
    }

    public void onProfileLookupSucceeded(GameProfile gameprofile) {
        this.a.getUserCache().a(gameprofile);
        this.b.add(new OpListEntry(gameprofile, this.a.l()));
    }

    public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
        NameReferencingFileConverter.a().warn("Could not lookup oplist entry for " + gameprofile.getName(), exception);
        if (!(exception instanceof ProfileNotFoundException)) {
            throw new FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, (PredicateEmptyList) null);
        }
    }
}
