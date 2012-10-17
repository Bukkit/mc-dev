package net.minecraft.server;

public class CommandKill extends CommandAbstract {

    public CommandKill() {}

    public String c() {
        return "kill";
    }

    public int a() {
        return 0;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        EntityPlayer entityplayer = c(icommandlistener);

        entityplayer.damageEntity(DamageSource.OUT_OF_WORLD, 1000);
        icommandlistener.sendMessage("Ouch. That looks like it hurt.");
    }
}
