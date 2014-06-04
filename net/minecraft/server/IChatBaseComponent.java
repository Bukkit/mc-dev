package net.minecraft.server;

import java.util.List;

public interface IChatBaseComponent extends Iterable {

    IChatBaseComponent setChatModifier(ChatModifier chatmodifier);

    ChatModifier getChatModifier();

    IChatBaseComponent a(String s);

    IChatBaseComponent addSibling(IChatBaseComponent ichatbasecomponent);

    String e();

    String c();

    List a();

    IChatBaseComponent f();
}
