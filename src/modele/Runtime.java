/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author p1002239
 */
public class Runtime implements Runnable, KeyListener
{
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
            
            while(!finished)
            {
                currentPiece.moveDown();
                if(grid.IsPossible(currentPiece))
                    grid.setVolatilePiece(currentPiece);
                else
                {
                    currentPiece.cancelLastMove();
                    nextPiece();
                }
                
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException ex)
        { }
    }
    
    private void nextPiece()
    {
        int score = grid.setPersistentPiece(currentPiece);
        this.score.addScore(score);
        
        currentPiece = queue.getNext();
        finished = grid.IsInCollision(currentPiece);
        
        if(!finished)
            grid.setVolatilePiece(currentPiece);
    }
    
    public void setObservers(vue.Grid grid, vue.Score score)
    {
        this.grid.addObserver(grid);
        this.score.addObserver(score);
    }

    //<editor-fold desc="Keys">
    @Override
    public void keyTyped(KeyEvent e)
    { }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(finished)
            return;
            
        switch(e.getKeyChar())
        {
            // Left
            case 'Q':
            case 'q':
                currentPiece.moveLeft();
                if(grid.IsPossible(currentPiece))
                    grid.setVolatilePiece(currentPiece);
                else
                    currentPiece.cancelLastMove();
                break;
                
            // Right
            case 'D':
            case 'd':
                currentPiece.moveRight();
                if(grid.IsPossible(currentPiece))
                    grid.setVolatilePiece(currentPiece);
                else
                    currentPiece.cancelLastMove();
                break;
                
            // Direct bottom
            case 'S':
            case 's':
                if(grid.IsPossible(currentPiece))
                {
                    do
                    {
                        currentPiece.moveDown();
                    } while(grid.IsPossible(currentPiece));

                    currentPiece.cancelLastMove();
                    nextPiece();
                }
                break;
                
            case ' ':
                currentPiece.rotateRight90();
                if(grid.IsPossible(currentPiece))
                    grid.setVolatilePiece(currentPiece);
                else
                    currentPiece.cancelLastRotate();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    { }
    //</editor-fold>
    
    
}
