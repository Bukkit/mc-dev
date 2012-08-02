package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;

class ThreadLoginVerifier extends Thread {

    final NetLoginHandler netLoginHandler;

    ThreadLoginVerifier(NetLoginHandler netloginhandler) {
        this.netLoginHandler = netloginhandler;
    }

    public void run() {
        try {
            String s = (new BigInteger(MinecraftEncryption.a(NetLoginHandler.a(this.netLoginHandler), NetLoginHandler.b(this.netLoginHandler).E().getPublic(), NetLoginHandler.c(this.netLoginHandler)))).toString(16);
            URL url = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(NetLoginHandler.d(this.netLoginHandler), "UTF-8") + "&serverId=" + URLEncoder.encode(s, "UTF-8"));
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s1 = bufferedreader.readLine();

            bufferedreader.close();
            if (!"YES".equals(s1)) {
                this.netLoginHandler.disconnect("Failed to verify username!");
                return;
            }

            NetLoginHandler.a(this.netLoginHandler, true);
        } catch (Exception exception) {
            this.netLoginHandler.disconnect("Failed to verify username! [internal error " + exception + "]");
            exception.printStackTrace();
        }
    }
}
