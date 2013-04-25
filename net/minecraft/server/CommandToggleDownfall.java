package net.minecraft.server;

public class CommandToggleDownfall extends CommandAbstract {

    public CommandToggleDownfall() {}

    public String c() {
        return "toggledownfall";
    }

    public int a() {
        return 2;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        this.d();
        a(icommandlistener, "commands.downfall.success", new Object[0]);
    }

    protected void d() {
        MinecraftServer.getServer().worldServer[0].A();
        MinecraftServer.getServer().worldServer[0].getWorldData().setThundering(true);
    }
}
