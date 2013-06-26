package net.minecraft.server;

import java.util.Vector;
import javax.swing.JList;

public class PlayerListBox extends JList implements IUpdatePlayerListBox {

    private MinecraftServer a;
    private int b;

    public PlayerListBox(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
        minecraftserver.a((IUpdatePlayerListBox) this);
    }

    public void a() {
        if (this.b++ % 20 == 0) {
            Vector vector = new Vector();

            for (int i = 0; i < this.a.getPlayerList().players.size(); ++i) {
                vector.add(((EntityPlayer) this.a.getPlayerList().players.get(i)).getName());
            }

            this.setListData(vector);
        }
    }
}
