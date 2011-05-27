package net.minecraft.server;

class NetworkMasterThread extends Thread {

    final NetworkManager a;

    NetworkMasterThread(NetworkManager networkmanager) {
        this.a = networkmanager;
    }

    public void run() {
        try {
            Thread.sleep(5000L);
            if (NetworkManager.e(this.a).isAlive()) {
                try {
                    NetworkManager.e(this.a).stop();
                } catch (Throwable throwable) {
                    ;
                }
            }

            if (NetworkManager.f(this.a).isAlive()) {
                try {
                    NetworkManager.f(this.a).stop();
                } catch (Throwable throwable1) {
                    ;
                }
            }
        } catch (InterruptedException interruptedexception) {
            interruptedexception.printStackTrace();
        }
    }
}
