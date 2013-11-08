package net.minecraft.server;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ServerGuiInvokeRunnable implements Runnable {

    final JTextArea a;
    final JScrollPane b;
    final String c;
    final ServerGUI d;

    ServerGuiInvokeRunnable(ServerGUI servergui, JTextArea jtextarea, JScrollPane jscrollpane, String s) {
        this.d = servergui;
        this.a = jtextarea;
        this.b = jscrollpane;
        this.c = s;
    }

    public void run() {
        this.d.a(this.a, this.b, this.c);
    }
}
