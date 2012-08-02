package net.minecraft.server;

public class CommandKill extends CommandAbstract {

    public CommandKill() {}

    public String b() {
        return "kill";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        EntityHuman entityhuman = c(icommandlistener);

        entityhuman.damageEntity(DamageSource.OUT_OF_WORLD, 1000);
        icommandlistener.sendMessage("Ouch. That look like it hurt.");
    }
}
