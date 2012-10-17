package net.minecraft.server;

class GameRuleValue {

    private String a;
    private boolean b;
    private int c;
    private double d;

    public GameRuleValue(String s) {
        this.a(s);
    }

    public void a(String s) {
        this.a = s;
        this.b = Boolean.parseBoolean(s);

        try {
            this.c = Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            ;
        }

        try {
            this.d = Double.parseDouble(s);
        } catch (NumberFormatException numberformatexception1) {
            ;
        }
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }
}
