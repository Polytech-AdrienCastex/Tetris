/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import modele.Grid;
import modele.TetrisRuntime;
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
        
        RuntimeManagerMulti runtime = new RuntimeManagerMulti(3, TetrisRuntime.class);
        GameWindow fenetre = new GameWindow(runtime, 2);
        
        fenetre.setVisible(true);
        runtime.run();
    }
    
}
