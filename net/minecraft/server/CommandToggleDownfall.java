package net.minecraft.server;

public class CommandToggleDownfall extends CommandAbstract {

    public CommandToggleDownfall() {}

    public String getCommand() {
        return "toggledownfall";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.downfall.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        this.d();
        a(icommandlistener, this, "commands.downfall.success", new Object[0]);
    }

    protected void d() {
        WorldData worlddata = MinecraftServer.getServer().worldServer[0].getWorldData();

        worlddata.setStorm(!worlddata.hasStorm());
    }
}
