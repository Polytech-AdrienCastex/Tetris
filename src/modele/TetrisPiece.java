/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author p1002239
 */
public class TetrisPiece extends Piece
{
    public TetrisPiece()
    {
        super();
    }
    
    @Override
    protected void generate()
    {
        switch(new Random().nextInt(7))
        {
            case 0:
                regenerateMatrix(4, 1, true);
                
                setColor(Color.RED);
                break;
                
            case 1:
                regenerateMatrix(3, 2, false);
                
                matrix[0][0] = true;
                matrix[1][0] = true;
                matrix[2][0] = true;
                matrix[2][1] = true;
                
                setColor(Color.WHITE);
                break;
                
            case 2:
                regenerateMatrix(3, 2, false);
                
                matrix[0][0] = true;
                matrix[1][0] = true;
                matrix[2][0] = true;
                matrix[0][1] = true;
                
                setColor(Color.ORANGE);
                break;
                
            case 3:
                regenerateMatrix(2, 2, true);
                
                setColor(Color.YELLOW);
                break;
                
            case 4:
                regenerateMatrix(3, 2, false);
                
                matrix[0][1] = true;
                matrix[1][1] = true;
                matrix[1][0] = true;
                matrix[2][0] = true;
                
                setColor(Color.MAGENTA);
                break;
                
            case 5:
                regenerateMatrix(3, 2, false);
                
                matrix[0][0] = true;
                matrix[1][0] = true;
                matrix[2][0] = true;
                matrix[1][1] = true;
                
                setColor(Color.CYAN);
                break;
                
            case 6:
                regenerateMatrix(3, 2, false);
                
                matrix[0][0] = true;
                matrix[1][0] = true;
                matrix[1][1] = true;
                matrix[2][1] = true;
                
                setColor(Color.GREEN);
                break;
        }
        
        setPosition(getDefaultPosition());
    }
}
