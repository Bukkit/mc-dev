package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportServerConnection implements Callable {

    final NetworkManager a;
    final ServerConnection b;

    CrashReportServerConnection(ServerConnection serverconnection, NetworkManager networkmanager) {
        this.b = serverconnection;
        this.a = networkmanager;
    }

    public String a() {
        return this.a.toString();
    }

    public Object call() {
        return this.a();
    }
}
