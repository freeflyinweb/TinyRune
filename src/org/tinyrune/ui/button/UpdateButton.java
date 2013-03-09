package org.tinyrune.ui.button;

import org.tinyrune.Client;
import org.tinyrune.ui.UIButton;

import java.awt.event.ActionEvent;

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
