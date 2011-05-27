package net.minecraft.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

final class ServerWindowAdapter extends WindowAdapter {

    final MinecraftServer a;

    ServerWindowAdapter(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
    }

    public void windowClosing(WindowEvent windowevent) {
        this.a.a();

        while (!this.a.isStopped) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException interruptedexception) {
                interruptedexception.printStackTrace();
            }
        }

        System.exit(0);
    }
}
