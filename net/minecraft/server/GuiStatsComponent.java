package net.minecraft.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import javax.swing.JComponent;
import javax.swing.Timer;

public class GuiStatsComponent extends JComponent {

    private static final DecimalFormat a = new DecimalFormat("########0.000");
    private int[] b = new int[256];
    private int c = 0;
    private String[] d = new String[10];
    private final MinecraftServer e;

    public GuiStatsComponent(MinecraftServer minecraftserver) {
        this.e = minecraftserver;
        this.setPreferredSize(new Dimension(256, 226));
        this.setMinimumSize(new Dimension(256, 226));
        this.setMaximumSize(new Dimension(256, 226));
        (new Timer(500, new GuiStatsListener(this))).start();
        this.setBackground(Color.BLACK);
    }

    private void a() {
        long i = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.gc();
        this.d[0] = "Memory use: " + i / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.d[1] = "Threads: " + NetworkManager.b + " + " + NetworkManager.c;
        this.d[2] = "Avg tick: " + a.format(this.a(this.e.f) * 1.0E-6D) + " ms";

        for (int j = 0; j < this.e.worldServer.length; ++j) {
            this.d[3 + j] = "Lvl " + j + " tick: " + a.format(this.a(this.e.g[j]) * 1.0E-6D) + " ms";
        }

        this.b[this.c++ & 255] = (int) (i * 100L / Runtime.getRuntime().maxMemory());
        this.repaint();
    }

    private double a(long[] along) {
        long i = 0L;

        for (int j = 0; j < along.length; ++j) {
            i += along[j];
        }

        return (double) i / (double) along.length;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(new Color(16777215));
        graphics.fillRect(0, 0, 256, 226);

        int i;

        for (i = 0; i < 256; ++i) {
            int j = this.b[i + this.c & 255];

            graphics.setColor(new Color(j + 28 << 16));
            graphics.fillRect(i, 100 - j, 1, j);
        }

        graphics.setColor(Color.BLACK);

        for (i = 0; i < this.d.length; ++i) {
            String s = this.d[i];

            if (s != null) {
                graphics.drawString(s, 32, 116 + i * 16);
            }
        }
    }

    static void a(GuiStatsComponent guistatscomponent) {
        guistatscomponent.a();
    }
}
