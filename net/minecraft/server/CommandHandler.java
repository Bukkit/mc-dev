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

    public void a(ICommandListener icommandlistener, String s) {
        if (s.startsWith("/")) {
            s = s.substring(1);
        }

        String[] astring = s.split(" ");
        String s1 = astring[0];

        astring = a(astring);
        ICommand icommand = (ICommand) this.a.get(s1);

        try {
            if (icommand == null) {
                throw new ExceptionUnknownCommand();
            }

            if (icommand.b(icommandlistener)) {
                icommand.b(icommandlistener, astring);
            } else {
                icommandlistener.sendMessage("\u00A7cYou do not have permission to use this command.");
            }
        } catch (ExceptionUsage exceptionusage) {
            icommandlistener.sendMessage("\u00A7c" + icommandlistener.a("commands.generic.usage", new Object[] { icommandlistener.a(exceptionusage.getMessage(), exceptionusage.a())}));
        } catch (CommandException commandexception) {
            icommandlistener.sendMessage("\u00A7c" + icommandlistener.a(commandexception.getMessage(), commandexception.a()));
        } catch (Throwable throwable) {
            icommandlistener.sendMessage("\u00A7c" + icommandlistener.a("commands.generic.exception", new Object[0]));
            throwable.printStackTrace();
        }
    }

    public ICommand a(ICommand icommand) {
        List list = icommand.a();

        this.a.put(icommand.b(), icommand);
        this.b.add(icommand);
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                ICommand icommand1 = (ICommand) this.a.get(s);

                if (icommand1 == null || !icommand1.b().equals(s)) {
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

                if (CommandAbstract.a(s1, (String) entry.getKey()) && ((ICommand) entry.getValue()).b(icommandlistener)) {
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

            if (icommand.b(icommandlistener)) {
                arraylist.add(icommand);
            }
        }

        return arraylist;
    }

    public Map a() {
        return this.a;
    }
}
