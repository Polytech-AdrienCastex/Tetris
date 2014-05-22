/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.Score.ScoreChangedEventArg;

/**
 *
 * @author Adrien
 */
public class Score extends JPanel implements Observer
{
    private static Color BG_COLOR = Color.BLACK;
    
    public Score()
    {
        super();
        
        label = new JLabel();
        label.setForeground(Color.WHITE);
        
        this.add(label);
        
        setScore(0);
        
        this.setBackground(BG_COLOR);
    }
    
    private JLabel label;
    private void setScore(int value)
    {
        label.setText("Score : " + value);
    }

    @Override
    public void update(Observable obs, Object obj)
    {
        if(obj instanceof ScoreChangedEventArg)
        {
            int score = ((ScoreChangedEventArg)obj).getScore();
            
            setScore(score);
        }
    }
    
}
