package net.minecraft.server;

public interface Convertable {

    boolean isConvertable(String s);

    boolean convert(String s, IProgressUpdate iprogressupdate);
}
