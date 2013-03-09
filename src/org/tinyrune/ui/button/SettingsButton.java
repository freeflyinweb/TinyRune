package org.tinyrune.ui.button;

import org.tinyrune.Client;
import org.tinyrune.ui.UIButton;
import org.tinyrune.ui.dialog.SettingsDialog;

import java.awt.event.ActionEvent;

public class SettingsButton extends UIButton {

    private Client client;
    private SettingsDialog settingsDialog;

    public SettingsButton(Client client) {
        super(client);
        this.setButtonIcon("gear.png");
        this.client = client;
        this.settingsDialog = new SettingsDialog(client);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.settingsDialog.setVisible(true);
    }
}
