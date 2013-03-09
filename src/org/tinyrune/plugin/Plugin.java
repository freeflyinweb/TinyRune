package org.tinyrune.plugin;

import org.tinyrune.Client;

import javax.swing.*;

public abstract class Plugin extends SwingWorker<Void, Void> {

    protected Client client;

    public Plugin(Client client) {
        this.client = client;
    }

    public abstract String getName();
    public abstract String getManifest();
    public abstract JDialog getGUI();
}
