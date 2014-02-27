package net.minecraft.server;

public class RegistryBlocks extends RegistryMaterials {

    private final String d;
    private Object e;

    public RegistryBlocks(String s) {
        this.d = s;
    }

    public void a(int i, String s, Object object) {
        if (this.d.equals(s)) {
            this.e = object;
        }

        super.a(i, s, object);
    }

    public Object a(String s) {
        Object object = super.a(s);

        return object == null ? this.e : object;
    }

    public Object a(int i) {
        Object object = super.a(i);

        return object == null ? this.e : object;
    }

    public Object get(Object object) {
        return this.a((String) object);
    }
}
