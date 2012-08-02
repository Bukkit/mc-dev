package net.minecraft.server;

public interface IMinecraftServer {

    int a(String s, int i);

    String a(String s, String s1);

    void a(String s, Object object);

    void a();

    String c();

    String t();

    int u();

    String v();

    String getVersion();

    int x();

    int y();

    String[] getPlayers();

    String I();

    String getPlugins();

    String i(String s);

    boolean isDebugging();

    void info(String s);

    void warning(String s);

    void j(String s);

    void k(String s);
}
