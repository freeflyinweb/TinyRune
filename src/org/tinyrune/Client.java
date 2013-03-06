package org.tinyrune;

import org.tinyrune.net.RunescapeJarDownloader;
import org.tinyrune.rs.RunescapeClient;
import org.tinyrune.ui.*;
import org.tinyrune.ui.impl.*;
import org.tinyrune.util.Settings;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Client extends JFrame {

    private JTabbedPane gameManager;
    private File runescape;
    private ArrayList<UIButton> buttons;
    private JToolBar toolbar;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        super("TinyRune");
        this.buttons = new ArrayList<UIButton>();
        this.runescape = new File("runescape.jar");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Settings.read();
        this.gameManager = new JTabbedPane();
        JPanel autoSize = new JPanel();
        autoSize.setPreferredSize(new Dimension(765, 503));
        this.gameManager.add(autoSize);

        this.toolbar = new JToolBar();
        this.addToolbarButton(new ConfigButton(this));
        this.addToolbarButton(new UpdateButton(this));
        this.addToolbarButton(new ScreenShotButton(this));
        this.toolbar.add(Box.createGlue());
        this.addToolbarButton(new AddScreenButton(this));
        this.addToolbarButton(new RemoveScreenButton(this));

        if(Settings.get("toolbar").equals("true"))
            this.getContentPane().add(this.toolbar, BorderLayout.PAGE_START);
        this.getContentPane().add(gameManager, BorderLayout.CENTER);
        this.pack();
        if(this.runescape.exists() && Settings.get("force-download").equals("false"))
            this.addRunescapeClient();
        else
            this.updateRunescape();
        this.gameManager.remove(autoSize);
        this.setVisible(true);
    }

    private void addToolbarButton(UIButton button) {
        this.buttons.add(button);
        this.toolbar.add(button);
        button.addActionListener(button);
    }


    public void addRunescapeClient() {
        String name = JOptionPane.showInputDialog(this, "Tab name?");
        if(name != null) {
            RunescapeClient rsClient = new RunescapeClient();
            Applet applet = rsClient.getApplet();
            applet.init();
            applet.start();
            this.gameManager.addTab(name, applet);
            this.pack();
        }
    }

    public void updateRunescape() {
        RunescapeJarDownloader runescapeJarDownloader = new RunescapeJarDownloader(this);
        runescapeJarDownloader.execute();
    }

    public void closeCurrentRunescape() {
        Component component = this.gameManager.getSelectedComponent();
        Applet applet = (Applet)component;
        applet.stop();
        this.gameManager.remove(component);
    }

    public JTabbedPane getGameManager() {
        return this.gameManager;
    }

}
