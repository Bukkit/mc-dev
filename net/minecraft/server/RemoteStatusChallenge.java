package net.minecraft.server;

import java.net.DatagramPacket;
import java.util.Date;
import java.util.Random;

class RemoteStatusChallenge {

    private long time;
    private int token;
    private byte[] identity;
    private byte[] response;
    private String f;

    final RemoteStatusListener a;

    public RemoteStatusChallenge(RemoteStatusListener remotestatuslistener, DatagramPacket datagrampacket) {
        this.a = remotestatuslistener;
        this.time = (new Date()).getTime();
        byte[] abyte = datagrampacket.getData();

        this.identity = new byte[4];
        this.identity[0] = abyte[3];
        this.identity[1] = abyte[4];
        this.identity[2] = abyte[5];
        this.identity[3] = abyte[6];
        this.f = new String(this.identity);
        this.token = (new Random()).nextInt(16777216);
        this.response = String.format("\t%s%d, new Object[] { this.f, Integer.valueOf(this.token)}).getBytes();
    }

    public Boolean isExpired(long i) {
        return Boolean.valueOf(this.time < i);
    }

    public int getToken() {
        return this.token;
    }

    public byte[] getChallengeResponse() {
        return this.response;
    }

    public byte[] getIdentityToken() {
        return this.identity;
    }
}
