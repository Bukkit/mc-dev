package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileIOThread implements Runnable {

    public static final FileIOThread a = new FileIOThread();
    private List b = Collections.synchronizedList(new ArrayList());
    private volatile long c = 0L;
    private volatile long d = 0L;
    private volatile boolean e = false;

    private FileIOThread() {
        Thread thread = new Thread(this, "File IO Thread");

        thread.setPriority(1);
        thread.start();
    }

    public void run() {
        this.b();
    }

    private void b() {
        for (int i = 0; i < this.b.size(); ++i) {
            IAsyncChunkSaver iasyncchunksaver = (IAsyncChunkSaver) this.b.get(i);
            boolean flag = iasyncchunksaver.c();

            if (!flag) {
                this.b.remove(i--);
                ++this.d;
            }

            try {
                if (!this.e) {
                    Thread.sleep(10L);
                } else {
                    Thread.sleep(0L);
                }
            } catch (InterruptedException interruptedexception) {
                interruptedexception.printStackTrace();
            }
        }

        if (this.b.isEmpty()) {
            try {
                Thread.sleep(25L);
            } catch (InterruptedException interruptedexception1) {
                interruptedexception1.printStackTrace();
            }
        }
    }

    public void a(IAsyncChunkSaver iasyncchunksaver) {
        if (!this.b.contains(iasyncchunksaver)) {
            ++this.c;
            this.b.add(iasyncchunksaver);
        }
    }

    public void a() {
        this.e = true;

        while (this.c != this.d) {
            Thread.sleep(10L);
        }

        this.e = false;
    }
}
