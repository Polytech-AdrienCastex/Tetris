/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import javax.swing.JOptionPane;

/**
 *
 * @author p1002239
 */
public class ChooseGameTypeWindow
{
    public ChooseGameTypeWindow(int nbMaxPlayers)
    {
        this.nbMaxPlayers = nbMaxPlayers;
    }
    
    private String[] players_texts = null;
    private int nbMaxPlayers;
    
    public int getNbPlayers()
    {
        if(players_texts == null)
        {
            players_texts = new String[nbMaxPlayers];
            for(int i = 1; i <= players_texts.length; i++)
                players_texts[i - 1] = i + " player" + (i > 1 ? "s" : "");
        }
        
        return JOptionPane.showOptionDialog(null,
                "How many players?",
                "Choose how many players",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, players_texts, null);
    }
}
