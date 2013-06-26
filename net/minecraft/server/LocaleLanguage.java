package net.minecraft.server;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class LocaleLanguage {

    private static final Pattern a = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
    private static final Splitter b = Splitter.on('=').limit(2);
    private static LocaleLanguage c = new LocaleLanguage();
    private Map d = Maps.newHashMap();

    public LocaleLanguage() {
        try {
            InputStream inputstream = LocaleLanguage.class.getResourceAsStream("/assets/minecraft/lang/en_US.lang");
            Iterator iterator = IOUtils.readLines(inputstream, Charsets.UTF_8).iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                if (!s.isEmpty() && s.charAt(0) != 35) {
                    String[] astring = (String[]) Iterables.toArray(b.split(s), String.class);

                    if (astring != null && astring.length == 2) {
                        String s1 = astring[0];
                        String s2 = a.matcher(astring[1]).replaceAll("%$1s");

                        this.d.put(s1, s2);
                    }
                }
            }
        } catch (IOException ioexception) {
            ;
        }
    }

    static LocaleLanguage a() {
        return c;
    }

    public synchronized String a(String s) {
        return this.c(s);
    }

    public synchronized String a(String s, Object... aobject) {
        String s1 = this.c(s);

        try {
            return String.format(s1, aobject);
        } catch (IllegalFormatException illegalformatexception) {
            return "Format error: " + s1;
        }
    }

    private String c(String s) {
        String s1 = (String) this.d.get(s);

        return s1 == null ? s : s1;
    }

    public synchronized boolean b(String s) {
        return this.d.containsKey(s);
    }
}
