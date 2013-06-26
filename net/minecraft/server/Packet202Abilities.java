package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet202Abilities extends Packet {

    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private float e;
    private float f;

    public Packet202Abilities() {}

    public Packet202Abilities(PlayerAbilities playerabilities) {
        this.a(playerabilities.isInvulnerable);
        this.b(playerabilities.isFlying);
        this.c(playerabilities.canFly);
        this.d(playerabilities.canInstantlyBuild);
        this.a(playerabilities.a());
        this.b(playerabilities.b());
    }

    public void a(DataInput datainput) {
        byte b0 = datainput.readByte();

        this.a((b0 & 1) > 0);
        this.b((b0 & 2) > 0);
        this.c((b0 & 4) > 0);
        this.d((b0 & 8) > 0);
        this.a(datainput.readFloat());
        this.b(datainput.readFloat());
    }

    public void a(DataOutput dataoutput) {
        byte b0 = 0;

        if (this.d()) {
            b0 = (byte) (b0 | 1);
        }

        if (this.f()) {
            b0 = (byte) (b0 | 2);
        }

        if (this.g()) {
            b0 = (byte) (b0 | 4);
        }

        if (this.h()) {
            b0 = (byte) (b0 | 8);
        }

        dataoutput.writeByte(b0);
        dataoutput.writeFloat(this.e);
        dataoutput.writeFloat(this.f);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2;
    }

    public boolean d() {
        return this.a;
    }

    public void a(boolean flag) {
        this.a = flag;
    }

    public boolean f() {
        return this.b;
    }

    public void b(boolean flag) {
        this.b = flag;
    }

    public boolean g() {
        return this.c;
    }

    public void c(boolean flag) {
        this.c = flag;
    }

    public boolean h() {
        return this.d;
    }

    public void d(boolean flag) {
        this.d = flag;
    }

    public void a(float f) {
        this.e = f;
    }

    public void b(float f) {
        this.f = f;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
