/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author p1002239
 */
public class Case extends JPanel
{
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    
    public Case()
    {
        super();
        super.setSize(WIDTH, HEIGHT);
        super.setBackground(Color.WHITE);
    }
    
    public void SetColor(Color color)
    {
        super.setBackground(color);
    }
}
