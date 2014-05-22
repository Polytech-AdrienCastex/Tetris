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
public class HiddenPanel extends JPanel
{
    protected static final Color TRANSPARENT_BG_COLOR = new Color(0,0,0,0);
    
    public HiddenPanel()
    {
        super();
        this.setBackground(TRANSPARENT_BG_COLOR);
    }
}
