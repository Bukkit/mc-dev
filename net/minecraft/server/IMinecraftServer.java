package net.minecraft.server;

public interface IMinecraftServer {

    int a(String s, int i);

    String a(String s, String s1);

    void a(String s, Object object);

    void a();

    String b_();

    String u();

    int v();

    String w();

    String getVersion();

    int y();

    int z();

    String[] getPlayers();

    String J();

    String getPlugins();

    String h(String s);

    boolean isDebugging();

    void info(String s);

    void warning(String s);

    void i(String s);

    void j(String s);
}
