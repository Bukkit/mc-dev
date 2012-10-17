package net.minecraft.server;

public interface ICommandListener {

    String getName();

    void sendMessage(String s);

    boolean a(int i, String s);

    String a(String s, Object... aobject);

    ChunkCoordinates b();
}
