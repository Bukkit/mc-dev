package net.minecraft.server;

class NetworkReaderThread extends Thread {

    final NetworkManager a;

    NetworkReaderThread(NetworkManager networkmanager, String s) {
        super(s);
        this.a = networkmanager;
    }

    public void run() {
        NetworkManager.a.getAndIncrement();

        try {
            while (NetworkManager.a(this.a) && !NetworkManager.b(this.a)) {
                while (true) {
                    if (!NetworkManager.c(this.a)) {
                        try {
                            sleep(2L);
                        } catch (InterruptedException interruptedexception) {
                            ;
                        }
                    }
                }
            }
        } finally {
            NetworkManager.a.getAndDecrement();
        }
    }
}
