/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Adrien
 */
public class PieceQueue
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
        Piece p = pieces.pop();
        insertNew();
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
    }
}
