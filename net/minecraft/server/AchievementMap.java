package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AchievementMap {

    public static AchievementMap a = new AchievementMap();
    private Map b = new HashMap();

    private AchievementMap() {
        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(AchievementMap.class.getResourceAsStream("/achievement/map.txt")));

            String s;

            while ((s = bufferedreader.readLine()) != null) {
                String[] astring = s.split(",");
                int i = Integer.parseInt(astring[0]);

                this.b.put(Integer.valueOf(i), astring[1]);
            }

            bufferedreader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String a(int i) {
        return (String) a.b.get(Integer.valueOf(i));
    }
}
