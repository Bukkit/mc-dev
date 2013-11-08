package net.minecraft.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ThreadCommandReader extends Thread {

    final DedicatedServer server;

    ThreadCommandReader(DedicatedServer dedicatedserver, String s) {
        super(s);
        this.server = dedicatedserver;
    }

    public void run() {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));

        String s;

        try {
            while (!this.server.isStopped() && this.server.isRunning() && (s = bufferedreader.readLine()) != null) {
                this.server.issueCommand(s, this.server);
            }
        } catch (IOException ioexception) {
            DedicatedServer.az().error("Exception handling console input", ioexception);
        }
    }
}
