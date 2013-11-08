package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

import net.minecraft.util.com.google.common.collect.Iterators;
import net.minecraft.util.com.google.common.collect.Lists;

public abstract class ChatBaseComponent implements IChatBaseComponent {

    protected List a = Lists.newArrayList();
    private ChatModifier b;

    public ChatBaseComponent() {}

    public IChatBaseComponent a(IChatBaseComponent ichatbasecomponent) {
        ichatbasecomponent.b().a(this.b());
        this.a.add(ichatbasecomponent);
        return this;
    }

    public List a() {
        return this.a;
    }

    public IChatBaseComponent a(String s) {
        return this.a((IChatBaseComponent) (new ChatComponentText(s)));
    }

    public IChatBaseComponent setChatModifier(ChatModifier chatmodifier) {
        this.b = chatmodifier;
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

            ichatbasecomponent.b().a(this.b());
        }

        return this;
    }

    public ChatModifier b() {
        if (this.b == null) {
            this.b = new ChatModifier();
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

                ichatbasecomponent.b().a(this.b);
            }
        }

        return this.b;
    }

    public Iterator iterator() {
        return Iterators.concat(Iterators.forArray(new ChatBaseComponent[] { this}), a((Iterable) this.a));
    }

    public final String c() {
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = this.iterator();

        while (iterator.hasNext()) {
            IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

            stringbuilder.append(ichatbasecomponent.e());
        }

        return stringbuilder.toString();
    }

    public static Iterator a(Iterable iterable) {
        Iterator iterator = Iterators.concat(Iterators.transform(iterable.iterator(), new ChatFunction1()));

        iterator = Iterators.transform(iterator, new ChatFunction2());
        return iterator;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatBaseComponent)) {
            return false;
        } else {
            ChatBaseComponent chatbasecomponent = (ChatBaseComponent) object;

            return this.a.equals(chatbasecomponent.a) && this.b().equals(chatbasecomponent.b());
        }
    }

    public int hashCode() {
        return 31 * this.b.hashCode() + this.a.hashCode();
    }

    public String toString() {
        return "BaseComponent{style=" + this.b + ", siblings=" + this.a + '}';
    }
}
