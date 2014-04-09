package net.minecraft.server;

import java.io.File;
import java.net.SocketAddress;

import net.minecraft.util.com.google.gson.JsonObject;

public class IpBanList extends JsonList {

    public IpBanList(File file1) {
        super(file1);
    }

    protected JsonListEntry a(JsonObject jsonobject) {
        return new IpBanEntry(jsonobject);
    }

    public boolean isBanned(SocketAddress socketaddress) {
        String s = this.c(socketaddress);

        return this.d(s);
    }

    public IpBanEntry get(SocketAddress socketaddress) {
        String s = this.c(socketaddress);

        return (IpBanEntry) this.get(s);
    }

    private String c(SocketAddress socketaddress) {
        String s = socketaddress.toString();

        if (s.contains("/")) {
            s = s.substring(s.indexOf(47) + 1);
        }

        if (s.contains(":")) {
            s = s.substring(0, s.indexOf(58));
        }

        return s;
    }
}
