package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GuiStatsListener implements ActionListener {

    final GuiStatsComponent a;

    GuiStatsListener(GuiStatsComponent guistatscomponent) {
        this.a = guistatscomponent;
    }

    public void actionPerformed(ActionEvent actionevent) {
        GuiStatsComponent.a(this.a);
    }
}
