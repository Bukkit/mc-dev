package net.minecraft.server;

public interface IMinecraftServer {

    int a(String s, int i);

    String a(String s, String s1);

    void a(String s, Object object);

    void a();

    String b();

    String x();

    int y();

    String z();

    String getVersion();

    int B();

    int C();

    String[] getPlayers();

    String M();

    String getPlugins();

    String g(String s);

    boolean isDebugging();

    void info(String s);

    void warning(String s);

    void h(String s);

    void i(String s);
}
