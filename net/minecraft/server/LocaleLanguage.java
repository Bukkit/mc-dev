package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.IllegalFormatException;
import java.util.Properties;
import java.util.TreeMap;

public class LocaleLanguage {

    private static LocaleLanguage a = new LocaleLanguage("en_US");
    private Properties b = new Properties();
    private TreeMap c;
    private TreeMap d = new TreeMap();
    private String e;
    private boolean f;

    public LocaleLanguage(String s) {
        this.e();
        this.a(s, false);
    }

    public static LocaleLanguage a() {
        return a;
    }

    private void e() {
        TreeMap treemap = new TreeMap();

        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(LocaleLanguage.class.getResourceAsStream("/lang/languages.txt"), "UTF-8"));

            for (String s = bufferedreader.readLine(); s != null; s = bufferedreader.readLine()) {
                String[] astring = s.trim().split("=");

                if (astring != null && astring.length == 2) {
                    treemap.put(astring[0], astring[1]);
                }
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
            return;
        }

        this.c = treemap;
        this.c.put("en_US", "English (US)");
    }

    public TreeMap b() {
        return this.c;
    }

    private void a(Properties properties, String s) {
        BufferedReader bufferedreader = null;

        if (this.d.containsKey(s)) {
            bufferedreader = new BufferedReader(new FileReader((File) this.d.get(s)));
        } else {
            bufferedreader = new BufferedReader(new InputStreamReader(LocaleLanguage.class.getResourceAsStream("/lang/" + s + ".lang"), "UTF-8"));
        }

        for (String s1 = bufferedreader.readLine(); s1 != null; s1 = bufferedreader.readLine()) {
            s1 = s1.trim();
            if (!s1.startsWith("#")) {
                String[] astring = s1.split("=");

                if (astring != null && astring.length == 2) {
                    properties.setProperty(astring[0], astring[1]);
                }
            }
        }
    }

    public synchronized void a(String s, boolean flag) {
        if (flag || !s.equals(this.e)) {
            Properties properties = new Properties();

            try {
                this.a(properties, "en_US");
            } catch (IOException ioexception) {
                ;
            }

            this.f = false;
            if (!"en_US".equals(s)) {
                try {
                    this.a(properties, s);
                    Enumeration enumeration = properties.propertyNames();

                    while (enumeration.hasMoreElements() && !this.f) {
                        Object object = enumeration.nextElement();
                        Object object1 = properties.get(object);

                        if (object1 != null) {
                            String s1 = object1.toString();

                            for (int i = 0; i < s1.length(); ++i) {
                                if (s1.charAt(i) >= 256) {
                                    this.f = true;
                                    break;
                                }
                            }
                        }
                    }
                } catch (IOException ioexception1) {
                    ioexception1.printStackTrace();
                    return;
                }
            }

            this.e = s;
            this.b = properties;
        }
    }

    public synchronized String a(String s) {
        return this.b.getProperty(s, s);
    }

    public synchronized String a(String s, Object... aobject) {
        String s1 = this.b.getProperty(s, s);

        try {
            return String.format(s1, aobject);
        } catch (IllegalFormatException illegalformatexception) {
            return "Format error: " + s1;
        }
    }

    public synchronized boolean b(String s) {
        return this.b.containsKey(s);
    }

    public synchronized String c(String s) {
        return this.b.getProperty(s + ".name", "");
    }
}
