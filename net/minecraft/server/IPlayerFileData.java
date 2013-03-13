package net.minecraft.server;

public interface IPlayerFileData {

    void save(EntityHuman entityhuman);

    NBTTagCompound load(EntityHuman entityhuman);

    String[] getSeenPlayers();
}
