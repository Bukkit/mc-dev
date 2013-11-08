package net.minecraft.server;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MojangsonParser {

    private static final Logger a = LogManager.getLogger();

    public static NBTBase a(String s) {
        s = s.trim();
        int i = b(s);

        if (i != 1) {
            throw new MojangsonParseException("Encountered multiple top tags, only one expected");
        } else {
            MojangsonTypeParser mojangsontypeparser = null;

            if (s.startsWith("{")) {
                mojangsontypeparser = a("tag", s);
            } else {
                mojangsontypeparser = a(b(s, false), c(s, false));
            }

            return mojangsontypeparser.a();
        }
    }

    static int b(String s) {
        int i = 0;
        boolean flag = false;
        Stack stack = new Stack();

        for (int j = 0; j < s.length(); ++j) {
            char c0 = s.charAt(j);

            if (c0 == 34) {
                if (j > 0 && s.charAt(j - 1) == 92) {
                    if (!flag) {
                        throw new MojangsonParseException("Illegal use of \\\": " + s);
                    }
                } else {
                    flag = !flag;
                }
            } else if (!flag) {
                if (c0 != 123 && c0 != 91) {
                    if (c0 == 125 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 123)) {
                        throw new MojangsonParseException("Unbalanced curly brackets {}: " + s);
                    }

                    if (c0 == 93 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 91)) {
                        throw new MojangsonParseException("Unbalanced square brackets []: " + s);
                    }
                } else {
                    if (stack.isEmpty()) {
                        ++i;
                    }

                    stack.push(Character.valueOf(c0));
                }
            }
        }

        if (flag) {
            throw new MojangsonParseException("Unbalanced quotation: " + s);
        } else if (!stack.isEmpty()) {
            throw new MojangsonParseException("Unbalanced brackets: " + s);
        } else if (i == 0 && !s.isEmpty()) {
            return 1;
        } else {
            return i;
        }
    }

    static MojangsonTypeParser a(String s, String s1) {
        s1 = s1.trim();
        b(s1);
        String s2;
        String s3;
        String s4;
        char c0;

        if (s1.startsWith("{")) {
            if (!s1.endsWith("}")) {
                throw new MojangsonParseException("Unable to locate ending bracket for: " + s1);
            } else {
                s1 = s1.substring(1, s1.length() - 1);
                MojangsonCompoundParser mojangsoncompoundparser = new MojangsonCompoundParser(s);

                while (s1.length() > 0) {
                    s2 = a(s1, false);
                    if (s2.length() > 0) {
                        s3 = b(s2, false);
                        s4 = c(s2, false);
                        mojangsoncompoundparser.b.add(a(s3, s4));
                        if (s1.length() < s2.length() + 1) {
                            break;
                        }

                        c0 = s1.charAt(s2.length());
                        if (c0 != 44 && c0 != 123 && c0 != 125 && c0 != 91 && c0 != 93) {
                            throw new MojangsonParseException("Unexpected token \'" + c0 + "\' at: " + s1.substring(s2.length()));
                        }

                        s1 = s1.substring(s2.length() + 1);
                    }
                }

                return mojangsoncompoundparser;
            }
        } else if (s1.startsWith("[") && !s1.matches("\\[[-\\d|,\\s]+\\]")) {
            if (!s1.endsWith("]")) {
                throw new MojangsonParseException("Unable to locate ending bracket for: " + s1);
            } else {
                s1 = s1.substring(1, s1.length() - 1);
                MojangsonListParser mojangsonlistparser = new MojangsonListParser(s);

                while (s1.length() > 0) {
                    s2 = a(s1, true);
                    if (s2.length() > 0) {
                        s3 = b(s2, true);
                        s4 = c(s2, true);
                        mojangsonlistparser.b.add(a(s3, s4));
                        if (s1.length() < s2.length() + 1) {
                            break;
                        }

                        c0 = s1.charAt(s2.length());
                        if (c0 != 44 && c0 != 123 && c0 != 125 && c0 != 91 && c0 != 93) {
                            throw new MojangsonParseException("Unexpected token \'" + c0 + "\' at: " + s1.substring(s2.length()));
                        }

                        s1 = s1.substring(s2.length() + 1);
                    } else {
                        a.debug(s1);
                    }
                }

                return mojangsonlistparser;
            }
        } else {
            return new MojangsonPrimitiveParser(s, s1);
        }
    }

    private static String a(String s, boolean flag) {
        int i = a(s, ':');

        if (i < 0 && !flag) {
            throw new MojangsonParseException("Unable to locate name/value separator for string: " + s);
        } else {
            int j = a(s, ',');

            if (j >= 0 && j < i && !flag) {
                throw new MojangsonParseException("Name error at: " + s);
            } else {
                if (flag && (i < 0 || i > j)) {
                    i = -1;
                }

                Stack stack = new Stack();
                int k = i + 1;
                boolean flag1 = false;
                boolean flag2 = false;
                boolean flag3 = false;

                for (int l = 0; k < s.length(); ++k) {
                    char c0 = s.charAt(k);

                    if (c0 == 34) {
                        if (k > 0 && s.charAt(k - 1) == 92) {
                            if (!flag1) {
                                throw new MojangsonParseException("Illegal use of \\\": " + s);
                            }
                        } else {
                            flag1 = !flag1;
                            if (flag1 && !flag3) {
                                flag2 = true;
                            }

                            if (!flag1) {
                                l = k;
                            }
                        }
                    } else if (!flag1) {
                        if (c0 != 123 && c0 != 91) {
                            if (c0 == 125 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 123)) {
                                throw new MojangsonParseException("Unbalanced curly brackets {}: " + s);
                            }

                            if (c0 == 93 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 91)) {
                                throw new MojangsonParseException("Unbalanced square brackets []: " + s);
                            }

                            if (c0 == 44 && stack.isEmpty()) {
                                return s.substring(0, k);
                            }
                        } else {
                            stack.push(Character.valueOf(c0));
                        }
                    }

                    if (!Character.isWhitespace(c0)) {
                        if (!flag1 && flag2 && l != k) {
                            return s.substring(0, l + 1);
                        }

                        flag3 = true;
                    }
                }

                return s.substring(0, k);
            }
        }
    }

    private static String b(String s, boolean flag) {
        if (flag) {
            s = s.trim();
            if (s.startsWith("{") || s.startsWith("[")) {
                return "";
            }
        }

        int i = s.indexOf(58);

        if (i < 0) {
            if (flag) {
                return "";
            } else {
                throw new MojangsonParseException("Unable to locate name/value separator for string: " + s);
            }
        } else {
            return s.substring(0, i).trim();
        }
    }

    private static String c(String s, boolean flag) {
        if (flag) {
            s = s.trim();
            if (s.startsWith("{") || s.startsWith("[")) {
                return s;
            }
        }

        int i = s.indexOf(58);

        if (i < 0) {
            if (flag) {
                return s;
            } else {
                throw new MojangsonParseException("Unable to locate name/value separator for string: " + s);
            }
        } else {
            return s.substring(i + 1).trim();
        }
    }

    private static int a(String s, char c0) {
        int i = 0;

        for (boolean flag = false; i < s.length(); ++i) {
            char c1 = s.charAt(i);

            if (c1 == 34) {
                if (i <= 0 || s.charAt(i - 1) != 92) {
                    flag = !flag;
                }
            } else if (!flag) {
                if (c1 == c0) {
                    return i;
                }

                if (c1 == 123 || c1 == 91) {
                    return -1;
                }
            }
        }

        return -1;
    }
}
