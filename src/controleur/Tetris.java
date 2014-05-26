/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import modele.Grid;
import vue.GameWindow;
import modele.Runtime;

/**
 *
 * @author p1002239
 */
public class Tetris
{

    public static void main(String[] args)
    {
        System.setProperty("Debug", "true");
        
        Runtime runtime = new Runtime();
        GameWindow fenetre = new GameWindow(runtime);
        
        fenetre.setVisible(true);
        runtime.run();
    }
    
}
