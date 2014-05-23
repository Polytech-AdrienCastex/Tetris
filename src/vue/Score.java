/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import modele.Score.ScoreChangedEventArg;

/**
 *
 * @author Adrien
 */
public class Score extends HiddenPanel implements Observer
{
    private static final Color FORE_COLOR = Color.MAGENTA;
    
    public Score()
    {
        super();
        super.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = createCenteredPanel(this);
        
        scoreLabel = new JLabel();
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.BOLD, 20));
        scoreLabel.setForeground(FORE_COLOR);
        
        createCenteredPanel(panel, BorderLayout.CENTER).add(scoreLabel);
        
        nbLabel = new JLabel();
        nbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nbLabel.setFont(new Font(nbLabel.getFont().getName(), Font.BOLD, 15));
        nbLabel.setForeground(FORE_COLOR);
        
        createCenteredPanel(panel, BorderLayout.SOUTH).add(nbLabel);
        
        
        
        this.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 0, 10),
                BorderFactory.createMatteBorder(7, 7, 0, 7, FORE_COLOR)));
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
    
    private JLabel scoreLabel;
    private JLabel nbLabel;
    private void setScore(int value)
    {
        scoreLabel.setText("Score : " + value);
        nbLabel.setText("Ligne : 0");
    }
    private void setScore(int value, int nb)
    {
        scoreLabel.setText("Score : " + value);
        nbLabel.setText("Ligne" + (nb > 1 ? "s" : "") + " : " + nb);
    }

    @Override
    public void update(Observable obs, Object obj)
    {
        if(obj instanceof ScoreChangedEventArg)
        {
            ScoreChangedEventArg scoreArg = ((ScoreChangedEventArg)obj);
            
            setScore(scoreArg.getScore(), scoreArg.getNb());
        }
    }
    
}
