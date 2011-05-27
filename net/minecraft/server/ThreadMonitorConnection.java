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
                NetworkManager.f(this.a).interrupt();
                this.a.a("Connection closed");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
