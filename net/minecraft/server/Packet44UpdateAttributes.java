package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Packet44UpdateAttributes extends Packet {

    private int a;
    private final List b = new ArrayList();

    public Packet44UpdateAttributes() {}

    public Packet44UpdateAttributes(int i, Collection collection) {
        this.a = i;
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            AttributeInstance attributeinstance = (AttributeInstance) iterator.next();

            this.b.add(new AttributeSnapshot(this, attributeinstance.a().a(), attributeinstance.b(), attributeinstance.c()));
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        int i = datainput.readInt();

        for (int j = 0; j < i; ++j) {
            String s = a(datainput, 64);
            double d0 = datainput.readDouble();
            ArrayList arraylist = new ArrayList();
            short short1 = datainput.readShort();

            for (int k = 0; k < short1; ++k) {
                UUID uuid = new UUID(datainput.readLong(), datainput.readLong());

                arraylist.add(new AttributeModifier(uuid, "Unknown synced attribute modifier", datainput.readDouble(), datainput.readByte()));
            }

            this.b.add(new AttributeSnapshot(this, s, d0, arraylist));
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b.size());
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            AttributeSnapshot attributesnapshot = (AttributeSnapshot) iterator.next();

            a(attributesnapshot.a(), dataoutput);
            dataoutput.writeDouble(attributesnapshot.b());
            dataoutput.writeShort(attributesnapshot.c().size());
            Iterator iterator1 = attributesnapshot.c().iterator();

            while (iterator1.hasNext()) {
                AttributeModifier attributemodifier = (AttributeModifier) iterator1.next();

                dataoutput.writeLong(attributemodifier.a().getMostSignificantBits());
                dataoutput.writeLong(attributemodifier.a().getLeastSignificantBits());
                dataoutput.writeDouble(attributemodifier.d());
                dataoutput.writeByte(attributemodifier.c());
            }
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 8 + this.b.size() * 24;
    }
}
