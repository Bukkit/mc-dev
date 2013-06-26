package net.minecraft.server;

import java.util.Collection;
import java.util.UUID;

public interface AttributeInstance {

    IAttribute a();

    double b();

    void a(double d0);

    Collection c();

    AttributeModifier a(UUID uuid);

    void a(AttributeModifier attributemodifier);

    void b(AttributeModifier attributemodifier);

    double e();
}
