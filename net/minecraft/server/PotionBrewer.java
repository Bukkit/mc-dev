package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PotionBrewer {

    public static final String a = null;
    public static final String b;
    public static final String c = "+0-1-2-3&4-4+13";
    public static final String d;
    public static final String e;
    public static final String f;
    public static final String g;
    public static final String h;
    public static final String i;
    public static final String j;
    public static final String k;
    public static final String l;
    public static final String m;
    private static final HashMap effectDurations = new HashMap();
    private static final HashMap effectAmplifiers = new HashMap();
    private static final HashMap p;
    private static final String[] appearances;

    public static boolean a(int i, int j) {
        return (i & 1 << j) != 0;
    }

    private static int c(int i, int j) {
        return a(i, j) ? 1 : 0;
    }

    private static int d(int i, int j) {
        return a(i, j) ? 0 : 1;
    }

    public static int a(int i) {
        return a(i, 5, 4, 3, 2, 1);
    }

    public static int a(Collection collection) {
        int i = 3694022;

        if (collection != null && !collection.isEmpty()) {
            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            float f3 = 0.0F;
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                MobEffect mobeffect = (MobEffect) iterator.next();
                int j = MobEffectList.byId[mobeffect.getEffectId()].j();

                for (int k = 0; k <= mobeffect.getAmplifier(); ++k) {
                    f += (float) (j >> 16 & 255) / 255.0F;
                    f1 += (float) (j >> 8 & 255) / 255.0F;
                    f2 += (float) (j >> 0 & 255) / 255.0F;
                    ++f3;
                }
            }

            f = f / f3 * 255.0F;
            f1 = f1 / f3 * 255.0F;
            f2 = f2 / f3 * 255.0F;
            return (int) f << 16 | (int) f1 << 8 | (int) f2;
        } else {
            return i;
        }
    }

    public static boolean b(Collection collection) {
        Iterator iterator = collection.iterator();

        MobEffect mobeffect;

        do {
            if (!iterator.hasNext()) {
                return true;
            }

            mobeffect = (MobEffect) iterator.next();
        } while (mobeffect.isAmbient());

        return false;
    }

    public static String c(int i) {
        int j = a(i);

        return appearances[j];
    }

    private static int a(boolean flag, boolean flag1, boolean flag2, int i, int j, int k, int l) {
        int i1 = 0;

        if (flag) {
            i1 = d(l, j);
        } else if (i != -1) {
            if (i == 0 && h(l) == j) {
                i1 = 1;
            } else if (i == 1 && h(l) > j) {
                i1 = 1;
            } else if (i == 2 && h(l) < j) {
                i1 = 1;
            }
        } else {
            i1 = c(l, j);
        }

        if (flag1) {
            i1 *= k;
        }

        if (flag2) {
            i1 *= -1;
        }

        return i1;
    }

    private static int h(int i) {
        int j;

        for (j = 0; i > 0; ++j) {
            i &= i - 1;
        }

        return j;
    }

    private static int a(String s, int i, int j, int k) {
        if (i < s.length() && j >= 0 && i < j) {
            int l = s.indexOf(124, i);
            int i1;
            int j1;

            if (l >= 0 && l < j) {
                i1 = a(s, i, l - 1, k);
                if (i1 > 0) {
                    return i1;
                } else {
                    j1 = a(s, l + 1, j, k);
                    return j1 > 0 ? j1 : 0;
                }
            } else {
                i1 = s.indexOf(38, i);
                if (i1 >= 0 && i1 < j) {
                    j1 = a(s, i, i1 - 1, k);
                    if (j1 <= 0) {
                        return 0;
                    } else {
                        int k1 = a(s, i1 + 1, j, k);

                        return k1 <= 0 ? 0 : (j1 > k1 ? j1 : k1);
                    }
                } else {
                    boolean flag = false;
                    boolean flag1 = false;
                    boolean flag2 = false;
                    boolean flag3 = false;
                    boolean flag4 = false;
                    byte b0 = -1;
                    int l1 = 0;
                    int i2 = 0;
                    int j2 = 0;

                    for (int k2 = i; k2 < j; ++k2) {
                        char c0 = s.charAt(k2);

                        if (c0 >= 48 && c0 <= 57) {
                            if (flag) {
                                i2 = c0 - 48;
                                flag1 = true;
                            } else {
                                l1 *= 10;
                                l1 += c0 - 48;
                                flag2 = true;
                            }
                        } else if (c0 == 42) {
                            flag = true;
                        } else if (c0 == 33) {
                            if (flag2) {
                                j2 += a(flag3, flag1, flag4, b0, l1, i2, k);
                                flag3 = false;
                                flag4 = false;
                                flag = false;
                                flag1 = false;
                                flag2 = false;
                                i2 = 0;
                                l1 = 0;
                                b0 = -1;
                            }

                            flag3 = true;
                        } else if (c0 == 45) {
                            if (flag2) {
                                j2 += a(flag3, flag1, flag4, b0, l1, i2, k);
                                flag3 = false;
                                flag4 = false;
                                flag = false;
                                flag1 = false;
                                flag2 = false;
                                i2 = 0;
                                l1 = 0;
                                b0 = -1;
                            }

                            flag4 = true;
                        } else if (c0 != 61 && c0 != 60 && c0 != 62) {
                            if (c0 == 43 && flag2) {
                                j2 += a(flag3, flag1, flag4, b0, l1, i2, k);
                                flag3 = false;
                                flag4 = false;
                                flag = false;
                                flag1 = false;
                                flag2 = false;
                                i2 = 0;
                                l1 = 0;
                                b0 = -1;
                            }
                        } else {
                            if (flag2) {
                                j2 += a(flag3, flag1, flag4, b0, l1, i2, k);
                                flag3 = false;
                                flag4 = false;
                                flag = false;
                                flag1 = false;
                                flag2 = false;
                                i2 = 0;
                                l1 = 0;
                                b0 = -1;
                            }

                            if (c0 == 61) {
                                b0 = 0;
                            } else if (c0 == 60) {
                                b0 = 2;
                            } else if (c0 == 62) {
                                b0 = 1;
                            }
                        }
                    }

                    if (flag2) {
                        j2 += a(flag3, flag1, flag4, b0, l1, i2, k);
                    }

                    return j2;
                }
            }
        } else {
            return 0;
        }
    }

    public static List getEffects(int i, boolean flag) {
        ArrayList arraylist = null;
        MobEffectList[] amobeffectlist = MobEffectList.byId;
        int j = amobeffectlist.length;

        for (int k = 0; k < j; ++k) {
            MobEffectList mobeffectlist = amobeffectlist[k];

            if (mobeffectlist != null && (!mobeffectlist.i() || flag)) {
                String s = (String) effectDurations.get(Integer.valueOf(mobeffectlist.getId()));

                if (s != null) {
                    int l = a(s, 0, s.length(), i);

                    if (l > 0) {
                        int i1 = 0;
                        String s1 = (String) effectAmplifiers.get(Integer.valueOf(mobeffectlist.getId()));

                        if (s1 != null) {
                            i1 = a(s1, 0, s1.length(), i);
                            if (i1 < 0) {
                                i1 = 0;
                            }
                        }

                        if (mobeffectlist.isInstant()) {
                            l = 1;
                        } else {
                            l = 1200 * (l * 3 + (l - 1) * 2);
                            l >>= i1;
                            l = (int) Math.round((double) l * mobeffectlist.getDurationModifier());
                            if ((i & 16384) != 0) {
                                l = (int) Math.round((double) l * 0.75D + 0.5D);
                            }
                        }

                        if (arraylist == null) {
                            arraylist = new ArrayList();
                        }

                        MobEffect mobeffect = new MobEffect(mobeffectlist.getId(), l, i1);

                        if ((i & 16384) != 0) {
                            mobeffect.setSplash(true);
                        }

                        arraylist.add(mobeffect);
                    }
                }
            }
        }

        return arraylist;
    }

    private static int a(int i, int j, boolean flag, boolean flag1, boolean flag2) {
        if (flag2) {
            if (!a(i, j)) {
                return 0;
            }
        } else if (flag) {
            i &= ~(1 << j);
        } else if (flag1) {
            if ((i & 1 << j) == 0) {
                i |= 1 << j;
            } else {
                i &= ~(1 << j);
            }
        } else {
            i |= 1 << j;
        }

        return i;
    }

    public static int a(int i, String s) {
        byte b0 = 0;
        int j = s.length();
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        int k = 0;

        for (int l = b0; l < j; ++l) {
            char c0 = s.charAt(l);

            if (c0 >= 48 && c0 <= 57) {
                k *= 10;
                k += c0 - 48;
                flag = true;
            } else if (c0 == 33) {
                if (flag) {
                    i = a(i, k, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    k = 0;
                }

                flag1 = true;
            } else if (c0 == 45) {
                if (flag) {
                    i = a(i, k, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    k = 0;
                }

                flag2 = true;
            } else if (c0 == 43) {
                if (flag) {
                    i = a(i, k, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    k = 0;
                }
            } else if (c0 == 38) {
                if (flag) {
                    i = a(i, k, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    k = 0;
                }

                flag3 = true;
            }
        }

        if (flag) {
            i = a(i, k, flag2, flag1, flag3);
        }

        return i & 32767;
    }

    public static int a(int i, int j, int k, int l, int i1, int j1) {
        return (a(i, j) ? 16 : 0) | (a(i, k) ? 8 : 0) | (a(i, l) ? 4 : 0) | (a(i, i1) ? 2 : 0) | (a(i, j1) ? 1 : 0);
    }

    static {
        effectDurations.put(Integer.valueOf(MobEffectList.REGENERATION.getId()), "0 & !1 & !2 & !3 & 0+6");
        b = "-0+1-2-3&4-4+13";
        effectDurations.put(Integer.valueOf(MobEffectList.FASTER_MOVEMENT.getId()), "!0 & 1 & !2 & !3 & 1+6");
        h = "+0+1-2-3&4-4+13";
        effectDurations.put(Integer.valueOf(MobEffectList.FIRE_RESISTANCE.getId()), "0 & 1 & !2 & !3 & 0+6");
        f = "+0-1+2-3&4-4+13";
        effectDurations.put(Integer.valueOf(MobEffectList.HEAL.getId()), "0 & !1 & 2 & !3");
        d = "-0-1+2-3&4-4+13";
        effectDurations.put(Integer.valueOf(MobEffectList.POISON.getId()), "!0 & !1 & 2 & !3 & 2+6");
        e = "-0+3-4+13";
        effectDurations.put(Integer.valueOf(MobEffectList.WEAKNESS.getId()), "!0 & !1 & !2 & 3 & 3+6");
        effectDurations.put(Integer.valueOf(MobEffectList.HARM.getId()), "!0 & !1 & 2 & 3");
        effectDurations.put(Integer.valueOf(MobEffectList.SLOWER_MOVEMENT.getId()), "!0 & 1 & !2 & 3 & 3+6");
        g = "+0-1-2+3&4-4+13";
        effectDurations.put(Integer.valueOf(MobEffectList.INCREASE_DAMAGE.getId()), "0 & !1 & !2 & 3 & 3+6");
        l = "-0+1+2-3+13&4-4";
        effectDurations.put(Integer.valueOf(MobEffectList.NIGHT_VISION.getId()), "!0 & 1 & 2 & !3 & 2+6");
        effectDurations.put(Integer.valueOf(MobEffectList.INVISIBILITY.getId()), "!0 & 1 & 2 & 3 & 2+6");
        m = "+0-1+2+3+13&4-4";
        effectDurations.put(Integer.valueOf(MobEffectList.WATER_BREATHING.getId()), "0 & !1 & 2 & 3 & 2+6");
        j = "+5-6-7";
        effectAmplifiers.put(Integer.valueOf(MobEffectList.FASTER_MOVEMENT.getId()), "5");
        effectAmplifiers.put(Integer.valueOf(MobEffectList.FASTER_DIG.getId()), "5");
        effectAmplifiers.put(Integer.valueOf(MobEffectList.INCREASE_DAMAGE.getId()), "5");
        effectAmplifiers.put(Integer.valueOf(MobEffectList.REGENERATION.getId()), "5");
        effectAmplifiers.put(Integer.valueOf(MobEffectList.HARM.getId()), "5");
        effectAmplifiers.put(Integer.valueOf(MobEffectList.HEAL.getId()), "5");
        effectAmplifiers.put(Integer.valueOf(MobEffectList.RESISTANCE.getId()), "5");
        effectAmplifiers.put(Integer.valueOf(MobEffectList.POISON.getId()), "5");
        i = "-5+6-7";
        k = "+14&13-13";
        p = new HashMap();
        appearances = new String[] { "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky"};
    }
}
