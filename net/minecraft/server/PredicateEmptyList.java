package net.minecraft.server;

import net.minecraft.util.com.google.common.base.Predicate;

final class PredicateEmptyList implements Predicate {

    PredicateEmptyList() {}

    public boolean a(String s) {
        return !UtilColor.b(s);
    }

    public boolean apply(Object object) {
        return this.a((String) object);
    }
}
