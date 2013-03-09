package org.tinyrune.ui.button;

import org.tinyrune.Client;
import org.tinyrune.plugin.Plugin;
import org.tinyrune.plugin.PluginClassLoader;
import org.tinyrune.ui.UIButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class PluginButton extends UIButton {

    private Client client;
    private PluginClassLoader classLoader;
    private File pluginsFolder;
    private String[] fileNames;
    private HashMap<String, Plugin> activePlugins = new HashMap<String, Plugin>();

    public PluginButton(Client client) {
        super(client);
        this.setButtonIcon("box.png");
        this.client = client;
        this.classLoader = new PluginClassLoader();
        this.pluginsFolder = new File("plugins");
        this.activePlugins = new HashMap<String, Plugin>();
        File[] files = this.pluginsFolder.listFiles();
        this.fileNames = new String[files.length];
        int i = 0;
        for(File file : files) {
            this.fileNames[i] = files[i].getName();
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = (String)JOptionPane.showInputDialog(this.client, "Pick a plugin to load", "Plugin Selector", JOptionPane.QUESTION_MESSAGE, null, this.fileNames, "");
        if(name != null) {
            if(this.activePlugins.get(name) == null) {
                Class<?> c = this.classLoader.loadClass(name);
                try {
                    Plugin plugin = (Plugin)c.getDeclaredConstructor(Client.class).newInstance(this.client);
                    this.activePlugins.put(name, plugin);
                    plugin.execute();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            } else {
                this.activePlugins.get(name).getGUI().setVisible(true);
            }
        }
    }
}
