/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import modele.general.Event;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1002239
 */
public class RuntimeA/* extends Observable implements Runnable*/
{/*
    private static final int START_TIME = 1000; // ms
    private static final int DECREMENT_TIME = 100; // ms
    
    public RuntimeA()
    {
        Piece.setDefaultPosition(new Point(Grid.MAX_W / 2, 0));
        
        grid = new Grid();
        
        queue = new PieceQueue();
        currentPiece = queue.getNext();
        grid.setVolatilePiece(currentPiece);
        
        score = new Score();
    }
    
    private final PieceQueue queue;
    private final Grid grid;
    private final Score score;
    
    private Piece currentPiece;
    private boolean finished;
    

    @Override
    public void run()
    {
        try
        {
            finished = false;
            score.reset();
            
            int time = START_TIME;
            
            while(!finished)
            {
                currentPiece.moveDown();
                if(grid.IsPossible(currentPiece))
                    grid.setVolatilePiece(currentPiece);
                else
                {
                    currentPiece.cancelLastMove();
                    nextPiece();
                    
                    // Accelerer le temps
                    time -= DECREMENT_TIME;
                }
                
                Thread.sleep(time);
                ManagePause();
            }
        }
        catch (InterruptedException ex)
        { }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Pause / Resume">
    private boolean pause = false;
    private void ManagePause()
    {
        if(pause)
        {
            try
            {
                synchronized(this)
                {
                    this.wait();
                }
            }
            catch (InterruptedException ex)
            { }
            finally
            {
                setPause(false);
            }
        }
    }
    public void Pause()
    {
        setPause(true);
    }
    synchronized private void setPause(boolean value)
    {
        pause = value;
        
        this.setChanged();
        this.notifyObservers(new PauseResumeEventArg(this, pause));
    }
    synchronized public void Resume()
    {
        this.notifyAll();
    }
    public void PauseResume()
    {
        if(pause)
            Resume();
        else
            Pause();
    }
    //</editor-fold>
    
    private void nextPiece()
    {
        int score = grid.setPersistentPiece(currentPiece);
        this.score.addScore(score);
        
        currentPiece = queue.getNext();
        finished = grid.IsInCollision(currentPiece);
        
        if(finished)
        { // Game finished
            this.setChanged();
            this.notifyObservers(new TerminatedEventArg(this, this.score.getScore()));
        }
        else
            grid.setVolatilePiece(currentPiece);
    }
    
    public void setObservers(Observer... obs)
    {
        for(Observer o : obs)
        {
            this.grid.addObserver(o);
            this.score.addObserver(o);
            this.queue.addObserver(o);
            this.addObserver(o);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Moves">
    private boolean isBlocked()
    {
        return finished || pause;
    }
    
    public void MoveLeft()
    {
        if(isBlocked())
            return;
        
        currentPiece.moveLeft();
        if(grid.IsPossible(currentPiece))
            grid.setVolatilePiece(currentPiece);
        else
            currentPiece.cancelLastMove();
    }
    public void MoveRight()
    {
        if(isBlocked())
            return;
        
        currentPiece.moveRight();
        if(grid.IsPossible(currentPiece))
            grid.setVolatilePiece(currentPiece);
        else
            currentPiece.cancelLastMove();
    }
    public void Rotate()
    {
        if(isBlocked())
            return;
        
        currentPiece.rotateRight90();
        if(grid.IsPossible(currentPiece))
            grid.setVolatilePiece(currentPiece);
        else
            currentPiece.cancelLastRotate();
    }
    public void PushBottom()
    {
        if(isBlocked())
            return;
        
        if(grid.IsPossible(currentPiece))
        {
            do
            {
                currentPiece.moveDown();
            } while(grid.IsPossible(currentPiece));

            currentPiece.cancelLastMove();
            nextPiece();
        }
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Events">
    public class TerminatedEventArg extends Event
    {
        public TerminatedEventArg(Object sender, int score)
        {
            super(sender);
            value = score;
        }
        
        private final int value;
        public int getFinalScore()
        {
            return value;
        }
    }
    public class PauseResumeEventArg extends Event
    {
        public PauseResumeEventArg(Object sender, boolean pause)
        {
            super(sender);
            value = pause;
        }
        
        private final boolean value;
        public boolean getPause()
        {
            return value;
        }
    }
    //</editor-fold>*/
}
