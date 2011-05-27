package net.minecraft.server;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class ServerGuiFocusAdapter extends FocusAdapter {

    final ServerGUI a;

    ServerGuiFocusAdapter(ServerGUI servergui) {
        this.a = servergui;
    }

    public void focusGained(FocusEvent focusevent) {}
}
