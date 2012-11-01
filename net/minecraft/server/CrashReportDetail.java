package net.minecraft.server;

class CrashReportDetail {

    private final String a;
    private final String b;

    public CrashReportDetail(String s, Object object) {
        this.a = s;
        if (object == null) {
            this.b = "~~NULL~~";
        } else if (object instanceof Throwable) {
            Throwable throwable = (Throwable) object;

            this.b = "~~ERROR~~ " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
        } else {
            this.b = object.toString();
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
