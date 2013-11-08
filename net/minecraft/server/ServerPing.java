package net.minecraft.server;

public class ServerPing {

    private IChatBaseComponent a;
    private ServerPingPlayerSample b;
    private ServerPingServerData c;
    private String d;

    public ServerPing() {}

    public IChatBaseComponent a() {
        return this.a;
    }

    public void setMOTD(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public ServerPingPlayerSample b() {
        return this.b;
    }

    public void setPlayerSample(ServerPingPlayerSample serverpingplayersample) {
        this.b = serverpingplayersample;
    }

    public ServerPingServerData c() {
        return this.c;
    }

    public void setServerInfo(ServerPingServerData serverpingserverdata) {
        this.c = serverpingserverdata;
    }

    public void setFavicon(String s) {
        this.d = s;
    }

    public String d() {
        return this.d;
    }
}
