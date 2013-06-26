package net.minecraft.server;

import com.google.common.primitives.Doubles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class CommandAbstract implements ICommand {

    private static ICommandDispatcher a;

    public CommandAbstract() {}

    public int a() {
        return 4;
    }

    public List b() {
        return null;
    }

    public boolean a(ICommandListener icommandlistener) {
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
            double d0 = Double.parseDouble(s);

            if (!Doubles.isFinite(d0)) {
                throw new ExceptionInvalidNumber("commands.generic.double.invalid", new Object[] { s});
            } else {
                return d0;
            }
        } catch (NumberFormatException numberformatexception) {
            throw new ExceptionInvalidNumber("commands.generic.double.invalid", new Object[] { s});
        }
    }

    public static double a(ICommandListener icommandlistener, String s, double d0) {
        return a(icommandlistener, s, d0, Double.MAX_VALUE);
    }

    public static double a(ICommandListener icommandlistener, String s, double d0, double d1) {
        double d2 = b(icommandlistener, s);

        if (d2 < d0) {
            throw new ExceptionInvalidNumber("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d2), Double.valueOf(d0)});
        } else if (d2 > d1) {
            throw new ExceptionInvalidNumber("commands.generic.double.tooBig", new Object[] { Double.valueOf(d2), Double.valueOf(d1)});
        } else {
            return d2;
        }
    }

    public static boolean c(ICommandListener icommandlistener, String s) {
        if (!s.equals("true") && !s.equals("1")) {
            if (!s.equals("false") && !s.equals("0")) {
                throw new CommandException("commands.generic.boolean.invalid", new Object[] { s});
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static EntityPlayer b(ICommandListener icommandlistener) {
        if (icommandlistener instanceof EntityPlayer) {
            return (EntityPlayer) icommandlistener;
        } else {
            throw new ExceptionPlayerNotFound("You must specify which player you wish to perform this action on.", new Object[0]);
        }
    }

    public static EntityPlayer d(ICommandListener icommandlistener, String s) {
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

    public static String e(ICommandListener icommandlistener, String s) {
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

    public static double a(ICommandListener icommandlistener, double d0, String s) {
        return a(icommandlistener, d0, s, -30000000, 30000000);
    }

    public static double a(ICommandListener icommandlistener, double d0, String s, int i, int j) {
        boolean flag = s.startsWith("~");

        if (flag && Double.isNaN(d0)) {
            throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { Double.valueOf(d0)});
        } else {
            double d1 = flag ? d0 : 0.0D;

            if (!flag || s.length() > 1) {
                boolean flag1 = s.contains(".");

                if (flag) {
                    s = s.substring(1);
                }

                d1 += b(icommandlistener, s);
                if (!flag1 && !flag) {
                    d1 += 0.5D;
                }
            }

            if (i != 0 || j != 0) {
                if (d1 < (double) i) {
                    throw new ExceptionInvalidNumber("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d1), Integer.valueOf(i)});
                }

                if (d1 > (double) j) {
                    throw new ExceptionInvalidNumber("commands.generic.double.tooBig", new Object[] { Double.valueOf(d1), Integer.valueOf(j)});
                }
            }

            return d1;
        }
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
        return a(collection.toArray(new String[collection.size()]));
    }

    public static String b(Collection collection) {
        String[] astring = new String[collection.size()];
        int i = 0;

        EntityLiving entityliving;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); astring[i++] = entityliving.getScoreboardDisplayName()) {
            entityliving = (EntityLiving) iterator.next();
        }

        return a((Object[]) astring);
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
