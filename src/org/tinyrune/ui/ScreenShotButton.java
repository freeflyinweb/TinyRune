package org.tinyrune.ui;

import org.tinyrune.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenShotButton extends JButton implements ActionListener {

    private static final ImageIcon icon = new ImageIcon("resources/camera.png");
    private Client client;
    private Robot robot = null;

    public ScreenShotButton(Client client) {
        super(icon);
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
