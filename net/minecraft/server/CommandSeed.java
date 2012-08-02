package net.minecraft.server;

public class CommandSeed extends CommandAbstract {

    public CommandSeed() {}

    public String b() {
        return "seed";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        EntityHuman entityhuman = c(icommandlistener);

        icommandlistener.sendMessage("Seed: " + entityhuman.world.getSeed());
    }
}
