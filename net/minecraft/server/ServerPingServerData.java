package net.minecraft.server;

public class ServerPingServerData {

    private final String a;
    private final int b;

    public ServerPingServerData(String s, int i) {
        this.a = s;
        this.b = i;
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
