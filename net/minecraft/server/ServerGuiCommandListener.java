package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

class ServerGuiCommandListener implements ActionListener {

    final JTextField a;

    final ServerGUI b;

    ServerGuiCommandListener(ServerGUI servergui, JTextField jtextfield) {
        this.b = servergui;
        this.a = jtextfield;
    }

    public void actionPerformed(ActionEvent actionevent) {
        String s = this.a.getText().trim();

        if (s.length() > 0) {
            ServerGUI.a(this.b).a(s, (ICommandListener) this.b);
        }

        this.a.setText("");
    }
}
