/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Adrien
 */
public class State extends HiddenPanel implements Observer
{
    private static final Color FORE_COLOR = Color.MAGENTA;
    
    public State()
    {
        super();
        super.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = createCenteredPanel(this);
        
        scoreLabel = new JLabel();
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.BOLD, 20));
        scoreLabel.setForeground(FORE_COLOR);
        
        createCenteredPanel(panel, BorderLayout.CENTER).add(scoreLabel);
        
        
        
        this.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 10, 10, 10),
                BorderFactory.createMatteBorder(0, 7, 7, 7, FORE_COLOR)));
        
        sound = new Sound("H:\\ProjetInfo\\Images\\douing.wav");
    }
    private JPanel createCenteredPanel(Container parent)
    {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(Color.BLACK);
        parent.add(panel, BorderLayout.CENTER);
        return panel;
    }
    private JPanel createCenteredPanel(Container parent, String borderLayout)
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        parent.add(panel, borderLayout);
        return panel;
    }
    
    private Sound sound;
    
    private JLabel scoreLabel;
    private JLabel nbLabel;
    private void setScore(int value)
    {
        scoreLabel.setText("Score : " + value);
        nbLabel.setText("Ligne : 0");
    }
    private void setScore(int value, int nb)
    {
        if(value > 0)
        {
            sound.play();
        }
        
        scoreLabel.setText("Score : " + value);
        nbLabel.setText("Ligne" + (nb > 1 ? "s" : "") + " : " + nb);
    }

    @Override
    public void update(Observable obs, Object obj)
    {
        if(obj instanceof modele.Score.ScoreChangedEventArg)
        {
            modele.Score.ScoreChangedEventArg scoreArg = ((modele.Score.ScoreChangedEventArg)obj);
            
            setScore(scoreArg.getScore(), scoreArg.getNb());
        }
    }
    
}
