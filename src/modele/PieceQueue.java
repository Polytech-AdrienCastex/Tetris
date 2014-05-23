/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Adrien
 */
public class PieceQueue extends Observable
{
    public static final int NB_ELEMENTS = 5;
    
    public PieceQueue()
    {
        pieces = new LinkedList();
        
        for(int i = 0; i < NB_ELEMENTS; i++)
            insertNew();
    }
    
    private final LinkedList<Piece> pieces;
    
    public Piece getNext()
    {
        Piece p = pieces.poll();
        insertNew();
        
        super.setChanged();
        super.notifyObservers(new CurrentPieceChangedEventArg(p));
        
        return p;
    }
    
    public Piece[] getList()
    {
        return (Piece[])pieces.toArray();
    }
    
    private void insertNew()
    {
        Piece p = new Piece();
        pieces.add(p);
        
        super.setChanged();
        super.notifyObservers(new PieceQueueChangedEventArg(pieces.iterator()));
    }
    
    @Override
    public void addObserver(Observer o)
    {
        super.addObserver(o);
        
        super.setChanged();
        super.notifyObservers(new CurrentPieceChangedEventArg(pieces.peek()));
        
        super.setChanged();
        super.notifyObservers(new PieceQueueChangedEventArg(pieces.iterator()));
    }
    
    //<editor-fold desc="Events">
    public class CurrentPieceChangedEventArg
    {
        public CurrentPieceChangedEventArg(Piece piece)
        {
            this.piece = piece;
        }
        
        private final Piece piece;
        public Piece getPiece()
        {
            return piece;
        }
    }
    public class PieceQueueChangedEventArg
    {
        public PieceQueueChangedEventArg(Iterator<Piece> pieces)
        {
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
