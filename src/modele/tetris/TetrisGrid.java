/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.tetris;

import modele.general.*;
import modele.general.Event;
import java.awt.Color;
import java.awt.Point;
import java.util.Observable;

/**
 *
 * @author p1002239
 */
public class TetrisGrid extends Grid
{
    public TetrisGrid()
    {
        super();
    }
    
    private boolean isFullLine(Color[][] cases, int offset)
    {
        if(cases[0][offset] == DEAD_LINE_COLOR)
            return false;
        
        for(int x = 0; x < MAX_W; x++)
            if(cases[x][offset] == DEFAULT_COLOR)
                return false;
        return true;
    }
    
    @Override
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
}