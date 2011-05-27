package net.minecraft.server;

class NetworkMasterThread extends Thread {

    final NetworkManager a;

    NetworkMasterThread(NetworkManager networkmanager) {
        this.a = networkmanager;
    }

    public void run() {
        try {
            Thread.sleep(5000L);
            if (NetworkManager.g(this.a).isAlive()) {
                try {
                    NetworkManager.g(this.a).stop();
                } catch (Throwable throwable) {
                    ;
                }
            }

            if (NetworkManager.h(this.a).isAlive()) {
                try {
                    NetworkManager.h(this.a).stop();
                } catch (Throwable throwable1) {
                    ;
                }
            }
        } catch (InterruptedException interruptedexception) {
            interruptedexception.printStackTrace();
        }
    }
}
