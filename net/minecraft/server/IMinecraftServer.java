package net.minecraft.server;

public interface IMinecraftServer {

    int getProperty(String s, int i);

    String a(String s, String s1);

    void a(String s, Object object);

    void c();

    String getPropertiesFile();

    String getMotd();

    int getPort();

    String getServerAddress();

    String getVersion();

    int getPlayerCount();

    int getMaxPlayers();

    String[] getPlayers();

    String getWorldName();

    String getPlugins();

    void o();

    String d(String s);

    boolean isDebugging();

    void sendMessage(String s);

    void warning(String s);

    void severe(String s);

    void debug(String s);
}
