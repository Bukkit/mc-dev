package net.minecraft.server;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.ProfileLookupCallback;

final class GameProfileLookup implements ProfileLookupCallback {

    final GameProfile[] a;

    GameProfileLookup(GameProfile[] agameprofile) {
        this.a = agameprofile;
    }

    public void onProfileLookupSucceeded(GameProfile gameprofile) {
        this.a[0] = gameprofile;
    }

    public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
        this.a[0] = null;
    }
}
