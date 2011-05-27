package net.minecraft.server;

import java.io.IOException;

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
                if (NetworkManager.e(this.a)) {
                    NetworkManager.a(this.a, false);

                    try {
                        NetworkManager.f(this.a).flush();
                    } catch (IOException ioexception) {
                        ioexception.printStackTrace();
                    }
                }
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
