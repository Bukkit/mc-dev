package net.minecraft.server;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ChunkFileFilter implements FileFilter {

    public static final Pattern a = Pattern.compile("[0-9a-z]|([0-9a-z][0-9a-z])");

    private ChunkFileFilter() {}

    public boolean accept(File file1) {
        if (file1.isDirectory()) {
            Matcher matcher = a.matcher(file1.getName());

            return matcher.matches();
        } else {
            return false;
        }
    }

    ChunkFileFilter(EmptyClass2 emptyclass2) {
        this();
    }
}
