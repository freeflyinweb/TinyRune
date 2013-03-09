package org.tinyrune.ui.button;

import org.tinyrune.Client;
import org.tinyrune.ui.UIButton;
import org.tinyrune.util.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotButton extends UIButton {

    private Client client;
    private Robot robot = null;

    public ScreenShotButton(Client client) {
        super(client);
        this.setButtonIcon("camera.png");
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
            DateFormat date = new SimpleDateFormat("MM-dd-yyyy HHmmss");
            String fileName = "screenshot " + date.format(new Date())+ ".png";
            String directory = Settings.get("screenshot-directory");
            if(directory != "")
                ImageIO.write(image, "png", new File(directory, fileName));
            else
                ImageIO.write(image, "png", new File(fileName));
            JOptionPane.showMessageDialog(this.client, fileName + " was saved");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
