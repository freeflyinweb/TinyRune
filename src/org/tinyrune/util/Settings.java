package org.tinyrune.util;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Settings {
    private static HashMap<String, String> map = new HashMap<String, String>();

    public static void read() {
        try {
            File settingsFile = new File("settings.conf");
            BufferedReader reader = null;
            if(settingsFile.exists()) {
                reader = new BufferedReader(new FileReader("settings.conf"));
            } else {
                InputStream is = Settings.class.getClassLoader().getResourceAsStream("settings.conf");
                reader = new BufferedReader(new InputStreamReader(is));
            }

            String line;
            String[] splits;
            while((line = reader.readLine()) != null) {
                if(line.contains("=")) {
                    splits = line.split("=");
                    map.put(splits[0], splits[1]);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("settings.conf"));
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry pair = (Map.Entry)iterator.next();
                writer.write(pair.getKey() + "=" + pair.getValue());
                iterator.remove();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return map.get(key);
    }


    public static void set(String key, String value) {
        map.remove(key);
        map.put(key, value);
    }

}
