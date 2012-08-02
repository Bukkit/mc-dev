package net.minecraft.server;

public class DemoWorldServer extends WorldServer {

    private static final long L = (long) "North Carolina".hashCode();
    public static final WorldSettings a = (new WorldSettings(L, EnumGamemode.SURVIVAL, true, false, WorldType.NORMAL)).a();

    public DemoWorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, MethodProfiler methodprofiler) {
        super(minecraftserver, idatamanager, s, i, a, methodprofiler);
    }
}
