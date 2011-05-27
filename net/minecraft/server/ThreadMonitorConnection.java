package net.minecraft.server;

class ThreadMonitorConnection extends Thread {

    final NetworkManager a;

    ThreadMonitorConnection(NetworkManager networkmanager) {
        this.a = networkmanager;
    }

    public void run() {
        try {
            Thread.sleep(2000L);
            if (NetworkManager.a(this.a)) {
                NetworkManager.h(this.a).interrupt();
                this.a.a("disconnect.closed", new Object[0]);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
