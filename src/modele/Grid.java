/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;

/**
 *
 * @author p1002239
 */
public class Grid extends Observable
{
    public static final int MAX_W = 15;
    public static final int MAX_H = 15;
    public static final Color DEFAULT_COLOR = new Color(10, 10, 10);
    
    public Grid()
    {
        super();
        
        this.cases_persistent = new Color[MAX_W][MAX_H];
        
        for(int x = 0; x < MAX_W; x++)
            for(int y = 0; y < MAX_H; y++)
                this.cases_persistent[x][y] = DEFAULT_COLOR;
    }
    
    private final Color[][] cases_persistent;
    
    public void setVolatilePiece(Piece p)
    {
        if(!p.isFixed())
        {
            Color[][] cases = (Color[][])DeepArrayClone(this.cases_persistent);
            setPiece(p, cases);
        }
    }
    public int setPersistentPiece(Piece p)
    {
        p.fixe();
        return setPiece(p, this.cases_persistent);
    }
    private int setPiece(Piece p, Color[][] cases)
    {
        Point pos = p.getPosition();
        Point size = p.getSize();
        
        for(int x = 0; x < size.x; x++)
            for(int y = 0; y < size.y; y++)
                if(p.getCase(x, y))
                    cases[pos.x + x][pos.y + y] = p.getColor();
        
        super.setChanged();
        super.notifyObservers(new GridChangedEventArg(this, cases, cases == this.cases_persistent));
        
        return getScore(cases);
    }
    private boolean isFullLine(Color[][] cases, int offset)
    {
        for(int x = 0; x < MAX_W; x++)
            if(cases[x][offset] == DEFAULT_COLOR)
                return false;
        return true;
    }
    protected int getScore(Color[][] cases)
    {
        int nb_decal = 0;
        
        for(int y = 0; y < MAX_H; y++)
            if(isFullLine(cases, y))
            {
                nb_decal++;
                
                if(y == 0)
                {
                    for(int x = 0; x < MAX_W; x++)
                        cases[x][0] = DEFAULT_COLOR;
                }
                else
                {
                    for(int y2 = y; y2 > 0; y2--)
                        for(int x = 0; x < MAX_W; x++)
                            cases[x][y2] = cases[x][y2 - 1];
                }
                
                y--;
            }
        
        return nb_decal;
    }
    
    
    private Color[][] DeepArrayClone(Color[][] array)
    {
        Color[][] cloned = new Color[array.length][];
        
        for(int i = 0; i < array.length; i++)
            cloned[i] = array[i].clone();
        
        return cloned;
    }
    
    public boolean IsPossible(Piece p)
    {
        return !IsOutOfBound(p) &&
               !IsInCollision(p);
    }
    public boolean IsInCollision(Piece p)
    {
        Point pos = p.getPosition();
        Point size = p.getSize();
        
        for(int x = 0; x < size.x; x++)
            for(int y = 0; y < size.y; y++)
                if(p.getCase(x, y))
                    if(this.cases_persistent[pos.x + x][pos.y + y] != DEFAULT_COLOR)
                        return true;
        
        return false;
    }
    public boolean IsOutOfBound(Piece p)
    {
        Point pos = p.getPosition();
        Point size = p.getSize();
        
        return (pos.y + size.y > MAX_H ||
                pos.x < 0 ||
                pos.x + size.x > MAX_W);
    }
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Events">
    public class GridChangedEventArg extends Event
    {
        public GridChangedEventArg(Object sender, Color[][] cases, boolean isPersistent)
        {
            super(sender);
            this._cases = cases;
            this._isPersistent = isPersistent;
        }
        
        private final boolean _isPersistent;
        public boolean isPersistent()
        {
            return _isPersistent;
        }
        
        private final Color[][] _cases;
        public Color[][] getCases()
        {
            return _cases;
        }
    }
    //</editor-fold>
}