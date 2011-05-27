package net.minecraft.server;

import java.io.File;
import java.util.regex.Matcher;

class ChunkFile implements Comparable {

    private final File a;
    private final int b;
    private final int c;

    public ChunkFile(File file1) {
        this.a = file1;
        Matcher matcher = ChunkFilenameFilter.a.matcher(file1.getName());

        if (matcher.matches()) {
            this.b = Integer.parseInt(matcher.group(1), 36);
            this.c = Integer.parseInt(matcher.group(2), 36);
        } else {
            this.b = 0;
            this.c = 0;
        }
    }

    public int compareTo(ChunkFile chunkfile) {
        int i = this.b >> 5;
        int j = chunkfile.b >> 5;

        if (i == j) {
            int k = this.c >> 5;
            int l = chunkfile.c >> 5;

            return k - l;
        } else {
            return i - j;
        }
    }

    public File a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}
