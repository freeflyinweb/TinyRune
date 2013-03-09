package org.tinyrune.plugin;

import java.io.*;
import java.util.HashMap;

public class PluginClassLoader extends ClassLoader {

    private File pluginFolder;
    private HashMap<String, byte[]> classes;
    private HashMap<String, Class<?>> definedClasses;

    public PluginClassLoader() {
        super();
        this.classes = new HashMap<String, byte[]>();
        this.definedClasses = new HashMap<String, Class<?>>();
        this.pluginFolder = new File("plugins");
        if(!pluginFolder.exists())
            pluginFolder.mkdir();
        this.loadClassData();
    }

    public Class<?> loadClass(String name) {
        if(this.classes.get(name) == null) {
            try {
                return super.loadClass(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        Class<?> c;
        if((c = this.definedClasses.get(name)) != null)
            return c;
        System.out.println("Loading plugin: " + name);
        byte[] buffer = this.classes.get(name);
        String className = "org.tinyrune.plugin.impl." + name.replace(".class", "");
        c = super.defineClass(className, buffer, 0, buffer.length);
        this.definedClasses.put(name, c);
        return c;
    }

    private void loadClassData() {
        for(File file : this.pluginFolder.listFiles())  {
            String name = file.getName();
            if(name.endsWith(".class")) {
                try {
                    DataInputStream dis = new DataInputStream(new FileInputStream(file));
                    int length = (int)file.length();
                    byte[] buffer = new byte[length];
                    dis.readFully(buffer);
                    dis.close();
                    this.classes.put(file.getName(), buffer);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
