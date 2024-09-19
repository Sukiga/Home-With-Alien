package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InventoryInsidePanel extends JPanel {

    private BufferedImage backgroundImage;

    // Effects: set background Image as MarsBackGround
    public InventoryInsidePanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/main/ui/Pics/InventoryInside.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Effects: draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
