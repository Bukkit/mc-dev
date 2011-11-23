package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class RemoteControlListener extends RemoteConnectionThread {

    private int g;
    private int h;
    private String i;
    private ServerSocket j = null;
    private String k;
    private HashMap l;

    public RemoteControlListener(IMinecraftServer iminecraftserver) {
        super(iminecraftserver);
        this.g = iminecraftserver.getProperty("rcon.port", 0);
        this.k = iminecraftserver.a("rcon.password", "");
        this.i = iminecraftserver.getMotd();
        this.h = iminecraftserver.getPort();
        if (0 == this.g) {
            this.g = this.h + 10;
            this.info("Setting default rcon port to " + this.g);
            iminecraftserver.a("rcon.port", Integer.valueOf(this.g));
            if (0 == this.k.length()) {
                iminecraftserver.a("rcon.password", "");
            }

            iminecraftserver.c();
        }

        if (0 == this.i.length()) {
            this.i = "0.0.0.0";
        }

        this.e();
        this.j = null;
    }

    private void e() {
        this.l = new HashMap();
    }

    private void f() {
        Iterator iterator = this.l.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            if (!((RemoteControlSession) entry.getValue()).b()) {
                iterator.remove();
            }
        }
    }

    public void run() {
        this.info("RCON running on " + this.i + ":" + this.g);

        try {
            while (this.running) {
                try {
                    Socket socket = this.j.accept();

                    socket.setSoTimeout(500);
                    RemoteControlSession remotecontrolsession = new RemoteControlSession(this.server, socket);

                    remotecontrolsession.a();
                    this.l.put(socket.getRemoteSocketAddress(), remotecontrolsession);
                    this.f();
                } catch (SocketTimeoutException sockettimeoutexception) {
                    this.f();
                } catch (IOException ioexception) {
                    if (this.running) {
                        this.info("IO: " + ioexception.getMessage());
                    }
                }
            }
        } finally {
            this.a(this.j);
        }
    }

    public void a() {
        if (0 == this.k.length()) {
            this.warning("No rcon password set in \'" + this.server.getPropertiesFile() + "\', rcon disabled!");
        } else if (0 < this.g && '\uffff' >= this.g) {
            if (!this.running) {
                try {
                    this.j = new ServerSocket(this.g, 0, InetAddress.getByName(this.i));
                    this.j.setSoTimeout(500);
                    super.a();
                } catch (IOException ioexception) {
                    this.warning("Unable to initialise rcon on " + this.i + ":" + this.g + " : " + ioexception.getMessage());
                }
            }
        } else {
            this.warning("Invalid rcon port " + this.g + " found in \'" + this.server.getPropertiesFile() + "\', rcon disabled!");
        }
    }
}
