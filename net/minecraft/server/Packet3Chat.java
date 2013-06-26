package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet3Chat extends Packet {

    public String message;
    private boolean b;

    public Packet3Chat() {
        this.b = true;
    }

    public Packet3Chat(ChatMessage chatmessage) {
        this(chatmessage.i());
    }

    public Packet3Chat(ChatMessage chatmessage, boolean flag) {
        this(chatmessage.i(), flag);
    }

    public Packet3Chat(String s) {
        this(s, true);
    }

    public Packet3Chat(String s, boolean flag) {
        this.b = true;
        if (s.length() > 32767) {
            s = s.substring(0, 32767);
        }

        this.message = s;
        this.b = flag;
    }

    public void a(DataInput datainput) {
        this.message = a(datainput, 32767);
    }

    public void a(DataOutput dataoutput) {
        a(this.message, dataoutput);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.message.length() * 2;
    }

    public boolean isServer() {
        return this.b;
    }
}
