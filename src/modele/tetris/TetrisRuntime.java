/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.tetris;

import java.awt.Point;
import java.util.Observer;
import modele.general.Grid;
import modele.general.Piece;
import modele.general.PieceQueue;
import modele.general.RuntimeManager;
import modele.general.Score;
import modele.general.TimedRuntime;

/**
 *
 * @author Adrien
 */
public class TetrisRuntime extends TimedRuntime
{
    public TetrisRuntime()
    {
        super();
        
        Piece.setDefaultPosition(new Point(Grid.MAX_W / 2, 0));
        
        grid = new Grid();
        
        queue = new PieceQueue(TetrisPiece.class);
        currentPiece = queue.getNext();
        grid.setVolatilePiece(currentPiece);
        
        score = new Score();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Members">
    private final PieceQueue queue;
    private final Grid grid;
    private final Score score;
    
    private Piece currentPiece;
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="Abstract methods">
    @Override
    public void setObservers(Observer[] obs)
    {
        for(Observer o : obs)
        {
            this.grid.addObserver(o);
            this.score.addObserver(o);
            this.queue.addObserver(o);
            this.addObserver(o);
        }
    }

    @Override
    protected void initialize()
    {
        score.reset();
    }

    @Override
    protected boolean timeIncremented()
    {
        currentPiece.moveDown();
        if(grid.IsPossible(currentPiece))
        {
            grid.setVolatilePiece(currentPiece);
            return false;
        }
        else
        {
            currentPiece.cancelLastMove();
            return true;
        }
    }

    @Override
    protected void terminedGame()
    {
        
    }

    @Override
    protected void uninitialize()
    {
        
    }

    @Override
    protected void error()
    {
        
    }

    @Override
    protected boolean checkTerminated()
    {
        return false;
    }

    @Override
    protected void nextStep()
    {
        int score = grid.setPersistentPiece(currentPiece);
        this.score.addScore(score);
        
        currentPiece = queue.getNext();
        if(grid.IsInCollision(currentPiece))
        { // Game finished
            terminate(this.score.getScore());
        }
        else
            grid.setVolatilePiece(currentPiece);
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Moves">
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
            nextStep();
        }
    }
    //</editor-fold>
}
