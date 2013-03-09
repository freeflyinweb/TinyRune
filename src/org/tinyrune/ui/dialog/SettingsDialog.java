package org.tinyrune.ui.dialog;

import org.tinyrune.Client;
import org.tinyrune.util.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog implements ActionListener {

    JCheckBox toolbar, forceDownload;
    JTextField url;
    private Client client;

    public SettingsDialog(Client client) {
        super();
        this.client = client;
        this.setTitle("Settings");
        this.setLayout(new GridLayout(0,2));
        this.setResizable(false);

        JLabel toolbarLabel = new JLabel("Enable Toolbar");
        toolbar = new JCheckBox("", null, Boolean.valueOf(Settings.get("toolbar")));

        JLabel forceDownloadLabel = new JLabel("Enable Force Download");
        forceDownload = new JCheckBox("", null, Boolean.valueOf(Settings.get("force-download")));

        JLabel screenShotDirectoryLabel = new JLabel("Screenshot Directory");
        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(this);
        JLabel urlLabel = new JLabel("Runescape URL");
        url = new JTextField(Settings.get("url"), 25);
        JButton save = new JButton("Save");
        save.addActionListener(this);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        this.add(toolbarLabel);
        this.add(toolbar);
        this.add(forceDownloadLabel);
        this.add(forceDownload);
        this.add(screenShotDirectoryLabel);
        this.add(browseButton);
        this.add(urlLabel);
        this.add(url);
        this.add(save);
        this.add(cancel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Cancel")) {
            this.setVisible(false);
        } else if(e.getActionCommand().equals("Browse")) {
            JFileChooser chooser = new JFileChooser(Settings.get("screenshot-directory")) ;
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = chooser.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                Settings.set("screenshot-directory", chooser.getSelectedFile().getAbsolutePath());
            }
        } else if(e.getActionCommand().equals("Save")) {
            Settings.set("toolbar", String.valueOf(this.toolbar.isSelected()));
            Settings.set("force-download", String.valueOf(this.forceDownload.isSelected()));
            Settings.set("url", this.url.getText());
            Settings.save();
            JOptionPane.showMessageDialog(client, "Settings saved!");
            this.setVisible(false);
        }
    }
}
