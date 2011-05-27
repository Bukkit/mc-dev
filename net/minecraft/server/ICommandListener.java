package net.minecraft.server;

public interface ICommandListener {

    void sendMessage(String s);

    String getName();
}
