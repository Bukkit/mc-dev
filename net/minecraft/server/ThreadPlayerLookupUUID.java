package net.minecraft.server;

import java.math.BigInteger;
import java.util.UUID;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.exceptions.AuthenticationUnavailableException;

class ThreadPlayerLookupUUID extends Thread {

    final LoginListener a;

    ThreadPlayerLookupUUID(LoginListener loginlistener, String s) {
        super(s);
        this.a = loginlistener;
    }

    public void run() {
        GameProfile gameprofile = LoginListener.a(this.a);

        try {
            String s = (new BigInteger(MinecraftEncryption.a(LoginListener.b(this.a), LoginListener.c(this.a).K().getPublic(), LoginListener.d(this.a)))).toString(16);

            LoginListener.a(this.a, LoginListener.c(this.a).av().hasJoinedServer(new GameProfile((UUID) null, gameprofile.getName()), s));
            if (LoginListener.a(this.a) != null) {
                LoginListener.e().info("UUID of player " + LoginListener.a(this.a).getName() + " is " + LoginListener.a(this.a).getId());
                LoginListener.a(this.a, EnumProtocolState.READY_TO_ACCEPT);
            } else if (LoginListener.c(this.a).N()) {
                LoginListener.e().warn("Failed to verify username but will let them in anyway!");
                LoginListener.a(this.a, this.a.a(gameprofile));
                LoginListener.a(this.a, EnumProtocolState.READY_TO_ACCEPT);
            } else {
                this.a.disconnect("Failed to verify username!");
                LoginListener.e().error("Username \'" + LoginListener.a(this.a).getName() + "\' tried to join with an invalid session");
            }
        } catch (AuthenticationUnavailableException authenticationunavailableexception) {
            if (LoginListener.c(this.a).N()) {
                LoginListener.e().warn("Authentication servers are down but will let them in anyway!");
                LoginListener.a(this.a, this.a.a(gameprofile));
                LoginListener.a(this.a, EnumProtocolState.READY_TO_ACCEPT);
            } else {
                this.a.disconnect("Authentication servers are down. Please try again later, sorry!");
                LoginListener.e().error("Couldn\'t verify username because servers are unavailable");
            }
        }
    }
}
