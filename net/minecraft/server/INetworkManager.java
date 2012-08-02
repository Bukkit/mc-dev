package net.minecraft.server;

import java.net.SocketAddress;

public interface INetworkManager {

    void a(NetHandler nethandler);

    void queue(Packet packet);

    void a();

    void b();

    SocketAddress getSocketAddress();

    void d();

    int e();

    void a(String s, Object... aobject);
}
