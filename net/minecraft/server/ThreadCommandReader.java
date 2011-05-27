package net.minecraft.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadCommandReader extends Thread {

    final MinecraftServer a;

    public ThreadCommandReader(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
    }

    public void run() {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;

        try {
            while (!this.a.g && MinecraftServer.a(this.a) && (s = bufferedreader.readLine()) != null) {
                this.a.a(s, (ICommandListener) this.a);
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }
}
