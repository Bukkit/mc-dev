package net.minecraft.server;

import java.util.Iterator;

public class CommandDispatcher extends CommandHandler implements ICommandDispatcher {

    public CommandDispatcher() {
        this.a(new CommandTime());
        this.a(new CommandGamemode());
        this.a(new CommandGamemodeDefault());
        this.a(new CommandKill());
        this.a(new CommandToggleDownfall());
        this.a(new CommandXp());
        this.a(new CommandTp());
        this.a(new CommandGive());
        this.a(new CommandMe());
        this.a(new CommandSeed());
        this.a(new CommandHelp());
        this.a(new CommandDebug());
        this.a(new CommandTell());
        if (MinecraftServer.getServer().S()) {
            this.a(new CommandOp());
            this.a(new CommandDeop());
            this.a(new CommandStop());
            this.a(new CommandSaveAll());
            this.a(new CommandSaveOff());
            this.a(new CommandSaveOn());
            this.a(new CommandBanIp());
            this.a(new CommandPardonIP());
            this.a(new CommandBan());
            this.a(new CommandBanList());
            this.a(new CommandPardon());
            this.a(new CommandKick());
            this.a(new CommandList());
            this.a(new CommandSay());
            this.a(new CommandWhitelist());
        } else {
            this.a(new CommandPublish());
        }

        CommandAbstract.a((ICommandDispatcher) this);
    }

    public void a(ICommandListener icommandlistener, int i, String s, Object... aobject) {
        Iterator iterator = MinecraftServer.getServer().getServerConfigurationManager().players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer != icommandlistener && MinecraftServer.getServer().getServerConfigurationManager().isOp(entityplayer.name)) {
                entityplayer.sendMessage("\u00A77\u00A7o[" + icommandlistener.getName() + ": " + entityplayer.a(s, aobject) + "]");
            }
        }

        if (icommandlistener != MinecraftServer.getServer()) {
            MinecraftServer.log.info("[" + icommandlistener.getName() + ": " + MinecraftServer.getServer().a(s, aobject) + "]");
        }

        if ((i & 1) != 1) {
            icommandlistener.sendMessage(icommandlistener.a(s, aobject));
        }
    }
}
