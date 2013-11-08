package net.minecraft.server;

import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.util.com.google.common.collect.Iterators;
import net.minecraft.util.com.google.common.collect.Lists;

public class ChatMessage extends ChatBaseComponent {

    private final String d;
    private final Object[] e;
    private final Object f = new Object();
    private long g = -1L;
    List b = Lists.newArrayList();
    public static final Pattern c = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

    public ChatMessage(String s, Object... aobject) {
        this.d = s;
        this.e = aobject;
        Object[] aobject1 = aobject;
        int i = aobject.length;

        for (int j = 0; j < i; ++j) {
            Object object = aobject1[j];

            if (object instanceof IChatBaseComponent) {
                ((IChatBaseComponent) object).b().a(this.b());
            }
        }
    }

    synchronized void g() {
        Object object = this.f;

        synchronized (this.f) {
            long i = LocaleI18n.a();

            if (i == this.g) {
                return;
            }

            this.g = i;
            this.b.clear();
        }

        try {
            this.b(LocaleI18n.get(this.d));
        } catch (ChatMessageException throw) {
            this.b.clear();

            try {
                this.b(LocaleI18n.b(this.d));
            } catch (ChatMessageException chatmessageexception1) {
                throw throw;
            }
        }
    }

    protected void b(String s) {
        boolean flag = false;
        Matcher matcher = c.matcher(s);
        int i = 0;
        int j = 0;

        try {
            int k;

            for (; matcher.find(j); j = k) {
                int l = matcher.start();

                k = matcher.end();
                if (l > j) {
                    ChatComponentText chatcomponenttext = new ChatComponentText(String.format(s.substring(j, l), new Object[0]));

                    chatcomponenttext.b().a(this.b());
                    this.b.add(chatcomponenttext);
                }

                String s1 = matcher.group(2);
                String s2 = s.substring(l, k);

                if ("%".equals(s1) && "%%".equals(s2)) {
                    ChatComponentText chatcomponenttext1 = new ChatComponentText("%");

                    chatcomponenttext1.b().a(this.b());
                    this.b.add(chatcomponenttext1);
                } else {
                    if (!"s".equals(s1)) {
                        throw new ChatMessageException(this, "Unsupported format: \'" + s2 + "\'");
                    }

                    String s3 = matcher.group(1);
                    int i1 = s3 != null ? Integer.parseInt(s3) - 1 : i++;

                    this.b.add(this.a(i1));
                }
            }

            if (j < s.length()) {
                ChatComponentText chatcomponenttext2 = new ChatComponentText(String.format(s.substring(j), new Object[0]));

                chatcomponenttext2.b().a(this.b());
                this.b.add(chatcomponenttext2);
            }
        } catch (IllegalFormatException illegalformatexception) {
            throw new ChatMessageException(this, illegalformatexception);
        }
    }

    private IChatBaseComponent a(int i) {
        if (i >= this.e.length) {
            throw new ChatMessageException(this, i);
        } else {
            Object object = this.e[i];
            Object object1;

            if (object instanceof IChatBaseComponent) {
                object1 = (IChatBaseComponent) object;
            } else {
                object1 = new ChatComponentText(object.toString());
                ((IChatBaseComponent) object1).b().a(this.b());
            }

            return (IChatBaseComponent) object1;
        }
    }

    public IChatBaseComponent setChatModifier(ChatModifier chatmodifier) {
        super.setChatModifier(chatmodifier);
        Object[] aobject = this.e;
        int i = aobject.length;

        for (int j = 0; j < i; ++j) {
            Object object = aobject[j];

            if (object instanceof IChatBaseComponent) {
                ((IChatBaseComponent) object).b().a(this.b());
            }
        }

        if (this.g > -1L) {
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

                ichatbasecomponent.b().a(chatmodifier);
            }
        }

        return this;
    }

    public Iterator iterator() {
        this.g();
        return Iterators.concat(a(this.b), a(this.a));
    }

    public String e() {
        this.g();
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

            stringbuilder.append(ichatbasecomponent.e());
        }

        return stringbuilder.toString();
    }

    public ChatMessage h() {
        Object[] aobject = new Object[this.e.length];

        for (int i = 0; i < this.e.length; ++i) {
            if (this.e[i] instanceof IChatBaseComponent) {
                aobject[i] = ((IChatBaseComponent) this.e[i]).f();
            } else {
                aobject[i] = this.e[i];
            }
        }

        ChatMessage chatmessage = new ChatMessage(this.d, aobject);

        chatmessage.setChatModifier(this.b().clone());
        Iterator iterator = this.a().iterator();

        while (iterator.hasNext()) {
            IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

            chatmessage.a(ichatbasecomponent.f());
        }

        return chatmessage;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatMessage)) {
            return false;
        } else {
            ChatMessage chatmessage = (ChatMessage) object;

            return Arrays.equals(this.e, chatmessage.e) && this.d.equals(chatmessage.d) && super.equals(object);
        }
    }

    public int hashCode() {
        int i = super.hashCode();

        i = 31 * i + this.d.hashCode();
        i = 31 * i + Arrays.hashCode(this.e);
        return i;
    }

    public String toString() {
        return "TranslatableComponent{key=\'" + this.d + '\'' + ", args=" + Arrays.toString(this.e) + ", siblings=" + this.a + ", style=" + this.b() + '}';
    }

    public String i() {
        return this.d;
    }

    public Object[] j() {
        return this.e;
    }

    public IChatBaseComponent f() {
        return this.h();
    }
}
