package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class CommandAbstract implements ICommand {

    private static ICommandDispatcher a = null;

    public CommandAbstract() {}

    public int a() {
        return 4;
    }

    public String a(ICommandListener icommandlistener) {
        return "/" + this.c();
    }

    public List b() {
        return null;
    }

    public boolean b(ICommandListener icommandlistener) {
        return icommandlistener.a(this.a(), this.c());
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return null;
    }

    public static int a(ICommandListener icommandlistener, String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { s});
        }
    }

    public static int a(ICommandListener icommandlistener, String s, int i) {
        return a(icommandlistener, s, i, Integer.MAX_VALUE);
    }

    public static int a(ICommandListener icommandlistener, String s, int i, int j) {
        int k = a(icommandlistener, s);

        if (k < i) {
            throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(k), Integer.valueOf(i)});
        } else if (k > j) {
            throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Integer.valueOf(k), Integer.valueOf(j)});
        } else {
            return k;
        }
    }

    public static double b(ICommandListener icommandlistener, String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException numberformatexception) {
            throw new ExceptionInvalidNumber("commands.generic.double.invalid", new Object[] { s});
        }
    }

    public static EntityPlayer c(ICommandListener icommandlistener) {
        if (icommandlistener instanceof EntityPlayer) {
            return (EntityPlayer) icommandlistener;
        } else {
            throw new ExceptionPlayerNotFound("You must specify which player you wish to perform this action on.", new Object[0]);
        }
    }

    public static EntityPlayer c(ICommandListener icommandlistener, String s) {
        EntityPlayer entityplayer = PlayerSelector.getPlayer(icommandlistener, s);

        if (entityplayer != null) {
            return entityplayer;
        } else {
            entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(s);
            if (entityplayer == null) {
                throw new ExceptionPlayerNotFound();
            } else {
                return entityplayer;
            }
        }
    }

    public static String d(ICommandListener icommandlistener, String s) {
        EntityPlayer entityplayer = PlayerSelector.getPlayer(icommandlistener, s);

        if (entityplayer != null) {
            return entityplayer.getLocalizedName();
        } else if (PlayerSelector.isPattern(s)) {
            throw new ExceptionPlayerNotFound();
        } else {
            return s;
        }
    }

    public static String a(ICommandListener icommandlistener, String[] astring, int i) {
        return a(icommandlistener, astring, i, false);
    }

    public static String a(ICommandListener icommandlistener, String[] astring, int i, boolean flag) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int j = i; j < astring.length; ++j) {
            if (j > i) {
                stringbuilder.append(" ");
            }

            String s = astring[j];

            if (flag) {
                String s1 = PlayerSelector.getPlayerNames(icommandlistener, s);

                if (s1 != null) {
                    s = s1;
                } else if (PlayerSelector.isPattern(s)) {
                    throw new ExceptionPlayerNotFound();
                }
            }

            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static String a(Object[] aobject) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int i = 0; i < aobject.length; ++i) {
            String s = aobject[i].toString();

            if (i > 0) {
                if (i == aobject.length - 1) {
                    stringbuilder.append(" and ");
                } else {
                    stringbuilder.append(", ");
                }
            }

            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static String a(Collection collection) {
        return a(collection.toArray(new String[0]));
    }

    public static boolean a(String s, String s1) {
        return s1.regionMatches(true, 0, s, 0, s.length());
    }

    public static List a(String[] astring, String... astring) {
        String s = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        String[] astring1 = astring;
        int i = astring.length;

        for (int j = 0; j < i; ++j) {
            String s1 = astring1[j];

            if (a(s, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public static List a(String[] astring, Iterable iterable) {
        String s = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();

            if (a(s, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public boolean a(String[] astring, int i) {
        return false;
    }

    public static void a(ICommandListener icommandlistener, String s, Object... aobject) {
        a(icommandlistener, 0, s, aobject);
    }

    public static void a(ICommandListener icommandlistener, int i, String s, Object... aobject) {
        if (a != null) {
            a.a(icommandlistener, i, s, aobject);
        }
    }

    public static void a(ICommandDispatcher icommanddispatcher) {
        a = icommanddispatcher;
    }

    public int a(ICommand icommand) {
        return this.c().compareTo(icommand.c());
    }

    public int compareTo(Object object) {
        return this.a((ICommand) object);
    }
}
