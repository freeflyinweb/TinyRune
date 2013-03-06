package org.tinyrune.ui.impl;

import org.tinyrune.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ScreenShotButton extends JButton implements ActionListener {

    private Client client;
    private Robot robot = null;

    public ScreenShotButton(Client client) {
        super();
        ImageIcon icon = null;
        InputStream is = getClass().getClassLoader().getResourceAsStream("resources/camera.png");
        if(is == null) {
            icon = new ImageIcon("resources/camera.png");
        } else {
            try {
                icon = new ImageIcon(ImageIO.read(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.setIcon(icon);
        this.client = client;
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = client.getLocationOnScreen().x;
        int y = client.getLocationOnScreen().y;
        int x2 = client.getWidth();
        int y2 = client.getHeight();
        BufferedImage image = this.robot.createScreenCapture(new Rectangle(x,y,x2,y2));
        try {
            ImageIO.write(image, "png", new File("screenshot.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
