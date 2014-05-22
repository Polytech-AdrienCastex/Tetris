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
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        
        label = new JLabel();
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 20));
        label.setForeground(FORE_COLOR);
        
        panel.add(label, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        
        
        
        this.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 0, 10),
                BorderFactory.createMatteBorder(7, 7, 0, 7, FORE_COLOR)));
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
