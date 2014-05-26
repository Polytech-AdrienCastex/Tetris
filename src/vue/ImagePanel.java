/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Adrien
 */
public class ImagePanel extends JComponent
{
    public ImagePanel(String image)
    {
        Image img;
        try
        {
            img = ImageIO.read(new File(image));
        }
        catch (IOException ex)
        {
            img = null;
        }

        this.image = img;
    }
    private final Image image;

    @Override
    protected void paintComponent(Graphics g)
    {
        if(this.image != null)
            g.drawImage(image, 0, 0, this.getSize().width, this.getSize().height, null);

        super.paintComponent(g);
    }
}
