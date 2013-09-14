package net.minecraft.server;

import java.util.Iterator;

public class CommandDispatcher extends CommandHandler implements ICommandDispatcher {

    public CommandDispatcher() {
        this.a(new CommandTime());
        this.a(new CommandGamemode());
        this.a(new CommandDifficulty());
        this.a(new CommandGamemodeDefault());
        this.a(new CommandKill());
        this.a(new CommandToggleDownfall());
        this.a(new CommandWeather());
        this.a(new CommandXp());
        this.a(new CommandTp());
        this.a(new CommandGive());
        this.a(new CommandEffect());
        this.a(new CommandEnchant());
        this.a(new CommandMe());
        this.a(new CommandSeed());
        this.a(new CommandHelp());
        this.a(new CommandDebug());
        this.a(new CommandTell());
        this.a(new CommandSay());
        this.a(new CommandSpawnpoint());
        this.a(new CommandGamerule());
        this.a(new CommandClear());
        this.a(new CommandTestFor());
        this.a(new CommandSpreadPlayers());
        this.a(new CommandPlaySound());
        this.a(new CommandScoreboard());
        if (MinecraftServer.getServer().V()) {
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
            this.a(new CommandWhitelist());
            this.a(new CommandIdleTimeout());
        } else {
            this.a(new CommandPublish());
        }

        CommandAbstract.a((ICommandDispatcher) this);
    }

    public void a(ICommandListener icommandlistener, int i, String s, Object... aobject) {
        boolean flag = true;

        if (icommandlistener instanceof TileEntityCommand && !MinecraftServer.getServer().worldServer[0].getGameRules().getBoolean("commandBlockOutput")) {
            flag = false;
        }

        ChatMessage chatmessage = ChatMessage.b("chat.type.admin", new Object[] { icommandlistener.getName(), ChatMessage.b(s, aobject)});

        chatmessage.a(EnumChatFormat.GRAY);
        chatmessage.b(Boolean.valueOf(true));
        if (flag) {
            Iterator iterator = MinecraftServer.getServer().getPlayerList().players.iterator();

            while (iterator.hasNext()) {
                EntityPlayer entityplayer = (EntityPlayer) iterator.next();

                if (entityplayer != icommandlistener && MinecraftServer.getServer().getPlayerList().isOp(entityplayer.getName())) {
                    entityplayer.sendMessage(chatmessage);
                }
            }
        }

        if (icommandlistener != MinecraftServer.getServer()) {
            MinecraftServer.getServer().sendMessage(chatmessage);
        }

        if ((i & 1) != 1) {
            icommandlistener.sendMessage(ChatMessage.b(s, aobject));
        }
    }
}
