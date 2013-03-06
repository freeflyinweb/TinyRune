package org.tinyrune.ui.impl;

import org.tinyrune.Client;
import org.tinyrune.ui.UIButton;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigButton extends UIButton {

    private Client client;

    public ConfigButton(Client client) {
        super(client);
        this.setButtonIcon("gear.png");
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(client, "Settings coming soon!");
    }
}
