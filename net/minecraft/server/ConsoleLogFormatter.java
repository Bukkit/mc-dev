package net.minecraft.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class ConsoleLogFormatter extends Formatter {

    private SimpleDateFormat b;

    final ConsoleLogManager a;

    private ConsoleLogFormatter(ConsoleLogManager consolelogmanager) {
        this.a = consolelogmanager;
        this.b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public String format(LogRecord logrecord) {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append(this.b.format(Long.valueOf(logrecord.getMillis())));
        if (ConsoleLogManager.a(this.a) != null) {
            stringbuilder.append(ConsoleLogManager.a(this.a));
        }

        stringbuilder.append(" [").append(logrecord.getLevel().getName()).append("] ");
        stringbuilder.append(this.formatMessage(logrecord));
        stringbuilder.append('\n');
        Throwable throwable = logrecord.getThrown();

        if (throwable != null) {
            StringWriter stringwriter = new StringWriter();

            throwable.printStackTrace(new PrintWriter(stringwriter));
            stringbuilder.append(stringwriter.toString());
        }

        return stringbuilder.toString();
    }

    ConsoleLogFormatter(ConsoleLogManager consolelogmanager, EmptyClass3 emptyclass3) {
        this(consolelogmanager);
    }
}
