package net.minecraft.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.Timer;

public class GuiStatsComponent extends JComponent {

    private int[] a = new int[256];
    private int b = 0;
    private String[] c = new String[10];

    public GuiStatsComponent() {
        this.setPreferredSize(new Dimension(256, 196));
        this.setMinimumSize(new Dimension(256, 196));
        this.setMaximumSize(new Dimension(256, 196));
        (new Timer(500, new GuiStatsListener(this))).start();
        this.setBackground(Color.BLACK);
    }

    private void a() {
        long i = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.gc();
        this.c[0] = "Memory use: " + i / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.c[1] = "Threads: " + NetworkManager.b + " + " + NetworkManager.c;
        this.a[this.b++ & 255] = (int) (i * 100L / Runtime.getRuntime().maxMemory());
        this.repaint();
    }

    public void paint(Graphics graphics) {
        graphics.setColor(new Color(16777215));
        graphics.fillRect(0, 0, 256, 192);

        int i;

        for (i = 0; i < 256; ++i) {
            int j = this.a[i + this.b & 255];

            graphics.setColor(new Color(j + 28 << 16));
            graphics.fillRect(i, 100 - j, 1, j);
        }

        graphics.setColor(Color.BLACK);

        for (i = 0; i < this.c.length; ++i) {
            String s = this.c[i];

            if (s != null) {
                graphics.drawString(s, 32, 116 + i * 16);
            }
        }
    }

    static void a(GuiStatsComponent guistatscomponent) {
        guistatscomponent.a();
    }
}
