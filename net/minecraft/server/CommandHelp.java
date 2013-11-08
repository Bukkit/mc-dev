package net.minecraft.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandHelp extends CommandAbstract {

    public CommandHelp() {}

    public String c() {
        return "help";
    }

    public int a() {
        return 0;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.help.usage";
    }

    public List b() {
        return Arrays.asList(new String[] { "?"});
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        List list = this.d(icommandlistener);
        byte b0 = 7;
        int i = (list.size() - 1) / b0;
        boolean flag = false;

        int j;

        try {
            j = astring.length == 0 ? 0 : a(icommandlistener, astring[0], 1, i + 1) - 1;
        } catch (ExceptionInvalidNumber throw) {
            Map map = this.d();
            ICommand icommand = (ICommand) map.get(astring[0]);

            if (icommand != null) {
                throw new ExceptionUsage(icommand.c(icommandlistener), new Object[0]);
            }

            if (MathHelper.a(astring[0], -1) != -1) {
                throw throw;
            }

            throw new ExceptionUnknownCommand();
        }

        int k = Math.min((j + 1) * b0, list.size());
        ChatMessage chatmessage = new ChatMessage("commands.help.header", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(i + 1)});

        chatmessage.b().setColor(EnumChatFormat.DARK_GREEN);
        icommandlistener.sendMessage(chatmessage);

        for (int l = j * b0; l < k; ++l) {
            ICommand icommand1 = (ICommand) list.get(l);
            ChatMessage chatmessage1 = new ChatMessage(icommand1.c(icommandlistener), new Object[0]);

            chatmessage1.b().a(new ChatClickable(EnumClickAction.SUGGEST_COMMAND, "/" + icommand1.c() + " "));
            icommandlistener.sendMessage(chatmessage1);
        }

        if (j == 0 && icommandlistener instanceof EntityHuman) {
            ChatMessage chatmessage2 = new ChatMessage("commands.help.footer", new Object[0]);

            chatmessage2.b().setColor(EnumChatFormat.GREEN);
            icommandlistener.sendMessage(chatmessage2);
        }
    }

    protected List d(ICommandListener icommandlistener) {
        List list = MinecraftServer.getServer().getCommandHandler().a(icommandlistener);

        Collections.sort(list);
        return list;
    }

    protected Map d() {
        return MinecraftServer.getServer().getCommandHandler().a();
    }
}
