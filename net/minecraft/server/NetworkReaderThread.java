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
                if (NetworkManager.a(this.a)) {
                    if (!NetworkManager.b(this.a)) {
                        NetworkManager.c(this.a);
                        continue;
                    }

                    flag = false;
                    break;
                }

                flag = false;
                break;
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
