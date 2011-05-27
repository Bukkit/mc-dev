package net.minecraft.server;

class NetworkWriterThread extends Thread {

    final NetworkManager a;

    NetworkWriterThread(NetworkManager networkmanager, String s) {
        super(s);
        this.a = networkmanager;
    }

    public void run() {
        Object object = NetworkManager.a;

        synchronized (NetworkManager.a) {
            ++NetworkManager.c;
        }

        while (true) {
            boolean flag = false;

            try {
                flag = true;
                if (!NetworkManager.a(this.a)) {
                    flag = false;
                    break;
                }

                NetworkManager.d(this.a);
            } finally {
                if (flag) {
                    Object object1 = NetworkManager.a;

                    synchronized (NetworkManager.a) {
                        --NetworkManager.c;
                    }
                }
            }
        }

        object = NetworkManager.a;
        synchronized (NetworkManager.a) {
            --NetworkManager.c;
        }
    }
}
