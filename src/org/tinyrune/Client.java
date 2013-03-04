package org.tinyrune;

import org.tinyrune.canvas.LoaderCanvas;
import org.tinyrune.rs.RunescapeClient;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class Client extends JFrame {
    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        super("TinyRune");
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        JPanel game = new JPanel(new BorderLayout());
        LoaderCanvas canvas = new LoaderCanvas();
        canvas.setText("Loading Runescape... Please Wait");
        game.add(canvas, BorderLayout.CENTER);
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

}
