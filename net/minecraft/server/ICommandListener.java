package net.minecraft.server;

public interface ICommandListener {

    String getName();

    IChatBaseComponent getScoreboardDisplayName();

    void sendMessage(IChatBaseComponent ichatbasecomponent);

    boolean a(int i, String s);

    ChunkCoordinates getChunkCoordinates();

    World getWorld();
}
