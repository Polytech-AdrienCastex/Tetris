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
        
        score = 0;
    }
    
    private int score;
    private void setScore(int value)
    {
        score = value;
        
        super.setChanged();
        super.notifyObservers(new ScoreChangedEventArg(score));
    }
    
    public void addScore(int value)
    {
        if(value == 0)
            setScore(score);
        else
            setScore(score + (int)Math.pow(2, value * 2));
    }
    
    public void reset()
    {
        setScore(0);
    }
    
    //<editor-fold desc="Events">
    public class ScoreChangedEventArg
    {
        public ScoreChangedEventArg(int score)
        {
            value = score;
        }
        
        private final int value;
        public int getScore()
        {
            return value;
        }
    }
    //</editor-fold>
}
