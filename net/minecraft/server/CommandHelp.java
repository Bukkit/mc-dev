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

        ICommand icommand;
        int j;

        try {
            j = astring.length == 0 ? 0 : a(icommandlistener, astring[0], 1, i + 1) - 1;
        } catch (ExceptionInvalidNumber exceptioninvalidnumber) {
            Map map = this.d();

            icommand = (ICommand) map.get(astring[0]);
            if (icommand != null) {
                throw new ExceptionUsage(icommand.c(icommandlistener), new Object[0]);
            }

            throw new ExceptionUnknownCommand();
        }

        int k = Math.min((j + 1) * b0, list.size());

        icommandlistener.sendMessage(ChatMessage.b("commands.help.header", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(i + 1)}).a(EnumChatFormat.DARK_GREEN));

        for (int l = j * b0; l < k; ++l) {
            icommand = (ICommand) list.get(l);
            icommandlistener.sendMessage(ChatMessage.e(icommand.c(icommandlistener)));
        }

        if (j == 0 && icommandlistener instanceof EntityHuman) {
            icommandlistener.sendMessage(ChatMessage.e("commands.help.footer").a(EnumChatFormat.GREEN));
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
