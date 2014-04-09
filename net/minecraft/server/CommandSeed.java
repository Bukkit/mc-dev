package net.minecraft.server;

public class CommandSeed extends CommandAbstract {

    public CommandSeed() {}

    public boolean canUse(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().N() || super.canUse(icommandlistener);
    }

    public String getCommand() {
        return "seed";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.seed.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        Object object = icommandlistener instanceof EntityHuman ? ((EntityHuman) icommandlistener).world : MinecraftServer.getServer().getWorldServer(0);

        icommandlistener.sendMessage(new ChatMessage("commands.seed.success", new Object[] { Long.valueOf(((World) object).getSeed())}));
    }
}
