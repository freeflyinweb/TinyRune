package org.tinyrune;

import org.tinyrune.canvas.LoaderCanvas;
import org.tinyrune.rs.RunescapeClient;
import org.tinyrune.ui.ConfigButton;
import org.tinyrune.ui.ScreenShotButton;
import org.tinyrune.util.Settings;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.File;

public class Client extends JFrame {

    private JPanel game;
    private LoaderCanvas canvas;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        super("TinyRune");
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        Settings.read();
        this.game = new JPanel(new BorderLayout());
        canvas = new LoaderCanvas();
        canvas.setText("Runescape Downloading... Please Wait");

        JToolBar toolbar = new JToolBar();
        ConfigButton configButton = new ConfigButton();
        ScreenShotButton screenShotButton = new ScreenShotButton(this);

        toolbar.add(configButton);
        toolbar.add(screenShotButton);

        configButton.addActionListener(configButton);
        screenShotButton.addActionListener(screenShotButton);

        this.game.add(canvas, BorderLayout.CENTER);
        this.getContentPane().add(toolbar, BorderLayout.PAGE_START);
        this.getContentPane().add(game, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

        RunescapeClient rsClient = new RunescapeClient();
        Applet applet = rsClient.getApplet();
        applet.init();
        applet.start();

        game.remove(canvas);
        game.add(applet, BorderLayout.CENTER);
        this.pack();
    }

    public LoaderCanvas getCanvas() {
        return this.canvas;
    }

}
