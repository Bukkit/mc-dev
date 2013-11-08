package net.minecraft.server;

import java.util.Iterator;

public class ChatComponentText extends ChatBaseComponent {

    private final String b;

    public ChatComponentText(String s) {
        this.b = s;
    }

    public String g() {
        return this.b;
    }

    public String e() {
        return this.b;
    }

    public ChatComponentText h() {
        ChatComponentText chatcomponenttext = new ChatComponentText(this.b);

        chatcomponenttext.setChatModifier(this.b().clone());
        Iterator iterator = this.a().iterator();

        while (iterator.hasNext()) {
            IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

            chatcomponenttext.a(ichatbasecomponent.f());
        }

        return chatcomponenttext;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatComponentText)) {
            return false;
        } else {
            ChatComponentText chatcomponenttext = (ChatComponentText) object;

            return this.b.equals(chatcomponenttext.g()) && super.equals(object);
        }
    }

    public String toString() {
        return "TextComponent{text=\'" + this.b + '\'' + ", siblings=" + this.a + ", style=" + this.b() + '}';
    }

    public IChatBaseComponent f() {
        return this.h();
    }
}
