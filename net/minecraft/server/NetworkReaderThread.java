package net.minecraft.server;

class NetworkReaderThread extends Thread {

    final NetworkManager a;

    NetworkReaderThread(NetworkManager networkmanager, String s) {
        super(s);
        this.a = networkmanager;
    }

    public void run() {
        Object object = NetworkManager.a;

        synchronized (NetworkManager.a) {
            ++NetworkManager.b;
        }

        while (true) {
            boolean flag = false;

            try {
                flag = true;
                if (!NetworkManager.a(this.a)) {
                    flag = false;
                    break;
                }

                if (NetworkManager.b(this.a)) {
                    flag = false;
                    break;
                }

                for (int i = 100; i > 0; --i) {
                    if (!NetworkManager.c(this.a)) {
                        i = 0;
                    }
                }

                try {
                    sleep(1L);
                } catch (InterruptedException interruptedexception) {
                    ;
                }
            } finally {
                if (flag) {
                    Object object1 = NetworkManager.a;

                    synchronized (NetworkManager.a) {
                        --NetworkManager.b;
                    }
                }
            }
        }

        object = NetworkManager.a;
        synchronized (NetworkManager.a) {
            --NetworkManager.b;
        }
    }
}
