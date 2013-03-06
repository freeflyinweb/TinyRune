package org.tinyrune;

import org.tinyrune.canvas.LoaderCanvas;
import org.tinyrune.net.RunescapeJarDownloader;
import org.tinyrune.rs.RunescapeClient;
import org.tinyrune.ui.ConfigButton;
import org.tinyrune.ui.ScreenShotButton;
import org.tinyrune.ui.UpdateButton;
import org.tinyrune.util.Settings;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.File;

public class Client extends JFrame {

    private JPanel game;
    private LoaderCanvas canvas;
    private File runescape;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        super("TinyRune");
        this.runescape = new File("runescape.jar");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Settings.read();
        this.game = new JPanel(new BorderLayout());
        this.canvas = new LoaderCanvas();
        this.canvas.setText("Runescape Downloading... Please Wait");

        JToolBar toolbar = new JToolBar();
        ConfigButton configButton = new ConfigButton(this);
        UpdateButton updateButton = new UpdateButton(this);
        ScreenShotButton screenShotButton = new ScreenShotButton(this);

        toolbar.add(configButton);
        toolbar.add(updateButton);
        toolbar.add(screenShotButton);


        configButton.addActionListener(configButton);
        updateButton.addActionListener(updateButton);
        screenShotButton.addActionListener(screenShotButton);

        this.game.add(canvas, BorderLayout.CENTER);
        if(Settings.get("toolbar").equals("true"))
            this.getContentPane().add(toolbar, BorderLayout.PAGE_START);
        this.getContentPane().add(game, BorderLayout.CENTER);
        this.pack();
        if(this.runescape.exists() && Settings.get("force-download").equals("false"))
            this.runRunescape();
        else
            this.updateRunescape();
        this.setVisible(true);
    }

    private void toolbar() {

    }

    public void runRunescape() {
        RunescapeClient rsClient = new RunescapeClient();
        Applet applet = rsClient.getApplet();
        applet.init();
        applet.start();
        this.game.remove(canvas);
        this.game.add(applet, BorderLayout.CENTER);
        this.pack();
    }

    public void updateRunescape() {
        RunescapeJarDownloader runescapeJarDownloader = new RunescapeJarDownloader(this);
        runescapeJarDownloader.execute();
    }

    public LoaderCanvas getCanvas() {
        return this.canvas;
    }

}
