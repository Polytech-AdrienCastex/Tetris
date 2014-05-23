/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

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
        score = value;
        
        super.setChanged();
        super.notifyObservers(new ScoreChangedEventArg(score, nb));
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
    
    public void reset()
    {
        nb = 0;
        setScore(0);
    }
    
    //<editor-fold desc="Events">
    public class ScoreChangedEventArg
    {
        public ScoreChangedEventArg(int score, int nb)
        {
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
