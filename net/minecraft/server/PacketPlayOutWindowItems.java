package net.minecraft.server;

import java.util.List;

public class PacketPlayOutWindowItems extends Packet {

    private int a;
    private ItemStack[] b;

    public PacketPlayOutWindowItems() {}

    public PacketPlayOutWindowItems(int i, List list) {
        this.a = i;
        this.b = new ItemStack[list.size()];

        for (int j = 0; j < this.b.length; ++j) {
            ItemStack itemstack = (ItemStack) list.get(j);

            this.b[j] = itemstack == null ? null : itemstack.cloneItemStack();
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
        short short1 = packetdataserializer.readShort();

        this.b = new ItemStack[short1];

        for (int i = 0; i < short1; ++i) {
            this.b[i] = packetdataserializer.c();
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b.length);
        ItemStack[] aitemstack = this.b;
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j) {
            ItemStack itemstack = aitemstack[j];

            packetdataserializer.a(itemstack);
        }
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
