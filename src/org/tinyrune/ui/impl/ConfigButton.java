package org.tinyrune.ui.impl;

import org.tinyrune.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class ConfigButton extends JButton implements ActionListener {

    private Client client;

    public ConfigButton(Client client) {
        super();
        ImageIcon icon = null;
        InputStream is = getClass().getClassLoader().getResourceAsStream("resources/gear.png");
        if(is == null) {
            icon = new ImageIcon("resources/gear.png");
        } else {
            try {
                icon = new ImageIcon(ImageIO.read(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.setIcon(icon);
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(client, "Settings coming soon!");
    }
}
