package net.minecraft.server;

public class CommandToggleDownfall extends CommandAbstract {

    public CommandToggleDownfall() {}

    public String b() {
        return "toggledownfall";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        this.c();
        a(icommandlistener, "commands.downfall.success", new Object[0]);
    }

    protected void c() {
        MinecraftServer.getServer().worldServer[0].w();
        MinecraftServer.getServer().worldServer[0].getWorldData().setThundering(true);
    }
}
