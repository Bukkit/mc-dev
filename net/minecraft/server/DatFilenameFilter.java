package net.minecraft.server;

import java.io.File;
import java.io.FilenameFilter;

final class DatFilenameFilter implements FilenameFilter {

    DatFilenameFilter() {}

    public boolean accept(File file1, String s) {
        return s.endsWith(".dat");
    }
}
