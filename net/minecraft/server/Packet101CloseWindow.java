package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet101CloseWindow extends Packet {

    public int a;

    public Packet101CloseWindow() {}

    public Packet101CloseWindow(int i) {
        this.a = i;
    }

    public void handle(Connection connection) {
        connection.handleContainerClose(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
    }

    public int a() {
        return 1;
    }
}
