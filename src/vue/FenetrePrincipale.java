/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import modele.Runtime;

/**
 *
 * @author p1002239
 */
public class FenetrePrincipale extends JFrame
{
    private static Color BG_COLOR = Color.BLACK;
    
    public FenetrePrincipale(Runtime runtime)
    {
        super();
        
        build();
        
        addListeners(runtime);
        
        setObservers(runtime);
    }
    private Grid grid;
    private Score score;
    
    private void build()
    {
        setTitle("Tetris");
        setSize(600, 400);
        
        setLayout(new BorderLayout());
        
        Border borderGrid = BorderFactory.createEmptyBorder(0, 100, 0, 0);
        
        grid = new Grid();
        grid.setSize(400, 100);
        grid.setBorder(borderGrid);
        this.add(grid, BorderLayout.CENTER);
        
        Border borderScore = BorderFactory.createEmptyBorder(0, 100, 0, 0);
        
        score = new Score();
        score.setBorder(borderScore);
        this.add(score, BorderLayout.EAST);
    }
    
    private void addListeners(Runtime runtime)
    {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
        
        this.addKeyListener(runtime);
    }
    
    public void setObservers(Runtime runtime)
    {
        runtime.setObservers(grid, score);
    }
}
