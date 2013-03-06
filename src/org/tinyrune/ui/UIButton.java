package org.tinyrune.ui;

import org.tinyrune.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public abstract class UIButton extends JButton implements ActionListener {

    protected Client client;

    public UIButton(Client client) {
        super();
        this.client = client;
    }

    protected void setButtonIcon(String iconName) {
        ImageIcon icon = null;
        InputStream is = getClass().getClassLoader().getResourceAsStream("resources/" + iconName);
        if(is == null) {
            icon = new ImageIcon("resources/" + iconName);
        } else {
            try {
                icon = new ImageIcon(ImageIO.read(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.setIcon(icon);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
