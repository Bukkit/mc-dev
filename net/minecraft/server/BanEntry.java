package net.minecraft.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BanEntry {

    private static final Logger b = LogManager.getLogger();
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private final String c;
    private Date d = new Date();
    private String e = "(Unknown)";
    private Date f;
    private String g = "Banned by an operator.";

    public BanEntry(String s) {
        this.c = s;
    }

    public String getName() {
        return this.c;
    }

    public Date getCreated() {
        return this.d;
    }

    public void setCreated(Date date) {
        this.d = date != null ? date : new Date();
    }

    public String getSource() {
        return this.e;
    }

    public void setSource(String s) {
        this.e = s;
    }

    public Date getExpires() {
        return this.f;
    }

    public void setExpires(Date date) {
        this.f = date;
    }

    public boolean hasExpired() {
        return this.f == null ? false : this.f.before(new Date());
    }

    public String getReason() {
        return this.g;
    }

    public void setReason(String s) {
        this.g = s;
    }

    public String g() {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append(this.getName());
        stringbuilder.append("|");
        stringbuilder.append(a.format(this.getCreated()));
        stringbuilder.append("|");
        stringbuilder.append(this.getSource());
        stringbuilder.append("|");
        stringbuilder.append(this.getExpires() == null ? "Forever" : a.format(this.getExpires()));
        stringbuilder.append("|");
        stringbuilder.append(this.getReason());
        return stringbuilder.toString();
    }

    public static BanEntry c(String s) {
        if (s.trim().length() < 2) {
            return null;
        } else {
            String[] astring = s.trim().split(Pattern.quote("|"), 5);
            BanEntry banentry = new BanEntry(astring[0].trim());
            byte b0 = 0;
            int i = astring.length;
            int j = b0 + 1;

            if (i <= j) {
                return banentry;
            } else {
                try {
                    banentry.setCreated(a.parse(astring[j].trim()));
                } catch (ParseException parseexception) {
                    b.warn("Could not read creation date format for ban entry \'" + banentry.getName() + "\' (was: \'" + astring[j] + "\')", parseexception);
                }

                i = astring.length;
                ++j;
                if (i <= j) {
                    return banentry;
                } else {
                    banentry.setSource(astring[j].trim());
                    i = astring.length;
                    ++j;
                    if (i <= j) {
                        return banentry;
                    } else {
                        try {
                            String s1 = astring[j].trim();

                            if (!s1.equalsIgnoreCase("Forever") && s1.length() > 0) {
                                banentry.setExpires(a.parse(s1));
                            }
                        } catch (ParseException parseexception1) {
                            b.warn("Could not read expiry date format for ban entry \'" + banentry.getName() + "\' (was: \'" + astring[j] + "\')", parseexception1);
                        }

                        i = astring.length;
                        ++j;
                        if (i <= j) {
                            return banentry;
                        } else {
                            banentry.setReason(astring[j].trim());
                            return banentry;
                        }
                    }
                }
            }
        }
    }
}
