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
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import modele.Runtime;

/**
 *
 * @author p1002239
 */
public class FenetrePrincipale extends JFrame implements KeyListener
{
    private static Color BG_COLOR = Color.BLACK;
    
    public FenetrePrincipale(Runtime runtime)
    {
        super();
        
        this.runtime = runtime;
        
        build();
        
        addListeners();
        
        setObservers();
        
    }
    private Grid grid;
    private Score score;
    private Runtime runtime;
    
    private void build()
    {
        setTitle("Tetris");
        setSize(600, 400);
        
        ImagePanel img = new ImagePanel("F:\\ProjetInfo\\Images\\TetrisBg.jpg");
        this.setContentPane(img);
        
        //setLayout(new BorderLayout());
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.CENTER;

        
        grid = new Grid();
        grid.setSize(400, 100);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 400;
        c.weighty = 100;
        c.gridheight = 3;
        this.add(grid, c);
        
        score = new Score();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 2;
        c.gridy = 0;
        c.ipadx = 100;
        c.gridheight = 1;
        this.add(score, c);
        
        /*
        Border borderGrid = BorderFactory.createEmptyBorder(0, 130, 0, 0);
        
        grid = new Grid();
        grid.setSize(400, 100);
        grid.setBorder(borderGrid);
        this.add(grid, BorderLayout.CENTER);
        
        Border borderScore = BorderFactory.createEmptyBorder(0, 70, 0, 0);
        
        score = new Score();
        score.setBorder(borderScore);
        this.add(score, BorderLayout.EAST);
        */
    }
    
    private void addListeners()
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
        
        this.addKeyListener(this);
    }
    
    public void setObservers()
    {
        runtime.setObservers(grid, score);
    }
    
    class ImagePanel extends JComponent
    {
        public ImagePanel(String image)
        {
            try
            {
                this.image = ImageIO.read(new File(image));
            }
            catch (IOException ex)
            {
                this.image = null;
            }
        }
        private Image image;
        
        @Override
        protected void paintComponent(Graphics g)
        {
            if(this.image != null)
                g.drawImage(image, 0, 0, null);
            
            super.paintComponent(g);
        }
    }
    
    
    //<editor-fold desc="Keys">
    @Override
    public void keyTyped(KeyEvent e)
    { }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            // Left
            case 81: // Q
                runtime.MoveLeft();
                break;
                
            // Right
            case 68: // D
                runtime.MoveRight();
                break;
                
            // Direct bottom
            case 83: // S
                runtime.PushBottom();
                break;
            
            // Rotate
            case 32: // Space
                runtime.Rotate();
                break;
            
            // Show/Hide menu
            case 27: // Escape
                runtime.Pause();
                //runtime.Resume();
                break;
            
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    { }
    //</editor-fold>
}
