package org.tinyrune.ui.impl;

import org.tinyrune.Client;
import org.tinyrune.ui.UIButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class UpdateButton extends UIButton {

    private Client client;

    public UpdateButton(Client client) {
        super(client);
        this.setButtonIcon("update.png");
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.client.updateRunescape();
    }
}
