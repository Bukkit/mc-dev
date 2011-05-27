package net.minecraft.server;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class ServerGuiFocusadapter extends FocusAdapter {

    final ServerGUI a;

    ServerGuiFocusadapter(ServerGUI servergui) {
        this.a = servergui;
    }

    public void focusGained(FocusEvent focusevent) {}
}
