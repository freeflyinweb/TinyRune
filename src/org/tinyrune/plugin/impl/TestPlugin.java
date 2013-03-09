package org.tinyrune.plugin.impl;

import org.tinyrune.Client;
import org.tinyrune.plugin.Plugin;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestPlugin extends Plugin implements KeyListener {

    private JDialog jDialog;

    public TestPlugin(Client client) {
        super(client);
        jDialog = new JDialog();
        jDialog.setTitle("Test");
        jDialog.add(new JLabel("Test plugin!"));
        jDialog.pack();
    }

    public String getName() {
        return "Test Plugin v0.0.1";
    }

    public String getManifest() {
        return "freeflyinweb v0.0.1";
    }

    @Override
    public JDialog getGUI() {
        return jDialog;
    }

    @Override
    protected Void doInBackground() throws Exception {
        /*Applet applet = (Applet)client.getGameManager().getSelectedComponent();
        Canvas canvas = (Canvas)applet.getComponent(0);
        canvas.requestFocus();
        KeyEvent keyEventPress = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'd');
        KeyEvent keyEventRelease = new KeyEvent(canvas, KeyEvent.KEY_RELEASED, System.currentTimeMillis()+100, 0, KeyEvent.VK_D, 'd');
        canvas.dispatchEvent(keyEventPress);
        canvas.dispatchEvent(keyEventRelease);
        this.getGUI().setVisible(true);*/
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
}
