package net.minecraft.server;

public interface ICommandListener {

    String getName();

    void sendMessage(ChatMessage chatmessage);

    boolean a(int i, String s);

    ChunkCoordinates b();

    World f_();
}
