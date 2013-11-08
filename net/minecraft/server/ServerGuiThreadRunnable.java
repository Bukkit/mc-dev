package net.minecraft.server;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.minecraft.util.com.mojang.util.QueueLogAppender;

class ServerGuiThreadRunnable implements Runnable {

    final JTextArea a;
    final JScrollPane b;
    final ServerGUI c;

    ServerGuiThreadRunnable(ServerGUI servergui, JTextArea jtextarea, JScrollPane jscrollpane) {
        this.c = servergui;
        this.a = jtextarea;
        this.b = jscrollpane;
    }

    public void run() {
        String s;

        while ((s = QueueLogAppender.getNextLogEvent("ServerGuiConsole")) != null) {
            this.c.a(this.a, this.b, s);
        }
    }
}
