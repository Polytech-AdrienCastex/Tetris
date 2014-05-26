/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import modele.Runtime;

/**
 *
 * @author p1002239
 */
public class GameWindow extends JFrame implements KeyListener, Observer
{
    public GameWindow(Runtime runtime)
    {
        super();
        
        this.runtime = runtime;
        
        build();
        
        addListeners();
        
        setObservers();
        
        shown();
    }
    private Grid grid;
    private Score score;
    private PieceQueue queue;
    
    private Sound bgSound;
    
    private Runtime runtime;
    
    private void build()
    {
        setTitle("Tetris");
        setSize(600, 400);
        
        ImagePanel img = new ImagePanel("H:\\ProjetInfo\\Images\\TetrisBg.jpg");
        this.setContentPane(img);
        
        this.setLayout(new GridBagLayout());
        grid = new Grid();
        score = new Score();
        queue = new PieceQueue();
        /*
        JPanel panel_1 = new JPanel();
        JPanel panel_2 = new JPanel();
        JPanel panel_3 = new JPanel();
        panel_3.setSize(0, 0);
        
        createGamePanel(panel_1, grid, score, queue);
        createGamePanel(panel_2, new Grid(), new Score(), new PieceQueue());
        
        this.add(panel_1, BorderLayout.WEST);
        this.add(panel_2, BorderLayout.EAST);
        this.add(panel_3, BorderLayout.CENTER);*/
        
        createGamePanel(this, grid, score, queue, 0);
        createGamePanel(this, new Grid(), new Score(), new PieceQueue(), 3);
        
        bgSound = new Sound("H:\\ProjetInfo\\Images\\bsound.mp3");
        bgSound.setLoop();
    }
    
    private void createGamePanel(Container cont, Grid grid, Score score, PieceQueue queue, int x_offset)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.weightx = 1.0;
        
        /*
        JPanel state = new State();
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.weighty = 0.1;
        c.weightx = 0.1;
        this.add(state, c);
        
        JPanel panel = new HiddenPanel();
        panel.setSize(400, 100);
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.weighty = 0.8;
        c.weightx = 0.1;
        this.add(panel, c);
        */
        
        grid.setSize(400, 100);
        c.gridx = 1 + x_offset;
        c.gridy = 0;
        c.gridheight = 3;
        c.weighty = 1.0;
        c.weightx = 0.6;
        cont.add(grid, c);
        
        c.gridx = 2 + x_offset;
        c.gridy = 2;
        c.gridheight = 1;
        c.weighty = 0.1;
        c.weightx = 0.1;
        cont.add(score, c);
        
        queue.setSize(100, 0);
        c.gridx = 0 + x_offset;
        c.gridy = 0;
        c.gridheight = 1;
        c.weighty = 0.9;
        c.weightx = 0.3;
        cont.add(queue, c);
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
        runtime.setObservers(grid, score, queue, this);
    }
    
    public void shown()
    {
        bgSound.play();
        bgSound.setVolume(0.1);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(arg instanceof modele.Runtime.TerminatedEventArg)
        {
            bgSound.stop();
            return;
        }
        if(arg instanceof modele.Runtime.PauseResumeEventArg)
        {
            modele.Runtime.PauseResumeEventArg argv = (modele.Runtime.PauseResumeEventArg)arg;
            
            if(argv.getPause())
                bgSound.pause();
            else
                bgSound.resume();
            
            return;
        }
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Keys">
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
            
            // Pause/Resume
            case 80: // P
                runtime.PauseResume();
                break;
            
            default:
                if(System.getProperty("Debug") != null)
                    System.out.println("KeyCode [" + e.getKeyChar() + " : " + e.getKeyCode() + "]");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    { }
    //</editor-fold>
}
