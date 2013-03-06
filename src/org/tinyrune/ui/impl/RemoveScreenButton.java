package org.tinyrune.ui.impl;

import org.tinyrune.Client;
import org.tinyrune.ui.UIButton;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveScreenButton extends UIButton {

    private Client client;

    public RemoveScreenButton(Client client) {
        super(client);
        this.setButtonIcon("cross.png");
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int reply = JOptionPane.showConfirmDialog(this.client, "Are you sure you would like to close this tab?");
        if(reply == JOptionPane.OK_OPTION)
            this.client.closeCurrentRunescape();
    }
}
