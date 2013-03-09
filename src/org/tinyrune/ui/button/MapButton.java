package org.tinyrune.ui.button;

import org.tinyrune.Client;
import org.tinyrune.ui.UIButton;
import org.tinyrune.ui.dialog.MapDialog;

import java.awt.event.ActionEvent;

public class MapButton extends UIButton {

    private Client client;
    private MapDialog mapDialog;

    public MapButton(Client client) {
        super(client);
        this.setButtonIcon("map.png");
        this.client = client;
        mapDialog = new MapDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mapDialog.setVisible(true);
    }
}
