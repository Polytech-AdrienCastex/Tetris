/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import modele.general.Event;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class PieceQueue<T extends Piece> extends Observable
{
    public static final int NB_ELEMENTS = 5;
    
    public PieceQueue(Class<T> c)
    {
        this.c = c;
        pieces = new LinkedList();
        
        for(int i = 0; i < NB_ELEMENTS; i++)
            insertNew();
    }
    
    private final Class<T> c;
    
    private final LinkedList<Piece> pieces;
    
    public Piece getNext()
    {
        Piece p = pieces.poll();
        insertNew();
        
        super.setChanged();
        super.notifyObservers(new CurrentPieceChangedEventArg(this, p));
        
        return p;
    }
    
    public Piece[] getList()
    {
        return (Piece[])pieces.toArray();
    }
    
    private void insertNew()
    {
        try
        {
            Piece p = c.newInstance();
            pieces.add(p);
            
            super.setChanged();
            super.notifyObservers(new PieceQueueChangedEventArg(this, pieces.iterator()));
        }
        catch (InstantiationException | IllegalAccessException ex)
        { }
    }
    
    @Override
    public void addObserver(Observer o)
    {
        super.addObserver(o);
        
        super.setChanged();
        super.notifyObservers(new CurrentPieceChangedEventArg(this, pieces.peek()));
        
        super.setChanged();
        super.notifyObservers(new PieceQueueChangedEventArg(this, pieces.iterator()));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Events">
    public class CurrentPieceChangedEventArg extends Event
    {
        public CurrentPieceChangedEventArg(Object sender, Piece piece)
        {
            super(sender);
            this.piece = piece;
        }
        
        private final Piece piece;
        public Piece getPiece()
        {
            return piece;
        }
    }
    public class PieceQueueChangedEventArg extends Event
    {
        public PieceQueueChangedEventArg(Object sender, Iterator<Piece> pieces)
        {
            super(sender);
            this.pieces = pieces;
        }
        
        private final Iterator<Piece> pieces;
        public Iterator<Piece> getPieces()
        {
            return pieces;
        }
    }
    //</editor-fold>
}
