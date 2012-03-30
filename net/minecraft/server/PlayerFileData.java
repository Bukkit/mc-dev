package net.minecraft.server;

public interface PlayerFileData {

    void save(EntityHuman entityhuman);

    void load(EntityHuman entityhuman);

    String[] getSeenPlayers();
}
