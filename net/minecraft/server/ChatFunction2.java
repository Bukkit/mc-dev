package net.minecraft.server;

import net.minecraft.util.com.google.common.base.Function;

final class ChatFunction2 implements Function {

    ChatFunction2() {}

    public IChatBaseComponent a(IChatBaseComponent ichatbasecomponent) {
        IChatBaseComponent ichatbasecomponent1 = ichatbasecomponent.f();

        ichatbasecomponent1.setChatModifier(ichatbasecomponent1.b().m());
        return ichatbasecomponent1;
    }

    public Object apply(Object object) {
        return this.a((IChatBaseComponent) object);
    }
}
