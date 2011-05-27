package net.minecraft.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadCommandReader extends Thread {

    final MinecraftServer server;

    public ThreadCommandReader(MinecraftServer minecraftserver) {
        this.server = minecraftserver;
    }

    public void run() {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;

        try {
            while (!this.server.isStopped && MinecraftServer.isRunning(this.server) && (s = bufferedreader.readLine()) != null) {
                this.server.issueCommand(s, this.server);
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }
}
