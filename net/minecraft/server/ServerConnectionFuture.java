package net.minecraft.server;

import net.minecraft.util.io.netty.util.concurrent.Future;
import net.minecraft.util.io.netty.util.concurrent.GenericFutureListener;

class ServerConnectionFuture implements GenericFutureListener {

    final NetworkManager a;
    final ChatComponentText b;
    final ServerConnection c;

    ServerConnectionFuture(ServerConnection serverconnection, NetworkManager networkmanager, ChatComponentText chatcomponenttext) {
        this.c = serverconnection;
        this.a = networkmanager;
        this.b = chatcomponenttext;
    }

    public void operationComplete(Future future) {
        this.a.close(this.b);
    }
}
