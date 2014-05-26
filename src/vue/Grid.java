/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import modele.general.Piece;

/**
 *
 * @author p1002239
 */
public class Grid extends HiddenPanel implements Observer
{
    public Grid()
    {
        super();
        
        GridLayout layout = new GridLayout(modele.general.Grid.MAX_H, modele.general.Grid.MAX_W);
        super.setLayout(layout);
        
        layout.setHgap(1);
        layout.setVgap(1);
        
        this.cases = new Case[modele.general.Grid.MAX_H][modele.general.Grid.MAX_W];
        for(int y = 0; y < modele.general.Grid.MAX_W; y++)
            for(int x = 0; x < modele.general.Grid.MAX_H; x++)
            {
                Case elem = new Case();
                this.add(elem);
                this.cases[x][y] = elem;
            }
        
        this.setBackground(Color.black);
        this.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, Color.magenta));
        
        sound = new Sound("H:\\ProjetInfo\\Images\\boup.wav");
    }
    
    private Sound sound;
    
    private Case[][] cases;
    
    public Case getCase(int x, int y)
    {
        return this.cases[x][y];
    }
    
    @Override
    public void update(Observable obs, Object obj)
    {
        if(obj instanceof modele.general.Grid.GridChangedEventArg)
        {
            modele.general.Grid.GridChangedEventArg gridArg = (modele.general.Grid.GridChangedEventArg)obj;
            
            if(gridArg.isPersistent())
            {
                // Son
                sound.play();
            }
            
            Color[][] colors = gridArg.getCases();
            
            for(int x = 0; x < modele.general.Grid.MAX_W; x++)
                for(int y = 0; y < modele.general.Grid.MAX_H; y++)
                    cases[x][y].setColor(colors[x][y]);
            
            
            this.getParent().repaint();
            return;
        }
        
        if(obj instanceof modele.general.TimedRuntime.TerminatedEventArg)
        {
            for(int x = 0; x < modele.general.Grid.MAX_W; x++)
                for(int y = 0; y < modele.general.Grid.MAX_H; y++)
                    if(cases[x][y].getColor() != modele.general.Grid.DEFAULT_COLOR)
                        cases[x][y].setColor(Color.GRAY);
            
            this.getParent().repaint();
            return;
        }
    }
    
}
