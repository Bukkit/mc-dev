package net.minecraft.server;

import java.util.List;

public interface ICommand extends Comparable {

    String b();

    String a(ICommandListener icommandlistener);

    List a();

    void b(ICommandListener icommandlistener, String[] astring);

    boolean b(ICommandListener icommandlistener);

    List a(ICommandListener icommandlistener, String[] astring);
}
