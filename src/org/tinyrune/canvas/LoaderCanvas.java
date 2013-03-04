package org.tinyrune.canvas;

import javax.swing.*;
import java.awt.*;

public class LoaderCanvas extends JPanel {
    private String text;

    public LoaderCanvas() {
        this.text = "";
        this.setPreferredSize(new Dimension(765, 503));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        int x = this.getWidth();
        int y = this.getHeight();
        int w = g.getFontMetrics().stringWidth(text)/2;
        int h = g.getFontMetrics().getDescent();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, x, y);
        g2d.setPaint(Color.white);
        g2d.drawString(text, x /2 - w, y /2 + h);
    }

    public void setText(String text) {
        this.text = text;
    }
}
