/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1002239
 */
public class Runtime implements Runnable
{
    private static final int START_TIME = 1000; // ms
    private static final int DECREMENT_TIME = 100; // ms
    
    public Runtime()
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
    
    //<editor-fold desc="Pause / Resume">
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
    synchronized public void setPause(boolean value)
    {
        pause = value;
    }
    synchronized public void Resume()
    {
        this.notifyAll();
    }
    //</editor-fold>
    
    private void nextPiece()
    {
        int score = grid.setPersistentPiece(currentPiece);
        this.score.addScore(score);
        
        currentPiece = queue.getNext();
        finished = grid.IsInCollision(currentPiece);
        
        if(!finished)
            grid.setVolatilePiece(currentPiece);
    }
    
    public void setObservers(vue.Grid grid, vue.Score score, vue.PieceQueue queue)
    {
        this.grid.addObserver(grid);
        this.score.addObserver(score);
        this.queue.addObserver(queue);
    }
    
    //<editor-fold desc="Moves">
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
    
    
    //<editor-fold desc="Events">
    public class TerminatedEventArg
    {
        public TerminatedEventArg(int score)
        {
            value = score;
        }
        
        private final int value;
        public int getFinalScore()
        {
            return value;
        }
    }
    //</editor-fold>
}
