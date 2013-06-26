package net.minecraft.server;

public class AttributeMapClient extends AttributeMapBase {

    public AttributeMapClient() {}

    public AttributeReadonly c(IAttribute iattribute) {
        return (AttributeReadonly) super.a(iattribute);
    }

    public AttributeReadonly b(String s) {
        return (AttributeReadonly) super.a(s);
    }

    public AttributeReadonly d(IAttribute iattribute) {
        if (this.b.containsKey(iattribute.a())) {
            throw new IllegalArgumentException("Attribute is already registered!");
        } else {
            AttributeReadonly attributereadonly = new AttributeReadonly(iattribute);

            this.b.put(iattribute.a(), attributereadonly);
            this.a.put(iattribute, attributereadonly);
            return attributereadonly;
        }
    }

    public AttributeInstance b(IAttribute iattribute) {
        return this.d(iattribute);
    }

    public AttributeInstance a(String s) {
        return this.b(s);
    }

    public AttributeInstance a(IAttribute iattribute) {
        return this.c(iattribute);
    }
}
