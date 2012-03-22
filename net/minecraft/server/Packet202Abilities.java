package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet202Abilities extends Packet {

    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;

    public Packet202Abilities() {}

    public Packet202Abilities(PlayerAbilities playerabilities) {
        this.a = playerabilities.isInvulnerable;
        this.b = playerabilities.isFlying;
        this.c = playerabilities.canFly;
        this.d = playerabilities.canInstantlyBuild;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readBoolean();
        this.b = datainputstream.readBoolean();
        this.c = datainputstream.readBoolean();
        this.d = datainputstream.readBoolean();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeBoolean(this.a);
        dataoutputstream.writeBoolean(this.b);
        dataoutputstream.writeBoolean(this.c);
        dataoutputstream.writeBoolean(this.d);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 1;
    }
}
