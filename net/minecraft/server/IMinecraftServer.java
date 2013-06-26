package net.minecraft.server;

public interface IMinecraftServer {

    int a(String s, int i);

    String a(String s, String s1);

    void a(String s, Object object);

    void a();

    String b_();

    String w();

    int x();

    String y();

    String getVersion();

    int A();

    int B();

    String[] getPlayers();

    String L();

    String getPlugins();

    String g(String s);

    boolean isDebugging();

    void info(String s);

    void warning(String s);

    void h(String s);

    void i(String s);
}
