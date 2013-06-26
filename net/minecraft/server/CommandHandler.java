package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class CommandHandler implements ICommandHandler {

    private final Map a = new HashMap();
    private final Set b = new HashSet();

    public CommandHandler() {}

    public int a(ICommandListener icommandlistener, String s) {
        s = s.trim();
        if (s.startsWith("/")) {
            s = s.substring(1);
        }

        String[] astring = s.split(" ");
        String s1 = astring[0];

        astring = a(astring);
        ICommand icommand = (ICommand) this.a.get(s1);
        int i = this.a(icommand, astring);
        int j = 0;

        try {
            if (icommand == null) {
                throw new ExceptionUnknownCommand();
            }

            if (icommand.a(icommandlistener)) {
                if (i > -1) {
                    EntityPlayer[] aentityplayer = PlayerSelector.getPlayers(icommandlistener, astring[i]);
                    String s2 = astring[i];
                    EntityPlayer[] aentityplayer1 = aentityplayer;
                    int k = aentityplayer.length;

                    for (int l = 0; l < k; ++l) {
                        EntityPlayer entityplayer = aentityplayer1[l];

                        astring[i] = entityplayer.getLocalizedName();

                        try {
                            icommand.b(icommandlistener, astring);
                            ++j;
                        } catch (CommandException commandexception) {
                            icommandlistener.sendMessage(ChatMessage.b(commandexception.getMessage(), commandexception.a()).a(EnumChatFormat.RED));
                        }
                    }

                    astring[i] = s2;
                } else {
                    icommand.b(icommandlistener, astring);
                    ++j;
                }
            } else {
                icommandlistener.sendMessage(ChatMessage.e("commands.generic.permission").a(EnumChatFormat.RED));
            }
        } catch (ExceptionUsage exceptionusage) {
            icommandlistener.sendMessage(ChatMessage.b("commands.generic.usage", new Object[] { ChatMessage.b(exceptionusage.getMessage(), exceptionusage.a())}).a(EnumChatFormat.RED));
        } catch (CommandException commandexception1) {
            icommandlistener.sendMessage(ChatMessage.b(commandexception1.getMessage(), commandexception1.a()).a(EnumChatFormat.RED));
        } catch (Throwable throwable) {
            icommandlistener.sendMessage(ChatMessage.e("commands.generic.exception").a(EnumChatFormat.RED));
            throwable.printStackTrace();
        }

        return j;
    }

    public ICommand a(ICommand icommand) {
        List list = icommand.b();

        this.a.put(icommand.c(), icommand);
        this.b.add(icommand);
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                ICommand icommand1 = (ICommand) this.a.get(s);

                if (icommand1 == null || !icommand1.c().equals(s)) {
                    this.a.put(s, icommand);
                }
            }
        }

        return icommand;
    }

    private static String[] a(String[] astring) {
        String[] astring1 = new String[astring.length - 1];

        for (int i = 1; i < astring.length; ++i) {
            astring1[i - 1] = astring[i];
        }

        return astring1;
    }

    public List b(ICommandListener icommandlistener, String s) {
        String[] astring = s.split(" ", -1);
        String s1 = astring[0];

        if (astring.length == 1) {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = this.a.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();

                if (CommandAbstract.a(s1, (String) entry.getKey()) && ((ICommand) entry.getValue()).a(icommandlistener)) {
                    arraylist.add(entry.getKey());
                }
            }

            return arraylist;
        } else {
            if (astring.length > 1) {
                ICommand icommand = (ICommand) this.a.get(s1);

                if (icommand != null) {
                    return icommand.a(icommandlistener, a(astring));
                }
            }

            return null;
        }
    }

    public List a(ICommandListener icommandlistener) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            ICommand icommand = (ICommand) iterator.next();

            if (icommand.a(icommandlistener)) {
                arraylist.add(icommand);
            }
        }

        return arraylist;
    }

    public Map a() {
        return this.a;
    }

    private int a(ICommand icommand, String[] astring) {
        if (icommand == null) {
            return -1;
        } else {
            for (int i = 0; i < astring.length; ++i) {
                if (icommand.a(astring, i) && PlayerSelector.isList(astring[i])) {
                    return i;
                }
            }

            return -1;
        }
    }
}
