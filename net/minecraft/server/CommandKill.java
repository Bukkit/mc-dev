package net.minecraft.server;

public class CommandKill extends CommandAbstract {

    public CommandKill() {}

    public String c() {
        return "kill";
    }

    public int a() {
        return 0;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.kill.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        EntityPlayer entityplayer = b(icommandlistener);

        entityplayer.damageEntity(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
        icommandlistener.sendMessage(new ChatMessage("commands.kill.success", new Object[0]));
    }
}
