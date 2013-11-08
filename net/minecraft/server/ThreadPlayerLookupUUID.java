package net.minecraft.server;

import java.math.BigInteger;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.exceptions.AuthenticationUnavailableException;

class ThreadPlayerLookupUUID extends Thread {

    final LoginListener a;

    ThreadPlayerLookupUUID(LoginListener loginlistener, String s) {
        super(s);
        this.a = loginlistener;
    }

    public void run() {
        try {
            String s = (new BigInteger(MinecraftEncryption.a(LoginListener.a(this.a), LoginListener.b(this.a).I().getPublic(), LoginListener.c(this.a)))).toString(16);

            LoginListener.a(this.a, LoginListener.b(this.a).as().hasJoinedServer(new GameProfile((String) null, LoginListener.d(this.a).getName()), s));
            if (LoginListener.d(this.a) != null) {
                LoginListener.e().info("UUID of player " + LoginListener.d(this.a).getName() + " is " + LoginListener.d(this.a).getId());
                LoginListener.a(this.a, EnumProtocolState.READY_TO_ACCEPT);
            } else {
                this.a.disconnect("Failed to verify username!");
                LoginListener.e().error("Username \'" + LoginListener.d(this.a).getName() + "\' tried to join with an invalid session");
            }
        } catch (AuthenticationUnavailableException authenticationunavailableexception) {
            this.a.disconnect("Authentication servers are down. Please try again later, sorry!");
            LoginListener.e().error("Couldn\'t verify username because servers are unavailable");
        }
    }
}
