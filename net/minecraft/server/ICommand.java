package net.minecraft.server;

import java.util.List;

public interface ICommand extends Comparable {

    String c();

    String c(ICommandListener icommandlistener);

    List b();

    void b(ICommandListener icommandlistener, String[] astring);

    boolean a(ICommandListener icommandlistener);

    List a(ICommandListener icommandlistener, String[] astring);

    boolean a(String[] astring, int i);
}
