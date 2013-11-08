package net.minecraft.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RemoteStatusListener extends RemoteConnectionThread {

    private long clearedTime;
    private int bindPort;
    private int serverPort;
    private int maxPlayers;
    private String localAddress;
    private String worldName;
    private DatagramSocket socket;
    private byte[] o = new byte[1460];
    private DatagramPacket p;
    private Map q;
    private String hostname;
    private String motd;
    private Map challenges;
    private long u;
    private RemoteStatusReply cachedReply;
    private long cacheTime;

    public RemoteStatusListener(IMinecraftServer iminecraftserver) {
        super(iminecraftserver, "Query Listener");
        this.bindPort = iminecraftserver.a("query.port", 0);
        this.motd = iminecraftserver.x();
        this.serverPort = iminecraftserver.y();
        this.localAddress = iminecraftserver.z();
        this.maxPlayers = iminecraftserver.C();
        this.worldName = iminecraftserver.M();
        this.cacheTime = 0L;
        this.hostname = "0.0.0.0";
        if (0 != this.motd.length() && !this.hostname.equals(this.motd)) {
            this.hostname = this.motd;
        } else {
            this.motd = "0.0.0.0";

            try {
                InetAddress inetaddress = InetAddress.getLocalHost();

                this.hostname = inetaddress.getHostAddress();
            } catch (UnknownHostException unknownhostexception) {
                this.warning("Unable to determine local host IP, please set server-ip in \'" + iminecraftserver.b() + "\' : " + unknownhostexception.getMessage());
            }
        }

        if (0 == this.bindPort) {
            this.bindPort = this.serverPort;
            this.info("Setting default query port to " + this.bindPort);
            iminecraftserver.a("query.port", Integer.valueOf(this.bindPort));
            iminecraftserver.a("debug", Boolean.valueOf(false));
            iminecraftserver.a();
        }

        this.q = new HashMap();
        this.cachedReply = new RemoteStatusReply(1460);
        this.challenges = new HashMap();
        this.u = (new Date()).getTime();
    }

    private void send(byte[] abyte, DatagramPacket datagrampacket) {
        this.socket.send(new DatagramPacket(abyte, abyte.length, datagrampacket.getSocketAddress()));
    }

    private boolean parsePacket(DatagramPacket datagrampacket) {
        byte[] abyte = datagrampacket.getData();
        int i = datagrampacket.getLength();
        SocketAddress socketaddress = datagrampacket.getSocketAddress();

        this.debug("Packet len " + i + " [" + socketaddress + "]");
        if (3 <= i && -2 == abyte[0] && -3 == abyte[1]) {
            this.debug("Packet \'" + StatusChallengeUtils.a(abyte[2]) + "\' [" + socketaddress + "]");
            switch (abyte[2]) {
            case 0:
                if (!this.hasChallenged(datagrampacket).booleanValue()) {
                    this.debug("Invalid challenge [" + socketaddress + "]");
                    return false;
                } else if (15 == i) {
                    this.send(this.getFullReply(datagrampacket), datagrampacket);
                    this.debug("Rules [" + socketaddress + "]");
                } else {
                    RemoteStatusReply remotestatusreply = new RemoteStatusReply(1460);

                    remotestatusreply.write((int) 0);
                    remotestatusreply.write(this.getIdentityToken(datagrampacket.getSocketAddress()));
                    remotestatusreply.write(this.localAddress);
                    remotestatusreply.write("SMP");
                    remotestatusreply.write(this.worldName);
                    remotestatusreply.write(Integer.toString(this.d()));
                    remotestatusreply.write(Integer.toString(this.maxPlayers));
                    remotestatusreply.write((short) this.serverPort);
                    remotestatusreply.write(this.hostname);
                    this.send(remotestatusreply.getBytes(), datagrampacket);
                    this.debug("Status [" + socketaddress + "]");
                }

            case 9:
                this.createChallenge(datagrampacket);
                this.debug("Challenge [" + socketaddress + "]");
                return true;

            default:
                return true;
            }
        } else {
            this.debug("Invalid packet [" + socketaddress + "]");
            return false;
        }
    }

    private byte[] getFullReply(DatagramPacket datagrampacket) {
        long i = MinecraftServer.ap();

        if (i < this.cacheTime + 5000L) {
            byte[] abyte = this.cachedReply.getBytes();
            byte[] abyte1 = this.getIdentityToken(datagrampacket.getSocketAddress());

            abyte[1] = abyte1[0];
            abyte[2] = abyte1[1];
            abyte[3] = abyte1[2];
            abyte[4] = abyte1[3];
            return abyte;
        } else {
            this.cacheTime = i;
            this.cachedReply.reset();
            this.cachedReply.write((int) 0);
            this.cachedReply.write(this.getIdentityToken(datagrampacket.getSocketAddress()));
            this.cachedReply.write("splitnum");
            this.cachedReply.write((int) 128);
            this.cachedReply.write((int) 0);
            this.cachedReply.write("hostname");
            this.cachedReply.write(this.localAddress);
            this.cachedReply.write("gametype");
            this.cachedReply.write("SMP");
            this.cachedReply.write("game_id");
            this.cachedReply.write("MINECRAFT");
            this.cachedReply.write("version");
            this.cachedReply.write(this.server.getVersion());
            this.cachedReply.write("plugins");
            this.cachedReply.write(this.server.getPlugins());
            this.cachedReply.write("map");
            this.cachedReply.write(this.worldName);
            this.cachedReply.write("numplayers");
            this.cachedReply.write("" + this.d());
            this.cachedReply.write("maxplayers");
            this.cachedReply.write("" + this.maxPlayers);
            this.cachedReply.write("hostport");
            this.cachedReply.write("" + this.serverPort);
            this.cachedReply.write("hostip");
            this.cachedReply.write(this.hostname);
            this.cachedReply.write((int) 0);
            this.cachedReply.write((int) 1);
            this.cachedReply.write("player_");
            this.cachedReply.write((int) 0);
            String[] astring = this.server.getPlayers();
            String[] astring1 = astring;
            int j = astring.length;

            for (int k = 0; k < j; ++k) {
                String s = astring1[k];

                this.cachedReply.write(s);
            }

            this.cachedReply.write((int) 0);
            return this.cachedReply.getBytes();
        }
    }

    private byte[] getIdentityToken(SocketAddress socketaddress) {
        return ((RemoteStatusChallenge) this.challenges.get(socketaddress)).getIdentityToken();
    }

    private Boolean hasChallenged(DatagramPacket datagrampacket) {
        SocketAddress socketaddress = datagrampacket.getSocketAddress();

        if (!this.challenges.containsKey(socketaddress)) {
            return Boolean.valueOf(false);
        } else {
            byte[] abyte = datagrampacket.getData();

            return ((RemoteStatusChallenge) this.challenges.get(socketaddress)).getToken() != StatusChallengeUtils.c(abyte, 7, datagrampacket.getLength()) ? Boolean.valueOf(false) : Boolean.valueOf(true);
        }
    }

    private void createChallenge(DatagramPacket datagrampacket) {
        RemoteStatusChallenge remotestatuschallenge = new RemoteStatusChallenge(this, datagrampacket);

        this.challenges.put(datagrampacket.getSocketAddress(), remotestatuschallenge);
        this.send(remotestatuschallenge.getChallengeResponse(), datagrampacket);
    }

    private void cleanChallenges() {
        if (this.running) {
            long i = MinecraftServer.ap();

            if (i >= this.clearedTime + 30000L) {
                this.clearedTime = i;
                Iterator iterator = this.challenges.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry entry = (Entry) iterator.next();

                    if (((RemoteStatusChallenge) entry.getValue()).isExpired(i).booleanValue()) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    public void run() {
        this.info("Query running on " + this.motd + ":" + this.bindPort);
        this.clearedTime = MinecraftServer.ap();
        this.p = new DatagramPacket(this.o, this.o.length);

        try {
            while (this.running) {
                try {
                    this.socket.receive(this.p);
                    this.cleanChallenges();
                    this.parsePacket(this.p);
                } catch (SocketTimeoutException sockettimeoutexception) {
                    this.cleanChallenges();
                } catch (PortUnreachableException portunreachableexception) {
                    ;
                } catch (IOException ioexception) {
                    this.a(ioexception);
                }
            }
        } finally {
            this.e();
        }
    }

    public void a() {
        if (!this.running) {
            if (0 < this.bindPort && '\uffff' >= this.bindPort) {
                if (this.g()) {
                    super.a();
                }
            } else {
                this.warning("Invalid query port " + this.bindPort + " found in \'" + this.server.b() + "\' (queries disabled)");
            }
        }
    }

    private void a(Exception exception) {
        if (this.running) {
            this.warning("Unexpected exception, buggy JRE? (" + exception.toString() + ")");
            if (!this.g()) {
                this.error("Failed to recover from buggy JRE, shutting down!");
                this.running = false;
            }
        }
    }

    private boolean g() {
        try {
            this.socket = new DatagramSocket(this.bindPort, InetAddress.getByName(this.motd));
            this.a(this.socket);
            this.socket.setSoTimeout(500);
            return true;
        } catch (SocketException socketexception) {
            this.warning("Unable to initialise query system on " + this.motd + ":" + this.bindPort + " (Socket): " + socketexception.getMessage());
        } catch (UnknownHostException unknownhostexception) {
            this.warning("Unable to initialise query system on " + this.motd + ":" + this.bindPort + " (Unknown Host): " + unknownhostexception.getMessage());
        } catch (Exception exception) {
            this.warning("Unable to initialise query system on " + this.motd + ":" + this.bindPort + " (E): " + exception.getMessage());
        }

        return false;
    }
}
