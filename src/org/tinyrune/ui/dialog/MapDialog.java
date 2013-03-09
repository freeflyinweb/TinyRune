package org.tinyrune.ui.dialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MapDialog extends JDialog {
    public MapDialog() {
        super();
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout());
        this.setTitle("Runescape Map");
        this.setResizable(true);
        MapImagePanel imagePanel = new MapImagePanel();
        JScrollPane scrollPane = new JScrollPane(imagePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.pack();
    }

    class MapImagePanel extends JPanel {

        private BufferedImage mapImage;

        public MapImagePanel() {
            super();
            this.mapImage = getMapImage();
            this.setPreferredSize(new Dimension(3968, 3584));
        }

        private BufferedImage getMapImage() {
            InputStream is = getClass().getClassLoader().getResourceAsStream("resources/rsmap.jpg");
            if(is == null) {
                try {
                    return ImageIO.read(new File("resources/rsmap.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    return ImageIO.read(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.mapImage, 0, 0, this);
        }
    }
}
