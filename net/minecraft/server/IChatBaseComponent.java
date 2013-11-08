package net.minecraft.server;

import java.util.List;

public interface IChatBaseComponent extends Iterable {

    IChatBaseComponent setChatModifier(ChatModifier chatmodifier);

    ChatModifier b();

    IChatBaseComponent a(String s);

    IChatBaseComponent a(IChatBaseComponent ichatbasecomponent);

    String e();

    String c();

    List a();

    IChatBaseComponent f();
}
