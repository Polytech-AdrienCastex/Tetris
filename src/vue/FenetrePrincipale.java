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
    private PieceQueue queue;
    
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
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.weightx = 1.0;

        
        grid = new Grid();
        grid.setSize(400, 100);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.weighty = 1.0;
        c.weightx = 0.6;
        this.add(grid, c);
        
        score = new Score();
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.weighty = 0.1;
        c.weightx = 0.1;
        this.add(score, c);
        
        queue = new PieceQueue();
        queue.setSize(100, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.9;
        c.weightx = 0.3;
        this.add(queue, c);
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
        runtime.setObservers(grid, score, queue);
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
