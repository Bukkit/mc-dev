package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Packet44UpdateAttributes extends Packet {

    private int a;
    private final Map b = new HashMap();

    public Packet44UpdateAttributes() {}

    public Packet44UpdateAttributes(int i, Collection collection) {
        this.a = i;
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            AttributeInstance attributeinstance = (AttributeInstance) iterator.next();

            this.b.put(attributeinstance.a().a(), Double.valueOf(attributeinstance.e()));
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        int i = datainput.readInt();

        for (int j = 0; j < i; ++j) {
            this.b.put(a(datainput, 64), Double.valueOf(datainput.readDouble()));
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b.size());
        Iterator iterator = this.b.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            a((String) entry.getKey(), dataoutput);
            dataoutput.writeDouble(((Double) entry.getValue()).doubleValue());
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 8 + this.b.size() * 24;
    }
}
