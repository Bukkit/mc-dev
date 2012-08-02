package net.minecraft.server;

public class ReportedException extends RuntimeException {

    private final CrashReport a;

    public ReportedException(CrashReport crashreport) {
        this.a = crashreport;
    }

    public CrashReport a() {
        return this.a;
    }

    public Throwable getCause() {
        return this.a.b();
    }

    public String getMessage() {
        return this.a.a();
    }
}
