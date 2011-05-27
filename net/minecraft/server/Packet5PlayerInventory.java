package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet5PlayerInventory extends Packet {

    public int a;
    public ItemStack[] b;

    public Packet5PlayerInventory() {}

    public Packet5PlayerInventory(int i, ItemStack[] aitemstack) {
        this.a = i;
        this.b = new ItemStack[aitemstack.length];

        for (int j = 0; j < this.b.length; ++j) {
            this.b[j] = aitemstack[j] == null ? null : aitemstack[j].d();
        }
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        short short1 = datainputstream.readShort();

        this.b = new ItemStack[short1];

        for (int i = 0; i < short1; ++i) {
            short short2 = datainputstream.readShort();

            if (short2 >= 0) {
                byte b0 = datainputstream.readByte();
                short short3 = datainputstream.readShort();

                this.b[i] = new ItemStack(short2, b0, short3);
            }
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b.length);

        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i] == null) {
                dataoutputstream.writeShort(-1);
            } else {
                dataoutputstream.writeShort((short) this.b[i].c);
                dataoutputstream.writeByte((byte) this.b[i].a);
                dataoutputstream.writeShort((short) this.b[i].d);
            }
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 6 + this.b.length * 5;
    }
}
