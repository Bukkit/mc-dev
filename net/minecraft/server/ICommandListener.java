package net.minecraft.server;

public interface ICommandListener {

    String getName();

    void sendMessage(String s);

    boolean b(String s);

    String a(String s, Object... aobject);
}
