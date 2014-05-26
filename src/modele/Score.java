/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import modele.general.Event;
import java.util.Observable;

/**
 *
 * @author Adrien
 */
public class Score extends Observable
{
    public Score()
    {
        super();
    }
    
    private int score;
    private int nb;
    private void setScore(int value)
    {
        if(score != value)
        {
            score = value;

            super.setChanged();
            super.notifyObservers(new ScoreChangedEventArg(this, score, nb));
        }
    }
    
    public void addScore(int value)
    {
        if(value == 0)
            setScore(score);
        else
        {
            nb++;
            setScore(score + (int)Math.pow(2, value * 2));
        }
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void reset()
    {
        nb = 0;
        score = 0;
        
        super.setChanged();
        super.notifyObservers(new ScoreChangedEventArg(this, score, nb));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Events">
    public class ScoreChangedEventArg extends Event
    {
        public ScoreChangedEventArg(Object sender, int score, int nb)
        {
            super(sender);
            this.score = score;
            this.nb = nb;
        }
        
        private final int score;
        public int getScore()
        {
            return score;
        }
        
        private final int nb;
        public int getNb()
        {
            return nb;
        }
    }
    //</editor-fold>
}
