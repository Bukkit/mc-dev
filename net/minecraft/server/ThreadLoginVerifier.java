package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;

class ThreadLoginVerifier extends Thread {

    final PendingConnection pendingConnection;

    ThreadLoginVerifier(PendingConnection pendingconnection) {
        this.pendingConnection = pendingconnection;
    }

    public void run() {
        try {
            String s = (new BigInteger(MinecraftEncryption.a(PendingConnection.a(this.pendingConnection), PendingConnection.b(this.pendingConnection).H().getPublic(), PendingConnection.c(this.pendingConnection)))).toString(16);
            URL url = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(PendingConnection.d(this.pendingConnection), "UTF-8") + "&serverId=" + URLEncoder.encode(s, "UTF-8"));
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openConnection(PendingConnection.b(this.pendingConnection).ap()).getInputStream()));
            String s1 = bufferedreader.readLine();

            bufferedreader.close();
            if (!"YES".equals(s1)) {
                this.pendingConnection.disconnect("Failed to verify username!");
                return;
            }

            PendingConnection.a(this.pendingConnection, true);
        } catch (Exception exception) {
            this.pendingConnection.disconnect("Failed to verify username! [internal error " + exception + "]");
            exception.printStackTrace();
        }
    }
}
