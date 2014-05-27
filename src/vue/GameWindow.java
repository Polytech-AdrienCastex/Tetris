/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import modele.general.RuntimeManager;
import modele.tetris.TetrisRuntime;

/**
 *
 * @author p1002239
 */
public class GameWindow extends JFrame implements KeyListener, MenuListener, Observer
{
    public GameWindow(RuntimeManager runtime)
    {
        this(runtime, 5);
    }
    public GameWindow(RuntimeManager runtime, int nbPerLine)
    {
        super();
        
        this.runtime = runtime;
        
        build(nbPerLine);
        
        addListeners();
        
        setObservers();
        
        shown();
    }
    private Grid[] grids;
    private Score[] scores;
    private PieceQueue[] queues;
    
    private Sound bgSound;
    
    private static final int[] keysGeneral = new int[]
    {
        27, // Escape
        80  // P
    };
    private static final int[][] keysMap = new int[][]
    {
        new int[]
        {
            81, // Q
            68, // D
            83, // S
            32  // Space
        },
        new int[]
        {
            100, // 4
            102, // 6
            101, // 5
            96   // 0
        }
    };
    
    private RuntimeManager<TetrisRuntime> runtime;
    
    private void build(int nbPerLine)
    {
        setTitle("Tetris");
        int x = runtime.getNbPlayers();
        if(x > nbPerLine)
            x = nbPerLine;
        setSize(600 * x, 400 * (int)((runtime.getNbPlayers() + nbPerLine - 1) / nbPerLine));
        
        ImagePanel img = new ImagePanel("F:\\ProjetInfo\\Images\\TetrisBg.jpg");
        this.setContentPane(img);

        this.setLayout(new GridBagLayout());
        
        
        grids = new Grid[runtime.getNbPlayers()];
        scores = new Score[runtime.getNbPlayers()];
        queues = new PieceQueue[runtime.getNbPlayers()];
        
        for(int i = 0; i < runtime.getNbPlayers(); i++)
        {
            grids[i] = new Grid();
            scores[i] = new Score();
            queues[i] = new PieceQueue();
            
            createGamePanel(this, grids[i], scores[i], queues[i], 3 * (i % nbPerLine), 3 * (i / nbPerLine));
        }
        
        bgSound = new Sound("H:\\ProjetInfo\\Images\\bsound.mp3");
        bgSound.setLoop();
    }
    
    private void createGamePanel(Container cont, Grid grid, Score score, PieceQueue queue, int x_offset, int y_offset)
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
        c.gridy = 0 + y_offset;
        c.gridheight = 3;
        c.weighty = 1.0;
        c.weightx = 0.6;
        cont.add(grid, c);
        
        c.gridx = 2 + x_offset;
        c.gridy = 2 + y_offset;
        c.gridheight = 1;
        c.weighty = 0.1;
        c.weightx = 0.1;
        cont.add(score, c);
        
        queue.setSize(100, 0);
        c.gridx = 0 + x_offset;
        c.gridy = 0 + y_offset;
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
        for(int i = 0; i < runtime.getNbPlayers(); i++)
            runtime.addObservers(i, grids[i], scores[i], queues[i], this);
    }
    
    public void shown()
    {
        bgSound.play();
        bgSound.setVolume(0.1);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(arg instanceof modele.general.TimedRuntime.TerminatedEventArg)
        {
            bgSound.stop();
            return;
        }
        if(arg instanceof modele.general.TimedRuntime.PauseResumeEventArg)
        {
            modele.general.TimedRuntime.PauseResumeEventArg argv = (modele.general.TimedRuntime.PauseResumeEventArg)arg;
            
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
        for(int cmd = 0; cmd < keysGeneral.length; cmd++)
            if(keysGeneral[cmd] == e.getKeyCode())
                switch(cmd)
                {
                    case 0:
                        for(TetrisRuntime r : runtime.getRuntime())
                            r.Pause();
                        
                        int res = JOptionPane.showOptionDialog(null,
                                    "Exit?",
                                    "Exit?",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.WARNING_MESSAGE,
                                    null, new String[] { "Exit", "Continue the game" }, null);
                        
                        if(res == 0)
                            System.exit(0);
                        
                        for(TetrisRuntime r : runtime.getRuntime())
                            r.Resume();
                        return;

                    case 1:
                        for(TetrisRuntime r : runtime.getRuntime())
                            r.PauseResume();
                        return;
                }
        
        TetrisRuntime run;
        for(int player = 0; player < keysMap.length; player++)
        {
            run = runtime.getRuntime(player);
            if(run != null)
                for(int cmd = 0; cmd < keysMap[player].length; cmd++)
                    if(keysMap[player][cmd] == e.getKeyCode())
                        switch(cmd)
                        {
                            case 0:
                                run.MoveLeft();
                                return;

                            case 1:
                                run.MoveRight();
                                return;

                            case 2:
                                run.PushBottom();
                                return;

                            case 3:
                                run.Rotate();
                                return;
                        }
        }
        
        if(System.getProperty("Debug") != null)
            System.out.println("KeyCode [" + e.getKeyChar() + " : " + e.getKeyCode() + "]");
    }

    @Override
    public void keyReleased(KeyEvent e)
    { }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menu events">
    @Override
    public void menuSelected(MenuEvent me)
    {
        //System.out.println("AAA");
    }

    @Override
    public void menuDeselected(MenuEvent me)
    { }

    @Override
    public void menuCanceled(MenuEvent me)
    { }
    //</editor-fold>
}
