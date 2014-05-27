/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modele.general.Grid;
import modele.general.RuntimeManager;
import modele.tetris.TetrisRuntime;
import modele.general.RuntimeManagerMulti;
import modele.general.RuntimeManagerSolo;
import vue.GameWindow;

/**
 *
 * @author p1002239
 */
public class Tetris
{

    public static void main(String[] args)
    {
        System.setProperty("Debug", "true");
        
        int nb_Players = JOptionPane.showOptionDialog(null,
                "How many players?",
                "Choose how many players",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, new String[]
                {
                    "1 player",
                    "2 players",
                    "3 players",
                    "4 players"
                }, null);
        
        if(nb_Players == -1)
            return;
        
        RuntimeManager runtime = new RuntimeManagerMulti(nb_Players + 1, TetrisRuntime.class);
        
        GameWindow fenetre = new GameWindow(runtime, 2);
        
        fenetre.setVisible(true);
        runtime.run();
    }
    
}
