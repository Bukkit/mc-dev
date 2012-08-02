package net.minecraft.server;

public interface Convertable {

    IDataManager a(String s, boolean flag);

    void d();

    void e(String s);

    boolean isConvertable(String s);

    boolean convert(String s, IProgressUpdate iprogressupdate);
}
