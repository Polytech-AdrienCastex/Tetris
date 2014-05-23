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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
        
        ImagePanel img = new ImagePanel("H:\\ProjetInfo\\Images\\TetrisBg.jpg");
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
        
        playSound("bsound.mp3");
    }
    
    public static synchronized void playSound(final String url) {
    new Thread(new Runnable() {
    // The wrapper thread is unnecessary, unless it blocks on the
    // Clip finishing; see comments.
      public void run() {
        try {
            new JFXPanel();
            
            String fullUrl = "file:///" + new File(url).getAbsolutePath();
            fullUrl = Paths.get(new File(url).getAbsolutePath()).toUri().toString();
            System.out.println(fullUrl);
            
            Media hit = new Media(fullUrl);
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
            
            /*
            System.out.print("1");
          Clip clip = AudioSystem.getClip();
            System.out.print("2");
            FileInputStream s = new FileInputStream(url);
            System.out.print("3");
          AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(s));
            System.out.print("4");
          clip.open(inputStream);
          clip.start(); */
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
    }).start();
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
