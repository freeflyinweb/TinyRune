package org.tinyrune.ui;

import org.tinyrune.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigButton extends JButton implements ActionListener {

    private static final ImageIcon icon = new ImageIcon("resources/gear.png");

    public ConfigButton() {
        super(icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
